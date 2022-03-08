package Manager;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

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


	public void Connexion(user email, user mdp, Shell shell) throws SQLException {
		
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
					return;

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
					return ;

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
						return ;

					}
					catch (Exception e)
					{
						e.printStackTrace();
					}
				}

			}


		}
		return ;

		
		
		
	}

}

