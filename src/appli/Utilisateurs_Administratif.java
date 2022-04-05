package appli;

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

public class Utilisateurs_Administratif extends Global
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
		shlListeUtilisateurs.setText("Liste des utilisateurs");

		Composite composite = new Composite(shlListeUtilisateurs, SWT.BORDER);
		composite.setBounds(357, 167, 361, 266);


		Label textNom = new Label(composite, SWT.NONE);
		textNom.setFont(SWTResourceManager.getFont("Segoe UI", 9, SWT.BOLD));
		textNom.setBounds(160, 29, 180, 35);

		Label textPrenom = new Label(composite, SWT.NONE);
		textPrenom.setFont(SWTResourceManager.getFont("Segoe UI", 9, SWT.BOLD));
		textPrenom.setBounds(160, 77, 180, 35);

		Label lblNom = new Label(composite, SWT.NONE);
		lblNom.setText("Nom");
		lblNom.setBounds(62, 29, 67, 35);

		Label lblPrenom = new Label(composite, SWT.NONE);
		lblPrenom.setText("Prenom");
		lblPrenom.setBounds(62, 77, 67, 35);

		Label lblError = new Label(shlListeUtilisateurs, SWT.NONE);
		lblError.setForeground(SWTResourceManager.getColor(SWT.COLOR_RED));
		lblError.setBounds(285, 494, 385, 25);
		lblError.setText("Veuillez selectionner un \u00E9l\u00E8ve en double cliquant");
		lblError.setVisible(false);

		Label listeUtilisateur = new Label(shlListeUtilisateurs, SWT.NONE);
		listeUtilisateur.setBackground(SWTResourceManager.getColor(SWT.COLOR_TRANSPARENT));
		listeUtilisateur.setFont(SWTResourceManager.getFont("Segoe UI", 10, SWT.BOLD));
		listeUtilisateur.setBounds(40, 127, 191, 29);
		listeUtilisateur.setText("Liste des patients");

		table = new Table(shlListeUtilisateurs, SWT.BORDER | SWT.FULL_SELECTION);
		table.setBounds(20, 162, 218, 283);
		table.setHeaderVisible(true);
		table.setLinesVisible(true);

		TableColumn libelleNomEleve = new TableColumn(table, SWT.NONE);
		libelleNomEleve.setWidth(100);
		libelleNomEleve.setText("Nom");

		TableColumn libellePrenomEleve = new TableColumn(table, SWT.NONE);
		libellePrenomEleve.setWidth(100);
		libellePrenomEleve.setText("Pr\u00E9nom");

		TableColumn tblclmnId = new TableColumn(table, SWT.NONE);
		tblclmnId.setResizable(false);
		tblclmnId.setText("id");




		Button btnModifUtilisateur = new Button(shlListeUtilisateurs, SWT.NONE);
		btnModifUtilisateur.setBounds(357, 126, 175, 35);
		btnModifUtilisateur.setText("Modifier un utilisateur");
		btnModifUtilisateur.setVisible(true);
		btnModifUtilisateur.setEnabled(false);
		btnModifUtilisateur.addSelectionListener(new SelectionAdapter()
		{
			@Override
			public void widgetSelected(SelectionEvent e)
			{
				shlListeUtilisateurs.close();
				try
				{
					Modifier_patient window = new Modifier_patient();
					window.open();
				}
				catch (Exception e1)
				{
					e1.printStackTrace();
				}

			}
		});


		Button btnValider = new Button(shlListeUtilisateurs, SWT.NONE);
		btnValider.setBounds(20, 86, 211, 35);
		btnValider.setText("Afficher les patients");

		Button btnAjouterUtilisateur = new Button(shlListeUtilisateurs, SWT.NONE);
		btnAjouterUtilisateur.setBounds(543, 126, 175, 35);
		btnAjouterUtilisateur.addSelectionListener(new SelectionAdapter()
		{
			@Override
			public void widgetSelected(SelectionEvent e)
			{
				shlListeUtilisateurs.close();
				try
				{
					AjoutDossier window = new AjoutDossier();
					window.open();
				}
				catch (Exception e1)
				{
					e1.printStackTrace();
				}
			}
		});
		btnAjouterUtilisateur.setText("Ajouter un dossier patient");
		
		Button btnSupprimer = new Button(shlListeUtilisateurs, SWT.NONE);
		btnSupprimer.setText("Supprimer");
		btnSupprimer.setBounds(615, 439, 105, 35);

		btnSupprimer.addSelectionListener(new SelectionAdapter()
		{
			@Override
			public void widgetSelected(SelectionEvent e)
			{
				String requete = "Delete from patient where id ='"+Globidselection+"'";
				boolean message = db.Prepare(cnx, requete);
				
				Globidselection = Globidselection;
			
		        btnSupprimer.setEnabled(false);
				table.removeAll();
				String sql = "SELECT * FROM patient where id ";
				ResultSet res = db.Request(cnx, sql);

				try
				{
					int i = 0;
					while(res.next())
					{
						String id = Integer.toString(res.getInt("id"));
						String nom = res.getString("nom");
						String prenom = res.getString("prenom");
						TableItem item = new TableItem(table, SWT.NONE , i);
					    item.setText(0, nom);
					    item.setText(1, prenom);
					    item.setText(2, id);
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
		        btnModifUtilisateur.setEnabled(false);
		        btnSupprimer.setEnabled(false);
				table.removeAll();
				String sql = "SELECT * FROM patient";
				ResultSet res = db.Request(cnx, sql);

				try
				{
					int i = 0;
					while(res.next())
					{
						String id = Integer.toString(res.getInt("id"));
						String nom = res.getString("nom");
						String prenom = res.getString("prenom");
						TableItem item = new TableItem(table, SWT.NONE , i);
					    item.setText(0, nom);
					    item.setText(1, prenom);
					    item.setText(2, id);
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
			        textNom.setText(selection[i].getText(0));
			        textPrenom.setText(selection[i].getText(1));
			        btnModifUtilisateur.setEnabled(true);
			        btnSupprimer.setEnabled(true);
		        }
		      }
		});

		btnSupprimer.addSelectionListener(new SelectionAdapter()
		{
			@Override
			public void widgetSelected(SelectionEvent e)
			{
				String requete = "Delete from patient where id ='"+Globidselection+"'";
				boolean message = db.Prepare(cnx, requete);

				Globidselection = null;
		        btnModifUtilisateur.setEnabled(false);
		        btnSupprimer.setEnabled(false);
				table.removeAll();
				String sql = "SELECT * FROM patient ";
				ResultSet res = db.Request(cnx, sql);

				try
				{
					int i = 0;
					while(res.next())
					{
						String id = Integer.toString(res.getInt("id"));
						String nom = res.getString("nom");
						String prenom = res.getString("prenom");
						TableItem item = new TableItem(table, SWT.NONE , i);
					    item.setText(0, nom);
					    item.setText(1, prenom);
					    item.setText(2, id);
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




		btnModifUtilisateur.addSelectionListener(new SelectionAdapter()
			{
				@Override
				public void widgetSelected(SelectionEvent e)
				{
					shlListeUtilisateurs.close();
					try
					{
						Modifier_patient window = new Modifier_patient();
						window.open();
					}
					catch (Exception e1)
					{
						e1.printStackTrace();
					}
				}
			});
		

		Button btnRetour = new Button(shlListeUtilisateurs, SWT.NONE);
		btnRetour.setBounds(10, 519, 105, 35);
		btnRetour.setText("Retour");
		
		Button btnHospitaliserLePatient = new Button(shlListeUtilisateurs, SWT.NONE);
		btnHospitaliserLePatient.setFont(SWTResourceManager.getFont("Segoe UI", 12, SWT.BOLD));
		btnHospitaliserLePatient.setForeground(SWTResourceManager.getColor(SWT.COLOR_RED));
		btnHospitaliserLePatient.setText("Hospitaliser le patient");
		btnHospitaliserLePatient.setBounds(357, 439, 186, 35);
		btnHospitaliserLePatient.addSelectionListener(new SelectionAdapter()
		{
			@Override
			public void widgetSelected(SelectionEvent e)
			{
				shlListeUtilisateurs.close();
				try
				{
					AjoutHospitalisation window = new AjoutHospitalisation();
					window.open();
				}
				catch (Exception e1)
				{
					e1.printStackTrace();
				}
			}
		});
		btnRetour.addSelectionListener(new SelectionAdapter()
		{
			@Override
			public void widgetSelected(SelectionEvent e)
			{
				shlListeUtilisateurs.close();
				try
				{
					Menu_Administratif window = new Menu_Administratif();
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
