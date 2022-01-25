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
import org.eclipse.swt.widgets.Composite;

public class Menu_Admin extends Global
{

	protected Shell shlMenuAdmin;
	private Text txtMenuAdministrateur;

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

		shlMenuAdmin.setText("Hôpital de Paris | Menuh");

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
		btnDconnexion.setBounds(193, 309, 116, 35);
		btnDconnexion.setText("D\u00E9connexion");
		
		Composite composite_1 = new Composite(shlMenuAdmin, SWT.NONE);
		composite_1.setBackground(SWTResourceManager.getColor(100, 149, 237));
		composite_1.setBounds(0, 0, 353, 45);
		
		txtMenuAdministrateur = new Text(composite_1, SWT.NONE);
		txtMenuAdministrateur.setText("Menu Administrateur\r\n");
		txtMenuAdministrateur.setForeground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		txtMenuAdministrateur.setFont(SWTResourceManager.getFont("Segoe UI Historic", 14, SWT.BOLD));
		txtMenuAdministrateur.setBackground(SWTResourceManager.getColor(SWT.COLOR_TRANSPARENT));
		txtMenuAdministrateur.setBounds(79, 10, 201, 25);
		
		Button btnDconnexion_1 = new Button(shlMenuAdmin, SWT.NONE);
		btnDconnexion_1.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
			}
		});
		btnDconnexion_1.setText("Utilisateurs");
		btnDconnexion_1.setForeground(SWTResourceManager.getColor(SWT.COLOR_BLACK));
		btnDconnexion_1.setFont(SWTResourceManager.getFont("Segoe UI", 9, SWT.NORMAL));
		btnDconnexion_1.setBounds(39, 66, 270, 35);
		
		Button btnDconnexion_1_1 = new Button(shlMenuAdmin, SWT.NONE);
		btnDconnexion_1_1.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
			}
		});
		btnDconnexion_1_1.setText("Comptes utilisateurs");
		btnDconnexion_1_1.setForeground(SWTResourceManager.getColor(SWT.COLOR_BLACK));
		btnDconnexion_1_1.setFont(SWTResourceManager.getFont("Segoe UI", 9, SWT.NORMAL));
		btnDconnexion_1_1.setBounds(39, 118, 270, 35);
		
		Button btnDconnexion_1_1_1 = new Button(shlMenuAdmin, SWT.NONE);
		btnDconnexion_1_1_1.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
			}
		});
		btnDconnexion_1_1_1.setText("Produits");
		btnDconnexion_1_1_1.setForeground(SWTResourceManager.getColor(SWT.COLOR_BLACK));
		btnDconnexion_1_1_1.setFont(SWTResourceManager.getFont("Segoe UI", 9, SWT.NORMAL));
		btnDconnexion_1_1_1.setBounds(39, 177, 270, 35);
		
		Button btnDconnexion_1_1_1_1 = new Button(shlMenuAdmin, SWT.NONE);
		btnDconnexion_1_1_1_1.setText("Commande des stocks");
		btnDconnexion_1_1_1_1.setForeground(SWTResourceManager.getColor(SWT.COLOR_BLACK));
		btnDconnexion_1_1_1_1.setFont(SWTResourceManager.getFont("Segoe UI", 9, SWT.NORMAL));
		btnDconnexion_1_1_1_1.setBounds(39, 236, 270, 35);
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
		
		Button btnMonCompte = new Button(shlMenuAdmin, SWT.NONE);
		btnMonCompte.setForeground(SWTResourceManager.getColor(SWT.COLOR_BLACK));
		btnMonCompte.setFont(SWTResourceManager.getFont("Segoe UI", 9, SWT.ITALIC));
		btnMonCompte.addSelectionListener(new SelectionAdapter()
		{
			@Override
			public void widgetSelected(SelectionEvent e)
			{
				shlMenuAdmin.close();
				try
				{
					Compte window = new Compte();
					window.open();
				}
				catch (Exception e1)
				{
					e1.printStackTrace();
				}
			}
		});
		btnMonCompte.setBounds(39, 309, 116, 35);
		btnMonCompte.setText("Mon compte");

	}
}
