import java.awt.Color;
import java.awt.Font;
import java.net.ServerSocket;
import java.net.Socket;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.spec.RSAPrivateKeySpec;
import java.security.spec.RSAPublicKeySpec;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
//import java.util.SecureRandom;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.math.BigInteger;
//import javax.security.*;
import javax.crypto.*;

import org.apache.commons.codec.binary.Hex;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.io.IOUtils;

/*import com.amazonaws.AmazonClientException;
import com.amazonaws.AmazonServiceException;
import com.amazonaws.auth.PropertiesCredentials;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.GetObjectRequest;
import com.amazonaws.services.s3.model.S3Object;
*/
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;

class user extends JFrame implements ActionListener
{
JFrame jf,jf1,jf2,jf3,jf7;
JLabel j1,j9,j2,jl1;
JLabel j10,j11;
JPanel jp,jp1,jp2,jp3,jp4;
List<FieldPop> data;
JTextField jt1,jt3,jt6,jt5,jt7,jt8,jt9,jt10,jt11;
static //HashMap newmap ;
ArrayList<fieldset> userData = null;
ArrayList<fieldset1> userData1 = null;
JComboBox jc;
JTextArea keyword;
String u1,u2;
public static String a1=null;
  public static String fn=null;
public static	String str = null;
JScrollPane jsp;
//public static final String PRIVATE_KEY_FILE ="C:\\topk\\Private.txt";
static ServerSocket cs_server;
JPasswordField jt2,jt4;
String uid;
JButton jb,jb1,jb2,jb3,jb4,jb5,jb6,jb7;
public Cipher cipher=null;
public KeyPair keyPair ;
static private PrivateKey privateKey;
static private PublicKey publicKey;
ObjectInputStream ois;
JScrollPane jsp1;
private static final String PUBLIC_KEY_FILE = "Public.key";  
private static final String PRIVATE_KEY_FILE = "Private.key";  
String email1;
private static final String INDEX_DIR = "E:\\new_Workspace\\MRSE\\somedir\\Indx";
DefaultTableModel model;
String rows[];
String[] columnNames = { "Filename","Scoring"};
JTable table;
Socket cs_socket ;
//public static Connection conn=new database().conn;


//private String[] COLUMNS = {"FAMILY MEMBERS", "STAR (NAKSHATRA)"};
//private DefaultTableModel model = new DefaultTableModel(columnNames, 0);
PreparedStatement ps;
ResultSet rs;
final static BigInteger one = new BigInteger("1");
BigInteger p,q,g,resulta;
private int bitlength = 3;
DataOutputStream dop;
DataInputStream dip;
static Connection con;
private final String privateKeyPathName = "E:\\new_Workspace\\MRSE\\temp\\";
//private final String privateKeyPathName = "C://topkavya//temp//";
Socket	soc;
String a;
String encmsg;
String ss=Searcher.title;
String uname;
int i=0;
ArrayList<String> nflisf;
//ArrayList<String,String > nflisf1;

user()  throws NoSuchAlgorithmException, NoSuchPaddingException, NoSuchProviderException
{
this.init();
	try
	{   
		//comment the next two lines if do not want look and feel
		UIManager.LookAndFeelInfo l[]=UIManager.getInstalledLookAndFeels();
		UIManager.setLookAndFeel(l[1].getClassName());
	}
	catch(Exception e)
	{
		System.out.println("Error with main method \n"+e);
	}
	

	jf2=new JFrame("Main");
			jf2.setSize(460,450);
			
			
			
			jp2=new JPanel();
			jp2.setBounds(0,0,460,450);
			jp2.setBackground(Color.white);
			jp2.setLayout(null);
			jp2.setBackground(new Color(173,216,230));
			jf2.add(jp2);
			
			
			
		/*	j9=new JLabel(new ImageIcon("images/virtual.jpg"));
			j9.setBounds(5,400,500,550);
			j9.setLayout(null);
			jp2.add(j9);*/
			

			j1=new JLabel("Enter the Keywords to search");
			j1.setFont(new Font("Times New Roman",Font.BOLD,14));
		j1.setBackground(Color.BLACK);
		j1.setLayout(null);
			j1.setForeground(Color.BLACK);
			j1.setBounds(80,10,210,100);
			j1.setLayout(null);
			jp2.add(j1);
			
			jt1=new JTextField();
			jt1.setFont(new Font("Times New Roman",Font.BOLD,14));
			jt1.setBackground(Color.white);
			jt1.setForeground(Color.black);
			jt1.setBounds(80,80,210,30);
			jt1.setLayout(null);
				jp2.add(jt1);
				
				j2=new JLabel("Trapdoor generator");
				j2.setFont(new Font("Times New Roman",Font.BOLD,14));
			j2.setBackground(Color.BLACK);
			j2.setLayout(null);
				j2.setForeground(Color.BLACK);
				j2.setBounds(100,90,210,100);
				j2.setLayout(null);
				jp2.add(j2);
				
			
			jb3=new JButton("enter");
			jb3.setFont(new Font("Times New Roman",Font.BOLD,14));
			jb3.setBackground(Color.white);
		jb3.setForeground(Color.black);
			jb3.setBounds(80,330,85,25);
			jb3.setLayout(null);
			jb3.addActionListener(this);
				jp2.add(jb3);
				
				jb7=new JButton("Top-k value");
				jb7.setFont(new Font("Times New Roman",Font.BOLD,14));
				jb7.setBackground(Color.white);
				jb7.setForeground(Color.black);
				jb7.setBounds(200,330,125,25);
				jb7.setLayout(null);
				jb7.addActionListener(this);
				jb7.setEnabled(true);
				jp2.add(jb7);
			
				keyword=new JTextArea();
				jsp=new JScrollPane(keyword);
				jsp.setBounds(80,160,200,150);
				jsp.setBorder(new TitledBorder(""));
				jp2.add(jsp);
				
				
				
				jf2.setVisible(true);
				jf2.setLayout(null);
				jb7.setEnabled(false);
}
				
public user(ArrayList<fieldset> userData2)
{
	// TODO Auto-generated constructor stub
}			
		
			
			




@SuppressWarnings("unchecked")
public void actionPerformed(ActionEvent ae)
{
			
if(ae.getSource()==jb3)
{
	String s=jt1.getText().toString();
	System.out.println(s);
	s=encmsg(s);
	System.out.println(s);
	System.out.println("connecting to cloud server "+  " on port ");
	
	try {
		
		Socket client;
		client = new Socket("localhost", 5000);
		OutputStream outtoserver = client.getOutputStream();
		DataOutputStream out = new DataOutputStream(outtoserver);
		InputStream infromserver = client.getInputStream();
		DataInputStream in = new DataInputStream(infromserver);
		ObjectInputStream oo = new ObjectInputStream(infromserver);
		out.writeUTF("h");
		out.writeUTF(s);
		ArrayList<fieldset> li=(ArrayList<fieldset>)oo.readObject();
		
		data = new ArrayList<FieldPop>();
//		nflisf1 = new ArrayList<String,String>();
		    System.out.println("dffghg");
		    int i=0;
		    for(fieldset rf:li){
		    	
				 System.out.println(rf.getId());
				 
				 String uid=rf.getId();
					String uname=rf.getName();
					data.add(new FieldPop(uname, uid));
					
				//	model.insertRow(0, new Object[]{uid,uid}); 
					
					i++;
					 System.out.println("=====>>>"+rf.getName());
			 }
		
		
		jb7.setEnabled(true);

	} 
	catch (Exception e)
	{
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	
			
	System.out.println("going jb7");

	
	
				
						 
						
	 
jf1=new JFrame();
jf1.setSize(500,500);
jf1.setLayout(null);

model=new DefaultTableModel();
table=new JTable(model);
model.addColumn("filename");
model.addColumn("scoring");


jsp1=new JScrollPane(table);
jsp1.setBounds(0,0,400,400);
jsp1.setBorder(new TitledBorder(""));
jf1.add(jsp1);


for(FieldPop rf:data){
	String uid=rf.getId();
	String uname=rf.getName();
	//data.add(new FieldPop(uid, uname));
	
	model.insertRow(0, new Object[]{uid,uname}); 
	
}
jf1.setVisible(true);
}

						   
				 
				
				
			

 
	
    

 
 
 





if(ae.getSource()==jb7)
{
	
	
	jf7=new JFrame("");
	jf7.setVisible(true);
	jf7.setSize(300,300);
	jf7.setLayout(null);
	
	j2=new JLabel(" Enter the Top-k value");
	j2.setBounds(35,20,160,30);
	j2.setFont(new Font("Times New Roman",Font.BOLD,14));
	jf7.add(j2);
	
	jt3=new JTextField();
	jt3.setFont(new Font("Times New Roman",Font.BOLD,14));
	jt3.setBackground(Color.white);
	jt3.setForeground(Color.black);
	jt3.setBounds(70,60,100,30);
	jt3.setLayout(null);
		jf7.add(jt3);
		
		
		
		
		jb4=new JButton("Search");
		jb4.setFont(new Font("Times New Roman",Font.BOLD,14));
		jb4.setBackground(Color.white);
	jb4.setForeground(Color.black);
		jb4.setBounds(70,120,85,25);
		jb4.setLayout(null);
		jb4.addActionListener(this);
			jf7.add(jb4);
			
			
								      
							
							


				
}			
	if(ae.getSource()==jb1)
	{
		Random n = new Random();
		int secretkey = n.nextInt(2500);
		String secret = "" + secretkey;
		System.out.println("SecretKey="+secret);
	//String secret=	JOptionPane.showInputDialog("enter your secret key");
		String user11=JOptionPane.showInputDialog("enter your user name");
		System.out.println("user name="+user11);
		
		try{
	    	 Class.forName("oracle.jdbc.driver.OracleDriver");
				con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE","system","system");
				String query1 = "select * from userdet2 where uname=?";
				
					PreparedStatement stmt1 = con.prepareStatement(query1);
					stmt1.setString(1,user11);
					ResultSet rs = stmt1.executeQuery();

					while(rs.next())
					{
						

						 email1 = rs.getString(4);
							System.out.println("user email=="+ email1);
						 
					}
					new Mail(email1,secret);
					
	     }
		catch(Exception e4)
	     {
	    	 e4.printStackTrace();
	     }
		
		
	    	 try{
	    		 final int secretcode=Integer.parseInt(JOptionPane.showInputDialog("Enter the secret"));
	    	     if(secretcode==secretkey)
	    	     {
			fn=(String)jc.getSelectedItem();
			System.out.println("...........fname"  +fn);
			String file="E:\\new_Workspace\\MRSE\\encrypted\\"+fn;
			BufferedReader br2=new BufferedReader(new FileReader(file));
			String line=br2.readLine();
			StringBuilder sb=new StringBuilder();
			while(line!=null){
				sb.append(line);
				line=br2.readLine();
				System.out.println("---....."+line);
				
			         }
			String cont=new String(sb.toString());
	    	     }
	    	 else
		     {
		    	 JOptionPane.showMessageDialog(null,"U Entered the wrong key sorry try again later");
		     } 
			
//		FileInputStream  fis = new FileInputStream(new File(file));
//		byte s[]=new byte[fis.available()];
//		String s3=new String(s,"UTF-8");
//		fis.read(s,0,s.length);
		//String key=JOptionPa;ne.showInputDialog("Enter secret key");
		//if(key!=null){
		//decrypt(s);
		
		//}
		
		/*************commented by karthi***********/
			/*String	add="C:\\Users\\HOME\\Desktop\\file\\topk.txt";
			FileWriter ryt=new FileWriter(add);
			BufferedWriter out=new BufferedWriter(ryt);
			out.write(cont+"!");
			out.close();*/
			
			
		
		}
	     
	    	 catch(Exception e3){}
		
		System.out.println("encrypted data loading...!!!kavya god bless you");
		System.out.println("Filenam while reading ?:"+fn);
		File fileDir = new File("E:\\new_Workspace\\MRSE\\encrypted\\"+fn);
        
 		BufferedReader in = null;
		try {
			in = new BufferedReader(
			   new InputStreamReader(
			               new FileInputStream(fileDir), "UTF-8"));
		} catch (UnsupportedEncodingException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
  
 	
  
 		try {
			while ((str = in.readLine()) != null) {
			    System.out.println(str);
			    a1=str;
			}
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
  
       try {
		in.close();
	} catch (IOException e1) {
		// TODO Auto-generated catch block
		e1.printStackTrace();
	}
	System.out.println("data decrypted.......!!!kavya god bless you");
	try {
		
		System.out.println(">>>>>>>>>>>>>>>>>>>> data decrypt block:"+str);
		String 	 sd=decrypt(a1);

		 FileWriter fw1 = new FileWriter("E:\\new_Workspace\\MRSE\\download\\"+fn);
			fw1.write(sd);
			fw1.flush();
			fw1.close();
			System.out.println("file downloaded successfully........!!!kavya god bless you");
			JOptionPane.showMessageDialog(null, "file Downloaded Succesfully");
	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}

			
		
			
	}
		
			
			
			
			
		
		//jf2.setVisible(false);
	

if(ae.getSource()==jb4)
{
	
	try {
		
		
		Socket client;
		client = new Socket("localhost", 5000);
		OutputStream outtoserver = client.getOutputStream();
		DataOutputStream out = new DataOutputStream(outtoserver);
		InputStream infromserver = client.getInputStream();
		DataInputStream in= new DataInputStream(infromserver);
		ObjectInputStream oo = new ObjectInputStream(infromserver);
	//	System.out.println(jt3.getText());
		String s5=jt3.getText();
		String s=jt1.getText().toString();
		System.out.println(s);
		System.out.println(s5);
		System.out.println("enter the top-k  value");
		out.writeUTF("hello");
		out.writeUTF(s5);
		out.writeUTF("hello1");
		out.writeUTF(s);
		System.out.println("sent successfully");
		ArrayList<fieldset1> li1=(ArrayList<fieldset1>)oo.readObject();
		System.out.println("received success");
		
	    System.out.println("dffghg");
   nflisf = new ArrayList<String>();

	    
	    for(fieldset1 rf:li1){
			 System.out.println(rf.getId());
			 
			 String uid=rf.getId();
				String uname=rf.getName();
 				nflisf.add(uid);

				// System.out.println("=====>>>"+rf.getName());
				 System.out.println("=====>>>"+rf.getId());
				 
				 System.out.println("top-k files");
				 System.out.println("received success");
		 }
	
	/*	out.flush();
		in.close();
		out.close();
		client.close();*/
		
	}
	catch(Exception e)
	{
		e.printStackTrace();
	}
	
	
	
	System.out.println("welcome to downloading;");
	jf=new JFrame("welcome : ");
	jf.setBounds(100,100,600,400);
	jf.setLayout(null);
	
	jl1=new JLabel(new ImageIcon("images/Router1.jpg"));
	jl1.setBounds(5,20,590,360);
	jl1.setLayout(null);
	jf.add(jl1);
	
	
	
jc=new JComboBox();
	jc.setBounds(10,50,200,60);
	jc.setBorder(new TitledBorder("File Names"));
	jc.setBackground(new Color(0,191,255));
	jc.addActionListener(this);
	jc.addItem("FILE LIST");
	jl1.add(jc);
	
	jb1=new JButton("DOWNLOAD");
	jb1.setBounds(240,60,200,50);
	jb1.setBackground(Color.black);
	jb1.setForeground(new Color(0,191,255));
	jb1.addActionListener(this);
	jl1.add(jb1);
	
	jb=new JButton("EXIT");
	jb.setBounds(10,280,150,40);
	jb.setBackground(Color.black);
	jb.setForeground(new Color(0,191,255));
	jb.addActionListener(this);
	jl1.add(jb);
	jf.setVisible(true);

	  for ( int j=0; j<nflisf.size(); j++ )
{
      jc.addItem(nflisf.get(j));
  }

	
				 }
	 }
	
		 
		
	
    /** 
     * read Public Key From File 
     * @param fileName 
     * @return PublicKey 
     * @throws IOException 
     */  




    public static PublicKey readPublicKeyFromFile(String fileName) throws IOException{  
        FileInputStream fis = null;  
        ObjectInputStream ois = null;  
        try {  
            fis = new FileInputStream(new File(fileName));  
            ois = new ObjectInputStream(fis);  
              
            BigInteger modulus = (BigInteger) ois.readObject();  
            BigInteger exponent = (BigInteger) ois.readObject();  
              
            //Get Public Key  
            RSAPublicKeySpec rsaPublicKeySpec = new RSAPublicKeySpec(modulus, exponent);  
            KeyFactory fact = KeyFactory.getInstance("RSA");  
            PublicKey publicKey = fact.generatePublic(rsaPublicKeySpec);  
                          
            return publicKey;  
              
        } catch (Exception e) {  
            e.printStackTrace();  
        }  
        finally{  
            if(ois != null){  
                ois.close();  
                if(fis != null){  
                    fis.close();  
                }  
            }  
        }  
        return null;  
    }  
      
    /** 
     * read Public Key From File 
     * @param fileName 
     * @return 
     * @throws IOException 
     */  
    public static PrivateKey readPrivateKeyFromFile(String fileName) throws IOException{  
        FileInputStream fis = null;  
        ObjectInputStream ois = null;  
        try {  
            fis = new FileInputStream(new File(fileName));  
            ois = new ObjectInputStream(fis);  
              
            BigInteger modulus = (BigInteger) ois.readObject();  
            BigInteger exponent = (BigInteger) ois.readObject();  
              
            //Get Private Key  
            RSAPrivateKeySpec rsaPrivateKeySpec = new RSAPrivateKeySpec(modulus, exponent);  
            KeyFactory fact = KeyFactory.getInstance("RSA");  
            PrivateKey privateKey = fact.generatePrivate(rsaPrivateKeySpec);  
                          
            return privateKey;  
              
        } catch (Exception e) {  
            e.printStackTrace();  
        }  
        finally{  
            if(ois != null){  
                ois.close();  
                if(fis != null){  
                    fis.close();  
                }  
            }  
        }  
        return null;  
    }  

/*public String fdwn(){
	String amzoncon="";
	 try {
	
	AmazonS3 s3Client = new AmazonS3Client(new PropertiesCredentials(
           user.class.getResourceAsStream("AwsCredentials.properties")));
   
        System.out.println("Downloading an object");
        //String bucketName=JOptionPane.showInputDialog("enter the bucket name");
        S3Object s3object = s3Client.getObject(new GetObjectRequest(
        		"topk", "encc.txt"));
        System.out.println("Content-Type: "  + 
        		s3object.getObjectMetadata().getContentType());
        amzoncon= displayTextInputStream(s3object.getObjectContent());
        
       // Get a range of bytes from an object.
        
       
        System.out.println("Printing bytes retrieved.");
        //displayTextInputStream(objectPortion.getObjectContent());
	 }catch(IOException e3){}
    catch (AmazonServiceException ase) {
        System.out.println("Caught an AmazonServiceException, which" +
        		" means your request made it " +
                "to Amazon S3, but was rejected with an error response" +
                " for some reason.");
        System.out.println("Error Message:    " + ase.getMessage());
        System.out.println("HTTP Status Code: " + ase.getStatusCode());
        System.out.println("AWS Error Code:   " + ase.getErrorCode());
        System.out.println("Error Type:       " + ase.getErrorType());
        System.out.println("Request ID:       " + ase.getRequestId());
    } catch (AmazonClientException ace) {
        System.out.println("Caught an AmazonClientException, which means"+
        		" the client encountered " +
                "an internal error while trying to " +
                "communicate with S3, " +
                "such as not being able to access the network.");
        System.out.println("Error Message: " + ace.getMessage());
    }
    return amzoncon;
}
*/
private static String displayTextInputStream(InputStream input)
throws IOException {
	// Read one text line at a time and display.
	StringBuilder sb=new StringBuilder();
	String enctext="";
    BufferedReader reader = new BufferedReader(new 
    		InputStreamReader(input));
    while (true) {
        String line = reader.readLine();
        sb.append(line);
        if (line == null) break;

        System.out.println("    " + line);
    }
    enctext=new String(sb.toString());
    System.out.println();
    return enctext;
}





	


public String encmsg(String s)
{
	String encmsg="";
try{
String fn=s;
	
Random	r = new Random();
BigInteger	P = BigInteger.probablePrime(bitlength, r);  
BigInteger  Q = BigInteger.probablePrime(bitlength, r);  
BigInteger N =P.multiply(Q);  
             
             Random rand3 = new Random(); 
              int kval= N.intValue();
              int kres=rand3.nextInt(kval-1);


                                System.out.println("value of k"+kres); 
              BigInteger k=BigInteger.valueOf(kres);


                                   System.out.println("value of P"+P); 
                BigInteger b1=k.multiply(P);
                                     System.out.println("b111111111111111"+b1);
             BigInteger M=new BigInteger(fn.getBytes());

             System.out.println("M val      "+M);
                                     
                                     

                BigInteger b2=M.add(b1);


                System.out.println("b2222222222222222"+b2);
               

encmsg=b1+","+b2;

System.out.println("encmsg  on SERVERside"+encmsg);
keyword.append(encmsg);
FileOutputStream fos = new FileOutputStream("keywords.txt"+"\n");
byte[] contentInBytes = encmsg.getBytes();

fos.write(contentInBytes);
fos.flush();
fos.close();

}
catch(Exception e3)
{
	}
return encmsg;

}

public String decrypt(String encrypted) throws Exception{
	//String fileNameWithOutExt = FilenameUtils.removeExtension(fn);
	String fileNameWithOutExt = FilenameUtils.removeExtension(fn);
	String Prikey = privateKeyPathName.concat("_"+fileNameWithOutExt+"//");
	String Privatefilepath = Prikey.concat("private.key");

    PKCS8EncodedKeySpec pkcs8EncodedKeySpec = new PKCS8EncodedKeySpec(IOUtils.toByteArray(new FileInputStream(Privatefilepath)));

    System.out.println(" decrypt block ");
//this.cipher.init(Cipher.DECRYPT_MODE, this.keyPair.getPrivate());
    this.cipher.init(Cipher.DECRYPT_MODE, KeyFactory.getInstance("RSA").generatePrivate(pkcs8EncodedKeySpec));
        System.out.println("Key in Decrypt Block Private :: "+this.keyPair.getPrivate());
byte[] bts = Hex.decodeHex(encrypted.toCharArray());
System.out.println(" decrypt hex  ");
byte[] decrypted = blockCipher(bts,Cipher.DECRYPT_MODE);
System.out.println(" decrypt bytes ");
return new String(decrypted,"UTF-8");
}



private byte[] blockCipher(byte[] bytes, int mode) throws IllegalBlockSizeException, BadPaddingException{
	// string initialize 2 buffers.
	// scrambled will hold intermediate results
	byte[] scrambled = new byte[0];

	// toReturn will hold the total result
	byte[] toReturn = new byte[0];
	// if we encrypt we use 100 byte long blocks. Decryption requires 128 byte long blocks (because of RSA)
	int length = (mode == Cipher.ENCRYPT_MODE)? 100 : 128;

	// another buffer. this one will hold the bytes that have to be modified in this step
	byte[] buffer = new byte[length];

	for (int i=0; i< bytes.length; i++){

		// if we filled our buffer array we have our block ready for de- or encryption
		if ((i > 0) && (i % length == 0)){
			//execute the operation
			scrambled = cipher.doFinal(buffer);
			// add the result to our total result.
			toReturn = append(toReturn,scrambled);
			// here we calculate the length of the next buffer required
			int newlength = length;

			// if newlength would be longer than remaining bytes in the bytes array we shorten it.
			if (i + length > bytes.length) {
				 newlength = bytes.length - i;
			}
			// clean the buffer array
			buffer = new byte[newlength];
		}
		// copy byte into our buffer.
		buffer[i%length] = bytes[i];
	}

	// this step is needed if we had a trailing buffer. should only happen when encrypting.
	// example: we encrypt 110 bytes. 100 bytes per run means we "forgot" the last 10 bytes. they are in the buffer array
	scrambled = cipher.doFinal(buffer);

	// final step before we can return the modified data.
	toReturn = append(toReturn,scrambled);

	return toReturn;
}

private byte[] append(byte[] prefix, byte[] suffix){
	byte[] toReturn = new byte[prefix.length + suffix.length];
	for (int i=0; i< prefix.length; i++){
		toReturn[i] = prefix[i];
	}
	for (int i=0; i< suffix.length; i++){
		toReturn[i+prefix.length] = suffix[i];
	}
	return toReturn;
}
  

/** 
* Encrypt Data 
* @param data 
* @throws IOException 
*/  
    

public void init() throws NoSuchAlgorithmException, NoSuchPaddingException, NoSuchProviderException
{

System.out.println("**********welcome for keys generation************");
KeyPairGenerator kpg = KeyPairGenerator.getInstance("RSA");
//KeyPair keyPair = kpg.generateKeyPair(); 
kpg.initialize(1024);
this.keyPair = kpg.generateKeyPair();
         publicKey = this.keyPair.getPublic();
         System.out.println("Key in INIT Block Public :: "+this.keyPair.getPublic());
         
         privateKey = this.keyPair.getPrivate();
        System.out.println("Key in INIT Block Private :: "+this.keyPair.getPrivate());
this.cipher = Cipher.getInstance("RSA");

}


 



public static void main(String[] args) throws NoSuchAlgorithmException, NoSuchPaddingException, NoSuchProviderException
{
	
	new user();
	//user a=new user(userData);
}
}
