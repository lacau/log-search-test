package br.com.moip.service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import br.com.moip.model.FileResult;

/**
 * Created by Lacau on 13/06/2017.
 */
public class FileService {

    private static final Pattern LOG_PATTERN = Pattern.compile("(request_to=\".*\")+.*(response_status=\".*\")+");

    private static final short REGEX_REQUEST_GROUP = 1;

    private static final short REGEX_RESPONSE_GROUP = 2;

    private static final FileService INSTANCE;

    static {
        INSTANCE = new FileService();
    }

    private FileService() {
    }

    public static FileService getInstance() {
        return INSTANCE;
    }

    public FileResult parseFile(final String path) throws IOException {
        Optional.ofNullable(path).orElseThrow(() -> new IllegalArgumentException("path can not be null. path=" + path));

        FileResult fileResult = new FileResult();
        try {
            final Matcher matcher = LOG_PATTERN.matcher(readFile(path));
            while(matcher.find()) {
                final String g1 = matcher.group(REGEX_REQUEST_GROUP);
                final String g2 = matcher.group(REGEX_RESPONSE_GROUP);
                fileResult.addRequestURL(g1.substring(g1.indexOf("\"") + 1, g1.length() - 1));
                fileResult.addResponseStatus(g2.substring(g2.indexOf("\"") + 1, g2.length() - 1));
            }
        } catch(IOException e) {
            e.printStackTrace();
            throw e;
        }

        return fileResult;
    }

    private String readFile(final String path) throws IOException {
        final byte[] bytes = Files.readAllBytes(Paths.get(path));
        return new String(bytes);
    }
}
