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

        learnDecisionTree(rootSample, new ArrayList<Attribute>(scheme.attList), "Root sMajor");
    }


    ///
    /// learnDecisionTree
    ///
    public static Node learnDecisionTree(Sample g, List<Attribute> attrib, String sMajor){
        if(g == null){
            return new Node("SMajor: " + sMajor);
        }

        int singleOutput = g.SingleOutput();
        int majorityOutput = g.MajorityOutput();
        
        if(singleOutput >= 0){
            return new Node("SingleOutput: " + singleOutput);
        }

        if(attrib.size() == 0){
            return new Node("MajorityOutput: " + majorityOutput);
        }


        Attribute a = g.getAttribute(attrib);
        int aIndex = scheme.AttributeIndex(a);

        Node tr = new Node("TR a: " + a.name);
        int m = majorityOutput; 


        //: For each value of attribute :: wat is this
        for(int i = 0; i < a.values.length; i++){
            Sample subg = new Sample(scheme);

            for(int j = 0; j < g.exampleList.size(); j++){
                if(a.values[i].equals(g.exampleList.get(j).values[aIndex])){
                    subg.AddExample(g.exampleList.get(j));
                }
            }

            attrib.remove(aIndex);
            Node subtr = learnDecisionTree(subg, attrib, m + "meow");

            subtr.LinkNode(tr, "label of link here");
        }


        return tr;
    }




} 
