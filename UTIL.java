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
    ///         : Given a full or relative path to a file, return the entirety of file contents as a string, 
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
    /// PrintLinearDT
    ///         : Put this in the util section
    public static void PrintLinearDT(Node rootNode){
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

        //: Print each depth of the decsision tree
        for(int i = 0; i < maxDepth + 1; i++){
            //: Grab all the nodes at the current depth
            List<Node> currentDepth = new LinkedList<Node>();
            for(Node n: linearList){
                if(i == n.depth){
                    currentDepth.add(n);
                }
            }

            //: Go through each node at the current depth
            for(Node n: currentDepth){
                System.out.println("Current depth: " + n.labelOfNode);
            }
            System.out.println("");
        }
    }

    ///
    /// LinearizeDT 
    ///         : Put this in the util section
    public static List<Node> LinearizeDT(Node node, int depth, List<Node> list){
        node.depth = depth;
        list.add(node);
        depth++;

        for(Node n: node.children){
            LinearizeDT(n, depth, list);
        }


        return list;        
    }

}