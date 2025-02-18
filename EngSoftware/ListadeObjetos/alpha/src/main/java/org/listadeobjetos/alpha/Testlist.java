package org.listadeobjetos.alpha;

import org.junit.Test;
import java.util.List;
import static org.junit.Assert.assertEquals;

public class Testlist {

    @Test
    public void Testlist() {

        ListaObjetos objects = new ListaObjetos();

        Lista caneta = new Lista("Caneta", "Usado para escrever.", "Cor Azul, esferográfica");
        Lista celular = new Lista("Celular", "Smartphone", "Iphone");
        Lista ventilador = new Lista("Ventilador", "Usado no calor", "4 hélices");


        objects.addObject(caneta);
        objects.addObject(celular);
        objects.addObject(ventilador);

        List<Lista> buscador = objects.acessList("Caneta");    // <- Alterar o nome do Objeto

        if (!buscador.isEmpty()) {
            assertEquals(buscador.get(0).getNome(), caneta.getNome());   // <- Considerar objeto.getNome()
        } else {
            System.out.println("Nenhum objeto encontrado.");
        }

        if (!buscador.isEmpty()) {
            System.out.println(buscador.get(0).getNome() + " - " + buscador.get(0).getCategoria() + " - " + buscador.get(0).getDetalhes());
        }
    }
}