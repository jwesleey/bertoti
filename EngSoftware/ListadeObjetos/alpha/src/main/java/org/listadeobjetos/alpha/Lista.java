package org.listadeobjetos.alpha;

public class Lista {

    private String nome;
    private String categoria;
    private String detalhes;

    public Lista(String nome, String categoria, String detalhes) {

        this.nome = nome;
        this.categoria = categoria;
        this.detalhes = detalhes;
    }

    public String getNome() {
        return nome;
    }

    public void setNome() {
        this.nome = nome;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria() {
        this.categoria = categoria;
    }

    public String getDetalhes() {
        return detalhes;
    }

    public void setValor() {
        this.detalhes = detalhes;
    }

}
