public class Sample{
    Scheme scheme;

    public Sample(Scheme scheme, String sampleFileContents){
        String[] exampleFileLines = sampleFileContents.split("[\\r\\n]+");    

        String[] attributes = exampleFileLines[0].split(" ");


        ///
        /// Check for errors in the first line
        ///
        if(attributes.length != scheme.attList.size()){
            System.out.println("Attributes provided in data file did not match the attributes provided in the attributes file");
            System.exit(3);
        }

        for(int i = 0; i < attributes.length; i++){
            if(!attributes[i].equals(scheme.attList.get(i).name)){
                System.out.println("Attribute: (" + attributes[i] + ") from data file at index: (" + i + "), does not match attribute: (" + scheme.attList.get(i) + ") from scheme file");
            }
        }


        

        ///
        /// Check for errors in each line of sample, and read the values into Examples
        ///
        String[] exampleValues;

        //: Go through each example
        for(int i = 1; i < exampleFileLines.length; i++){
            exampleValues = exampleFileLines[i].split(" ");

            //: Go through each value of the current example
            for(int j = 0; j < exampleValues.length; j++){
                //if(!scheme.get(j).hasAttr());
                //Compare current attribute value to possible attribute values for the attribute at the current index
            }
        }

        
    }


    public String toString(){
        return "unimplemented to string of sample class";
    }

}
