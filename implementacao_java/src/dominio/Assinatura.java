package dominio;

import java.util.UUID;

public class Assinatura {
    private String assinaturaId;
    private Assinante assinante;
    private Plano plano;

    public Assinatura(Assinante assinante) {
        this.assinaturaId = UUID.randomUUID().toString().substring(0, 8);
        this.assinante    = assinante;
    }

    public void vincularPlano(Plano plano) {
        this.plano = plano;
    }

    public Plano buscarPlano() {
        return this.plano;
    }

    public boolean verificarLimitePlano(int qtdAtual, double pesoAtual) {
        if (plano == null) return false;
        return qtdAtual <= plano.getLimiteItens() && pesoAtual <= plano.getLimitePeso();
    }

    public String getAssinaturaId() { return assinaturaId; }
    public Assinante getAssinante() { return assinante; }
    public Plano getPlano()         { return plano; }

    @Override
    public String toString() {
        return "Assinatura{id='" + assinaturaId + "', assinante='" + assinante.getNome()
               + "', plano='" + (plano != null ? plano.getNome() : "sem plano") + "'}";
    }
}
