package com.grupo03.lucastream.model;

public class Videojuego {

	private int rank;
	private String nombre;
	private String plataforma;
	private int ano;
	private String genero;
	private String editor;
	private double na_ventas;
	private double eu_ventas;
	private double jp_ventas;
	private double otras_ventas;
	private double global_ventas;

	
	public Videojuego() {
		super();
	}
	
	public Videojuego(String nombre, String plataforma, int ano, String genero, String editor) {
		super();
		this.nombre = nombre;
		this.plataforma = plataforma;
		this.ano = ano;
		this.genero = genero;
		this.editor = editor;
		this.na_ventas = 0;
		this.eu_ventas = 0;
		this.jp_ventas = 0;
		this.otras_ventas = 0;
		this.global_ventas = 0;
	}

	public Videojuego(int rank, String nombre, String plataforma, int ano, String genero, String editor) {
		super();
		this.rank = rank;
		this.nombre = nombre;
		this.plataforma = plataforma;
		this.ano = ano;
		this.genero = genero;
		this.editor = editor;
	
	}


	public Videojuego(int rank, String nombre, String plataforma, int ano, String genero, String editor,
			double na_ventas, double eu_ventas, double jp_ventas, double otras_ventas, double global_ventas) {
		super();
		this.rank = rank;
		this.nombre = nombre;
		this.plataforma = plataforma;
		this.ano = ano;
		this.genero = genero;
		this.editor = editor;
		this.na_ventas = na_ventas;
		this.eu_ventas = eu_ventas;
		this.jp_ventas = jp_ventas;
		this.otras_ventas = otras_ventas;
		this.global_ventas = global_ventas;
	}

	public int getRank() {
		return rank;
	}



	public void setRank(int rank) {
		this.rank = rank;
	}



	public String getNombre() {
		return nombre;
	}



	public void setNombre(String nombre) {
		this.nombre = nombre;
	}



	public String getPlataforma() {
		return plataforma;
	}



	public void setPlataforma(String plataforma) {
		this.plataforma = plataforma;
	}



	public int getAno() {
		return ano;
	}



	public void setAno(int ano) {
		this.ano = ano;
	}



	public String getGenero() {
		return genero;
	}



	public void setGenero(String genero) {
		this.genero = genero;
	}

	public String getEditor() {
		return editor;
	}



	public void setEditor(String editor) {
		this.editor = editor;
	}



	public double getNa_ventas() {
		return na_ventas;
	}

	public void setNa_ventas(double na_ventas) {
		this.na_ventas = na_ventas;
	}

	public double getEu_ventas() {
		return eu_ventas;
	}

	public void setEu_ventas(double eu_ventas) {
		this.eu_ventas = eu_ventas;
	}

	public double getJp_ventas() {
		return jp_ventas;
	}

	public void setJp_ventas(double jp_ventas) {
		this.jp_ventas = jp_ventas;
	}

	public double getOtras_ventas() {
		return otras_ventas;
	}

	public void setOtras_ventas(double otras_ventas) {
		this.otras_ventas = otras_ventas;
	}

	public double getGlobal_ventas() {
		return global_ventas;
	}

	public void setGlobal_ventas(double global_ventas) {
		this.global_ventas = global_ventas;
	}

	@Override
	public String toString() {
		return "Videojuego [rank=" + rank + ", nombre=" + nombre + ", plataforma=" + plataforma + ", ano=" + ano
				+ ", genero=" + genero + ", editor=" + editor + "]";
	}

}
