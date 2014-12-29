package wiki.tagger.exception;

/**
 * Created by vishnupk on 29/12/14.
 * <p/>
 * This is the generic exception thrown by the tagger module.
 */
public class WikiTaggerException extends Exception {
    public WikiTaggerException() {
        super("Exception while processing dump!!");
    }

    public WikiTaggerException(String message) {
        super(message);
    }

    public WikiTaggerException(String message, Throwable cause) {
        super(message, cause);
    }

    public WikiTaggerException(Throwable cause) {
        super(cause);
    }

}
