package com.ufn.view;

import com.ufn.model.Filial;

import java.util.List;

public class RelatorioVendasView {

    public void exibir(List<Filial> filiais, double total) {
        for (int i = 0; i < filiais.size(); i++) {
            System.out.printf("Filial %d: R$ %.2f%n", i + 1, filiais.get(i).getTotal());
        }
        System.out.printf("Faturamento total: R$ %.2f%n", total);
    }
}
