import java.text.ParseException;
import java.util.ArrayList;
import java.util.Scanner;


public class Processamento {
    ArrayList<Quarto> quartos = new ArrayList<Quarto>();
    Quarto quartoAux = new Quarto();
    Cama camaAux = new Cama();
    Cliente clienteAux = new Cliente();
    Reserva reservaAux = new Reserva();
    ArrayList<Cama> camas = new ArrayList<Cama>();
    ArrayList<Cliente> clientes = new ArrayList<Cliente>();
    ArrayList<Reserva> reservas = new ArrayList<Reserva>();
    Scanner sc = new Scanner(System.in);
    public void processar() throws ParseException {
        int opcao;

        do {
            System.out.println("""
                    Oque deseja fazer?
                    1 - Manter Quarto
                    2 - Manter Cama
                    3 - Manter Cliente
                    4 - Manter Reserva
                    5 -  Sair""");
            opcao = sc.nextInt();
            switch (opcao){
                case 1: quartoAux.processarQuarto(quartos, camas);
                    break;
                case 2: camaAux.processarCama(camas);
                    break;
                case 3: clienteAux.processarCliente(clientes);
                    break;
                case 4: reservaAux.processarReserva(reservas, quartos, clientes, camas);
                    break;
            }

        } while (opcao != 5);
    }

}
