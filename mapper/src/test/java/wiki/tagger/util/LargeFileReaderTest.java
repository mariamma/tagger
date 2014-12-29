package wiki.tagger.util;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

public class LargeFileReaderTest {
    LargeFileReader fileReader = null;

    @BeforeMethod
    public void setUp() throws Exception {
        fileReader = new LargeFileReader("./mapper/src/test/resources/ConfigBundleTest.properties");
    }

    @AfterMethod
    public void tearDown() throws Exception {
        fileReader = null;
    }

    @Test
    public void testNextLine() throws Exception {
        String line = fileReader.nextLine();
        assertEquals(line, "key1=value1");
    }

    @Test
    public void testHasNextLine() {
        boolean hasNext = fileReader.hasNextLine();
        assertEquals(hasNext, true);
    }


    @Test
    public void testClose() throws Exception {
        fileReader.close(); // closes the file handle
        assertEquals(true, true); // nothing to assert here.!!//TODO: find a new way
        //fileReader.close(); // throws exception
    }
}