package pl.edu.pw.ee.performance;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.LinkedHashMap;

public class ResultsProcessor {

    public static LinkedHashMap<Integer, Double> processResults(LinkedHashMap<Integer, ArrayList<Long>> results) {
        LinkedHashMap<Integer, Double> avgResults = new LinkedHashMap<>();
        for (Integer size : results.keySet()) {
            results.get(size).sort(Comparator.naturalOrder());
            ArrayList<Long> trimmedResults = new ArrayList<>(results.get(size).subList(10, 19));
            double avgTime = 0;
            for (Long time : trimmedResults) {
                avgTime += time;
            }
            avgTime /= trimmedResults.size();
            avgResults.put(size, avgTime);
        }
        return avgResults;
    }
}
