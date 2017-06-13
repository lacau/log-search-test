package br.com.moip.service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.AbstractMap;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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

    public List<AbstractMap.SimpleEntry<String, String>> parseFile(final String path) {
        try {
            final Matcher matcher = LOG_PATTERN.matcher(readFile(path));
            while(matcher.find()) {
                System.out.println(matcher.group(1));
                System.out.println(matcher.group(2));
            }
        } catch(IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    private String readFile(final String path) throws IOException {
        final byte[] bytes = Files.readAllBytes(Paths.get(path));
        return new String(bytes);
    }
}
