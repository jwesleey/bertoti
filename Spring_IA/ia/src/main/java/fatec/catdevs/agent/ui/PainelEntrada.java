package fatec.catdevs.agent.ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.ActionListener;
import java.util.function.Consumer;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class PainelEntrada extends JPanel {

    private final Campo campo = new Campo();
    private final Botao botao = new Botao("Enviar");

    public PainelEntrada(Consumer<String> aoEnviar) {
        super(new BorderLayout(10, 0));
        setBackground(Tema.PAINEL);
        setBorder(BorderFactory.createEmptyBorder(12, 14, 12, 14));
        add(campo, BorderLayout.CENTER);
        add(botao, BorderLayout.EAST);

        ActionListener acao = e -> {
            String texto = campo.getText().trim();
            if (!texto.isEmpty()) {
                campo.setText("");
                aoEnviar.accept(texto);
            }
        };
        botao.addActionListener(acao);
        campo.addActionListener(acao);
    }


    @Override
    public void setEnabled(boolean enabled) {
        super.setEnabled(enabled);
        campo.setEnabled(enabled);
        botao.setEnabled(enabled);
        if (enabled) {
            campo.requestFocusInWindow();
        }
    }

    private static class Campo extends JTextField {

        Campo() {
            setOpaque(false);
            setFont(Tema.FONTE);
            setForeground(Tema.TEXTO);
            setCaretColor(Tema.TEXTO);
            setDisabledTextColor(Tema.TEXTO_SUAVE);
            setBorder(BorderFactory.createEmptyBorder(10, 16, 10, 16));
        }

        @Override
        protected void paintComponent(Graphics g) {
            Graphics2D g2 = (Graphics2D) g.create();
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g2.setColor(Tema.CAMPO);
            g2.fillRoundRect(0, 0, getWidth(), getHeight(), getHeight(), getHeight());
            g2.dispose();
            super.paintComponent(g);
        }
    }

    private static class Botao extends JButton {

        Botao(String texto) {
            super(texto);
            setContentAreaFilled(false);
            setBorderPainted(false);
            setFocusPainted(false);
            setRolloverEnabled(true);
            setForeground(Color.WHITE);
            setFont(Tema.FONTE.deriveFont(Font.BOLD));
            setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            setBorder(BorderFactory.createEmptyBorder(10, 22, 10, 22));
        }

        @Override
        protected void paintComponent(Graphics g) {
            Graphics2D g2 = (Graphics2D) g.create();
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            Color cor = !isEnabled() ? new Color(0x3A3F52)
                    : getModel().isRollover() ? new Color(0x818CF8)
                    : Tema.DESTAQUE;
            g2.setColor(cor);
            g2.fillRoundRect(0, 0, getWidth(), getHeight(), getHeight(), getHeight());
            g2.dispose();
            super.paintComponent(g);
        }
    }
}