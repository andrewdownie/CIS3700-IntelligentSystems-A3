public class Example{

    int[] values;    


    public Example(int[] example){
        values = new int[example.length];
        for(int i = 0; i < example.length; i++){
            values[i] = example[i];
        }
    }

    public int functionOutput(){
        return values[values.length - 1];
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