package View;

import Controller.CaixaController;

public class Principal {

    public static void main(String[] args) throws InterruptedException {

        CaixaController controller = new CaixaController();

        System.out.println("Iniciando os 5 caixas...\n");

        controller.iniciarCaixas();

        System.out.println("\nSaldo Final: R$ "
                + controller.consultarSaldo());
    }
}