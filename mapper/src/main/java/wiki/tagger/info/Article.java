package wiki.tagger.info;

import com.google.protobuf.InvalidProtocolBufferException;
import wiki.tagger.proto.WikiTaggerProtos.WikiArticle;

import java.io.Serializable;

/**
 * Created by vishnupk on 28/12/14.
 * <p/>
 * <p/>
 * The article Entity to be stored in database.
 */
public class Article implements WikiTaggerProcessable, Serializable {
    private final String id;
    private final WikiArticle info;

    private Article(Builder b) {
        id = b.id;
        info = b.info;
    }

    public String getId() {
        return id;
    }

    public byte[] toByteArray() {
        return info.toByteArray();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Article)) return false;

        Article article = (Article) o;

        return id.equals(article.id);

    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }

    public static class Builder {
        private String id;
        private WikiArticle info;

        public Article build() {
            return new Article(this);
        }

        public Builder id(String id) {
            this.id = id;
            return this;
        }

        public Builder setWikiArticle(WikiArticle wikiArticle) {
            this.info = wikiArticle;
            return this;
        }

        public Builder info(byte[] data) throws InvalidProtocolBufferException {
            info = WikiArticle.parseFrom(data);
            return this;
        }
    }
}
