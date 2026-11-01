package fatec.catdevs.agent.ui;

import java.awt.BorderLayout;
import java.awt.Font;
import java.util.concurrent.ExecutionException;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.SwingUtilities;
import javax.swing.SwingWorker;

import fatec.catdevs.agent.service.AgentService;

public class PainelChat extends JPanel {

    private final AgentService service;
    private final PainelMensagens mensagens = new PainelMensagens();
    private final PainelEntrada entrada;
    private final JScrollPane scroll;

    public PainelChat(AgentService service) {
        super(new BorderLayout());
        this.service = service;
        this.entrada = new PainelEntrada(this::enviar);
        setBackground(Tema.FUNDO);

        JLabel titulo = new JLabel("Agente IA");
        titulo.setFont(Tema.FONTE.deriveFont(Font.BOLD, 16f));
        titulo.setForeground(Tema.TEXTO);
        titulo.setOpaque(true);
        titulo.setBackground(Tema.PAINEL);
        titulo.setBorder(BorderFactory.createEmptyBorder(14, 18, 14, 18));

        // o wrapper evita que as bolhas esticem na vertical
        JPanel wrapper = new JPanel(new BorderLayout());
        wrapper.setBackground(Tema.FUNDO);
        wrapper.add(mensagens, BorderLayout.NORTH);

        scroll = new JScrollPane(wrapper);
        scroll.setBorder(null);
        scroll.getViewport().setBackground(Tema.FUNDO);
        scroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scroll.getVerticalScrollBar().setUnitIncrement(16);
        Tema.estilizar(scroll.getVerticalScrollBar());

        add(titulo, BorderLayout.NORTH);
        add(scroll, BorderLayout.CENTER);
        add(entrada, BorderLayout.SOUTH);
    }

    private void enviar(String texto) {
        mensagens.adicionar(texto, true, null);

        IndicadorDigitando indicador = new IndicadorDigitando();
        mensagens.mostrarIndicador(indicador);
        rolarParaOFim();
        entrada.setEnabled(false);
        
        new SwingWorker<String, Void>() {
            @Override
            protected String doInBackground() {
                return service.chat(texto);
            }

            @Override
            protected void done() {
                long ms = indicador.parar();
                mensagens.removerIndicador(indicador);

                String resposta;
                try {
                    resposta = get();
                } catch (InterruptedException | ExecutionException e) {
                    resposta = "Erro: " + e.getMessage();
                }

                mensagens.adicionar(resposta, false,
                        String.format("respondido em %.1fs", ms / 1000.0));
                rolarParaOFim();
                entrada.setEnabled(true);
            }
        }.execute();
    }

    private void rolarParaOFim() {
        SwingUtilities.invokeLater(() -> {
            JScrollBar barra = scroll.getVerticalScrollBar();
            barra.setValue(barra.getMaximum());
        });
    }
}