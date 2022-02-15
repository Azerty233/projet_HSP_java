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

public class AjoutDossier extends Global
{

	protected Shell Role;
	private String patient;
	private String numero_secu;
	private String mutuelle;
	private String adresse_postale;
	private Text textAdresse;
	private Text textMutuelle;
	private Text textSecu;
	private Text textPatient;



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
		Role.setText("Ajouter un dossier Patient");

		Label lblNom = new Label(Role, SWT.NONE);
		lblNom.setBackground(SWTResourceManager.getColor(SWT.COLOR_TRANSPARENT));
		lblNom.setBounds(165, 121, 81, 25);
		lblNom.setText("Nom");

		Button btnValider = new Button(Role, SWT.NONE);
		btnValider.setBounds(328, 310, 105, 35);
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

		Label lblRole = new Label(Role, SWT.NONE);
		lblRole.setBackground(SWTResourceManager.getColor(SWT.COLOR_TRANSPARENT));
		lblRole.setBounds(165, 212, 157, 25);
		lblRole.setText("Num\u00E9ro de s\u00E9curit\u00E9 sociale");

		Combo comboPatient = new Combo(Role, SWT.NONE);
		comboPatient.setBounds(328, 120, 147, 20);
		Database db = new Database();
		Connection cnx = db.DbConnexion();
		String requete = "Select * from utilisateurs";
		ResultSet resultat = db.Request(cnx, requete);
		ArrayList<Integer> PatientList = new  ArrayList<Integer>();
		while(resultat.next())
		{

			comboPatient.add(resultat.getString("nom"));
			PatientList.add(resultat.getInt("id"));
		}
		
		Label lblAdd = new Label(Role, SWT.NONE);
		lblAdd.setText("Adresse postale");
		lblAdd.setBackground(SWTResourceManager.getColor(SWT.COLOR_TRANSPARENT));
		lblAdd.setBounds(165, 168, 94, 25);
		
		textAdresse = new Text(Role, SWT.BORDER);
		textAdresse.setBounds(328, 166, 253, 27);
		
		
		
		textSecu = new Text(Role, SWT.BORDER);
		textSecu.setBounds(328, 210, 202, 27);
		
		Label lblMutuelle = new Label(Role, SWT.NONE);
		lblMutuelle.setText("Mutuelle");
		lblMutuelle.setBackground(SWTResourceManager.getColor(SWT.COLOR_TRANSPARENT));
		lblMutuelle.setBounds(165, 259, 157, 25);
		
		textMutuelle = new Text(Role, SWT.BORDER);
		textMutuelle.setBounds(328, 256, 202, 27);

		btnValider.addSelectionListener(new SelectionAdapter()
		{
			@Override
			public void widgetSelected(SelectionEvent e)
			{
				String requete = "INSERT into patient (patient, adresse_postale, numero_secu, mutuelle) Values('"+PatientList.get(comboPatient.getSelectionIndex())+"','"+textAdresse.getText()+"','"+textSecu.getText()+"','"+textMutuelle.getText()+"')";
				boolean message = db.Prepare(cnx, requete);
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
