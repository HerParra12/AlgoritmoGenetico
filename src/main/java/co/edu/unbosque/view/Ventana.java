package co.edu.unbosque.view;

import javax.swing.JOptionPane;

public class Ventana {

	public Ventana() {}
	
	public int leerDato(String message) {
		String data = JOptionPane.showInputDialog(null, message);
		return Integer.parseInt(data);
	}
	
	public String leerString(String message) {
		return JOptionPane.showInputDialog(null, message);
	}

	public void mostrarInformacion(String message) {
		JOptionPane.showMessageDialog(null, message, "Información", JOptionPane.INFORMATION_MESSAGE);
	}
	
	public void warningMessage(String message) {
		JOptionPane.showMessageDialog(null, message, "warning", JOptionPane.WARNING_MESSAGE);
	}
	
	public void errorMessage(String message) {
		JOptionPane.showMessageDialog(null, message, "error", JOptionPane.ERROR_MESSAGE);
	}
}
