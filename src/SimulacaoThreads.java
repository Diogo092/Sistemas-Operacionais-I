import java.util.concurrent.*;

public class SimulacaoThreads {
    public static void main(String[] args) {
        ExecutorService executor = Executors.newFixedThreadPool(21);
        for (int i = 1; i <= 21; i++) {
            Runnable worker = new WorkerThread(i);
            executor.execute(worker);
        }
        executor.shutdown();
        while (!executor.isTerminated()) {
        }
        System.out.println("Todas as threads foram concluídas.");
    }
}

class WorkerThread implements Runnable {
    private final int threadId;

    public WorkerThread(int id) {
        this.threadId = id;
    }

    @Override
    public void run() {
        if (threadId % 3 == 1) {
            fazerTransacoes(1);
        } else if (threadId % 3 == 2) {
            fazerTransacoes(1.5);
        } else {
            fazerTransacoes(2);
        }
    }

    private void fazerTransacoes(double tempoTransacao) {
        System.out.println("Thread " + threadId + " iniciou cálculos.");
        calcularTempo(0.2, 1);
        System.out.println("Thread " + threadId + " iniciou transação no banco de dados.");
        calcularTempo(tempoTransacao, 1);
        System.out.println("Thread " + threadId + " concluiu transação no banco de dados.");
        calcularTempo(0.2, 1);
        System.out.println("Thread " + threadId + " concluiu cálculos.");
    }

    private void calcularTempo(double tempoMinimo, double tempoMaximo) {
        double tempo = tempoMinimo + Math.random() * (tempoMaximo - tempoMinimo);
        try {
            Thread.sleep((long) (tempo * 1000));
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}

