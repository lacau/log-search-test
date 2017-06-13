package br.com.moip.service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import br.com.moip.model.FileResult;

/**
 * Created by Lacau on 13/06/2017.
 */
public class FileService {

    private static final Pattern LOG_PATTERN = Pattern.compile("(request_to=\".*\")+.*(response_status=\".*\")+");

    private static final class Holder {
        static final FileService INSTANCE = new FileService();
    }

    private FileService() {
    }

    public static FileService getInstance() {
        return Holder.INSTANCE;
    }

    public FileResult parseFile(final String path) {
        FileResult fileResult = new FileResult();
        try {
            final Matcher matcher = LOG_PATTERN.matcher(readFile(path));
            while(matcher.find()) {
                final String g1 = matcher.group(1);
                final String g2 = matcher.group(2);
                fileResult.addRequestURL(g1.substring(g1.indexOf("\"") + 1, g1.length() - 1));
                fileResult.addResponseStatus(g2.substring(g2.indexOf("\"") + 1, g2.length() - 1));
            }
        } catch(IOException e) {
            e.printStackTrace();
        }

        return fileResult;
    }

    private String readFile(final String path) throws IOException {
        final byte[] bytes = Files.readAllBytes(Paths.get(path));
        return new String(bytes);
    }
}
