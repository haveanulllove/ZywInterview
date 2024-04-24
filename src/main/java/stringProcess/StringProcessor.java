package stringProcess;

import java.util.Collections;
import java.util.concurrent.atomic.AtomicReference;
 import java.util.stream.IntStream;

public class StringProcessor {

    public String removeConsecutiveChars(String input) {
        StringBuilder result = new StringBuilder(input);
        int i = 0;
        while (i < result.length() - 2) {
            if (result.charAt(i) == result.charAt(i + 1) && result.charAt(i) == result.charAt(i + 2)) {
                int start = i;
                while (i < result.length() && result.charAt(start) == result.charAt(i)) {
                    i++;
                }
                result.delete(start, i);
                i = Math.max(0, start - 1); // Adjust i to handle overlapping patterns
            } else {
                i++;
            }
        }
        return result.toString();
    }
    // Stage 2: Replace sequences of three identical characters with the previous character
    public String transformConsecutiveChars(String input) {
        AtomicReference<String> result = new AtomicReference<>(input);
        IntStream.rangeClosed('a', 'z').boxed()
                .sorted(Collections.reverseOrder()) // Start from 'z' to 'a'
                .forEach(c -> {
                    String triple = "" + (char)c.intValue() + (char)c.intValue() + (char)c.intValue();
                    while(result.get().contains(triple)) {
                        result.set(transformHelper(result.get(), triple));
                    }
                });
        return result.get();
    }

    private String transformHelper(String input, String triple) {
        char ch = triple.charAt(0);
        char replacement = ch > 'a' ? (char)(ch - 1) : '\0'; // Use '\0' for deletion
        return input.replace(triple, replacement == '\0' ? "" : String.valueOf(replacement));
    }

    public static void main(String[] args) {
        StringProcessor processor = new StringProcessor();
        String input = "aabcccbad";
        String output = processor.transformConsecutiveChars(input);
        String output1 = processor.removeConsecutiveChars(input);
        System.out.println(output);
        System.out.println(output1);
    }
}
