package br.com.moip;

import br.com.moip.model.FileResult;
import br.com.moip.service.FileService;

/**
 * Created by Lacau on 13/06/2017.
 */
public class Main {

    public static void main(String[] args) {
        long init = System.currentTimeMillis();
        //        if(args.length == 0) {
        //            printHelp();
        //            System.exit(0);
        //        }

        final FileResult fileResult = FileService.getInstance().parseFile("C:\\Users\\Lacau\\Desktop\\moip\\log.txt");
        if(!fileResult.hasData()) {
            System.out.println("No data found! file: " + "C:\\Users\\Lacau\\Desktop\\moip\\log_min.txt");
            System.exit(0);
        }
        System.out.println("URL COUNT: " + fileResult.getRequestURL().size());
        System.out.println("STATUS COUNT: " + fileResult.getResponseStatus().size());
        System.out.println("START: " + init);
        System.out.println("END: " + System.currentTimeMillis());
        System.out.println("ELAPSED: " + (System.currentTimeMillis() - init));
    }

    private static void printHelp() {
        System.out.println("--- HELP ---");
        System.out.println("command line -> 'java -jar log-server.jar <fileName>");
        System.out.println("<fileName> -> log fileName with full path.");
    }
}
