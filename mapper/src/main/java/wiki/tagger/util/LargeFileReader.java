package wiki.tagger.util;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Scanner;

/**
 * Created by vishnupk on 26/12/14.
 * <p/>
 * This class can be used to read very large files line by line with out the fear for OutOfMemoryError.
 */
public class LargeFileReader {
    private final Scanner sc;
    private final InputStream fin;

    public LargeFileReader(final String path) throws FileNotFoundException {
        fin = new FileInputStream(path);
        sc = new Scanner(fin);
    }

    /**
     * Reads next line of the file if it exists
     *
     * @return next line if it exists else null
     * @throws java.io.IOException
     */
    public String nextLine() throws IOException {
        String line = hasNextLine() ? sc.nextLine() : null;
        if (sc.ioException() != null) {
            throw sc.ioException();
        }
        return line;
    }

    public boolean hasNextLine() {
        return sc.hasNextLine();
    }

    /**
     * Should be called to cleanup all the resources in LargeFileReader's possession
     */
    public void close() throws IOException {
        sc.close();
        if (fin != null) {
            fin.close();
        }
    }
}
