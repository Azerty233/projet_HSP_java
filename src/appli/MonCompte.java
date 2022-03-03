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

import Manager.Global;

import org.eclipse.wb.swt.SWTResourceManager;
import org.eclipse.swt.widgets.Composite;

public class MonCompte extends Global
{

	protected Shell shell;
	private String nom;
	private String prenom;
	private String email;
	private String mdp;
	private Text txtMonCompte;
	private Text textNom;
	private Text textPrenom;
	private Text textEmail;
	private Text textMDP;



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
		shell.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		shell.setSize(765, 559);
		shell.setText("Modifier mon compte");

		
		Composite composite_1 = new Composite(shell, SWT.NONE);
		composite_1.setBackground(SWTResourceManager.getColor(100, 149, 237));
		composite_1.setBounds(0, 0, 752, 45);
		
		txtMonCompte = new Text(composite_1, SWT.NONE);
		txtMonCompte.setText("Mon compte");
		txtMonCompte.setForeground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		txtMonCompte.setFont(SWTResourceManager.getFont("Segoe UI Historic", 14, SWT.BOLD));
		txtMonCompte.setBackground(SWTResourceManager.getColor(SWT.COLOR_TRANSPARENT));
		txtMonCompte.setBounds(301, 10, 249, 25);
		
		textNom = new Text(shell, SWT.BORDER);
		textNom.setBounds(244, 129, 215, 35);
		
		Label lblNom = new Label(shell, SWT.NONE);
		lblNom.setText("Nom");
		lblNom.setBackground(SWTResourceManager.getColor(SWT.COLOR_TRANSPARENT));
		lblNom.setBounds(244, 101, 68, 25);
		
		Text textPrenom = new Text(shell, SWT.BORDER);
		textPrenom.setBounds(244, 221, 215, 35);
		
		Label lblPrenom = new Label(shell, SWT.NONE);
		lblPrenom.setText("Pr\u00E9nom");
		lblPrenom.setBackground(SWTResourceManager.getColor(SWT.COLOR_TRANSPARENT));
		lblPrenom.setBounds(244, 193, 68, 25);
		
		textEmail = new Text(shell, SWT.BORDER);
		textEmail.setBounds(244, 310, 215, 35);
		
		Label lblEmail = new Label(shell, SWT.NONE);
		lblEmail.setText("Adresse email");
		lblEmail.setBackground(SWTResourceManager.getColor(SWT.COLOR_TRANSPARENT));
		lblEmail.setBounds(244, 282, 90, 25);
		
		textMDP = new Text(shell, SWT.BORDER);
		textMDP.setBounds(244, 401, 215, 35);
		
		Label lblMDP = new Label(shell, SWT.NONE);
		lblMDP.setText("Mot de passe");
		lblMDP.setBackground(SWTResourceManager.getColor(SWT.COLOR_TRANSPARENT));
		lblMDP.setBounds(244, 373, 90, 25);
		
		Button btnModifierMonProfil = new Button(shell, SWT.NONE);
		btnModifierMonProfil.setText("Modifier mon profil");
		btnModifierMonProfil.setForeground(SWTResourceManager.getColor(SWT.COLOR_BLACK));
		btnModifierMonProfil.setFont(SWTResourceManager.getFont("Rockwell", 9, SWT.BOLD));
		btnModifierMonProfil.setBounds(244, 467, 151, 35);

		Database db = new Database();
		Connection cnx = db.DbConnexion();
		String requete = "Select nom, prenom, email, mdp from utilisateurs";
		ResultSet resultat = db.Request(cnx, requete);
		while(resultat.next())
		{
			nom = resultat.getString("nom");
			prenom = resultat.getString("prenom");
			email = resultat.getString("email");
			mdp = resultat.getString("mdp");
	
		}
		textNom.setText(nom);
		textPrenom.setText(prenom);
		textEmail.setText(email);
		textMDP.setText(mdp);


		
		btnModifierMonProfil.addSelectionListener(new SelectionAdapter()
		{
			@Override
			public void widgetSelected(SelectionEvent e)
			{
				String requete = "Update utilisateurs set nom ='"+textNom.getText()+"', prenom ='"+textPrenom.getText()+"', email ='"+textEmail.getText()+"', mdp ='"+textMDP.getText()+"'";
				requete = "Select nom, prenom, email, mdp from utilisateurs";
				ResultSet resultat = db.Request(cnx, requete);
				try
				{
					while(resultat.next())
					{
						nom = resultat.getString("nom");
						prenom = resultat.getString("prenom");
						email = resultat.getString("email");
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
				textMDP.setText(mdp);
			}
		});
	}
}