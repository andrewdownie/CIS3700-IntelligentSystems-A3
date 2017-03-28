


public class Node{
    public int connectionAttribute;
    public int connectionValue;
    public Sample sample;
    public Sample parent;

    //I have no idea what this needs to do atm.
    public Node(Sample parent, Sample sample, int connectionAttribute, int connectionValue){
        this.connectionAttribute = connectionAttribute;
        this.connectionValue = connectionValue;

        this.parent = parent;
        this.sample = sample;

    }


}