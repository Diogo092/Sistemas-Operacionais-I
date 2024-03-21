import java.util.concurrent.*;

public class SimulacaoCozinha {
    public static void main(String[] args) {
        ExecutorService executor = Executors.newFixedThreadPool(5);
        for (int i = 1; i <= 5; i++) {
            Runnable prato = new Cozinheiro(i);
            executor.execute(prato);
        }
        executor.shutdown();
        while (!executor.isTerminated()) {
        }
        System.out.println("Todos os pratos foram preparados e entregues.");
    }
}

class Cozinheiro implements Runnable {
    private final int idPrato;

    public Cozinheiro(int id) {
        this.idPrato = id;
    }

    @Override
    public void run() {
        String nomePrato = idPrato % 2 == 0 ? "Lasanha à Bolonhesa" : "Sopa de Cebola";
        double tempoMinimo = idPrato % 2 == 0 ? 0.6 : 0.5;
        double tempoMaximo = idPrato % 2 == 0 ? 1.2 : 0.8;

        System.out.println("Iniciando preparo do prato " + idPrato + " (" + nomePrato + ").");
        for (int i = 0; i <= 10; i++) {
            double percentual = i * 10.0;
            System.out.println("Percentual de cozimento do prato " + idPrato + ": " + percentual + "%.");
            try {
                Thread.sleep(100); // Simula o tempo de cozimento a cada 0,1 segundo
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
        System.out.println("Prato " + idPrato + " (" + nomePrato + ") pronto para entrega.");
        fazerEntrega();
    }

    private void fazerEntrega() {
        try {
            Thread.sleep(500); // Simula o tempo de entrega de 0,5 segundos
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        System.out.println("Entrega do prato " + idPrato + " realizada com sucesso.");
    }
}
