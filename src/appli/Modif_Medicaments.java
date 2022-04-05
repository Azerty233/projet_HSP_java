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

public class Modif_Medicaments extends Global
{

	protected Shell shell;
	private Text textLibelle;
	private Text textNv_toxicite;
	private Text textStock;
	private String libelle;
	private String nv_toxicite;
	private String stock;



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
		shell.setText("Modifier son profil");

		Label lblLibelle = new Label(shell, SWT.NONE);
		lblLibelle.setBackground(SWTResourceManager.getColor(SWT.COLOR_TRANSPARENT));
		lblLibelle.setBounds(165, 121, 81, 25);
		lblLibelle.setText("Libelle");

		Label lblNv_toxicite = new Label(shell, SWT.NONE);
		lblNv_toxicite.setBackground(SWTResourceManager.getColor(SWT.COLOR_TRANSPARENT));
		lblNv_toxicite.setBounds(165, 179, 106, 25);
		lblNv_toxicite.setText("Niveau toxicit\u00E9");

		Label lblStock = new Label(shell, SWT.NONE);
		lblStock.setBackground(SWTResourceManager.getColor(SWT.COLOR_TRANSPARENT));
		lblStock.setBounds(165, 234, 81, 25);
		lblStock.setText("Stock");


		Label lblTitre = new Label(shell, SWT.NONE);
		lblTitre.setFont(SWTResourceManager.getFont("Segoe UI", 10, SWT.NORMAL));
		lblTitre.setBackground(SWTResourceManager.getColor(SWT.COLOR_TRANSPARENT));
		lblTitre.setBounds(277, 65, 192, 25);
		lblTitre.setText("Modifier le produit");

		textLibelle = new Text(shell, SWT.BORDER);
		textLibelle.setBounds(277, 121, 147, 31);

		textNv_toxicite = new Text(shell, SWT.BORDER);
		textNv_toxicite.setBounds(277, 176, 147, 31);

		textStock = new Text(shell, SWT.BORDER);
		textStock.setBounds(277, 231, 147, 31);


		Button btnValider = new Button(shell, SWT.NONE);
		btnValider.setBounds(306, 340, 105, 35);
		btnValider.setText("Valider");

		Button btnRetour = new Button(shell, SWT.NONE);
		btnRetour.setBounds(10, 10, 105, 35);
		btnRetour.setText("Retour");

		Database db = new Database();
		Connection cnx = db.DbConnexion();
		String requete = "Select libelle, niveau_toxicite, stock from medicaments where id = '"+Globidentifiant+"'";
		System.out.println(Globidentifiant);
		ResultSet resultat = db.Request(cnx, requete);
		while(resultat.next())
		{
			libelle = resultat.getString("libelle");
			nv_toxicite = resultat.getString("niveau_toxicite");
			stock = resultat.getString("stock");
		}

		Label lblErreur = new Label(shell, SWT.NONE);
		lblErreur.setBackground(SWTResourceManager.getColor(SWT.COLOR_TRANSPARENT));
		lblErreur.setForeground(SWTResourceManager.getColor(SWT.COLOR_RED));
		lblErreur.setBounds(277, 436, 253, 25);
		lblErreur.setText("Veuiller remplir tous les champs");
		lblErreur.setVisible(false);

		Label lblSucces = new Label(shell, SWT.NONE);
		lblSucces.setBackground(SWTResourceManager.getColor(SWT.COLOR_TRANSPARENT));
		lblSucces.setVisible(false);
		lblSucces.setText("Modifications enregistr\u00E9es");
		lblSucces.setForeground(SWTResourceManager.getColor(SWT.COLOR_GREEN));
		lblSucces.setBounds(277, 403, 253, 25);
		lblSucces.setVisible(false);

		btnValider.addSelectionListener(new SelectionAdapter()
		{
			@Override
			public void widgetSelected(SelectionEvent e)
			{
				String requete = "Update medicaments set libelle ='"+textLibelle.getText()+"', niveau_toxicite ='"+textNv_toxicite.getText()+"', stock ='"+textStock.getText()+"' where id = '"+Globidentifiant+"'";
				boolean message = db.Prepare(cnx, requete);
				lblErreur.setVisible(message);
				lblSucces.setVisible(!message);
				requete = "Select libelle, niveau_toxicite, stock from medicaments where id = '"+Globidentifiant+"'";
				ResultSet resultat = db.Request(cnx, requete);
				try
				{
					while(resultat.next())
					{
						libelle = resultat.getString("libelle");
						nv_toxicite = resultat.getString("nv_toxicite");
						stock = resultat.getString("stock");
					}
				}
				catch (SQLException e1)
				{
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				textLibelle.setText(libelle);
				textNv_toxicite.setText(nv_toxicite);
				textStock.setText(stock);
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
					Globnom = libelle;
					Menu_GEST window = new Menu_GEST();
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
