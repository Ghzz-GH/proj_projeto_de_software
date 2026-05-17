package fronteira;

import controller.ControleAssinaturas;
import dominio.Plano;
import dominio.Produto;
import java.util.List;
import java.util.Scanner;

public class GuiaAssinatura {

    private final ControleAssinaturas controle = new ControleAssinaturas();
    private final Scanner scanner = new Scanner(System.in);

    public void iniciar() {
        System.out.println("========================================");
        System.out.println("   BEM-VINDO AO SISTEMA DE ASSINATURAS ");
        System.out.println("========================================");
        fluxoCriarAssinante();
    }

    private void fluxoCriarAssinante() {
        System.out.println("\n--- CADASTRO ---");
        System.out.print("Nome: ");
        String nome = scanner.nextLine();
        System.out.print("E-mail: ");
        String email = scanner.nextLine();
        System.out.print("Senha: ");
        String senha = scanner.nextLine();

        boolean criado = controle.criarAssinante(nome, email, senha);
        if (!criado) {
            System.out.println("Tente novamente com outro e-mail.");
            fluxoCriarAssinante();
            return;
        }

        fluxoVerificarCodigo();
    }

    private void fluxoVerificarCodigo() {
        System.out.println("\n--- VERIFICACAO ---");
        boolean verificado = false;
        while (!verificado) {
            System.out.print("Informe o codigo recebido: ");
            String codigo = scanner.nextLine();
            verificado = controle.verificarCodigo(codigo);
        }
        fluxoSelecionarPlano();
    }

    private void fluxoSelecionarPlano() {
        System.out.println("\n--- PLANOS DISPONIVEIS ---");
        List<Plano> planos = controle.listarPlanos();
        if (planos.isEmpty()) {
            System.out.println("[ERRO] Nenhum plano disponivel.");
            return;
        }
        for (Plano p : planos) {
            System.out.println("  " + p);
        }

        boolean selecionado = false;
        while (!selecionado) {
            System.out.print("Digite o ID do plano desejado: ");
            String id = scanner.nextLine();
            selecionado = controle.selecionarPlano(id);
        }
        fluxoMontarCesta();
    }

    private void fluxoMontarCesta() {
        System.out.println("\n--- MONTAGEM DA CESTA ---");
        System.out.print("Semana de referencia (ex: 2025-W20): ");
        String semana = scanner.nextLine();
        controle.abrirCesta(semana);

        List<Produto> produtos = controle.listarProdutos();
        boolean continuar = true;

        while (continuar) {
            System.out.println("\nProdutos disponíveis:");
            for (Produto p : produtos) {
                if (p.isDisponivel()) System.out.println("  " + p);
            }
            System.out.print("\nID do produto (ou CONFIRMAR para finalizar): ");
            String entrada = scanner.nextLine().trim();

            if (entrada.equalsIgnoreCase("CONFIRMAR")) {
                continuar = false;
            } else {
                System.out.print("Quantidade: ");
                int qtd = Integer.parseInt(scanner.nextLine().trim());
                controle.adicionarItem(entrada, qtd);
            }
        }

        boolean confirmado = controle.confirmarCesta();
        if (!confirmado) {
            fluxoMontarCesta();
        } else {
            System.out.println("\nObrigado! Ate a proxima.");
        }
    }
}
