package dominio;

import java.util.ArrayList;
import java.util.List;

public class Plano {
    private String planoId;
    private String nome;
    private boolean ativo;
    private int limiteItens;
    private double limitePeso;
    private double preco;

    public Plano(String planoId, String nome, int limiteItens, double limitePeso, double preco, boolean ativo) {
        this.planoId   = planoId;
        this.nome      = nome;
        this.limiteItens = limiteItens;
        this.limitePeso  = limitePeso;
        this.preco     = preco;
        this.ativo     = ativo;
    }

    public List<String> buscarItensPermitidos() {
        List<String> descricao = new ArrayList<>();
        descricao.add("Ate " + limiteItens + " itens por cesta");
        descricao.add("Ate " + limitePeso + " kg por cesta");
        return descricao;
    }

    public String getPlanoId()    { return planoId; }
    public String getNome()       { return nome; }
    public boolean isAtivo()      { return ativo; }
    public int getLimiteItens()   { return limiteItens; }
    public double getLimitePeso() { return limitePeso; }
    public double getPreco()      { return preco; }

    public String toCsv() {
        return planoId + "," + nome + "," + limiteItens + "," + limitePeso + "," + preco + "," + (ativo ? "1" : "0");
    }

    public static Plano fromCsv(String linha) {
        String[] p = linha.split(",");
        return new Plano(p[0], p[1], Integer.parseInt(p[2]), Double.parseDouble(p[3]),
                         Double.parseDouble(p[4]), p[5].equals("1"));
    }

    @Override
    public String toString() {
        return "[" + planoId + "] " + nome + " - ate " + limiteItens + " itens / " + limitePeso + "kg - R$ " + preco;
    }
}
