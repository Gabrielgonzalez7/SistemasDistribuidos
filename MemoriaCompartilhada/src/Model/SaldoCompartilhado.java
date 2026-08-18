package Model;
/**
 * Representa o saldo central compartilhado entre as diferentes threads
 * responsáveis pelo processamento das vendas dos caixas.
 *
 * <p>A classe utiliza o modificador {@code synchronized} nos métodos
 * que acessam o saldo para garantir que apenas uma thread por vez
 * possa realizar uma operação sobre o recurso compartilhado.</p>
 *
 * <p>Essa sincronização evita condições de corrida (race conditions),
 * garantindo a consistência do saldo durante a execução concorrente.</p>
 *
 */
public class SaldoCompartilhado {
    /**
     * Armazena o saldo total das vendas realizadas pelos caixas.
     */
    private double saldoCentral = 0.0;
     /**
     * Adiciona o valor de uma venda ao saldo central.
     *
     *  método é sincronizado para garantir que múltiplas threads
     * não alterem o saldo simultaneamente.
     *
     * @param valor valor da venda que será adicionada ao saldo
     */

    public synchronized void adicionarVenda(double valor) {
        saldoCentral += valor;
        System.out.println(Thread.currentThread().getName() + " adicionou: " +  valor + ", saldo atual: R$ " + saldoCentral);
    }
     /**
     * Retorna o saldo central atual.
     *
     *  método é sincronizado para garantir uma leitura segura
     * do recurso compartilhado.
     *
     * @return saldo total acumulado pelas vendas
     */

    public synchronized double getSaldoCentral() { //metodo get de acesso tipo leitura do saldo
        return saldoCentral;
    }
}

