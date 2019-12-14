package trietree;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Mojtaba
 */
public class SpellCheck {

    private final HashMap<String, Integer> newlist = new HashMap<>();
    ArrayList<String> wordList;

    public SpellCheck(ArrayList<String> wordList) {
        this.wordList = wordList;
    }

    private int computeDistance(String s1, String s2) {
        int[] costs = new int[s2.length() + 1];
        for (int i = 0; i <= s1.length(); i++) {
            int lastValue = i;
            for (int j = 0; j <= s2.length(); j++) {
                if (i == 0) {
                    costs[j] = j;
                } else {
                    if (j > 0) {
                        int newValue = costs[j - 1];
                        if (s1.charAt(i - 1) != s2.charAt(j - 1)) {
                            newValue = Math.min(Math.min(newValue, lastValue), costs[j]) + 1;
                        }
                        costs[j - 1] = lastValue;
                        lastValue = newValue;
                    }
                }
            }
            if (i > 0) {
                costs[s2.length()] = lastValue;
            }
        }
        return costs[s2.length()];
    }

    public void wordSuggester(String word) {
        int i;
        newlist.clear();
        for (String s : wordList) {
            i = computeDistance(s, word);
            if (i < 3) {
                newlist.put(s, i);
            }
        }
        List<Map.Entry<String, Integer>> entries = new ArrayList<>(newlist.entrySet());
        Collections.sort(entries, new Comparator<Map.Entry<String, Integer>>() {
            public int compare(Map.Entry<String, Integer> e1, Map.Entry<String, Integer> e2) {
                return e1.getValue().compareTo(e2.getValue());
            }
        });
        Map<String, Integer> orderedMap = new LinkedHashMap<>();
        for (Map.Entry<String, Integer> entry : entries) {
            orderedMap.put(entry.getKey(), entry.getValue());
        }
        System.out.print("\nThe word <" + word + "> is not supported by my dic.Did you mean:");
        for (Map.Entry<String, Integer> e : orderedMap.entrySet()) {
            System.out.println(e.getKey());
        }
        System.out.println();
    }
}
