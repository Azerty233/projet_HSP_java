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

public class Planning extends Global
{

	protected Shell shell;
	private String nom;
	private String prenom;
	private Table table;



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
		shell.setText("Planning");

		Button btnRetour = new Button(shell, SWT.NONE);
		btnRetour.setBounds(22, 479, 105, 35);
		btnRetour.setText("Retour");

		Label lblSucces = new Label(shell, SWT.NONE);
		lblSucces.setBackground(SWTResourceManager.getColor(SWT.COLOR_TRANSPARENT));
		lblSucces.setText("Ajout r\u00E9ussi");
		lblSucces.setForeground(SWTResourceManager.getColor(SWT.COLOR_DARK_GREEN));
		lblSucces.setBounds(234, 455, 253, 25);
		lblSucces.setVisible(false);
		
		table = new Table(shell, SWT.BORDER | SWT.FULL_SELECTION);
		table.setBounds(22, 59, 720, 219);
		table.setHeaderVisible(true);
		table.setLinesVisible(true);
		
		TableColumn tableHeure = new TableColumn(table, SWT.NONE);
		tableHeure.setWidth(100);
		tableHeure.setText("Heure");
		
		TableColumn tableLundi = new TableColumn(table, SWT.NONE);
		tableLundi.setWidth(100);
		tableLundi.setText("Lundi");
		
		TableColumn tableMardi = new TableColumn(table, SWT.NONE);
		tableMardi.setWidth(100);
		tableMardi.setText("Mardi");
		
		TableColumn tableMercredi = new TableColumn(table, SWT.NONE);
		tableMercredi.setWidth(100);
		tableMercredi.setText("Mercredi");
		
		TableColumn tableJeudi = new TableColumn(table, SWT.NONE);
		tableJeudi.setWidth(100);
		tableJeudi.setText("Jeudi");
		
		TableColumn tableVendredi = new TableColumn(table, SWT.NONE);
		tableVendredi.setWidth(100);
		tableVendredi.setText("Vendredi");
		
		TableColumn tableSamedi = new TableColumn(table, SWT.NONE);
		tableSamedi.setWidth(100);
		tableSamedi.setText("Samedi");
		
		TableCursor tableCursor = new TableCursor(table, SWT.NONE);
		
		TableItem tableItem9h = new TableItem(table, SWT.NONE);
		tableItem9h.setText("9h");
		
		TableItem tableItem10h = new TableItem(table, SWT.NONE);
		tableItem10h.setBackground(SWTResourceManager.getColor(SWT.COLOR_TITLE_INACTIVE_BACKGROUND_GRADIENT));
		tableItem10h.setText("10h");
		
		TableItem tableItem11h = new TableItem(table, SWT.NONE);
		tableItem11h.setText("11h");
		
		TableItem tableItem12h = new TableItem(table, SWT.NONE);
		tableItem12h.setBackground(SWTResourceManager.getColor(SWT.COLOR_TITLE_INACTIVE_BACKGROUND_GRADIENT));
		tableItem12h.setText("12h");
		
		TableItem tableItem13h = new TableItem(table, SWT.NONE);
		tableItem13h.setText("13h");
		
		TableItem tableItem14h = new TableItem(table, SWT.NONE);
		tableItem14h.setBackground(SWTResourceManager.getColor(SWT.COLOR_TITLE_INACTIVE_BACKGROUND_GRADIENT));
		tableItem14h.setText("14h");
		
		TableItem tableItem15h = new TableItem(table, SWT.NONE);
		tableItem15h.setText("15h");
		
		TableItem tableItem16h = new TableItem(table, SWT.NONE);
		tableItem16h.setBackground(SWTResourceManager.getColor(SWT.COLOR_TITLE_INACTIVE_BACKGROUND_GRADIENT));
		tableItem16h.setText("16h");
		
		TableItem tableItem17h = new TableItem(table, SWT.NONE);
		tableItem17h.setText("17h");
		
		TableItem tableItem18h = new TableItem(table, SWT.NONE);
		tableItem18h.setBackground(SWTResourceManager.getColor(SWT.COLOR_TITLE_INACTIVE_BACKGROUND_GRADIENT));
		tableItem18h.setText("18h");
		Database db = new Database();
		Connection cnx = db.DbConnexion();
		
		Combo comboMed = new Combo(shell, SWT.NONE);
		comboMed.setBounds(564, 24, 178, 20);
		ArrayList<Integer> profList = new  ArrayList<Integer>();
		String requete = "Select * from utilisateurs where role = 'ADMIN'";
		ResultSet resultat = db.Request(cnx, requete);
		while(resultat.next()) {
			comboMed.add(resultat.getString("nom"));
			profList.add(resultat.getInt("id"));
		}
		comboMed.select(0);
		
		Button btnAjouter = new Button(shell, SWT.NONE);
		btnAjouter.setBounds(22, 24, 70, 21);
		btnAjouter.setText("Ajouter");
		btnAjouter.addSelectionListener(new SelectionAdapter()
		{
			@Override
			public void widgetSelected(SelectionEvent e)
			{
				try
				{
					Admin_AjouterPlanning window = new Admin_AjouterPlanning();
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
				shell.close();
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
