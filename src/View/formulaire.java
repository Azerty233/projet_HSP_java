package View;

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
import org.eclipse.swt.widgets.DateTime;

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
		txtNomMdicaments.setBounds(22, 123, 171, 37);
		
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
		txtQuantits.setBounds(22, 179, 122, 31);
		
		Button btnValider = new Button(shell, SWT.NONE);
		btnValider.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
			}
		});
		btnValider.setBounds(189, 260, 105, 35);
		btnValider.setText("Valider");
		
		text = new Text(shell, SWT.BORDER);
		text.setBackground(SWTResourceManager.getColor(SWT.COLOR_GRAY));
		text.setBounds(265, 129, 80, 31);
		
		text_1 = new Text(shell, SWT.BORDER);
		text_1.setBackground(SWTResourceManager.getColor(SWT.COLOR_GRAY));
		text_1.setBounds(265, 179, 80, 31);
		
		DateTime dateTime = new DateTime(shell, SWT.BORDER);
		dateTime.setBounds(167, 68, 125, 33);
	
	
	}

	protected Shell shell;
	private Text txtNomMdicaments;
	private Text txtCommandeDeMdicaments;
	private Text txtQuantits;
	private Text text;
	private Text text_1;

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
