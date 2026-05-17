package persistencia;

import dominio.Assinante;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class ColecaoAssinantes {
    private static final String ARQUIVO = "data/assinantes.csv";
    private List<Assinante> assinantes = new ArrayList<>();

    public ColecaoAssinantes() {
        carregar();
    }

    public Assinante buscar(String email) {
        return assinantes.stream()
                .filter(a -> a.getEmail().equalsIgnoreCase(email))
                .findFirst().orElse(null);
    }

    public void adicionar(Assinante assinante) {
        assinantes.add(assinante);
        salvar();
    }

    public void salvar() {
        try (PrintWriter pw = new PrintWriter(new FileWriter(ARQUIVO))) {
            for (Assinante a : assinantes) {
                pw.println(a.toCsv());
            }
        } catch (IOException e) {
            System.err.println("Erro ao salvar assinantes: " + e.getMessage());
        }
    }

    private void carregar() {
        File f = new File(ARQUIVO);
        if (!f.exists()) return;
        try (BufferedReader br = new BufferedReader(new FileReader(f))) {
            String linha;
            while ((linha = br.readLine()) != null) {
                if (!linha.isBlank()) assinantes.add(Assinante.fromCsv(linha));
            }
        } catch (IOException e) {
            System.err.println("Erro ao carregar assinantes: " + e.getMessage());
        }
    }

    public List<Assinante> getTodos() { return assinantes; }
}
