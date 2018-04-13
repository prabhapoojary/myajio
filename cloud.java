
import java.awt.Color;
import java.awt.Font;
import java.io.*;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import javax.swing.table.*;

import java.net.*;



import org.apache.lucene.queryParser.ParseException;


import java.util.ArrayList;

import java.math.BigInteger;
public class cloud 
{
	int psize=2000;
	int dmem=5;
	static int foo;
		JFrame jf;
	JPanel jp;
	public static String keyword;
	 String[] columnNames = {"Files"};
	 String[] columnNames1 = {"USer name","Files", "Downloaded date","Size"};
	 java.util.Date date=new java.util.Date();
	 DefaultTableModel model,model1;
	 JTable table,table1;
	 JScrollPane jsp;
	 static String fname;
	 JButton jb;
	 JLabel j1,j2;
static	 String  s6;
	 static JTextArea jta;
	
	 Object[][] data;
	 Object[][] data1;
	 private static final String INDEX_DIR = "C:\\Users\\sys\\Desktop\\221116\\MRSE\\Indx";
	 static ServerSocket cs_server;
	 
	 
	 JLabel jl,jl1;
	
	 public cloud() 
	{
		
		try
		{

			UIManager.LookAndFeelInfo l[]=UIManager.getInstalledLookAndFeels();
			UIManager.setLookAndFeel(l[1].getClassName());
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
		jf=new JFrame("");
		jf.setBounds(100,200,500,400);
		jf.setLayout(null);

		jp=new JPanel();
		jp.setBounds(2,2,496,396);
		jp.setBackground(Color.white);
		jp.setBorder(new TitledBorder(""));
		jp.setLayout(null);
		jf.add(jp);
		
		jl=new JLabel(new ImageIcon(""));
		jl.setBounds(0,0,496,396);
		jl.setLayout(null);
		jl.setBorder(new TitledBorder(""));
		jp.add(jl);
		
		jl1=new JLabel(new ImageIcon("C:\\Documents and Settings\\Administrator\\Desktop\\cloud.jpg"));
		jl1.setBounds(5,5,200,200);
		jl1.setBorder(new TitledBorder(""));
		jl.add(jl1);
		
		jta=new JTextArea();
		jsp=new JScrollPane(jta);
		jsp.setBounds(220,60,250,150);
		jsp.setBorder(new TitledBorder(""));
		jl.add(jsp);
		
		j2= new JLabel("Files in cloud");
		j2.setFont(new Font("Times New Roman", Font.BOLD, 14));
		j2.setBackground(Color.BLACK);
		j2.setForeground(Color.BLACK);
		j2.setBounds(300, 5, 120, 60);
		j2.setLayout(null);
		jl.add(j2);
			
			jb=new JButton("EXIT");
			jb.setBounds(270,250,130,30);
			jb.setBackground(Color.cyan);
			jb.setForeground(Color.red);
			jb.setFont(new Font("Times New Roman",Font.BOLD,20));
			jl.add(jb);
			
			
	jf.setVisible(true);
	JFileChooser fc = new JFileChooser();
	

	
		
	}
	
		
	public static void main(String[] args) 
	{

	int bytesRead;     
    int current = 0; 
    new cloud();
    try
    {
    	cs_server=new ServerSocket(5000);
    	
    }
    catch(Exception e)
    {
    	
    }
    while(true)
    {
	    
  
    	try
    	{
    		System.out.println("cloud server waiting at "+ cs_server.getLocalPort());
            Socket    cs_socket = cs_server.accept();
            System.out.println("request accepted");
               
    			 InputStream in = cs_socket.getInputStream();
				  DataOutputStream out = new DataOutputStream(cs_socket.getOutputStream());
                ObjectOutputStream oos=new ObjectOutputStream(cs_socket.getOutputStream());
                 DataInputStream clientData = new DataInputStream(in); 
				   
                 String line = clientData.readUTF();    
				  System.out.println("========>"+line);
				  if(line.equals("hi"))
				  {
					  System.out.println("hi am in hi block");
					  line=clientData.readUTF();
					  System.out.println("hi am in line");
        //OutputStream output = new FileOutputStream("C:\\Users\\lenovo\\Dropbox\\resources\\"+line);   
        OutputStream output = new FileOutputStream("C:\\Users\\sys\\Desktop\\221116\\MRSE\\resources"+line);   
        long size = clientData.readLong();        
        byte[] buffer = new byte[1024];        
        while (size > 0 && (bytesRead = clientData.read(buffer, 0, (int)Math.min(buffer.length, size))) != -1)        
        {        
          
        output.write(buffer, 0, bytesRead);
            size -= bytesRead;        
        } 
             JOptionPane.showMessageDialog(null,"file uploaded successful","Click ok",JOptionPane.INFORMATION_MESSAGE);
        
        in.close();   
        clientData.close();   
        output.close();     
				  }

             
                 if(line.equals("h"))
                {
                
                	   keyword=clientData.readUTF();
                    	System.out.println(keyword);
                  decrypt(keyword);	
                  ArrayList<fieldset> userList=Searcher.userList;
                  for(fieldset li:userList)
                  {
                	  System.out.println("........>keyword value");
                  System.out.println(li.getId());
                  }  
                  
                  oos.writeObject(userList);
                }
                
                 if(line.equals("hello"))
                {
                	//line=in.readUTF();
                	
                	String  s3=clientData.readUTF();
                	 System.out.println("fff");
                	 System.out.println("top-k value");

                	// System.out.println(s3);
                	int foo = Integer.parseInt(s3);
                  	System.out.println(s3);
                	System.out.println(foo);
                	

                	System.out.println(keyword);
                	 decrypt2(keyword,foo);
                	   s6=clientData.readUTF();
                	 
                	   
                	   ArrayList<fieldset1> userList1=Searcher.userList1;
          			 for(fieldset1 li1:userList1)
                       {
                     	  System.out.println("........>list value");
                       System.out.println(li1.getId());
                       }  
                       
                       oos.writeObject(userList1);
                       System.out.println("sent sucees to user");
                	   
                	   
                	   

                	
                	
                }
            
                
    	}
    	
    	catch(Exception e)
    	{
    		
    	}
    }
    }  
	public static void create_file(String filename,String msg)throws IOException
	{
		String add=null;
		add="C:\\Users\\sys\\Desktop\\221116\\MRSE\\resources";
		add+=filename;
		FileWriter ryt=new FileWriter(add);
		BufferedWriter out=new BufferedWriter(ryt);
		out.write(msg);
		out.close();
	}
		
	
	public static void decrypt(String keyword) throws IOException
    {
			
	
	String spt[]=keyword.split(",");
	
	

	BigInteger b1=new BigInteger(spt[1]);
	BigInteger m=b1.subtract(new BigInteger(spt[0]));
    System.out.println("decrypted text==========="+new String(m.toByteArray()));
    String s =new String(m.toByteArray());
    Searcher searcher = new Searcher(INDEX_DIR);
    try {
		searcher.Searcherone(INDEX_DIR,s);
		 
	} 
    catch (ParseException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
    }
	
	public static void decrypt2(String keyword,int topk) throws IOException
    {
			
	
	String spt[]=keyword.split(",");
	
	

	BigInteger b1=new BigInteger(spt[1]);
	BigInteger m=b1.subtract(new BigInteger(spt[0]));
    System.out.println("decrypted text==========="+new String(m.toByteArray()));
    String s =new String(m.toByteArray());
    Searcher searcher = new Searcher(INDEX_DIR);
    try {
    	System.out.println("Start top key search ");
    	System.out.println(keyword);
		searcher.Searchertop(INDEX_DIR,s,topk);
		System.out.println("end top key search ");
	} 
    catch (ParseException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
    }
	
		
	public static void topfiles(int topkey) throws IOException {
		

		Searcher searcher = new Searcher(INDEX_DIR);
	    try {
	    	System.out.println("Start top key search ");
	    	System.out.println(keyword);
			searcher.Searchertop(INDEX_DIR,keyword,topkey);
			System.out.println("end top key search ");
		} 
	    catch (ParseException e) {
			
			e.printStackTrace();
		}
	}
	
}

