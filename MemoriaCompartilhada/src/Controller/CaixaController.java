package Controller;

import Model.SaldoCompartilhado;
import Model.ThreadDeTrabalho;
/**
 * Controlador responsável por coordenar a execução dos caixas
 * e o acesso ao saldo compartilhado.
 *
 * <p>Essa classe pertence à camada Controller do padrão MVC.
 * Sua função é controlar a criação, inicialização e finalização
 * das threads que representam os caixas.</p>
 *
 * @author Gabriel Gonzalez
 * @version 1.0
 */

public class CaixaController {
  /**
     * Objeto que representa o saldo compartilhado entre os caixas.
     */
    private final SaldoCompartilhado saldoCompartilhado;
         /**
     * Cria um novo controlador de caixas.
     *
     * <p>Durante a criação do controlador, um novo objeto de
     * {@link SaldoCompartilhado} é inicializado para armazenar
     * o saldo central.</p>
     */

    public CaixaController() {
        saldoCompartilhado = new SaldoCompartilhado();
    }
      /**
     * Inicia os cinco caixas responsáveis pelas operações de venda.
     *
     * <p>Cada caixa é representado por uma thread independente.
     * Todas as threads utilizam o mesmo objeto de saldo compartilhado,
     * permitindo demonstrar o acesso concorrente a um recurso comum.</p>
     *
     * <p>O método utiliza {@code join()} para aguardar a finalização
     * de todas as threads antes de continuar a execução.</p>
     *
     * @throws InterruptedException caso a thread principal seja
     *                              interrompida enquanto aguarda
     *                              a finalização dos caixas
     */

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
   /**
     * Consulta o saldo central após as operações realizadas pelos caixas.
     *
     * @return saldo total acumulado pelas vendas
     */

    public double consultarSaldo() {
        return saldoCompartilhado.getSaldoCentral();
    }
}
