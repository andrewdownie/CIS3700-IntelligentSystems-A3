public class Sample{
    Scheme scheme;

    public Sample(Scheme scheme, String sampleFileContents){
        String[] exampleFileLines = sampleFileContents.split("[\\r\\n]+");    

        ///
        /// Split the first line into the list of attributes used
        ///
        String[] attributes = exampleFileLines[0].split(" ");


        ///
        /// Check for errors in the first line
        ///
        if(attributes.length != scheme.attList.size()){
            System.out.println("Attributes provided in data file did not match the attributes provided in the attributes file");
            System.out.println("Exiting...");
            System.exit(3);
        }

        for(int i = 0; i < attributes.length; i++){
            if(!attributes[i].equals(scheme.attList.get(i).name)){
                System.out.println("Attribute: (" + attributes[i] + ") from data file at index: (" + i + "), does not match attribute: (" + scheme.attList.get(i) + ") from scheme file");
                System.out.println("Exiting...");
                System.exit(4);
            }
        }


        ///
        /// Check for errors in each line of sample, and read the values into Examples
        ///
        String[] currentLine;
        String currentAttribute;

        //: Go through each example
        for(int i = 1; i < exampleFileLines.length; i++){
            currentLine = exampleFileLines[i].split("[ ]+");
            System.out.println("\ncurrentLine: " + exampleFileLines[i]);

            //: Go through each value of the current example
            for(int j = 0; j < currentLine.length; j++){
                currentAttribute = currentLine[j];
                System.out.println("Current att: " + currentAttribute);
                //if(!scheme.attList.get(j).ContainsValue(currentAttribute)){
                    //System.out.println("j: " + scheme.attList.get(i).name + ", does not contain: " + currentAttribute);
                //}
                //Compare current attribute value to possible attribute values for the attribute at the current index
            }
        }

        
    }


    public String toString(){
        return "unimplemented to string of sample class";
    }

}
