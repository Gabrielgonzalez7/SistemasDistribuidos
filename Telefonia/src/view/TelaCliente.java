package view;

import controller.ClienteController;
import model.Sessao;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TelaCliente extends JFrame {

    private ClienteController controller;
    private JTextField txtNumero;
    private JTextArea txtResultado;
    private JButton btnConsultar;

    public TelaCliente() {
        controller = new ClienteController();
        initComponents();
    }

    private void initComponents() {
        setTitle("Consulta de Telefonia - MVC RMI");
        setSize(550, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout(10, 10));

        // Painel Superior
        JPanel panelTop = new JPanel(new FlowLayout(FlowLayout.LEFT));
        panelTop.add(new JLabel("Número do Celular:"));
        
        txtNumero = new JTextField(15);
        panelTop.add(txtNumero);

        btnConsultar = new JButton("Consultar");
        panelTop.add(btnConsultar);

        add(panelTop, BorderLayout.NORTH);

        // Painel Central
        txtResultado = new JTextArea();
        txtResultado.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(txtResultado);
        add(scrollPane, BorderLayout.CENTER);

        // Ação do Botão
        btnConsultar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                btnConsultarActionPerformed(evt);
            }
        });
    }

    private void btnConsultarActionPerformed(ActionEvent evt) {
        String numero = txtNumero.getText().trim();

        if (numero.isEmpty()) {
            txtResultado.setText("Por favor, digite um número de celular.");
            return;
        }

        txtResultado.setText("Buscando no servidor...\n");

        Sessao sessaoEncontrada = controller.buscarDadosSessao(numero);

        if (sessaoEncontrada != null) {
            txtResultado.setText("SESSÃO ENCONTRADA:\n\n");
            txtResultado.append("Número: " + sessaoEncontrada.getNumeroCelular() + "\n");
            txtResultado.append("Dados: " + sessaoEncontrada.getDadosSessao() + "\n");
        } else {
            txtResultado.setText("Nenhuma sessão encontrada para o número: " + numero);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new TelaCliente().setVisible(true);
        });
    }
}