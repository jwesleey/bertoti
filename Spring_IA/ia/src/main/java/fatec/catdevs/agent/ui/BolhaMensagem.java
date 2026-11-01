package fatec.catdevs.agent.ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;

public class BolhaMensagem extends JPanel {

    private static final int LARGURA_MAXIMA = 320;

    public BolhaMensagem(String texto, boolean doUsuario, String rodape) {
        super(new FlowLayout(doUsuario ? FlowLayout.RIGHT : FlowLayout.LEFT, 12, 4));
        setOpaque(false);

        Balao balao = new Balao(
                doUsuario ? Tema.BOLHA_USUARIO : Tema.BOLHA_AGENTE,
                new BorderLayout(0, 4));

        balao.add(criarTexto(texto), BorderLayout.CENTER);

        if (rodape != null) {
            JLabel info = new JLabel(rodape);
            info.setFont(Tema.FONTE.deriveFont(11f));
            info.setForeground(Tema.TEXTO_SUAVE);
            balao.add(info, BorderLayout.SOUTH);
        }

        add(balao);
    }

    private JTextArea criarTexto(String texto) {
        JTextArea area = new JTextArea(texto);
        area.setEditable(false);                
        area.setWrapStyleWord(true);            
        area.setOpaque(false);               
        area.setBorder(null);
        area.setFont(Tema.FONTE);
        area.setForeground(Tema.TEXTO);
        area.setSelectionColor(new Color(0x818CF8));
        area.setSelectedTextColor(Color.WHITE);

        area.setLineWrap(false);
        int largura = Math.min(area.getPreferredSize().width + 4, LARGURA_MAXIMA);


        area.setLineWrap(true);
        area.setSize(new Dimension(largura, Short.MAX_VALUE));
        area.setPreferredSize(new Dimension(largura, area.getPreferredSize().height));

        return area;
    }

    @Override
    public Dimension getMaximumSize() {
        return new Dimension(Integer.MAX_VALUE, getPreferredSize().height);
    }
}