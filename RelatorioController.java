package com.ufn.controller;

import com.ufn.model.Filial;
import com.ufn.thread.ThreadFilial;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class RelatorioController {

    private final List<Filial> filiais = new ArrayList<>();

    public void gerarDados() {
        Random random = new Random();
        for (int i = 0; i < 4; i++) {
            List<Double> vendas = new ArrayList<>();
            for (int j = 0; j < 100000; j++) {
                vendas.add(random.nextDouble() * 1000);
            }
            filiais.add(new Filial(vendas));
        }
    }

    /** Calcula o total de cada filial e retorna a soma geral. */
    public double processar() throws InterruptedException {
        List<Thread> threads = new ArrayList<>();

        for (int i = 0; i < filiais.size(); i++) {
            Thread thread = new Thread(new ThreadFilial(filiais.get(i)));
            threads.add(thread);
            thread.start();
        }

        for (int i = 0; i < threads.size(); i++) {
            threads.get(i).join();
        }

        double totalGeral = 0.0;
        for (int i = 0; i < filiais.size(); i++) {
            totalGeral += filiais.get(i).getTotal();
        }

        return totalGeral;
    }

    public List<Filial> getFiliais() {
        return filiais;
    }
}