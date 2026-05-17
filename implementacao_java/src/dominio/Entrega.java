package dominio;

import java.util.UUID;

public class Entrega {
    private String entregaId;
    private String status;
    private Cesta cesta;
    private Assinatura assinatura;

    public Entrega(Cesta cesta) {
        this.entregaId  = UUID.randomUUID().toString().substring(0, 8);
        this.cesta      = cesta;
        this.assinatura = cesta.getAssinatura();
        this.status     = "PENDENTE";
    }

    public void adicionarItem(Produto produto, int qtd) {
        Plano plano = assinatura.buscarPlano();
        System.out.println("  [Entrega] Adicionando item baseado no plano: " + plano.getNome());
        ItemCesta item = new ItemCesta(produto, qtd, produto.getPreco());
        cesta.adicionarItem(item);
    }

    public String getEntregaId()    { return entregaId; }
    public String getStatus()       { return status; }
    public Cesta getCesta()         { return cesta; }
    public Assinatura getAssinatura() { return assinatura; }

    @Override
    public String toString() {
        return "Entrega{id='" + entregaId + "', status='" + status
               + "', plano='" + assinatura.getPlano().getNome() + "'}";
    }
}
