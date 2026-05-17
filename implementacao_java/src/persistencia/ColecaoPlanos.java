package persistencia;

import dominio.Plano;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class ColecaoPlanos {
    private static final String ARQUIVO = "data/planos.csv";
    private List<Plano> planos = new ArrayList<>();

    public ColecaoPlanos() {
        carregar();
    }

    public List<Plano> buscarTodos() {
        return planos;
    }

    public Plano buscarPorId(String planoId) {
        return planos.stream()
                .filter(p -> p.getPlanoId().equals(planoId))
                .findFirst().orElse(null);
    }

    private void carregar() {
        File f = new File(ARQUIVO);
        if (!f.exists()) return;
        try (BufferedReader br = new BufferedReader(new FileReader(f))) {
            String linha;
            while ((linha = br.readLine()) != null) {
                if (!linha.isBlank()) planos.add(Plano.fromCsv(linha));
            }
        } catch (IOException e) {
            System.err.println("Erro ao carregar planos: " + e.getMessage());
        }
    }

    public List<Plano> getTodos() { return planos; }
}
