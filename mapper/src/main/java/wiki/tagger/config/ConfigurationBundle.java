package wiki.tagger.config;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Enumeration;
import java.util.PropertyResourceBundle;
import java.util.ResourceBundle;
import java.util.logging.Logger;

/**
 * Created by vishnupk on 26/12/14.
 * <p/>
 * Abstraction for reading configuration values.
 */
public class ConfigurationBundle {
    private final ResourceBundle bundle;
    private final InputStream in;
    private Logger log = Logger.getLogger(this.getClass().getCanonicalName());

    public ConfigurationBundle(final String path) throws IOException {
        in = new FileInputStream(path);
        bundle = new PropertyResourceBundle(in);
    }

    public Enumeration<String> getKeys() {
        Enumeration<String> keys = bundle.getKeys();
        log.finest("Keys :: " + keys);
        return bundle.getKeys();
    }

    public String get(final String key) {
        String value = bundle.getString(key);
        log.finest("Key :: " + key + ",  Value :: " + value);
        return value;
    }
}
