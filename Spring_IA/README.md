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

> Os diagramas UML detalhados (classes, sequência e/ou estados) estão disponíveis na pasta [`/docs/uml`](./docs/uml), evidenciando onde cada padrão se encontra na arquitetura do código Java.

<br>

## ![](https://img.shields.io/badge/-%20-a855f7?style=for-the-badge) Stack Tecnológica

[![My Skills](https://skillicons.dev/icons?i=java,spring,maven,gradle)](https://skillicons.dev)

- **Linguagem:** Java 17+
- **Framework de IA:** [Spring AI](https://spring.io/projects/spring-ai)
- **Modelo de IA:** [Ollama](https://ollama.com/) (CPU), modelo aberto básico
- **Interface do modelo (auxiliar):** [Jan.ai](https://jan.ai/) (Jan4)
- **Interface gráfica:** Java AWT / Swing
- **Banco de Dados Vetorial:** *em avaliação* (ex: Chroma, Weaviate, PGVector — a definir conforme pesquisa do grupo)
- **Build:** Maven/Gradle *(definir conforme o setup do projeto)*

<br>

## ![](https://img.shields.io/badge/-%20-a855f7?style=for-the-badge) Como Executar

```bash
# 1. Clone o repositório
git clone <url-do-repositorio>
cd <nome-do-repositorio>

# 2. Suba o modelo local via Ollama
ollama pull <nome-do-modelo>
ollama serve

# 3. Compile e execute a aplicação
./mvnw spring-boot:run
# ou
./gradlew bootRun
```

> Instruções detalhadas de configuração do Ollama, do banco vetorial e da GUI serão atualizadas conforme o avanço do desenvolvimento.

<br>

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
