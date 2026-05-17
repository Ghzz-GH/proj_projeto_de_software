package dominio;

public class ItemCesta {
    private Produto produto;
    private int quantidade;
    private double preco;

    public ItemCesta(Produto produto, int quantidade, double preco) {
        this.produto    = produto;
        this.quantidade = quantidade;
        this.preco      = preco;
    }

    public Produto getProduto()   { return produto; }
    public int getQuantidade()    { return quantidade; }
    public double getPreco()      { return preco; }
    public double getPesoTotal()  { return produto.getPeso() * quantidade; }
    public double getPrecoTotal() { return preco * quantidade; }

    @Override
    public String toString() {
        return quantidade + "x " + produto.getNome() + " (R$ " + String.format("%.2f", getPrecoTotal()) + ")";
    }
}
