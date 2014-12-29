package wiki.tagger.processor;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import wiki.tagger.dao.ArticleDao;
import wiki.tagger.info.Article;

import static org.mockito.Mockito.*;

public class WikiArticleProcessorTest {

    @Mock
    private ArticleDao dao;

    @InjectMocks
    private WikiArticleProcessor processor = new WikiArticleProcessor();

    @BeforeMethod(alwaysRun = true)
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testProcess() throws Exception {
        //when(dao.setArticle(any(Article.class))).thenReturn(new Void());
        processor.process("./mapper/src/test/resources/wiki_article.nt");
        verify(dao, times(2)).setArticle(any(Article.class));
    }
}