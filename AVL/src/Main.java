import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Main {

	private JFrame frmAvlTreeVisulization;
	private JTextField textField;
	AVLTreeImpl x = new AVLTreeImpl();
	treeGUI t;
	private JTextField textField_1;
	private JButton btnDelete;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Main window = new Main();
					window.frmAvlTreeVisulization.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Main() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmAvlTreeVisulization = new JFrame();
		frmAvlTreeVisulization.setTitle("AVL tree visulization");
		frmAvlTreeVisulization.setBounds(100, 100, 523, 410);
		frmAvlTreeVisulization.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmAvlTreeVisulization.getContentPane().setLayout(null);
		
		JButton btnInsert = new JButton("Insert");
		btnInsert.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
					x.insert(Integer.parseInt(textField.getText()));
					if(t!=null){
						t.dispose();
					}
				 t = new treeGUI(x);
				}
			
		});
		btnInsert.setBounds(120, 27, 89, 23);
		frmAvlTreeVisulization.getContentPane().add(btnInsert);
		
		textField = new JTextField();
		textField.setBounds(24, 28, 86, 20);
		frmAvlTreeVisulization.getContentPane().add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setBounds(255, 28, 86, 20);
		frmAvlTreeVisulization.getContentPane().add(textField_1);
		textField_1.setColumns(10);
		
		btnDelete = new JButton("Delete");
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				x.delete(Integer.parseInt(textField_1.getText()));
				if(t!=null){
					t.dispose();
				}
			 t = new treeGUI(x);
			}
			
		});
		btnDelete.setBounds(365, 27, 89, 23);
		frmAvlTreeVisulization.getContentPane().add(btnDelete);
	}
}
