package wiki.tagger.matcher;

import com.google.protobuf.GeneratedMessage;
import wiki.tagger.exception.MatcherException;
import wiki.tagger.util.Tuple;


/**
 * Created by vishnupk on 29/12/14.
 * <p/>
 * Base class for all Matchers.
 */

//TODO: returning a Tuple now. Ideally it should return a generalisation of the matcher output.

public interface WikiMatcher {
    public Tuple<String, ? extends GeneratedMessage> match(final String in) throws MatcherException;
}
