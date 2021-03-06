package View;

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

import Manager.Global;

import org.eclipse.wb.swt.SWTResourceManager;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.eclipse.swt.widgets.Composite;

public class AjoutCompte extends Global
{

	protected Shell Role;
	private Text textNom;
	private Text textPrenom;
	private String nom;
	private String prenom;
	private Text textEmail;
	private Text textMdp;



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
		Role.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		Role.setSize(765, 559);
		Role.setText("Ajouter un utilisateur");

		Label lblNom = new Label(Role, SWT.NONE);
		lblNom.setBackground(SWTResourceManager.getColor(SWT.COLOR_TRANSPARENT));
		lblNom.setBounds(165, 121, 81, 25);
		lblNom.setText("Nom");

		Label lblPrnom = new Label(Role, SWT.NONE);
		lblPrnom.setBackground(SWTResourceManager.getColor(SWT.COLOR_TRANSPARENT));
		lblPrnom.setBounds(165, 173, 81, 25);
		lblPrnom.setText("Pr\u00E9nom");

		textNom = new Text(Role, SWT.BORDER);
		textNom.setBounds(277, 121, 147, 31);

		textPrenom = new Text(Role, SWT.BORDER);
		textPrenom.setBounds(277, 176, 147, 31);

		Button btnValider = new Button(Role, SWT.NONE);
		btnValider.setBounds(295, 383, 105, 35);
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
		lblRole.setBounds(165, 340, 81, 25);
		lblRole.setText("Role");

		Database db = new Database();
		Connection cnx = db.DbConnexion();
		
		Label lblEmail = new Label(Role, SWT.NONE);
		lblEmail.setText("Email");
		lblEmail.setBackground(SWTResourceManager.getColor(SWT.COLOR_TRANSPARENT));
		lblEmail.setBounds(165, 226, 81, 25);
		
		textEmail = new Text(Role, SWT.BORDER);
		textEmail.setBounds(277, 229, 147, 31);
		
		Label lblMdp = new Label(Role, SWT.NONE);
		lblMdp.setText("Mot de passe");
		lblMdp.setBackground(SWTResourceManager.getColor(SWT.COLOR_TRANSPARENT));
		lblMdp.setBounds(165, 277, 81, 25);
		
		textMdp = new Text(Role, SWT.BORDER);
		textMdp.setBounds(277, 280, 147, 31);
		
		Combo combo = new Combo(Role, SWT.NONE);
		combo.setItems(new String[] {});
		combo.setBounds(277, 340, 147, 20);
		Database db2 = new Database();
		Connection cnx2 = db2.DbConnexion();
		String requete2 = "Select * from utilisateurs";
		ResultSet resultat2 = db2.Request(cnx2, requete2);
		ArrayList<Integer> PatientList = new  ArrayList<Integer>();
		while(resultat2.next())
		{

			combo.add(resultat2.getString("nom"));
			PatientList.add(resultat2.getInt("id"));
		}
		
		
		textMdp = new Text(Role, SWT.BORDER);
		textMdp.setBounds(277, 277, 147, 31);

		btnValider.addSelectionListener(new SelectionAdapter()
		{
			@Override
			public void widgetSelected(SelectionEvent e)
			{
				String requete = "INSERT into utilisateurs (nom, prenom, email, mdp, role) Values('"+textNom.getText()+"','"+textPrenom.getText()+"','"+textEmail.getText()+"','"+textMdp.getText()+"',"+PatientList.get(combo.getSelectionIndex())+"')";
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