# Sistema de Votação de Filmes do Oscar

## Introdução
Este projeto é um sistema web para votação em filmes indicados ao Oscar. Ele permite que usuários votem em seus filmes favoritos, fornecendo uma crítica e um email único. O backend é construído com **Spring Boot**, enquanto o frontend é desenvolvido com **Angular**. O sistema implementa operações CRUD (Create, Read, Update, Delete) para gerenciar filmes e votos.

## Tecnologias Utilizadas
### Backend
- **Spring Boot**: Framework para construção da API REST.
- **Spring Data JPA**: Para persistência de dados com Hibernate.
- **MySQL**: Banco de dados em memória para desenvolvimento (pode ser substituído por MySQL/PostgreSQL).
- **Maven**: Gerenciador de dependências.

### Frontend
- **Angular**: Framework para construção da interface web.
- **TypeScript**: Linguagem principal do frontend.

### Outras Ferramentas
- **Git**: Controle de versão.
- **IntelliJ IDEA**: IDE para desenvolvimento.
- **VSCODE**: IDE para desenvolvimento.
- **Postman**: Para testes da API.

## Funcionalidades
- **Votar em um filme**: Usuários podem votar em um filme fornecendo email, crítica e selecionando o filme. Cada email só pode votar uma vez.
- **Visualizar votos**: Exibe os votos registrados.
- **Validação de email**: Garante que emails duplicados não sejam usados para votação.
- **Feedback ao usuário**: Mensagens de sucesso ou erro exibidas.

### Passos para Configuração
1. **Clonar o repositório**:
   ```bash
   git clone https://github.com/jwesleey/bertoti/tree/main/lab3/myProjectCRUD
   ```

2. **Configurar o Backend**:
   - Navegue até a pasta `SpringBoot`:
     ```bash
     cd backend
     ```

   - Configure o banco de dados em `src/main/resources/application.properties`:
     ```properties
     spring.application.name=SpringBoot
     spring.datasource.url=jdbc:mysql://localhost:3306/oscarbd
     spring.datasource.username=admin
     spring.datasource.password=123
     spring.jpa.hibernate.ddl-auto=update
     spring.jpa.show-sql=true
     ```
   - Execute o Script de criação de Banco de Dados no MySQL Workbench:
     > [Script Banco de Dados](./docs/oscarbd.sql)

   - Compile e execute o backend:
     ```bash
     mvn clean install
     ```
     ```bash
     mvn spring-boot:run
     ```

   - A API estará disponível em `http://localhost:8080`.

   <br>
3. **Configurar o Frontend**:


   - Navegue até a pasta `frontend`:
     ```bash
     cd myprojectWeb
     ```
   - Instale as dependências:
     ```bash
     npm install
     ```
   - Execute o frontend:
     ```bash
     ng serve
     ```
   - A aplicação estará disponível em `http://localhost:4200`.


4. **Testar a API**:


   - Use o Postman para testar endpoint:
      - `POST /api/votar`: Enviar um voto.
   - Exemplo de corpo para `POST /api/votar`:
     ```json
     {
       "email_user": "user@example.com",
       "critic_voto": "Ótimo filme!",
       "name_mov": "Ainda Estou Aqui"
     }
     ```

### Endpoints Principais
- **POST /api/votar**: Registra um voto. Retorna 409 (CONFLICT) se o email já foi usado.
- **GET /api/ranking**: Lista o resultado da votação0.
- **GET /api/meuvoto**: Verifica as informações do voto enviado.
- **PUT /api/editar**: Com base no voto enviado é possivel editar a crítica.
- **DELETE /api/delete**: Remove o voto.

## Como Usar
1. Acesse `http://localhost:4200` no navegador.
2. Selecione um filme da lista de indicados.
3. Preencha o formulário com seu email e uma crítica.
4. Envie o voto. Você verá uma mensagem de sucesso ou erro (ex.: "Email já utilizado").