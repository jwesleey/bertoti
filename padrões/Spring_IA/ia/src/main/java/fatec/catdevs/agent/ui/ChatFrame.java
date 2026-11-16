package fatec.catdevs.agent.ui;

import java.awt.Dimension;

import javax.swing.JFrame;

import fatec.catdevs.agent.service.AgentService;

public class ChatFrame extends JFrame {

    public ChatFrame(AgentService service) {
        super("Chat com o Agente");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        getContentPane().setBackground(Tema.FUNDO);
        add(new PainelChat(service));
        setSize(600, 700);
        setMinimumSize(new Dimension(420, 500));
        setLocationRelativeTo(null);
    }
}