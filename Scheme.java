import java.util.LinkedList;
import java.util.List;
import java.util.*;


public class Scheme{



    List<Attribute> attList;
    Attribute functionOutput;

    public Scheme(String schemeFileContents){
        attList = new LinkedList<Attribute>();

        int attributeCount;

        String attName, attValues;
        int attValCount;

        String[] schemeFileLines = schemeFileContents.split("[\\r\\n]+");

        attributeCount = Integer.parseInt(schemeFileLines[0]);

        for(int i = 1; i < attributeCount  * 3; i+=3){
            attName = schemeFileLines[i + 0];
            attValCount = Integer.parseInt(schemeFileLines[i + 1]);
            attValues = schemeFileLines[i + 2];

            attList.add(new Attribute(attName, attValCount, attValues));
        }

        functionOutput = attList.get(attList.size() - 1);
        attList.remove(attList.size() - 1);


    }

    public int AttributeIndex(Attribute a){
        for(int i = 0; i < attList.size(); i++){
            if(attList.get(i).equals(a)){
                return i;
            }
        }

        return -1;
    }

   
    public String toString(){
        String output = "";

        for(int i = 0; i < attList.size(); i++){
            output += attList.get(i).toString() + "\n";
        }

        return output;
    }

}