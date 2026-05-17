package controller;

import dominio.*;
import persistencia.*;
import java.util.List;

public class ControleAssinaturas {

    private ColecaoAssinantes colAssinantes = new ColecaoAssinantes();
    private ColecaoPlanos     colPlanos     = new ColecaoPlanos();
    private ColecaoProdutos   colProdutos   = new ColecaoProdutos();

    private Assinante assinanteAtual;
    private Assinatura assinaturaAtual;
    private Cesta cestaAtual;
    private Entrega entregaAtual;

    public boolean criarAssinante(String nome, String email, String senha) {
        if (colAssinantes.buscar(email) != null) {
            System.out.println("[ERRO] E-mail ja cadastrado.");
            return false;
        }
        Assinante novo = new Assinante(nome, email, senha);
        novo.gerarCodigo();
        novo.gerarNotificacao(novo.getCodigo());
        this.assinanteAtual = novo;
        colAssinantes.adicionar(novo);
        return true;
    }

    public boolean verificarCodigo(String codigo) {
        if (assinanteAtual == null) return false;
        if (assinanteAtual.validarCodigo(codigo)) {
            colAssinantes.salvar();
            System.out.println("[OK] Conta verificada com sucesso!");
            return true;
        }
        System.out.println("[ERRO] Codigo invalido. Tente novamente.");
        return false;
    }

    public List<Plano> listarPlanos() {
        return colPlanos.buscarTodos();
    }

    public boolean selecionarPlano(String planoId) {
        Plano plano = colPlanos.buscarPorId(planoId);
        if (plano == null || !plano.isAtivo()) {
            System.out.println("[ERRO] Plano indisponivel.");
            return false;
        }
        assinaturaAtual = new Assinatura(assinanteAtual);
        assinaturaAtual.vincularPlano(plano);
        System.out.println("[OK] Assinatura criada: " + assinaturaAtual);
        return true;
    }

    public void abrirCesta(String semana) {
        cestaAtual  = new Cesta(assinaturaAtual, semana);
        entregaAtual = new Entrega(cestaAtual);
        System.out.println("[OK] Cesta aberta para semana: " + semana);
    }

    public List<Produto> listarProdutos() {
        return colProdutos.buscarTodos();
    }

    public boolean adicionarItem(String produtoId, int qtd) {
        Produto produto = colProdutos.buscarPorId(produtoId);
        if (produto == null || !produto.isDisponivel()) {
            System.out.println("[ERRO] Produto indisponivel.");
            return false;
        }
        int qtdNova   = cestaAtual.getTotalItens() + qtd;
        double pesoNovo = cestaAtual.getTotalPeso() + (produto.getPeso() * qtd);

        if (!assinaturaAtual.verificarLimitePlano(qtdNova, pesoNovo)) {
            System.out.println("[ERRO] Limite do plano atingido (itens ou peso).");
            return false;
        }
        entregaAtual.adicionarItem(produto, qtd);
        System.out.println("[OK] Item adicionado: " + produto.getNome() + " x" + qtd);
        return true;
    }

    public boolean confirmarCesta() {
        if (cestaAtual.isEmpty()) {
            System.out.println("[ERRO] A cesta esta vazia. Adicione ao menos um item.");
            return false;
        }
        System.out.println("\n=== CESTA CONFIRMADA ===");
        System.out.println(cestaAtual);
        System.out.println("Entrega: " + entregaAtual);
        return true;
    }

    public Assinante getAssinanteAtual()     { return assinanteAtual; }
    public Assinatura getAssinaturaAtual()   { return assinaturaAtual; }
    public Cesta getCestaAtual()             { return cestaAtual; }
}
