package br.com.moip.service;

import java.util.List;
import java.util.Map;

import br.com.moip.model.FileResult;
import org.junit.Assert;
import org.junit.Test;

/**
 * Created by Lacau on 13/06/2017.
 */
public class MathServiceTest {

    private static final String FIRST_URL = "http://first-url.com";

    private static final String SECOND_URL = "http://second-url.com";

    private static final String THIRD_URL = "http://third-url.com";

    private static final String[] RESPONSE_STATUS = {"200", "400", "404", "500"};

    private static final MathService SERVICE = MathService.getInstance();

    @Test
    public void calculateMostCalledURLsWhenMaxSizeGreaterThenUrlCount() {
        final FileResult fileResult = new FileResult();
        fileResult.addRequestURL(FIRST_URL);
        fileResult.addRequestURL(SECOND_URL);
        fileResult.addRequestURL(SECOND_URL);

        List<Map.Entry<String, Integer>> entries = SERVICE.calculateMostCalledURLs(fileResult, 10);

        Assert.assertTrue(entries.size() == 2);
        Assert.assertEquals(entries.get(0).getKey(), SECOND_URL);
        Assert.assertEquals(entries.get(0).getValue().intValue(), 2);
        Assert.assertEquals(entries.get(1).getKey(), FIRST_URL);
        Assert.assertEquals(entries.get(1).getValue().intValue(), 1);
    }

    @Test
    public void calculateMostCalledURLsWhenMaxSizeLessThenUrlCount() {
        final FileResult fileResult = new FileResult();
        fileResult.addRequestURL(FIRST_URL);
        fileResult.addRequestURL(SECOND_URL);
        fileResult.addRequestURL(SECOND_URL);
        fileResult.addRequestURL(THIRD_URL);
        fileResult.addRequestURL(THIRD_URL);
        fileResult.addRequestURL(THIRD_URL);
        fileResult.addRequestURL(THIRD_URL);

        List<Map.Entry<String, Integer>> entries = SERVICE.calculateMostCalledURLs(fileResult, 2);

        Assert.assertTrue(entries.size() == 2);
        Assert.assertEquals(entries.get(0).getKey(), THIRD_URL);
        Assert.assertEquals(entries.get(0).getValue().intValue(), 4);
        Assert.assertEquals(entries.get(1).getKey(), SECOND_URL);
        Assert.assertEquals(entries.get(1).getValue().intValue(), 2);
    }

    @Test
    public void calculateResponseStatusCount() {
        final FileResult fileResult = new FileResult();
        for(int i = 0; i < 10; i++) {
            fileResult.addResponseStatus(RESPONSE_STATUS[0]);
        }
        for(int i = 0; i < 20; i++) {
            fileResult.addResponseStatus(RESPONSE_STATUS[1]);
        }
        for(int i = 0; i < 3; i++) {
            fileResult.addResponseStatus(RESPONSE_STATUS[2]);
        }
        for(int i = 0; i < 7; i++) {
            fileResult.addResponseStatus(RESPONSE_STATUS[3]);
        }

        List<Map.Entry<String, Integer>> entries = SERVICE.calculateResponseStatusCount(fileResult);

        Assert.assertTrue(entries.size() == 4);
        Assert.assertEquals(entries.get(0).getKey(), RESPONSE_STATUS[0]);
        Assert.assertEquals(entries.get(0).getValue().intValue(), 10);
        Assert.assertEquals(entries.get(1).getKey(), RESPONSE_STATUS[1]);
        Assert.assertEquals(entries.get(1).getValue().intValue(), 20);
        Assert.assertEquals(entries.get(2).getKey(), RESPONSE_STATUS[2]);
        Assert.assertEquals(entries.get(2).getValue().intValue(), 3);
        Assert.assertEquals(entries.get(3).getKey(), RESPONSE_STATUS[3]);
        Assert.assertEquals(entries.get(3).getValue().intValue(), 7);
    }

    @Test(expected = IllegalArgumentException.class)
    public void calculateMostCalledURLsWhenFileResultIsNull() {
        SERVICE.calculateMostCalledURLs(null, 3);
    }

    @Test(expected = IllegalArgumentException.class)
    public void calculateMostCalledURLsWhenMaxSizeNegative() {
        SERVICE.calculateMostCalledURLs(new FileResult(), -1);
    }

    @Test(expected = IllegalArgumentException.class)
    public void calculateResponseStatusCountWhenFileResultIsNull() {
        SERVICE.calculateResponseStatusCount(null);
    }
}
