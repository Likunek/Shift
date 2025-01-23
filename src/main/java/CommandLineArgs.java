import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class CommandLineArgs {
    private final List<File> inputFiles = new ArrayList<>();
    private String outputPath = ".";
    private String prefix = "";
    private boolean appendMode = false;
    private static Statistics statistic = Statistics.NOT;

    public static CommandLineArgs parse(String[] args) {
        CommandLineArgs result = new CommandLineArgs();

        for (int i = 0; i < args.length; i++) {
            switch (args[i]) {
                case "-o":
                    if (++i < args.length) result.outputPath = args[i];
                    else throw new IllegalArgumentException("Не указан путь для опции -o");
                    break;
                case "-p":
                    if (++i < args.length) result.prefix = args[i];
                    else throw new IllegalArgumentException("Не указан префикс для опции -p");
                    break;
                case "-a":
                    result.appendMode = true;
                    break;
                case "-s":
                    statistic = Statistics.BRIEF;
                    break;
                case "-f":
                    statistic = Statistics.FULL;
                    break;
                default:
                    result.inputFiles.add(new File(args[i]));
            }
        }

        if (result.inputFiles.isEmpty())
            throw new IllegalArgumentException("Не указаны входные файлы");

        return result;
    }

    public List<File> getInputFiles() {
        return inputFiles;
    }

    public String getOutputPath() {
        return outputPath;
    }

    public String getPrefix() {
        return prefix;
    }

    public boolean isAppendMode() {
        return appendMode;
    }

    public Statistics getStatistic() {return statistic;}
}