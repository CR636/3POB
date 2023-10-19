import java.util.ArrayList;
public class Carrinho {
    private int id;
    private ArrayList<ItensCarrinho> itensCarrinho;
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public ArrayList<ItensCarrinho> getItensCarrinho() {
        return itensCarrinho;
    }

    public void setItensCarrinho(ArrayList<ItensCarrinho> itensCarrinho) {
        this.itensCarrinho = itensCarrinho;

    }
}
