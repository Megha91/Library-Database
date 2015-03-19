package com.library.booksearch.ui;


import com.library.booksearch.dao.booksdao;
import com.library.booksearch.dao.borrowersdao;
import com.library.booksearch.dao.finesdao;
import com.library.booksearch.dao.loansdao;
import com.library.booksearch.core.*;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;

import java.awt.FlowLayout;

import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.JOptionPane;

import java.util.List;

import javax.swing.JTabbedPane;
import javax.swing.JScrollBar;

import java.awt.Color;

import javax.swing.JTextPane;
import javax.swing.JRadioButton;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;

import java.awt.Rectangle;

import javax.swing.border.SoftBevelBorder;
import javax.swing.border.BevelBorder;

import java.awt.Dimension;
import java.awt.Component;
import java.awt.Point;

import org.eclipse.wb.swing.FocusTraversalOnArray;

import java.awt.Font;
import java.awt.ComponentOrientation;

import javax.swing.border.MatteBorder;

import java.awt.Cursor;
import java.awt.GridLayout;

import javax.swing.JDesktopPane;
import javax.swing.border.LineBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.JInternalFrame;

import java.awt.SystemColor;

import javax.swing.JSplitPane;


public class BookSearch extends JFrame {

	private JPanel contentPane;

	private booksdao booksdao;
	private borrowersdao borrowersdao;
	private loansdao loansdao;
	private finesdao finesdao;
	private JTabbedPane tabbedPane;
	private JPanel panel;
	private JPanel panel_1;
	private JTable table;
	private JButton btnNewButton;
	private JScrollPane scrollPane_1;
	private JPanel panel_2;
	private JTextPane txtpnCardNo;
	private JTextPane txtpnPhone;
	private JPanel panel_3;
	private JTextPane txtpnFillTheForm;
	private JDesktopPane desktopPane;
	private JTextField fname;
	private JTextField lname;
	private JTextField address;
	private JTextField phone;
	private JPanel panel_4;
	private JLabel lblBookId;
	private JTextField book_Id;
	private JLabel lblBranchId;
	private JTextField Branch_Id;
	private JLabel lblCardNo;
	private JTextField card_No;
	private JButton btnDone;
	private JPanel panel_5;
	private JButton btnCheckin;
	private JTable table_1;
	private JTextField txtBookId;
	private JTextField txtAuthor;
	private JTextField txtTitle;
	private JTextField bookid;
	private JTextField author;
	private JTextField title;
	private JPanel Fines;
	private JPanel panel_7;
	private JTable table_2;
	private JLabel lblEnterCardid;
	private JTextField cardid;
	private JPanel panel_8;
	private JButton btnDisplayFine;
	private JPanel panel_9;
	private JLabel lblToDisplayAll;
	private JButton btnRefresh;
	private JButton btnPay;
	private JButton btnRefresh_1;
	private JLabel lblSelectAFine;
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					BookSearch frame = new BookSearch();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});

	
	}

	/**
	 * Create the frame.
	 */
	public BookSearch() {
		
		// create the DAO
		try {
			booksdao = new booksdao();
		} catch (Exception exc) {
			JOptionPane.showMessageDialog(this, "Error: " + exc, "Error", JOptionPane.ERROR_MESSAGE); 
		}
		try {
			borrowersdao = new borrowersdao();
		} catch (Exception exc) {
			JOptionPane.showMessageDialog(this, "Error: " + exc, "Error", JOptionPane.ERROR_MESSAGE); 
		}
		
		setTitle("LIBRARY MANAGEMENT SYSTEM");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 533, 547);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(0, 0, 153));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBackground(new Color(0, 102, 153));
		tabbedPane.setForeground(Color.DARK_GRAY);
		//tabbedPane.setToolTipText("Book Search");
		contentPane.add(tabbedPane, BorderLayout.CENTER);
		
		panel = new JPanel();
		panel.setBackground(SystemColor.activeCaption);
		FlowLayout flowLayout = (FlowLayout) panel.getLayout();
		flowLayout.setVgap(15);
		flowLayout.setHgap(15);
		flowLayout.setAlignOnBaseline(true);
		panel.setToolTipText("");
		tabbedPane.addTab("Book Search", null, panel, null);
		tabbedPane.setBackgroundAt(0, Color.GRAY);
		
		
		txtBookId = new JTextField();
		txtBookId.setEditable(false);
		txtBookId.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
		txtBookId.setBorder(null);
		txtBookId.setBackground(SystemColor.activeCaption);
		txtBookId.setText("Book Id");
		panel.add(txtBookId);
		txtBookId.setColumns(6);
		
		bookid = new JTextField();
		panel.add(bookid);
		bookid.setColumns(10);
		
		txtAuthor = new JTextField();
		txtAuthor.setEditable(false);
		txtAuthor.setBorder(null);
		txtAuthor.setBackground(SystemColor.activeCaption);
		txtAuthor.setText("Author");
		panel.add(txtAuthor);
		txtAuthor.setColumns(6);
		
		author = new JTextField();
		panel.add(author);
		author.setColumns(10);
		
		txtTitle = new JTextField();
		txtTitle.setEditable(false);
		txtTitle.setBorder(null);
		txtTitle.setBackground(SystemColor.activeCaption);
		txtTitle.setText("Title");
		panel.add(txtTitle);
		txtTitle.setColumns(6);
		title = new JTextField();
		panel.add(title);
		title.setColumns(10);
		
		
		btnNewButton = new JButton("Search");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try{
					String Book_id=bookid.getText();
					String Author=author.getText();
					String Title=title.getText();
					System.out.println(Book_id);
					System.out.println(Author);
					System.out.println(Title);
				List<Book> books = null;
				
				//if(combovalue!= null && comboBox.getSelectedItem().toString()=="Title")
				//{
					//System.out.println(combovalue);
				
				if (Book_id != null || Author!=null || Title!=null) {
					
					books = booksdao.searchBook(Book_id,Author,Title);
					System.out.println(books);
				} else {
					books = booksdao.getAllBooks();
					
				}
				BookTable model = new BookTable(books);
				
				table.setModel(model);

				//}
				} catch (Exception exc) {
					JOptionPane.showMessageDialog(BookSearch.this, "Error: " + exc, "Error", JOptionPane.ERROR_MESSAGE); 
				}
				
			}
		});
		
		panel.add(btnNewButton);
		
		
		
		panel_2 = new JPanel();
		panel_2.setLocation(new Point(10, 10));
		panel_2.setPreferredSize(new Dimension(1200, 500));
		panel_2.setForeground(new Color(153, 0, 51));
		panel_2.setMinimumSize(new Dimension(100, 100));
		panel_2.setBounds(new Rectangle(10, 10, 700, 300));
		panel_2.setBorder(null);
		panel_2.setBackground(SystemColor.activeCaption);
		FlowLayout fl_panel_2 = (FlowLayout) panel_2.getLayout();
		fl_panel_2.setVgap(20);
		fl_panel_2.setHgap(50);
		panel.add(panel_2);
		
		scrollPane_1 = new JScrollPane();
		scrollPane_1.setOpaque(false);
		scrollPane_1.setForeground(new Color(0, 204, 51));
		scrollPane_1.setSize(new Dimension(1000, 700));
		scrollPane_1.setLocation(new Point(25, 25));
		scrollPane_1.setPreferredSize(new Dimension(1100, 700));
		scrollPane_1.setBackground(new Color(0, 102, 204));
		scrollPane_1.setBounds(new Rectangle(10, 10, 700, 700));
		scrollPane_1.setAlignmentY(2.0f);
		scrollPane_1.setAlignmentX(Component.RIGHT_ALIGNMENT);
		scrollPane_1.setMinimumSize(new Dimension(300, 300));
		panel_2.add(scrollPane_1);
		
		table = new JTable();
		table.setForeground(new Color(255, 0, 0));
		table.setAlignmentY(5.0f);
		table.setSurrendersFocusOnKeystroke(true);
		table.setFillsViewportHeight(true);
		table.setIntercellSpacing(new Dimension(5, 5));
		table.setPreferredSize(new Dimension(500, 500));
		table.setSize(new Dimension(950, 700));
		table.setMinimumSize(new Dimension(600, 600));
		table.setBackground(SystemColor.window);
		scrollPane_1.setViewportView(table);
		table.setBounds(new Rectangle(10, 5, 700, 700));
		panel_2.setFocusTraversalPolicy(new FocusTraversalOnArray(new Component[]{table, scrollPane_1}));
		
		panel_1 = new JPanel();
		panel_1.setLocation(new Point(500, 500));
		panel_1.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
		panel_1.setBounds(new Rectangle(500, 500, 500, 500));
		panel_1.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		panel_1.setBackground(SystemColor.activeCaption);
		panel_1.setToolTipText("");
		tabbedPane.addTab("Add New Borrower", null, panel_1, null);
		FlowLayout fl_panel_1 = new FlowLayout(FlowLayout.CENTER, 5, 5);
		fl_panel_1.setAlignOnBaseline(true);
		panel_1.setLayout(fl_panel_1);
		
		panel_3 = new JPanel();
		panel_3.setBackground(SystemColor.activeCaption);
		panel_3.setPreferredSize(new Dimension(1300, 50));
		panel_3.setMinimumSize(new Dimension(100, 20));
		panel_1.add(panel_3);
		
		txtpnFillTheForm = new JTextPane();
		txtpnFillTheForm.setBackground(SystemColor.activeCaption);
		txtpnFillTheForm.setFont(new Font("Bookman Old Style", Font.BOLD, 20));
		txtpnFillTheForm.setText("FILL THE FORM TO ADD A NEW BORROWER");
		panel_3.add(txtpnFillTheForm);
		
		desktopPane = new JDesktopPane();
		desktopPane.setBackground(SystemColor.window);
		desktopPane.setPreferredSize(new Dimension(700, 500));
		desktopPane.setBorder(new LineBorder(new Color(0, 0, 0), 5));
		panel_1.add(desktopPane);
		
		JTextPane txtpnFname = new JTextPane();
		txtpnFname.setBackground(SystemColor.window);
		txtpnFname.setFont(new Font("Bookman Old Style", Font.BOLD, 18));
		txtpnFname.setText("FNAME :");
		txtpnFname.setBounds(230, 85, 150, 34);
		desktopPane.add(txtpnFname);
		
		JTextPane txtpnLname = new JTextPane();
		txtpnLname.setBackground(SystemColor.window);
		txtpnLname.setFont(new Font("Bookman Old Style", Font.BOLD, 18));
		txtpnLname.setText("LNAME :");
		txtpnLname.setBounds(230, 130, 150, 34);
		desktopPane.add(txtpnLname);
		
		JTextPane txtpnAddress = new JTextPane();
		txtpnAddress.setBackground(SystemColor.window);
		txtpnAddress.setFont(new Font("Bookman Old Style", Font.BOLD, 18));
		txtpnAddress.setText("ADDRESS :");
		txtpnAddress.setBounds(205, 173, 175, 34);
		desktopPane.add(txtpnAddress);
		
		JTextPane txtpnPhone_1 = new JTextPane();
		txtpnPhone_1.setBackground(SystemColor.window);
		txtpnPhone_1.setFont(new Font("Bookman Old Style", Font.BOLD, 18));
		txtpnPhone_1.setText("PHONE :");
		txtpnPhone_1.setBounds(230, 218, 150, 34);
		desktopPane.add(txtpnPhone_1);
		
		txtpnCardNo = new JTextPane();
		txtpnCardNo.setBackground(new Color(30, 144, 255));
		txtpnCardNo.setFont(new Font("Bookman Old Style", Font.BOLD, 15));
		txtpnCardNo.setText("Card No");
		
		fname = new JTextField();
		fname.setBounds(381, 85, 137, 26);
		desktopPane.add(fname);
		fname.setColumns(10);
		
		lname = new JTextField();
		lname.setBounds(381, 130, 137, 26);
		desktopPane.add(lname);
		lname.setColumns(10);
		
		address = new JTextField();
		address.setBounds(381, 175, 137, 26);
		desktopPane.add(address);
		address.setColumns(10);
		
		phone = new JTextField();
		phone.setBounds(381, 214, 137, 26);
		desktopPane.add(phone);
		phone.setColumns(10);
		
		JButton submit = new JButton("SUBMIT");
		submit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try{
					
					String firstname=fname.getText();
					String lastname=lname.getText();
					String add=address.getText();
					String phoneno=phone.getText();
				//	List<Borrower> borrowers = null;
					
					if (firstname.equals("")){  //User have not entered anything. 
						 JOptionPane.showMessageDialog(null,"Please enter your name.");
						    //firstname.requestFocusInWindow();
					}
					else if(lastname.equals("")){  //User have not entered anything. 
						 JOptionPane.showMessageDialog(null,"Please enter your last name.");
					
					}
					else if(add.equals("")){  //User have not entered anything. 
						 JOptionPane.showMessageDialog(null,"Please enter your address.");
					}	
					
					else
					{ 		
					borrowersdao.addBorrower(firstname,lastname,add, phoneno);
				
			
					}
				} catch (Exception exc) {
					JOptionPane.showMessageDialog(BookSearch.this, "Error: " + exc, "Error", JOptionPane.ERROR_MESSAGE); 
				}
				
			}
		});
		
		submit.setFont(new Font("Bookman Old Style", Font.BOLD, 20));
		submit.setBounds(295, 282, 123, 34);
		desktopPane.add(submit);
		
		panel_4 = new JPanel();
		panel_4.setBackground(SystemColor.activeCaption);
		tabbedPane.addTab("Book Loans", null, panel_4, null);
		tabbedPane.setForegroundAt(2, SystemColor.desktop);
		tabbedPane.setBackgroundAt(2, SystemColor.activeCaption);
		panel_4.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		lblBookId = new JLabel("Book Id");
		panel_4.add(lblBookId);
		
		book_Id = new JTextField();
		panel_4.add(book_Id);
		book_Id.setColumns(10);
		
		lblBranchId = new JLabel("Branch Id");
		panel_4.add(lblBranchId);
		
		Branch_Id = new JTextField();
		panel_4.add(Branch_Id);
		Branch_Id.setColumns(10);
		
		lblCardNo = new JLabel("Card No");
		panel_4.add(lblCardNo);
		
		card_No = new JTextField();
		panel_4.add(card_No);
		card_No.setColumns(10);
		
		panel_5 = new JPanel();
		panel_5.setBackground(SystemColor.activeCaption);
		panel_5.setPreferredSize(new Dimension(1200, 35));
		panel_4.add(panel_5);
		
		btnDone = new JButton("Checkout");
		panel_5.add(btnDone);
		btnDone.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try{
					loansdao loansdao=new loansdao();
					String Book_id=book_Id.getText();
					String Branch_id=Branch_Id.getText();
					String card_no=card_No.getText();
					int Card_no=Integer.parseInt(card_no);
				
				List<Loans> loans = null;
				
				if (Book_id != null && Branch_id !=null) {
				loansdao.addLoan(Book_id,Branch_id,Card_no);
				JOptionPane.showMessageDialog(null,"Book borrowed successfully");
				} else {
					JOptionPane.showMessageDialog(null,"Please enter valid details");
					
				}
				
				} catch (Exception exc) {
					JOptionPane.showMessageDialog(BookSearch.this, "Error: " + exc, "Error", JOptionPane.ERROR_MESSAGE); 
				}
				
			}
		});
		
		btnCheckin = new JButton("Check In");
		panel_5.add(btnCheckin);	
		btnCheckin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			
				try{
					loansdao loansdao=new loansdao();
					String Book_id=book_Id.getText();
					String Branch_id=Branch_Id.getText();
					String card_no=card_No.getText();
					int Card_no=Integer.parseInt(card_no);
				
				List<Loans> loans = null;
				
				if (Book_id != null && Branch_id !=null) {
				loans=loansdao.returnBook(Book_id,Branch_id,Card_no);
				
				System.out.println("loop entered");
				
				} else {
					JOptionPane.showMessageDialog(null,"Please enter valid details");
					
				}
				LoansTable model = new LoansTable(loans);
				
				table_1.setModel(model);
				
				//}
				} catch (Exception exc) {
					JOptionPane.showMessageDialog(BookSearch.this, "Error: " + exc, "Error", JOptionPane.ERROR_MESSAGE); 
				}
				
			}
		});
		JPanel panel_6 = new JPanel();
		panel_6.setSize(new Dimension(300, 300));
		panel_6.setMinimumSize(new Dimension(200, 200));
		panel_6.setBackground(SystemColor.controlShadow);
		panel_6.setPreferredSize(new Dimension(600, 500));
		panel_4.add(panel_6);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setPreferredSize(new Dimension(600, 500));
		panel_6.add(scrollPane);
		
		table_1 = new JTable();
		scrollPane.setColumnHeaderView(table_1);
		txtpnPhone = new JTextPane();
		txtpnPhone.setBackground(new Color(30, 144, 255));
		
		/*FINES	 */
		
		
		Fines = new JPanel();
		tabbedPane.addTab("Fines", null, Fines, null);
		
		panel_7 = new JPanel();
		panel_7.setBackground(SystemColor.activeCaption);
		panel_7.setPreferredSize(new Dimension(500, 500));
		Fines.add(panel_7);
		
		panel_9 = new JPanel();
		panel_9.setBackground(SystemColor.activeCaption);
		panel_9.setPreferredSize(new Dimension(500, 30));
		panel_7.add(panel_9);
		
		lblToDisplayAll = new JLabel("click to refresh");
		panel_9.add(lblToDisplayAll);
		
		
		
				
		lblEnterCardid = new JLabel("Enter Card_Id ");
		panel_7.add(lblEnterCardid);
		
		cardid = new JTextField();
		panel_7.add(cardid);
		cardid.setColumns(10);
		
		btnRefresh_1 = new JButton("Refresh");
		panel_9.add(btnRefresh_1);
		btnRefresh_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try{
					
				//List<Book> books = null;
				finesdao finesdao=new finesdao();
				
				List<Fines> fines = null;
				
			
				finesdao.RefreshFines();
				System.out.println("refreshed");	
				
			
			
				
			}
			 catch (Exception exc) {
					JOptionPane.showMessageDialog(BookSearch.this, "Error: " + exc, "Error", JOptionPane.ERROR_MESSAGE); 
				}
				
			}
		});

		
		btnDisplayFine = new JButton("Display Fine");
		panel_7.add(btnDisplayFine);
	
		
		btnDisplayFine.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try{
					String Card_no=cardid.getText();
					if (Card_no.equals("")){  //User have not entered anything. 
						 JOptionPane.showMessageDialog(null,"Please enter card no.");
						    //firstname.requestFocusInWindow();
					}else
					{
					int card_no=Integer.parseInt(Card_no);
					finesdao finesdao=new finesdao();
					
					List<Fines> fines = null;
				
					fines = finesdao.getAllFines(card_no);
					
				
					FinesTable model3 = new FinesTable(fines);
				
					table_2.setModel(model3);
					ListSelectionModel cellSelectionModel = table_2.getSelectionModel();

				
				cellSelectionModel.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

				cellSelectionModel.addListSelectionListener(new ListSelectionListener() {

							public void valueChanged(ListSelectionEvent e) {

								int selectedRow;
								selectedRow = table_2.getSelectedRow();

								
								int selectedLoanid = 0;
								selectedLoanid = ((int) table_2.getValueAt(selectedRow, 0));
								boolean finepaid=false;
								finepaid=(boolean) table_2.getValueAt(selectedRow, 2);
								//System.out.println(selectedLoanid);
								
								if(finepaid==false){
								finesdao finesdao;
								try {
									finesdao = new finesdao();
									
									
									finesdao.PayFine(selectedLoanid);
							
										JOptionPane.showMessageDialog(null,"Fine paid");
									} 
								catch (Exception e1) {
									// TODO Auto-generated catch block
									e1.printStackTrace();
								}
								}else
									
									JOptionPane.showMessageDialog(null,"Fine already paid");
									
							
							}
						});
					}
					}
			
				
				catch (Exception exc) {
					JOptionPane.showMessageDialog(BookSearch.this, "Error: " + exc, "Error", JOptionPane.ERROR_MESSAGE); 
				}
				
			}
		});
		
		lblSelectAFine = new JLabel("Select a fine to pay");
		lblSelectAFine.setPreferredSize(new Dimension(300, 14));
		panel_7.add(lblSelectAFine);
		 
		
		panel_8 = new JPanel();
		panel_8.setPreferredSize(new Dimension(400, 400));
		panel_7.add(panel_8);
		
	/*	btnPay = new JButton("Pay");
	panel_8.add(btnPay);
		btnPay.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
				String Card_no=cardid.getText();
				int card_no=Integer.parseInt(Card_no);
				finesdao finesdao;
				
					finesdao = new finesdao();
				
				
			List<Fines> fines = null;
			
			fines = finesdao.getAllFines(card_no);
				
			
			FinesTable model3 = new FinesTable(fines);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				}
		}
		);
		*/
		table_2 = new JTable();
		table_2.setPreferredSize(new Dimension(400, 200));
		panel_8.add(table_2);
		
		
		
		
        }
			}
