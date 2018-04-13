import java.util.Properties;
 
import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
 
public class Mail {
	
	 Mail(String email1, String key)
	 {
		 final String fromEmail = "raghuknowx@gmail.com"; //requires valid gmail id
	        final String password = "raghuknowx123"; // correct password for gmail id
	        final String toEmail = email1; // can be any email id 
	         
	        System.out.println("TLSEmail Start");
	        Properties props = new Properties();
	        props.put("mail.smtp.host", "smtp.gmail.com"); //SMTP Host
	        props.put("mail.smtp.port", "587"); //TLS Port
	        props.put("mail.smtp.auth", "true"); //enable authentication
	        props.put("mail.smtp.starttls.enable", "true"); //enable STARTTLS
	         
	                //create Authenticator object to pass in Session.getInstance argument
	        Authenticator auth = new Authenticator() {
	            //override the getPasswordAuthentication method
	            protected PasswordAuthentication getPasswordAuthentication() {
	                return new PasswordAuthentication(fromEmail, password);
	            }
	        };
	        Session session = Session.getInstance(props, auth);
	         
	        EmailUtil.sendEmail(session, toEmail,"secret key", key);
	 }
	
	
    /**
       Outgoing Mail (SMTP) Server
       requires TLS or SSL: smtp.gmail.com (use authentication)
       Use Authentication: Yes
       Port for TLS/STARTTLS: 587
     */
    public static void main(String[] args) {
        
         
    }
 
     
}