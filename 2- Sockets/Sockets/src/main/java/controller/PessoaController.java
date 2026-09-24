package controller;
import model.Pessoa;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

/**
 * Além de aplicar as regras de negócio (gerar e-mail, evitar duplicidade),
 * este controller agora PERSISTE os cadastros em disco, em um arquivo texto
 * simples (CSV), para que os dados sobrevivam a um reinício do servidor.
 *
 * Formato de cada linha do arquivo: nome;dataNascimento;email
 *
 * Não é um banco de dados de verdade — é só um arquivo local, suficiente
 * para o exercício. Se um dia precisar de algo mais robusto (múltiplos
 * servidores, concorrência entre processos diferentes etc.), o próximo
 * passo natural seria trocar isso por um banco (SQLite/H2/PostgreSQL) via
 * JDBC, mantendo a mesma interface pública desta classe.
 */
public class PessoaController {

    // Arquivo criado na pasta em que o processo é executado
    // (normalmente a raiz do projeto, quando rodado pela IDE).
    private static final Path ARQUIVO_DADOS = Paths.get("cadastros.csv");
    private static final String SEPARADOR = ";";

    private final List<Pessoa> listaPessoas = new ArrayList<>();

    public PessoaController() {
        carregarDoArquivo();
    }

    /** Método para evitar duplicatas e persistir novos cadastros. */
    public synchronized Pessoa processarCadastro(Pessoa pessoa) {
        if (listaPessoas.contains(pessoa)) {
            int index = listaPessoas.indexOf(pessoa);
            return listaPessoas.get(index);
        }
        // Gerar o email
        String emailGerado = gerarEmail(pessoa.getNome(), pessoa.getDataNascimento());
        pessoa.setEmail(emailGerado);
        listaPessoas.add(pessoa);
        salvarNoArquivo();
        return pessoa;
    }

    public synchronized List<Pessoa> getListaPessoas() {
        return new ArrayList<>(listaPessoas);
    }

    // Regra de formatação do e-mail: primeiro.ultimo.ano@ufn.edu.br
    public String gerarEmail(String nomeCompleto, String dataNascimentoStr) {
        if (nomeCompleto == null || nomeCompleto.trim().isEmpty()) {
            return "invalido@ufn.edu.br";
        }

        String nomeLimpo = nomeCompleto.trim();
        String[] partesNome = nomeLimpo.split("\\s+");

        String primeiroNome = partesNome[0];
        String ultimoSobrenome = (partesNome.length > 1) ? partesNome[partesNome.length - 1] : "";

        String ano = "0000";
        if (dataNascimentoStr != null && dataNascimentoStr.contains("/")) {
            String[] partesData = dataNascimentoStr.trim().split("/");
            if (partesData.length == 3) {
                ano = partesData[2].trim();
            }
        }

        String email;
        if (ultimoSobrenome.isEmpty()) {
            email = primeiroNome + "." + ano + "@ufn.edu.br";
        } else {
            email = primeiroNome + "." + ultimoSobrenome + "." + ano + "@ufn.edu.br";
        }

        return email.toLowerCase();
    }

    // ---- Persistência em disco ----

    private void carregarDoArquivo() {
        if (!Files.exists(ARQUIVO_DADOS)) {
            return; // primeira execução: ainda não existe arquivo, tudo bem
        }
        try (BufferedReader leitor = Files.newBufferedReader(ARQUIVO_DADOS, StandardCharsets.UTF_8)) {
            String linha;
            while ((linha = leitor.readLine()) != null) {
                if (linha.isBlank()) {
                    continue;
                }
                String[] partes = linha.split(SEPARADOR, -1);
                if (partes.length < 3) {
                    continue; // linha corrompida/inesperada: ignora
                }
                Pessoa pessoa = new Pessoa(partes[0], partes[1]);
                pessoa.setEmail(partes[2]);
                listaPessoas.add(pessoa);
            }
        } catch (IOException e) {
            System.err.println("Não foi possível carregar cadastros salvos: " + e.getMessage());
        }
    }

    private void salvarNoArquivo() {
        try (BufferedWriter escritor = Files.newBufferedWriter(ARQUIVO_DADOS, StandardCharsets.UTF_8)) {
            for (Pessoa p : listaPessoas) {
                escritor.write(p.getNome() + SEPARADOR + p.getDataNascimento() + SEPARADOR + p.getEmail());
                escritor.newLine();
            }
        } catch (IOException e) {
            System.err.println("Não foi possível salvar os cadastros: " + e.getMessage());
        }
    }
}