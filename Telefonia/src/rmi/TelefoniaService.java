package rmi;

import java.rmi.Remote;
import java.rmi.RemoteException;
import model.Sessao;

public interface TelefoniaService extends Remote {
    /**
     * Consulta os dados de sessão a partir do número de celular.
     * @param numeroCelular O número do celular a ser consultado.
     * @return O objeto Sessao correspondente, ou null se não encontrado.
     * @throws RemoteException Se ocorrer um erro de comunicação RMI.
     */
    Sessao consultarSessao(String numeroCelular) throws RemoteException;
}