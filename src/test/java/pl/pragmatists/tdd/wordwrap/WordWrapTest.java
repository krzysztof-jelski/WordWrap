package pl.pragmatists.tdd.wordwrap;

import org.junit.Test;

import static org.assertj.core.api.Assertions.*;
import static pl.pragmatists.tdd.wordwrap.WordWrapper.*;

public class WordWrapTest {

    @Test
    public void WrapNullReturnsEmptyString() {
        assertThat(wrap(null, 10)).isEqualTo("");
    }

    @Test
    public void WrapEmptyStringReturnsEmptyString() {
        assertThat(wrap("", 10)).isEqualTo("");
    }

    @Test
    public void OneShortWordDoesNotWrap() {
        assertThat(wrap("word", 5)).isEqualTo("word");
    }

    @Test
    public void LengthLessThanOneShouldThrowInvalidArgument() {

        Exception caught = catchException(() -> wrap("xxx", 0));

        assertThat(caught).isInstanceOf(WordWrapper.InvalidArgument.class);
    }

    @Test
    public void WordLongerThanLengthBreaksAtLength() throws Exception {
        assertThat(wrap("longword", 4)).isEqualTo("long\nword");
        assertThat(wrap("longerword", 6)).isEqualTo("longer\nword");
    }

    @Test
    public void WordLongerThanTwiceLengthShouldBreakTwice() {
        assertThat(wrap("verylongword", 4)).isEqualTo("very\nlong\nword");

    }

    @Test
    public void TwoWordsLongerThanLimitShouldWrap() throws Exception {
        assertThat(wrap("word word", 6)).isEqualTo("word\nword");
        assertThat(wrap("wrap here", 6)).isEqualTo("wrap\nhere");
    }

    @Test
    public void ThreeWordsEachLongerThanLimitShouldWrap() {
        assertThat(wrap("word word word", 6)).isEqualTo("word\nword\nword");
    }

    @Test
    public void ThreeWordsJustOverTheLimitShouldBreakAtSecond() {
        assertThat(wrap("word word word", 11)).isEqualTo("word word\nword");
    }

    @Test
    public void TwoWordsTheFirstEndingAtTheLimit() {
        assertThat(wrap("word word", 4)).isEqualTo("word\nword");
    }

    private Exception catchException(Runnable runnable) {
        try {
            runnable.run();
            fail("Expected an exception, but none was thrown");
        } catch (Exception e) {
            return e;
        }
        return null;
    }


}
