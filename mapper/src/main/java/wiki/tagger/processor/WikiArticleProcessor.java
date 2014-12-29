package wiki.tagger.processor;

import wiki.tagger.dao.ArticleDao;
import wiki.tagger.exception.MatcherException;
import wiki.tagger.exception.WikiTaggerException;
import wiki.tagger.info.Article;
import wiki.tagger.matcher.WikiArticleMatcher;
import wiki.tagger.proto.WikiTaggerProtos;
import wiki.tagger.proto.WikiTaggerProtos.WikiCategory;
import wiki.tagger.util.LargeFileReader;
import wiki.tagger.util.Tuple;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

/**
 * Created by vishnupk on 29/12/14.
 * <p/>
 * Processor for Wikipedia Article entry. It interacts with other processors if necessary.
 * Also calls DAO to update database.
 */
public class WikiArticleProcessor implements Processor {
    private final String ARTICLE_REDIS_KEY_PREFIX = "WikiArticle::";
    private final Logger log = Logger.getLogger(getClass().getName());
    private WikiArticleMatcher matcher = new WikiArticleMatcher();
    private ArticleDao articleDao = new ArticleDao();

    @Override
    public void process(final String fileName) throws WikiTaggerException {

        try {
            // fileName = "/Users/vishnupk/Downloads/category_labels_en.nt"
            LargeFileReader reader = new LargeFileReader(fileName);

            String line;
            String lastProcessedArticle = null;
            List<WikiCategory> categories = new ArrayList<WikiCategory>();

            while (reader.hasNextLine()) {
                line = reader.nextLine();
                Tuple<String, WikiCategory> tuple;
                try {
                    log.info("Processing : " + line);
                    tuple = matcher.match(line);
                } catch (MatcherException e) {
                    log.info("No match found for current line. Skipping");
                    continue;
                }

                if (lastProcessedArticle != null && tuple.getFirst() != null && !lastProcessedArticle.equals(tuple.getFirst())) {
                    // Create new article
                    addArticle(lastProcessedArticle, categories);

                    categories = new ArrayList<WikiCategory>();
                    lastProcessedArticle = tuple.getFirst();
                } else {
                    // collect categories
                    categories.add(tuple.getSecond());
                    lastProcessedArticle = tuple.getFirst();
                }

            }
            if (lastProcessedArticle != null) {
                addArticle(lastProcessedArticle, categories);
            }

        } catch (java.io.IOException e) {
            throw new WikiTaggerException("Exception while processing wikipedia dump !!", e);
        }

    }

    private void addArticle(String lastProcessedArticle, List<WikiCategory> categories) throws WikiTaggerException {
        WikiTaggerProtos.WikiArticle wikiArticle = WikiTaggerProtos.WikiArticle.newBuilder()
                .setId(ARTICLE_REDIS_KEY_PREFIX + lastProcessedArticle)
                .setLabel(lastProcessedArticle.replaceAll("_", " "))
                .addAllCategory(categories)
                .build();


        Article article = new Article.Builder()
                .id(ARTICLE_REDIS_KEY_PREFIX + lastProcessedArticle)
                .setWikiArticle(wikiArticle)
                .build();
        if (articleDao != null)
            articleDao.setArticle(article);
        else
            throw new WikiTaggerException("DB Handle not found. (ArticleDao is null)");
    }
}
