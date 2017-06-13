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
        if(maxSize <= 0) {
            throw new IllegalArgumentException("maxSize must be > 0");
        }

        final Map<String, Integer> urlCount = countEquals(fileResult.getRequestURL());

        final List<Map.Entry<String, Integer>> sortedEntries = new ArrayList<>(urlCount.entrySet());

        Collections.sort(sortedEntries, byValueDesc());

        if(maxSize > sortedEntries.size()) {
            return sortedEntries;
        }

        return sortedEntries.subList(0, maxSize);
    }

    public List<Map.Entry<String, Integer>> calculateResponseStatusCount(FileResult fileResult) {
        final Map<String, Integer> statusCount = countEquals(fileResult.getResponseStatus());

        final List<Map.Entry<String, Integer>> sortedEntries = new ArrayList<>(statusCount.entrySet());

        Collections.sort(sortedEntries, byKeyAsc());

        return sortedEntries;
    }

    private Map<String, Integer> countEquals(List<String> list) {
        final Map<String, Integer> map = new HashMap<>();

        for(String url : list) {
            if(map.get(url) == null) {
                map.put(url, 1);
            } else {
                map.put(url, map.get(url) + 1);
            }
        }
        return map;
    }

    private Comparator byValueDesc() {
        return new Comparator<Map.Entry<String, Integer>>() {
            @Override
            public int compare(Map.Entry<String, Integer> e1, Map.Entry<String, Integer> e2) {
                return e2.getValue().compareTo(e1.getValue());
            }
        };
    }

    private Comparator byKeyAsc() {
        return new Comparator<Map.Entry<String, Integer>>() {
            @Override
            public int compare(Map.Entry<String, Integer> e1, Map.Entry<String, Integer> e2) {
                return e1.getKey().compareTo(e2.getKey());
            }
        };
    }
}
