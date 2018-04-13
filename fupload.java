import javax.swing.*;

import java.awt.event.*;
import java.awt.*;
import java.sql.*;

import javax.swing.border.TitledBorder;

import java.io.*;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.PrivateKey;
import java.security.PublicKey;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

import org.apache.commons.codec.binary.Hex;
import org.apache.poi.hssf.record.formula.functions.Count;
import org.apache.poi.hwpf.HWPFDocument;
import org.apache.poi.hwpf.extractor.WordExtractor;
import org.pdfbox.cos.COSDocument;
import org.pdfbox.pdfparser.PDFParser;
import org.pdfbox.pdmodel.PDDocument;
import org.pdfbox.pdmodel.PDDocumentInformation;
import org.pdfbox.util.PDFTextStripper;
import org.apache.commons.io.*;

import java.security.spec.X509EncodedKeySpec;
import java.security.KeyFactory;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.StringTokenizer;
import java.util.Map.Entry;
import java.util.List;

import sun.misc.BASE64Encoder;

import java.math.BigInteger;
import java.net.*;

public class fupload implements ActionListener
{
	JFrame jf;
	JLabel l1,l2,l3,l4,e5,e6;

	JLabel j1, j2, j3, j4, j5, j6, j7, j8, j9, j10, j11;
	JPanel jp, jp1, jp2;
	JTextField jt1, jt3, jt4, jt6, jt5;
	static JTextField jt7;
	JTextField jt8, jt9, jt2, jt10;
	JButton jb, jb1, jb2, jb3, jb4,jb5;
	JTextArea jta, jta1, jta2, jta3;
	JScrollPane jsp, jsp1, jsp2, jsp3;
	String file_data, file_name;
	String email, key, name;
	static	 Connection con;
	int bitlength = 3;
	public static String pubKeyStr;
	public static String privateKeyStr;
	byte[] publicKeyBytes;
	private static final String INDEX_DIR = "C:\\Users\\sys\\Desktop\\221116\\MRSE\\Indx";

	static String extension,encmsg;
	int port = 5000;
	PreparedStatement ps;
	static String sf;
	byte[] privateKeyBytes;

	public static String path;
	public static String aString;
	byte[] pubBytes, priBytes;
	public Cipher cipher = null;
	public KeyPair keyPair;
	static private PrivateKey privateKey;
	static private PublicKey publicKey;

	Socket	sock;
	PDFParser parser;
	String parsedText;
	PDFTextStripper pdfStripper;
	PDDocument pdDoc;
	COSDocument cosDoc;
	PDDocumentInformation pdDocInfo;

	private final String privateKeyPathName =  "C:\\Users\\sys\\Desktop\\221116\\MRSE\\temp\\";
	private final String publicKeyPathName = "C:\\Users\\sys\\Desktop\\221116\\MRSE\\temp\\";


	public final PrivateKey getPrivateKey() 
	{
		return privateKey;
	}

	public final PublicKey getPublicKey() {
		return publicKey;
	}

	public final void toFileSystem(String privateKeyPathName,
			String publicKeyPathName) throws IOException {
	}

	public fupload()
	{
		try{

			init();

		}catch(Exception e3){}
		jf=new JFrame("Upload");
		jf.setLayout(null);
		//Container cp=jf.getContentPane();
		//cp.setBackground(new Color(0x708090));

		l3=new JLabel("");
		l3.setBounds(80,20,600,30);
		l3.setForeground(Color.black);    
		l3.setFont(new Font("Times New Roman",Font.BOLD|Font.ITALIC,28));
		jf.add(l3);

		l4=new JLabel("");
		l4.setBounds(10,60,500,30);
		l4.setForeground(Color.black);
		l4.setFont(new Font("Times New Roman",Font.BOLD|Font.ITALIC,28));    
		jf.add(l4);


		l2 = new JLabel("Filename");
		l2.setBounds(85,130,280,20);
		l2.setFont(new Font("Times New Roman",Font.BOLD|Font.ITALIC,20));    
		l2.setForeground(Color.black);
		jf.add(l2);

		jt1=new JTextField();
		jt1.setBounds(185,130,100,20);
		jf.add(jt1);

		jb=new JButton("browse");  
		jb.setBounds(290,130,90,20);
		jb.addActionListener(this);
		jf.add(jb);

		jta1 = new JTextArea();
		jsp1 = new JScrollPane(jta1);
		jsp1.setBounds(450,90,200,150);
		jf.add(jsp1);



		e5 = new JLabel("Keyword");
		e5.setBounds(85,160,280,20);
		e5.setFont(new Font("Times New Roman",Font.BOLD|Font.ITALIC,20));    
		e5.setForeground(Color.black);
		jf.add(e5);

		jt2 = new JTextField();
		jt2.setFont(new Font("Times New Roman", Font.BOLD, 14));
		jt2.setBackground(Color.white);
		jt2.setForeground(Color.black);
		jt2.setBounds(185, 160, 100, 20);
		jt2.setLayout(null);
		jf.add(jt2);

		jb4 = new JButton("extract");
		jb4.setFont(new Font("Times New Roman", Font.BOLD, 14));
		jb4.setBackground(Color.white);
		jb4.setForeground(Color.black);
		jb4.setBounds(290, 160, 100, 20);
		jb4.setLayout(null);
		jb4.addActionListener(this);
		jf.add(jb4);

		j9 = new JLabel("Public key :");
		j9.setFont(new Font("Times New Roman", Font.BOLD, 14));
		j9.setBackground(Color.BLACK);
		j9.setForeground(Color.BLACK);
		j9.setBounds(140, 420, 120, 20);
		j9.setLayout(null);
		jf.add(j9);

		jt8 = new JTextField();
		jt8.setFont(new Font("Times New Roman", Font.BOLD, 14));
		jt8.setBackground(Color.white);
		jt8.setForeground(Color.black);
		jt8.setBounds(220, 420, 120, 20);
		jt8.setLayout(null);
		jf.add(jt8);

		j8 = new JLabel("secret key :");
		j8.setFont(new Font("Times New Roman", Font.BOLD, 14));
		j8.setBackground(Color.BLACK);
		j8.setForeground(Color.BLACK);
		j8.setBounds(140, 450, 120, 20);
		j8.setLayout(null);
		jf.add(j8);

		jt7 = new JTextField();
		jt7.setFont(new Font("Times New Roman", Font.BOLD, 14));
		jt7.setBackground(Color.white);
		jt7.setForeground(Color.black);
		jt7.setBounds(220, 450, 120, 20);
		jt7.setLayout(null);
		jf.add(jt7);

		e6=new JLabel();
		e6.setText("<html> <u>Back</u>  <a href=\"\"></a></html>");
		e6.setCursor(new Cursor(Cursor.HAND_CURSOR));
		e6.setBounds(50,375,100,20);
		e6.setFont(new Font("Times New Roman",Font.BOLD|Font.ITALIC,16));    
		e6.setForeground(Color.black);
		jf.add(e6);  


		e6.addMouseListener(new MouseAdapter() {

			public void mouseClicked(MouseEvent e) {
				try {
					jf.dispose();
					new sign_in();
				} catch (Exception  ex) {
					//It looks like there's a problem
				}
			}
		});





		jb2 = new JButton("Generate");
		jb2.setFont(new Font("Times New Roman", Font.BOLD, 14));
		jb2.setBackground(Color.white);
		jb2.setForeground(Color.black);
		jb2.setBounds(140, 500, 100, 20);
		jb2.addActionListener(this);
		jf.add(jb2);

		jb3 = new JButton("Encrypt and upload");
		jb3.setFont(new Font("Times New Roman", Font.BOLD, 14));
		jb3.setBackground(Color.white);
		jb3.setForeground(Color.black);
		jb3.setBounds(260, 500, 190, 20);

		jb3.addActionListener(this);
		jf.add(jb3);
		jb5= new JButton("Clear");
		jb5.setFont(new Font("Times New Roman",Font.BOLD,14));
		jb5.setBackground(Color.white);
		jb5.setForeground(Color.black);
		jb5.setBounds(490, 500, 100, 20);

		jb5.addActionListener(this);
		jf.add(jb5);
		jb5.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent ai) {
				if(ai.getSource()==jb5)
				{
					jt1.setText("");
					jt2.setText("");
					jta.setText("");
					jta1.setText("");

					jt7.setText("");
					jt8.setText("");


				}}

		});
		jta = new JTextArea();
		jsp = new JScrollPane(jta);
		jta.setBounds(160, 210, 260, 200);
		jsp.setBorder(new TitledBorder(""));

		jf.add(jta);


		jb1=new JButton("Upload");  
		jb1.addActionListener(this);
		jb1.setBounds(430,500,80,20);
		//jf.add(jb1);

		jb1.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {


				String pub=jt8.getText();
				String pri=jt7.getText();

				try{

					Class.forName("oracle.jdbc.driver.OracleDriver");  

					//step2 create  the connection object  
					Connection con=DriverManager.getConnection(  
							"jdbc:oracle:thin:@localhost:1521:xe","system","system");  
					System.out.println("connected to the server");
					PreparedStatement ps=con.prepareStatement("insert into cloud_key values(?,?)");
					ps.setString(1, pub);
					ps.setString(2, pri);

					ps.executeUpdate();

				}
				catch(Exception e1)
				{
					System.out.println(e1);
				}





			}
		});




		ImageIcon image1 = new ImageIcon("C:\\Users\\sys\\Desktop\\221116\\MRSE\\splash.png");
		l1 = new JLabel(image1);
		l1.setBounds(0,0,700,700);
		jf.add(l1);

		jf.setSize(700,600);
		jf.setVisible(true);

	}
	public void actionPerformed(ActionEvent ae){
		if(ae.getSource()==jb){
			try {//2

				JFileChooser fc = new JFileChooser();
				int option = fc.showOpenDialog(null);
				if (option == JFileChooser.APPROVE_OPTION) {//1


					sf = fc.getSelectedFile().getAbsolutePath();
					File fn1 = new File(sf);
					file_name = fn1.getName();
					jt1.setText(file_name);

					System.out.println(sf);
					path = sf;
					SimplePDFSearch ob = new SimplePDFSearch();
					System.out.println("File Name :-----" + path);

					extension = path.substring(path.lastIndexOf('.'));
					System.out.println("Extension  ::" + extension);
					if (extension.equalsIgnoreCase(".txt")) {
						/******** Text / Log File Encryption / Decryption Method Cal ***********************/
						String stxt = this.readtxtFile(path);
						jta1.setFont(new Font("Serif", Font.PLAIN, 18));
						jta1.setText(new String(stxt));
						this.setString(stxt);
						System.out.println("Original Text  :: " + stxt);

						//						util.hashing(stxt);

					}

					if (extension.equalsIgnoreCase(".rtf")) {
						String my = "";


						Reader reader = new InputStreamReader(
								new FileInputStream(path));
						BufferedReader fin = new BufferedReader(reader);
						String tempf;

						while ((tempf = fin.readLine()) != null) {
							my = my + tempf;
						}
						fin.close();

						System.out.println("Result2:=>" + my);



						jta1.setFont(new Font("Serif", Font.PLAIN, 18));
						jta1.setText(new String(my));
						this.setString(my);

						System.out.println("Original Word RTF    : " + my);


					}
					else if (extension.equalsIgnoreCase(".doc")) {

						String s = this.readDocFile(path);
						jta1.setFont(new Font("Serif", Font.PLAIN, 18));
						jta1.setText(new String(s));
						this.setString(s);
						System.out.println("Original Word Doc  :: " + s);

					} 
					else if (extension.equalsIgnoreCase(".pdf")) {




						jta1.setFont(new Font("Serif", Font.PLAIN, 18));
						String pageText = this.pdftoText(path);
						jta1.setText(new String(pageText));
						this.setString(pageText);
						System.out.println("Original  PDF    : " + pageText);

					}
					else {
						System.out.println(" File not found ....Please Check file extension ");
					}
				}//end of if option	

			} catch (Exception ee) {
				JOptionPane.showMessageDialog(null, "Reload File Again.."
						+ ee);
			}


		}

		if (ae.getSource() == jb4) {

			try {

				if (extension.equalsIgnoreCase(".rtf")) {
					String rtfkey = "";
					Map<String, Integer> wordMap = this.getWordCount(path);
					List<Entry<String, Integer>> list = this.sortByValue(wordMap);
					for (Map.Entry<String, Integer> entry : list) {
						if (entry.getValue() > 10) {
							// rtfkey=entry.getKey();
							Pattern p = Pattern.compile("[^a-z0-9 ]",
									Pattern.CASE_INSENSITIVE);
							// Pattern p =
							// Pattern.compile("[^a-z0-9\"\\.&,@!?#%'$()/\\\\ \\-_<>]+$",Pattern.CASE_INSENSITIVE);
							Matcher m = p.matcher(entry.getKey());
							//                            
							boolean b = m.find();
							if (b) {
								//
							} else {
								Pattern pattern = Pattern.compile(
										"[\"\\.&,@!?#%'$()/\\\\ \\-_<>]+$",
										Pattern.CASE_INSENSITIVE);
								Matcher matcher = pattern.matcher(entry
										.getKey());
								boolean b1 = matcher.find();
								if (b1) {
								} else {
									rtfkey = rtfkey + "," + entry.getKey();
									jt3.setText(rtfkey);
								}
							}

							System.out.println(entry.getKey() + " ==== "
									+ entry.getValue());
						}

					}

				} else if (extension.equalsIgnoreCase(".doc")) {
					String Dockey = "";
					Map<String, Integer> wordMap = this.getWordCount(path);
					List<Entry<String, Integer>> list = this.sortByValue(wordMap);
					for (Map.Entry<String, Integer> entry : list) {
						if (entry.getValue() > 10) {
							// rtfkey=entry.getKey();
							Pattern p = Pattern.compile("[^a-z0-9 ]",
									Pattern.CASE_INSENSITIVE);
							// Pattern p =
							// Pattern.compile("[^a-z0-9\"\\.&,@!?#%'$()/\\\\ \\-_<>]+$",Pattern.CASE_INSENSITIVE);
							Matcher m = p.matcher(entry.getKey());
							//                            
							boolean b = m.find();
							if (b) {
								//
							} else {
								Pattern pattern = Pattern.compile(
										"[\"\\.&,@!?#%'$()/\\\\ \\-_<>]+$",
										Pattern.CASE_INSENSITIVE);
								Matcher matcher = pattern.matcher(entry
										.getKey());
								boolean b1 = matcher.find();
								if (b1) {
								} else {
									Dockey = Dockey + "," + entry.getKey();
									jt2.setText(Dockey);
								}
							}

							// Pattern pattern =
							// Pattern.compile("[\\$#@\\^&]Hello(\\s|$)",
							// Pattern.CASE_INSENSITIVE);
							System.out.println(entry.getKey() + " ==== "
									+ entry.getValue());
						}
					}
				} else if (extension.equalsIgnoreCase(".txt")) {
					String Txtkey = "";
					System.out.println("path is "+path);
					Map<String, Integer> wordMap = this.getWordCount(path);
					List<Entry<String, Integer>> list = this.sortByValue(wordMap);

					for (Map.Entry<String, Integer> entry : list) {
						System.out.println("dfgfdgdfhdhgfh");
						if (entry.getValue() > 10) {

							Pattern p = Pattern.compile("[^a-z0-9 ]",
									Pattern.CASE_INSENSITIVE);
							// Pattern p =
							// Pattern.compile("[^a-z0-9\"\\.&,@!?#%'$()/\\\\ \\-_<>]+$",Pattern.CASE_INSENSITIVE);
							Matcher m = p.matcher(entry.getKey());
							//                            
							boolean b = m.find();

							if (b) {
								//
							} else {
								Pattern pattern = Pattern.compile(
										"[\"\\.&,@!?#%'$()/\\\\ \\-_<>]+$",
										Pattern.CASE_INSENSITIVE);
								Matcher matcher = pattern.matcher(entry
										.getKey());
								boolean b1 = matcher.find();
								if (b1) {
								} else {
									Txtkey = Txtkey + "," + entry.getKey();
									jt2.setText(Txtkey);
								}
							}

							// Pattern pattern =
							// Pattern.compile("[\\$#@\\^&]Hello(\\s|$)",
							// Pattern.CASE_INSENSITIVE);
							System.out.println(entry.getKey() + " ==== "
									+ entry.getValue());
						}
					}
				} else if (extension.equalsIgnoreCase(".pdf")) {
					String pdfstring = "";
					String text = parsedText;
					List<String> list = Arrays.asList(text.split(" "));
					Set<String> uniqueWords = new HashSet<String>(list);
					for (String word : uniqueWords) {
						Pattern p = Pattern.compile("[^a-z0-9 ]",
								Pattern.CASE_INSENSITIVE);
						// Pattern p =
						// Pattern.compile("[^a-z0-9\"\\.&,@!?#%'$()/\\\\ \\-_<>]+$",Pattern.CASE_INSENSITIVE);
						Matcher m = p.matcher(word);
						//                            
						boolean b = m.find();
						if (b) {
							//
						} else if (Collections.frequency(list, word) > 10) {
							Pattern pattern = Pattern.compile(
									"[\"\\.&,@!?#%'$()/\\\\ \\-_<>]+$",
									Pattern.CASE_INSENSITIVE);
							Matcher matcher = pattern.matcher(word);
							boolean b1 = matcher.find();
							if (b1) {
							} else {
								pdfstring = pdfstring + "," + word;
								jt2.setText(pdfstring);
								System.out.println(word + ": "
										+ Collections.frequency(list, word));
							}
						}
					}

				}
			} catch (Exception e) {
				System.out.println("Exception " + e);
			}

			//jb2.setEnabled(true);
		}


		if (ae.getSource() == jb2) 
		{

			FileOutputStream privateKeyOutputStream = null;
			FileOutputStream publicKeyOutputStream = null;

			try {

				String fileNameWithOutExt = FilenameUtils.removeExtension(jt1.getText());
				String Prikey = privateKeyPathName.concat("_"+fileNameWithOutExt+"//");
				String Privatefilepath =  Prikey.concat("private.key");

				System.out.println("--------------------"+Privatefilepath);
				boolean dir=new File(privateKeyPathName+"_"+fileNameWithOutExt).mkdir();
				System.out.println("==============="+dir);
				if(!dir)
				{
					System.out.println("A folder with name"+fileNameWithOutExt+"  is already exist in the path "+path);
				}else
				{
					System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!");
				}



				String Pubkey = publicKeyPathName.concat("_"+fileNameWithOutExt+"//");
				String Publicfilepath =  Pubkey.concat("public.key");

				boolean dir1=new File(publicKeyPathName+"_"+fileNameWithOutExt).mkdir();
				if(!dir1)
				{
					System.out.println("A folder with name"+fileNameWithOutExt+"  is already exist in the path "+path);
					//					JOptionPane.showMessageDialog(null,"file exist duplication not allowed ","Click ok",JOptionPane.INFORMATION_MESSAGE);
				}
				else
				{
					System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!");
				}


				System.out.println("File Name with out extension : "+ fileNameWithOutExt);
				System.out.println("Public Key File Path  : "+ Publicfilepath);
				System.out.println("Private Key File Path  : "+ Privatefilepath);

				File privateKeyFile = new File(Privatefilepath);
				File publicKeyFile = new File(Publicfilepath);

				privateKeyOutputStream = new FileOutputStream(privateKeyFile);
				privateKeyOutputStream.write(privateKey.getEncoded());

				publicKeyOutputStream = new FileOutputStream(publicKeyFile);
				publicKeyOutputStream.write(publicKey.getEncoded());

			} 
			catch (IOException ioException) 
			{
				System.out.println(ioException);
			}
			finally 
			{

				try {

					if (privateKeyOutputStream != null) 
					{
						privateKeyOutputStream.close();
					}
					if (publicKeyOutputStream != null) 
					{
						publicKeyOutputStream.close();
					}

				} 
				catch (IOException ioException) 
				{
					System.out.println(ioException);
				}
			}

			publicKeyBytes = publicKey.getEncoded();
			BASE64Encoder encoder = new BASE64Encoder();
			pubKeyStr = encoder.encode(publicKeyBytes);



			privateKeyBytes = privateKey.getEncoded();
			privateKeyStr = encoder.encode(privateKeyBytes);
			jt8.setText(new String(pubKeyStr));
			jt7.setText(new String(privateKeyStr));
			System.out.print("test Jb2");
			jb3.setEnabled(true);

			jb3.setEnabled(true);

		}


		if (ae.getSource() == jb3) {
			try {

				if (extension.equalsIgnoreCase(".rtf")) {
					System.out.println("RTF Start : " + this.getString());

					String se = this.encrypt(this.getString());

					jta.setText(new String(se));


				} else if (extension.equalsIgnoreCase(".doc")) {
					System.out.println("Doc Start : " + this.getString());
					String sedoc = this.encrypt(this.getString());
					jta.setText(new String(sedoc));

				} else if (extension.equalsIgnoreCase(".txt")) {
					System.out.println("TXT Start------------ : " + this.getString());
					String setxt = this.encrypt(this.getString());
					jta.setText(new String(setxt));

				} else if (extension.equalsIgnoreCase(".pdf")) {
					System.out.println("PDF Start : " + this.getString());
					String sepdf = this.encrypt(this.getString());
					System.out.println(sepdf);
					jta.setText(new String(sepdf));



					// System.out.println("Encription  PDF    : "+sepdf);

				}

			} catch (Exception e) {
				System.out.println("File not found" + e);
			}

			jb.setEnabled(true);
		}

		if (ae.getSource() == jb1) {


			try {
				sock = new Socket("localhost", 5000);
				System.out.println("done succes");
			} catch (UnknownHostException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			File myFile = new File("C:\\Users\\sys\\Desktop\\221116\\MRSE\\encrypted\\" + jt1.getText());
			byte[] mybytearray = new byte[(int) myFile.length()];

			FileInputStream fis = null;



			try {
				fis = new FileInputStream(myFile);






			} catch (FileNotFoundException e) {

				e.printStackTrace();
			}
			BufferedInputStream bis = new BufferedInputStream(fis);

			DataInputStream dis = new DataInputStream(bis);
			try {
				dis.readFully(mybytearray, 0, mybytearray.length);
			} catch (IOException e) {

				e.printStackTrace();
			}

			// Sending file name and file size to the server
			/*OutputStream os = null;
			try {
				os = sock.getOutputStream();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}*/


			try {
				DataOutputStream dos = new DataOutputStream(sock.getOutputStream());
				dos.writeUTF("hi");
				dos.writeUTF(jt1.getText());
				dos.writeLong(mybytearray.length);
				dos.write(mybytearray, 0, mybytearray.length);
				dos.flush();

				// Sending file data to the server
				//os.write(mybytearray, 0, mybytearray.length);
				//os.flush();

				// Closing socket
				//os.close();
				//dos.close();
				//sock.close();
			} catch (Exception e) {

			}

		}

	}

	public String getString() {
		return aString;
	}

	public void setString(String s) {
		aString = s;
	}


	public void create_file(String filename, String msg) throws IOException {
		String add = null;
		add = "C:\\Users\\sys\\Desktop\\221116\\MRSE\\encrypted\\";
		add += filename;
		FileWriter ryt = new FileWriter(add);
		BufferedWriter out = new BufferedWriter(ryt);
		out.write(msg);
		out.close();

	}

	//**************ency*****************

	public String encrypt(String plaintext) throws Exception {
		//String hash = util.hashing(plaintext);
		boolean temp = false;
		int count =0;


		String fileNameWithOutExt = FilenameUtils
		.removeExtension(jt1.getText());
		String Pubkey = publicKeyPathName.concat("_"+fileNameWithOutExt+"//");
		String Publicfilepath =  Pubkey.concat("public.key");
		System.out.println("File Name with out extension : "
				+ fileNameWithOutExt);

		System.out.println("Public Key File Path  : " + Publicfilepath);

		X509EncodedKeySpec x509EncodedKeySpec = new X509EncodedKeySpec(IOUtils
				.toByteArray(new FileInputStream(Publicfilepath)));

		this.cipher.init(Cipher.ENCRYPT_MODE, KeyFactory.getInstance("RSA")
				.generatePublic(x509EncodedKeySpec));

		Random n = new Random();
		int secretkey = n.nextInt(2500);
		String secret = "" + secretkey;
		System.out.println("SecretKey="+secret);
		String filename = jt1.getText();
		String privatekey1 = jt7.getText();

		byte[] bytes = plaintext.getBytes("UTF-8");

		byte[] encrypted = blockCipher(bytes, Cipher.ENCRYPT_MODE);

		char[] encryptedTranspherable = Hex.encodeHex(encrypted);
		create_file(filename, new String(encryptedTranspherable));



		//				JOptionPane.showMessageDialog(null, "uploaded in cloud");
		JOptionPane.showMessageDialog(null,"uploaded sucessfully","Click ok",JOptionPane.INFORMATION_MESSAGE);

		new fupload();
		return new String(encryptedTranspherable);			
	}
	private byte[] blockCipher(byte[] bytes, int mode)
	throws IllegalBlockSizeException, BadPaddingException {

		byte[] scrambled = new byte[0];

		// toReturn will hold the total result
		byte[] toReturn = new byte[0];

		int length = (mode == Cipher.ENCRYPT_MODE) ? 100 : 128;

		// another buffer. this one will hold the bytes that have to be modified
		// in this step
		byte[] buffer = new byte[length];

		for (int i = 0; i < bytes.length; i++) {

			// if we filled our buffer array we have our block ready for de- or
			// encryption
			if ((i > 0) && (i % length == 0)) {
				// execute the operation
				scrambled = cipher.doFinal(buffer);
				// add the result to our total result.
				toReturn = append(toReturn, scrambled);
				// here we calculate the length of the next buffer required
				int newlength = length;

				// if newlength would be longer than remaining bytes in the
				// bytes array we shorten it.
				if (i + length > bytes.length) {
					newlength = bytes.length - i;
				}
				// clean the buffer array
				buffer = new byte[newlength];
			}
			// copy byte into our buffer.
			buffer[i % length] = bytes[i];
		}

		scrambled = cipher.doFinal(buffer);

		toReturn = append(toReturn, scrambled);

		return toReturn;
	}

	private byte[] append(byte[] prefix, byte[] suffix) {
		byte[] toReturn = new byte[prefix.length + suffix.length];
		for (int i = 0; i < prefix.length; i++) {
			toReturn[i] = prefix[i];
		}
		for (int i = 0; i < suffix.length; i++) {
			toReturn[i + prefix.length] = suffix[i];
		}
		return toReturn;
	}

	private void saveKeys(String fileName, BigInteger mod, BigInteger exp)
	throws IOException {
		FileOutputStream fos = null;
		ObjectOutputStream oos = null;

		try {
			System.out.println("Generating " + fileName + "...");
			fos = new FileOutputStream(fileName);
			oos = new ObjectOutputStream(new BufferedOutputStream(fos));

			oos.writeObject(mod);
			oos.writeObject(exp);

			System.out.println(fileName + " generated successfully");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (oos != null) {
				oos.close();

				if (fos != null) {
					fos.close();
				}
			}
		}
	}


	//***********************************************
	public String readtxtFile(String fileName) throws IOException {
		BufferedReader br = new BufferedReader(new FileReader(fileName));
		try {
			StringBuilder sb = new StringBuilder();
			String line = br.readLine();

			while (line != null) {
				sb.append(line);
				sb.append("\n");
				line = br.readLine();
				System.out.println("txt con "+line);
			}
			return sb.toString();
		} finally {
			br.close();
		}

	}


	//**********************8reading doc file*************************
	public String readDocFile(String path1) {
		File docFile = null;
		WordExtractor docExtractor = null;
		// WordExtractor exprExtractor = null ;
		try {
			docFile = new File(path1);
			// A FileInputStream obtains input bytes from a file.
			FileInputStream fis = new FileInputStream(docFile.getAbsolutePath());

			// A HWPFDocument used to read document file from FileInputStream
			HWPFDocument doc = new HWPFDocument(fis);

			docExtractor = new WordExtractor(doc);
		} catch (Exception exep) {
			System.out.println(exep.getMessage());
		}

		// This Array stores each line from the document file.
		String[] docArray = docExtractor.getParagraphText();

		for (int i = 0; i < docArray.length; i++) {
			if (docArray[i] != null)
				System.out.println("Line " + i + " : " + docArray[i]);
		}
		StringBuffer result = new StringBuffer();
		for (int i = 0; i < docArray.length; i++) {
			result.append(docArray[i]);
			// result.append( optional separator );
		}
		String mynewstring = result.toString();
		return mynewstring;
	}
	//**********************************************************

	//&&&&&&&&&&&&&&&&&&&&&pdf reader ****************************
	public String pdftoText(String filename) {
		System.out.println("Parsing text from PDF file " + filename + "....");
		File f = new File(filename);
		if (!f.isFile()) {
			System.out.println("File " + filename + " does not exist.");
			return null;
		}
		try {
			parser = new PDFParser(new FileInputStream(f));
		} catch (Exception e) {
			System.out.println("Unable to open PDF Parser.");
			return null;
		}
		try {
			parser.parse();
			cosDoc = parser.getDocument();
			pdfStripper = new PDFTextStripper();
			pdDoc = new PDDocument(cosDoc);
			parsedText = pdfStripper.getText(pdDoc);
		} catch (Exception e) {
			System.out
			.println("An exception occured in parsing the PDF Document.");
			e.printStackTrace();
			try {
				if (cosDoc != null)
					cosDoc.close();
				if (pdDoc != null)
					pdDoc.close();

			} catch (Exception e1) {
				e1.printStackTrace();
			}
			return null;
		}
		System.out.println("Done.");
		return parsedText;
	}



	//*******************************getwordcount**********************

	public Map<String, Integer> getWordCount(String fileName) {
		FileInputStream fis = null;
		DataInputStream dis = null;
		BufferedReader br = null;
		Map<String, Integer> wordMap = new HashMap<String, Integer>();
		try {
			System.out.println("path is get wordcount "+path);
			fis = new FileInputStream(fileName);
			dis = new DataInputStream(fis);
			br = new BufferedReader(new InputStreamReader(dis));
			String line = null;
			while ((line = br.readLine()) != null) {
				StringTokenizer st = new StringTokenizer(line, " ");
				while (st.hasMoreTokens()) {
					String tmp = st.nextToken().toLowerCase();
					System.out.println("tmp "+tmp);
					if (wordMap.containsKey(tmp)) {
						wordMap.put(tmp, wordMap.get(tmp) + 1);
					} else {
						wordMap.put(tmp, 1);
					}
				}
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (br != null)
					br.close();
			} catch (Exception ex) {
			}
		}
		return wordMap;
	}
	//*****************************************

	public Map<String, Integer> getWordCountpdf(String fileName) {
		FileInputStream fis = null;
		DataInputStream dis = null;
		BufferedReader br = null;
		Map<String, Integer> wordMap = new HashMap<String, Integer>();
		try {
			fis = new FileInputStream(fileName);
			dis = new DataInputStream(fis);
			br = new BufferedReader(new InputStreamReader(dis));
			String line = null;
			while ((line = br.readLine()) != null) {
				StringTokenizer st = new StringTokenizer(line, " ");
				while (st.hasMoreTokens()) {
					String tmp = st.nextToken().toLowerCase();
					if (wordMap.containsKey(tmp)) {
						wordMap.put(tmp, wordMap.get(tmp) + 1);
					} else {
						wordMap.put(tmp, 1);
					}
				}
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (br != null)
					br.close();
			} catch (Exception ex) {
			}
		}
		return wordMap;
	}

	public List<Entry<String, Integer>> sortByValue(Map<String, Integer> wordMap) {
		Set<Entry<String, Integer>> set = wordMap.entrySet();
		List<Entry<String, Integer>> list = new ArrayList<Entry<String, Integer>>(
				set);
		Collections.sort(list, new Comparator<Map.Entry<String, Integer>>() {
			public int compare(Map.Entry<String, Integer> o1,
					Map.Entry<String, Integer> o2) {
				return (o2.getValue()).compareTo(o1.getValue());
			}
		});
		return list;
	}

	//***********************key generation*******************88//


	public void init() throws NoSuchAlgorithmException, NoSuchPaddingException,
	NoSuchProviderException {

		System.out.println("**********welcome for keys generation************");
		KeyPairGenerator kpg = KeyPairGenerator.getInstance("RSA");

		kpg.initialize(1024);
		this.keyPair = kpg.generateKeyPair();
		publicKey = this.keyPair.getPublic();

		privateKey = this.keyPair.getPrivate();

		this.cipher = Cipher.getInstance("RSA");

	}
	//************************************************************8
	public static void main(String args[]){
		fupload oc=new fupload();

	}
}