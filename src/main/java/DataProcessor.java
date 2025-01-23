import java.io.*;
import java.nio.file.Files;
import java.util.*;

public class DataProcessor {
    private final List<File> inputFiles;
    private final String outputPath;
    private final String prefix;
    private final boolean appendMode;
    private final Statistics statistics;

    public DataProcessor(List<File> inputFiles, String outputPath, String prefix, boolean appendMode, Statistics statistics) {
        this.inputFiles = inputFiles;
        this.outputPath = outputPath;
        this.prefix = prefix;
        this.appendMode = appendMode;
        this.statistics = statistics;
    }

    public void process() {
        // Статистики
        StatisticsCollector intStats = new StatisticsCollector();
        StatisticsCollector floatStats = new StatisticsCollector();
        StatisticsCollector stringStats = new StatisticsCollector();

        try (FileWriterHelper intWriter = new FileWriterHelper(outputPath, prefix + "integers.txt", appendMode);
             FileWriterHelper floatWriter = new FileWriterHelper(outputPath, prefix + "floats.txt", appendMode);
             FileWriterHelper stringWriter = new FileWriterHelper(outputPath, prefix + "strings.txt", appendMode)) {

            for (File inputFile : inputFiles) {
                if (!inputFile.exists()) {
                    System.err.println("Файл не найден: " + inputFile);
                    continue;
                }

                try (BufferedReader reader = Files.newBufferedReader(inputFile.toPath())) {
                    String line;
                    while ((line = reader.readLine()) != null) {
                        if (Utils.isInteger(line)) {
                            int number = Integer.parseInt(line);
                            intWriter.writeLine(line);
                            intStats.add(number);
                        } else if (Utils.isFloat(line)) {
                            float number = Float.parseFloat(line);
                            floatWriter.writeLine(line);
                            floatStats.add(number);
                        } else {
                            stringWriter.writeLine(line);
                            stringStats.add(line);
                        }
                    }
                }
            }

            // Вывод статистики
            if (!statistics.equals(Statistics.NOT)) {
                System.out.println("Целые числа:");
                System.out.println(statistics.equals(Statistics.FULL) ? intStats.getFullStatistics() : intStats.getShortStatistics());

                System.out.println("Вещественные числа:");
                System.out.println(statistics.equals(Statistics.FULL) ? floatStats.getFullStatistics() : floatStats.getShortStatistics());

                System.out.println("Строки:");
                System.out.println(statistics.equals(Statistics.FULL) ? stringStats.getFullStatistics() : stringStats.getShortStatistics());
            }

        } catch (IOException e) {
            System.err.println("Ошибка ввода-вывода: " + e.getMessage());
        }
    }
}