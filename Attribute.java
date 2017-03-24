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

public boolean ContainsValue(String value){

    for(String val: values){
        if(val.equals(value)){
            return true;
        }
    }

    return false;
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