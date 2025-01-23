import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class FileWriterHelper implements AutoCloseable {
    private final BufferedWriter writer;

    public FileWriterHelper(String outputPath, String fileName, boolean append) throws IOException {
        Path path = Paths.get(outputPath, fileName);

        // Создаем директорию, если ее нет
        if (!Files.exists(path.getParent())) {
            Files.createDirectories(path.getParent());
        }

        // Инициализируем writer
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