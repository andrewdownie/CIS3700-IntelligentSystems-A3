import java.util.LinkedList;
import java.lang.Math.*;
import java.util.List;
import java.util.*;
import static java.lang.System.out;

public class Sample{
    Scheme scheme;
    List<Example> exampleList;
    String sampleName;

    ///
    /// Sample
    ///         : Create a sample from a scheme, and a text file filled with data that follows the scheme
    public Sample(Scheme scheme, String sampleFileContents){
        sampleName = "Root";

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
    /// Sample
    ///         : Create a sample from a scheme, and a list of examples that follow the scheme
    public Sample(Scheme scheme, List<Example> exampleList, String sampleName){
        this.exampleList = exampleList;
        this.sampleName = sampleName;
        this.scheme = scheme;
    }


    ///
    /// toString
    ///         : map the indicies of the examples, to the attribute values they correspond to 
    public String toString(){
        String output = "Example list(" + sampleName + "):\n";

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

    public void getRemainder(Attribute a){
        int size = exampleList.size();
        int m = a.values.length;

        Sample[] subSamples = CreateSubSamples(a);


        for(Sample s: subSamples){
            System.out.println(s.toString());
        }

        // split g by value of a, into subg [1..m];
        //      -> so I think we are going to split this sample, into 1..m "subsamples"
        // create subcnt[1..m]
    }


    ///
    /// CreateSubSamples
    ///         : Breaks this sample into subsamples based on the given attribute
    private Sample[] CreateSubSamples(Attribute splitter){

        int m = splitter.values.length;

        List<Example> tempList = new LinkedList<Example>();
        int attIndex = scheme.AttributeIndex(splitter);
        Sample[] subSamples = new Sample[m];


        for(int i = 0; i < m; i++){
            for(Example e: exampleList){

                if(i == e.values[attIndex]){
                    tempList.add(e);
                }

            }

            subSamples[i] = new Sample(scheme, tempList, scheme.attList.get(attIndex).values[i]);
            tempList = new LinkedList<Example>();
        }

        return subSamples;
    }


    ///
    /// CountOuputOfExamples
    ///         : Go through each possible function output, and tally the number of examples that share that output result
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


    ///
    /// infoFmGp
    ///
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
