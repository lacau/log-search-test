package br.com.moip;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import br.com.moip.model.FileResult;
import br.com.moip.service.FileService;
import br.com.moip.service.MathService;
import br.com.moip.service.PrinterService;

/**
 * Created by Lacau on 13/06/2017.
 */
public class Main {

    private static final int MAX_RESULTS = 3;

    public static void main(String[] args) throws IOException {
        //long init = System.currentTimeMillis();
        if(args.length == 0) {
            PrinterService.getInstance().printHelp();
            System.exit(0);
        }

        final FileResult fileResult = FileService.getInstance().parseFile(args[0]);
        final List<Map.Entry<String, Integer>> urls = MathService.getInstance().calculateMostCalledURLs(fileResult, MAX_RESULTS);
        PrinterService.getInstance().printFormattedEntries(urls);

        final List<Map.Entry<String, Integer>> status = MathService.getInstance().calculateResponseStatusCount(fileResult);
        PrinterService.getInstance().printFormattedEntries(status);

        if(!fileResult.hasData()) {
            System.out.println("No data found! file: " + args[0]);
            System.exit(0);
        }

        //        System.out.println("URL COUNT: " + fileResult.getRequestURL().size());
        //        System.out.println("STATUS COUNT: " + fileResult.getResponseStatus().size());
        //        System.out.println("START: " + init);
        //        System.out.println("END: " + System.currentTimeMillis());
        //        System.out.println("ELAPSED: " + (System.currentTimeMillis() - init));
    }
}
