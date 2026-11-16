package fatec.catdevs.agent;

import javax.swing.SwingUtilities;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;

import fatec.catdevs.agent.service.AgentService;
import fatec.catdevs.agent.ui.ChatFrame;

@SpringBootApplication
public class AgentApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext ctx = new SpringApplicationBuilder(AgentApplication.class)
                .headless(false)
                .run(args);

        AgentService service = ctx.getBean(AgentService.class);

        SwingUtilities.invokeLater(() -> new ChatFrame(service).setVisible(true));
    }
}
