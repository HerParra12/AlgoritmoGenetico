package co.edu.unbosque.controller;

import co.edu.unbosque.model.Modelo;
import co.edu.unbosque.view.Ventana;

public class Controller {

	private Ventana view;
	private Modelo model;
	
	public Controller() {
		view = new Ventana();
		model = new Modelo();
		funcionar();
	}
	
	private void funcionar() {
		try {
			int menu = 0;
			do {
				menu = view.leerDato("" + 
					  "\n Selecciona la opción a realizar." +
					  "\n 1. " +
					  "\n 0. Salir.");
				
				switch(menu) {
				case 1:
					
					break;
					
				case 0:
					view.mostrarInformacion("Hasta luego.");
					break;
					default:
						view.warningMessage("Selecciona una opción valida.");
						break;
				}
			}while(menu != 0);
		} catch(Exception error) {
			view.errorMessage("Hubo un error.");
			funcionar();
		}
	}
}
