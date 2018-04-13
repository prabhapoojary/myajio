

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Random;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.text.JTextComponent;




public class Ureg {
	JFrame f1;
	JLabel l1,l2,l3,l4,l5,l6,l7,l8;
	JTextField t2,t3,t5,t6;
	JPasswordField t4,t1;
	JButton b1,b2;
	
	boolean valid,valid1;
	String key="";
	String[] rand={"1","2","3","4","5","6","7","8","9","0","a","b","c","d","e","q","t","y","u","i","o","p",
			"s","f","g","h","j","k","l","z","x","v","n","m","A","B","C","D","E","Q","T","Y","U","I","O","P"
			,"S","F","G","H","J","K","L","Z","X","V","N","M"};


	public Ureg() {

		JDBC.ConnectDatabase();

		f1=new JFrame(" User details");
		f1.setLayout(null);

		l1=new JLabel("Name");
		l1.setFont(new Font("Serif",Font.BOLD,14));
		l1.setBounds(20,60,100,20);
		f1.add(l1);

		l2=new JLabel("User name");
		l2.setFont(new Font("Serif",Font.BOLD,14));
		l2.setBounds(20,95,120,20);
		f1.add(l2);

		t3=new JTextField();
		t3.setBounds(150,97,150,20);
		f1.add(t3);

		l3=new JLabel("Password");
		l3.setFont(new Font("Serif",Font.BOLD,14));
		l3.setBounds(20,130,120,20);
		f1.add(l3);

		t2=new JTextField(5);
		t2.setBounds(150,62,150,20);
		f1.add(t2);

		t4=new JPasswordField();
		t4.setBounds(150,132,150,20);
		f1.add(t4);

		l4=new JLabel("Confirm password");
		l4.setFont(new Font("Serif",Font.BOLD,14));
		l4.setBounds(20,165,120,20);
		f1.add(l4);

		t1=new JPasswordField();
		t1.setBounds(150,167,150,20);
		f1.add(t1);

		l5=new JLabel("Email id");
		l5.setFont(new Font("Serif",Font.BOLD,14));
		l5.setBounds(20,200,120,20);
		f1.add(l5);

		t5=new JTextField();
		t5.setBounds(150,202,150,20);
		f1.add(t5);

		l6=new JLabel("Phone no.");
		l6.setFont(new Font("Serif",Font.BOLD,14));
		l6.setBounds(20,235,120,20);
		f1.add(l6);

		t6=new JTextField();
		t6.setBounds(150,237,150,20);
		f1.add(t6);

		l8=new JLabel("User details",JLabel.CENTER);
		l8.setFont(new Font("Serif",Font.BOLD,20));
		l8.setBounds(0,20,350,20);
		f1.add(l8);

		b1=new JButton("Sign up");
		b1.setBounds(30,300,100,20);
		f1.add(b1);

		b2=new JButton("Clear");
		b2.setBounds(190,300,100,20);
		f1.add(b2);

		l7=new JLabel(new ImageIcon("images/ureg.jpg"));
		l7.setBounds(0,0,350,400);
		f1.add(l7);

		f1.setVisible(true);
		f1.setSize(350, 400);

		b2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e){
				t1.setText(null);
				t3.setText(null);
				t4.setText(null);
				t5.setText(null);
				t6.setText(null);
			}});

		b1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e){
				Random randomGenerator = new Random();
				for (int idx = 1; idx <= 20; idx++){
					int randomInt = randomGenerator.nextInt(58);
			  	key+=rand[randomInt];
				}
				
				if(valid && valid1){
					if(t4.getText().equals(t1.getText()))
					{
						String user=t3.getText(),pass=t1.getText(),name=t2.getText(),ph=t6.getText(),email=t5.getText();
					
						JDBC.update(name,user,pass,email,ph);
						
						f1.setVisible(false);
						//new Mail(key,email);

						new sign_in();
					}
					else
					{
						Component fram=null;
						JOptionPane.showMessageDialog(fram,"password/conform did't match "," ",JOptionPane.WARNING_MESSAGE);
					}
				}
			}});

		FocusAdapter fl = new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent fe) {
				super.focusGained(fe);
				((JTextComponent) fe.getSource()).setBackground(new Color(175,238,238));
			}

			@Override
			public void focusLost(FocusEvent fe) {
				super.focusLost(fe);
				if (fe.getSource().equals(t6)) {
					validationForNumber(t6);
				}
				else if(fe.getSource().equals(t3))
					validateUser(t3);
				else if(fe.getSource().equals(t2))
					validateUserName(t2);
				else
					validationForEmail(t5);
			}};

			t6.addFocusListener(fl);
			t3.addFocusListener(fl);
			t5.addFocusListener(fl);
			t2.addFocusListener(fl);
	}
	public void validationForEmail(JTextComponent comp) {
		String text = comp.getText();
		if (text.matches("[^@]+@([^.]+\\.)+[^.]+")) {
			if (JDBC.email(text)) {
				setGreen(comp);
				setGreen(comp);
				valid=true;
			} 
			else 
			{
				JOptionPane.showMessageDialog(f1,"username Exists"," ",JOptionPane.INFORMATION_MESSAGE);
				comp.setText(null);
				setRed(comp);
				valid=false;
			}
		} 
		else 
		{
			setRed(comp);
			valid=false;
		}
	}
	public void validateUser(JTextComponent comp) {
		String u_name = comp.getText();
		if (JDBC.user_available(u_name)) {
			setGreen(comp);
			valid1= true;
		} 
		else {
			JOptionPane.showMessageDialog(f1,"username Exists"," ",JOptionPane.INFORMATION_MESSAGE);
			comp.setText(null);
			setRed(comp);
			valid1=false;
		}
	}

	public void validationForNumber(JTextComponent comp) {
		String text = comp.getText();
		if (text.matches("[0-9]{10}$")) {
			setGreen(comp);
			valid= true;
		} else {
			comp.setText(null);
			setRed(comp);
			valid=false;
			JOptionPane.showMessageDialog(f1,"Enter valid Phone Number"," ",JOptionPane.INFORMATION_MESSAGE);
		}
	}
	public void validateUserName(JTextComponent comp) {
		String text = comp.getText();
		if (text.matches("^[A-Za-z]{3,20}$")) {
			setGreen(comp);
			valid= true;
		} else {
			comp.setText(null);
			setRed(comp);
			valid=false;
			JOptionPane.showMessageDialog(f1,"Enter minimum 3 and maximum 20 Character"," ",JOptionPane.INFORMATION_MESSAGE);
		}
	}
	private void setRed(JTextComponent comp) {
		comp.setBackground(Color.RED);
	}

	private void setGreen(JTextComponent comp) {
		comp.setBackground(Color.GREEN);
	}

	public static void main(String[] a){
		new Ureg();
	}

	
	

}
