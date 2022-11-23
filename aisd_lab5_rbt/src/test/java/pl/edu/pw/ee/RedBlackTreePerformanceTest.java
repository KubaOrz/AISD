package pl.edu.pw.ee;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import org.junit.Test;
import static pl.edu.pw.ee.utils.DataIO.prepareListOfWords;
import static pl.edu.pw.ee.utils.DataIO.saveMeasuredHeight;

public class RedBlackTreePerformanceTest {

    @Test
    public void generateTimeResultsAcordingToNumsOfItems() {
        int stepToMeasure = 100;
        
        generateAndSaveTimeResultsWithAscItems(stepToMeasure);
        generateAndSaveTimeResultsWithDescItems(stepToMeasure);
        generateAndSaveTimeResultRandomItems(stepToMeasure);
    }

    private static void generateAndSaveTimeResultsWithAscItems(int stepToMeasure) {
        String resultFilename = "rbt_height_ascending.csv";
        String[] words = prepareListOfWords();

        List<String> rbtHeightResults = putAllWordsAndMeasureHeightEveryNumOfWords(words, stepToMeasure);

        saveMeasuredHeight(rbtHeightResults, resultFilename);
    }
    
    private static void generateAndSaveTimeResultsWithDescItems(int stepToMeasure) {
        String resultFilename = "rbt_height_descending.csv";
        String[] words = prepareListOfWords();
        String[] descendingWords = new String[words.length];
        
        int index = 0;
        for (int i = words.length - 1; i >= 0; i--) {
            descendingWords[index] = words[i];
            index++;
        }
        
        List<String> rbtHeightResults = putAllWordsAndMeasureHeightEveryNumOfWords(descendingWords, stepToMeasure);

        saveMeasuredHeight(rbtHeightResults, resultFilename);
    }
    
    private static void generateAndSaveTimeResultRandomItems(int stepToMeasure) {
        String resultFilename = "rbt_height_random.csv";
        String[] words = prepareListOfWords();
        String[] shuffledWords = new String[words.length];
        
        Random rand = new Random();
        
        for (int i = 0; i < words.length; i++) {
            int randomIndexToSwap = rand.nextInt(words.length);
			String temp = words[randomIndexToSwap];
			words[randomIndexToSwap] = words[i];
			words[i] = temp;
        }
        
        List<String> rbtHeightResults = putAllWordsAndMeasureHeightEveryNumOfWords(words, stepToMeasure);

        saveMeasuredHeight(rbtHeightResults, resultFilename);
    }

    private static List<String> putAllWordsAndMeasureHeightEveryNumOfWords(String[] words, int stepToMeasure) {
        validateInputs(words, stepToMeasure);

        RedBlackTree<String, Integer> rbt = new RedBlackTree<>();
        int index = 0;
        int putCounter;
        List<String> hightResults = new ArrayList<>();

        for (String word : words) {
            putCounter = rbt.put(word, index++);

            if (index % stepToMeasure == 0) {
                hightResults.add(String.format("%d,%d", index, putCounter));
            }
        }

        return hightResults;
    }

    private static void validateInputs(String[] words, int stepToMeasure) {
        if (words == null) {
            throw new IllegalArgumentException("List of words cannot be null!");
        }
        if (stepToMeasure < 1) {
            throw new IllegalArgumentException("StepToMeasure value cannot be less than zero!");
        }
    }

}
