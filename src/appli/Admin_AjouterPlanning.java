package appli;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.FormData;
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
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.custom.TableCursor;
import org.eclipse.swt.widgets.TableItem;

public class Admin_AjouterPlanning {

	protected Shell shlPlanning;

	/**
	 * Launch the application.
	 * @param args
	 */

	/**
	 * Open the window.
	 */
	public void open() {
		Display display = Display.getDefault();
		createContents();
		shlPlanning.open();
		shlPlanning.layout();
		while (!shlPlanning.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
	}

	/**
	 * Create contents of the window.
	 * @wbp.parser.entryPoint
	 */
	protected void createContents() {
		Database db = new Database();
		Connection cnx = db.DbConnexion();
		shlPlanning = new Shell();
		shlPlanning.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		shlPlanning.setSize(375, 563);
		shlPlanning.setText("Planning");

		Label lblAjoutErreur = new Label(shlPlanning, SWT.NONE);
		lblAjoutErreur.setVisible(false);
		lblAjoutErreur.setText("Veuiller remplir le champs");
		lblAjoutErreur.setForeground(SWTResourceManager.getColor(SWT.COLOR_RED));
		lblAjoutErreur.setBounds(16, 461, 217, 25);

		Label lblAjoutSucces = new Label(shlPlanning, SWT.NONE);
		lblAjoutSucces.setForeground(SWTResourceManager.getColor(SWT.COLOR_GREEN));
		lblAjoutSucces.setBounds(16, 461, 102, 25);
		lblAjoutSucces.setText("Ajout r\u00E9ussi");
		lblAjoutSucces.setVisible(false);

		Label lblType = new Label(shlPlanning, SWT.NONE);
		lblType.setBackground(SWTResourceManager.getColor(SWT.COLOR_TRANSPARENT));
		lblType.setBounds(16, 84, 81, 25);
		lblType.setText("Classe :");

		Combo comboType = new Combo(shlPlanning, SWT.READ_ONLY);
		comboType.setBackground(SWTResourceManager.getColor(255, 255, 255));
		comboType.setBounds(16, 115, 211, 33);
		comboType.select(0);

		String requete = "Select * from type_rdv";
		ResultSet resultat = db.Request(cnx, requete);
		ArrayList<Integer> classeList = new  ArrayList<Integer>();
		try {
			while(resultat.next())
			{

				comboType.add(resultat.getString("libelle"));
				classeList.add(resultat.getInt("id"));
			}
			comboType.select(0);
		} catch (SQLException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}

		Label lblMed = new Label(shlPlanning, SWT.NONE);
		lblMed.setBackground(SWTResourceManager.getColor(SWT.COLOR_TRANSPARENT));
		lblMed.setBounds(16, 141, 105, 25);
		lblMed.setText("Medecin :");
		
		Combo comboMed = new Combo(shlPlanning, SWT.READ_ONLY);
		comboMed.setBackground(SWTResourceManager.getColor(255, 255, 255));
		comboMed.setBounds(16, 172, 211, 33);
		requete = "Select * from utilisateurs where role = 'MED'";
		resultat = db.Request(cnx, requete);
		ArrayList<Integer> profList = new  ArrayList<Integer>();
		try {
			while(resultat.next())
			{
				comboMed.add(resultat.getString("nom"));
				profList.add(resultat.getInt("id"));
			}
			comboMed.select(0);
		} catch (SQLException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		
		ArrayList<Integer> heureList = new  ArrayList<Integer>();
		Button btnLundi = new Button(shlPlanning, SWT.RADIO);
		btnLundi.setBounds(16, 214, 81, 25);
		btnLundi.setText("Lundi");

		
		Button btnMardi = new Button(shlPlanning, SWT.RADIO);
		btnMardi.setBounds(103, 214, 81, 25);
		btnMardi.setText("Mardi");
		
		
		Button btnMercredi = new Button(shlPlanning, SWT.RADIO);
		btnMercredi.setBounds(190, 214, 105, 25);
		btnMercredi.setText("Mercredi");
		
		
		Button btnJeudi = new Button(shlPlanning, SWT.RADIO);
		btnJeudi.setBounds(16, 260, 76, 25);
		btnJeudi.setText("Jeudi");
		
		
		Button btnVendredi = new Button(shlPlanning, SWT.RADIO);
		btnVendredi.setBounds(103, 260, 105, 25);
		btnVendredi.setText("Vendredi");
		
				Button AjouterRDV = new Button(shlPlanning, SWT.NONE);
				AjouterRDV.setBounds(16, 312, 174, 35);
				AjouterRDV.setText("Ajouter un RDV");
				
				Label lblAjouterUnPlanning = new Label(shlPlanning, SWT.NONE);
				lblAjouterUnPlanning.setText("Ajouter un RDV");
				lblAjouterUnPlanning.setFont(SWTResourceManager.getFont("Segoe UI", 10, SWT.NORMAL));
				lblAjouterUnPlanning.setBackground(SWTResourceManager.getColor(SWT.COLOR_TRANSPARENT));
				lblAjouterUnPlanning.setBounds(114, 23, 121, 25);



	}
}