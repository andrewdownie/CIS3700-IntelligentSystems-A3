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
        //System.out.println(sample);

        //System.out.println(rootSample);

        //System.out.println("Get remainder: " + rootSample.getRemainder(scheme.attList.get(0)));
        //System.out.println(sample.getRemainder(scheme.attList.get(5)));
        System.out.println("Attribute choosen: " + rootSample.getAttribute(scheme.attList));
        //learnDecisionTree(rootSample, scheme.attList, "Root");



        //Test infoFmGp(...)
        //System.out.println(sample.infoFmGp());
    }


    //... I think sMajor is just a name that gets put on the node, so couldn't I just use the name from the current sample
   /* private static Node learnDecisionTree(Sample g, List<Attribute> attrib, String sMajor){

        if(g.exampleList == null || g.exampleList.size() == 0){
            //return node labeled: sMajor
            return new Node(g, new Sample(scheme, attrib), "testConnectionAttribute", "testConnectionName");
        }

        int singleOutput = g.SingleOutput();
        if(singleOutput > -1){
            //label the node singleOutput;
            //return a node labeld q;
        }


        if(attrib == null || attrib.size() == 0){
            //int majorityVal = g.MajorityValue();
            //return a node labelled majorityValue(g)
        }


        Attribute a = g.getAttribute(attrib);
        
        //tr = new Decision tree with the root a only;
        //Sample tr = new Sample();

        int m = g.MajorityValue();

        for(String val: a.values){
            System.out.println("Val is: " + val);
        }

        return null;
    }*/


} 
