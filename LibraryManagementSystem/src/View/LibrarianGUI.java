package View;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;


import java.awt.Color;
import javax.swing.JLabel;
import java.awt.Font;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import Model.BookTypes;
import java.util.ArrayList;
import Model.*;
import Helper.*;
import javax.swing.JComboBox;

public class LibrarianGUI extends JFrame {

	static Librarian librarian = new Librarian();
	BookTypes booktypes = new BookTypes();
	private JPanel w_pane;
	private JTextField fld_bookID;
	private JTextField fld_bookname;
	private JTextField fld_bookauthor;
	private JTable w_tableBook;
	private DefaultTableModel bookModel = null;
	private Object[] bookData = null;
	private JTextField fld_bookdeleteid;
	private JTable table_type;
	private JTextField fld_typename;
	private DefaultTableModel typeModel = null;
	private Object[] typeData = null;
	
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LibrarianGUI frame = new LibrarianGUI(librarian);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 * @throws SQLException 
	 */
	public LibrarianGUI(final Librarian librarian) throws SQLException {
		
		
		// Book Model
		bookModel = new DefaultTableModel();
		Object[] colBookName = new Object[3];
		colBookName[0] = "ID";
		colBookName[1] = "Book's Name";
		colBookName[2] = "Book's Author";
		bookModel.setColumnIdentifiers(colBookName);
		bookData = new Object[3];
		for(int i = 0; i< librarian.getBookList().size(); i++) {
			bookData[0] = librarian.getBookList().get(i).getIdentitynumber();
			bookData[1] = librarian.getBookList().get(i).getBooksname();
			bookData[2] = librarian.getBookList().get(i).getBooksauthor();
			bookModel.addRow(bookData);
			
		}
		
		// Type Model
		typeModel = new DefaultTableModel();
				Object[] colTypeName = new Object [2];
		colTypeName[0] = "ID";
		colTypeName[1] = "Type's Name";
		typeModel.setColumnIdentifiers(colTypeName);
		typeData = new Object [2];
		for(int i=0; i< booktypes.getList().size(); i++) {
			typeData[0] = booktypes.getList().get(i).getId();
			typeData[1]= booktypes.getList().get(i).getName();
			typeModel.addRow(typeData);
		}
		
		setTitle("Library Management System");
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 550, 400);
		w_pane = new JPanel();
		w_pane.setBackground(Color.WHITE);
		w_pane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(w_pane);
		w_pane.setLayout(null);
		
		JLabel lbl_welcome = new JLabel("Welcome," + librarian.getName());
		lbl_welcome.setFont(new Font("Will&Grace", Font.PLAIN, 21));
		lbl_welcome.setBounds(24, 11, 236, 34);
		w_pane.add(lbl_welcome);
		
		JButton btn_exit = new JButton("EXIT");
		btn_exit.setFont(new Font("Will&Grace", Font.PLAIN, 11));
		btn_exit.setBounds(431, 21, 89, 23);
		w_pane.add(btn_exit);
		
		JTabbedPane w_tabpane = new JTabbedPane(JTabbedPane.TOP);
		w_tabpane.setBackground(Color.WHITE);
		w_tabpane.setBounds(10, 66, 524, 294);
		w_pane.add(w_tabpane);
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.WHITE);
		w_tabpane.addTab("BOOK MANAGEMENT", null, panel, null);
		panel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Book's Name:");
		lblNewLabel.setFont(new Font("Will&Grace", Font.PLAIN, 14));
		lblNewLabel.setBounds(416, 47, 93, 29);
		panel.add(lblNewLabel);
		
		fld_bookID = new JTextField();
		fld_bookID.setBounds(416, 29, 93, 20);
		panel.add(fld_bookID);
		fld_bookID.setColumns(10);
		
		JLabel lblBooksAuthor = new JLabel("Book's Author:");
		lblBooksAuthor.setFont(new Font("Will&Grace", Font.PLAIN, 14));
		lblBooksAuthor.setBounds(416, 87, 93, 29);
		panel.add(lblBooksAuthor);
		
		fld_bookname = new JTextField();
		fld_bookname.setBounds(416, 73, 93, 20);
		panel.add(fld_bookname);
		fld_bookname.setColumns(10);
		
		JLabel lblBooksId = new JLabel("Book's ID:");
		lblBooksId.setFont(new Font("Will&Grace", Font.PLAIN, 14));
		lblBooksId.setBounds(416, 3, 93, 29);
		panel.add(lblBooksId);
		
		fld_bookauthor = new JTextField();
		fld_bookauthor.setColumns(10);
		fld_bookauthor.setBounds(416, 115, 93, 20);
		panel.add(fld_bookauthor);
		
		JButton btn_add = new JButton("ADD BOOK");
		btn_add.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(fld_bookID.getText().length() == 0 || fld_bookname.getText().length() == 0 || fld_bookauthor.getText().length() == 0) {
					Helper.showMsg("fill");
				}else {
					try {
					boolean control = librarian.addBook(fld_bookID.getText(),fld_bookname.getText(), fld_bookauthor.getText());
					if(control) {
						Helper.showMsg("success");
						fld_bookID.setText(null);
						fld_bookname.setText(null);
						fld_bookauthor.setText(null);
						updateBookModel();
					}
					}catch (SQLException e1) {
						e1.printStackTrace();
					}
				}
			}
		});
		btn_add.setBounds(416, 146, 93, 23);
		panel.add(btn_add);
		
		JScrollPane w_scrollBook = new JScrollPane();
		w_scrollBook.setBounds(0, 3, 406, 262);
		panel.add(w_scrollBook);
		
		w_tableBook = new JTable(bookModel);
		w_scrollBook.setViewportView(w_tableBook);
		w_tableBook.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
			
			@Override
			public void valueChanged(ListSelectionEvent e) {
				try {
				
				fld_bookdeleteid.setText(w_tableBook.getValueAt(w_tableBook.getSelectedRow(), 0).toString());
			}catch (Exception ex) {
				}
			}
			});
	
		JLabel book_id = new JLabel("Book's ID:");
		book_id.setFont(new Font("Will&Grace", Font.PLAIN, 14));
		book_id.setBounds(416, 168, 93, 29);
		panel.add(book_id);
		
		JButton btn_delete = new JButton("DELETE");
		btn_delete.setBounds(416, 232, 93, 23);
		panel.add(btn_delete);
		
		JPanel w_type = new JPanel();
		w_type.setBackground(Color.WHITE);
		w_tabpane.addTab("TYPES", null, w_type, null);
		w_type.setLayout(null);
		
		JScrollPane w_scrolltype = new JScrollPane();
		w_scrolltype.setBounds(10, 11, 177, 244);
		w_type.add(w_scrolltype);
		
		table_type = new JTable(typeModel);
		w_scrolltype.setViewportView(table_type);
		
		JLabel lblBooksType = new JLabel("Book's Type:");
		lblBooksType.setFont(new Font("Will&Grace", Font.PLAIN, 14));
		lblBooksType.setBounds(207, 11, 112, 29);
		w_type.add(lblBooksType);
		
		fld_typename = new JTextField();
		fld_typename.setColumns(10);
		fld_typename.setBounds(207, 37, 112, 20);
		w_type.add(fld_typename);
		
		JButton btn_addtype = new JButton("ADD");
		btn_addtype.setBounds(207, 68, 112, 23);
		w_type.add(btn_addtype);
		btn_addtype.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {	
				if(fld_typename.getText().length() == 0) {
					Helper.showMsg("fill");
				}else {
					try {
						if(booktypes.addBookTypes(fld_typename.getText())) {
							Helper.showMsg("success");
							fld_typename.setText(null);
							updateTypeModel();
						}
					} catch (SQLException e1) {
						e1.printStackTrace();
					}
				}
			}
		});
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(341, 11, 168, 244);
		w_type.add(scrollPane);
		
		JComboBox select_type = new JComboBox();
		select_type.setBounds(207, 186, 112, 22);
		for(int i=0; i< librarian.getBookList().size() ; i ++) {
			select_type.addItem(librarian.getBookList().get(i).getBooksname());
		}
		w_type.add(select_type);
		
		JButton btn_addType = new JButton("ADD");
		btn_addType.setBounds(207, 219, 112, 23);
		w_type.add(btn_addType);
		
		if(book_id.getText().length() == 0) {
			Helper.showMsg("Please Choose An Appropriate Book!");
		}else {
			try {
			String identitynumber = null;
			boolean control = librarian.deleteBook(identitynumber);
			if(control) {
				book_id.setText(null);
				updateBookModel();
				
			}
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		
		fld_bookdeleteid = new JTextField();
		fld_bookdeleteid.setColumns(10);
		fld_bookdeleteid.setBounds(416, 201, 93, 20);
		panel.add(fld_bookdeleteid);
		}
		}

public void updateBookModel() throws SQLException {
	DefaultTableModel clearModel =(DefaultTableModel) w_tableBook.getModel();
	clearModel.setRowCount(0);
	for(int i = 0; i< librarian.getBookList().size(); i++) {
		bookData[0] = librarian.getBookList().get(i).getIdentitynumber();
		bookData[1] = librarian.getBookList().get(i).getBooksname();
		bookData[2] = librarian.getBookList().get(i).getBooksauthor();
		bookModel.addRow(bookData);
		
}
}

public void updateTypeModel() throws SQLException {
	DefaultTableModel clearModel = (DefaultTableModel) table_type.getModel();
	clearModel.setRowCount(0);
	for(int i=0; i< booktypes.getList().size(); i++) {
		typeData[0] = booktypes.getList().get(i).getId();
		typeData[1]= booktypes.getList().get(i).getName();
		typeModel.addRow(typeData);
	}
}
}