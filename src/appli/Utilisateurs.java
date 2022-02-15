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

import controller.Global;
import org.eclipse.swt.widgets.Combo;

public class Utilisateurs extends Global
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
		shlListeUtilisateurs.setBackground(SWTResourceManager.getColor(SWT.COLOR_TITLE_INACTIVE_BACKGROUND_GRADIENT));
		shlListeUtilisateurs.setSize(802, 599);
		shlListeUtilisateurs.setText("Liste des utilisateurs");

		Composite composite = new Composite(shlListeUtilisateurs, SWT.BORDER);
		composite.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		composite.setBounds(332, 125, 385, 266);


		Label textNom = new Label(composite, SWT.NONE);
		textNom.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		textNom.setFont(SWTResourceManager.getFont("Segoe UI", 9, SWT.BOLD));
		textNom.setBounds(160, 26, 180, 35);

		Label textPrenom = new Label(composite, SWT.NONE);
		textPrenom.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		textPrenom.setFont(SWTResourceManager.getFont("Segoe UI", 9, SWT.BOLD));
		textPrenom.setBounds(160, 72, 180, 35);

		Label lblNom = new Label(composite, SWT.NONE);
		lblNom.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		lblNom.setText("Nom");
		lblNom.setBounds(62, 29, 67, 35);

		Label lblPrenom = new Label(composite, SWT.NONE);
		lblPrenom.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		lblPrenom.setText("Prenom");
		lblPrenom.setBounds(62, 77, 67, 35);
		
		Label lblEmail = new Label(composite, SWT.NONE);
		lblEmail.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		lblEmail.setText("Email");
		lblEmail.setBounds(62, 129, 67, 35);
		
		Label textEmail = new Label(composite, SWT.NONE);
		textEmail.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		textEmail.setFont(SWTResourceManager.getFont("Segoe UI", 9, SWT.BOLD));
		textEmail.setBounds(160, 120, 180, 35);

		Label lblError = new Label(shlListeUtilisateurs, SWT.NONE);
		lblError.setForeground(SWTResourceManager.getColor(SWT.COLOR_RED));
		lblError.setBounds(20, 529, 385, 25);
		lblError.setText("Veuillez selectionner un utilisateur en faisant un double clic ");
		lblError.setVisible(false);

		Label listeUtilisateur = new Label(shlListeUtilisateurs, SWT.NONE);
		listeUtilisateur.setBackground(SWTResourceManager.getColor(SWT.COLOR_TRANSPARENT));
		listeUtilisateur.setFont(SWTResourceManager.getFont("Segoe UI", 10, SWT.BOLD));
		listeUtilisateur.setBounds(20, 116, 191, 25);
		listeUtilisateur.setText("Liste des utilisateurs");

		table = new Table(shlListeUtilisateurs, SWT.BORDER | SWT.FULL_SELECTION);
		table.setBounds(20, 175, 219, 216);
		table.setHeaderVisible(true);
		table.setLinesVisible(true);

		TableColumn libelleNomUtil = new TableColumn(table, SWT.NONE);
		libelleNomUtil.setWidth(100);
		libelleNomUtil.setText("Nom");

		TableColumn libellePrenomUtil = new TableColumn(table, SWT.NONE);
		libellePrenomUtil.setWidth(100);
		libellePrenomUtil.setText("Pr\u00E9nom");

		TableColumn tblclmnId = new TableColumn(table, SWT.NONE);
		tblclmnId.setResizable(false);
		tblclmnId.setText("id");




		Button btnModifUtilisateur = new Button(shlListeUtilisateurs, SWT.NONE);
		btnModifUtilisateur.setBounds(469, 397, 125, 35);
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
					Modification window = new Modification();
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
		btnValider.setText("Afficher les utilisateurs");

		Button btnAjouterUtilisateur = new Button(shlListeUtilisateurs, SWT.NONE);
		btnAjouterUtilisateur.setBounds(332, 397, 119, 35);
		btnAjouterUtilisateur.setText("Ajouter un utilisateur");
		
		Button btnSupprimer = new Button(shlListeUtilisateurs, SWT.NONE);
		btnSupprimer.setText("Supprimer");
		btnSupprimer.setBounds(612, 397, 105, 35);

		btnSupprimer.addSelectionListener(new SelectionAdapter()
		{
			@Override
			public void widgetSelected(SelectionEvent e)
			{
				String requete = "Delete from utilisateurs where id ='"+Globidselection+"'";
				boolean message = db.Prepare(cnx, requete);
				
				Globidselection = Globidselection;
			
		        btnSupprimer.setEnabled(false);
				table.removeAll();
				String sql = "SELECT * FROM utilisateurs where id ";
				ResultSet res = db.Request(cnx, sql);

				try
				{
					int i = 0;
					while(res.next())
					{
						String id = Integer.toString(res.getInt("id"));
						String nom = res.getString("nom");
						String prenom = res.getString("prenom");
						String email = res.getString("email");
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
				String sql = "SELECT * FROM utilisateurs";
				ResultSet res = db.Request(cnx, sql);

				try
				{
					int i = 0;
					while(res.next())
					{
						String id = Integer.toString(res.getInt("id"));
						String nom = res.getString("nom");
						String prenom = res.getString("prenom");
						String email = res.getString("email");
						String role = res.getString("role");
						TableItem item = new TableItem(table, SWT.NONE , i);
					    item.setText(0, nom);
					    item.setText(1, prenom);
					    item.setText(2, email);
					    item.setText(4, id);
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
			        textEmail.setText(selection[i].getText(2));
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
				String requete = "Delete from utilisateurs where id ='"+Globidselection+"'";
				boolean message = db.Prepare(cnx, requete);

				Globidselection = null;
		        btnModifUtilisateur.setEnabled(false);
		        btnSupprimer.setEnabled(false);
				table.removeAll();
				String sql = "SELECT * FROM utilisateurs ";
				ResultSet res = db.Request(cnx, sql);

				try
				{
					int i = 0;
					while(res.next())
					{
						String id = Integer.toString(res.getInt("id"));
						String nom = res.getString("nom");
						String prenom = res.getString("prenom");
						String email = res.getString("email");
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

		btnAjouterUtilisateur.addSelectionListener(new SelectionAdapter()
		{
			@Override
			public void widgetSelected(SelectionEvent e)
			{
				shlListeUtilisateurs.close();
				try
				{
					AjoutCompte window = new AjoutCompte();
					window.open();
				}
				catch (Exception e1)
				{
					e1.printStackTrace();
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
						Comptes window = new Comptes();
						window.open();
					}
					catch (Exception e1)
					{
						e1.printStackTrace();
					}
				}
			});
		

		Button btnRetour = new Button(shlListeUtilisateurs, SWT.NONE);
		btnRetour.setBounds(10, 520, 105, 35);
		btnRetour.setText("Retour");
		btnRetour.addSelectionListener(new SelectionAdapter()
		{
			@Override
			public void widgetSelected(SelectionEvent e)
			{
				shlListeUtilisateurs.close();
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