<div align="center">

# Knowledge Assistant
### Agente de Base de Conhecimento com Spring AI

**Fatec Professor Jessen Vidal**
*Disciplina:* **Padrões de Projetos de Sistemas**
*Professor:* **Giuliano Araújo Bertoti**

[![My Skills](https://skillicons.dev/icons?i=java,spring,maven)](https://skillicons.dev)

![Status](https://img.shields.io/badge/STATUS-EM%20DESENVOLVIMENTO-fbbf24?style=for-the-badge&labelColor=1e1b2e)
![Java](https://img.shields.io/badge/JAVA-17%2B-f97316?style=for-the-badge&labelColor=1e1b2e)
![License](https://img.shields.io/badge/USO-ACAD%C3%8AMICO-64748b?style=for-the-badge&labelColor=1e1b2e)

</div>

<br>

## ![](https://img.shields.io/badge/-%20-a855f7?style=for-the-badge) Sobre o Projeto

Este repositório contém a implementação de um **Agente de Base de Conhecimento (Knowledge Assistant)**, desenvolvido com **Spring AI** e modelos de IA totalmente **open-source**. O objetivo é construir um agente capaz de receber requisições do usuário, planejar suas ações, executar ferramentas (*tools*) e validar os resultados obtidos — tudo utilizando um modelo de linguagem aberto rodando localmente.

O projeto é desenvolvido na **Fatec Professor Jessen Vidal**, como trabalho em grupo da disciplina **Padrões de Projetos de Sistemas**, ministrada pelo professor **Giuliano Araújo Bertoti**.

O tema escolhido faz parte do catálogo de casos de uso de agentes empresariais descrito em [Spring AI — Enterprise Agent Use Cases](https://springdevpro.com/spring-ai/source-code/agent/#enterprise-agent-use-cases), com base na categoria **Knowledge Assistants**. Este projeto foi validado com o professor da disciplina conforme orientação da atividade.

<br>

## ![](https://img.shields.io/badge/-%20-a855f7?style=for-the-badge) Objetivo Acadêmico

Implementar, na arquitetura do agente, os seguintes **padrões de projeto** (design patterns), documentados também via **diagramas UML**:

| # | Padrão | Aplicação no projeto | Conceito |
|---|--------|----------------------|----------|
| 1 | **Strategy** | *Planning Strategies* — `ReActPlanner`, `PlanThenExecutePlanner`, `HumanInTheLoopPlanner` | Define estratégias intercambiáveis de planejamento das ações do agente |
| 2 | **State** | *State Machine Concepts* | Gerencia os estados do agente durante o ciclo de execução |
| 3 | **Command** | *Tool Execution* | Encapsula a execução das ferramentas (*tools*) chamadas pelo agente |
| 4 | **Observer** | *Execution Monitoring* | Monitora e valida se a execução das ações ocorreu com sucesso |
| + | **Composite** | Aplicado na **GUI** (Java AWT/Swing) | Organiza a interface gráfica em componentes compostos |

Referência dos padrões utilizados como base conceitual: [Spring AI — Design Patterns Used](https://springdevpro.com/spring-ai/source-code/agent/#design-patterns-used).

<br>

## ![](https://img.shields.io/badge/-%20-a855f7?style=for-the-badge) Arquitetura

```
┌─────────────────────────────────────────────────────────┐
│                      GUI (Java Swing/AWT)               │
│                     Padrão: Composite                   │
└───────────────────────────┬─────────────────────────────┘
                            │
                 Requisição do usuário
                            │
                            ▼
┌─────────────────────────────────────────────────────────┐
│                  Strategy (Planejamento)                │
│   ReActPlanner | PlanThenExecutePlanner | HITL Planner  │
└───────────────────────────┬─────────────────────────────┘
                            │
                            ▼
┌─────────────────────────────────────────────────────────┐
│               State (Máquina de Estados)                │
│       Controla o ciclo de vida da execução do agente    │
└───────────────────────────┬─────────────────────────────┘
                            │
                            ▼
┌─────────────────────────────────────────────────────────┐
│              Command (Execução de Ferramentas)          │
│            Encapsula chamadas às tools do agente        │
└───────────────────────────┬─────────────────────────────┘
                            │
                            ▼
┌─────────────────────────────────────────────────────────┐
│             Observer (Monitoramento de Execução)        │
│        Valida sucesso/falha e notifica interessados     │
└─────────────────────────────────────────────────────────┘
```
<br>

## ![](https://img.shields.io/badge/-%20-a855f7?style=for-the-badge) Diagramas UML

Os diagramas de classes ficam em [`ia/uml`](./ia/uml) e mostram onde cada padrão se encontra no código Java. Clique em cada um para expandir.

<details>
<summary><b>Visão geral</b> — arquitetura completa, por padrão</summary>
<br>

```
  ┌───────────────────────────────────────────────────────────────────────┐
  │                            ui  (Composite)                            │
  ├───────────────────────────────────────────────────────────────────────┤
  │ ChatFrame, PainelChat, PainelEntrada                                  │
  │ ComponenteChat, PainelMensagens                                       │
  │ BolhaMensagem, IndicadorDigitando                                     │
  └───────────────────────────────────────────────────────────────────────┘
                                      │ usa
                                      │
                                      ▼
  ┌───────────────────────────────────────────────────────────────────────┐
  │                            service / memory                           │
  ├───────────────────────────────────────────────────────────────────────┤
  │ AgentService, ConversationMemory                                      │
  └───────────────────────────────────────────────────────────────────────┘
                                      │ cria o AgentContext e inicia o ciclo
                                      │
                                      ▼
  ┌───────────────────────────────────────────────────────────────────────┐
  │                             state  (State)                            │
  ├───────────────────────────────────────────────────────────────────────┤
  │ AgentContext  ▶  AgentState                                           │
  │ PlanningState ─▶ ResearchState ─▶ ExecutingState ─▶ FinalState        │
  └───────────────────────────────────────────────────────────────────────┘
                    │                                     │
                    │ PlanningState                       │ ResearchState
                    ▼                                     ▼
  ┌─────────────────────────────────┐   ┌─────────────────────────────────┐
  │       strategy  (Strategy)      │   │        command  (Command)       │
  ├─────────────────────────────────┤   ├─────────────────────────────────┤
  │ PlanningStrategy                │   │ CommandInvoker, Command         │
  │ ReActPlanner                    │   │ SearchKnowledgeCommand          │
  │ PlanThenExecutePlanner          │   │ ListDocumentsCommand            │
  └─────────────────────────────────┘   └─────────────────────────────────┘
                                                          │
                                                          │ receiver
                                                          ▼
                                        ┌─────────────────────────────────┐
                                        │      knowledge  (Receivers)     │
                                        ├─────────────────────────────────┤
                                        │ KnowledgeBase                   │
                                        │ VectorKnowledgeBase             │
                                        └─────────────────────────────────┘

  ┌───────────────────────────────────────────────────────────────────────┐
  │                          observer  (Observer)                         │
  ├───────────────────────────────────────────────────────────────────────┤
  │ AgentEventPublisher, AgentObserver, AgentEvent                        │
  │ ConsoleLogObserver  |  PainelChat (lambda)                            │
  └───────────────────────────────────────────────────────────────────────┘
  Publicam eventos: AgentContext, CommandInvoker e AgentService.
  Observam: ConsoleLogObserver (console) e PainelChat (status na tela).
```

[Ver código PlantUML](ia/uml/general.puml)

</details>

<details>
<summary><b>State</b> — ciclo de vida do agente</summary>
<br>

```
                ┌────────────────────────────────────────────┐
                │                 «Context»                  │
                │                AgentContext                │
                ├────────────────────────────────────────────┤
                │ - state: AgentState                        │
                │ + run()                                    │
                │ + setState(state)                          │
                └────────────────────────────────────────────┘
                                       │
                                       │ state atual
                                       │
                                       ▼
                ┌────────────────────────────────────────────┐
                │            «interface» «State»             │
                │                 AgentState                 │
                ├────────────────────────────────────────────┤
                │ + handle(context)                          │
                │ + name(): String                           │
                │ + isFinal(): boolean                       │
                └────────────────────────────────────────────┘
                                       ▲ implementa
         ┌───────────────────┬─────────┴─────────┬───────────────────┐
         │                   │                   │                   │
┌────────────────┐  ┌────────────────┐  ┌────────────────┐  ┌────────────────┐
│«ConcreteState» │  │«ConcreteState» │  │«ConcreteState» │  │«ConcreteState» │
│ PlanningState  │─>│ ResearchState  │─>│ ExecutingState │─>│   FinalState   │
├────────────────┤  ├────────────────┤  ├────────────────┤  ├────────────────┤
│ + handle(ctx)  │  │ + handle(ctx)  │  │ + handle(ctx)  │  │ + handle(ctx)  │
└────────────────┘  └────────────────┘  └────────────────┘  └────────────────┘
─▶  next: cada estado escolhe o próximo com context.setState(next)
AgentContext.run() repete state.handle(this) até isFinal() ser verdadeiro
```

[Ver código PlantUML](ia/uml/state.puml)

</details>

<details>
<summary><b>Strategy</b> — estratégias de planejamento</summary>
<br>

```
                      ┌────────────────────────────────┐
                      │     PainelChat (JComboBox)     │
                      └────────────────────────────────┘
                                       │
                                       │ usePlanner(nome)
                                       │
                                       ▼
               ┌──────────────────────────────────────────────┐
               │                   «Client»                   │
               │                 AgentService                 │
               ├──────────────────────────────────────────────┤
               │ - planners: Map<String, PlanningStrategy>    │
               │ + usePlanner(name)                           │
               │ + currentPlanner()                           │
               └──────────────────────────────────────────────┘
                                       │
                                       │ troca a strategy
                                       │
                                       ▼
               ┌──────────────────────────────────────────────┐
               │                  «Context»                   │
               │                PlanningState                 │
               ├──────────────────────────────────────────────┤
               │ - strategy: PlanningStrategy                 │
               │ + setStrategy(strategy)                      │
               │ + handle(context)                            │
               └──────────────────────────────────────────────┘
                                       │
                                       │ strategy
                                       │
                                       ▼
               ┌──────────────────────────────────────────────┐
               │            «interface» «Strategy»            │
               │               PlanningStrategy               │
               ├──────────────────────────────────────────────┤
               │ + name(): String                             │
               │ + createPlan(userMessage): String            │
               └──────────────────────────────────────────────┘
                                       ▲ implementa
                     ┌─────────────────┴──────────────────┐
                     │                                    │
     ┌───────────────────────────────┐    ┌───────────────────────────────┐
     │       «ConcreteStrategy»      │    │       «ConcreteStrategy»      │
     │          ReActPlanner         │    │     PlanThenExecutePlanner    │
     ├───────────────────────────────┤    ├───────────────────────────────┤
     │ + createPlan(userMessage)     │    │ + createPlan(userMessage)     │
     └───────────────────────────────┘    └───────────────────────────────┘

Estratégia inicial: ReActPlanner (@Primary).
A troca acontece em tempo de execução, pelo JComboBox do PainelChat.
```

[Ver código PlantUML](ia/uml/strategy.puml)

</details>

<details>
<summary><b>Command</b> — execução das buscas na base de conhecimento</summary>
<br>

```
                     ┌──────────────────────────────────┐
                     │             «Client»             │
                     │          ResearchState           │
                     ├──────────────────────────────────┤
                     │ + handle(context)                │
                     └──────────────────────────────────┘
                                       │
                                       │ run(command)
                                       │
                                       ▼
                     ┌──────────────────────────────────┐
                     │            «Invoker»             │
                     │          CommandInvoker          │
                     ├──────────────────────────────────┤
                     │ + run(command): CommandResult    │
                     └──────────────────────────────────┘
                                       │
                                       │ execute()
                                       │
                                       ▼
                     ┌──────────────────────────────────┐
                     │      «interface» «Command»       │
                     │             Command              │
                     ├──────────────────────────────────┤
                     │ + name(): String                 │
                     │ + execute(): CommandResult       │
                     └──────────────────────────────────┘
                                       ▲ implementa
                     ┌─────────────────┴──────────────────┐
                     │                                    │
     ┌───────────────────────────────┐    ┌───────────────────────────────┐
     │       «ConcreteCommand»       │    │       «ConcreteCommand»       │
     │     SearchKnowledgeCommand    │    │      ListDocumentsCommand     │
     ├───────────────────────────────┤    ├───────────────────────────────┤
     │ - termo: String               │    │ + execute(): CommandResult    │
     │ + execute(): CommandResult    │    └───────────────────────────────┘
     └───────────────────────────────┘
                     │                                    │
                     │ receiver                           │ receiver
                     │                                    │
                     ▼                                    ▼
     ┌───────────────────────────────┐    ┌───────────────────────────────┐
     │           «Receiver»          │    │           «Receiver»          │
     │      VectorKnowledgeBase      │    │         KnowledgeBase         │
     ├───────────────────────────────┤    ├───────────────────────────────┤
     │ + buscar(consulta)            │    │ + listarDocumentos()          │
     └───────────────────────────────┘    │ + catalogo()                  │
                                          └───────────────────────────────┘

ResearchState cria os dois comandos e os entrega ao CommandInvoker.
execute() devolve um CommandResult (success, output).
Atalho: para perguntas sobre a própria base, chama KnowledgeBase.catalogo() direto.
```

[Ver código PlantUML](ia/uml/command.puml)

</details>

<details>
<summary><b>Observer</b> — monitoramento da execução</summary>
<br>

```
┌──────────────────────┐   ┌──────────────────────┐   ┌──────────────────────┐
│   «emite eventos»    │   │   «emite eventos»    │   │   «emite eventos»    │
│     AgentContext     │   │    CommandInvoker    │   │     AgentService     │
├──────────────────────┤   ├──────────────────────┤   ├──────────────────────┤
│ evento: STATE_CHANGED│   │ evento: COMMAND_*    │   │ evento: ERROR        │
└──────────────────────┘   └──────────────────────┘   └──────────────────────┘
            │                          │                          │
            └──────────────────────────┼──────────────────────────┘
                                       │ publish(event)
                                       ▼
                ┌────────────────────────────────────────────┐
                │                «Publisher»                 │
                │            AgentEventPublisher             │
                ├────────────────────────────────────────────┤
                │ - observers: List<AgentObserver>           │
                │ + attach(observer)                         │
                │ + detach(observer)                         │
                │ + publish(event)                           │
                └────────────────────────────────────────────┘
                                       │
                                       │ notifica
                                       │
                                       ▼
                ┌────────────────────────────────────────────┐
                │          «interface» «Subscriber»          │
                │               AgentObserver                │
                ├────────────────────────────────────────────┤
                │ + onEvent(event)                           │
                └────────────────────────────────────────────┘
                                       ▲ implementa
                   ┌───────────────────┴───────────────────┐
                   │                                       │
 ┌──────────────────────────────────┐    ┌──────────────────────────────────┐
 │       «ConcreteSubscriber»       │    │       «ConcreteSubscriber»       │
 │        ConsoleLogObserver        │    │            PainelChat            │
 ├──────────────────────────────────┤    ├──────────────────────────────────┤
 │ + onEvent(event)                 │    │ (lambda) atualiza o status       │
 │ registra: attach(this)           │    │ registra: service.addObserver()  │
 └──────────────────────────────────┘    └──────────────────────────────────┘

        ┌────────────────────────────────────────────────────────────┐
        │                          «record»                          │
        │                         AgentEvent                         │
        ├────────────────────────────────────────────────────────────┤
        │ type: Type  |  message: String                             │
        │ Type: STATE_CHANGED, COMMAND_STARTED, COMMAND_FINISHED,    │
        │       COMMAND_FAILED, ERROR                                │
        └────────────────────────────────────────────────────────────┘
AgentEvent é o dado que o Publisher entrega a cada Subscriber.
```

[Ver código PlantUML](ia/uml/observer.puml)

</details>

<details>
<summary><b>Composite</b> — interface gráfica (Java Swing)</summary>
<br>

```
                  ┌────────────────────────────────────────┐
                  │        «interface» «Component»         │
                  │             ComponenteChat             │
                  ├────────────────────────────────────────┤
                  │ + getView(): JComponent                │
                  │ + getTexto(): String                   │
                  │ + adicionar(filho)                     │
                  │ + remover(filho)                       │
                  └────────────────────────────────────────┘
                                       ▲ implementa
             ┌─────────────────────────┼─────────────────────────┐
             │                         │                         │
 ┌───────────────────────┐ ┌───────────────────────┐ ┌───────────────────────┐
 │         «Leaf»        │ │         «Leaf»        │ │      «Composite»      │
 │     BolhaMensagem     │ │   IndicadorDigitando  │ │    PainelMensagens    │
 ├───────────────────────┤ ├───────────────────────┤ ├───────────────────────┤
 │ - texto: String       │ │ + iniciar()           │ │ - filhos:             │
 │ - doUsuario: boolean  │ │ + parar(): long       │ │   List<ComponenteChat>│
 │ + getView()           │ │ + getView()           │ │ + adicionar(filho)    │
 │ + getTexto()          │ │ + getTexto()          │ │ + remover(filho)      │
 │                       │ │                       │ │ + getTexto()          │
 └───────────────────────┘ └───────────────────────┘ └───────────────────────┘
                                                                 ▲
                                                                 │ mensagens (composição)
                                                                 │
                                                 ┌──────────────────────────────┐
                                                 │           «Client»           │
                                                 │          PainelChat          │
                                                 ├──────────────────────────────┤
                                                 │ - mensagens: PainelMensagens │
                                                 └──────────────────────────────┘

PainelMensagens ──▶ ComponenteChat (0..*): agrega filhos e os trata igual.
getTexto() percorre os filhos e junta o texto (botão "Copiar conversa").
As três classes também estendem JPanel (Swing).
```

[Ver código PlantUML](ia/uml/composite.puml)

</details>

<br>
## ![](https://img.shields.io/badge/-%20-a855f7?style=for-the-badge) Stack Tecnológica

[![My Skills](https://skillicons.dev/icons?i=java,spring,maven,gradle)](https://skillicons.dev)

- **Linguagem:** Java 21+
- **Framework de IA:** [Spring AI](https://spring.io/projects/spring-ai)
- **Modelo de chat:** [Jan.ai](https://jan.ai/) via API local OpenAI-compatible
- **Modelo de embeddings:** [Ollama](https://ollama.com/) (`nomic-embed-text`)
- **Interface gráfica:** Java AWT / Swing
- **Banco de Dados Vetorial:** ChromaDB
- **Build:** Maven Wrapper

<br>

## ![](https://img.shields.io/badge/-%20-a855f7?style=for-the-badge) Como Executar

### Requisitos

- Java JDK 21
- Docker com suporte ao Docker Compose
- Ollama instalado para gerar embeddings
- Jan instalado com um modelo de chat baixado e o Local API Server iniciado em `http://127.0.0.1:1337`

O projeto usa Jan para o chat e Ollama (`nomic-embed-text`) para os embeddings. São serviços separados. O identificador do modelo do Jan precisa corresponder a `spring.ai.openai.chat.model` em `ia/src/main/resources/application.properties` (valor atual: `gemma-4-E4B-it-qat-UD-Q4_K_XL`). Confira o ID servido pelo Jan em `http://127.0.0.1:1337/v1/models` e ajuste a propriedade se necessário.

### Windows (PowerShell)

1. Instale o [Java JDK 21](https://adoptium.net/temurin/releases/?version=21), o [Docker Desktop](https://docs.docker.com/desktop/setup/install/windows-install/) e o [Ollama para Windows](https://ollama.com/download/windows). Inicie o Docker Desktop e aguarde ele ficar pronto.
2. Em um PowerShell, baixe o modelo de embeddings:

   ```powershell
   ollama pull nomic-embed-text
   ```

3. No Jan, baixe um modelo de chat, selecione-o para o Local API Server e inicie o servidor. Por padrão, ele usa `http://127.0.0.1:1337`.
4. No PowerShell, a partir da pasta do repositório:

   ```powershell
   cd .\ia
   docker compose up --detach chroma
   .\mvnw.cmd spring-boot:run
   ```

### Linux/macOS

```bash
git clone <url-do-repositorio>
cd <nome-do-repositorio>/ia
ollama pull nomic-embed-text
docker compose up --detach chroma
bash mvnw spring-boot:run
```

Na inicialização, os arquivos `.md` e `.txt` de `ia/knowledge` são divididos em trechos e indexados na coleção `knowledge-assistant`. O ChromaDB persiste os dados no volume `chroma_data`. Os endereços dos serviços podem ser ajustados por `CHROMA_HOST`, `CHROMA_PORT` e `OLLAMA_BASE_URL`.

<br>

# Banco de dados vetorial

Para visualizar o banco de dados vetorial, acesse "https://github.com/BlackyDrum/chromadb-ui/tree/main" e siga as instruções do repositório. 

### Server URL:
http://localhost:8000
### Tenant: 
SpringAiTenant
### Database:
SpringAiDatabase

## ![](https://img.shields.io/badge/-%20-a855f7?style=for-the-badge) Entregas

| Entrega | Data | Expectativa |
|---------|------|-------------|
| **1ª Entrega** | 14/09/2026 | Código encaminhado (não precisa estar finalizado). MVP rodando com, no mínimo, **Strategy** e **State** funcionais; se possível, **Command** também. |

<br>

## ![](https://img.shields.io/badge/-%20-a855f7?style=for-the-badge) Equipe

| Nome | Função/Contribuição |
|------|----------------------|
| Giovanna Marques | — |
| João Pedro Menegasso | — |
| José Wesley | — |
| Pedro Henrique Quirino | — |

<br>

## ![](https://img.shields.io/badge/-%20-a855f7?style=for-the-badge) Referências

- [Spring AI — Enterprise Agent Use Cases](https://springdevpro.com/spring-ai/source-code/agent/#enterprise-agent-use-cases)
- [Spring AI — Design Patterns Used](https://springdevpro.com/spring-ai/source-code/agent/#design-patterns-used)
- [Documentação oficial do Spring AI](https://docs.spring.io/spring-ai/reference/)
- [Ollama](https://ollama.com/)
- [Jan.ai](https://jan.ai/)

<br>

## ![](https://img.shields.io/badge/-%20-a855f7?style=for-the-badge) Licença

Projeto acadêmico desenvolvido para fins educacionais na disciplina de Padrões de Projetos de Sistemas, na Fatec Professor Jessen Vidal.
