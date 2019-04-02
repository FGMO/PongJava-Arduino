package Objetos;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.util.Random;

import Constantes.Constantes;
import Juego.Graficos;
import Juego.Pong;

public class Jugador_2 {

	private Graficos g;
	private int a = 255, b = 255, c = 255;
	public static int puntos;
	public static Color colorJugador;

	public Jugador_2(Graficos g) {
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
		g2.drawString("Jugador #2", Constantes.ANCHO_PANTALLA - 110, 14);
		font = new Font("Consolas", Font.PLAIN, 50);
		FontMetrics fm = g2.getFontMetrics();
		g2.setFont(font);
		g2.drawString("" + puntos, ((Pong.x / 4) * 3) - 13, 45);
		// g2.fillRect((Pong.x) - (Constantes.ANCHO_RAQUETA + 3), Conexion.Jy2,
		// Constantes.ANCHO_RAQUETA,
		// Constantes.ALTO_RAQUETA);

	}

	public java.awt.Rectangle borde() {
		return null;
		// return new java.awt.Rectangle(Constantes.ANCHO_PANTALLA -
		// (Constantes.ANCHO_RAQUETA + 3), Conexion.Jy2,
		// Constantes.ANCHO_RAQUETA, Constantes.ALTO_RAQUETA);
	}

}
