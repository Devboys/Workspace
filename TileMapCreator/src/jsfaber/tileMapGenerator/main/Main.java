package jsfaber.tileMapGenerator.main;

import javax.swing.JFrame;

public class Main {
	public static void main (String[] args) {
		JFrame window = new JFrame();
		window.setTitle("Test title");
		window.add(new SuperPanel());
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setResizable(false);
		window.pack();
		window.setLocationRelativeTo(null);
		window.setVisible(true);
	}
}
