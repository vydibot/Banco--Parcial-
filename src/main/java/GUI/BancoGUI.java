package GUI;

import Gestion.Adicionar;
import javax.swing.*;

import Cracion.Nodo;

import java.awt.*;
import java.util.Random;

public class BancoGUI extends JFrame {
    private Adicionar banco;
    private JTextArea areaClientes;
    private JButton btnAgregarCliente;
    private JButton btnProcesarCliente;
    private JPanel panelEstado;
    private Timer clienteTimer;
    private Random random = new Random();
    private JLabel estadoLabel;
    
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
        
        // Modificar panel de estado
        panelEstado = new JPanel();
        panelEstado.setBorder(BorderFactory.createTitledBorder("Estado Actual"));
        estadoLabel = new JLabel("Esperando clientes...");
        panelEstado.add(estadoLabel);
        
        // Agregar componentes
        add(panelBotones, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);
        add(panelEstado, BorderLayout.SOUTH);
        
        // Eventos
        btnAgregarCliente.addActionListener(e -> agregarCliente());
        btnProcesarCliente.addActionListener(e -> procesarCliente());
        
        // Configurar timer para llegada automática de clientes
        clienteTimer = new Timer(10000, e -> generarClientesAutomaticos());
        clienteTimer.start();
        
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
    
    private void generarClientesAutomaticos() {
        int cantidadClientes = 1 + random.nextInt(5); // Genera entre 1 y 5 clientes
        for (int i = 0; i < cantidadClientes; i++) {
            banco.agregarCliente();
        }
        estadoLabel.setText("Llegaron " + cantidadClientes + " nuevos clientes");
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
