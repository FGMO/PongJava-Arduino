package Objetos;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.util.Random;

import Juego.Graficos;
import Juego.Pong;

public class Jugador_1 {

	private Graficos g;
	private int a = 255, b = 255, c = 255;
	public static int puntos;
	public static Color colorJugador;

	public Jugador_1(Graficos g) {
		this.g = g;
		Random rnd = new Random();
		a = (int) (rnd.nextDouble() * 255 + 1);
		b = (int) (rnd.nextDouble() * 255 + 1);
		c = (int) (rnd.nextDouble() * 255 + 1);

		colorJugador = new java.awt.Color(a, b, c);
	}

	public void paint(java.awt.Graphics2D g2) {
		g2.setColor(new java.awt.Color(a, b, c));
		Font font = new Font("Consolas", Font.PLAIN, 20);
		g2.setFont(font);
		g2.drawString("Jugador #1", 0, 14);
		font = new Font("Consolas", Font.PLAIN, 50);
		FontMetrics fm = g2.getFontMetrics(font);
		g2.setFont(font);
		g2.drawString("" + puntos, (Pong.x / 4) - 13, 45);
		// g2.fillRect(3, Conexion.Jy1, Constantes.ANCHO_RAQUETA,
		// Constantes.ALTO_RAQUETA);
	}

	public java.awt.Rectangle borde() {
		return null;
		// return new java.awt.Rectangle(3, Conexion.Jy1,
		// Constantes.ANCHO_RAQUETA, Constantes.ALTO_RAQUETA);
	}

}
