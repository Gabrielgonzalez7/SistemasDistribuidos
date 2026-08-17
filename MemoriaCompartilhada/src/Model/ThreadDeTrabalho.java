package Model;

public class ThreadDeTrabalho extends Thread{
    private final SaldoCompartilhado saldoCompartilhado;
    private final int quantidadeVendas;

    public ThreadDeTrabalho(SaldoCompartilhado saldoCompartilhado, int quantidadeVendas) {
        this.saldoCompartilhado = saldoCompartilhado;
        this.quantidadeVendas = quantidadeVendas;
    }

    @Override
    public void run() {
        for (int i = 1; i <= this.quantidadeVendas; i++) {
            saldoCompartilhado.adicionarVenda(10.0);
            try {
                Thread.sleep(5);
            } catch (InterruptedException ignored) {}
        }
    }
}

