package eg.edu.alexu.csd.oop.paint.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.EventQueue;
import java.awt.Label;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPopupMenu;
import javax.swing.JToolBar;
import javax.swing.SwingConstants;
import javax.swing.border.BevelBorder;
import javax.swing.border.SoftBevelBorder;
import java.awt.Font;
import java.awt.HeadlessException;

import com.google.gson.JsonIOException;
import com.google.gson.JsonSyntaxException;

import eg.edu.alexu.csd.oop.paint.controller.DynamicLoader;
import eg.edu.alexu.csd.oop.paint.controller.JsonLoad;
import eg.edu.alexu.csd.oop.paint.controller.JsonSave;
import eg.edu.alexu.csd.oop.paint.controller.LoadXml;
import eg.edu.alexu.csd.oop.paint.controller.SaveXml;
import eg.edu.alexu.csd.oop.paint.controller.Surface;
import eg.edu.alexu.csd.oop.paint.model.AllShape;

public class Gui extends JFrame {
	public static String activeButton;
	public static String activeButtonEdit = "null";
	private static Label label = new Label("omar wasfy  & shaban sheta");

	public static Label getLabel() {
		return label;
	}

	public Gui() {
		initUI();
	}

	private void initUI() {
		final Surface surface = new Surface();
		surface.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null, null, null));
		surface.setBackground(Color.WHITE);
		getContentPane().add(surface);
		surface.setLayout(null);
		label.setFont(new Font("Dialog", Font.BOLD, 16));
		label.setBackground(Color.YELLOW);
		label.setBounds(0, 610, 870, 31);
		surface.add(label);

		JToolBar toolBar = new JToolBar();
		toolBar.setOrientation(SwingConstants.VERTICAL);
		getContentPane().add(toolBar, BorderLayout.WEST);
		toolBar.bounds();
		JButton line2 = new JButton("");
		line2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				activeButton = "line";
				activeButtonEdit = "null";
			}
		});
		line2.setToolTipText("line");
		toolBar.add(line2);
		line2.setIcon(new ImageIcon("img\\Line-icon.png"));

		JButton rectangle2 = new JButton("");
		rectangle2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				activeButton = "rectangle";
				activeButtonEdit = "null";
			}
		});
		rectangle2.setToolTipText("rectangle");
		toolBar.add(rectangle2);
		rectangle2.setIcon(new ImageIcon("img\\Rectangle-tool-icon.png"));

		JButton square2 = new JButton("");
		square2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				activeButton = "square";
				activeButtonEdit = "null";
			}
		});
		square2.setToolTipText("square");
		toolBar.add(square2);
		square2.setIcon(new ImageIcon("img\\Square-icon.png"));

		JButton elipse2 = new JButton("");
		elipse2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				activeButton = "elipse";
				activeButtonEdit = "null";
			}
		});
		elipse2.setToolTipText("elipse");
		toolBar.add(elipse2);
		elipse2.setIcon(new ImageIcon("img\\elipse-icon.png"));

		JButton circle2 = new JButton("");
		circle2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				activeButton = "circle";
				activeButtonEdit = "null";
			}
		});
		circle2.setToolTipText("circle");
		toolBar.add(circle2);
		circle2.setIcon(new ImageIcon("img\\Circle-icon.png"));

		final JButton triangle = new JButton("");
		triangle.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				activeButton = "triangle";
				activeButtonEdit = "null";
			}
		});
		triangle.setVisible(false);
		triangle.setToolTipText("triangle");
		toolBar.add(triangle);
		triangle.setIcon(new ImageIcon("img\\triangle-icon.png"));

		JToolBar toolBar_2 = new JToolBar();
		toolBar_2.setOrientation(SwingConstants.VERTICAL);
		getContentPane().add(toolBar_2, BorderLayout.EAST);

		JButton select2 = new JButton("");
		select2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				activeButton = "select";
				activeButtonEdit = "edit";
			}
		});
		select2.setIcon(new ImageIcon("img\\finger-pointer-icon.png"));
		select2.setToolTipText("select");
		toolBar_2.add(select2);

		JButton move2 = new JButton("");
		move2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				activeButton = "move";
				activeButtonEdit = "edit";
			}
		});
		move2.setIcon(new ImageIcon("img\\move-icon.png"));
		move2.setToolTipText("move");
		toolBar_2.add(move2);

		JButton resize2 = new JButton("");
		resize2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				activeButton = "resize";
				activeButtonEdit = "edit";
			}
		});
		resize2.setHorizontalAlignment(SwingConstants.RIGHT);
		resize2.setIcon(new ImageIcon("img\\resize-picture-icon.png"));
		resize2.setToolTipText("resize");
		toolBar_2.add(resize2);

		JButton delete2 = new JButton("");
		delete2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				activeButton = "delete";
				activeButtonEdit = "edit";
			}
		});
		delete2.setIcon(new ImageIcon("img\\delete-icon.png"));
		delete2.setToolTipText("delete");
		toolBar_2.add(delete2);
		
		JButton deleteMore = new JButton("");
		deleteMore.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				for(AllShape s : surface.getSelectedShapes()) {
					surface.getShapes().remove(s);
				}
				surface.getSelectedShapes().clear();
			}
		});
		deleteMore.setIcon(new ImageIcon("img\\Actions-window-close-icon.png"));
		deleteMore.setToolTipText("Delete More");
		toolBar_2.add(deleteMore);
		setTitle("paint");
		setSize(900, 700);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);

		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);

		JMenu mnFile = new JMenu("File");
		menuBar.add(mnFile);

		JMenuItem mntmOpen = new JMenuItem("Open");
		mnFile.add(mntmOpen);

		JMenuItem mntmLoad = new JMenuItem("LoadJson");
		mntmLoad.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				JFileChooser loadFile = new JFileChooser();
				loadFile.showDialog(null, "Load");
				JsonLoad l = new JsonLoad();
				try {
					surface.setShapes(l.load(loadFile.getSelectedFile().toString()));
					repaint();
				} catch (JsonSyntaxException | JsonIOException e) {
					JOptionPane.showMessageDialog(null, " something went wrong");
				} catch (FileNotFoundException e) {
					JOptionPane.showMessageDialog(null, " something went wrong");
				} catch (IOException e) {
					JOptionPane.showMessageDialog(null, " something went wrong");
				}
				repaint();
			}
		});
		mnFile.add(mntmLoad);

		JMenuItem mntmSave = new JMenuItem("SaveJson");
		mntmSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				JFileChooser saveFile = new JFileChooser();
				saveFile.showSaveDialog(null);
				// SaveXml.save(Surface.shapes,
				// saveFile.getSelectedFile().toString());
				JsonSave x = new JsonSave();

				try {
					try {
						x.save(surface.getShapes(), saveFile.getSelectedFile().toString());
					} catch (NoSuchMethodException | SecurityException | IllegalArgumentException
							| InvocationTargetException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				} catch (JsonIOException e) {
					JOptionPane.showMessageDialog(null, " something went wrong");
				} catch (IOException e) {
					JOptionPane.showMessageDialog(null, " something went wrong");
				}
			}
		});
		mnFile.add(mntmSave);

		JMenuItem mntmLoadxml = new JMenuItem("LoadXml");
		mntmLoadxml.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					JFileChooser loadFile = new JFileChooser();
					loadFile.showDialog(null, "Load");
					LoadXml l = new LoadXml();
					surface.setShapes(l.load(loadFile.getSelectedFile().toString()));
					repaint();
				} catch (Exception e) {
					JOptionPane.showMessageDialog(null, "someThing went wrong");
				}
			}
		});
		mnFile.add(mntmLoadxml);

		JMenuItem mntmSavexml = new JMenuItem("SaveXml");
		mntmSavexml.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFileChooser saveFile = new JFileChooser();
				saveFile.showDialog(null, "save");
				SaveXml s = new SaveXml();
				try {
					s.save(surface.getShapes(), saveFile.getSelectedFile().toString());
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					JOptionPane.showMessageDialog(null, " something went wrong");
				}
			}
		});
		mnFile.add(mntmSavexml);

		JMenu mnShapes = new JMenu("Shapes");
		menuBar.add(mnShapes);

		JMenu mnDraw = new JMenu("Draw");
		mnShapes.add(mnDraw);

		JMenuItem mntmLine = new JMenuItem("Line");
		mntmLine.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				activeButtonEdit = "null";
				activeButton = "line";
			}
		});
		mnDraw.add(mntmLine);

		JMenuItem mntmRectangle = new JMenuItem("Rectangle");
		mntmRectangle.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				activeButtonEdit = "null";
				activeButton = "rectangle";
			}
		});
		mnDraw.add(mntmRectangle);

		JMenuItem mntmSquare = new JMenuItem("Square");
		mntmSquare.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				activeButtonEdit = "null";
				activeButton = "square";
			}
		});
		mnDraw.add(mntmSquare);

		JMenuItem mntmElipse = new JMenuItem("Elipse");
		mntmElipse.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				activeButtonEdit = "null";
				activeButton = "elipse";
			}
		});
		mnDraw.add(mntmElipse);

		JMenuItem mntmCircle = new JMenuItem("Circle");
		mntmCircle.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				activeButtonEdit = "null";
				activeButton = "circle";
			}
		});
		mnDraw.add(mntmCircle);

		final JMenuItem mntmTriangle = new JMenuItem("Triangle");
		mntmTriangle.setVisible(false);
		mntmTriangle.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				activeButtonEdit = "null";
				activeButton = "triangle";
			}
		});
		mnDraw.add(mntmTriangle);

		JMenu mnEdit = new JMenu("Edit");
		mnEdit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				activeButton = "edit";
			}
		});
		mnShapes.add(mnEdit);

		JMenuItem mntmMove = new JMenuItem("Move");
		mntmMove.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				activeButtonEdit = "edit";
				activeButton = "move";
			}
		});
		mnEdit.add(mntmMove);

		JMenuItem mntmResize = new JMenuItem("ReSize");
		mntmResize.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				activeButtonEdit = "edit";
				activeButton = "resize";
			}
		});
		mnEdit.add(mntmResize);

		JMenuItem mntmDelete = new JMenuItem("Delete");
		mntmDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				activeButtonEdit = "edit";
				activeButton = "delete";
			}
		});
		mnEdit.add(mntmDelete);

		JMenuItem fillColor = new JMenuItem("FillColor");
		fillColor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				JColorChooser ch = new JColorChooser();
				surface.setFillColor(ch.showDialog(ch, "choose color", Color.green));
				activeButtonEdit = "edit";
				activeButton = "fillcolor";

			}
		});
		mnEdit.add(fillColor);

		JMenuItem drawcolor = new JMenuItem("DrawColor");
		drawcolor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				JColorChooser ch = new JColorChooser();
				surface.setColor(ch.showDialog(ch, "choose color", Color.green));

				activeButtonEdit = "edit";
				activeButton = "drawcolor";
			}
		});
		mnEdit.add(drawcolor);

		JMenuItem mntmUndo = new JMenuItem("Undo");
		mntmUndo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Gui.activeButton = "undo";

				try {
					surface.setShapes(Surface.m.undo());
				} catch (Exception e) {
					// TODO Auto-generated catch block
					JOptionPane.showMessageDialog(null, "no previous");
				}
			}
		});
		mnEdit.add(mntmUndo);
		JMenuItem mntmRedo = new JMenuItem("Redo");
		mntmRedo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Gui.activeButton = "redo";
				try {
					surface.setShapes(Surface.m.redo());
				} catch (Exception e) {
					// TODO Auto-generated catch block
					JOptionPane.showMessageDialog(null, "no next");
				}
				// Surface.shapes = Surface.m.redo();

			}
		});
		mnEdit.add(mntmRedo);

		JMenu mnAddextension = new JMenu("addExtension");
		menuBar.add(mnAddextension);

		JMenuItem mntmTriangle_1 = new JMenuItem("Choose .class file");
		mntmTriangle_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				try {
					JFileChooser loadFile = new JFileChooser();
					loadFile.showDialog(null, "Load");
					loadFile.getSelectedFile();
					surface.url = loadFile.getSelectedFile().toString();
					DynamicLoader x = new DynamicLoader();
					x.getShape(surface.url, 1, 1, 1, 1, null, null);
						mntmTriangle.setVisible(true);
						triangle.setVisible(true);
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					
				}
				}
			

		});
		mnAddextension.add(mntmTriangle_1);

	}

	public static void main(String[] args) {

		EventQueue.invokeLater(new Runnable() {

			@Override
			public void run() {
				Gui ex = new Gui();
				ex.setVisible(true);
			}
		});
	}

	private static void addPopup(Component component, final JPopupMenu popup) {
		component.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				if (e.isPopupTrigger()) {
					showMenu(e);
				}
			}

			public void mouseReleased(MouseEvent e) {
				if (e.isPopupTrigger()) {
					showMenu(e);
				}
			}

			private void showMenu(MouseEvent e) {
				popup.show(e.getComponent(), e.getX(), e.getY());
			}
		});
	}
}
