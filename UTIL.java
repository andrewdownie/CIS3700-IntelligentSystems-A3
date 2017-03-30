//Andrew Downie 0786342
import java.util.LinkedList;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.*;

public class UTIL{



    ///
    /// ErrChk_ArgCount 
    ///         : Ensure there are the correct number of CommandLineArguments
    public static void ErrChk_ArgCount(int arg_count){
        int expected_command_line_args = 2;

        if(arg_count != expected_command_line_args){
            System.out.println("\nERROR:"); 
            System.out.println("\t- " + expected_command_line_args + " command line args required,");
            System.out.println("\t\tbut " + arg_count + " command line args found\n");

            System.out.println("HINT:");
            System.out.println("\t- arg0: scheme file path");
            System.out.println("\t- arg1: data file path");
            System.out.println("\nExiting...\n");
            System.exit(1);
        }
    }



    ///
    /// ReadEntireFile
    ///         : Given a full or relative path to a text file, return the entirety of file contents as a string, 
    public static String ReadEntireFile(String filePath){
            String fileContents = "";

            try{
                fileContents = new String(Files.readAllBytes(Paths.get(filePath)));
            }
            catch(Exception e){
                System.out.println("Error opening exp file: " + filePath);
                System.out.println("Exiting...");
                System.exit(2);
            }

            return fileContents;
    }



    ///
    /// PrintDT
    ///         : Prints a decision tree, given the root node
    public static void PrintDT(Node rootNode){
        //: Linearize the decision tree
        List<Node> linearList = new LinkedList<Node>();
        linearList = LinearizeDT(rootNode, 0, linearList);

        //: Find the max depth of the decision tree
        int maxDepth = -1;
        for(Node n: linearList){
            if(n.depth > maxDepth){
                maxDepth = n.depth;
            }
        }

        //: Print the root node of the tree
        System.out.println(rootNode.labelOfNode);
        System.out.println("0\n");

        //: Go through each depth of the tree 
        for(int i = 1; i < maxDepth + 1; i++){

            //: Grab all the nodes at the current depth, and add them to a list
            List<Node> currentDepth = new LinkedList<Node>();
            for(Node n: linearList){
                if(i == n.depth){
                    currentDepth.add(n);
                }
            }

            //: Print the nodes at the same depth
            PrintDT_AtDepth(currentDepth);
        }
    }



    ///
    /// LinearizeDT 
    ///         : Creates a linear version of a tree using recursion. Given the root node, and an empty list, in which the linear tree will be filled into
    private static List<Node> LinearizeDT(Node node, int depth, List<Node> list){
        node.depth = depth;
        list.add(node);
        depth++;


        for(Node n: node.children){
            LinearizeDT(n, depth, list);
        }


        return list;        
    }


    ///
    /// PrintDT_AtDepth
    ///         : Given a list of nodes at the same depth, the nodes will be printed side by side on the command line
    private static void PrintDT_AtDepth(List<Node> nodes){
        int size = nodes.size();



        //: Print the parent node of each node
        for(int i = 0; i < size; i++){
            System.out.print(nodes.get(i).parent.IndexAtDepth + "          ");
        }
        System.out.print("\n");


        //: Print a line for each node
        for(int i = 0; i < size; i++){
            System.out.print("|          ");
        }
        System.out.print("\n");


        //: Print a line for each node, with the name of the attribut value that connects this node to its parent
        for(int i = 0; i < size; i++){
            System.out.print("|" + nodes.get(i).labelOfLink);

            for(int j = nodes.get(i).labelOfLink.length(); j <= 9; j++){
                System.out.print(" ");
            }
        }
        System.out.print("\n");


        //: Print a chevron for each node
        for(int i = 0; i < size; i++){
            System.out.print("v          ");
        }
        System.out.print("\n");


        //: Print the label/output of each node
        for(int i = 0; i < size; i++){
            System.out.print(nodes.get(i).labelOfNode);

            for(int j = nodes.get(i).labelOfNode.length(); j <= 10; j++){
                System.out.print(" ");
            }
        }
        System.out.print("\n");


        //: Print each nodes index at the current depth
        for(int i = 0; i < size; i++){
            System.out.print(i + "          ");
            nodes.get(i).IndexAtDepth = i;
        }
        System.out.print("\n");


        System.out.print("\n");

    }

}