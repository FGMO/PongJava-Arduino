package Juego;

import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.net.URL;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class Pong extends JFrame {

	private JPanel contentPane;
	private static Graficos grafico = new Graficos();
	public static int x;
	public static int y;

	private int jfx = 6;
	private int jfy = 29;
	private URL musica;
	private AudioInputStream audioIn;
	private static Clip clip;

	public static void main(String[] args) throws InterruptedException {
		new Pong().setVisible(true);
	}

	public Pong() {
		try {
			musica = new URL("file:musica.wav");
			audioIn = AudioSystem.getAudioInputStream(musica);
			clip = AudioSystem.getClip();
			clip.open(audioIn);
		} catch (Exception e) {

		}
		setTitle("Pong_Arduino");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
		setBounds(0, 0, d.width, d.height - 40);
		setExtendedState(MAXIMIZED_BOTH);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setLocationRelativeTo(null);
		setResizable(true);
		setIconImage(new ImageIcon(getClass().getClassLoader().getResource("ups.png")).getImage());
		Cursor cursorInvisible = Toolkit.getDefaultToolkit().createCustomCursor(
				new BufferedImage(16, 16, BufferedImage.TRANSLUCENT), new Point(0, 0), "Cursor Invisible");

		addWindowListener(new java.awt.event.WindowAdapter() {
			public void windowClosing(java.awt.event.WindowEvent we) {

			}
		});

		addKeyListener(new KeyAdapter() {
			public void keyPressed(KeyEvent k) {
				if (k.getKeyCode() == KeyEvent.VK_F) {
					dispose();
					if (!isUndecorated()) {
						setExtendedState(MAXIMIZED_BOTH);
						setUndecorated(true);
						setCursor(cursorInvisible);
						jfx = 0;
						jfy = 0;
					} else {
						setUndecorated(false);
						setCursor(Cursor.getDefaultCursor());
						jfx = 6;
						jfy = 29;
					}
					setVisible(true);
				}
				if (k.getKeyCode() == KeyEvent.VK_P) {
					start();
				}
			}
		});

		contentPane.add(grafico);

		addComponentListener(new ComponentAdapter() {
			public void componentResized(ComponentEvent evt) {
				x = getWidth() - jfx;
				y = getHeight() - jfy;
				grafico.setBounds(0, 0, x, y);
			}
		});

	}

	public static void start() {
		if (Graficos.enFuncionamiento) {
			grafico.detener();
			clip.stop();
			Graficos.texto = "PAUSA";
		} else {
			grafico.iniciar();
			clip.loop(Clip.LOOP_CONTINUOUSLY);
		}
	}
}
