package com.ufn.model;

import java.util.List;

public class Filial {

    private final List<Double> vendas;
    private double total;

    public Filial(List<Double> vendas) {
        this.vendas = vendas;
    }

    public List<Double> getVendas() {
        return vendas;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }
}