package server;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import model.Sessao;
import model.TabelaHash;
import rmi.TelefoniaService;

public class ServidorTelefonia extends UnicastRemoteObject implements TelefoniaService {
    
    private static ServidorTelefonia servidor;
    private static Registry registry;
    private TabelaHash tabela;

    protected ServidorTelefonia() throws RemoteException {
        super();
        tabela = new TabelaHash();
        carregarBaseDeDados();
    }

    private void carregarBaseDeDados() {
        String arquivo = "telephony_sessions.txt"; 
        
        try (BufferedReader br = new BufferedReader(new FileReader(arquivo))) {
            String linha;
            while ((linha = br.readLine()) != null) {
                String linhaTratada = linha.trim();
                if (linhaTratada.isEmpty()) continue;

                // Localiza o início do objeto JSON na linha
                int posJson = linhaTratada.indexOf('{');
                
                if (posJson != -1) {
                    // Extrai apenas os dígitos numéricos antes do JSON (o número do celular)
                    String numero = linhaTratada.substring(0, posJson).replaceAll("[^0-9]", "").trim();
                    String dadosJson = linhaTratada.substring(posJson).trim();
                    
                    if (!numero.isEmpty()) {
                        Sessao sessao = new Sessao(numero, dadosJson);
                        tabela.sessoes.put(numero, sessao);
                    }
                } else {
                    // Caso a linha use separadores comuns como ';' ou espaço
                    String[] partes = linhaTratada.split("[;\\s\\t]+", 2);
                    if (partes.length == 2) {
                        String numero = partes[0].replaceAll("[^0-9]", "").trim();
                        String dados = partes[1].trim();
                        if (!numero.isEmpty()) {
                            Sessao sessao = new Sessao(numero, dados);
                            tabela.sessoes.put(numero, sessao);
                        }
                    }
                }
            }
            System.out.println("Base de dados real carregada! " + tabela.sessoes.size() + " sessões registradas.");
        } catch (IOException e) {
            System.err.println("Erro crítico: O arquivo 'telephony_sessions.txt' não foi encontrado na raiz do projeto!");
            e.printStackTrace();
        }
    }

    @Override
    public Sessao consultarSessao(String numeroCelular) throws RemoteException {
        // Trata a entrada do usuário para manter apenas os números
        String numeroLimpo = numeroCelular.replaceAll("[^0-9]", "").trim();
        System.out.println("Servidor: Recebida consulta para o número " + numeroLimpo);
        return tabela.sessoes.get(numeroLimpo);
    }

    public static void main(String[] args) {
        try {
            servidor = new ServidorTelefonia();
            registry = LocateRegistry.createRegistry(1099);
            registry.rebind("TelefoniaService", servidor);
            System.out.println("Servidor de Telefonia está online e aguardando conexões...");

            // Mantém o processo do servidor ativo na porta 1099
            synchronized (ServidorTelefonia.class) {
                ServidorTelefonia.class.wait();
            }
        } catch (Exception e) {
            System.err.println("Erro no servidor: " + e.toString());
            e.printStackTrace();
        }
    }
}