package Objetos;

import java.awt.Color;
import java.util.Random;

import Constantes.Constantes;
import Juego.Graficos;
import Juego.Pong;

public class Pelota {

	private Graficos g;
	public static int x = (Constantes.ANCHO_PANTALLA / 2) - Constantes.RADIO_PELOTA;
	public static int y = (Constantes.ALTO_PANTALLA / 2) - Constantes.RADIO_PELOTA;
	public static int h = 7;
	private int x1 = h;
	private int y1 = h;
	private int a = 255, b = 255, c = 255;

	public Pelota(Graficos g) {
		this.g = g;
	}

	public void mover() {
		if (x > Constantes.ANCHO_PANTALLA - Constantes.RADIO_PELOTA)
			x1 = -h;

		if (y > Constantes.ALTO_PANTALLA - Constantes.RADIO_PELOTA)
			y1 = -h;

		if (x < 0)
			x1 = h;

		if (y < 0)
			y1 = h;

		if (colisionJ1()) {
			x1 = h;
			sound();
		}
		if (colisionJ2()) {
			x1 = -h;
			sound();
		}
		if (x > Pong.x - Constantes.RADIO_PELOTA) {
			Jugador_1.puntos++;
			iniVar();
		}
		if (x < 0) {
			Jugador_2.puntos++;
			iniVar();
		}

		x += x1;
		y += y1;

		Random rnd = new Random();

		a = (int) (rnd.nextDouble() * 255 + 1);
		b = (int) (rnd.nextDouble() * 255 + 1);
		c = (int) (rnd.nextDouble() * 255 + 1);

	}

	public void paint(java.awt.Graphics2D g2) {
		g2.setColor(new java.awt.Color(a, b, c));
		g2.fillOval(x, y, Constantes.DIAMETRO_PELOTA, Constantes.DIAMETRO_PELOTA);
		g2.setColor(Color.WHITE);
		g2.fillOval(x + 5, y + 5, Constantes.DIAMETRO_PELOTA - 10, Constantes.DIAMETRO_PELOTA - 10);
	}

	public void iniVar() {
		x = (Constantes.ANCHO_PANTALLA / 2) - Constantes.RADIO_PELOTA;
		y = (Constantes.ALTO_PANTALLA / 2) - Constantes.RADIO_PELOTA;
	}

	public boolean colisionJ1() {
		return g.J1.borde().intersects(borde());
	}

	public boolean colisionJ2() {
		return g.J2.borde().intersects(borde());
	}

	public java.awt.Rectangle borde() {
		return new java.awt.Rectangle(x, y, Constantes.DIAMETRO_PELOTA, Constantes.DIAMETRO_PELOTA);
	}

	public void sound() {
		java.io.File archivo = new java.io.File("sound.wav");
		try {
			java.applet.AudioClip clip = java.applet.Applet.newAudioClip(archivo.toURI().toURL());
			clip.play();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

}
