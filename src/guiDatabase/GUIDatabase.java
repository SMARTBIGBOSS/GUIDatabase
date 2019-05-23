package guiDatabase;

import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import java.io.*;
import java.sql.Connection;
import java.util.Arrays;

public class GUIDatabase extends JFrame implements ActionListener{
	int i = 0;
	String[][] rs = null;
	
	JLabel jLabel_title;
	JLabel jLabel_Id;
	JLabel jLabel_FName;
	JLabel jLabel_LName;
	JLabel jLabel_Email;
	JLabel jLabel_tol;
	JLabel jLabel_msg;
	
	JTextField TxtId;
	JTextField TxtFName;
	JTextField TxtLName;
	JTextField TxtEmail;
	
	JButton BtnAdd;
	JButton BtnUpdate;
	JButton BtnDelete;
	JButton BtnPrevious;
	JButton BtnNext;
	JButton BtnClear;
	JButton BtnSearch;
	JButton BtnSearchAll;
	
	public GUIDatabase() {
		//Custom color
		Color btnColor = new Color(123,104,238);
		Color labelColor = new Color(205,133,63);
		//Custom font
		Font small = new Font("Dialog",1,12); 
		Font large = new Font("Dialog",1,25); 
		Font middle = new Font("Dialog",1,18); 
		
		jLabel_title = new JLabel("Student Information");
		jLabel_Id = new JLabel("Student Number: ");
		jLabel_FName = new JLabel("First Name: ");
		jLabel_LName = new JLabel("Last Name: ");
		jLabel_Email = new JLabel("Email Address: ");
		jLabel_tol = new JLabel("");
		jLabel_msg = new JLabel("");
		
		TxtId = new JTextField();
		TxtFName = new JTextField();
		TxtLName = new JTextField();
		TxtEmail = new JTextField();
		
		BtnAdd = new JButton("Add");
		BtnUpdate = new JButton("Update");
		BtnDelete = new JButton("Delete");
		BtnSearchAll = new JButton("Search All");
		BtnSearch = new JButton("Search");
		BtnPrevious = new JButton("Previous");
		BtnNext = new JButton("Next");
		BtnClear = new JButton("Clear");
		
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		 this.setLayout(null);
		 this.setSize(800,500);
		 
		 this.add(jLabel_title);
		 jLabel_title.setBounds(230, 50, 250, 40);
		 jLabel_title.setForeground(Color.black);
		 jLabel_title.setFont(large);
		 
		 this.add(jLabel_Id);
		 jLabel_Id.setBounds(50, 100, 150, 40);
		 jLabel_Id.setForeground(labelColor);
		 jLabel_Id.setFont(small);
		 
		 this.add(jLabel_FName);
		 jLabel_FName.setBounds(50, 150, 150, 40);
		 jLabel_FName.setForeground(labelColor);
		 jLabel_FName.setFont(small);
		 
		 this.add(jLabel_LName);
		 jLabel_LName.setBounds(50, 200, 150, 40);
		 jLabel_LName.setForeground(labelColor);
		 jLabel_LName.setFont(small);
		 
		 this.add(jLabel_Email);
		 jLabel_Email.setBounds(50, 250, 150, 40);
		 jLabel_Email.setForeground(labelColor);
		 jLabel_Email.setFont(small);
		 
		 this.add(jLabel_tol);
		 jLabel_tol.setBounds(500, 290, 50, 40);
		 jLabel_tol.setForeground(Color.DARK_GRAY);
		 jLabel_tol.setFont(middle);
		 
		 this.add(jLabel_msg);
		 jLabel_msg.setBounds(50, 370, 200, 50);
		 jLabel_msg.setForeground(Color.red);
		 jLabel_msg.setFont(middle);
		 
		 this.add(TxtId);
		 TxtId.setBounds(210, 100, 300, 40);
		 
		 this.add(TxtFName);
		 TxtFName.setBounds(210, 150, 300, 40);
		 
		 this.add(TxtLName);
		 TxtLName.setBounds(210, 200, 300, 40);
		 
		 this.add(TxtEmail);
		 TxtEmail.setBounds(210, 250, 300, 40);
		 
		 this.add(BtnAdd);
		 BtnAdd.setBounds(50,320,100,50);
		 BtnAdd.addActionListener(this);
		 BtnAdd.setBackground(btnColor);
		 BtnAdd.setForeground(Color.white);
		 BtnAdd.setFont(small);
		 
		 this.add(BtnUpdate);
		 BtnUpdate.setBounds(160,320,100,50);
		 BtnUpdate.addActionListener(this);
		 BtnUpdate.setBackground(btnColor);
		 BtnUpdate.setForeground(Color.white);
		 BtnUpdate.setFont(small);
		 
		 this.add(BtnDelete);
		 BtnDelete.setBounds(270,320,100,50);
		 BtnDelete.addActionListener(this);
		 BtnDelete.setBackground(btnColor);
		 BtnDelete.setForeground(Color.white);
		 BtnDelete.setFont(small);
		 
		 this.add(BtnClear);
		 BtnClear.setBounds(380,320,100,50);
		 BtnClear.addActionListener(this);
		 BtnClear.setBackground(btnColor);
		 BtnClear.setForeground(Color.white);
		 BtnClear.setFont(small);
		 
		 this.add(BtnSearchAll);
		 BtnSearchAll.setBounds(550,100,100,50);
		 BtnSearchAll.addActionListener(this);
		 BtnSearchAll.setBackground(btnColor);
		 BtnSearchAll.setForeground(Color.white);
		 BtnSearchAll.setFont(small);
		 
		 this.add(BtnSearch);
		 BtnSearch.setBounds(550,160,100,50);
		 BtnSearch.addActionListener(this);
		 BtnSearch.setBackground(btnColor);
		 BtnSearch.setForeground(Color.white);
		 BtnSearch.setFont(small);
		 
		 this.add(BtnPrevious);
		 BtnPrevious.setBounds(550,220,100,50);
		 BtnPrevious.addActionListener(this);
		 BtnPrevious.setBackground(btnColor);
		 BtnPrevious.setForeground(Color.white);
		 BtnPrevious.setFont(small);
		 
		 this.add(BtnNext);
		 BtnNext.setBounds(550,280,100,50);
		 BtnNext.addActionListener(this);
		 BtnNext.setBackground(btnColor);
		 BtnNext.setForeground(Color.white);
		 BtnNext.setFont(small);
		 
		 this.setVisible(true);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
		JdbcOperation jdbc = new JdbcOperation();
		Connection con = jdbc.connectDB();	
		
		if(e.getSource() == BtnAdd) {
			String str = jdbc.addContent(con, TxtId.getText(), TxtFName.getText(), TxtLName.getText(), TxtEmail.getText());
			jLabel_msg.setText(str);
			jLabel_tol.setText("");
			
		}else if(e.getSource() == BtnUpdate) {
			String str = jdbc.updateContent(con, TxtId.getText(), TxtFName.getText(), TxtLName.getText(), TxtEmail.getText());
			jLabel_msg.setText(str);
			jLabel_tol.setText("");
			
		}else if(e.getSource() == BtnDelete) {
			String str = jdbc.deleteContent(con, TxtId.getText());
			jLabel_msg.setText(str);
			jLabel_tol.setText("");
			
		}else if(e.getSource() == BtnClear) {
			TxtId.setText(null);
			TxtFName.setText(null);
			TxtLName.setText(null);
			TxtEmail.setText(null);
			jLabel_msg.setText("");
			jLabel_tol.setText("");
			
		}else if(e.getSource() == BtnSearchAll) {
			jLabel_msg.setText("");
			i = 0;
			int index = i + 1; 
			rs = jdbc.retrieveAll(con);
			TxtId.setText(rs[i][0]);
			TxtFName.setText(rs[i][1]);
			TxtLName.setText(rs[i][2]);
			TxtEmail.setText(rs[i][3]);
			jLabel_tol.setText(index + "/" + rs.length);
			
		}else if(e.getSource() == BtnSearch) {
			jLabel_msg.setText("");
			rs = null;
			i = 0;
			int index = i + 1; 
			if(!TxtId.getText().trim().equals("")) {
				rs = jdbc.retrieveById(con, TxtId.getText());
			}else if(!TxtFName.getText().trim().equals("")) {
				rs = jdbc.retrieveByFName(con, TxtFName.getText());
			} else if(!TxtLName.getText().trim().equals("")) {
				rs = jdbc.retrieveByLName(con, TxtLName.getText());
			} else if(!TxtEmail.getText().trim().equals("")) {
				rs = jdbc.retrieveByEmail(con, TxtEmail.getText());
			}
			if(rs[0].length > 1) {
				TxtId.setText(rs[i][0]);
				TxtFName.setText(rs[i][1]);
				TxtLName.setText(rs[i][2]);
				TxtEmail.setText(rs[i][3]);
				jLabel_tol.setText(index + "/" + rs.length);
			} else {
				jLabel_msg.setText(rs[0][0]);
			}

			
		}else if(e.getSource() == BtnPrevious) {
			jLabel_msg.setText("");
			if(i > 0) {
				i --;
				System.out.println(i);
				int index = i + 1;
				TxtId.setText(rs[i][0]);
				TxtFName.setText(rs[i][1]);
				TxtLName.setText(rs[i][2]);
				TxtEmail.setText(rs[i][3]);	
				jLabel_tol.setText(index + "/" + rs.length);	
			}else {
				jLabel_msg.setText("This is the first data");
			}
			
		}else if(e.getSource() == BtnNext) {
			jLabel_msg.setText("");
			if(i < rs.length-1) {
				i ++;
				System.out.println(i);
				int index = i + 1;
				TxtId.setText(rs[i][0]);
				TxtFName.setText(rs[i][1]);
				TxtLName.setText(rs[i][2]);
				TxtEmail.setText(rs[i][3]);	
				jLabel_tol.setText(index + "/" + rs.length);

			}else {
				jLabel_msg.setText("This is the last data");
			}
		}
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		 GUIDatabase demo=new GUIDatabase();
	 	//Custom background color
		 Color customColor = new Color(240,255,240);
		 demo.getContentPane().setBackground(customColor);
	}

}
