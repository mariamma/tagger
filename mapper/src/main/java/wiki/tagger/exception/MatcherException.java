package wiki.tagger.exception;

/**
 * Created by vishnupk on 29/12/14.
 * <p/>
 * This exception will be thrown if there is no match found by the Matcher.
 */
public class MatcherException extends Exception {
    public MatcherException() {
        super("Exception in pattern matching!!");
    }

    public MatcherException(String message) {
        super(message);
    }

    public MatcherException(String message, Throwable cause) {
        super(message, cause);
    }

    public MatcherException(Throwable cause) {
        super(cause);
    }
}
