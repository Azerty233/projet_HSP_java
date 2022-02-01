package appli;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.Combo;

import com.dbconnexion.Database;

import controller.Global;
import org.eclipse.wb.swt.SWTResourceManager;
import org.eclipse.swt.widgets.Composite;

public class Comptes extends Global
{

	protected Shell shell;
	private Text textNom;
	private Text textPrenom;
	private String nom;
	private String prenom;
	private String email;
	private Text textEmail;
	private Text txtAjouterUnUtilisateur;
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
		shell.setText("Ajouter un utilisateur");

		Label lblNom = new Label(shell, SWT.NONE);
		lblNom.setBackground(SWTResourceManager.getColor(SWT.COLOR_TRANSPARENT));
		lblNom.setBounds(165, 121, 81, 25);
		lblNom.setText("Nom");

		Label lblPrnom = new Label(shell, SWT.NONE);
		lblPrnom.setBackground(SWTResourceManager.getColor(SWT.COLOR_TRANSPARENT));
		lblPrnom.setBounds(165, 173, 81, 25);
		lblPrnom.setText("Pr\u00E9nom");

		textNom = new Text(shell, SWT.BORDER);
		textNom.setBounds(277, 121, 147, 31);

		textPrenom = new Text(shell, SWT.BORDER);
		textPrenom.setBounds(277, 176, 147, 31);

		Button btnValider = new Button(shell, SWT.NONE);
		btnValider.setBounds(277, 352, 105, 35);
		btnValider.setText("Valider");

		Label lblErreur = new Label(shell, SWT.NONE);
		lblErreur.setBackground(SWTResourceManager.getColor(SWT.COLOR_TRANSPARENT));
		lblErreur.setForeground(SWTResourceManager.getColor(SWT.COLOR_RED));
		lblErreur.setBounds(277, 450, 253, 25);
		lblErreur.setText("Veuiller remplir tous les champs");
		lblErreur.setVisible(false);

		Label lblSucces = new Label(shell, SWT.NONE);
		lblSucces.setBackground(SWTResourceManager.getColor(SWT.COLOR_TRANSPARENT));
		lblSucces.setText("Ajout r\u00E9ussi !\r\nUn email contenant les acc\u00E8s vient d'\u00EAtre envoy\u00E9 \u00E0 l'utilisateur");
		lblSucces.setForeground(SWTResourceManager.getColor(SWT.COLOR_GREEN));
		lblSucces.setBounds(277, 402, 348, 42);
		lblSucces.setVisible(false);

		Label lblClasse = new Label(shell, SWT.NONE);
		lblClasse.setBackground(SWTResourceManager.getColor(SWT.COLOR_TRANSPARENT));
		lblClasse.setBounds(165, 321, 81, 25);
		lblClasse.setText("Role");

		Combo comboRole = new Combo(shell, SWT.READ_ONLY);
		comboRole.setItems(new String[] {"ADMIN", "GEST"});
		comboRole.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		comboRole.setBounds(277, 320, 147, 33);

		Database db = new Database();
		Connection cnx = db.DbConnexion();
		String requete = "Select * from utilisateurs";
		ResultSet resultat = db.Request(cnx, requete);
		ArrayList<Integer> roleList = new  ArrayList<Integer>();
		while(resultat.next())
		{

			comboRole.add(resultat.getString("nom"));
			roleList.add(resultat.getInt("id"));
		}
		comboRole.select(0);
		
		Label lblEmail = new Label(shell, SWT.NONE);
		lblEmail.setText("Email");
		lblEmail.setBackground(SWTResourceManager.getColor(SWT.COLOR_TRANSPARENT));
		lblEmail.setBounds(165, 230, 81, 25);
		
		textEmail = new Text(shell, SWT.BORDER);
		textEmail.setBounds(277, 233, 147, 31);
		
		Composite composite_1 = new Composite(shell, SWT.NONE);
		composite_1.setBackground(SWTResourceManager.getColor(100, 149, 237));
		composite_1.setBounds(0, 0, 752, 45);
		
		txtAjouterUnUtilisateur = new Text(composite_1, SWT.NONE);
		txtAjouterUnUtilisateur.setText("Ajouter un utilisateur");
		txtAjouterUnUtilisateur.setForeground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		txtAjouterUnUtilisateur.setFont(SWTResourceManager.getFont("Segoe UI Historic", 14, SWT.BOLD));
		txtAjouterUnUtilisateur.setBackground(SWTResourceManager.getColor(SWT.COLOR_TRANSPARENT));
		txtAjouterUnUtilisateur.setBounds(262, 10, 249, 25);
		
		textMDP = new Text(shell, SWT.BORDER);
		textMDP.setBounds(277, 281, 147, 31);
		
		Label lblMotDePasse = new Label(shell, SWT.NONE);
		lblMotDePasse.setText("Mot de passe");
		lblMotDePasse.setBackground(SWTResourceManager.getColor(SWT.COLOR_TRANSPARENT));
		lblMotDePasse.setBounds(165, 278, 81, 25);

		btnValider.addSelectionListener(new SelectionAdapter()
		{
			@Override
			public void widgetSelected(SelectionEvent e)
			{
				String requete = "INSERT into utilisateurs (nom, prenom, email, mdp, role) Values('"+textNom.getText()+"','"+textPrenom.getText()+"','"+textEmail.getText()+"', '"+textMDP.getText()+"',"+roleList.get(comboRole.getSelectionIndex())+")";
				boolean message = db.Prepare(cnx, requete);
				lblErreur.setVisible(message);
				lblSucces.setVisible(!message);
			}
		});

		

	}
}

