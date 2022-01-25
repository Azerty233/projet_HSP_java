package appli;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;

import com.dbconnexion.Database;

import controller.Global;
import org.eclipse.wb.swt.SWTResourceManager;

public class Compte extends Global
{

	protected Shell shell;
	private Text textNom;
	private Text textPrenom;
	private Text textEmail;
	private Text textIdentifiant;
	private String nom;
	private String prenom;
	private String email;
	private String identifiant;
	private String mdp;
	private Text textMotdePasse;



	public void open() throws SQLException
	{
		Display display = Display.getDefault();
		createContents();
		shell.open();
		shell.layout();
		while (!shell.isDisposed())
		{
			if (!display.readAndDispatch())
			{
				display.sleep();
			}
		}
	}

	/**
	* Fondation du contenu de la fenetre
	* @throws SQLException
	* @wbp.parser.entryPoint
	*/
	protected void createContents() throws SQLException
	{
		shell = new Shell();
		shell.setBackground(SWTResourceManager.getColor(SWT.COLOR_TITLE_INACTIVE_BACKGROUND_GRADIENT));
		shell.setSize(765, 559);
		shell.setText("Modifier professeur");

		Label lblNom = new Label(shell, SWT.NONE);
		lblNom.setBackground(SWTResourceManager.getColor(SWT.COLOR_TRANSPARENT));
		lblNom.setBounds(165, 121, 81, 25);
		lblNom.setText("Nom");

		Label lblPrnom = new Label(shell, SWT.NONE);
		lblPrnom.setBackground(SWTResourceManager.getColor(SWT.COLOR_TRANSPARENT));
		lblPrnom.setBounds(165, 178, 81, 25);
		lblPrnom.setText("Pr\u00E9nom");

		Label lblEmail = new Label(shell, SWT.NONE);
		lblEmail.setBackground(SWTResourceManager.getColor(SWT.COLOR_TRANSPARENT));
		lblEmail.setBounds(165, 231, 81, 25);
		lblEmail.setText("Email");

		Label lblIdentifiant = new Label(shell, SWT.NONE);
		lblIdentifiant.setBackground(SWTResourceManager.getColor(SWT.COLOR_TRANSPARENT));
		lblIdentifiant.setBounds(165, 284, 81, 25);
		lblIdentifiant.setText("Identifiant");

		textNom = new Text(shell, SWT.BORDER);
		textNom.setBounds(277, 121, 147, 31);

		textPrenom = new Text(shell, SWT.BORDER);
		textPrenom.setBounds(277, 176, 147, 31);

		textEmail = new Text(shell, SWT.BORDER);
		textEmail.setBounds(277, 231, 147, 31);

		textIdentifiant = new Text(shell, SWT.BORDER);
		textIdentifiant.setBounds(277, 287, 147, 31);
		
		textMotdePasse = new Text(shell, SWT.BORDER | SWT.PASSWORD);
		textMotdePasse.setBounds(277, 348, 147, 31);

		Button btnValider = new Button(shell, SWT.NONE);
		btnValider.setBounds(297, 406, 105, 35);
		btnValider.setText("Valider");

		Button btnRetour = new Button(shell, SWT.NONE);
		btnRetour.setBounds(10, 10, 105, 35);
		btnRetour.setText("Retour");

		Database db = new Database();
		Connection cnx = db.DbConnexion();
		String requete = "Select nom, prenom, email, identifiant, mdp from utilisateur  where id ='"+Globidselection+"'";
		ResultSet resultat = db.Request(cnx, requete);
		while(resultat.next())
		{
			nom = resultat.getString("nom");
			prenom = resultat.getString("prenom");
			email = resultat.getString("email");
			identifiant = resultat.getString("identifiant");
			mdp = resultat.getString("mdp");
	
		}
		textNom.setText(nom);
		textPrenom.setText(prenom);
		textEmail.setText(email);
		textIdentifiant.setText(identifiant);
		textMotdePasse.setText(mdp);

		Label lblErreur = new Label(shell, SWT.NONE);
		lblErreur.setForeground(SWTResourceManager.getColor(SWT.COLOR_RED));
		lblErreur.setBounds(297, 458, 253, 25);
		lblErreur.setText("Veuiller remplir tous les champs");
		lblErreur.setVisible(false);

		Label lblSucces = new Label(shell, SWT.NONE);
		lblSucces.setVisible(false);
		lblSucces.setText("Modifications enregistr\u00E9es");
		lblSucces.setForeground(SWTResourceManager.getColor(SWT.COLOR_GREEN));
		lblSucces.setBounds(297, 489, 253, 25);
		lblSucces.setVisible(false);
				
		Label lblMotDePasse = new Label(shell, SWT.NONE);
		lblMotDePasse.setBackground(SWTResourceManager.getColor(SWT.COLOR_TRANSPARENT));
		lblMotDePasse.setText("Mot de passe");
		lblMotDePasse.setBounds(165, 345, 81, 25);
		
		Label lblModifierUnProfesseur = new Label(shell, SWT.NONE);
		lblModifierUnProfesseur.setText("Modifier un professeur");
		lblModifierUnProfesseur.setFont(SWTResourceManager.getFont("Segoe UI", 10, SWT.NORMAL));
		lblModifierUnProfesseur.setBackground(SWTResourceManager.getColor(SWT.COLOR_TRANSPARENT));
		lblModifierUnProfesseur.setBounds(283, 50, 151, 25);
		
		Label lblMenu = new Label(shell, SWT.NONE);
		lblMenu.setText("Ma Gestion");
		lblMenu.setForeground(SWTResourceManager.getColor(SWT.COLOR_DARK_GREEN));
		lblMenu.setFont(SWTResourceManager.getFont("Segoe UI", 13, SWT.BOLD));
		lblMenu.setBackground(SWTResourceManager.getColor(SWT.COLOR_TRANSPARENT));
		lblMenu.setAlignment(SWT.CENTER);
		lblMenu.setBounds(273, 10, 151, 34);

		btnValider.addSelectionListener(new SelectionAdapter()
		{
			@Override
			public void widgetSelected(SelectionEvent e)
			{
				String requete = "Update utilisateur set nom ='"+textNom.getText()+"', prenom ='"+textPrenom.getText()+"', email ='"+textEmail.getText()+"', identifiant ='"+textIdentifiant.getText()+"', mdp ='"+textMotdePasse.getText()+"' where id = '"+Globidselection+"'";
				boolean message = db.Prepare(cnx, requete);
				lblErreur.setVisible(message);
				lblSucces.setVisible(!message);
				requete = "Select nom, prenom, email, identifiant, mdp from utilisateur where id = '"+Globidselection+"'";
				ResultSet resultat = db.Request(cnx, requete);
				try
				{
					while(resultat.next())
					{
						nom = resultat.getString("nom");
						prenom = resultat.getString("prenom");
						email = resultat.getString("email");
						identifiant = resultat.getString("identifiant");
						mdp = resultat.getString("mdp");
					}
				}
				catch (SQLException e1)
				{
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				textNom.setText(nom);
				textPrenom.setText(prenom);
				textEmail.setText(email);
				textIdentifiant.setText(identifiant);
				textMotdePasse.setText(mdp);
			}
		});

		btnRetour.addSelectionListener(new SelectionAdapter()
		{
			@Override
			public void widgetSelected(SelectionEvent e)
			{
				shell.close();
				try
				{
					Globnom = nom;
					Menu_Admin window = new Menu_Admin();
					window.open();
				}
				catch (Exception e1)
				{
					e1.printStackTrace();
				}
			}
		});

	}
}