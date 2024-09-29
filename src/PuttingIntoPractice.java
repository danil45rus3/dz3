import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class PuttingIntoPractice {

    public static void main(String... args) {
        Trader raoul = new Trader("Raoul", "Cambridge");
        Trader mario = new Trader("Mario", "Milan");
        Trader alan = new Trader("Alan", "Cambridge");
        Trader brian = new Trader("Brian", "Cambridge");

        List<Transaction> transactions = Arrays.asList(
                new Transaction(brian, 2011, 300),
                new Transaction(raoul, 2012, 1000),
                new Transaction(raoul, 2011, 400),
                new Transaction(mario, 2012, 710),
                new Transaction(mario, 2012, 700),
                new Transaction(alan, 2012, 950)
        );
        // 1. Найти все транзакции за 2011 год и отсортировать их по сумме (от меньшей
        //к большей).
        System.out.println("Все транзакции за 2011 год, отсортированные по сумме.");
        transactions.stream()
                .filter(transaction -> transaction.getYear() == 2011)
                .sorted(Comparator.comparing(Transaction::getValue))
                .forEach(System.out::println);

        //2. Вывести список неповторяющихся городов, в которых работают трейдеры.
        System.out.println("Список неповторяющихся городов.");
        transactions.stream()
                .map(Transaction::getTrader)
                .map(Trader::getCity)
                .distinct()
                .forEach(System.out::println);

        //3. Найти всех трейдеров из Кембриджа и отсортировать их по именам.
        System.out.println("Все трейдеры из Кембриджа, отсортированные по имени.");
        transactions.stream()
                .map(Transaction::getTrader)
                .distinct()
                .filter(trader -> trader.getCity().equals("Cambridge"))
                .sorted(Comparator.comparing(Trader::getName))
                .forEach(System.out::println);

        //4. Вернуть строку со всеми именами трейдеров, отсортированными в алфавитном
        //порядке.
        String names = transactions.stream()
                .map(Transaction::getTrader)
                .distinct()
                .map(Trader::getName)
                .sorted(String::compareTo)
                .collect(Collectors.joining(", "));
        System.out.println("Все трейдеры, отсортированные в алфавитном порядке " + names);

        //5. Выяснить, существует ли хоть один трейдер из Милана.
        boolean result = transactions.stream()
                .anyMatch(transaction -> transaction.getTrader().getCity().equals("Milan"));
        System.out.println(result ? "Существуют трейдеры из Милана" : "Не существуют трейдеры из Милана");

        //6. Вывести суммы всех транзакций трейдеров из Кембриджа.
        int total = transactions.stream()
                .filter(transaction -> transaction.getTrader().getCity().equals("Cambridge"))
                .mapToInt(Transaction::getValue)
                .sum();
        System.out.println("Сумма всех транзакций: " + total);

        //7. Какова максимальная сумма среди всех транзакций?
        total = transactions.stream()
                .map(Transaction::getValue)
                .max(Integer::compareTo)
                .orElseThrow();
        System.out.println("Максимальная сумма среди всех транзакций : " + total);

        //8. Найти транзакцию с минимальной суммой.
        total = transactions.stream()
                .map(Transaction::getValue)
                .min(Integer::compareTo)
                .orElseThrow();
        System.out.println("Минимальная сумма среди всех транзакций : " + total);
    }
}