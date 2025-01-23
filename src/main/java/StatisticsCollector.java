import java.util.ArrayList;
import java.util.List;

public class StatisticsCollector {
    private final List<Number> numbers = new ArrayList<>();
    private final List<String> strings = new ArrayList<>();

    public void add(int number) {
        numbers.add(number);
    }

    public void add(float number) {
        numbers.add(number);
    }

    public void add(String string) {
        strings.add(string);
    }

    public String getShortStatistics() {
        if (!numbers.isEmpty()) {
            return "Количество: " + numbers.size();
        } else if (!strings.isEmpty()) {
            return "Количество: " + strings.size();
        } else {
            return "Нет данных.";
        }
    }

    public String getFullStatistics() {
        if (!numbers.isEmpty()) {
            int count = numbers.size();
            double min = numbers.stream().mapToDouble(Number::doubleValue).min().orElse(0);
            double max = numbers.stream().mapToDouble(Number::doubleValue).max().orElse(0);
            double sum = numbers.stream().mapToDouble(Number::doubleValue).sum();
            double average = sum / count;

            return String.format("Количество: %d, Минимум: %.2f, Максимум: %.2f, Сумма: %.2f, Среднее: %.2f",
                    count, min, max, sum, average);
        } else if (!strings.isEmpty()) {
            int count = strings.size();
            int minLength = strings.stream().mapToInt(String::length).min().orElse(0);
            int maxLength = strings.stream().mapToInt(String::length).max().orElse(0);

            return String.format("Количество: %d, Самая короткая строка: %d символов, Самая длинная строка: %d символов",
                    count, minLength, maxLength);
        } else {
            return "Нет данных.";
        }
    }
}