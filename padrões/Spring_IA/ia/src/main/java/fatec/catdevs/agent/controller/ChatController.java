package fatec.catdevs.agent.controller;

import fatec.catdevs.agent.service.AgentService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/chat")
public class ChatController {

    private final AgentService agentService;

    public ChatController(AgentService agentService) {
        this.agentService = agentService;
    }

    @GetMapping
    public String chat(@RequestParam String message) {
        return agentService.chat(message);
    }
}