package appli;

import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;

import controller.Global;
import org.eclipse.wb.swt.SWTResourceManager;

public class Menu_Admin extends Global
{

	protected Shell shlMenuAdmin;

	/**
	* Launch the application.
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

		shlMenuAdmin.setText("Hôpital de Paris | Menu");

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
		btnDconnexion.setBounds(207, 308, 116, 35);
		btnDconnexion.setText("D\u00E9connexion");
		
		Button btnDconnexion_1 = new Button(shlMenuAdmin, SWT.NONE);
		btnDconnexion_1.setText("Utilisateurs");
		btnDconnexion_1.setForeground(SWTResourceManager.getColor(SWT.COLOR_BLACK));
		btnDconnexion_1.setFont(SWTResourceManager.getFont("Segoe UI", 9, SWT.NORMAL));
		btnDconnexion_1.setBounds(32, 23, 291, 35);
		btnDconnexion_1.addSelectionListener(new SelectionAdapter()
		{
			@Override
			public void widgetSelected(SelectionEvent e)
			{
				shlMenuAdmin.close();
				try
				{
					Utilisateurs window = new Utilisateurs();
					window.open();
				}
				catch (Exception e1)
				{
					e1.printStackTrace();
				}
			}
		});
		
		Button btnDconnexion_1_1 = new Button(shlMenuAdmin, SWT.NONE);
		btnDconnexion_1_1.setText("Comptes");
		btnDconnexion_1_1.setForeground(SWTResourceManager.getColor(SWT.COLOR_BLACK));
		btnDconnexion_1_1.setFont(SWTResourceManager.getFont("Segoe UI", 9, SWT.NORMAL));
		btnDconnexion_1_1.setBounds(32, 73, 291, 35);
		btnDconnexion_1_1.addSelectionListener(new SelectionAdapter()
		{
			@Override
			public void widgetSelected(SelectionEvent e)
			{
				shlMenuAdmin.close();
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
		
		Button btnDconnexion_1_2 = new Button(shlMenuAdmin, SWT.NONE);
		btnDconnexion_1_2.setText("Fiches de toxicit\u00E9");
		btnDconnexion_1_2.setForeground(SWTResourceManager.getColor(SWT.COLOR_BLACK));
		btnDconnexion_1_2.setFont(SWTResourceManager.getFont("Segoe UI", 9, SWT.NORMAL));
		btnDconnexion_1_2.setBounds(32, 121, 291, 35);
		
		Button btnDconnexion_1_3 = new Button(shlMenuAdmin, SWT.NONE);
		btnDconnexion_1_3.setText("Commandes des stocks");
		btnDconnexion_1_3.setForeground(SWTResourceManager.getColor(SWT.COLOR_BLACK));
		btnDconnexion_1_3.setFont(SWTResourceManager.getFont("Segoe UI", 9, SWT.NORMAL));
		btnDconnexion_1_3.setBounds(32, 173, 291, 35);
		
		Button btnDconnexion_1_4 = new Button(shlMenuAdmin, SWT.NONE);
		btnDconnexion_1_4.setText("Produits");
		btnDconnexion_1_4.setForeground(SWTResourceManager.getColor(SWT.COLOR_BLACK));
		btnDconnexion_1_4.setFont(SWTResourceManager.getFont("Segoe UI", 9, SWT.NORMAL));
		btnDconnexion_1_4.setBounds(32, 224, 291, 35);
		
		Button btnMonCompte = new Button(shlMenuAdmin, SWT.NONE);
		btnMonCompte.setText("Mon compte");
		btnMonCompte.setForeground(SWTResourceManager.getColor(SWT.COLOR_BLACK));
		btnMonCompte.setFont(SWTResourceManager.getFont("Segoe UI", 9, SWT.BOLD));
		btnMonCompte.setBounds(32, 308, 116, 35);
		
		btnMonCompte.addSelectionListener(new SelectionAdapter()
		{
			@Override
			public void widgetSelected(SelectionEvent e)
			{
				shlMenuAdmin.close();
				try
				{
					MonCompte window = new MonCompte();
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
