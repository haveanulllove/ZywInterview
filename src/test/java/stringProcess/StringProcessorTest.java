package stringProcess;

import static org.junit.Assert.assertEquals;
import org.junit.Test;

public class StringProcessorTest {

    @Test
    public void testRemoveConsecutiveChars() {
        StringProcessor processor = new StringProcessor();

        assertEquals("aabbad", processor.removeConsecutiveChars("aabcccbad"));


        assertEquals("bcd", processor.transformConsecutiveChars("aabbbccccd"));
    }
}
