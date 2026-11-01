package fatec.catdevs.agent.ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;

public class IndicadorDigitando extends JPanel {

    private final Pontos pontos = new Pontos();
    private final JLabel tempo = new JLabel("0,0s");
    private final Timer timer = new Timer(50, e -> atualizar());
    private long inicio;

    public IndicadorDigitando() {
        super(new FlowLayout(FlowLayout.LEFT, 12, 4));
        setOpaque(false);

        tempo.setFont(Tema.FONTE.deriveFont(12f));
        tempo.setForeground(Tema.TEXTO_SUAVE);

        Balao balao = new Balao(Tema.BOLHA_AGENTE, new BorderLayout(10, 0));
        balao.add(pontos, BorderLayout.WEST);
        balao.add(tempo, BorderLayout.EAST);
        add(balao);
    }

    public void iniciar() {
        inicio = System.currentTimeMillis();
        timer.start();
    }

    public long parar() {
        timer.stop();
        return System.currentTimeMillis() - inicio;
    }

    private void atualizar() {
        long ms = System.currentTimeMillis() - inicio;
        tempo.setText(String.format("%.1fs", ms / 1000.0));
        pontos.setTempo(ms);
    }

    @Override
    public Dimension getMaximumSize() {
        return new Dimension(Integer.MAX_VALUE, getPreferredSize().height);
    }

    private static class Pontos extends JComponent {

        private long ms;

        Pontos() {
            setPreferredSize(new Dimension(48, 20));
        }

        void setTempo(long ms) {
            this.ms = ms;
            repaint();
        }

        @Override
        protected void paintComponent(Graphics g) {
            Graphics2D g2 = (Graphics2D) g.create();
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            int d = 8;
            for (int i = 0; i < 3; i++) {
                double onda = (Math.sin(ms / 180.0 - i * 0.9) + 1) / 2; // 0..1
                int alpha = (int) (70 + 185 * onda);
                int x = 4 + i * 14;
                int y = (int) (getHeight() / 2.0 - d / 2.0 - 3 * onda);
                g2.setColor(new Color(154, 160, 255, alpha));
                g2.fillOval(x, y, d, d);
            }
            g2.dispose();
        }
    }
}