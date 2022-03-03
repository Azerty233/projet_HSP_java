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
import org.eclipse.swt.widgets.Composite;

public class Menu_GEST extends Global
{

	protected Shell shlMenuAdmin;

	/**
	* Launch the application.kjb kuj 
	* @param args
	*/
	/**
	* Ouvrir la fenetre.
	* @wbp.parser.entryPoint
	*/
	public void open()
	{
		Display display = Display.getDefault();
		createContents();
		shlMenuAdmin.open();
		shlMenuAdmin.layout();
		while (!shlMenuAdmin.isDisposed())
		{
			if (!display.readAndDispatch())
			{
				display.sleep();
			}
		}
	}

	/**
	* fondation du contenu de la fenetre.
	*/
	protected void createContents()
	{
		shlMenuAdmin = new Shell();
		shlMenuAdmin.setBackground(SWTResourceManager.getColor(SWT.COLOR_TITLE_INACTIVE_BACKGROUND_GRADIENT));
		shlMenuAdmin.setSize(366, 401);

		shlMenuAdmin.setText("Hopital de Paris | Espace Administrateur");

		Button btnDconnexion = new Button(shlMenuAdmin, SWT.NONE);
		btnDconnexion.setFont(SWTResourceManager.getFont("Segoe UI", 9, SWT.BOLD));
		btnDconnexion.setForeground(SWTResourceManager.getColor(SWT.COLOR_BLACK));
		btnDconnexion.addSelectionListener(new SelectionAdapter()
		{
			@Override
			public void widgetSelected(SelectionEvent e)
			{
				shlMenuAdmin.close();
				try
				{
					Connexion window = new Connexion();
					window.open();
				}
				catch (Exception e1)
				{
					e1.printStackTrace();
				}
			}
		});
		btnDconnexion.setBounds(115, 271, 116, 35);
		btnDconnexion.setText("D\u00E9connexion");
		
		Button btnDconnexion_1_2 = new Button(shlMenuAdmin, SWT.NONE);
		btnDconnexion_1_2.setText("Fiches de toxicit\u00E9");
		btnDconnexion_1_2.setForeground(SWTResourceManager.getColor(SWT.COLOR_BLACK));
		btnDconnexion_1_2.setFont(SWTResourceManager.getFont("Segoe UI", 9, SWT.NORMAL));
		btnDconnexion_1_2.setBounds(32, 37, 291, 35);
		
		Button btnDconnexion_1_3 = new Button(shlMenuAdmin, SWT.NONE);
		btnDconnexion_1_3.setText("Commandes des stocks");
		btnDconnexion_1_3.setForeground(SWTResourceManager.getColor(SWT.COLOR_BLACK));
		btnDconnexion_1_3.setFont(SWTResourceManager.getFont("Segoe UI", 9, SWT.NORMAL));
		btnDconnexion_1_3.setBounds(32, 120, 291, 35);
		
		Button btnDconnexion_1_4 = new Button(shlMenuAdmin, SWT.NONE);
		btnDconnexion_1_4.setText("Produits");
		btnDconnexion_1_4.setForeground(SWTResourceManager.getColor(SWT.COLOR_BLACK));
		btnDconnexion_1_4.setFont(SWTResourceManager.getFont("Segoe UI", 9, SWT.NORMAL));
		btnDconnexion_1_4.setBounds(32, 199, 291, 35);
		btnDconnexion_1_4.addSelectionListener(new SelectionAdapter()
		{
			@Override
			public void widgetSelected(SelectionEvent e)
			{
				shlMenuAdmin.close();
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