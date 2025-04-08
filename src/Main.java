import java.util.*;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicBoolean;

public class Main {
    private static final AtomicBoolean isRunning = new AtomicBoolean(false);
    private static final ConcurrentLinkedQueue<LogEntry> logQueue = new ConcurrentLinkedQueue<>();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Controles: [S] Iniciar | [P] Parar | [Q] Salir");

        Thread processingThread = new Thread(() -> {
            while (true) {
                try {
                    if (isRunning.get()) {
                        LogEntry entry = ServerLogAnalysis.generateLogEntry();
                        logQueue.add(entry);
                        System.out.println(entry);
                        Thread.sleep(50);
                    } else {
                        Thread.sleep(100);
                    }
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    break;
                }
            }
        });

        processingThread.start();

        while (true) {
            String input = scanner.nextLine().trim().toUpperCase();

            switch (input) {
                case "S":
                    if (!isRunning.getAndSet(true)) {
                        System.out.println("\n>>> Análisis INICIADO");
                    }
                    break;

                case "P":
                    if (isRunning.getAndSet(false)) {
                        System.out.println("\n>>> Análisis DETENIDO");
                        procesarResultados();
                    }
                    break;

                case "Q":
                    System.out.println("\n>>> Saliendo del sistema...");
                    scanner.close();
                    System.exit(0);
                    break;

                default:
                    System.out.println("Comando no válido. Usa: S/P/Q");
            }
        }
    }

    private static void procesarResultados() {
        try {
            List<LogEntry> currentLogs = new ArrayList<>(logQueue);
            if (!currentLogs.isEmpty()) {
                ServerLogAnalysis.analyze(currentLogs);
                logQueue.clear();
            } else {
                System.out.println("No hay logs para analizar");
            }
        } catch (ExecutionException | InterruptedException e) {
            e.printStackTrace();
        }
    }
}