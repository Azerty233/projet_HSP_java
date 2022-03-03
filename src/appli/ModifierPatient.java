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

import com.dbconnexion.Database;

import controller.Global;
import org.eclipse.wb.swt.SWTResourceManager;
import org.eclipse.swt.widgets.Combo;


public class ModifierPatient extends Global
{

	protected Shell shelleleve;
	private Text textNom;
	private Text textPrenom;
	private String nom;
	private String prenom;


	public void open() throws SQLException
	{
		Display display = Display.getDefault();
		createContents();
		shelleleve.open();
		shelleleve.layout();
		while (!shelleleve.isDisposed())
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
		shelleleve = new Shell();
		shelleleve.setBackground(SWTResourceManager.getColor(SWT.COLOR_TITLE_INACTIVE_BACKGROUND_GRADIENT));
		shelleleve.setSize(765, 559);
		shelleleve.setText("Modifier son profil");

		Label lblNom = new Label(shelleleve, SWT.NONE);
		lblNom.setBackground(SWTResourceManager.getColor(SWT.COLOR_TRANSPARENT));
		lblNom.setBounds(165, 123, 81, 25);
		lblNom.setText("Nom");

		Label lblPrenom = new Label(shelleleve, SWT.NONE);
		lblPrenom.setBackground(SWTResourceManager.getColor(SWT.COLOR_TRANSPARENT));
		lblPrenom.setBounds(165, 173, 81, 25);
		lblPrenom.setText("Pr\u00E9nom");

		textNom = new Text(shelleleve, SWT.BORDER);
		textNom.setBounds(277, 120, 147, 31);

		textPrenom = new Text(shelleleve, SWT.BORDER);
		textPrenom.setBounds(277, 170, 147, 31);

		Button btnValider = new Button(shelleleve, SWT.NONE);
		btnValider.setBounds(295, 223, 105, 35);
		btnValider.setText("Modifier");

		Button btnRetour = new Button(shelleleve, SWT.NONE);
		btnRetour.setBounds(10, 10, 105, 35);
		btnRetour.setText("Retour");
		btnRetour.addSelectionListener(new SelectionAdapter()
		{
			@Override
			public void widgetSelected(SelectionEvent e)
			{
				shelleleve.close();
				Globpatient = null;
				try
				{
					Utilisateurs_Administratif window = new Utilisateurs_Administratif();
					window.open();
				}
				catch (Exception e1)
				{
					e1.printStackTrace();
				}
			}
		});

		Database db = new Database();
		Connection cnx = db.DbConnexion();

		String requete = "Select * from patient";
		ResultSet resultat = db.Request(cnx, requete);
		ArrayList<Integer> classeList = new  ArrayList<Integer>();
		while(resultat.next()) {
			
			comboClasse.add(resultat.getString("nom"));
			classeList.add(resultat.getInt("id"));
		}
		requete = "Select nom, prenom from patient where id ='"+Globpatient+"'";
		resultat = db.Request(cnx, requete);
		while(resultat.next())
		{
			nom = resultat.getString("nom");
			prenom = resultat.getString("prenom");

		}
		textNom.setText(nom);
		textPrenom.setText(prenom);


		Label lblErreur = new Label(shelleleve, SWT.NONE);
		lblErreur.setBackground(SWTResourceManager.getColor(SWT.COLOR_TRANSPARENT));
		lblErreur.setForeground(SWTResourceManager.getColor(SWT.COLOR_RED));
		lblErreur.setBounds(227, 327, 253, 25);
		lblErreur.setText("Veuiller remplir tous les champs");
		lblErreur.setVisible(false);

		Label lblSucces = new Label(shelleleve, SWT.NONE);
		lblSucces.setBackground(SWTResourceManager.getColor(SWT.COLOR_TRANSPARENT));
		lblSucces.setVisible(false);
		lblSucces.setText("Modifications enregistr\u00E9es");
		lblSucces.setForeground(SWTResourceManager.getColor(SWT.COLOR_GREEN));
		lblSucces.setBounds(227, 358, 253, 25);
		lblSucces.setVisible(false);

		btnValider.addSelectionListener(new SelectionAdapter()
		{
			@Override
			public void widgetSelected(SelectionEvent e)
			{
				String requete = "Update patient set nom ='"+textNom.getText()+"', prenom ='"+textPrenom.getText()+"'";
				boolean message = db.Prepare(cnx, requete);
				lblErreur.setVisible(message);
				lblSucces.setVisible(!message);

			}
		});

		

	}
}
