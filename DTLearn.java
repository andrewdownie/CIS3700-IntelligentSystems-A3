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



        //Test infoFmGp(...)
        System.out.println(sample.infoFmGp());
    }


} 
