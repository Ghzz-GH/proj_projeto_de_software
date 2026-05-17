package dominio;

import java.util.Random;

public class Assinante {
    private String nome;
    private String email;
    private String senha;
    private String codigo;
    private boolean verificado;

    public Assinante(String nome, String email, String senha) {
        this.nome = nome;
        this.email = email;
        this.senha = senha;
        this.verificado = false;
    }

    public void gerarCodigo() {
        Random r = new Random();
        this.codigo = String.format("%06d", r.nextInt(999999));
    }

    public void gerarNotificacao(String codigo) {
        System.out.println("\n[NOTIFICACAO] Codigo enviado para " + email + ": " + codigo);
    }

    public boolean validarCodigo(String codigoInformado) {
        if (this.codigo != null && this.codigo.equals(codigoInformado)) {
            this.verificado = true;
            return true;
        }
        return false;
    }

    public String getNome()     { return nome; }
    public String getEmail()    { return email; }
    public String getSenha()    { return senha; }
    public String getCodigo()   { return codigo; }
    public boolean isVerificado() { return verificado; }
    public void setVerificado(boolean v) { this.verificado = v; }
    public void setCodigo(String c) { this.codigo = c; }

    public String toCsv() {
        return nome + "," + email + "," + senha + "," + (verificado ? "1" : "0");
    }

    public static Assinante fromCsv(String linha) {
        String[] p = linha.split(",");
        Assinante a = new Assinante(p[0], p[1], p[2]);
        a.setVerificado(p[3].equals("1"));
        return a;
    }

    @Override
    public String toString() {
        return "Assinante{nome='" + nome + "', email='" + email + "', verificado=" + verificado + "}";
    }
}
