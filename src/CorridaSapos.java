import java.util.Random;

public class CorridaSapos {
    private static final int DISTANCIA_MAXIMA = 1000;
    private static final int TAMANHO_MAXIMO_PULO = 50;
    private static int colocacao = 1;

    public static class Sapo implements Runnable {
        private String nome;
        private int distanciaPercorrida;

        public Sapo(String nome) {
            this.nome = nome;
            this.distanciaPercorrida = 0;
        }

        @Override
        public void run() {
            Random random = new Random();
            while (distanciaPercorrida < DISTANCIA_MAXIMA) {
                int tamanhoPulo = random.nextInt(TAMANHO_MAXIMO_PULO + 1);
                distanciaPercorrida += tamanhoPulo;
                System.out.println(nome + " pulou " + tamanhoPulo + " metros. Distância percorrida: " + distanciaPercorrida + " metros.");

                try {
                    Thread.sleep(100); // pausa para simular o salto
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            synchronized (CorridaSapos.class) {
                System.out.println(nome + " chegou na colocação " + colocacao);
                colocacao++;
            }
        }
    }

    public static void main(String[] args) {
        Thread[] threads = new Thread[5];
        for (int i = 0; i < threads.length; i++) {
            Sapo sapo = new Sapo("Sapo " + (i + 1));
            threads[i] = new Thread(sapo);
            threads[i].start();
        }
    }
}
