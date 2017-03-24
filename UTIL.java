import java.util.LinkedList;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.*;

public class UTIL{

    ///
    /// CLA_ErrorChecking
    ///         : Ensure there are two CommandLineArguments
    public static void CLA_ErrorChecking(int CLA_count){
        if(CLA_count != 2){
            System.out.println("\nERROR:"); 
            System.out.println("\t- 2 command line args required, but " + CLA_count + " command line args found\n");

            System.out.println("HINT:");
            System.out.println("\t- arg0: scheme path");
            System.out.println("\t- arg1: data path");
            System.out.println("\nExiting...\n");
            System.exit(1);
        }
    }

    public static String ReadEntireFile(String filePath){
            String fileContents = "";

            try{
                fileContents = new String(Files.readAllBytes(Paths.get(filePath)));
            }
            catch(Exception e){
                System.out.println("Error opening exp file: " + filePath);
            }

            return fileContents;
    }

}