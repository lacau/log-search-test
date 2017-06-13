package br.com.moip.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Lacau on 13/06/2017.
 */
public class FileResult {

    private List<String> requestURL;

    private List<String> responseStatus;

    public FileResult() {
        requestURL = new ArrayList<>();
        responseStatus = new ArrayList<>();
    }

    public void addRequestURL(String url) {
        requestURL.add(url);
    }

    public void addResponseStatus(String status) {
        responseStatus.add(status);
    }

    public boolean hasData() {
        return !requestURL.isEmpty() && !responseStatus.isEmpty();
    }

    public List<String> getRequestURL() {
        return requestURL;
    }

    public List<String> getResponseStatus() {
        return responseStatus;
    }
}
