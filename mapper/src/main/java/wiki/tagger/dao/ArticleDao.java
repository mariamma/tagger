package wiki.tagger.dao;

import com.google.protobuf.InvalidProtocolBufferException;
import redis.clients.jedis.Jedis;
import wiki.tagger.dao.utils.RedisManager;
import wiki.tagger.info.Article;

/**
 * Created by vishnupk on 28/12/14.
 * <p/>
 * DAO for article.
 */
public class ArticleDao {

    private final RedisManager manager;

    public ArticleDao() {
        manager = RedisManager.getInstance();
    }

    public void setArticle(final Article article) {
        Jedis jedis = manager.getJedis();
        jedis.set(article.getId().getBytes(), article.toByteArray());
        manager.returnJedis(jedis);
    }

    public Article getArticle(final Article article) throws InvalidProtocolBufferException {
        Jedis jedis = manager.getJedis();
        Article.Builder builder = new Article.Builder();
        builder = builder
                .id(article.getId())
                .info(jedis.get(article.getId().getBytes()));
        manager.returnJedis(jedis);
        return builder.build();
    }
}
