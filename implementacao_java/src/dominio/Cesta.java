package dominio;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Cesta {
    private String cestaId;
    private String semana;
    private Assinatura assinatura;
    private List<ItemCesta> itens;

    public Cesta(Assinatura assinatura, String semana) {
        this.cestaId    = UUID.randomUUID().toString().substring(0, 8);
        this.assinatura = assinatura;
        this.semana     = semana;
        this.itens      = new ArrayList<>();
    }

    public void adicionarItem(ItemCesta item) {
        itens.add(item);
    }

    public int getTotalItens() {
        return itens.stream().mapToInt(ItemCesta::getQuantidade).sum();
    }

    public double getTotalPeso() {
        return itens.stream().mapToDouble(ItemCesta::getPesoTotal).sum();
    }

    public double getTotalPreco() {
        return itens.stream().mapToDouble(ItemCesta::getPrecoTotal).sum();
    }

    public boolean isEmpty() { return itens.isEmpty(); }

    public String getCestaId()        { return cestaId; }
    public String getSemana()         { return semana; }
    public Assinatura getAssinatura() { return assinatura; }
    public List<ItemCesta> getItens() { return itens; }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Cesta [").append(cestaId).append("] - Semana: ").append(semana).append("\n");
        for (ItemCesta item : itens) {
            sb.append("  ").append(item).append("\n");
        }
        sb.append("  Total: R$ ").append(String.format("%.2f", getTotalPreco()));
        sb.append(" | Peso: ").append(String.format("%.2f", getTotalPeso())).append("kg");
        sb.append(" | Itens: ").append(getTotalItens());
        return sb.toString();
    }
}
