package remitlyIntern;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Scanner;

public class Main {

    public static void main(String[] args){
        Scanner scanner = new Scanner(System.in);
        int decision;
        String json;
        String file;
        String line;
        boolean result;
        RolePolicyChecker checker = new RolePolicyChecker();
        while(true) {
            System.out.println("Would you like to check JSON file{0}, or JSON string {1}, to exit enter {2}");
            try {
                line = scanner.nextLine();
                decision = Integer.parseInt(line);
            } catch (Exception e) {
                System.out.println(e.getMessage());
                continue;
            }
            if(decision==0){
                System.out.println("Enter file path.");
                file = scanner.nextLine();
                try {
                    json = Files.readString(Path.of(file));
                }
                catch (Exception e){
                    System.out.println(e.getMessage());
                    continue;
                }
                try {
                    result = checker.verifyPolicy(json);
                    System.out.println("The result was: "+result);
                }
                catch (Exception e){
                    System.out.println("EXCEPTION: "+e.getMessage());
                }
            }
            else if (decision == 1) {
                System.out.println("Paste json file, press enter on empty line.");
                StringBuilder builder = new StringBuilder();
                while (true) {
                    line = scanner.nextLine();
                    if (line.isEmpty())
                        break;
                    builder.append(line).append("\n");
                }

                json = builder.toString();

                try {
                    result = checker.verifyPolicy(json);
                    System.out.println("The result was: "+result);
                }
                catch (Exception e){
                    System.out.println("EXCEPTION: "+e.getMessage());
                }
            }
            else if (decision == 2) {
                break;
            }
            else
                System.out.println("Unknown command");
        }
    }
}
