package fatec.catdevs.agent.ui;

import javax.swing.JComponent;

/**
 * Component do padrão Composite: folhas e compostos da tela de chat são usados pelo mesmo contrato.
 */
public interface ComponenteChat {

    JComponent getView();

    String getTexto();

    default void adicionar(ComponenteChat filho) {
        throw new UnsupportedOperationException("Folha não aceita filhos");
    }

    default void remover(ComponenteChat filho) {
        throw new UnsupportedOperationException("Folha não possui filhos");
    }
}
