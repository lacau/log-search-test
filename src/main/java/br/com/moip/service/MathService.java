package br.com.moip.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import br.com.moip.model.FileResult;

/**
 * Created by Lacau on 13/06/2017.
 */
public class MathService {

    private static final class Holder {
        static final MathService INSTANCE = new MathService();
    }

    private MathService() {
    }

    public static MathService getInstance() {
        return Holder.INSTANCE;
    }

    public List<Map.Entry<String, Integer>> calculateMostCalledURLs(FileResult fileResult, int maxSize) {
        Map<String, Integer> urlCount = new HashMap<String, Integer>();

        for(String url : fileResult.getRequestURL()) {
            if(urlCount.get(url) == null) {
                urlCount.put(url, 1);
            } else {
                urlCount.put(url, urlCount.get(url) + 1);
            }
        }

        List<Map.Entry<String, Integer>> sortedEntries = new ArrayList<Map.Entry<String, Integer>>(urlCount.entrySet());

        Collections.sort(sortedEntries, new Comparator<Map.Entry<String, Integer>>() {
            @Override
            public int compare(Map.Entry<String, Integer> e1, Map.Entry<String, Integer> e2) {
                return e2.getValue().compareTo(e1.getValue());
            }
        });

        return sortedEntries.subList(0, maxSize);
    }
}
