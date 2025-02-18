package org.listadeobjetos.alpha;

import java.util.List;
import java.util.LinkedList;
import java.util.Scanner;

public class ListaObjetos {

    private List<Lista> objetos = new LinkedList<Lista>();

    public void addObject(Lista lista) {
        objetos.add(lista);
    }

    public List<Lista> acessList(String nome) {
        List<Lista> result = new LinkedList<Lista>();
        for (Lista objeto : objetos) {
            if (objeto.getNome().equalsIgnoreCase(nome)) {
                result.add(objeto);
            }
        }
        return result;
    }

    public List<Lista> getObjetos() {
        return objetos;
    }
}

