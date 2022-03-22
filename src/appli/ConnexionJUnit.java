package appli;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JOptionPane;

import org.springframework.security.crypto.bcrypt.BCrypt;

import com.dbconnexion.Database;

import Model.user;

public class ConnexionJUnit {

	// Connection d'un utilisateur dans la BDD
	public user connexion(user Utilisateur) {
		try {
			System.out.print(Utilisateur.getMdp());
			Connection coBdd = Database.getInstance();
			java.sql.Statement stm = coBdd.createStatement();

			if (("".equals(Utilisateur.getMdp()) || Utilisateur.getMdp() == null) && ("".equals(Utilisateur.getEmail()) || Utilisateur.getEmail() == null)) {
				// Si les deux champs sont vides
				JOptionPane.showMessageDialog(null, "Champs vide.", "Erreur", JOptionPane.WARNING_MESSAGE);
				return null;
			} else if ("".equals(Utilisateur.getMdp()) || Utilisateur.getMdp() == null) {
				// Si le champ login est vide
				JOptionPane.showMessageDialog(null, "Mot de passe vide.", "Erreur", JOptionPane.WARNING_MESSAGE);
				return null;
			} else if ("".equals(Utilisateur.getEmail()) || Utilisateur.getEmail() == null) {
				// Si le champ login est vide
				JOptionPane.showMessageDialog(null, "Login vide.", "Erreur", JOptionPane.WARNING_MESSAGE);
				return null;
			}

			// Selectionne tout dans la table "Utilisateur"
			ResultSet info = stm
					.executeQuery("SELECT * FROM utilisateurs WHERE Login = '" + Utilisateur.getEmail() + "' LIMIT 1;");

			// Si le login n'existe pas
			if (!info.next()) {
				JOptionPane.showMessageDialog(null, "Le login n'existe pas.", "Erreur", JOptionPane.WARNING_MESSAGE);
				return null;
			}

			// Si le mot de passe ne correspond pas au login
			if (Utilisateur.getMdp() != info.getString("mdp")) {
				JOptionPane.showMessageDialog(null, "Le mot de passe est incorecte.", "Erreur",
						JOptionPane.WARNING_MESSAGE);
				return null;
			}

			// Si la requete trouve l'utilisateur, il garde les valeurs de "Statut" et "Id"
			//Utilisateur.setStatut(info.getInt("Statut"));
			//Utilisateur.setId(info.getInt("Id"));
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return Utilisateur;

	}
}
