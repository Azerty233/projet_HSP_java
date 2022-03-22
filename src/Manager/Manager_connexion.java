package Manager;

import javax.mail.Authenticator;
import javax.mail.Message;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.eclipse.swt.widgets.Shell;

import com.dbconnexion.*;

import Model.user;
import appli.Menu_Admin;
import appli.Menu_Administratif;
import appli.Menu_GEST;


public class Manager_connexion extends Global
{



	public boolean mdpOublie(String email, Shell shell) throws SQLException
	{

		Database db = new Database();
		Connection cnx = db.DbConnexion();
		String requete = "Select * from utilisateurs where email = ''"+email+"'";
		String role = "role";
		ResultSet resultat = db.Request(cnx, requete);
		while(resultat.next())
		{
			Globemail = resultat.getString("email");
			Globnom = resultat.getString("nom");
			if(resultat.getString(role).equals("ADMIN"))
			{
				Globadmin = true;
				try
				{ //Connexion en tant qu'Administrateur
					shell.close();
					Menu_Admin window_Admin = new Menu_Admin();
					window_Admin.open();
					return false;

				}
				catch (Exception e)
				{
					e.printStackTrace();
				}
			}
			else {
				Globemail = resultat.getString("email");
				Globnom = resultat.getString("nom");
				if(resultat.getString(role).equals("GEST"))
				{
					Globadmin = true;
					try
					{ //Connexion en tant qu'Administrateur
						shell.close();
						Menu_GEST window_GEST = new Menu_GEST();
						window_GEST.open();
						return false;

					}
					catch (Exception e)
					{
						e.printStackTrace();
					}
				}

			}


		}
		return true;

	}


	public boolean Connexion(String email, String mdp, Shell shell) throws SQLException
	{

		Database db = new Database();
		Connection cnx = db.DbConnexion();
		String requete = "Select * from utilisateurs where email = '"+email+"' and mdp = '"+mdp+"'";
		String role = "role";
		ResultSet resultat = db.Request(cnx, requete);
		while(resultat.next())
		{
			Globidentifiant = resultat.getString("id");
			Globnom = resultat.getString("nom");
			if(resultat.getString(role).equals("ADMIN"))
			{
				Globadmin = true;
				try
				{ //Connexion en tant qu'Administrateur
					shell.close();
					Menu_Admin window_Admin = new Menu_Admin();
					window_Admin.open();
					return false;

				}
				catch (Exception e)
				{
					e.printStackTrace();
				}
			}
			if(resultat.getString(role).equals("TRATIF"))
			{
				Globadmin = true;
				try
				{ //Connexion en tant qu'Administrateur
					shell.close();
					Menu_Administratif window_Administratif = new Menu_Administratif();
					window_Administratif.open();
					return false;

				}
				catch (Exception e)
				{
					e.printStackTrace();
				}
			}
			else {
				Globemail = resultat.getString("email");
				Globnom = resultat.getString("nom");
				if(resultat.getString(role).equals("GEST"))
				{
					Globadmin = true;
					try
					{ //Connexion en tant qu'Administrateur
						shell.close();
						Menu_GEST window_Admin = new Menu_GEST();
						window_Admin.open();
						return false;

					}
					catch (Exception e)
					{
						e.printStackTrace();
					}
				}

			}


		}
		return true;

	}


	public static void sendMail(String recipient, String mdp) throws Exception {
		Properties prop = new Properties();
		
		prop.put("mail.smtp.auth","true");
		prop.put("mail.smtp.starttls.enable","true");
		prop.put("mail.smtp.host","smtp.gmail.com");
		prop.put("mail.smtp.port","587");
		
		String emailSender = "ibrayoman02@gmail.com";
		String mdpSender = "ylpleqvqfevximzy";
		
		Session session = Session.getInstance(prop, new Authenticator() {
			@Override
			
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(emailSender, mdpSender);
			}
			
		});
		
		System.out.println(session);
		
		Message message = prepareMessage(session, emailSender, recipient, mdp);
		
		Transport.send(message);
			
			
	}


	private static Message prepareMessage(Session session, String email, String recipient, String mdp) {
		try {
			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress(email));
			message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(recipient));
			message.setSubject("HSP");
			message.setText("Voici votre nouveau mdp provisiore:" + mdp);
			return message;
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}


	public void ConnexionJUnit(user user) {
		// TODO Auto-generated method stub
		
	}
	
	


}

