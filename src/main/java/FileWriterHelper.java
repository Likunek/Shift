import lombok.Getter;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
@Getter
public class FileWriterHelper implements AutoCloseable {
    private final BufferedWriter writer;

    public FileWriterHelper(String outputPath, String fileName, boolean append) throws IOException {
        Path path = Paths.get(outputPath, fileName);

        if (!Files.exists(path.getParent())) {
            Files.createDirectories(path.getParent());
        }

        this.writer = new BufferedWriter(new FileWriter(path.toFile(), append));
    }

    public void writeLine(String line) throws IOException {
        writer.write(line);
        writer.newLine();
    }


    @Override
    public void close() throws IOException {
        if (writer != null) {
            writer.close();
        }
    }
}