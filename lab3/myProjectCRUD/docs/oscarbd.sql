-- Entidade para armazenar os titulos dos filmes.
CREATE TABLE movies (
    id_mov INT PRIMARY KEY AUTO_INCREMENT,
    name_mov VARCHAR(50)
);

-- Entidade para armazenar os votos dos usuários.
CREATE TABLE votes (
    id_voto INT PRIMARY KEY AUTO_INCREMENT,
    email_user VARCHAR(100) UNIQUE,
    critic_voto VARCHAR(500),
    id_mov INT,
    FOREIGN KEY (id_mov)
        REFERENCES movies (id_mov)
);

-- Inserção dos filmes participantes do votação. 
INSERT INTO movies (name_mov) VALUES
('Ainda Estou Aqui'),
('Emilia Pérez'),
('The Brutalist'),
('Wicked'),
('A Complete Unknown'),
('Conclave'),
('Anora'),
('The Substance'),
('Dune: Part II'),
('Nickel Boys');

-- Criação de Usuário para gestão do banco de dados vinculado ao application properties no SpringBoot. 
CREATE USER 'admin'@'localhost' IDENTIFIED BY '123';
GRANT ALL PRIVILEGES ON oscarbd.* TO 'admin'@'localhost';
FLUSH PRIVILEGES;

-- Inserção de dados ficticios para testar funcionalidades. 
INSERT INTO votes (email_user, critic_voto, id_mov) VALUES
('jessica.martins@gmail.com', 'Simplesmente incrível, "Ainda Estou Aqui" merece todos os prêmios.', 1),
('carlos.souza@yahoo.com', 'Uma história emocionante e muito bem executada.', 1),
('mariana.oliveira@hotmail.com', 'Chorei do começo ao fim, maravilhoso.', 1),
('lucas.pereira@outlook.com', 'Filme sensível e bem produzido, adorei.', 1),
('ana.lima@gmail.com', 'Excelente enredo e atuação impecável.', 1),
('roberto.silva@gmail.com', 'Surpreendente e com uma carga emocional muito forte.', 1),
('priscila.goncalves@yahoo.com', 'Roteiro bem escrito e direção competente.', 1),
('matheus.lima@hotmail.com', 'Valeu cada minuto, incrível.', 1),
('juliana.martins@outlook.com', 'Muito bem dirigido e atuações de alto nível.', 1),
('eduardo.ferreira@gmail.com', 'O melhor filme do ano até agora.', 1),
('larissa.souza@yahoo.com', 'Superou minhas expectativas, emocionante.', 1),
('thiago.almeida@hotmail.com', 'O final foi maravilhoso, recomendo.', 1),
('fernanda.costa@outlook.com', 'Um dos melhores dramas que já vi.', 1),
('rafael.santos@gmail.com', 'Simplesmente perfeito.', 1),
('isabela.mendes@yahoo.com', 'História tocante e atuações brilhantes.', 1),
('aline.ferreira@gmail.com', '"The Brutalist" é visualmente incrível.', 3),
('daniel.silva@yahoo.com', 'Gostei muito da direção de arte.', 3),
('rodrigo.santos@gmail.com', '"Emilia Pérez" mistura gêneros bem, achei criativo.', 2),
('laura.costa@yahoo.com', 'Ótima trilha sonora e atuações.', 2),
('felipe.mendes@outlook.com', 'História interessante mas meio arrastada.', 5),
('sabrina.moura@gmail.com', '"Wicked" foi divertido, adorei as músicas.', 4),
('vinicius.almeida@hotmail.com', 'Bom musical, mas não superou minhas expectativas.', 4),
('eduardo.gomes@outlook.com', 'Achei "Conclave" bem intrigante.', 6),
('patricia.cardoso@gmail.com', 'Suspense bem feito.', 6),
('marcos.tavares@gmail.com', '"Dune: Part II" é visualmente espetacular.', 9),
('juliana.ramos@yahoo.com', 'Fiquei impressionada com os efeitos especiais.', 9),
('thiago.pinto@hotmail.com', '"The Substance" foi uma experiência única.', 8),
('isabela.fernandes@outlook.com', 'Muito original, gostei.', 8),
('gustavo.castro@gmail.com', 'História interessante em "Nickel Boys".', 10),
('nathalia.silveira@gmail.com', 'Bom filme, mas nada demais.', 10),
('bruno.teixeira@yahoo.com', '"Anora" foi surpreendentemente bom.', 7),
('carol.sampaio@hotmail.com', 'Gostei da direção e do ritmo.', 7),
('pedro.barbosa@outlook.com', 'Bem produzido, mas não fez meu estilo.', 5),
('luciana.mendes@gmail.com', 'Atuações muito boas.', 2),
('matheus.oliveira@gmail.com', '"Dune: Part II" me prendeu do começo ao fim.', 9),
('bianca.azevedo@yahoo.com', 'Muito bem feito, fiquei impressionada.', 9),
('anderson.felix@hotmail.com', '"Wicked" é ótimo para quem curte musicais.', 4),
('fernanda.alves@outlook.com', 'Gostei da produção, mas achei longo.', 3),
('ricardo.souza@gmail.com', '"The Brutalist" é profundo e complexo.', 3),
('tatiane.freitas@gmail.com', '"A Complete Unknown" é nostálgico.', 5),
('joao.cunha@gmail.com', 'Me identifiquei muito com a história.', 5),
('patricia.lima@yahoo.com', '"Conclave" foi uma boa surpresa.', 6),
('felipe.rocha@outlook.com', 'Gostei mais do final.', 6),
('camila.torres@gmail.com', '"Nickel Boys" traz uma história importante.', 10),
('rodrigo.lopes@yahoo.com', '"Anora" tem uma pegada diferente, gostei.', 7),
('ana.silva@outlook.com', '"The Substance" me surpreendeu positivamente.', 8),
('lucas.almeida@gmail.com', 'Muito criativo.', 8),
('julio.mendes@yahoo.com', '"Emilia Pérez" é bom, mas não espetacular.', 2),
('tamires.fernandes@hotmail.com', '"Dune: Part II" entrega o que promete.', 9);


