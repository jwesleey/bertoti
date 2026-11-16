package fatec.catdevs.agent.ui;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JComponent;
import javax.swing.JPanel;

/** Composite do padrão Composite: guarda filhos (bolhas, indicador) e os trata de forma uniforme. */
public class PainelMensagens extends JPanel implements ComponenteChat {

    private final List<ComponenteChat> filhos = new ArrayList<>();

    public PainelMensagens() {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setBackground(Tema.FUNDO);
        setBorder(BorderFactory.createEmptyBorder(8, 0, 8, 0));
    }

    @Override
    public void adicionar(ComponenteChat filho) {
        filhos.add(filho);
        add(filho.getView());
        atualizar();
    }

    @Override
    public void remover(ComponenteChat filho) {
        filhos.remove(filho);
        remove(filho.getView());
        atualizar();
    }

    @Override
    public JComponent getView() {
        return this;
    }

    /** Operação recursiva: pede o texto a cada filho e junta tudo. */
    @Override
    public String getTexto() {
        return filhos.stream()
                .map(ComponenteChat::getTexto)
                .filter(t -> !t.isBlank())
                .collect(Collectors.joining("\n\n"));
    }

    public void adicionar(String texto, boolean doUsuario, String rodape) {
        adicionar(new BolhaMensagem(texto, doUsuario, rodape));
    }

    public void mostrarIndicador(IndicadorDigitando indicador) {
        adicionar(indicador);
        indicador.iniciar();
    }

    public void removerIndicador(IndicadorDigitando indicador) {
        remover(indicador);
    }

    private void atualizar() {
        revalidate();
        repaint();
    }
}
