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
        Node tr = new Node("TR a: " + a.name);
        int m = majorityOutput; 


        //: For each value of attribute :: wat is this
        for(String attVal: a.values){
            Sample subg = new Sample(scheme);

            for(String exVal: g.exampleList.values){
                if(attVal.equals(exVal)){
                    subg.AddExample(attVal);
                }
            }

            Node subtr = learnDecisionTree(subg, attrib.remove(a), m);

            //subtr.LinkNode(tr, "label of link here");
        }


        return tr;
    }




} 
