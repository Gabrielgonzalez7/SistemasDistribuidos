package com.ufn;

import com.ufn.controller.RelatorioController;
import com.ufn.view.RelatorioVendasView;

public class Principal {

    public static void main(String[] args) throws InterruptedException {
        RelatorioController controller = new RelatorioController();
        controller.gerarDados();
        double totalGeral = controller.processar();

        new RelatorioVendasView().exibir(controller.getFiliais(), totalGeral);
    }
}
