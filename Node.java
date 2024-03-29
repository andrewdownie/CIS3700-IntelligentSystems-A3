//Andrew Downie 0786342
import java.util.Arrays;
import java.util.List;
import java.util.*;

public class Node{
    String labelOfLink;
    String labelOfNode;
    Node parent;
    List<Node> children;
    int depth;


    //: The index for this node from left to right for its depth in the tree
    int IndexAtDepth = 0;

    public Node(String labelOfNode){
        this.labelOfNode = labelOfNode;
        children = new LinkedList<Node>();
    }

    public void LinkNode(Node parent, String labelOfLink){
        this.labelOfLink = labelOfLink;
        this.parent = parent;
    }


    public void AddChild(Node child){
        children.add(child);
    }


}