package com.example.demo.model;

import java.util.Objects;

public class Beta {

    private String nome;
    private Long order;

    public Beta(String nome){
        this.nome = nome;
    }

    public Beta(String nome, Long order) {
        this.nome = nome;
        this.order = order;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Beta)) return false;
        Beta beta = (Beta) o;
        return Objects.equals(getOrder(), beta.getOrder());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getOrder());
    }

    @Override
    public String toString() {
        return "Beta{" +
                "nome='" + nome + '\'' +
                ", order=" + order +
                '}';
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Long getOrder() {
        return order;
    }

    public void setOrder(Long order) {
        this.order = order;
    }
}
