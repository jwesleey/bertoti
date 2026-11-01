package fatec.catdevs.agent.ui;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JPanel;

public class PainelMensagens extends JPanel {

    public PainelMensagens() {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setBackground(Tema.FUNDO);
        setBorder(BorderFactory.createEmptyBorder(8, 0, 8, 0));
    }

    public void adicionar(String texto, boolean doUsuario, String rodape) {
        add(new BolhaMensagem(texto, doUsuario, rodape));
        atualizar();
    }

    public void mostrarIndicador(IndicadorDigitando indicador) {
        add(indicador);
        indicador.iniciar();
        atualizar();
    }

    public void removerIndicador(IndicadorDigitando indicador) {
        remove(indicador);
        atualizar();
    }

    private void atualizar() {
        revalidate();
        repaint();
    }
}