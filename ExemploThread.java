import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

class TarefaPopular extends Thread {
    private List<Integer> lista;
    private int quantidade;

    public TarefaPopular(List<Integer> lista, int quantidade) {
        this.lista = lista;
        this.quantidade = quantidade;
    }

    @Override
    public void run() {
        Random gerador = new Random();
        for (int i = 0; i < this.quantidade; i++) {
            lista.add(gerador.nextInt(500));
        }
    }
}

public class ExemploThread {

    public static void main(String[] args) throws InterruptedException {
        List<Integer> listaA = Collections.synchronizedList(new ArrayList<>());
        List<Integer> listaB = Collections.synchronizedList(new ArrayList<>());
        Random gerador = new Random();

        Thread t1 = new Thread(() -> {
            for (int i = 0; i < 1000; i++) {
                listaA.add(gerador.nextInt(200));
            }
        });

        Thread t2 = new Thread(() -> {
            for (int i = 0; i < 1000; i++) {
                listaB.add(gerador.nextInt(200));
            }
        });

        t1.start();
        t2.start();

        List<Integer> listaC = Collections.synchronizedList(new ArrayList<>());
        List<Integer> listaD = Collections.synchronizedList(new ArrayList<>());

        TarefaPopular t3 = new TarefaPopular(listaC, 500);
        TarefaPopular t4 = new TarefaPopular(listaD, 500);

        t3.start();
        t4.start();

        t1.join();
        t2.join();
        t3.join();
        t4.join();

        System.out.println("Tamanho da Lista A: " + listaA.size());
        System.out.println("Tamanho da Lista B: " + listaB.size());
        System.out.println("Tamanho da Lista C: " + listaC.size());
        System.out.println("Tamanho da Lista D: " + listaD.size());
    }
}