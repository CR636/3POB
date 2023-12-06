import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Cliente {
    private int id;
    private String nome;
    private String endereco;
    private String postalCode;
    private String pais;
    private String cpf;
    private String passaporte;
    private String email;
    private Date dataNascimento;
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public String getPais() {
        return pais;
    }

    public void setPais(String pais) {
        this.pais = pais;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getPassaporte() {
        return passaporte;
    }

    public void setPassaporte(String passaporte) {
        this.passaporte = passaporte;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(Date dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public boolean validarCPF(String cpf) {
        if (cpf == null || cpf.length() != 11 || !cpf.chars().allMatch(Character::isDigit)) {
            return false;
        }

        int[] numeros = new int[11];
        for (int i = 0; i < 11; i++) {
            numeros[i] = Character.getNumericValue(cpf.charAt(i));
        }
         for (int i = 0; i < 9; i++) {
             if (numeros[i] != numeros[i + 1]) {
                 break;
             }
             if (i == 8) {
                 return false;
             }
         }

         int soma = 0;
         for (int i = 0; i < 9; i++) {
             soma += (10 - i) * numeros[i];
         }

         int primeiroDigitoVerificador = 11 - (soma % 11);
         if (primeiroDigitoVerificador == 10 || primeiroDigitoVerificador == 11) {
             primeiroDigitoVerificador = 0;
         }

         if (primeiroDigitoVerificador != numeros[9]) {
             return false;
         }

         soma = 0;
         for (int i = 0; i < 10; i++) {
             soma += (11 - i) * numeros[i];
         }

         int segundoDigitoVerificador = 11 - (soma % 11);
         if (segundoDigitoVerificador == 10 || segundoDigitoVerificador == 11) {
             segundoDigitoVerificador = 0;
         }

         return segundoDigitoVerificador == numeros[10];
    }

    public boolean validarEmail(String email) {
        String emailRegex = "[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,6}";
        Pattern pattern = Pattern.compile(emailRegex);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }
    public boolean validarData(String data) {
        String dataRegex = "\\d{2}-\\d{2}-\\d{4}";
        Pattern pattern = Pattern.compile(dataRegex);
        Matcher matcher = pattern.matcher(data);

        if (!matcher.matches()) {
            return false;
        }

        try {
            SimpleDateFormat formato = new SimpleDateFormat("dd-MM-yyyy");
            Date dataInserida = formato.parse(data);
            Date dataAtual = new Date();

            if (dataInserida.after(dataAtual)) {
                return false;
            }
        } catch (Exception e) {
            return false;
        }

        return true;
    }
    public Date incluirData(String dataNascimentoStr) throws ParseException {
        SimpleDateFormat formato = new SimpleDateFormat("dd-MM-yyyy");
        return formato.parse(dataNascimentoStr);
    }
    public void processarCliente(ArrayList<Cliente> clientes) throws ParseException {
        Cliente cliente = new Cliente();
        Scanner sc = new Scanner(System.in);
        int opcao;
        do {
            exibirMenu();
            opcao = sc.nextInt();
            switch (opcao){
                case 1: cliente.cadastrarCliente(clientes);
                    break;
                case 2: cliente.listarCliente(clientes);
                    break;
                case 3: cliente.editarCliente(clientes);
                    break;
                case 4: cliente.excluirCliente(clientes);
                    break;
            }
        } while (opcao != 5);
    }
    public void exibirMenu(){
        System.out.println("""
                Oque deseja fazer?
                1 - Cadastrar Cliente
                2 - Listar Clientes
                3 - Editar Cliente
                4 - Excluir Cliente
                5 - Sair""");
    }
    public boolean validarId(int id, ArrayList<Cliente> clientes){
        for (Cliente cliente : clientes) {
            if (cliente.getId() == id) {
                return true;
            }
        }
        return false;
    }
    public void cadastrarCliente(ArrayList<Cliente> clientes) throws ParseException {
        Cliente cliente = new Cliente();
        boolean cpfValido = false;
        boolean emailValido = false;
        boolean dataValida = false;
        int id = 0;
        Scanner sc = new Scanner(System.in);
        System.out.println("Digite o nome do cliente: ");
        cliente.setNome(sc.nextLine());
        System.out.println("Digite o endereço do cliente: ");
        cliente.setEndereco(sc.nextLine());
        System.out.println("Digite o postal code do cliente: ");
        cliente.setPostalCode(sc.nextLine());
        System.out.println("Digite o pais do cliente: ");
        cliente.setPais(sc.nextLine());
        while (!cpfValido) {
            System.out.println("Digite o cpf do cliente: ");
            String cpf = sc.nextLine();
            if (cliente.validarCPF(cpf)) {
                cliente.setCpf(cpf);
                cpfValido = true;
            } else {
                System.out.println("CPF invalido!! Tente novamente");
            }
        }
        System.out.println("Digite o passaporte do cliente: ");
        cliente.setPassaporte(sc.nextLine());
        while (!emailValido) {
            System.out.println("Digite o email do cliente: ");
            String email = sc.nextLine();
            if (cliente.validarEmail(email)) {
                cliente.setEmail(email);
                emailValido = true;
            } else {
                System.out.println("Email invalido!! Tente novamente");
            }
        }
        while (!dataValida) {
            System.out.println("Digite a data de nascimento do cliente :(Formato DD-MM-AAAA) ");
            String dataNascimentoStr = sc.nextLine();
            if (cliente.validarData(dataNascimentoStr)) {
                cliente.setDataNascimento(incluirData(dataNascimentoStr));
                dataValida = true;
            } else {
                System.out.println("Data invalida!! Tente novamente,Verifique se esta no formato DD-MM-AAAA utilzando o - , " +
                        "e se a data eh anterior a data atual");
            }
        }
        System.out.println("Digite o id do cliente: ");
        id = sc.nextInt();
        while (validarId(id, clientes)) {
            System.out.println("Id ja cadastrado!! Tente novamente");
            System.out.println("Digite o id do cliente: ");
            id = sc.nextInt();
        }
        cliente.setId(id);
        clientes.add(cliente);
    }

    public void listarCliente(ArrayList<Cliente> clientes){
        for (Cliente cliente : clientes) {
            System.out.println("Id: " + cliente.getId());
            System.out.println("Nome: " + cliente.getNome());
            System.out.println("Endereço: " + cliente.getEndereco());
            System.out.println("Postal Code: " + cliente.getPostalCode());
            System.out.println("Pais: " + cliente.getPais());
            System.out.println("CPF: " + cliente.getCpf());
            System.out.println("Passaporte: " + cliente.getPassaporte());
            System.out.println("Email: " + cliente.getEmail());
            System.out.println("Data de Nascimento: " + cliente.getDataNascimento());
        }
    }
    public void editarCliente(ArrayList<Cliente> clientes) throws ParseException {
        Scanner sc = new Scanner(System.in);
        String dataNascimentoStr;
        boolean cpfValido = false;
        boolean emailValido = false;
        boolean dataValida = false;
        System.out.println("Digite o id do cliente que deseja editar: ");
        int id = sc.nextInt();
        for (Cliente cliente : clientes) {
            if (cliente.getId() == id) {
                System.out.println("Digite o nome do cliente: ");
                cliente.setNome(sc.nextLine());
                System.out.println("Digite o endereço do cliente: ");
                cliente.setEndereco(sc.nextLine());
                System.out.println("Digite o postal code do cliente: ");
                cliente.setPostalCode(sc.nextLine());
                System.out.println("Digite o pais do cliente: ");
                cliente.setPais(sc.nextLine());
                while (!cpfValido) {
                    System.out.println("Digite o cpf do cliente: ");
                    String cpf = sc.nextLine();
                    if (cliente.validarCPF(cpf)) {
                        cliente.setCpf(cpf);
                        cpfValido = true;
                    } else {
                        System.out.println("CPF invalido!! Tente novamente");
                    }
                }
                System.out.println("Digite o passaporte do cliente: ");
                cliente.setPassaporte(sc.nextLine());
                while (!emailValido) {
                    System.out.println("Digite o email do cliente: ");
                    String email = sc.nextLine();
                    if (cliente.validarEmail(email)) {
                        cliente.setEmail(email);
                        emailValido = true;
                    } else {
                        System.out.println("Email invalido!! Tente novamente");
                    }
                }
                while (!dataValida) {
                    System.out.println("Digite a data de nascimento do cliente :(Formato DD-MM-AAAA) ");
                    dataNascimentoStr = sc.nextLine();
                    if (cliente.validarData(dataNascimentoStr)) {
                        cliente.setDataNascimento(incluirData(dataNascimentoStr));
                        dataValida = true;
                    } else {
                        System.out.println("Data invalida!! Tente novamente,Verifique se esta no formato DD-MM-AAAA utilzando o - , " +
                                "e se a data eh anterior a data atual");
                    }
                }
            }
        }
    }
    public void excluirCliente(ArrayList<Cliente> clientes){
        Scanner sc = new Scanner(System.in);
        int confirmacao;
        System.out.println("Digite o id do cliente que deseja excluir: ");
        int id = sc.nextInt();
        for (Cliente cliente : clientes) {
            if (cliente.getId() == id) {
                System.out.println("Tem certeza que deseja excluir o cliente?\n1 - Sim\n2 - Nao");
                    confirmacao = sc.nextInt();
                    if (confirmacao == 1) {
                        clientes.remove(cliente);
                    } else {
                        System.out.println("Cliente nao excluido");
                    }
                }
            }
        }
}

