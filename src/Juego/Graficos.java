package Juego;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

import Constantes.Constantes;
import Objetos.Jugador_1;
import Objetos.Jugador_2;
import Objetos.Pelota;

public class Graficos extends JPanel implements Runnable {

	private Pelota pelota = new Pelota(this);
	public Jugador_1 J1 = new Jugador_1(this);
	public Jugador_2 J2 = new Jugador_2(this);
	private static Thread hilo;
	public static volatile boolean enFuncionamiento = false;
	private int tiempo = 0;
	public static int puntaje = 5;
	private int cont = 0;
	private int seg = 0;
	public static String texto = "PONG";
	private Dialogo d = new Dialogo();

	public Graficos() {
		setBackground(Color.BLACK);
	}

	public void iniciar() {
		enFuncionamiento = true;
		hilo = new Thread(this, "Pong");
		hilo.start();
	}

	public void detener() {
		enFuncionamiento = false;
		try {
			hilo.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public void paint(Graphics g) {
		super.paint(g);
		int ancho = 0;
		Graphics2D g2 = (Graphics2D) g;
		FontMetrics fm = null;
		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		g2.drawImage(new ImageIcon("res/w4.jpg").getImage(), 0, 0, getWidth(), getHeight(), null);
		g2.setColor(Color.WHITE);
		g2.drawLine(Pong.x / 2, 0, Pong.x / 2, Pong.y);
		g2.setColor(Color.RED);
		pelota.paint(g2);
		J1.paint(g2);
		J2.paint(g2);
		Font font = new Font("Consolas", Font.PLAIN, 12);
		g2.setFont(font);
		g2.setColor(Color.RED);
		g2.drawString("tmp: " + tiempo + " vel: " + Pelota.h, 0, Pong.y);
		if (!enFuncionamiento && Jugador_1.puntos < puntaje && Jugador_2.puntos < puntaje) {
			font = new Font("Consolas", Font.PLAIN, 96);
			fm = getFontMetrics(font);
			ancho = fm.stringWidth(texto);
			g2.setFont(font);
			g2.setColor(Color.WHITE);
			g2.drawString(texto, (Pong.x / 2) - (ancho / 2), (Pong.y / 2) - 43);
		}
		if (Jugador_1.puntos >= puntaje) {
			Dialogo.jugador.setText("GANO EL JUGADOR #1");
			Dialogo.jugador.setForeground(Jugador_1.colorJugador);
			d.setVisible(true);
			reiniciar();
		}
		if (Jugador_2.puntos >= puntaje) {
			Dialogo.jugador.setText("GANO EL JUGADOR #2");
			Dialogo.jugador.setForeground(Jugador_2.colorJugador);
			d.setVisible(true);
			reiniciar();
		}
		repaint();
	}

	public void run() {
		while (enFuncionamiento) {
			try {
				pelota.mover();
				if (cont >= 33) {
					seg++;
					tiempo++;
					cont = 0;
				}
				cont++;
				if (seg == 5 && Pelota.h != 28) {
					Pelota.h += 2;
					seg = 0;
				}
				Thread.sleep(30);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

	}

	public void reiniciar() {
		detener();
		Jugador_1.puntos = 0;
		Jugador_2.puntos = 0;
		tiempo = 0;
		seg = 0;
		cont = 0;
		Pelota.h = 7;
		texto = "PONG";
		pelota = new Pelota(this);
		Pelota.x = (Constantes.ANCHO_PANTALLA / 2) - Constantes.RADIO_PELOTA;
		Pelota.y = (Constantes.ALTO_PANTALLA / 2) - Constantes.RADIO_PELOTA;
		J1 = new Jugador_1(this);
		J2 = new Jugador_2(this);
	}

}
