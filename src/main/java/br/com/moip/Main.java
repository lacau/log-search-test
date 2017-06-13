package br.com.moip;

/**
 * Created by Lacau on 13/06/2017.
 */
public class Main {

    public static void main(String[] args) {
        if(args.length == 0) {
            printHelp();
            System.exit(0);
        }
    }

    private static void printHelp() {
        System.out.println("--- HELP ---");
        System.out.println("command line -> 'java -jar log-server.jar <fileName>");
        System.out.println("<fileName> -> log fileName with full path.");
    }
}
