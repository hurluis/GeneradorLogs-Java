import java.util.*;
import java.util.concurrent.*;
import java.util.stream.IntStream;
import java.util.stream.Collectors;

class ServerLogAnalysis {
    private static final String[] HTTP_METHODS = {"GET", "POST", "PUT", "DELETE"};
    private static final int THREAD_POOL_SIZE = 4;
    private static final Random random = new Random();

    public static LogEntry generateLogEntry() {
        return new LogEntry(
                generateDateTime(),
                generateIp(),
                HTTP_METHODS[random.nextInt(HTTP_METHODS.length)],
                random.nextBoolean() ? 200 : 400
        );
    }

    private static String generateDateTime() {
        return String.format("2025-%02d-%02d %02d:%02d:%02d",
                random.nextInt(13),
                random.nextInt(32),
                random.nextInt(25),
                random.nextInt(60),
                random.nextInt(60));
    }

    private static String generateIp() {
        return IntStream.range(0, 4)
                .mapToObj(i -> String.valueOf(random.nextInt(256)))
                .collect(Collectors.joining("."));
    }

    public static void analyze(List<LogEntry> logEntries) throws ExecutionException, InterruptedException {
        ExecutorService executor = Executors.newFixedThreadPool(THREAD_POOL_SIZE);

        Future<Map<Integer, Long>> statusCodeTask = executor.submit(() ->
                logEntries.stream()
                        .collect(Collectors.groupingBy(LogEntry::getCode, Collectors.counting()))
        );

        Future<Map<String, Long>> httpMethodTask = executor.submit(() ->
                logEntries.stream()
                        .collect(Collectors.groupingBy(LogEntry::getRequestMethod, Collectors.counting()))
        );

        Map<Integer, Long> statusCodeCounts = statusCodeTask.get();
        Map<String, Long> httpMethodCounts = httpMethodTask.get();

        System.out.println("\n--- Resumen del análisis ---");
        System.out.println("Total de logs procesados: " + logEntries.size());

        System.out.println("\nCódigos de estado:");
        statusCodeCounts.forEach((code, count) ->
                System.out.printf("- Código %d: %d solicitudes%n", code, count));

        System.out.println("\nMétodos HTTP:");
        httpMethodCounts.forEach((method, count) ->
                System.out.printf("- %s: %d solicitudes%n", method, count));

        System.out.println("----------------------------");
        executor.shutdown();
    }
}