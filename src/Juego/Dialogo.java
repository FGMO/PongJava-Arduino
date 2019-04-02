package Juego;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.Timer;
import javax.swing.border.EmptyBorder;

public class Dialogo extends JFrame implements ActionListener {

	private JPanel contentPane;
	public static JLabel jugador;
	Timer t = null;

	/**
	 * Create the frame.
	 */
	public Dialogo() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 750, 200);
		contentPane = new JPanel();
		contentPane.setBackground(Color.BLACK);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setUndecorated(true);
		setLocationRelativeTo(null);

		jugador = new JLabel("", SwingConstants.CENTER);
		jugador.setFont(new Font("Comic Sans MS", Font.PLAIN, 61));
		jugador.setBounds(10, 11, 714, 178);
		contentPane.add(jugador);

		t = new Timer(10000, this);
		t.start();

	}

	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == t) {
			dispose();
		}
	}
}
