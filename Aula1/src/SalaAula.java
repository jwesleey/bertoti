import java.util.List;
import java.util.LinkedList;

public class SalaAula {

	private List<Aluno> alunos = new LinkedList<Aluno>();

		public void cadastrarAluno(Aluno aluno) {
			alunos.add(aluno);;
		}

	public List<Aluno> buscarAlunoPorNome(String nome) {
		List<Aluno> alunosEncontrados = new LinkedList<Aluno>();
		for (Aluno aluno:alunos)
		{
			if(((Aluno)aluno).getNome().equals(nome))alunosEncontrados.add(aluno);
			
		}
		return alunosEncontrados;
		
	}

	public List<Aluno> getAlunos() {
		return alunos;
	}

}
