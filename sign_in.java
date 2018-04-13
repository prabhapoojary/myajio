import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.sql.SQLException;

import javax.crypto.NoSuchPaddingException;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.UIManager;

public class sign_in {
	JFrame f1;
	JLabel l1,l2,l3,l4;
	String username,password = null,emp_id;
	JTextField t1;
	JPasswordField p1;
	JButton b1,b2,b3;
	public  sign_in() {
		JDBC.ConnectDatabase();
		// TODO Auto-generated constructor stub
		f1=new JFrame("Sign in");
		f1.setLayout(null);
		f1.setVisible(true);
		f1.setSize(350, 350);

		l2=new JLabel("Sign in",JLabel.CENTER);
		l2.setFont(new Font("Serif",Font.BOLD,16));
		l2.setBounds(0,10,250,30);
		f1.add(l2);

		l3=new JLabel("USERNAME:");
		l3.setBounds(10,50,90,30);
		//l3.setFont(new Font("Serif",Font.BOLD,16));
		f1.add(l3);



		l4=new JLabel("PASSWORD");
		l4.setBounds(10,90,90,30);
		f1.add(l4);

		JLabel jl11 = new JLabel("TYPES");
		jl11.setBounds(10, 140, 90, 30);
		//jl11.setForeground(Color.BLUE);
		//	jl11.setFont(new Font("Brush Script MT",Font.CENTER_BASELINE,22));
		f1.add(jl11);

		p1=new JPasswordField();
		p1.setBounds(135,92,130,30);
		f1.add(p1);

		final JComboBox type_of_user = new JComboBox();

		type_of_user.setEditable(false);
		type_of_user.addItem("SELECT");
		type_of_user.addItem("OWNER");
		type_of_user.addItem("USER");
		type_of_user.setBounds(130, 130, 100, 30);
		f1.add(type_of_user);

		b1=new JButton("Sign in");
		b1.setBounds(50,200,100,40);
		f1.add(b1);

		t1=new JTextField();
		t1.setBounds(135,51,130,30);
		f1.add(t1);

		b2=new JButton("sign up");
		b2.setBounds(180,200,100,40);
		f1.add(b2);


		l1=new JLabel(new ImageIcon("images/pelatihan.png"));
		l1.setBounds(0,0,350,350);
		f1.add(l1);

		b1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e){
				String type=type_of_user.getSelectedItem().toString();

				username=t1.getText();
				if(type.equals("OWNER"))
				{
					if(JDBC.user_authenticate(t1.getText(),p1.getText()))
					{
//						String sec_key=initialize();
//						String get_key=JDBC.get_sec_key(username);
//						if(sec_key.equalsIgnoreCase(get_key))
//						{
							new fupload();
							JOptionPane.showMessageDialog(f1,"Wel come user"," ",JOptionPane.PLAIN_MESSAGE);
//						}
//						else
//						{
//							JOptionPane.showConfirmDialog(null,"KEY was wrong..!!!!!!"," ",JOptionPane.PLAIN_MESSAGE);
//						}
					}
					else
					{
						JOptionPane.showMessageDialog(null,"ERRORR"," USERNAME/PASSWORD FAILED",JOptionPane.PLAIN_MESSAGE);
					}
				}
				else
				{
					if(JDBC.user_authenticate(t1.getText(),p1.getText()))
					{
						f1.setVisible(false);
						try {
							new user();
						} catch (NoSuchAlgorithmException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						} catch (NoSuchPaddingException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						} catch (NoSuchProviderException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						JOptionPane.showMessageDialog(f1,"Wel come user"," ",JOptionPane.PLAIN_MESSAGE);

					}
					else
					{
						JOptionPane.showMessageDialog(null,"ERRORR"," USERNAME/PASSWORD FAILED",JOptionPane.PLAIN_MESSAGE);
					}

				}
			}});

		b2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e){
				f1.setVisible(false);
				new Ureg();
			}});
	}
//	private String initialize() {
//		
//		String key = JOptionPane.showInputDialog(null, "Enter your secret key");
//		return key;
//	}
	public static void main(String[] a){
		JFrame.setDefaultLookAndFeelDecorated(true);
		JDialog.setDefaultLookAndFeelDecorated(true);
		try
		{
			UIManager.setLookAndFeel("com.seaglasslookandfeel.SeaGlassLookAndFeel");
		}
		catch (Exception ex)
		{
			System.out.println("Failed loading L&F: ");
			System.out.println(ex);
		}
		new sign_in();

	}

}
