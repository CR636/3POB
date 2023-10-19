import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Scanner;

public class Menu2 {
    public void Processamento() {
        int opcao = 0;
        Scanner sc = new Scanner(System.in);
        ArrayList<Produto> produtos = new ArrayList();
        do{
            System.out.println("Digite a opção:\n 1 " +
                    "para adicionar mais produtos\n 2: para ir para o carrinho " +
                    "\n3: para sair\n");
            exibirProdutos(produtos);
            opcao= sc.nextInt();
            switch (opcao) {
                case 1:
                    adicionarProduto(produtos);
                    break;
                case 2:
                    irParaCarrinho(produtos);
                    break;
                case 3:
                    System.exit(0);
                    break;
                default:
                System.out.println("Opção inválida. Tente novamente!");
            }
        }
        while (opcao != 3) ;

    }
    public void adicionarProduto(ArrayList<Produto> produtos) {
        Produto novoProduto = new Produto();
        Scanner sc = new Scanner(System.in);
        System.out.println("Digite o id do produto: ");
        novoProduto.setId(sc.nextInt());
        System.out.println("Digite o nome do produto: ");
        novoProduto.setNome(sc.next());
        System.out.println("Digite o preço do produto: ");
        novoProduto.setPreco(sc.nextFloat());
        produtos.add(novoProduto);
    }
    public void irParaCarrinho(ArrayList<Produto> produtos){
        Carrinho carrinho = new Carrinho();
        Scanner sc = new Scanner(System.in);
        int opcao = 0;
        ArrayList<ItensCarrinho> itensCarrinho = new ArrayList();
        carrinho=criarCarrinho(itensCarrinho);
        do{
            System.out.println("Digite 1 para adicinar mais produtos ao Carrinho: \n" +
                    "2 Para Exibir Carrinho:\n"+"3 para fechar a compra");
            opcao = sc.nextInt();
            switch (opcao){
                case 1:
                    adicionarNoCarrinho(produtos,carrinho);
                    break;
                case 2:
                    exibirCarrinho(carrinho,produtos);
                    break;
                case 3:
                    fecharCompra(carrinho,produtos);
                    break;
            }
        }while (opcao != 0);
        
    }
    public Carrinho criarCarrinho(ArrayList<ItensCarrinho> itensCarrinho){
        Carrinho carrinho = new Carrinho();
        Scanner sc = new Scanner(System.in);
        System.out.println("Digite o id do carrinho: ");
        carrinho.setId(sc.nextInt());
        carrinho.setItensCarrinho(itensCarrinho);
        return carrinho;
    }
    public void exibirProdutos(ArrayList<Produto> produtos){
        for (Produto produto : produtos) {
            System.out.println("Id: " + produto.getId());
            System.out.println("Nome: " + produto.getNome());
            System.out.println("Preço: " + produto.getPreco());
        }
    }
    public void adicionarNoCarrinho(ArrayList<Produto> produtos,Carrinho carrinho){
        Scanner sc = new Scanner(System.in);
        ItensCarrinho pCR = new ItensCarrinho();
        ArrayList<ItensCarrinho> itensCarrinho = carrinho.getItensCarrinho();
        int id;
        int quant;
        exibirProdutos(produtos);
        System.out.println("Digite o ID do produto para adicionar ao carrinho:");
        id = sc.nextInt();
        System.out.println("Digite a Quantidade do produto");
        quant = sc.nextInt();
        pCR.setIdProduto(id);
        pCR.setQuantidade(quant);
        itensCarrinho.add(pCR);
        carrinho.setItensCarrinho(itensCarrinho);
        System.out.println("Produto adicionado ao Carrinho com sucesso!");
    }
    public void fecharCompra(Carrinho carrinho, ArrayList<Produto> produtos){
        System.out.println("Compra fechada com sucesso!");
        float valorTotal = 0;
        
        ArrayList<ItensCarrinho> compra = carrinho.getItensCarrinho();
        for (ItensCarrinho itensCarrinho : compra) {
            for (Produto produto : produtos) {
                if (itensCarrinho.getIdProduto() == produto.getId())
                    valorTotal += produto.getPreco() * itensCarrinho.getQuantidade();
            }
        }
    System.out.println("Valor total da Compra é:R$"+valorTotal);
    }
    public void exibirCarrinho(Carrinho carrinho, ArrayList<Produto> produtos){
        ArrayList<ItensCarrinho> compra = carrinho.getItensCarrinho();
        for (ItensCarrinho itensCarrinho : compra) {
            for (Produto produto : produtos) {
                if (itensCarrinho.getIdProduto() == produto.getId()) {
                    System.out.println("Nome do Produto:" + produto.getNome());
                    System.out.println("Preço do Total Produto:" + produto.getPreco() * itensCarrinho.getQuantidade());
                }
            }
        }
    }
}

