# Padrões de projeto usados no agente

O padrão State permite que o agente mude de comportamento conforme o estado atual do seu ciclo de execução.
No agente, os estados são Planejando, Pesquisando, Respondendo e Concluído, e cada estado decide qual é o próximo.
O padrão Strategy define uma família de algoritmos intercambiáveis; no agente, ele é usado para escolher a forma de planejamento.
As estratégias de planejamento disponíveis são ReAct e Plan-then-Execute.
O padrão Command transforma uma ferramenta do agente em um objeto com o método execute.
Os comandos do agente são buscar trechos na base de conhecimento e listar os documentos disponíveis.
O padrão Observer permite que outros objetos acompanhem o que o agente está fazendo sem que o agente os conheça.
O agente notifica os observers quando muda de estado e quando um comando começa, termina ou falha.
O padrão Composite organiza a interface gráfica em árvore, tratando folhas e compostos da mesma forma.
Na interface de chat, as mensagens são folhas e o painel de mensagens é o composto.
