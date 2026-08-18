package View;

import Controller.CaixaController;
/**
 * Classe principal responsável pela execução da aplicação.
 *
 * <p>Essa classe pertence à camada View do padrão MVC e atua
 * como ponto de entrada do sistema. Ela cria o controlador,
 * inicia os cinco caixas e exibe o saldo final das vendas.</p>
 *
 * @author Gabriel Gonzalez
 * @version 1.0
 */

public class Principal {
    /**
     * Método principal responsável por iniciar a aplicação.
     *
     * @param args argumentos fornecidos pela linha de comando
     * @throws InterruptedException caso a execução seja interrompida
     *                              enquanto aguarda as threads
     */
    public static void main(String[] args) throws InterruptedException {

        CaixaController controller = new CaixaController();

        System.out.println("Iniciando os 5 caixas...\n");

        controller.iniciarCaixas();

        System.out.println("\nSaldo Final: R$ "
                + controller.consultarSaldo());
    }
}
