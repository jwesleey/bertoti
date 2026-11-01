package fatec.catdevs.agent.ui;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.LayoutManager;
import java.awt.RenderingHints;

import javax.swing.BorderFactory;
import javax.swing.JPanel;

public class Balao extends JPanel {

    private final Color cor;

    public Balao(Color cor, LayoutManager layout) {
        super(layout);
        this.cor = cor;
        setOpaque(false);
        setBorder(BorderFactory.createEmptyBorder(10, 14, 10, 14));
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setColor(cor);
        g2.fillRoundRect(0, 0, getWidth(), getHeight(), 22, 22);
        g2.dispose();
        super.paintComponent(g);
    }
}