import java.util.LinkedList;
import java.util.List;
import java.util.*;

public class Sample{
    Scheme scheme;
    List<Example> exampleList;

    public Sample(Scheme scheme, String sampleFileContents){
        String[] exampleFileLines = sampleFileContents.split("[\\r\\n]+");    
        exampleList = new LinkedList<Example>();

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
        String[] currentExampleLine;
        String currentAttribute;

        //: Go through each example
        for(int i = 1; i < exampleFileLines.length; i++){
            currentExampleLine = exampleFileLines[i].trim().split("[ ]+", -1);

            //: Go through each value of the current example
            for(int j = 0; j < currentExampleLine.length; j++){
                currentAttribute = currentExampleLine[j];
                if(!scheme.attList.get(j).ContainsValue(currentAttribute)){
                    System.out.println("Attribute: " + scheme.attList.get(j).name + ", does not contain value: " + currentAttribute);
                    System.out.println("Exiiting...");
                    System.exit(5);
                }

            }


            exampleList.add(new Example(currentExampleLine));
        }

        
    }


    public String toString(){
        String output = "Example list:\n";

        for(int i = 0; i < exampleList.size(); i++){
            output += "\tExample" + i + ":\t" + exampleList.get(i).toString() + "\n";
        }

        return output;
    }

}
