
import java.util.Random;

public class ThreadVetor implements Runnable {
    private int valor;
    private int[] vetor;

    public ThreadVetor(int valor, int[] vetor) {
        this.valor = valor;
        this.vetor = vetor;
    }

    @Override
    public void run() {
        long startTime = System.currentTimeMillis();

        if (valor % 2 == 0) {
            for (int i = 0; i < vetor.length; i++) {
                // percorre o vetor usando estrutura for
            }
        } else {
            for (int num : vetor) {
                // percorre o vetor usando estrutura foreach
            }
        }

        long endTime = System.currentTimeMillis();
        long elapsedTimeSeconds = (endTime - startTime) / 1000;
        System.out.println("Tempo de execução: " + elapsedTimeSeconds + " segundos");
    }

    public static void main(String[] args) {
        int[] vetor = new int[1000];
        Random random = new Random();
        for (int i = 0; i < vetor.length; i++) {
            vetor[i] = random.nextInt(100) + 1; // números aleatórios de 1 a 100
        }

        ThreadVetor threadPar = new ThreadVetor(2, vetor);
        ThreadVetor threadImpar = new ThreadVetor(1, vetor);

        Thread t1 = new Thread(threadPar);
        Thread t2 = new Thread(threadImpar);

        t1.start();
        t2.start();
    }
}
