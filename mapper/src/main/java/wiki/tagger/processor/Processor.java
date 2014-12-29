package wiki.tagger.processor;

import wiki.tagger.exception.WikiTaggerException;

/**
 * Created by vishnupk on 29/12/14.
 * <p/>
 * Base class for All Processors.
 */
public interface Processor {
    public void process(final String fileName) throws WikiTaggerException;
}
