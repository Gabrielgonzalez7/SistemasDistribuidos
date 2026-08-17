package Model;

public class SaldoCompartilhado {
    private double saldoCentral = 0.0;

    public synchronized void adicionarVenda(double valor) {
        saldoCentral += valor;
        System.out.println(Thread.currentThread().getName() + " adicionou: " +  valor + ", saldo atual: R$ " + saldoCentral);
    }

    public synchronized double getSaldoCentral() { //metodo get de acesso tipo leitura do saldo
        return saldoCentral;
    }
}

