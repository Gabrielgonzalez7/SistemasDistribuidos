/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

/**
 *
 * @author gabri
 */
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import model.Sessao;
import rmi.TelefoniaService;

public class ClienteController {
    
    private TelefoniaService servico;
    
    public ClienteController(){
        conectarServidor();
    }
    
    private void conectarServidor(){
        try{
            Registry registry = LocateRegistry.getRegistry("localhost", 1099);
            servico = (TelefoniaService) registry.lookup("TelefoniaService");
            System.out.println("Conectando ao servidor RMI com sucesso");
        } catch (Exception e){
            System.out.println("Erro ao concetar no servidor: " + e.getMessage());
        }
    }
    
    public Sessao buscarDadosSessao(String numeroCelular){
        try{
            if(servico != null){
                return servico.consultarSessao(numeroCelular);
            }
        } catch(Exception e){
            System.out.println("Erro ao consultar RMI: " + e.getMessage());
        }
        return null;
    }
    
}
