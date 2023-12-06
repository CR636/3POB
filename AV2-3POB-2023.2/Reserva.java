import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Reserva {
    private int id;
    private int idCliente;
    private int idQuarto;
    private int idCama;
    private Date dataEntrada;
    private Date dataSaida;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
    }

    public int getIdQuarto() {
        return idQuarto;
    }

    public void setIdQuarto(int idQuarto) {
        this.idQuarto = idQuarto;
    }

    public int getIdCama() {
        return idCama;
    }

    public void setIdCama(int idCama) {
        this.idCama = idCama;
    }

    public Date getDataEntrada() {
        return dataEntrada;
    }

    public void setDataEntrada(Date dataEntrada) {
        this.dataEntrada = dataEntrada;
    }

    public Date getDataSaida() {
        return dataSaida;
    }

    public void setDataSaida(Date dataSaida) {
        this.dataSaida = dataSaida;
    }
    public void processarReserva(ArrayList<Reserva> reservas, ArrayList<Quarto> quartos, ArrayList<Cliente> clientes, ArrayList<Cama> camas){
        Reserva reserva = new Reserva();
        Scanner sc = new Scanner(System.in);
        int opcao;
        do {
            exibirMenu();
            opcao = sc.nextInt();
            switch (opcao){
                case 1: reserva.cadastrarReserva(reservas, quartos, clientes, camas);
                    break;
                case 2: reserva.listarReserva(reservas);
                    break;
                case 3: reserva.editarReserva(reservas, quartos, clientes, camas);
                    break;
                case 4: reserva.excluirReserva(reservas);
                    break;
            }
        } while (opcao != 5);
    }
    public void exibirMenu(){
        System.out.println("""
                Oque deseja fazer?
                1 - Cadastrar Reserva
                2 - Listar Reservas
                3 - Editar Reserva
                4 - Excluir Reserva
                5 - Sair""");
    }
    public boolean verificarID(ArrayList<Reserva> reservas, int id){
        for (Reserva reserva : reservas) {
            if (reserva.getId() == id){
                return true;
            }
        }
        return false;
    }
    public boolean verificarIDCliente(ArrayList<Cliente> clientes, int id){
        for (Cliente cliente : clientes) {
            if (cliente.getId() == id){
                return true;
            }
        }
        return false;
    }
    public boolean verificarIDQuarto(ArrayList<Quarto> quartos, int id){
        for (Quarto quarto : quartos) {
            if (quarto.getId() == id){
                return true;
            }
        }
        return false;
    }
    public boolean verificarIDCama(ArrayList<Cama> camas, int id){
        for (Cama cama : camas) {
            if (cama.getId() == id){
                return true;
            }
        }
        return false;
    }
    public Date incluirData(String dataReserva) throws ParseException {
        SimpleDateFormat formato = new SimpleDateFormat("dd-MM-yyyy");
        return formato.parse(dataReserva);
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

            Calendar cal = Calendar.getInstance();
            cal.setTime(dataAtual);
            cal.set(Calendar.HOUR_OF_DAY, 0);
            cal.set(Calendar.MINUTE, 0);
            cal.set(Calendar.SECOND, 0);
            cal.set(Calendar.MILLISECOND, 0);
            dataAtual = cal.getTime();


            if (dataInserida.before(dataAtual)) {
                return false;
            }
        } catch (Exception e) {
            return false;
        }

        return true;
    }
    public void cadastrarReserva(ArrayList<Reserva> reservas, ArrayList<Quarto> quartos, ArrayList<Cliente> clientes, ArrayList<Cama> camas){
        Reserva reserva = new Reserva();
        Scanner sc = new Scanner(System.in);
        boolean idValido = false;
        boolean idValidoCliente = false;
        boolean idValidoQuarto = false;
        boolean idValidoCama = false;
        boolean dataValida = false;
        boolean dataValida2 = false;
        while (!idValido) {
            System.out.println("Digite o id da reserva");
            int id = sc.nextInt();
            idValido = verificarID(reservas, id);
            if (idValido) {
                System.out.println("Id ja cadastrado. Digite novamente");
            } else {
                reserva.setId(id);
            }
        }
        while (!idValidoCliente) {
            System.out.println("Digite o id do cliente");
            int id = sc.nextInt();
            idValidoCliente = verificarIDCliente(clientes, id);
            if (idValidoCliente) {
                reserva.setIdCliente(id);
            } else {
                System.out.println("Id nao cadastrado. Digite novamente");
            }
        }
        while (!idValidoQuarto) {
            System.out.println("Digite o id do quarto");
            int id = sc.nextInt();
            idValidoQuarto = verificarIDQuarto(quartos, id);
            if (idValidoQuarto) {
                reserva.setIdQuarto(id);
            } else {
                System.out.println("Id nao cadastrado. Digite novamente");
            }
        }
        while (!idValidoCama) {
            System.out.println("Digite o id da cama");
            int id = sc.nextInt();
            idValidoCama = verificarIDCama(camas, id);
            if (idValidoCama) {
                reserva.setIdCama(id);
            } else {
                System.out.println("Id nao cadastrado. Digite novamente");
            }
        }
        while (!dataValida) {
            System.out.println("Digite a data de entrada");
            String dataReserva = sc.nextLine();
            dataValida = validarData(dataReserva);
            if (dataValida) {
                try {
                    reserva.setDataEntrada(incluirData(dataReserva));
                } catch (ParseException e) {
                    e.printStackTrace();
                }
            } else {
                System.out.println("Data invalida. Digite novamente, verifique se a data de saida eh maior que a data de hoje" +
                        " e esta no formato correto (DD-MM-AAA) com o - .");
            }
        }
        while (!dataValida2) {
            System.out.println("Digite a data de saida");
            String dataReserva = sc.nextLine();
            dataValida2 = validarData(dataReserva);
            if (dataValida2) {
                try {
                    reserva.setDataSaida(incluirData(dataReserva));
                } catch (ParseException e) {
                    e.printStackTrace();
                }
            } else {
                System.out.println("Data invalida. Digite novamente, verifique se a data de saida eh maior que a data de hoje" +
                        " e esta no formato correto (DD-MM-AAA) com o - .");
            }
        }
        reservas.add(reserva);
    }
    public void listarReserva(ArrayList<Reserva> reservas){
        for (Reserva reserva : reservas) {
            System.out.println("Id da reserva: " + reserva.getId());
            System.out.println("Id do cliente: " + reserva.getIdCliente());
            System.out.println("Id do quarto: " + reserva.getIdQuarto());
            System.out.println("Id da cama: " + reserva.getIdCama());
            System.out.println("Data de entrada: " + reserva.getDataEntrada());
            System.out.println("Data de saida: " + reserva.getDataSaida());
        }
    }
    public void editarReserva(ArrayList<Reserva> reservas, ArrayList<Quarto> quartos, ArrayList<Cliente> clientes, ArrayList<Cama> camas){
        Scanner sc = new Scanner(System.in);
        boolean idinValido = false;
        boolean idValidoCliente = false;
        boolean idValidoQuarto = false;
        boolean idValidoCama = false;
        boolean dataValida = false;
        boolean dataValida2 = false;
       while (idinValido){
              System.out.println("Digite o id da reserva que deseja editar");
                int id = sc.nextInt();
                if (verificarID(reservas, id)){
                    idinValido = true;
                } else {
                    System.out.println("Id nao cadastrado. Digite novamente");
                }
       }
        for (Reserva reserva : reservas) {
            if (reserva.getId() == id){
                while (!idValidoCliente) {
                    System.out.println("Digite o id do cliente");
                    int idCliente = sc.nextInt();
                    if (verificarIDCliente(clientes, idCliente)){
                        reserva.setIdCliente(idCliente);
                        idValidoCliente = true;
                    } else {
                        System.out.println("Id nao cadastrado. Digite novamente");
                    }
                }
                while (!idValidoQuarto) {
                    System.out.println("Digite o id do quarto");
                    int idQuarto = sc.nextInt();
                    if (verificarIDQuarto(quartos, idQuarto)){
                        reserva.setIdQuarto(idQuarto);
                        idValidoQuarto = true;
                    } else {
                        System.out.println("Id nao cadastrado. Digite novamente");
                    }
                }
                while (!idValidoCama) {
                    System.out.println("Digite o id da cama");
                    int idCama = sc.nextInt();
                    if (verificarIDCama(camas, idCama)){
                        reserva.setIdCama(idCama);
                        idValidoCama = true;
                    } else {
                        System.out.println("Id nao cadastrado. Digite novamente");
                    }
                }
                while (!dataValida) {
                    System.out.println("Digite a data de entrada");
                    String dataReserva = sc.nextLine();
                    dataValida = validarData(dataReserva);
                    if (dataValida) {
                        try {
                            reserva.setDataEntrada(incluirData(dataReserva));
                        } catch (ParseException e) {
                            e.printStackTrace();
                        }
                    } else {
                        System.out.println("Data invalida. Digite novamente, verifique se a data de saida eh maior que a data de hoje" +
                                " e esta no formato correto (DD-MM-AAA) com o - .");
                    }
                }
                while (!dataValida2) {
                    System.out.println("Digite a data de saida");
                    String dataReserva = sc.nextLine();
                    dataValida2 = validarData(dataReserva);
                    if (dataValida2) {
                        try {
                            reserva.setDataSaida(incluirData(dataReserva));
                        } catch (ParseException e) {
                            e.printStackTrace();
                        }
                    } else {
                        System.out.println("Data invalida. Digite novamente, verifique se a data de saida eh maior que a data de hoje" +
                                " e esta no formato correto (DD-MM-AAA) com o - .");
                    }
                }
            }
        }
    }
    public void excluirReserva(ArrayList<Reserva> reservas){
        Scanner sc = new Scanner(System.in);
        int confirmacao;
        System.out.println("Digite o id da reserva que deseja excluir");
        int id = sc.nextInt();
        for (Reserva reserva : reservas) {
            if (reserva.getId() == id){
                System.out.println("Tem certeza que deseja excluir a reserva? 1 - Sim 2 - Nao");
                confirmacao = sc.nextInt();
                if (confirmacao == 1){
                    reservas.remove(reserva);
                } else {
                    System.out.println("Reserva nao excluida");
                }
            }
        }
    }
}
