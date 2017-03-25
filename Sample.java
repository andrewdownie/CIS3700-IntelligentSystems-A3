import java.util.LinkedList;
import java.lang.Math.*;
import java.util.List;
import java.util.*;
import static java.lang.System.out;

public class Sample{
    Scheme scheme;
    List<Example> exampleList;

    public Sample(Scheme scheme, String sampleFileContents){
        String[] exampleFileLines = sampleFileContents.split("[\\r\\n]+");    
        exampleList = new LinkedList<Example>();
        this.scheme = scheme;

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
        int[] attributeIndexList;
        int currentAttributeIndex;

        //: Go through each example
        for(int i = 1; i < exampleFileLines.length; i++){
            currentExampleLine = exampleFileLines[i].trim().split("[ ]+", -1);
            attributeIndexList = new int[scheme.attList.size()];

            //: Go through each value of the current example
            for(int j = 0; j < currentExampleLine.length; j++){
                currentAttribute = currentExampleLine[j];

                currentAttributeIndex = scheme.attList.get(j).IndexOfValue(currentAttribute);

                if(currentAttributeIndex < 0){
                    System.out.println("Attribute: " + scheme.attList.get(j).name + ", does not contain value: " + currentAttribute);
                    System.out.println("Exiting...");
                    System.exit(5);
                }

                attributeIndexList[j] = currentAttributeIndex;

            }


            exampleList.add(new Example(attributeIndexList));
        }

        
    }



    ///
    /// toString
    ///         : map the indicies of the examples, to the attribute values they correspond to 
    public String toString(){
        String output = "Example list:\n";

        for(int i = 0; i < exampleList.size(); i++){
            output += "\tExample" + i + ":\t{";

            for(int j = 0; j < exampleList.get(i).values.length; j++){
                int curIndex = exampleList.get(i).values[j];

                output += scheme.attList.get(j).values[curIndex]; 

                if(j < exampleList.get(i).values.length - 1){
                    output += ", ";
                }
            }

            output += "}\n";
        }

        return output;
    }

    public void getRemainder(List<Attribute> a){

    }


    ///
    /// CountOuputOfExamples
    ///         : Go through each possible function output, and tally the number of examples that share that function output
    private int[] CountOuputOfExamples(){
        int outputCount = scheme.functionOutput.values.length;
        int[] count = new int[outputCount];

        for(int i = 0; i < outputCount; i++){
            for(Example e: exampleList){
                if(e.functionOutput() == i){
                    count[i]++;
                }
            }
        }


        return count;
    }


    public double infoFmGp(){
        int outputCount = scheme.functionOutput.values.length; 
        int size = exampleList.size();
        int[] count;

        count = CountOuputOfExamples();


        double I = 0;
        for(int i = 0; i < outputCount; i++){
            double ratio = (double)count[i] / size;
            I = I - (ratio * Log2(ratio));
        }

        return I;
    }

    private double Log2(double value){
        return Math.log(value) / Math.log(2);
    }
    

    /// getInfo, getRemainder, getAttribute

}
