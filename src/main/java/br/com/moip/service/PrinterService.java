package br.com.moip.service;

/**
 * Created by Lacau on 13/06/2017.
 */
public class PrinterService {

    private static final class Holder {
        static final PrinterService INSTANCE = new PrinterService();
    }

    private PrinterService() {
    }

    public static PrinterService getInstance() {
        return Holder.INSTANCE;
    }

    public void printHelp() {
        System.out.println("--- HELP ---");
        System.out.println("command line -> 'java -jar log-server.jar <fileName>");
        System.out.println("<fileName> -> log fileName with full path.");
    }
}
