package View;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;
import org.eclipse.wb.swt.SWTResourceManager;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.swt.widgets.Menu;
import com.dbconnexion.*;

import Manager.Global;

import org.eclipse.swt.widgets.Combo;

public class Medicaments extends Global
{

	protected Shell shlListeUtilisateurs;
	private Table table;
	Database db = new Database();
	Connection cnx = db.DbConnexion();




	/**
	* Ouverture de la fenetre
	 * @throws SQLException
	*/
	public void open() throws SQLException
	{
		Display display = Display.getDefault();
		createContents();
		shlListeUtilisateurs.open();
		shlListeUtilisateurs.layout();
		while (!shlListeUtilisateurs.isDisposed())
		{
			if (!display.readAndDispatch())
			{
				display.sleep();
			}
		}
	}


	/**
	* @throws SQLException
	 * @wbp.parser.entryPoint
	*/
	protected void createContents() throws SQLException
	{

		shlListeUtilisateurs = new Shell();
		shlListeUtilisateurs.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		shlListeUtilisateurs.setSize(802, 599);
		shlListeUtilisateurs.setText("Liste des medicaments");

		Composite composite = new Composite(shlListeUtilisateurs, SWT.BORDER);
		composite.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		composite.setBounds(271, 110, 446, 281);


		Label textLibelle = new Label(composite, SWT.NONE);
		textLibelle.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		textLibelle.setFont(SWTResourceManager.getFont("Segoe UI", 9, SWT.BOLD));
		textLibelle.setBounds(160, 26, 180, 35);

		Label textNvToxicite = new Label(composite, SWT.NONE);
		textNvToxicite.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		textNvToxicite.setFont(SWTResourceManager.getFont("Segoe UI", 9, SWT.BOLD));
		textNvToxicite.setBounds(160, 72, 180, 35);

		Label lblNom = new Label(composite, SWT.NONE);
		lblNom.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		lblNom.setText("Nom");
		lblNom.setBounds(62, 29, 67, 35);

		Label lblPrenom = new Label(composite, SWT.NONE);
		lblPrenom.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		lblPrenom.setText("Lvl toxicit\u00E9");
		lblPrenom.setBounds(62, 77, 85, 35);
		
		Label lblEmail = new Label(composite, SWT.NONE);
		lblEmail.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		lblEmail.setText("Stock");
		lblEmail.setBounds(62, 129, 67, 35);
		
		Label textStock = new Label(composite, SWT.NONE);
		textStock.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		textStock.setFont(SWTResourceManager.getFont("Segoe UI", 9, SWT.BOLD));
		textStock.setBounds(160, 120, 180, 35);

		Label lblError = new Label(shlListeUtilisateurs, SWT.NONE);
		lblError.setForeground(SWTResourceManager.getColor(SWT.COLOR_RED));
		lblError.setBounds(20, 529, 385, 25);
		lblError.setText("Veuillez selectionner un utilisateur en faisant un double clic ");
		lblError.setVisible(false);

		table = new Table(shlListeUtilisateurs, SWT.BORDER | SWT.FULL_SELECTION);
		table.setBounds(20, 175, 219, 216);
		table.setHeaderVisible(true);
		table.setLinesVisible(true);

		TableColumn libelleNomUtil = new TableColumn(table, SWT.NONE);
		libelleNomUtil.setWidth(100);
		libelleNomUtil.setText("Nom");

		TableColumn libellePrenomUtil = new TableColumn(table, SWT.NONE);
		libellePrenomUtil.setWidth(100);
		libellePrenomUtil.setText("Stock");

		TableColumn tblclmnId = new TableColumn(table, SWT.NONE);
		tblclmnId.setResizable(false);
		tblclmnId.setText("id");




		Button btnModifMedicaments = new Button(shlListeUtilisateurs, SWT.NONE);
		btnModifMedicaments.setBounds(429, 397, 177, 35);
		btnModifMedicaments.setText("Modifier les produits");
		btnModifMedicaments.setVisible(true);
		btnModifMedicaments.setEnabled(false);
		btnModifMedicaments.addSelectionListener(new SelectionAdapter()
		{
			@Override
			public void widgetSelected(SelectionEvent e)
			{
				shlListeUtilisateurs.close();
				try
				{
					Modif_Medicaments window = new Modif_Medicaments();
					window.open();
				}
				catch (Exception e1)
				{
					e1.printStackTrace();
				}

			}
		});


		Button btnValider = new Button(shlListeUtilisateurs, SWT.NONE);
		btnValider.setBounds(20, 147, 219, 25);
		btnValider.setText("Afficher les medicaments");

		Button btnAjouterUtilisateur = new Button(shlListeUtilisateurs, SWT.NONE);
		btnAjouterUtilisateur.setBounds(256, 397, 167, 35);
		btnAjouterUtilisateur.setText("Ajouter un produits");
		
		Button btnSupprimer = new Button(shlListeUtilisateurs, SWT.NONE);
		btnSupprimer.setText("Supprimer");
		btnSupprimer.setBounds(617, 397, 105, 35);

		btnSupprimer.addSelectionListener(new SelectionAdapter()
		{
			@Override
			public void widgetSelected(SelectionEvent e)
			{
				String requete = "Delete from medicaments where id ='"+Globidselection+"'";
				boolean message = db.Prepare(cnx, requete);
				
				Globidselection = Globidselection;
			
		        btnSupprimer.setEnabled(false);
				table.removeAll();
				String sql = "SELECT * FROM medicaments where id ";
				ResultSet res = db.Request(cnx, sql);

				try
				{
					int i = 0;
					while(res.next())
					{
						String id = Integer.toString(res.getInt("id"));
						String libelle = res.getString("libelle");
						String niveau_toxicite = res.getString("niveau toxicite");
						String stock = res.getString("stock");
						TableItem item = new TableItem(table, SWT.NONE , i);
					    item.setText(0, libelle);
					    item.setText(1, niveau_toxicite);
					    item.setText(2, stock);
					    i++;
					}
				}
				
				
				catch (SQLException e2)
				{
					// TODO Auto-generated catch block
					e2.printStackTrace();
				}
			}
		});


		btnValider.addSelectionListener(new SelectionAdapter()
		{
			@Override
			public void widgetSelected(SelectionEvent e)
			{
				Globidselection = null;
		        btnModifMedicaments.setEnabled(false);
		        btnSupprimer.setEnabled(false);
				table.removeAll();
				String sql = "SELECT * FROM medicaments";
				ResultSet res = db.Request(cnx, sql);

				try
				{
					int i = 0;
					while(res.next())
					{
						String id = Integer.toString(res.getInt("id"));
						String libelle = res.getString("libelle");
						String nvToxicite = res.getString("Niveau_toxicite");
						String stock = res.getString("Stock");

						TableItem item = new TableItem(table, SWT.NONE , i);
					    item.setText(0, libelle);
					    item.setText(1, nvToxicite);
					    item.setText(2, stock);
					    i++;

					}
				}
				catch (SQLException e2)
				{
					// TODO Auto-generated catch block
					e2.printStackTrace();
				}
			}
		});
		table.addListener(SWT.DefaultSelection, new Listener()
		{
		      public void handleEvent(Event e)
					{
		        TableItem[] selection = table.getSelection();
		        for (int i = 0; i < selection.length; i++)
						{
		        	Globidselection = selection[i].getText(2);
			        textLibelle.setText(selection[i].getText(0));
			        textNvToxicite.setText(selection[i].getText(1));
			        textStock.setText(selection[i].getText(2));
			        btnModifMedicaments.setEnabled(true);
			        btnSupprimer.setEnabled(true);
		        }
		      }
		});

		btnSupprimer.addSelectionListener(new SelectionAdapter()
		{
			@Override
			public void widgetSelected(SelectionEvent e)
			{
				String requete = "Delete from medicaments where id ='"+Globidselection+"'";
				boolean message = db.Prepare(cnx, requete);

				Globidselection = null;
		        btnModifMedicaments.setEnabled(false);
		        btnSupprimer.setEnabled(false);
				table.removeAll();
				String sql = "SELECT * FROM medicaments ";
				ResultSet res = db.Request(cnx, sql);

				try
				{
					int i = 0;
					while(res.next())
					{
						String id = Integer.toString(res.getInt("id"));
						String libelle = res.getString("libelle");
						String nvToxicite = res.getString("niveau toxicit?");
						String stock = res.getString("stock");
						TableItem item = new TableItem(table, SWT.NONE , i);
					    item.setText(0, libelle);
					    item.setText(1, nvToxicite);
					    item.setText(2, stock);
					    i++;
					}
				}
				catch (SQLException e2)
				{
					// TODO Auto-generated catch block
					e2.printStackTrace();
				}
			}
		});

		btnAjouterUtilisateur.addSelectionListener(new SelectionAdapter()
		{
			@Override
			public void widgetSelected(SelectionEvent e)
			{
				shlListeUtilisateurs.close();
				try
				{
					AjoutMedicaments window = new AjoutMedicaments();
					window.open();
				}
				catch (Exception e1)
				{
					e1.printStackTrace();
				}
			}
		});




		btnModifMedicaments.addSelectionListener(new SelectionAdapter()
			{
				@Override
				public void widgetSelected(SelectionEvent e)
				{
					shlListeUtilisateurs.close();
					try
					{
						Modif_Medicaments window = new Modif_Medicaments();
						window.open();
					}
					catch (Exception e1)
					{
						e1.printStackTrace();
					}
				}
			});
		

		Button btnRetour = new Button(shlListeUtilisateurs, SWT.NONE);
		btnRetour.setBounds(20, 508, 105, 35);
		btnRetour.setText("Retour");
		btnRetour.addSelectionListener(new SelectionAdapter()
		{
			@Override
			public void widgetSelected(SelectionEvent e)
			{
				shlListeUtilisateurs.close();
				try
				{
					Menu_GEST window = new Menu_GEST();
					window.open();
				}
				catch (Exception e1)
				{
					e1.printStackTrace();
				}
			}

		});
		
		Label lblListeDesProduits = new Label(shlListeUtilisateurs, SWT.NONE);
		lblListeDesProduits.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		lblListeDesProduits.setFont(SWTResourceManager.getFont("Segoe UI", 12, SWT.BOLD));
		lblListeDesProduits.setBounds(20, 110, 208, 31);
		lblListeDesProduits.setText("Liste des produits");

	}
}