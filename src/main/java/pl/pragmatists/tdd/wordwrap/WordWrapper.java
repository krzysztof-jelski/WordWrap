package pl.pragmatists.tdd.wordwrap;

public class WordWrapper {
    private int length;

    public WordWrapper(int length) {
        this.length = length;
    }

    public static String wrap(String s, int length) {
        return new WordWrapper(length).wrap(s);
    }

    public String wrap(String s) {
        if (length < 1) {
            throw new InvalidArgument();
        }
        if (s == null) {
            return "";
        }

        if (s.length() <= length) {
            return s;
        } else {
            int space = s.substring(0, length + 1).lastIndexOf(" ");
            if (space >= 0) {
                return breakBetween(s, space, space + 1);
            }
            return breakBetween(s, length, length);
        }
    }

    private String breakBetween(String s, int start, int end) {
        return s.substring(0, start) + "\n" + wrap(s.substring(end), length);
    }


    public class InvalidArgument extends RuntimeException {
    }
}
