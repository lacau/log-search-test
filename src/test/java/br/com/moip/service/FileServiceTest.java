package br.com.moip.service;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.NoSuchFileException;

import br.com.moip.model.FileResult;
import org.junit.Assert;
import org.junit.Test;

/**
 * Created by Lacau on 13/06/2017.
 */
public class FileServiceTest {

    private static final String FILE_NAME = "log_temp.txt";

    private static final String LOG_PATTERN = " level=info response_body=\"\" request_to=\"<url>\" response_headers= response_status=\"<code>\"";

    private static final String[] URLS = {"https://eagerhaystack.com", "https://surrealostrich.com.br", "https://grimpottery.net.br"};

    private static final String[] STATUS = {"200", "400"};

    private static final FileService SERVICE = FileService.getInstance();

    @Test
    public void parseFile() throws IOException {
        File file = new File(FILE_NAME);
        FileWriter fw = new FileWriter(file);

        String currentURL = LOG_PATTERN.replace("<url>", URLS[0]).replace("<code>", STATUS[0]);
        for(int i = 0; i < 1000; i++) {
            fw.write(currentURL + "\n");
        }

        currentURL = LOG_PATTERN.replace("<url>", URLS[1]).replace("<code>", STATUS[0]);
        for(int i = 0; i < 250; i++) {
            fw.write(currentURL + "\n");
        }

        currentURL = LOG_PATTERN.replace("<url>", URLS[2]).replace("<code>", STATUS[1]);
        for(int i = 0; i < 600; i++) {
            fw.write(currentURL + "\n");
        }

        fw.flush();
        fw.close();

        FileResult fileResult = SERVICE.parseFile(file.getPath());

        file.delete();

        Assert.assertTrue(fileResult.hasData());
        Assert.assertTrue(fileResult.getRequestURL().size() == 1850);
        Assert.assertTrue(fileResult.getResponseStatus().size() == 1850);
        for(int i = 0; i < 3; i++) {
            Assert.assertTrue(fileResult.getRequestURL().contains(URLS[i]));
        }
        for(int i = 0; i < 2; i++) {
            Assert.assertTrue(fileResult.getResponseStatus().contains(STATUS[i]));
        }
    }

    @Test(expected = IllegalArgumentException.class)
    public void parseFileWhenPathIsNull() throws IOException {
        SERVICE.parseFile(null);
    }

    @Test(expected = NoSuchFileException.class)
    public void parseFileWhenPathIsInvalid() throws IOException {
        SERVICE.parseFile("/export/abcde.txt");
    }
}
