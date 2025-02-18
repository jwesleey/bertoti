import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

class Test {

	@org.junit.jupiter.api.Test
	void test() {

		SalaAula fatec = new SalaAula();
		
		Aluno joao = new Aluno("João", "12345");
		Aluno jose = new Aluno("José", "54321");
		
		fatec.cadastrarAluno(joao);
		fatec.cadastrarAluno(jose);
		
		assertEquals(fatec.getAlunos().size(), 2);
		
		
		List<Aluno> joses = fatec.buscarAlunoPorNome("José");
		assertEquals(joses.get(0).getRa(), jose.getRa());
		
		System.out.println(jose.getNome() + jose.getRa());
		
	}

}
