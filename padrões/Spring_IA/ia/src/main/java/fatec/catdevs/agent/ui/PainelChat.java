package fatec.catdevs.agent.ui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.util.concurrent.ExecutionException;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.SwingUtilities;
import javax.swing.SwingWorker;

import fatec.catdevs.agent.observer.AgentEvent;
import fatec.catdevs.agent.service.AgentService;

public class PainelChat extends JPanel {

    private final AgentService service;
    private final PainelMensagens mensagens = new PainelMensagens();
    private final PainelEntrada entrada;
    private final JScrollPane scroll;
    private final JLabel status = new JLabel(" ");

    public PainelChat(AgentService service) {
        super(new BorderLayout());
        this.service = service;
        this.entrada = new PainelEntrada(this::enviar);
        setBackground(Tema.FUNDO);

        JLabel titulo = new JLabel("Agente IA");
        titulo.setFont(Tema.FONTE.deriveFont(Font.BOLD, 16f));
        titulo.setForeground(Tema.TEXTO);
        titulo.setBorder(BorderFactory.createEmptyBorder(14, 18, 14, 18));

        // Strategy: o usuário troca a estratégia de planejamento em tempo de execução
        JComboBox<String> planejador = new JComboBox<>(service.planners().toArray(new String[0]));
        planejador.setSelectedItem(service.currentPlanner());
        planejador.setToolTipText("Estratégia de planejamento");
        planejador.addActionListener(e -> service.usePlanner((String) planejador.getSelectedItem()));

        // Composite: getTexto() percorre todos os filhos do painel de mensagens
        JButton copiar = new JButton("Copiar conversa");
        copiar.addActionListener(e -> Toolkit.getDefaultToolkit().getSystemClipboard()
                .setContents(new StringSelection(mensagens.getTexto()), null));

        JPanel acoes = new JPanel(new FlowLayout(FlowLayout.RIGHT, 8, 12));
        acoes.setOpaque(false);
        acoes.add(planejador);
        acoes.add(copiar);

        status.setFont(Tema.FONTE.deriveFont(12f));
        status.setForeground(Tema.TEXTO_SUAVE);
        status.setBorder(BorderFactory.createEmptyBorder(0, 18, 8, 18));

        JPanel topo = new JPanel(new BorderLayout());
        topo.setBackground(Tema.PAINEL);
        topo.add(titulo, BorderLayout.WEST);
        topo.add(acoes, BorderLayout.EAST);
        topo.add(status, BorderLayout.SOUTH);

        // Observer: a GUI acompanha o que o agente está fazendo
        service.addObserver(evento -> SwingUtilities.invokeLater(() -> mostrarStatus(evento)));

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

        add(topo, BorderLayout.NORTH);
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

    private void mostrarStatus(AgentEvent evento) {
        boolean concluido = evento.type() == AgentEvent.Type.STATE_CHANGED
                && evento.message().equals("Concluído");
        status.setText(concluido ? " " : evento.descricao());
    }

    private void rolarParaOFim() {
        SwingUtilities.invokeLater(() -> {
            JScrollBar barra = scroll.getVerticalScrollBar();
            barra.setValue(barra.getMaximum());
        });
    }
}