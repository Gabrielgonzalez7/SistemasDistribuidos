package Controller;

import Model.SaldoCompartilhado;
import Model.ThreadDeTrabalho;

public class CaixaController {

    private final SaldoCompartilhado saldoCompartilhado;

    public CaixaController() {
        saldoCompartilhado = new SaldoCompartilhado();
    }

    public void iniciarCaixas() throws InterruptedException {

        Thread t1 = new ThreadDeTrabalho(saldoCompartilhado, 1000);
        Thread t2 = new ThreadDeTrabalho(saldoCompartilhado, 1000);
        Thread t3 = new ThreadDeTrabalho(saldoCompartilhado, 1000);
        Thread t4 = new ThreadDeTrabalho(saldoCompartilhado, 1000);
        Thread t5 = new ThreadDeTrabalho(saldoCompartilhado, 1000);

        t1.setName("Caixa 1");
        t2.setName("Caixa 2");
        t3.setName("Caixa 3");
        t4.setName("Caixa 4");
        t5.setName("Caixa 5");

        t1.start();
        t2.start();
        t3.start();
        t4.start();
        t5.start();

        t1.join();
        t2.join();
        t3.join();
        t4.join();
        t5.join();
    }

    public double consultarSaldo() {
        return saldoCompartilhado.getSaldoCentral();
    }
}