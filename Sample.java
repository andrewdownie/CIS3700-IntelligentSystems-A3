//Andrew Downie 0786342
import java.util.LinkedList;
import java.lang.Math.*;
import java.util.Arrays;
import java.util.List;
import java.util.*;


public class Sample{
    Scheme scheme;
    List<Example> exampleList;


    ///
    /// Sample
    ///         : Create a sample from a scheme, and a text file filled with data that follows the scheme
    public Sample(Scheme scheme, String sampleFileContents){
        String[] exampleFileLines = sampleFileContents.split("[\\r\\n]+");    
        exampleList = new LinkedList<Example>();
        this.scheme = scheme;

        ///
        /// Split the first line into the list of attributes used
        ///
        String[] attributes = exampleFileLines[0].split(" ");
        String functionOutput = attributes[attributes.length - 1];
        attributes = Arrays.copyOf(attributes, attributes.length-1);


        ///
        /// Check for errors in the first line of data file
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

        if(!functionOutput.equals(scheme.functionOutput.name)){
            System.out.println("Function output definition(" + functionOutput + ") on first line of data file, did not match function output (" + scheme.functionOutput.name + ") definition of scheme file.");
            System.out.println("Exitting...");
            System.exit(5);
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
            currentExampleLine = exampleFileLines[i].trim().split("\\s+");
            attributeIndexList = new int[scheme.attList.size() + 1];

            String currentExampleOutput = currentExampleLine[currentExampleLine.length - 1];
            currentExampleLine = Arrays.copyOf(currentExampleLine, currentExampleLine.length - 1);


            //: Go through each attribute value of the current example (including class / output)
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

            //: validate function output
            currentAttributeIndex = scheme.functionOutput.IndexOfValue(currentExampleOutput);

            if(currentAttributeIndex < 0){
                System.out.println("Attribute: " + scheme.functionOutput.name + ", does not contain value: " + currentExampleOutput);
                System.out.println("Exiting...");
                System.exit(5);
            }

            attributeIndexList[attributeIndexList.length - 1] = currentAttributeIndex;


            //: Create new example from the int list representing attribute values, and then add the new example to this scheme's list of attributes
            exampleList.add(new Example(attributeIndexList));
        }

        
    }


    ///
    /// Sample
    ///         : Create a sample from a scheme, and a list of examples that follow the scheme
    public Sample(Scheme scheme, List<Example> exampleList){
        this.exampleList = exampleList;
        this.scheme = scheme;
    }


    ///
    /// Sample
    ///         : Create a sample with an empty exampleList
    public Sample(Scheme scheme){
        this.scheme = scheme;
        exampleList = new LinkedList<Example>();
    }


    ///
    /// AddExample
    ///         : An an example to the exampleList
    public void AddExample(Example e){
        exampleList.add(e);
    }


    ///
    /// toString
    ///         : map the indicies of the examples, to the attribute values they correspond to to make things human readable
    public String toString(){
        String output = "Example list:\n";

        for(int i = 0; i < exampleList.size(); i++){
            output += "\tExample" + i + ":\t{";

            for(int j = 0; j < exampleList.get(i).values.length; j++){

                if(j < exampleList.get(i).values.length - 1){
                    int curIndex = exampleList.get(i).values[j];
                    output += scheme.attList.get(j).values[curIndex]; 
                }
                else{
                    int curIndex = exampleList.get(i).values[j];
                    output += scheme.functionOutput.values[curIndex]; 
                }

                if(j < exampleList.get(i).values.length - 1){
                    output += ", ";
                }
            }


            output += "}\n";
        }

        return output;
    }

    
    ///
    /// getRemainder
    ///
    public double getRemainder(Attribute a){
        int size = exampleList.size();
        int m = a.values.length;

        Sample[] subSamples = CreateSubSamples(a);




        int[] subcnt = new int[m];

        for(int i = 0; i < m; i++){
            subcnt[i] = subSamples[i].exampleList.size();
        }



        double remainder = 0;
        for(int i = 0; i < m; i++){

            double pr = (double)subcnt[i]/size;
            double I = (double)subSamples[i].infoFmGp(); 


            if(pr > 0){
                remainder += (double)pr * I;
            }

        }

        return remainder;

    }


    ///
    /// CreateSubSamples
    ///         : Breaks this sample into subsamples based on the given attribute
    private Sample[] CreateSubSamples(Attribute splitter){

        int m = splitter.values.length;

        List<Example> tempAttList = new LinkedList<Example>();
        int attIndex = scheme.AttributeIndex(splitter);
        Sample[] subSamples = new Sample[m];


        for(int i = 0; i < m; i++){
            for(Example e: exampleList){

                if(i == e.values[attIndex]){
                    tempAttList.add(e);
                }

            }

            subSamples[i] = new Sample(scheme, tempAttList);
            tempAttList = new LinkedList<Example>();
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

        if(size == 0){
            return 0;
        }


        double I = 0;
        for(int i = 0; i < outputCount; i++){
            double ratio = (double)count[i] / size;
            if(ratio > 0){
                I = I - (ratio * Log2(ratio));
            }
        }

        return I;
    }
    

    ///
    /// getAttribute
    ///
    public Attribute getAttribute(List<Attribute> attrib){
        int k = scheme.attList.size() - 1;
        double I = infoFmGp();

        

        Attribute bestA = null;
        double remainder = 0;
        double maxGain = -1;
        double gain = 0;


        for(Attribute a: attrib){
            remainder = getRemainder(a);
            gain = I - remainder;
            if(gain > maxGain){
                maxGain = gain;
                bestA = a;
            }

        }


        return bestA;
    }


    private double Log2(double value){
        return Math.log(value) / Math.log(2);
    }
    


    ///
    /// MajorityOutput
    ///         : Finds the FVF of this sample, and returns its index 
    public int MajorityOutput(){
        int[] count = CountOuputOfExamples(); 
        int biggestIndex = -1;
        int biggestCount = -1;


        for(int i = 0; i < count.length; i++){
            if(count[i] > biggestCount){
                biggestCount = count[i];
                biggestIndex = i;
            }
        }


        return biggestIndex;

    }



    ///
    /// SingleOutput
    ///         : If all the examples share a single output, then the index of that output is returned, otherwise -1 is returned
    public int SingleOutput(){
        int[] count = CountOuputOfExamples();

        for(int i = 0; i < count.length; i++){
            if(count[i] == exampleList.size()){
                return i;
            }
        }

        return -1;
    }


}
