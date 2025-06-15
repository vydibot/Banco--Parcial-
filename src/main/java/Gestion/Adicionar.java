package Gestion;

/**
 *
 * @author JORGE MALAVER
 */
import Cracion.Nodo;
import java.util.Random;
import javax.swing.JOptionPane;
public class Adicionar {
    public Nodo cajero, cab;
    private Random rnd = new Random();
    private static final int MAX_TRANSACCIONES_POR_TURNO = 5;
    
    public void inicializarCajero() {
        cajero = new Nodo();
        cajero.clientId = 0;  // ID 0 representa al cajero
        cajero.transacciones = 0;
        cajero.sig = cajero;
        cab = cajero;
    }
    
    public void agregarCliente() {
        Nodo nuevoCliente = new Nodo();
        nuevoCliente.clientId = 1000 + rnd.nextInt(9000); // IDs de 1000 a 9999
        nuevoCliente.transacciones = 1 + rnd.nextInt(10); // 1-10 transacciones
        
        // Insertar al final de la cola
        nuevoCliente.sig = cajero;
        cab.sig = nuevoCliente;
        cab = nuevoCliente;
    }
    
    public void procesarCliente() {
        if (cajero.sig == cajero) {
            JOptionPane.showMessageDialog(null, "No hay clientes en la cola");
            return;
        }
        
        Nodo cliente = cajero.sig;
        int transaccionesAProcesar = Math.min(cliente.transacciones, MAX_TRANSACCIONES_POR_TURNO);
        cliente.transacciones -= transaccionesAProcesar;
        
        String mensaje = "Cliente " + cliente.clientId + "\n" +
                        "Transacciones procesadas: " + transaccionesAProcesar + "\n" +
                        "Transacciones pendientes: " + cliente.transacciones;
        
        if (cliente.transacciones > 0) {
            // Mover al final de la cola
            cajero.sig = cliente.sig;
            cab.sig = cliente;
            cliente.sig = cajero;
            cab = cliente;
        } else {
            // Eliminar cliente
            cajero.sig = cliente.sig;
            if (cliente == cab) {
                cab = cajero;
            }
        }
        
        JOptionPane.showMessageDialog(null, mensaje);
    }
    
    public void mostrarCola() {
        if (cajero.sig == cajero) {
            JOptionPane.showMessageDialog(null, "Cola vacía");
            return;
        }
        
        StringBuilder cola = new StringBuilder("Cola de clientes:\n");
        Nodo actual = cajero.sig;
        
        while (actual != cajero) {
            cola.append("Cliente ID: ").append(actual.clientId)
                .append(" - Transacciones pendientes: ").append(actual.transacciones)
                .append("\n");
            actual = actual.sig;
        }
        
        JOptionPane.showMessageDialog(null, cola.toString());
    }
    
}
