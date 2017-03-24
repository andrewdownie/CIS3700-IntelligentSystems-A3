import java.util.LinkedList;
import java.util.List;
import java.util.*;


public class Scheme{



    List<Attribute> attList;

    public Scheme(String schemeFileContents){
        attList = new LinkedList<Attribute>();

        int attributeCount;

        String attName, attValues;
        int attValCount;

        String[] schemeFileLines = schemeFileContents.split("[\\r\\n]+");

        attributeCount = Integer.parseInt(schemeFileLines[0]);

        for(int i = 1; i < attributeCount * 3; i+=3){
            attName = schemeFileLines[i + 0];
            attValCount = Integer.parseInt(schemeFileLines[i + 1]);
            attValues = schemeFileLines[i + 2];

            attList.add(new Attribute(attName, attValCount, attValues));

        }

    }

    public bool AttHasValue(int index, String value){
        if(index >= attList.size()){
            return false;
        }

        for(String val: attList.get(i).values){
            if(val.equals(value)){
                return true;
            }
        }
        
        return false;
    }

    public void PrintAttributes(){
        for(int i = 0; i < attList.size(); i++){
            System.out.println(attList.get(i).toString());
        }
    }

}