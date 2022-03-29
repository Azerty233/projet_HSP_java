package appli;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;
import org.eclipse.wb.swt.SWTResourceManager;

import com.dbconnexion.Database;

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
		shlPlanning.setBackground(SWTResourceManager.getColor(SWT.COLOR_TITLE_INACTIVE_BACKGROUND_GRADIENT));
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

		Label lblClasse = new Label(shlPlanning, SWT.NONE);
		lblClasse.setBackground(SWTResourceManager.getColor(SWT.COLOR_TRANSPARENT));
		lblClasse.setBounds(16, 84, 81, 25);
		lblClasse.setText("Classe :");

		Combo comboClasse = new Combo(shlPlanning, SWT.READ_ONLY);
		comboClasse.setBackground(SWTResourceManager.getColor(255, 255, 255));
		comboClasse.setBounds(16, 115, 211, 33);
		comboClasse.select(0);

		String requete = "Select * from type_rdv where undeletable = 0";
		ResultSet resultat = db.Request(cnx, requete);
		ArrayList<Integer> classeList = new  ArrayList<Integer>();
		try {
			while(resultat.next())
			{

				comboClasse.add(resultat.getString("libelle"));
				classeList.add(resultat.getInt("id"));
			}
			comboClasse.select(0);
		} catch (SQLException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		
		Combo comboHeure = new Combo(shlPlanning, SWT.READ_ONLY);
		comboHeure.setBackground(SWTResourceManager.getColor(255, 255, 255));
		comboHeure.setBounds(16, 298, 104, 33);

		Label lblProf = new Label(shlPlanning, SWT.NONE);
		lblProf.setBackground(SWTResourceManager.getColor(SWT.COLOR_TRANSPARENT));
		lblProf.setBounds(16, 141, 105, 25);
		lblProf.setText("Professeur :");
		
		Combo comboProf = new Combo(shlPlanning, SWT.READ_ONLY);
		comboProf.setBackground(SWTResourceManager.getColor(255, 255, 255));
		comboProf.setBounds(16, 172, 211, 33);
		requete = "Select * from utilisateurs where role = 'MED'";
		resultat = db.Request(cnx, requete);
		ArrayList<Integer> profList = new  ArrayList<Integer>();
		try {
			while(resultat.next())
			{
				comboProf.add(resultat.getString("nom"));
				profList.add(resultat.getInt("id"));
			}
			comboProf.select(0);
		} catch (SQLException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		
		ArrayList<Integer> heureList = new  ArrayList<Integer>();
		
		Label lblAjout = new Label(shlPlanning, SWT.NONE);
		lblAjout.setBackground(SWTResourceManager.getColor(SWT.COLOR_TRANSPARENT));
		lblAjout.setText("Ajout :");
		lblAjout.setBounds(16, 346, 81, 25);
		
		Label lblVue = new Label(shlPlanning, SWT.NONE);
		lblVue.setBounds(10, 377, 327, 25);
		Button btnLundi = new Button(shlPlanning, SWT.RADIO);
		btnLundi.setBounds(16, 214, 81, 25);
		btnLundi.setText("Lundi");
		btnLundi.addSelectionListener(new SelectionAdapter()  {
			 
            @Override
            public void widgetSelected(SelectionEvent e) {
                Button source=  (Button) e.getSource();
                 
                if(source.getSelection())  {
                    comboHeure.removeAll();
                    heureList.clear();
                    int jour = 1;
                    String requete = "SELECT DISTINCT heure.id, libelle FROM planning inner join heure where heure.id not in (SELECT id_heure FROM planning where id_utilisateurs = "+profList.get(comboProf.getSelectionIndex())+" AND id_jour = "+jour+" or id_classe = "+classeList.get(comboClasse.getSelectionIndex())+" AND id_jour = "+jour+")";
            		ResultSet resultat = db.Request(cnx, requete);
            		try {
						while(resultat.next())
						{
							comboHeure.add(resultat.getString("libelle"));
							heureList.add(resultat.getInt("id"));
						}
						comboHeure.select(0);
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
                }
            }
             
        });
		
		Button btnMardi = new Button(shlPlanning, SWT.RADIO);
		btnMardi.setBounds(103, 214, 81, 25);
		btnMardi.setText("Mardi");
		btnMardi.addSelectionListener(new SelectionAdapter()  {
			 
            @Override
            public void widgetSelected(SelectionEvent e) {
                Button source=  (Button) e.getSource();
                 
                if(source.getSelection())  {
                    comboHeure.removeAll();
                    heureList.clear();
                    int jour = 2;
                    String requete = "SELECT DISTINCT heure.id, libelle FROM planning inner join heure where heure.id not in (SELECT id_heure FROM planning where id_utilisateurs = "+profList.get(comboProf.getSelectionIndex())+" AND id_jour = "+jour+" or id_classe = "+classeList.get(comboClasse.getSelectionIndex())+" AND id_jour = "+jour+")";
            		ResultSet resultat = db.Request(cnx, requete);
            		try {
						while(resultat.next())
						{
							comboHeure.add(resultat.getString("libelle"));
							heureList.add(resultat.getInt("id"));
						}
						comboHeure.select(0);
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
                }
            }
             
        });
		
		Button btnMercredi = new Button(shlPlanning, SWT.RADIO);
		btnMercredi.setBounds(190, 214, 105, 25);
		btnMercredi.setText("Mercredi");
		btnMercredi.addSelectionListener(new SelectionAdapter()  {
			 
            @Override
            public void widgetSelected(SelectionEvent e) {
                Button source=  (Button) e.getSource();
                 
                if(source.getSelection())  {
                    comboHeure.removeAll();
                    heureList.clear();
                    int jour = 3;
                    String requete = "SELECT DISTINCT heure.id, libelle FROM planning inner join heure where heure.id not in (SELECT id_heure FROM planning where id_utilisateurs = "+profList.get(comboProf.getSelectionIndex())+" AND id_jour = "+jour+" or id_classe = "+classeList.get(comboClasse.getSelectionIndex())+" AND id_jour = "+jour+")";
            		ResultSet resultat = db.Request(cnx, requete);
            		try {
						while(resultat.next())
						{
							comboHeure.add(resultat.getString("libelle"));
							heureList.add(resultat.getInt("id"));
						}
						comboHeure.select(0);
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
                }
            }
             
        });
		
		Button btnJeudi = new Button(shlPlanning, SWT.RADIO);
		btnJeudi.setBounds(16, 260, 76, 25);
		btnJeudi.setText("Jeudi");
		btnJeudi.addSelectionListener(new SelectionAdapter()  {
			 
            @Override
            public void widgetSelected(SelectionEvent e) {
                Button source=  (Button) e.getSource();
                 
                if(source.getSelection())  {
                    comboHeure.removeAll();
                    heureList.clear();
                    int jour = 4;
                    String requete = "SELECT DISTINCT heure.id, libelle FROM planning inner join heure where heure.id not in (SELECT id_heure FROM planning where id_utilisateurs = "+profList.get(comboProf.getSelectionIndex())+" AND id_jour = "+jour+" or id_classe = "+classeList.get(comboClasse.getSelectionIndex())+" AND id_jour = "+jour+")";
            		ResultSet resultat = db.Request(cnx, requete);
            		try {
						while(resultat.next())
						{
							comboHeure.add(resultat.getString("libelle"));
							heureList.add(resultat.getInt("id"));
						}
						comboHeure.select(0);
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
                }
            }
             
        });
		
		Button btnVendredi = new Button(shlPlanning, SWT.RADIO);
		btnVendredi.setBounds(103, 260, 105, 25);
		btnVendredi.setText("Vendredi");
		
				Button AjouterPlanning = new Button(shlPlanning, SWT.NONE);
				AjouterPlanning.setBounds(16, 408, 174, 35);
				AjouterPlanning.setText("Ajouter un planning");
				
						AjouterPlanning.addSelectionListener(new SelectionAdapter()
						{
							@Override
							public void widgetSelected(SelectionEvent e)
							{
									classeList.clear();
									String requete = "INSERT into planning (id_jour, id_heure, id_classe, id_utilisateurs) Values('')";
									boolean message = db.Prepare(cnx, requete);
				
									requete = "Select * from type_rdv where undeletable = 0";
									ResultSet resultat = db.Request(cnx, requete);
									try {
										while(resultat.next()) {
											comboClasse.add(resultat.getString("libelle"));
											classeList.add(resultat.getInt("id"));
										}
										lblAjoutSucces.setVisible(true);
									} catch (SQLException e1) {
										// TODO Auto-generated catch block
										e1.printStackTrace();
									}
								}
				
				
				
						});
		btnVendredi.addSelectionListener(new SelectionAdapter()  {
			 
            @Override
            public void widgetSelected(SelectionEvent e) {
                Button source=  (Button) e.getSource();
                 
                if(source.getSelection())  {
                    comboHeure.removeAll();
                    heureList.clear();
                    int jour = 5;
                    String requete = "SELECT DISTINCT heure.id, libelle FROM planning inner join heure where heure.id not in (SELECT id_heure FROM planning where id_utilisateurs = "+profList.get(comboProf.getSelectionIndex())+" AND id_jour = "+jour+" or id_classe = "+classeList.get(comboClasse.getSelectionIndex())+" AND id_jour = "+jour+")";
            		ResultSet resultat = db.Request(cnx, requete);
            		try {
						while(resultat.next())
						{
							comboHeure.add(resultat.getString("libelle"));
							heureList.add(resultat.getInt("id"));
						}
						comboHeure.select(0);
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
                }
            }
             
        });


	}
}
