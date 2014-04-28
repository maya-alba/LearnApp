package com.android.learnapp;

public class Usuario {
	
	private String usuario;
	private char genero;
	private String grado;
	private int puntos;
	
	public Usuario(String usuario, char genero, String grado, int puntos) {
		this.usuario = usuario;
		this.genero = genero;
		this.grado = grado;
		this.puntos = puntos;
	}

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public char getGenero() {
		return genero;
	}

	public void setGenero(char genero) {
		this.genero = genero;
	}

	public String getGrado() {
		return grado;
	}

	public void setGrado(String grado) {
		this.grado = grado;
	}
	
	public int getPuntos() {
		return puntos;
	}

	public void setPuntos(int puntos) {
		this.puntos = puntos;
	}
	
}
