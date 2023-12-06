import java.util.ArrayList;
import java.util.Scanner;

public class Quarto {
    private int id;
    private String nomeQuarto;
    ArrayList<Integer> qtDeCamasID = new ArrayList<Integer>();
    private boolean temBanheiro;
    private String descricao;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNomeQuarto() {
        return nomeQuarto;
    }

    public void setNomeQuarto(String nomeQuarto) {
        this.nomeQuarto = nomeQuarto;
    }

    public int getQtDeCamasID(int i) {
        return qtDeCamasID.get(i);
    }

    public void setQtDeCamasID(int i) {
        this.qtDeCamasID.add(i);
    }

    public int numeroDeCamas() {
        return qtDeCamasID.size();
    }

    public boolean isTemBanheiro() {
        return temBanheiro;
    }

    public void setTemBanheiro(boolean temBanheiro) {
        this.temBanheiro = temBanheiro;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public void processarQuarto(ArrayList<Quarto> quartos, ArrayList<Cama> camas) {
        Scanner sc = new Scanner(System.in);
        Quarto quarto = new Quarto();
        int opcao;
        do {
            exibirMenu();
            opcao = sc.nextInt();
            switch (opcao) {
                case 1:
                    cadastrarQuarto(quartos, camas, sc, quarto);
                    break;
                case 2:
                    alterarQuarto(quartos, camas, sc, quarto);
                    break;
                case 3:
                    excluirQuarto(quartos, sc);
                    break;
                case 4:
                    listarQuartos(quartos, camas);
                    break;
            }
        } while (opcao != 5);

    }
    private void exibirMenu() {
        System.out.println("""
                Oque deseja fazer?
                1 - Cadastrar Quarto
                2 - Alterar Quarto
                3 - Excluir Quarto
                4 - Listar Quartos
                5 -  Sair""");
    }
    private void cadastrarQuarto(ArrayList<Quarto> quartos, ArrayList<Cama> camas, Scanner sc, Quarto quarto) {
        int numeroDeCamas;
        System.out.println("Digite o id do quarto\n");
        quarto.setId(sc.nextInt());
        System.out.println("Digite o nome do quarto\n");
        quarto.setNomeQuarto(sc.next());
        System.out.println("O quarto tem banheiro?\n1 - Sim\n2 - Nao\n");
        if (sc.nextInt() == 1) {
            quarto.setTemBanheiro(true);
        } else {
            quarto.setTemBanheiro(false);
        }
        int maximoDeCamas = camas.size();
        System.out.println("Escolha o numero camas(o maximo eh"+maximoDeCamas+"\n");
        numeroDeCamas = sc.nextInt();
        while (numeroDeCamas > maximoDeCamas || numeroDeCamas < 0){
            System.out.println("Numero de camas invalido\n");
            System.out.println("Escolha o numero camas(o maximo eh "+maximoDeCamas+"\n");
            numeroDeCamas = sc.nextInt();
        }
        for (int i = 0; i < numeroDeCamas; i++) {
            for (Cama cama : camas) {
                System.out.println("Codigo das Camas\n");
                System.out.println(cama.getCodigoCama());
                if (cama.EhBeliche()) {
                    System.out.println("Cama eh Beliche\n");
                } else {
                    System.out.println("Cama nao eh Beliche\n");
                }

            }
            System.out.println("Digite o id da cama");
            int idCama = sc.nextInt();
            quarto.setQtDeCamasID(idCama);
            System.out.println("Digite a descricao da cama");
            quarto.setDescricao(sc.next());

        }
        System.out.println("Digite a descricao do quarto\n");
        quarto.setDescricao(sc.next());
        quartos.add(quarto);
    }

    private void alterarQuarto(ArrayList<Quarto> quartos, ArrayList<Cama> camas, Scanner sc, Quarto quarto) {
        System.out.println("Digite o id do quarto que deseja alterar");
        int idQuarto = sc.nextInt();
        for (int i = 0; i < quartos.size(); i++) {
            if (quartos.get(i).getId() == idQuarto) {
                System.out.println("Digite o novo nome do quarto");
                quartos.get(i).setNomeQuarto(sc.next());
                System.out.println("Digite a nova descricao do quarto");
                quartos.get(i).setDescricao(sc.next());
                System.out.println("O quarto tem banheiro?/n1 - Sim/n2 - Nao");
                if (sc.nextInt() == 1) {
                    quartos.get(i).setTemBanheiro(true);
                } else {
                    quartos.get(i).setTemBanheiro(false);
                }
                quartos.get(i).qtDeCamasID.clear();
                for (Cama cama : camas) {
                    System.out.println("Codigo da Camas\n");
                    System.out.println(cama.getCodigoCama());
                    if (cama.EhBeliche()) {
                        System.out.println("Cama eh Beliche\n");
                    } else {
                        System.out.println("Cama nao eh Beliche\n");
                    }

                }
                System.out.println("Digite o id da cama");
                int idCama = sc.nextInt();
                quarto.setQtDeCamasID(idCama);
                System.out.println("Digite a descricao da cama");
                quarto.setDescricao(sc.next());
            }
        }
    }

    private void excluirQuarto(ArrayList<Quarto> quartos, Scanner sc) {
        System.out.println("Digite o id do quarto que deseja excluir");
        int idQuartoExcluir = sc.nextInt();
        int confirmacao;
        for (int i = 0; i < quartos.size(); i++) {
            if (quartos.get(i).getId() == idQuartoExcluir) {
                System.out.println("Tem certeza que deseja excluir o quarto?\n1 - Sim\n2 - Nao");
                confirmacao = sc.nextInt();
                if (confirmacao == 1) {
                    quartos.remove(i);
                } else {
                    System.out.println("Quarto nao excluido");
                }
            }
        }
    }

    private void listarQuartos(ArrayList<Quarto> quartos, ArrayList<Cama> camas) {
        for (Quarto quarto1 : quartos) {
            System.out.println("Id do quarto: "+quarto1.getId());
            System.out.println("Nome do quarto: "+quarto1.getNomeQuarto());
            System.out.println("Descricao do quarto: "+quarto1.getDescricao());
            if (quarto1.isTemBanheiro()) {
                System.out.println("Tem banheiro: Sim");
            } else {
                System.out.println("Tem banheiro: Nao");
            }
            System.out.println("Camas: ");
            for (int i = 0; i < quarto1.numeroDeCamas(); i++) {
                System.out.println("Codigo da cama: "+camas.get(quarto1.getQtDeCamasID(i)).getCodigoCama());
                if (camas.get(quarto1.getQtDeCamasID(i)).EhBeliche()) {
                    System.out.println("Cama eh Beliche\n");
                } else {
                    System.out.println("Cama nao eh Beliche\n");
                }
                System.out.println("Descricao da cama: "+quarto1.getDescricao());
            }
        }
    }
}