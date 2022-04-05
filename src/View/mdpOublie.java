package View;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Random;

import javax.swing.JOptionPane;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;
import org.eclipse.wb.swt.SWTResourceManager;

import com.dbconnexion.Database;

import Manager.Manager_connexion;

import org.eclipse.swt.widgets.Composite;

public class mdpOublie {

	/**
	 * fondation du contenu de la fen"etre
	 * 
	 * @wbp.parser.entryPoint
	 */
	protected void createContents() {
		shell = new Shell();
		shell.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		shell.setSize(494, 381);
		shell.setText("Hopital de Paris | Connexion");

		Label lblErreur = new Label(shell, SWT.NONE);
		lblErreur.setBackground(SWTResourceManager.getColor(SWT.COLOR_TRANSPARENT));
		lblErreur.setForeground(SWTResourceManager.getColor(SWT.COLOR_RED));
		lblErreur.setBounds(85, 260, 317, 25);
		lblErreur.setText("Erreur dans l'identifiant ou le mot de passe");
		lblErreur.setVisible(false);

		Composite composite_1 = new Composite(shell, SWT.NONE);
		composite_1.setBackground(SWTResourceManager.getColor(100, 149, 237));
		composite_1.setBounds(0, 0, 486, 45);

		txtEspaceHpitalDe = new Text(composite_1, SWT.NONE);
		txtEspaceHpitalDe.setForeground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		txtEspaceHpitalDe.setBackground(SWTResourceManager.getColor(SWT.COLOR_TRANSPARENT));
		txtEspaceHpitalDe.setFont(SWTResourceManager.getFont("Segoe UI Historic", 14, SWT.BOLD));
		txtEspaceHpitalDe.setText("Espace H\u00F4pital de Paris");
		txtEspaceHpitalDe.setBounds(130, 10, 249, 25);

		Composite composite_1_1 = new Composite(shell, SWT.NONE);
		composite_1_1.setBackground(SWTResourceManager.getColor(100, 149, 237));
		composite_1_1.setBounds(0, 301, 486, 45);

		txtApplication = new Text(composite_1_1, SWT.NONE);
		txtApplication.setText(
				"@2022 - Application mobile de l'h\u00F4pital de Paris.\r\nTous droits r\u00E9serv\u00E9s.\r\nEspace r\u00E9serv\u00E9 aux collaborateurs.");
		txtApplication.setForeground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		txtApplication.setFont(SWTResourceManager.getFont("Segoe UI Historic", 7, SWT.NORMAL));
		txtApplication.setBackground(SWTResourceManager.getColor(SWT.COLOR_TRANSPARENT));
		txtApplication.setBounds(10, 10, 449, 25);

		Label lblMotDePasse = new Label(shell, SWT.NONE);
		lblMotDePasse.setFont(SWTResourceManager.getFont("Segoe UI", 13, SWT.BOLD));
		lblMotDePasse.setBackground(SWTResourceManager.getColor(SWT.COLOR_TRANSPARENT));
		lblMotDePasse.setBounds(143, 104, 187, 30);
		lblMotDePasse.setText("Entrer votre Email");

		Button btnRenitialiserVotreMot = new Button(shell, SWT.NONE);
		btnRenitialiserVotreMot.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {

				String pstr = textEmail.getText();
				try {

					// Genere un nouveau mdp aleatoire de 12 caracteres
					int leftLimit = 48; // numeral '0'
					int rightLimit = 122; // letter 'z'
					int targetStringLength = 12;
					Random random = new Random();

					String generatedString = random.ints(leftLimit, rightLimit + 1)
							.filter(i -> (i <= 57 || i >= 65) && (i <= 90 || i >= 97)).limit(targetStringLength)
							.collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
							.toString();

					Manager_connexion sendMail = new Manager_connexion();
					Manager_connexion.sendMail(textEmail.getText(), generatedString);

					Database db = new Database();
					Connection cnx = db.DbConnexion();
					String requete = "UPDATE utilisateurs set mdp = '" + generatedString + "' WHERE email = '"
							+ textEmail.getText() + "'; ";
					boolean resultat = db.Prepare(cnx, requete);

				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnRenitialiserVotreMot.setFont(SWTResourceManager.getFont("Segoe UI", 9, SWT.BOLD));
		btnRenitialiserVotreMot.setBounds(117, 207, 233, 30);
		btnRenitialiserVotreMot.setText("Renitialiser votre Email");

		textEmail = new Text(shell, SWT.BORDER);
		textEmail.setBounds(117, 152, 233, 26);

	}

	protected Shell shell;
	private Text txtEspaceHpitalDe;
	private Text txtApplication;
	private Text textEmail;

	/**
	 * Launch the application.
	 * 
	 * @param args
	 * @wbp.parser.entryPoint
	 */
	public static void main(String[] args) {
		try {
			Connexion window = new Connexion();
			window.open();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Ouverture de la fenetre.
	 */
	public void open() {
		Display display = Display.getDefault();
		createContents();
		shell.open();
		shell.layout();
		while (!shell.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
	}
}
