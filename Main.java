package pob;
import java.util.Scanner;
public class Main {
	 public static void main(String[] args) {
		        String aux;
		        int aux2;
		        Scanner s = new Scanner(System.in);
		        Aluno aluno = new Aluno();
		        System.out.println("Digite o nome do aluno:");
		        aux=s.next();
		        aluno.setNome(aux);
		        System.out.println("Digite a idade do aluno:");
		        aux2=s.nextInt();
		        aluno.setIdade(aux2);
		        System.out.println("Digite a matricula do aluno:");
		        aux2=s.nextInt();
		        aluno.setMatricula(aux2);
		        System.out.println("Digite o perido do aluno:");
		        aux2=s.nextInt();
		        aluno.setPeriodo(aux2);
		        System.out.println("Nome: " + aluno.getNome());
		        System.out.println("Idade: " + aluno.getIdade());
		        System.out.println("Matricula: " + aluno.getMatricula());
		        System.out.println("Periodo: " + aluno.getPeriodo());
		        s.close();
		    }
		}
