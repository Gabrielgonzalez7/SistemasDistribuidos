package Model;
/**
 * Representa uma thread responsável por simular as operações
 * de um caixa realizando diversas vendas.
 *
 * Cada instância da classe recebe uma referência para o saldo
 * compartilhado e uma quantidade de vendas a serem realizadas.</p>
 *
 *A classe estende {@link Thread} para permitir a execução
 * concorrente das operações dos diferentes caixas.</p>
 *
 */
public class ThreadDeTrabalho extends Thread{
     /**
     * Referência para o saldo compartilhado entre os caixas.
     */
    private final SaldoCompartilhado saldoCompartilhado;
    /**
     * Quantidade de vendas que a thread deverá realizar.
     */
    private final int quantidadeVendas;
     /**
     * Cria uma nova thread de trabalho.
     *
     * parametro saldoCompartilhado objeto responsável pelo armazenamento
     *                           do saldo compartilhado
     * parametro quantidadeVendas quantidade de vendas que serão realizadas
     */

    public ThreadDeTrabalho(SaldoCompartilhado saldoCompartilhado, int quantidadeVendas) {
        this.saldoCompartilhado = saldoCompartilhado;
        this.quantidadeVendas = quantidadeVendas;
    }
    /**
     * Executa as vendas simuladas pela thread.
     *
     * Cada venda adiciona R$ 10,00 ao saldo compartilhado.
     * Após cada operação, a thread aguarda 5 milissegundos para
     * simular um intervalo entre as vendas.</p>
     */

    @Override
    public void run() {
        for (int i = 1; i <= this.quantidadeVendas; i++) {
            saldoCompartilhado.adicionarVenda(10.0);
            try {
                Thread.sleep(5);
            } catch (InterruptedException ignored) {
                                // Interrupção da thread ignorada durante a espera.

            }
        }
    }
}

