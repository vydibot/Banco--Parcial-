package GUI;

import Gestion.Adicionar;
import javax.swing.*;

import Cracion.Nodo;

import java.awt.*;
import java.awt.event.*;

public class BancoGUI extends JFrame {
    private Adicionar banco;
    private JTextArea areaClientes;
    private JButton btnAgregarCliente;
    private JButton btnProcesarCliente;
    private JPanel panelEstado;
    
    public BancoGUI() {
        banco = new Adicionar();
        banco.inicializarCajero();
        
        setTitle("Simulación Banco");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout(10, 10));
        
        // Panel superior con botones
        JPanel panelBotones = new JPanel();
        btnAgregarCliente = new JButton("Agregar Cliente");
        btnProcesarCliente = new JButton("Procesar Cliente");
        panelBotones.add(btnAgregarCliente);
        panelBotones.add(btnProcesarCliente);
        
        // Área de texto para mostrar clientes
        areaClientes = new JTextArea();
        areaClientes.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(areaClientes);
        
        // Panel de estado actual
        panelEstado = new JPanel();
        panelEstado.setBorder(BorderFactory.createTitledBorder("Estado Actual"));
        
        // Agregar componentes
        add(panelBotones, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);
        add(panelEstado, BorderLayout.SOUTH);
        
        // Eventos
        btnAgregarCliente.addActionListener(e -> agregarCliente());
        btnProcesarCliente.addActionListener(e -> procesarCliente());
        
        actualizarInterfaz();
    }
    
    private void agregarCliente() {
        banco.agregarCliente();
        actualizarInterfaz();
    }
    
    private void procesarCliente() {
        banco.procesarCliente();
        actualizarInterfaz();
    }
    
    private void actualizarInterfaz() {
        // Actualizar área de clientes
        StringBuilder cola = new StringBuilder("COLA DE CLIENTES:\n\n");
        Nodo actual = banco.cajero.sig;
        
        while (actual != banco.cajero) {
            cola.append("Cliente ID: ").append(actual.clientId)
                .append(" - Transacciones pendientes: ").append(actual.transacciones)
                .append("\n");
            actual = actual.sig;
        }
        
        areaClientes.setText(cola.toString());
    }
}
