


public class Node{
    String labelOfLink;
    String labelOfNode;
    Node parent;

    public Node(String labelOfNode){
        this.labelOfNode = labelOfNode;
    }

    public void LinkNode(Node parent, String labelOfLink){
        this.labelOfLink = labelOfLink;
        this.parent = parent;
    }


}