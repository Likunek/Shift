import java.io.File;
import java.nio.file.Paths;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        try {
            // Разбор аргументов командной строки
            CommandLineArgs parsedArgs = CommandLineArgs.parse(args);

            // Инициализация процессора данных
            DataProcessor processor = new DataProcessor(
                    parsedArgs.getInputFiles(),
                    parsedArgs.getOutputPath(),
                    parsedArgs.getPrefix(),
                    parsedArgs.isAppendMode(),
                    parsedArgs.getStatistic()
            );

            // Запуск обработки данных
            processor.process();
        } catch (IllegalArgumentException e) {
            System.err.println("Ошибка: " + e.getMessage());
        } catch (Exception e) {
            System.err.println("Непредвиденная ошибка: " + e.getMessage());
            e.printStackTrace();
        }
    }
}