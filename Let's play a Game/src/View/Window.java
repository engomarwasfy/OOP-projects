package View;

//	(final int width, final int height, final String title, final Game game)

import java.awt.Canvas;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

import Controller.ObjectsHandler;

public class Window extends Canvas {
	private final JMenu menu;
	private final JMenuBar menuBar;
	private final Game game;
	private final ObjectsHandler handler;

	public Window(final int width, final int height, final String title, final Game game, final ObjectsHandler handler) {
		menu = new JMenu("file");
		menuBar = new JMenuBar();
		this.handler = handler;



		final JFrame frame = new JFrame(title);
		frame.setJMenuBar(menuBar);
		menuBar.add(menu);
		frame.setPreferredSize(new Dimension(width, height));
		frame.setMaximumSize(new Dimension(width, height));
		frame.setMinimumSize(new Dimension(width, height));
		save();
		saveScore();
		load();
		loadScore();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		frame.setResizable(false);
		frame.add(game);
		frame.setLocationRelativeTo(null);
		this.game = game;

		game.start();

	}

	public void save() {
		final JMenuItem save = new JMenuItem("save");
		menu.add(save);
		save.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(final ActionEvent e) {
				try {
					Game.pause = true;
					final JFileChooser fileChooser = new JFileChooser();
					fileChooser.showSaveDialog(null);
					final String dir = fileChooser.getSelectedFile().toString();
					final Save save = new Save(handler);
					save.saveGame(dir);
					Game.pause = false;

				} catch (final Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
					JOptionPane.showMessageDialog(save, Window.this, e1.toString(), 0);
				}

			}
		});

	}

	public void load() {
		final JMenuItem load = new JMenuItem("load");
		menu.add(load);
		load.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(final ActionEvent e) {
				try {
					Game.pause = true;
					final JFileChooser fileChooser = new JFileChooser();
					fileChooser.showSaveDialog(null);
					final String dir = fileChooser.getSelectedFile().toString();
					final LoadGame load = new LoadGame(handler,game,fileChooser.getSelectedFile());
					Game.pause = false;
				} catch (final Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
					JOptionPane.showMessageDialog(load, Window.this, e1.toString(), 0);
				}

			}
		});

	}
	
	public void saveScore() {
		final JMenuItem save = new JMenuItem("saveScore");
		menu.add(save);
		save.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(final ActionEvent e) {
				try {
					Game.pause = true;
					final JFileChooser fileChooser = new JFileChooser();
					fileChooser.showSaveDialog(null);
					final String dir = fileChooser.getSelectedFile().toString();
					ScoreSaveAndLoad sl = new ScoreSaveAndLoad();
					sl.save(dir);
					Game.pause = false;

				} catch (final Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
					JOptionPane.showMessageDialog(save, Window.this, e1.toString(), 0);
				}

			}
		});
		

	}
	
	
	public void loadScore() {
		final JMenuItem load = new JMenuItem("loadScore");
		menu.add(load);
		load.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(final ActionEvent e) {
				try {
					Game.pause = true;
					final JFileChooser fileChooser = new JFileChooser();
					fileChooser.showSaveDialog(null);
					final String dir = fileChooser.getSelectedFile().toString();
					ScoreSaveAndLoad sl = new ScoreSaveAndLoad();
					sl.load(dir);
					Game.pause = false;
				} catch (final Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
					JOptionPane.showMessageDialog(load, Window.this, e1.toString(), 0);
				}

			}
		});

	}

}

