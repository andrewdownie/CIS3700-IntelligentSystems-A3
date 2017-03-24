public class Example{
    String[] values;    


    public Example(String[] example){
        values = example; 
    }

    public String toString(){
        String output = "{";

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