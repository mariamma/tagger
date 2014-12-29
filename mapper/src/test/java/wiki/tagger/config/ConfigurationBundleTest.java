package wiki.tagger.config;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.Enumeration;

import static org.testng.Assert.assertEquals;

public class ConfigurationBundleTest {
    private ConfigurationBundle bundle;

    @BeforeMethod
    public void setUp() throws Exception {
        bundle = new ConfigurationBundle("./mapper/src/test/resources/ConfigBundleTest.properties");
    }

    @AfterMethod
    public void tearDown() throws Exception {
        bundle = null;
    }

    @Test
    public void testGetKeys() throws Exception {
        Enumeration<String> keys = bundle.getKeys();
        int count = 0;
        while (keys.hasMoreElements()) {
            count += 1;
            String key = keys.nextElement();
            assertEquals(key, "key" + count, "Keys are different");
        }

        assertEquals(count, 2, "Number of keys are different");
    }

    @Test
    public void testGet() throws Exception {
        String actual = bundle.get("key1");
        assertEquals(actual, "value1", "Retrieved value is different");
        actual = bundle.get("key2");
        assertEquals(actual, "value2", "Retrieved value is different");
    }
}