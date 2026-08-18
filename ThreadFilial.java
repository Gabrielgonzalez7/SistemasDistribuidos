package com.ufn.thread;

import com.ufn.model.Filial;

public class ThreadFilial implements Runnable {

    private final Filial filial;

    public ThreadFilial(Filial filial) {
        this.filial = filial;
    }

    /** Soma local de uma filial. */
    @Override
    public void run() {
        double soma = 0.0;
        for (double venda : filial.getVendas()) {
            soma += venda;
        }
        filial.setTotal(soma);
    }
}