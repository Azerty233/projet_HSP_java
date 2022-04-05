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

import Manager.Global;

import org.eclipse.wb.swt.SWTResourceManager;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.eclipse.swt.widgets.Composite;

public class AjoutMedicaments extends Global
{

	protected Shell Role;
	private Text textLibelle;
	private Text textNv_toxicite;
	private Text textStock;



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
		Role.setText("Ajouter un utilisateur");

		Label lblNom = new Label(Role, SWT.NONE);
		lblNom.setBackground(SWTResourceManager.getColor(SWT.COLOR_TRANSPARENT));
		lblNom.setBounds(165, 121, 81, 25);
		lblNom.setText("libelle");

		Label lblPrnom = new Label(Role, SWT.NONE);
		lblPrnom.setBackground(SWTResourceManager.getColor(SWT.COLOR_TRANSPARENT));
		lblPrnom.setBounds(165, 173, 106, 25);
		lblPrnom.setText("niveau_toxicit\u00E9");

		textLibelle = new Text(Role, SWT.BORDER);
		textLibelle.setBounds(277, 121, 147, 31);

		textNv_toxicite = new Text(Role, SWT.BORDER);
		textNv_toxicite.setBounds(277, 176, 147, 31);

		Button btnValider = new Button(Role, SWT.NONE);
		btnValider.setBounds(294, 284, 105, 35);
		btnValider.setText("Valider");

		Button btnRetour = new Button(Role, SWT.NONE);
		btnRetour.setBounds(10, 448, 105, 35);
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

		Database db = new Database();
		Connection cnx = db.DbConnexion();
		
		Label lblEmail = new Label(Role, SWT.NONE);
		lblEmail.setText("stock");
		lblEmail.setBackground(SWTResourceManager.getColor(SWT.COLOR_TRANSPARENT));
		lblEmail.setBounds(165, 226, 81, 25);
		
		textStock = new Text(Role, SWT.BORDER);
		textStock.setBounds(277, 229, 147, 31);

		btnValider.addSelectionListener(new SelectionAdapter()
		{
			@Override
			public void widgetSelected(SelectionEvent e)
			{
				String requete = "INSERT into medicaments (libelle, niveau_toxicite, stock) Values('"+textLibelle.getText()+"','"+textNv_toxicite.getText()+"','"+textStock.getText()+"')";
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
					Medicaments window = new Medicaments();
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