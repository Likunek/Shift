
public class Main {
    public static void main(String[] args) {
        try {
            CommandLineArgs parsedArgs = CommandLineArgs.parse(args);

            DataProcessor processor = new DataProcessor(
                    parsedArgs.getInputFiles(),
                    parsedArgs.getOutputPath(),
                    parsedArgs.getPrefix(),
                    parsedArgs.isAppendMode(),
                    parsedArgs.getStatistic()
            );

            processor.process();
        } catch (IllegalArgumentException e) {
            System.err.println("Ошибка: " + e.getMessage());
        } catch (Exception e) {
            System.err.println("Непредвиденная ошибка: " + e.getMessage());
            e.printStackTrace();
        }
    }
}