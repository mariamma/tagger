package wiki.tagger.matcher;

import org.testng.annotations.Test;
import wiki.tagger.proto.WikiTaggerProtos.WikiCategory;
import wiki.tagger.util.Tuple;

import static org.testng.Assert.assertEquals;

public class WikiArticleMatcherTest {

    @Test
    public void testMatch() throws Exception {
        WikiArticleMatcher matcher = new WikiArticleMatcher();
        String in = "<http://dbpedia.org/resource/Albedo_Test> <http://purl.org/dc/terms/subject> <http://dbpedia.org/resource/Category:Radiation_Test> .";
        Tuple<String, WikiCategory> tuple = matcher.match(in);
        assertEquals(tuple.getFirst(), "Albedo_Test");
        assertEquals(tuple.getSecond().getCategory(), "Category:Radiation_Test");
        assertEquals(tuple.getSecond().getLabel(), "Radiation Test");
    }
}