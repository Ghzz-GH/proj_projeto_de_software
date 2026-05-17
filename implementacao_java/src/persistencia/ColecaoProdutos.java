package persistencia;

import dominio.Produto;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class ColecaoProdutos {
    private static final String ARQUIVO = "data/produtos.csv";
    private List<Produto> produtos = new ArrayList<>();

    public ColecaoProdutos() {
        carregar();
    }

    public List<Produto> buscarTodos() {
        return produtos;
    }

    public Produto buscarPorId(String produtoId) {
        return produtos.stream()
                .filter(p -> p.getProdutoId().equals(produtoId))
                .findFirst().orElse(null);
    }

    private void carregar() {
        File f = new File(ARQUIVO);
        if (!f.exists()) return;
        try (BufferedReader br = new BufferedReader(new FileReader(f))) {
            String linha;
            while ((linha = br.readLine()) != null) {
                if (!linha.isBlank()) produtos.add(Produto.fromCsv(linha));
            }
        } catch (IOException e) {
            System.err.println("Erro ao carregar produtos: " + e.getMessage());
        }
    }
}
