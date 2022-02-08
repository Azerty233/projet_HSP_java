package controller;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.eclipse.swt.widgets.Shell;

import com.dbconnexion.*;

import appli.Menu_Admin;


public class Manager_connexion extends Global
{

	public boolean Connexion(String email, String mdp, Shell shell) throws SQLException
	{

		Database db = new Database();
		Connection cnx = db.DbConnexion();
		String requete = "Select * from utilisateurs where email = '"+email+"' and mdp = '"+mdp+"'";
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
					Menu_Admin window_Admin = new Menu_Admin();
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
}
