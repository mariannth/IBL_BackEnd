package com.infinitybyteleague.app.exception;

public class AppNotFoundException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	// Definir metodo constructor que evalúa un parámetro
		public AppNotFoundException(int idUsuario) {
			super("Id with: " + idUsuario + "not found");
		}
		
}
