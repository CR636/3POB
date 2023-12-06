import java.util.ArrayList;
import java.util.Scanner;

public class Cama {
    private int id;
    private int codigoCama;
    private boolean ehBeliche;
    private String descricao;
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCodigoCama() {
        return codigoCama;
    }

    public void setCodigoCama(int codigoCama) {
        this.codigoCama = codigoCama;
    }

    public boolean EhBeliche() {
        return ehBeliche;
    }

    public void setEhBeliche(boolean ehBeliche) {
        this.ehBeliche = ehBeliche;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
    public void processarCama(ArrayList<Cama> camas){
        Cama cama = new Cama();
        Scanner sc = new Scanner(System.in);
        int opcao;
        do {
            exibirMenu();
            opcao = sc.nextInt();
            switch (opcao){
                case 1: cama.cadastrarCama(camas);
                    break;
                case 2: cama.listarCama(camas);
                    break;
                case 3: cama.editarCama(camas);
                    break;
                case 4: cama.excluirCama(camas);
                    break;
            }
        } while (opcao != 5);

    }
    public void exibirMenu(){
        System.out.println("""
                Oque deseja fazer?
                1 - Cadastrar Cama
                2 - Listar Cama
                3 - Editar Cama
                4 - Excluir Cama
                5 - Sair""");
    }
    public boolean verificarIdCamas(ArrayList<Cama> camas, int id){
        for (Cama cama: camas) {
            if (cama.getId() == id) {
                return true;
            }
        }
        return false;
    }
    public void cadastrarCama(ArrayList<Cama> camas){
        Scanner sc = new Scanner(System.in);
        Cama cama = new Cama();
        System.out.println("Digite o id da cama\n");
        cama.setId(sc.nextInt());
        System.out.println("Digite o codigo da cama\n");
        cama.setCodigoCama(sc.nextInt());
        System.out.println("Digite se a cama eh beliche\n 1- Sim\n 2- Nao");
        int ehBeliche = sc.nextInt();
        while (ehBeliche != 1 && ehBeliche != 2){
            System.out.println("Opcao invalida\n");
            System.out.println("Digite se a cama eh beliche\n 1 - Sim\n 2- Nao");
            ehBeliche = sc.nextInt();
        }
        if (ehBeliche == 1) {
            cama.setEhBeliche(true);
        } else {
            cama.setEhBeliche(false);
        }
        System.out.println("Digite a descricao da cama\n");
        cama.setDescricao(sc.next());
        camas.add(cama);
    }
    public void listarCama(ArrayList<Cama> camas){
        for (Cama cama: camas) {
            System.out.println("Id: " + cama.getId());
            System.out.println("Codigo da cama: " + cama.getCodigoCama());
            if (cama.EhBeliche()) {
                System.out.println("Eh beliche: Sim");
            } else {
                System.out.println("Eh beliche: Nao");
            }
            System.out.println("Descricao: " + cama.getDescricao());
        }
    }
    public void editarCama(ArrayList<Cama> camas){
        Scanner sc = new Scanner(System.in);
        boolean camaID = false;
        while (!camaID) {
            System.out.println("Digite o id da cama que deseja editar\n");
            int id = sc.nextInt();
            camaID = verificarIdCamas(camas, id);
            if (!camaID) {
                System.out.println("Id nao encontrado\n");
            }
        }
        for (Cama cama: camas) {
            if (cama.getId() == id) {
                System.out.println("Digite o codigo da cama\n");
                cama.setCodigoCama(sc.nextInt());
                System.out.println("Digite se a cama eh beliche\n 1- Sim\n 2- Nao");
                int ehBeliche = sc.nextInt();
                while (ehBeliche != 1 && ehBeliche != 2){
                    System.out.println("Opcao invalida\n");
                    System.out.println("Digite se a cama eh beliche\n 1 - Sim\n 2- Nao");
                    ehBeliche = sc.nextInt();
                }
                if (ehBeliche == 1) {
                    cama.setEhBeliche(true);
                } else {
                    cama.setEhBeliche(false);
                }
                System.out.println("Digite a descricao da cama\n");
                cama.setDescricao(sc.next());
            }
        }
    }
    public void excluirCama(ArrayList<Cama> camas){
        Scanner sc = new Scanner(System.in);
        int confirma;
        boolean camaID = false;
        System.out.println("Digite o id da cama que deseja excluir\n");
        int id = sc.nextInt();
        if (verificarIdCamas(camas, id)){
            camaID = true;
        } else {
            System.out.println("Id nao encontrado\n");
        }
        while (!camaID) {
            System.out.println("Digite o id da cama que deseja excluir\n");
            id = sc.nextInt();
            camaID = verificarIdCamas(camas, id);
            if (!camaID) {
                System.out.println("Id nao encontrado\n");
            }
        }
        for (int i = 0; i < camas.size(); i++) {
            if (camas.get(i).getId() == id) {
                System.out.println("Tem certeza que deseja excluir a cama?\n1 - Sim\n2 - Nao\n");
                confirma = sc.nextInt();
                if (confirma == 1){
                    System.out.println("Cama excluida com sucesso\n");
                    camas.remove(i);
                } else {
                    System.out.println("Cama nao excluida\n");
                }
            }
        }
    }
}
