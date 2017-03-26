public class DTLearn{


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
        Scheme scheme = new Scheme(scheme_contents);
        //System.out.println(scheme);

        ///
        /// Example setup
        ///
        Sample sample = new Sample(scheme, data_contents);
        //System.out.println(sample);


        //System.out.println(sample.getRemainder(scheme.attList.get(5)));
        System.out.println("Attribute choosen: " + sample.getAttribute(scheme.attList));



        //Test infoFmGp(...)
        //System.out.println(sample.infoFmGp());
    }


    //... I think sMajor is just a name that gets put on the node, so couldn't I just use the name from the current sample
    private void learnDecisionTree(Sample g, Scheme attrib, String sMajor){

        if(g.exampleList == null || g.exampleList.size()){
            //return node labeled: sMajor
        }

        if(g.SingleOutput() > -1){
            //return a node labeld majorityValue(g);
        }

    }

} 
