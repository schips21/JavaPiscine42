import java.io.FileInputStream;
import java.io.IOException;
import java.util.Map;

public class FileProcessor {
    private String filename;

    public FileProcessor(String filename) {
        this.filename = filename;
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public String matches(String fileContent, Map<String, String> signatures) {
        for (Map.Entry<String, String> entry : signatures.entrySet()) {
            if (fileContent.indexOf(entry.getValue()) == 0) {
                return entry.getKey();
            }
        }
        return null;
    }

    public String process() throws IOException {
        StringBuilder file = new StringBuilder();
        try (FileInputStream stream = new FileInputStream(this.filename)) {
            int readResult = 0;
            int count = 0;
            while ((readResult = stream.read()) != -1 && count < 100) {
                file.append((char) readResult);
                count++;
            }
        }
        return (file.toString());
    }
}
