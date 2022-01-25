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

	import com.dbconnexion.Database;

	import controller.Global;
	import org.eclipse.wb.swt.SWTResourceManager;
	import org.eclipse.swt.widgets.Combo;

	public class modification extends Global
	{

		protected Shell shelleleve;
		private Text textNom;
		private Text textPrenom;
		private String nom;
		private String prenom;
		private int classe;


		public void open() throws SQLException
		{
			Display display = Display.getDefault();
			createContents();
			shelleleve.open();
			shelleleve.layout();
			while (!shelleleve.isDisposed())
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
			shelleleve = new Shell();
			shelleleve.setBackground(SWTResourceManager.getColor(SWT.COLOR_TITLE_INACTIVE_BACKGROUND_GRADIENT));
			shelleleve.setSize(765, 559);
			shelleleve.setText("Modifier son profil");

			Label lblNom = new Label(shelleleve, SWT.NONE);
			lblNom.setBackground(SWTResourceManager.getColor(SWT.COLOR_TRANSPARENT));
			lblNom.setBounds(165, 123, 81, 25);
			lblNom.setText("Nom");

			Label lblPrenom = new Label(shelleleve, SWT.NONE);
			lblPrenom.setBackground(SWTResourceManager.getColor(SWT.COLOR_TRANSPARENT));
			lblPrenom.setBounds(165, 173, 81, 25);
			lblPrenom.setText("Pr\u00E9nom");

			textNom = new Text(shelleleve, SWT.BORDER);
			textNom.setBounds(277, 120, 147, 31);

			textPrenom = new Text(shelleleve, SWT.BORDER);
			textPrenom.setBounds(277, 170, 147, 31);

			Button btnValider = new Button(shelleleve, SWT.NONE);
			btnValider.setBounds(298, 275, 105, 35);
			btnValider.setText("Modifier");

			

			Database db = new Database();
			Connection cnx = db.DbConnexion();


			Label lblErreur = new Label(shelleleve, SWT.NONE);
			lblErreur.setBackground(SWTResourceManager.getColor(SWT.COLOR_TRANSPARENT));
			lblErreur.setForeground(SWTResourceManager.getColor(SWT.COLOR_RED));
			lblErreur.setBounds(227, 327, 253, 25);
			lblErreur.setText("Veuiller remplir tous les champs");
			lblErreur.setVisible(false);

			Label lblSucces = new Label(shelleleve, SWT.NONE);
			lblSucces.setBackground(SWTResourceManager.getColor(SWT.COLOR_TRANSPARENT));
			lblSucces.setVisible(false);
			lblSucces.setText("Modifications enregistr\u00E9es");
			lblSucces.setForeground(SWTResourceManager.getColor(SWT.COLOR_GREEN));
			lblSucces.setBounds(227, 358, 253, 25);
			lblSucces.setVisible(false);



			

		}
	}

