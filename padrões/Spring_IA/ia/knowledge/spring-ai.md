# Spring AI

O Spring AI é um projeto do ecossistema Spring que facilita a integração de aplicações Java com modelos de inteligência artificial.
O ChatClient é a API fluente do Spring AI para enviar prompts ao modelo e receber a resposta.
O prompt de sistema define o papel e as regras do modelo, e o prompt do usuário traz a solicitação.
Modelos abertos podem ser executados localmente com ferramentas como Ollama e Jan.ai.
O Spring AI acessa esses modelos locais por uma API compatível com a da OpenAI, configurada no application.properties.
Um assistente de base de conhecimento responde às perguntas com base em documentos e não deve inventar informações.
