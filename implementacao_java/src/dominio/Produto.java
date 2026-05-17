package dominio;

public class Produto {
    private String produtoId;
    private String nome;
    private double peso;
    private double preco;
    private boolean disponivel;

    public Produto(String produtoId, String nome, double peso, double preco, boolean disponivel) {
        this.produtoId  = produtoId;
        this.nome       = nome;
        this.peso       = peso;
        this.preco      = preco;
        this.disponivel = disponivel;
    }

    public String buscarDetalhes() {
        return "[" + produtoId + "] " + nome + " - " + peso + "kg - R$ " + preco;
    }

    public String getProdutoId()  { return produtoId; }
    public String getNome()       { return nome; }
    public double getPeso()       { return peso; }
    public double getPreco()      { return preco; }
    public boolean isDisponivel() { return disponivel; }

    public String toCsv() {
        return produtoId + "," + nome + "," + peso + "," + preco + "," + (disponivel ? "1" : "0");
    }

    public static Produto fromCsv(String linha) {
        String[] p = linha.split(",");
        return new Produto(p[0], p[1], Double.parseDouble(p[2]), Double.parseDouble(p[3]), p[4].equals("1"));
    }

    @Override
    public String toString() {
        return buscarDetalhes();
    }
}
