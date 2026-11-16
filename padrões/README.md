<div align="center">

# Relatório de Contribuições
### Knowledge Assistant - Agente de Base de Conhecimento com Spring AI


*Disciplina:* **Padrões de Projetos de Sistemas**

[![My Skills](https://skillicons.dev/icons?i=java,spring,maven)](https://skillicons.dev)

![Commits](https://img.shields.io/badge/COMMITS-4-a855f7?style=for-the-badge&labelColor=1e1b2e)
![Período](https://img.shields.io/badge/PER%C3%8DODO-AGO%20A%20NOV%202026-f97316?style=for-the-badge&labelColor=1e1b2e)
![License](https://img.shields.io/badge/USO-ACAD%C3%8AMICO-64748b?style=for-the-badge&labelColor=1e1b2e)

</div>

<br>

## ![](https://img.shields.io/badge/-%20-a855f7?style=for-the-badge) Resumo

O projeto é um agente de base de conhecimento feito em Java com Spring AI, desenvolvido em grupo. Nas minhas contribuições implementei os padrões de projeto **State, Strategy, Command, Observer** e **Composite** (na interface Swing), além da memória de conversa e do acesso à base de conhecimento em arquivos. 

| Padrão | Onde foi aplicado | Classes principais |
|--------|-------------------|--------------------|
| **State** | Ciclo de vida do agente: `PLANNING → RESEARCH → EXECUTING → FINAL` | `AgentContext`, `AgentState`, `PlanningState`, `ResearchState`, `ExecutingState`, `FinalState` |
| **Strategy** | Forma de planejar a resposta do agente | `PlanningStrategy`, `ReActPlanner`, `PlanThenExecutePlanner` |
| **Command** | Ações do agente sobre a base de conhecimento | `Command`, `CommandInvoker`, `CommandResult`, `ListDocumentsCommand`, `SearchKnowledgeCommand` |
| **Observer** | Notificação de eventos do agente | `AgentEvent`, `AgentEventPublisher`, `AgentObserver`, `ConsoleLogObserver` |
| **Composite** | Interface gráfica de chat em Swing | `ComponenteChat`, `PainelChat`, `PainelMensagens`, `BolhaMensagem` |

<br>

## ![](https://img.shields.io/badge/-%20-a855f7?style=for-the-badge) Contribuições

| Commit | O que foi feito |
|--------|-----------------|
| [`8e9eeff`](https://github.com/pedroquirino/Spring_IA/commit/8e9eeff) | **Configuração inicial**: ajuste do `application.properties` e atualização de dependências no `pom.xml`, com pequenos ajustes no `ChatController` e no `ChatService`. |
| [`e35a0be`](https://github.com/pedroquirino/Spring_IA/commit/e35a0be) | **Estrutura base de State e Strategy**: reorganização do pacote para `fatec.catdevs.agent`, criação do `AgentContext`, `AgentState`, `AgentStateType`, `PlanningStrategy` e `ReActPlanner`. O `ChatService` passou a se chamar `AgentService`. |
| [`271c34c`](https://github.com/pedroquirino/Spring_IA/commit/271c34c) | **Refatoração do State e interface Swing (Composite)**. Detalhada abaixo. |
| [`4eefba3`](https://github.com/pedroquirino/Spring_IA/commit/4eefba3) | **Principal contribuição técnica**: Command, Observer, Strategy, conclusão do State, memória e acesso a arquivos. Detalhada abaixo. |

<details>
<summary><b>Detalhes do commit 271c34c</b> - refatoração do State e interface Swing</summary>
<br>

- **State**
  - A lógica de cada etapa (research e executing) saiu do `AgentService` e passou para as classes `PlanningState`, `ResearchState` e `ExecutingState`, cada uma representando um estado do agente.
  - Correção das transições: `PLANNING → RESEARCH → EXECUTING → FINAL`.
  - O `AgentService` passou a escolher o estado e delegar a ele o trabalho. O loop encerra com `isFinal()`, executando o `FinalState`.
- **Composite (interface gráfica)**
  - Criação do pacote `ui` com `ChatFrame`, `PainelChat`, `PainelMensagens`, `PainelEntrada`, `BolhaMensagem`, `Balao`, `Tema` e `IndicadorDigitando`.
  - O `AgentService` é chamado em segundo plano com `SwingWorker`, para não travar a janela.
- **Organização:** a pasta `ai-demo` foi renomeada para `ia`, e os arquivos antigos (`WebSearchTool` e `AgentApplication` do pacote antigo) foram removidos.

</details>
<br>

<details>
<summary><b>Detalhes do commit 4eefba3</b> - padrões de projeto, memória e acesso a arquivos</summary>
<br>

- **Command**
  - Pacote `command` com a interface `Command`, as ações concretas `ListDocumentsCommand` (lista os documentos) e `SearchKnowledgeCommand` (busca na base), o `CommandInvoker` que as executa e o `CommandResult` com o resultado.
  - A `KnowledgeBase` atua como Receiver, executando o trabalho real.
- **Observer**
  - Pacote `observer` com o `AgentEventPublisher` (Publisher), a interface `AgentObserver` (Subscriber), o `AgentEvent` (evento publicado) e o `ConsoleLogObserver`, que registra os eventos no console.
- **State (conclusão)**
  - Os estados foram movidos do pacote `strategy` para o pacote `state`, e o enum `AgentStateType` foi removido.
  - `AgentContext` (Context) reescrito e `AgentState` ajustado.
- **Strategy**
  - Criação do `PlanThenExecutePlanner`, que se soma ao `ReActPlanner` como estratégia de planejamento, ambas implementando `PlanningStrategy`.
- **Memória em execução**
  - `ConversationMemory`: mantém as trocas da conversa durante a execução do agente.
- **Acesso a dados em arquivos**
  - `KnowledgeBase`: leitura e busca nos documentos da base de conhecimento.
  - 3 documentos criados: `padroes-de-projeto.md`, `projeto-knowledge-assistant.md` e `spring-ai.md`. Os demais foram inseridos com o decorrer do projeto.
- **Composite (interface)**
  - Criação do `ComponenteChat` e ajustes em `BolhaMensagem`, `IndicadorDigitando`, `PainelChat` e `PainelMensagens`.
- **`AgentService`:** refatorado para trabalhar com State, Command e Observer.

</details>
<br>

## ![](https://img.shields.io/badge/-%20-a855f7?style=for-the-badge) Licença

Projeto acadêmico desenvolvido para fins educacionais na disciplina de Padrões de Projetos de Sistemas, na Fatec Professor Jessen Vidal.