//Andrew Downie 0786342
import java.util.LinkedList;
import java.util.List;
import java.util.*;

public class Attribute{
    String name;
    String[] values;
    
    public Attribute(String name, int valueCount, String values){
        this.name = name;

        String[] tempValues = values.split(" ");
        this.values = tempValues;
    }


    public int IndexOfValue(String value){

        for(int i = 0; i < values.length; i++){
            if(values[i].equals(value)){
                return i;
            }
        }

        return -1;
    }

    public String toString(){
        String output = name + ": {";

        for(int i = 0; i < values.length; i++){
            output += values[i];
            if(i < values.length - 1){
                output += ", ";
            }
        }
        output += "}";

        return output;
    } 

}