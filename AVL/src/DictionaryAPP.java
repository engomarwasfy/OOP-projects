import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JFileChooser;

import java.awt.event.ActionListener;
import java.io.File;
import java.awt.event.ActionEvent;

public class DictionaryAPP {

	private JFrame frmDictionary;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	treeGUI t ; 
	DictionaryImp dic = new DictionaryImp();
	File file;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					DictionaryAPP window = new DictionaryAPP();
					window.frmDictionary.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public DictionaryAPP() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmDictionary = new JFrame();
		frmDictionary.setTitle("Dictionary");
		frmDictionary.setBounds(100, 100, 450, 300);
		frmDictionary.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmDictionary.getContentPane().setLayout(null);
		
		textField = new JTextField();
		textField.setBounds(22, 22, 86, 20);
		frmDictionary.getContentPane().add(textField);
		textField.setColumns(10);
		
		JButton btnInsert = new JButton("Insert");
		btnInsert.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(!dic.insert(textField.getText())){
					JOptionPane.showMessageDialog(null, "word already in dictionary");
				}else{
					JOptionPane.showMessageDialog(null, "insert succesfully done");
				}
				
				
			}
		});
		btnInsert.setBounds(125, 21, 89, 23);
		frmDictionary.getContentPane().add(btnInsert);
		
		textField_1 = new JTextField();
		textField_1.setBounds(22, 63, 86, 20);
		frmDictionary.getContentPane().add(textField_1);
		textField_1.setColumns(10);
		
		JButton btnDelete = new JButton("delete");
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(!dic.delete(textField_1.getText())){
					JOptionPane.showMessageDialog(null, "word not found");
				}else{
					JOptionPane.showMessageDialog(null, "delete succesfully done");
				}
			}
		});
		btnDelete.setBounds(125, 62, 89, 23);
		frmDictionary.getContentPane().add(btnDelete);
		
		JButton btnFind = new JButton("find");
		btnFind.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(dic.exists(textField_2.getText())){
				JOptionPane.showMessageDialog(null, "Word Found");	
				}else{
					JOptionPane.showMessageDialog(null, "Word not Found");
				}
			}
		});
		btnFind.setBounds(125, 104, 89, 23);
		frmDictionary.getContentPane().add(btnFind);
		
		textField_2 = new JTextField();
		textField_2.setBounds(22, 107, 86, 20);
		frmDictionary.getContentPane().add(textField_2);
		textField_2.setColumns(10);
		
		JButton btnSize = new JButton("Size");
		btnSize.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				JOptionPane.showMessageDialog(null, dic.size());
			}
		});
		btnSize.setBounds(125, 151, 89, 23);
		frmDictionary.getContentPane().add(btnSize);
		
		JButton btnHeight = new JButton("Height");
		btnHeight.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				JOptionPane.showMessageDialog(null, dic.height());
			}
		});
		btnHeight.setBounds(125, 196, 89, 23);
		frmDictionary.getContentPane().add(btnHeight);
		
		JButton btnLoaddictionary = new JButton("LoadDictionary");
		btnLoaddictionary.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				JFileChooser f = new JFileChooser();
				f.showSaveDialog(null);
				file= f.getSelectedFile();
				dic.load(file);
				JOptionPane.showMessageDialog(null, "Dictionary Loaded");
			}
		});
		btnLoaddictionary.setBounds(252, 21, 144, 23);
		frmDictionary.getContentPane().add(btnLoaddictionary);
		
		JButton btnShowDictionary = new JButton("Show Dictionary");
		btnShowDictionary.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(t!=null){
					t.dispose();
				}
			 t = new treeGUI(dic.tree);
			}
			
		});
		btnShowDictionary.setBounds(252, 62, 144, 23);
		frmDictionary.getContentPane().add(btnShowDictionary);
	}
}
