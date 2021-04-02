package guiDatabase;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.Box;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;

import java.util.ArrayList;
import java.util.List;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import javax.swing.JTextArea;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import javax.swing.JTable;
import javax.swing.JFrame;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.JTable;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.Box;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;



public class GUI_Database {

	private static final String DISPOSE_ON_CLOSE = null;
	private JFrame frame;
	private JComboBox jdbcField;
	private JComboBox urlField;
	private JTextArea sqlField;
	private JTextField userNameField;
	private JPasswordField passwordField;
	private ResultSetTableModel table;
	private JTable table_1;
	private JTable table_2;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GUI_Database window = new GUI_Database();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public GUI_Database() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		
		try {
		table = new ResultSetTableModel();
		sqlField = new JTextArea("", 3, 100);
        sqlField.setWrapStyleWord(true);
        sqlField.setLineWrap(true);
        
        JScrollPane scrollPane = new JScrollPane(sqlField, ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED,
                ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        
        Box resultTableAndHeader = Box.createVerticalBox();
        JTable resultTable = new JTable(table);
        resultTable.setGridColor(Color.BLACK);
        
        Box box = Box.createHorizontalBox();
        box.setBorder(new EmptyBorder(10, 10, 10, 10));
        
        Box verticalMainBox = Box.createVerticalBox();
        verticalMainBox.setBorder(new EmptyBorder(0, 0, 10, 0));
        
        verticalMainBox.add(box);
        
        
        
        
		frame = new JFrame();
		frame.getContentPane().setFont(new Font("Tahoma", Font.BOLD, 11));
		frame.setBounds(100, 100, 929, 551);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		//Labels for boxes
		JLabel lblNewLabel = new JLabel("Enter Database Information");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNewLabel.setForeground(Color.BLUE);
		lblNewLabel.setBounds(10, 11, 164, 14);
		frame.getContentPane().add(lblNewLabel);
		
		
     
        JLabel lblNewLabel_1 = new JLabel("JDBC Driver");
		lblNewLabel_1.setBackground(Color.WHITE);
		lblNewLabel_1.setBounds(10, 36, 134, 14);
		frame.getContentPane().add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Database URL");
		lblNewLabel_2.setBounds(10, 61, 134, 14);
		frame.getContentPane().add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("Username");
		lblNewLabel_3.setBounds(10, 86, 134, 14);
		frame.getContentPane().add(lblNewLabel_3);
		
		JLabel lblNewLabel_4 = new JLabel("Password");
		lblNewLabel_4.setBounds(10, 111, 134, 14);
		frame.getContentPane().add(lblNewLabel_4);
		
		//Combo Box for command window
		String[] jdbcDriver = {"com.mysql.cj.jdbc.Driver"};
		JComboBox comboBox = new JComboBox(jdbcDriver);
		comboBox.setBounds(154, 32, 367, 22);
		frame.getContentPane().add(comboBox);
		
		String[] databaseURL = {"jdbc:mysql://localhost:3306/project3?useTimezone=true&serverTimezone=UTC"};
		JComboBox comboBox_1 = new JComboBox(databaseURL);
		comboBox_1.setToolTipText("jdbc:mysql://localhost:3306/project3?useTimezone=true&serverTimezone=UTC");
		comboBox_1.setBounds(154, 57, 367, 22);
		frame.getContentPane().add(comboBox_1);
		//username
		userNameField = new JTextField();
		userNameField.setBounds(154, 83, 367, 20);
		frame.getContentPane().add(userNameField);
		userNameField.setColumns(10);
		//password
		passwordField = new JPasswordField();
		passwordField.setBounds(154, 108, 367, 20);
		frame.getContentPane().add(passwordField);
		
		JLabel lblNewLabel_5 = new JLabel("Enter an SQL Command");
		lblNewLabel_5.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNewLabel_5.setForeground(Color.BLUE);
		lblNewLabel_5.setBounds(531, 11, 151, 14);
		frame.getContentPane().add(lblNewLabel_5);
		
		JTextArea textArea = new JTextArea();
		textArea.setBounds(531, 31, 372, 97);
		frame.getContentPane().add(textArea);
		
      
		final JTextArea txtrNoConnection = new JTextArea("No Connection", 1, 1);
		txtrNoConnection.setBackground(Color.BLACK);
		txtrNoConnection.setForeground(Color.RED);
		txtrNoConnection.setBounds(10, 165, 351, 22);
		frame.getContentPane().add(txtrNoConnection);
		
		final JButton btnNewButton = new JButton("Connect to Database");
		btnNewButton.setBackground(Color.BLUE);
		btnNewButton.setForeground(Color.BLACK);
		btnNewButton.setBounds(398, 166, 169, 23);
		frame.getContentPane().add(btnNewButton);
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// perform a new query
				String url =  getAvailableJDBCURLs().get(0);
				String username = userNameField.getText();
                String password = new String(passwordField.getPassword());   
				try {
	                  table.Connection(url, username, password);

	                  txtrNoConnection.setText("Connected to " + url);
	                  btnNewButton.setEnabled(false);
	                  btnNewButton.setText("CONNECTED");
	               } // end try
	               catch (SQLException sqlException) {
	                  JOptionPane.showMessageDialog(null, sqlException.getMessage(), "Database error",
	                        JOptionPane.ERROR_MESSAGE);
	               } // end outer catch
	            } // end actionPerformed
	         } // end ActionListener inner class
	         ); // end call to addActionListener
		
		
		
		JButton btnNewButton_2 = new JButton("Execute SQL Command");
		btnNewButton_2.setBackground(Color.GREEN);
		btnNewButton_2.setFont(new Font("Tahoma", Font.PLAIN, 10));
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
                // perform a new query
                try {
                   String queryToExecute = sqlField.getText();

                   if (queryToExecute.startsWith("select") || queryToExecute.startsWith("SELECT")) {
                      table.setQuery(queryToExecute);
                   } else {
                      table.setUpdate(queryToExecute);
                   }

                } // end try
                catch (Exception exception) {
                   System.out.println(exception);
                   exception.printStackTrace();
                   JOptionPane.showMessageDialog(null, exception.getMessage(), "Database error",
                         JOptionPane.ERROR_MESSAGE);
                } // end outer catch
             } // end actionPerformed
          } // end ActionListener inner class
    ); // end call to addActionListener
		btnNewButton_2.setBounds(763, 166, 140, 23);
		frame.getContentPane().add(btnNewButton_2);
		
		JLabel lblNewLabel_6 = new JLabel("SQL Execution Result Window");
		lblNewLabel_6.setBounds(10, 198, 207, 14);
		frame.getContentPane().add(lblNewLabel_6);
			
		JButton btnNewButton_3 = new JButton("Clear Result Window");
		btnNewButton_3.setBackground(Color.YELLOW);
		btnNewButton_3.setBounds(10, 459, 175, 23);
		frame.getContentPane().add(btnNewButton_3);
		
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				table.clearTable();
            } // end actionPerformed
         } // end ActionListener inner class
         );
		
		JButton btnNewButton_1 = new JButton("Clear SQL Command");
		btnNewButton_1.setForeground(Color.RED);
		btnNewButton_1.setBounds(577, 166, 176, 23);
		frame.getContentPane().add(btnNewButton_1);
		
		table_2 = new JTable(table);
		table_2.setBounds(10, 223, 871, 225);
		frame.getContentPane().add(table_2);
	
		btnNewButton_1.addActionListener(new ActionListener() {
		 public void actionPerformed(ActionEvent e) {
             // perform a new query
             sqlField.setText("");
          } // end actionPerformed
       } // end ActionListener inner class
       ); // end call to addActionListener
		
		}
		catch (Exception sqlException) {
	         JOptionPane.showMessageDialog(null, sqlException.getMessage(), "Database error", JOptionPane.ERROR_MESSAGE);

	         // ensure database connection is closed
	         table.disconnectFromDatabase();

	         System.exit(1); // terminate application
	      } // end catch

	      // dispose of window when user quits application (this overrides
	      // the default of HIDE_ON_CLOSE)
	      frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		  frame.addWindowListener(new WindowAdapter() {
	         // disconnect from database and exit when window has closed
	         public void windowClosed(WindowEvent e) {
	            table.disconnectFromDatabase();
	            System.exit(0);
	         } // end method windowClosed

	      } // end WindowAdapter inner class
	      ); // end call to addWindowListener
	   } // end DisplayQueryResults constructor
	

	public static List<String> getAvailableJDBCDrivers() {
	      List<String> availableDrivers = new ArrayList<>();

	      availableDrivers.add("com.mysql.cj.jdbc.Driver");

	      return availableDrivers;
	   }

	   public static List<String> getAvailableJDBCURLs() {
	      List<String> availableURLs = new ArrayList<>();

	      availableURLs.add("jdbc:mysql://localhost:3306/project3?useTimezone=true&serverTimezone=UTC");

	      return availableURLs;
	   }
}
	

