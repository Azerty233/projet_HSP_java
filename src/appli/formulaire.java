package appli;

import java.sql.SQLException;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;
import org.eclipse.wb.swt.SWTResourceManager;


import org.eclipse.swt.widgets.Composite;

public class formulaire
{

	/**
	* fondation du contenu de la fenetre
	*/
	protected void createContents()
	{
		shell = new Shell();
		shell.setBackground(SWTResourceManager.getColor(SWT.COLOR_DARK_MAGENTA));
		shell.setSize(494, 381);
		shell.setText("Hopital de Paris | Connexion");
		
		txtNomMdicaments = new Text(shell, SWT.BORDER);
		txtNomMdicaments.setBackground(SWTResourceManager.getColor(SWT.COLOR_GRAY));
		txtNomMdicaments.setText("Nom M\u00E9dicaments:");
		txtNomMdicaments.setBounds(22, 97, 171, 37);
		
		txtCommandeDeMdicaments = new Text(shell, SWT.BORDER);
		txtCommandeDeMdicaments.setBackground(SWTResourceManager.getColor(SWT.COLOR_GRAY));
		txtCommandeDeMdicaments.setForeground(SWTResourceManager.getColor(SWT.COLOR_BLACK));
		txtCommandeDeMdicaments.setText("Commande de m\u00E9dicaments:");
		txtCommandeDeMdicaments.setBounds(121, 21, 251, 31);
		
		txtQuantits = new Text(shell, SWT.BORDER);
		txtQuantits.setEditable(false);
		txtQuantits.setBackground(SWTResourceManager.getColor(SWT.COLOR_GRAY));
		txtQuantits.setForeground(SWTResourceManager.getColor(SWT.COLOR_BLACK));
		txtQuantits.setText("  Quantit\u00E9s:");
		txtQuantits.setBounds(24, 156, 122, 31);
		
		Button btnValider = new Button(shell, SWT.NONE);
		btnValider.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
			}
		});
		btnValider.setBounds(192, 258, 105, 35);
		btnValider.setText("Valider");
	
	
	}

	protected Shell shell;
	private Text txtNomMdicaments;
	private Text txtCommandeDeMdicaments;
	private Text txtQuantits;

	/**
	* Launch the application.
	* @param args
	*/
	public static void main(String[] args)
	{
		try
		{
			Connexion window = new Connexion();
			window.open();
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}

	/**
	* Ouverture de la fenetre.
	 * @wbp.parser.entryPoint
	*/
	public void open()
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
}
