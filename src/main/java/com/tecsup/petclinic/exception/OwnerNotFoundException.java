package com.tecsup.petclinic.exception;

public class OwnerNotFoundException extends Exception {

	private static final long serialVersionUID = 1L;

	public OwnerNotFoundException(String mensaje) {
		super(mensaje);
	}
	
}