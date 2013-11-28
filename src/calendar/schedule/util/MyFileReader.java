package calendar.schedule.util;

import java.io.BufferedReader;
import java.io.Closeable;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class MyFileReader {

    private File file;

    public String readFile() {
        StringBuilder builder = new StringBuilder();
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new FileReader(getFile()));
            String line = null;
            while ((line = reader.readLine()) != null)
                builder.append(line);
        } catch (IOException ignored) {
        } finally {
            closeQuietly(reader);
        }
        return builder.toString();
    }

    private void closeQuietly(Closeable c) {
        if (c != null) {
            try {
                c.close();
            } catch (IOException ignored) {}
        }
    }

    public File getFile() {
        return file;
    }

    public void setFile(File file) {
        this.file = file;
    }

}