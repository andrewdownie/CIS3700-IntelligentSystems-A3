import java.util.Arrays;
import java.util.List;
import java.util.*;

public class DTLearn{
    static Scheme scheme;


    public static void main(String[] args){
        UTIL.ErrChk_ArgCount(args.length);

        ///
        /// File input
        ///
        String scheme_contents, data_contents;
        String scheme_path, data_path;

        scheme_path = args[0];
        data_path = args[1];

        scheme_contents = UTIL.ReadEntireFile(scheme_path);
        data_contents = UTIL.ReadEntireFile(data_path);



        ///
        /// Scheme setup
        ///
        scheme = new Scheme(scheme_contents);
        //System.out.println(scheme);

        ///
        /// Example setup
        ///
        Sample rootSample = new Sample(scheme, data_contents);

        Node rootNode = learnDecisionTree(rootSample, new ArrayList<Attribute>(scheme.attList), "Root sMajor");

        System.out.println(rootNode.labelOfNode);
        for(Node n: rootNode.children){
            System.out.println(n.labelOfNode + ", label: " + n.labelOfLink);
        }


    }


    ///
    /// learnDecisionTree
    ///
    public static Node learnDecisionTree(Sample g, List<Attribute> attrib, String sMajor){
        if(g == null){
            return new Node("SMajor: " + sMajor);
        }

        //for()

        int singleOutput = g.SingleOutput();
        int majorityOutput = g.MajorityOutput();
        
        if(singleOutput >= 0){
            return new Node("SingleOutput: " + scheme.functionOutput.values[singleOutput]);
        }

        if(attrib.size() == 0){
            return new Node("MajorityOutput: " + majorityOutput);
        }


        Attribute a = g.getAttribute(attrib);
        int aIndex = scheme.AttributeIndex(a);

        Node tr = new Node("TR a: " + a.name);
        int m = majorityOutput; 


        //: For each value of attribute
        for(int i = 0; i < a.values.length; i++){
            Sample subg = new Sample(scheme);

            for(int j = 0; j < g.exampleList.size(); j++){
                //System.out.println("a.values[" + i + "]: " + a.values[i] + ", exampleList(j): " + g.exampleList.get(j).values[aIndex]);
                if(i == g.exampleList.get(j).values[aIndex]){
                    subg.AddExample(g.exampleList.get(j));
                }
            }

            if(subg.exampleList.size() == 0){
                subg = null;
            }

            List<Attribute> aList = new LinkedList<Attribute>(attrib);
            aList = RemoveAttrib(aList, a);
            Node subtr = learnDecisionTree(subg, aList, m + "meow");

            subtr.LinkNode(tr, "link label: " + scheme.attList.get(aIndex).values[i]);
            tr.AddChild(subtr);
            
        }


        return tr;
    }

    public static List<Attribute> RemoveAttrib(List<Attribute> attrib, Attribute a){

        for(int i = 0; i < attrib.size(); i++){
            if(attrib.get(i).equals(a)){
                attrib.remove(i);
            }
        }


        return attrib;
    }




} 
