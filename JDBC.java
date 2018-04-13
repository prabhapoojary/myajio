

import java.awt.BorderLayout;
import java.awt.Component;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Vector;

import javax.swing.*;
import javax.swing.text.TabableView;
import javax.swing.text.TableView;

public class JDBC extends JFrame{

	/**
	 * @param args
	 */
	static String val = null;
	static String query = null ;
	static String update_string;
	static Vector columnNames = new Vector();
	static Vector data = new Vector();
	public static void ConnectDatabase()
	{
		try
		{
			Class.forName("oracle.jdbc.driver.OracleDriver");
			con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE","system","system");
			System.out.println("Connected to Database...");
		}
		catch (Exception e)
		{
			System.out.println(e);
		}
		//return b;
	}
	public static void clear_data() {
		// TODO Auto-generated method stub
		//String tables[]={"dac_register","expressive_insert_key"};
		try {
			//for(int i=0;i<tables.length;i++)
			//{
			PreparedStatement	pstm = con.prepareStatement("delete from on_security_register ");
			pstm.executeQuery();
			//}
		
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		System.out.println("deleted  successfully");
	}
	public static void clear_files() {
		// TODO Auto-generated method stub
		//String tables[]={"dac_files","expressive_insert_key"};
		try {
			//for(int i=0;i<tables.length;i++)
			//{
			PreparedStatement	pstm = con.prepareStatement("delete  from dac_files ");
			pstm.executeQuery();
			//}
		
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		System.out.println("files deleted  successfully");
	}
	public static void clear_keys() {
		// TODO Auto-generated method stub
		//String tables[]={"dac_files","expressive_insert_key"};
		try {
			//for(int i=0;i<tables.length;i++)
			//{
			PreparedStatement	pstm = con.prepareStatement("delete  from secure_access_insert_key ");
			pstm.executeQuery();
			//}
		
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		System.out.println("key deleted  successfully");
	}
	public static String no_of_rows(){
		int count = 0; 

		query =
				"SELECT count(name) FROM on_security_register ";
		try {
			PreparedStatement stmt = con.prepareStatement(query);
			ResultSet rs = stmt.executeQuery();

			while(rs.next())
			{
				//count++;
				count=rs.getInt(1);
			}
			rs.close();
			stmt.close();


		}

		catch (SQLException e ) {

			e.printStackTrace();
			return "1";

		} 
		if(count==0)return "1";
		else{
			count++;
			return Integer.toString(count);}

	}




	public static String authenticate(String name)throws Exception
	{

		query ="select user_obj from on_security_register where USERNAME=?";

		try {
			PreparedStatement stmt = con.prepareStatement(query);
			stmt.setString(1,name);
			//stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery();
			while(rs.next())
			{
				val=rs.getString(1);
			}
			rs.close();
			stmt.close();
		}
		catch (SQLException e ) 
		{
			e.printStackTrace();
		} 
		return val;
	}

	public static boolean check_first(String username) {
		// TODO Auto-generated method stub
		int count = 0;

		query ="SELECT EMPLOYEE_ID FROM on_security_register WHERE EMPLOYEE_ID ='"+username+"'";

		try {
			PreparedStatement stmt = con.prepareStatement(query);
			//stmt.setString(1,username);
			//stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery();
			while(rs.next())
			{
				count++;
			}
			rs.close();
			stmt.close();
		}

		catch (SQLException e )
		{
			e.printStackTrace();
			return false;
		} 
		if(count>0)
			return true;
		else return false;
	}

	static Connection con;
	static ResultSet rs;
	Statement st;

	public static void revoc(String name) {
		// TODO Auto-generated method stub
		PreparedStatement ps;

		update_string="UPDATE on_security_register SET REVOCATION=? WHERE USER_OBJ ='"+name+"'";
		try {
			ps = con.prepareStatement(update_string);
			ps.setString(1, "YES");
			ps.executeUpdate();
			System.out.println("updated  successfully");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static String get_date(){
		DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
		//get current date time with Date()
		Date date = new Date();
		return dateFormat.format(date);
	}

	
	
		
	public static String user_no(String u) {
		// TODO Auto-generated method stub
		query =
				"select USER_OBJ from on_security_register  where USERNAME=?";
		String[] names = null;
		String i=null ;


		try {
			PreparedStatement stmt = con.prepareStatement(query);
			stmt.setString(1,u);
			//stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery();
			while(rs.next())
			{
				//i++;
				i = rs.getString(1);
			}
			rs.close();
			stmt.close();

		}

		catch (SQLException e ) {

			e.printStackTrace();

		} 

		return i;

	}


	public static void delete(String file_name) {
		// TODO Auto-generated method stub
		query =
				"delete from dac_file  where f_name=?";


		try {
			PreparedStatement stmt = con.prepareStatement(query);
			stmt.setString(1,file_name);
			stmt.executeUpdate();
			stmt.close();
		}

		catch (SQLException e ) {

			e.printStackTrace();

		} 

		System.out.println("file deleted from the database");

	}
	public static boolean user_available(String u_name) {
		int count = 0;

		query =
				"SELECT count(name) FROM on_security_register where USERNAME=?";

		try {
			PreparedStatement stmt = con.prepareStatement(query);
			stmt.setString(1,u_name);
			//stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery();

			while(rs.next())
			{

				count=rs.getInt(1);
			}
			rs.close();
			stmt.close();
		}

		catch (SQLException e ) {
			e.printStackTrace();
			return true;
		} 
		if(count==0)return true;
		else		
			return false;

	}
	public static boolean email(String text) {
		int count = 0;

		query =
				"SELECT count(name) FROM on_security_register where email_id=?";

		try {
			PreparedStatement stmt = con.prepareStatement(query);
			stmt.setString(1,text);
			//stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery();

			while(rs.next())
			{

				count=rs.getInt(1);
			}
			rs.close();
			stmt.close();
		}

		catch (SQLException e ) {
			e.printStackTrace();
			return true;
		} 
		if(count==0)return true;
		else		
			return false;

	}
	public static DefaultListModel get_non_revocked_users() {
		DefaultListModel model2=new DefaultListModel();

		query =
				"SELECT username FROM on_security_register where revocation='NO'";

		try {
			PreparedStatement stmt = con.prepareStatement(query);
			//stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery();

			while(rs.next())
			{

				model2.addElement(rs.getString(1));
			}
			rs.close();
			stmt.close();
		}

		catch (SQLException e ) {
			e.printStackTrace();

		} 

		return model2;
	}
	public static String get_user_id(String s) {

		query =
				"SELECT user_obj FROM on_security_register where username=?";
		String id="";
		try {
			PreparedStatement stmt = con.prepareStatement(query);
			stmt.setString(1, s);
			//stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery();

			while(rs.next())
			{
				id=rs.getString(1);
			}
			rs.close();
			stmt.close();
		}

		catch (SQLException e ) {
			e.printStackTrace();
		} 
		return id;
	}
	public static void update(String name, String user, String pass,String email, String ph) {
		PreparedStatement ps;
		String user_no = "user"+JDBC.no_of_rows();
		String	update_string="insert into on_security_register values(?,?,?,?,?)";
		try {
			ps = con.prepareStatement(update_string);
			ps.setString(1, name);
			ps.setString(2, user);
			ps.setString(3, pass);
		
			ps.setString(4, email);
			ps.setString(5, ph);

	
			ps.executeUpdate();
			System.out.println("inserted  successfully");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	public static boolean user_authenticate(String text, String text2) {

		query =
				"SELECT name FROM on_security_register where username=? and password=?";
		String id="";
		try {
			PreparedStatement stmt = con.prepareStatement(query);
			stmt.setString(1, text);
			stmt.setString(2, text2);
			//stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery();

			while(rs.next())
			{
				return true;
			}
			rs.close();
			stmt.close();
		}

		catch (SQLException e ) {
			e.printStackTrace();
		} 
		return false;
	}
	public static DefaultListModel get_files_list() 
	{
		DefaultListModel files = new DefaultListModel();
		query =
				"SELECT f_name FROM dac_files";
		String id="";
		try {
			PreparedStatement stmt = con.prepareStatement(query);

			ResultSet rs = stmt.executeQuery();

			while(rs.next())
			{
				files.addElement(rs.getString(1));
			}
			rs.close();
			stmt.close();
		}

		catch (SQLException e ) {
			e.printStackTrace();
			return files;
		} 

		return files;


	}
	public static void add_files(String fname, String DCS) {
		PreparedStatement ps;
		update_string="insert into  dac_files values (?,?)";
		try {
			ps = con.prepareStatement(update_string);
			ps.setString(1, fname);
			ps.setString(2, DCS);

			ps.executeUpdate();
			System.out.println("updated  successfully");

		} catch (SQLException e) {
			e.printStackTrace();

		}

	}
	public static boolean file_in_dsc1(String file_name) {
		query =
				"SELECT DISTRIBUTION_CLOUD_NO FROM dac_files where f_name=?";

		try {
			PreparedStatement stmt = con.prepareStatement(query);
			stmt.setString(1, file_name);
			ResultSet rs = stmt.executeQuery();

			while(rs.next())
			{
				if(rs.getString(1).equals("distributed cloud storage1"))
					return true;
				else
					return false;
			}
			rs.close();
			stmt.close();
		}

		catch (SQLException e ) {
			e.printStackTrace();
			return false;
		} 
		return false;
	}
	public static void insert_key(String name ,String file_name, String encr_access) {
		try{



			PreparedStatement pst=con.prepareStatement("insert into secure_access_insert_key values(?,?,?)");
			pst.setString(1,name);
			pst.setString(2,file_name);
			pst.setString(3,encr_access);
			

			pst.executeUpdate();
			System.out.println("inserted successfully");

		}catch(Exception e3){
			e3.printStackTrace();
		}
	}
	public static String check_access_policy(String file_name) {
		String query = "select access_key from secure_access_insert_key where file_name = ? ";
		String count ="" ;

		try {
			PreparedStatement stmt = con.prepareStatement(query);
			stmt.setString(1, file_name);
			ResultSet rs = stmt.executeQuery();
			while(rs.next())
			{
				count = rs.getString(1);

			}
			rs.close();
			stmt.close();

		}

		catch (SQLException e ) 
		{
			e.printStackTrace();
		}

		return count; 

	}
	public static boolean file_already_exits(String file_name) {
		String query = null ;
		int count=0;
		query =
				"select count(*) from dac_files where  f_name = ?";
		try {
			PreparedStatement stmt = con.prepareStatement(query);
			stmt.setString(1, file_name);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) 
			{
				count = rs.getInt(1);
			}
			stmt.close();
		}

		catch (SQLException e ) 
		{
			e.printStackTrace();
			
		}
		if(count==0)
			return true;
		else
			return false;
	}




	public static DefaultListModel get_revocked_users() {

		DefaultListModel user_list = new DefaultListModel();
		String val = null;
		String query = null ;

		query =
			"select username from on_security_register where revocation ='YES' ";
		try {
			PreparedStatement stmt = con.prepareStatement(query);
			ResultSet rs = stmt.executeQuery();
			while(rs.next())
			{
				user_list.addElement(rs.getString(1));
			}
			rs.close();
			stmt.close();
		}

		catch (SQLException e ) 
		{
			e.printStackTrace();
		} 
		return user_list;
	}
	public static void remove_revocation(String username)
	{
		String val = null;
		String query = null ;

		query =
			"update on_security_register set revocation=? where username=?  ";
		try {
			PreparedStatement stmt = con.prepareStatement(query);
			stmt.setString(1,"NO");
			stmt.setString(2,username);
			stmt.executeQuery();
			stmt.close();
			System.out.println(username+ " revocation status updated in database to not revoked");
		}

		catch (SQLException e ) 
		{
			e.printStackTrace();
		} 
	}
	public static String get_sec_key(String username) {
		
		String query = "select KEY from on_security_register where USERNAME = ? ";
		String key ="" ;

		try {
			PreparedStatement stmt = con.prepareStatement(query);
			stmt.setString(1, username);
			ResultSet rs = stmt.executeQuery();
			while(rs.next())
			{
				key = rs.getString(1);

			}
			rs.close();
			stmt.close();

		}

		catch (SQLException e ) 
		{
			e.printStackTrace();
		}

		return key; 

	}
	
	public static boolean  check_update_authority(String user_name)
	{
		query =
			"SELECT * FROM on_security_register where username=? and revocation=?";
	String id="";
	try {
		PreparedStatement stmt = con.prepareStatement(query);
		stmt.setString(1, user_name);
		stmt.setString(2, "NO");
		//stmt = con.createStatement();
		ResultSet rs = stmt.executeQuery();

		while(rs.next())
		{
			return true;
		}
		rs.close();
		stmt.close();
	}

	catch (SQLException e ) {
		e.printStackTrace();
	} 
	return false;
		
	
		
	}
	public static boolean owner_file_check(String user_name,String file_name) {
		query =
			"SELECT * FROM secure_access_insert_key where name=? and file_name=?";
	String id="";
	try {
		PreparedStatement stmt = con.prepareStatement(query);
		stmt.setString(1, user_name);
		stmt.setString(2,file_name);
		//stmt = con.createStatement();
		ResultSet rs = stmt.executeQuery();

		while(rs.next())
		{
			return true;
		}
		rs.close();
		stmt.close();
	}

	catch (SQLException e ) {
		e.printStackTrace();
	} 
		return false;
	}
	public static void update_insert_key(String message_access ,String file_name ) {
		
	
		String query = null ;

		query =
			"update secure_access_insert_key set access_key=? where file_name=?  ";
		try {
			PreparedStatement stmt = con.prepareStatement(query);
			stmt.setString(1,message_access);
			stmt.setString(2,file_name);
			
			stmt.executeQuery();
			stmt.close();
			System.out.println(file_name+ " file access key updated successfully by Data owner");
		}

		catch (SQLException e ) 
		{
			e.printStackTrace();
		} 

	}
	public static String Get_access_policy(String f_name) {
		
		
		String query = "select access_key from secure_access_insert_key where file_name = ? ";
		String Access_key ="" ;

		try {
			PreparedStatement stmt = con.prepareStatement(query);
			stmt.setString(1, f_name);
			ResultSet rs = stmt.executeQuery();
			while(rs.next())
			{
				Access_key = rs.getString(1);

			}
			rs.close();
			stmt.close();

		}

		catch (SQLException e ) 
		{
			e.printStackTrace();
		}

		return Access_key; 

		
	}
	
}
