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

public class AjoutHospitalisation extends Global
{

	protected Shell Role;
	private String patient;
	private String numero_secu;
	private String mutuelle;
	private String adresse_postale;
	private Text textNiveau;
	private Text textPatient;
	private Text text_Commentaire;



	public void open() throws SQLException
	{
		Display display = Display.getDefault();
		createContents();
		Role.open();
		Role.layout();
		while (!Role.isDisposed())
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
		Role = new Shell();
		Role.setBackground(SWTResourceManager.getColor(SWT.COLOR_TITLE_INACTIVE_BACKGROUND_GRADIENT));
		Role.setSize(765, 559);
		Role.setText("Hospitalisation du patient");

		Label lblPatient = new Label(Role, SWT.NONE);
		lblPatient.setBackground(SWTResourceManager.getColor(SWT.COLOR_TRANSPARENT));
		lblPatient.setBounds(165, 76, 81, 25);
		lblPatient.setText("Patient");

		Button btnValider = new Button(Role, SWT.NONE);
		btnValider.setBounds(328, 261, 105, 35);
		btnValider.setText("Valider");

		Button btnRetour = new Button(Role, SWT.NONE);
		btnRetour.setBounds(10, 479, 105, 35);
		btnRetour.setText("Retour");

		Label lblErreur = new Label(Role, SWT.NONE);
		lblErreur.setBackground(SWTResourceManager.getColor(SWT.COLOR_TRANSPARENT));
		lblErreur.setForeground(SWTResourceManager.getColor(SWT.COLOR_RED));
		lblErreur.setBounds(234, 424, 253, 25);
		lblErreur.setText("Veuiller remplir tous les champs");
		lblErreur.setVisible(false);

		Label lblSucces = new Label(Role, SWT.NONE);
		lblSucces.setBackground(SWTResourceManager.getColor(SWT.COLOR_TRANSPARENT));
		lblSucces.setText("Ajout r\u00E9ussi");
		lblSucces.setForeground(SWTResourceManager.getColor(SWT.COLOR_DARK_GREEN));
		lblSucces.setBounds(234, 455, 253, 25);
		lblSucces.setVisible(false);

		Label lblNiveau = new Label(Role, SWT.NONE);
		lblNiveau.setBackground(SWTResourceManager.getColor(SWT.COLOR_TRANSPARENT));
		lblNiveau.setBounds(165, 163, 157, 25);
		lblNiveau.setText("Niveau chambre");
		Database db = new Database();
		Connection cnx = db.DbConnexion();
		String requete = "Select * from utilisateurs";
		
		
		
		textNiveau = new Text(Role, SWT.BORDER);
		textNiveau.setBounds(328, 161, 46, 27);
		
		Label lblMutuelle = new Label(Role, SWT.NONE);
		lblMutuelle.setText("Medicament s\u00E9lectionn\u00E9");
		lblMutuelle.setBackground(SWTResourceManager.getColor(SWT.COLOR_TRANSPARENT));
		lblMutuelle.setBounds(165, 210, 157, 25);
		
		Label lblCommentaire = new Label(Role, SWT.NONE);
		lblCommentaire.setText("Commentaire");
		lblCommentaire.setBackground(SWTResourceManager.getColor(SWT.COLOR_TRANSPARENT));
		lblCommentaire.setBounds(165, 119, 81, 25);
		
		text_Commentaire = new Text(Role, SWT.BORDER);
		text_Commentaire.setBounds(328, 117, 253, 27);
		
		Label lblPersonneCommanditaire = new Label(Role, SWT.NONE);
		lblPersonneCommanditaire.setText("Personne commanditaire");
		lblPersonneCommanditaire.setBackground(SWTResourceManager.getColor(SWT.COLOR_TRANSPARENT));
		lblPersonneCommanditaire.setBounds(165, 34, 144, 25);
		
		Combo comboCommanditaire = new Combo(Role, SWT.NONE);
		comboCommanditaire.setBounds(328, 34, 157, 20);
		Database db1 = new Database();
		Connection cnx1 = db1.DbConnexion();
		String requete1 = "Select * from utilisateurs WHERE role='MED' OR role='ADMIN'";
		ResultSet resultat = db1.Request(cnx1, requete1);
		ArrayList<Integer> CommanditaireList = new  ArrayList<Integer>();
		while(resultat.next())
		{

			comboCommanditaire.add(resultat.getString("nom"));
			CommanditaireList.add(resultat.getInt("id"));
		}
		
		Combo comboPatient = new Combo(Role, SWT.NONE);
		comboPatient.setBounds(328, 76, 157, 20);
		Database db2 = new Database();
		Connection cnx2 = db1.DbConnexion();
		String requete2 = "Select * from patient";
		ResultSet resultat2 = db2.Request(cnx2, requete2);
		ArrayList<Integer> PatientList = new  ArrayList<Integer>();
		while(resultat2.next())
		{

			comboPatient.add(resultat2.getString("nom"));
			PatientList.add(resultat2.getInt("id"));
		}
		
		Label lblChambre_1 = new Label(Role, SWT.NONE);
		lblChambre_1.setFont(SWTResourceManager.getFont("Segoe UI", 7, SWT.NORMAL));
		lblChambre_1.setText("1 : Chambre niveau 1\r\n2 : Chambre niveau 2\r\n3 : Chambre niveau 3");
		lblChambre_1.setBackground(SWTResourceManager.getColor(SWT.COLOR_TRANSPARENT));
		lblChambre_1.setBounds(380, 150, 157, 38);
		
		Combo comboMedicament = new Combo(Role, SWT.NONE);
		comboMedicament.setBounds(328, 210, 157, 20);
		Database db3 = new Database();
		Connection cnx3 = db1.DbConnexion();
		String requete3 = "Select * from medicaments";
		ResultSet resultat3 = db3.Request(cnx3, requete3);
		ArrayList<Integer> MedicamentsList = new  ArrayList<Integer>();
		while(resultat3.next())
		{

			comboMedicament.add(resultat3.getString("libelle"));
			MedicamentsList.add(resultat3.getInt("id"));
		}

		btnValider.addSelectionListener(new SelectionAdapter()
		{
			@Override
			public void widgetSelected(SelectionEvent e)
			{
				String requete = "INSERT into chambre (id_utilisateurs, id_patient, libelle, niveau, id_medicaments) Values("+CommanditaireList.get(comboCommanditaire.getSelectionIndex())+","+PatientList.get(comboPatient.getSelectionIndex())+",'"+text_Commentaire.getText()+"','"+textNiveau.getText()+"',"+MedicamentsList.get(comboMedicament.getSelectionIndex())+")";
				boolean message = db1.Prepare(cnx1, requete);
				lblErreur.setVisible(message);
				lblSucces.setVisible(!message);
			}
		});

		btnRetour.addSelectionListener(new SelectionAdapter()
		{
			@Override
			public void widgetSelected(SelectionEvent e)
			{
				Role.close();
				try
				{
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
