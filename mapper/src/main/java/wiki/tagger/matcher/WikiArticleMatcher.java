package wiki.tagger.matcher;

import wiki.tagger.exception.MatcherException;
import wiki.tagger.proto.WikiTaggerProtos.WikiCategory;
import wiki.tagger.util.Tuple;

import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by vishnupk on 29/12/14.
 * <p/>
 * Matcher for Article Patterns.
 */
public class WikiArticleMatcher implements WikiMatcher {
    private final String ARTICLE_PATTERN;
    private final Pattern pattern;
    private final Logger log = Logger.getLogger(getClass().getName());

    public WikiArticleMatcher() {
        ARTICLE_PATTERN = "/resource/(?<artl>[^>]+).*/resource/Category:(?<catl>[^>]+)";
        pattern = Pattern.compile(ARTICLE_PATTERN);
    }

    @Override
    public Tuple<String, WikiCategory> match(final String in) throws MatcherException {

        String id;
        WikiCategory wikiCategory;
        Matcher matcher = pattern.matcher(in);

        if (!matcher.find()) {
            log.info("Pattern not found for :: " + in);
            throw new MatcherException("Pattern not found for :: \" + in");
        }
        //match Article
        id = matcher.group("artl");
        // match category
        String label = matcher.group("catl").replaceAll("_", " ");
        String category = "Category:" + matcher.group("catl");

        WikiCategory.Builder builder = WikiCategory.newBuilder().setCategory(category).setLabel(label);
        wikiCategory = builder.build();

        return new Tuple<String, WikiCategory>(id, wikiCategory);
    }
}
