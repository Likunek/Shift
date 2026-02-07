import lombok.Getter;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

@Getter
public class CommandLineArgs {
    private final List<File> inputFiles = new ArrayList<>();
    private String outputPath = ".";
    private String prefix = "";
    private boolean appendMode = false;
    private DataOutput statistic = DataOutput.NOT;

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
                    result.statistic = DataOutput.BRIEF;
                    break;
                case "-f":
                    result.statistic = DataOutput.FULL;
                    break;
                default:
                    result.inputFiles.add(new File(args[i]));
            }
        }

        if (result.inputFiles.isEmpty())
            throw new IllegalArgumentException("Не указаны входные файлы");

        return result;
    }

}