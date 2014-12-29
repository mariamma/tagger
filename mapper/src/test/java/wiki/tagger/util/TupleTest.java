package wiki.tagger.util;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

public class TupleTest {

    private Tuple<String, String> tuple;

    @BeforeMethod
    public void setUp() throws Exception {
        tuple = new Tuple<String, String>("first", "second");
    }

    @AfterMethod
    public void tearDown() throws Exception {
        tuple = null;
    }

    @Test
    public void testGetFirst() throws Exception {
        String expected = "first";
        assertEquals(tuple.getFirst(), expected);
    }

    @Test
    public void testGetSecond() throws Exception {
        String expected = "second";
        assertEquals(tuple.getSecond(), expected);
    }
}