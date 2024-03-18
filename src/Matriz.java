import java.util.Random;

public class Matriz {
    private static final int ROWS = 3;
    private static final int COLS = 5;
    private static int[][] matriz = new int[ROWS][COLS];

    public static void main(String[] args) {
        // Preenche a matriz com números aleatórios
        Random random = new Random();
        for (int i = 0; i < ROWS; i++) {
            for (int j = 0; j < COLS; j++) {
                matriz[i][j] = random.nextInt(10); // Números aleatórios de 0 a 9
            }
        }

        // Cria e inicia as threads para calcular a soma de cada linha
        for (int i = 0; i < ROWS; i++) {
            final int row = i;
            Thread thread = new Thread(() -> {
                int sum = 0;
                for (int j = 0; j < COLS; j++) {
                    sum += matriz[row][j];
                }
                System.out.println("Thread " + Thread.currentThread().getId() + " - Linha " + row + ": Soma = " + sum);
            });
            thread.start();
        }
    }
}