package fatec.catdevs.agent.ui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.RenderingHints;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JScrollBar;
import javax.swing.plaf.basic.BasicScrollBarUI;

public final class Tema {

    public static final Color FUNDO = new Color(0x0F1117);
    public static final Color PAINEL = new Color(0x171A23);
    public static final Color CAMPO = new Color(0x1E2230);
    public static final Color BOLHA_USUARIO = new Color(0x4F46E5);
    public static final Color BOLHA_AGENTE = new Color(0x242836);
    public static final Color TEXTO = new Color(0xE6E8EF);
    public static final Color TEXTO_SUAVE = new Color(0x8B90A0);
    public static final Color DESTAQUE = new Color(0x6366F1);

    public static final Font FONTE = new Font("SansSerif", Font.PLAIN, 14);

    private Tema() {
    }


    public static void estilizar(JScrollBar barra) {
        barra.setPreferredSize(new Dimension(10, 0));
        barra.setUI(new BasicScrollBarUI() {
            @Override
            protected void paintThumb(Graphics g, JComponent c, Rectangle r) {
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2.setColor(new Color(0x3A3F52));
                g2.fillRoundRect(r.x + 2, r.y, r.width - 4, r.height, 8, 8);
                g2.dispose();
            }

            @Override
            protected void paintTrack(Graphics g, JComponent c, Rectangle r) {
                g.setColor(FUNDO);
                g.fillRect(r.x, r.y, r.width, r.height);
            }

            @Override
            protected JButton createDecreaseButton(int orientation) {
                return botaoVazio();
            }

            @Override
            protected JButton createIncreaseButton(int orientation) {
                return botaoVazio();
            }

            private JButton botaoVazio() {
                JButton b = new JButton();
                b.setPreferredSize(new Dimension(0, 0));
                return b;
            }
        });
    }
}