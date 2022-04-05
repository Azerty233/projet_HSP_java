package appli;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import javax.swing.UIManager;

import Manager.Manager_connexion;

import javax.swing.GroupLayout.Alignment;
import javax.swing.GroupLayout;
import javax.swing.LayoutStyle.ComponentPlacement;

import org.eclipse.swt.widgets.Shell;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class comboBoxMedicaments extends javax.swing.JFrame {

  String id="";
  
  
	protected Shell shlMenuAdmin;


    public comboBoxMedicaments() {
        initComponents();
    }

   private void retrieve()
   {
       DefaultComboBoxModel dm=new Manager_connexion().recuperer();
       jComboBox1.setModel(dm);
   }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">
    private void initComponents() {

        jLabel2 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jComboBox1 = new javax.swing.JComboBox();
        jLabel1 = new javax.swing.JLabel();
        nameTxt = new javax.swing.JTextField();
        addBtn = new javax.swing.JButton();
        retrieveBtn = new javax.swing.JButton();
        clearBtn = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel2.setText("Liste des medicaments");

        jPanel1.setBackground(new java.awt.Color(45, 155, 193));

        jComboBox1.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jComboBox1ItemStateChanged(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(41, 41, 41)
                .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(66, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(58, 58, 58)
                .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(64, Short.MAX_VALUE))
        );

        jLabel1.setText("medicament s\u00E9lectionn\u00E9");

        addBtn.setText("Selectionner");
        addBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addBtnActionPerformed(evt);
            }
        });

        retrieveBtn.setText("afficher les medicaments");
        retrieveBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                retrieveBtnActionPerformed(evt);
            }
        });

        clearBtn.setText("Annuler");
        clearBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                clearBtnActionPerformed(evt);
            }
        });
        
        JButton btnNewButton = new JButton("Retour");
        btnNewButton.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		
        		shlMenuAdmin.close();
				try
				{
					Chambre window = new Chambre();
					window.run();
				}
				catch (Exception e1)
				{
					e1.printStackTrace();
				}
        		
        	}
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        layout.setHorizontalGroup(
        	layout.createParallelGroup(Alignment.LEADING)
        		.addGroup(layout.createSequentialGroup()
        			.addContainerGap()
        			.addComponent(jPanel1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
        			.addGroup(layout.createParallelGroup(Alignment.LEADING)
        				.addGroup(layout.createSequentialGroup()
        					.addGap(18)
        					.addComponent(jLabel1)
        					.addPreferredGap(ComponentPlacement.RELATED)
        					.addComponent(nameTxt, 100, 100, 100))
        				.addGroup(layout.createSequentialGroup()
        					.addGap(32)
        					.addComponent(addBtn)
        					.addGap(18)
        					.addComponent(clearBtn))
        				.addGroup(layout.createSequentialGroup()
        					.addGap(50)
        					.addComponent(retrieveBtn)))
        			.addGap(40))
        		.addGroup(layout.createSequentialGroup()
        			.addGap(66)
        			.addComponent(jLabel2)
        			.addContainerGap(348, Short.MAX_VALUE))
        		.addGroup(layout.createSequentialGroup()
        			.addContainerGap(433, Short.MAX_VALUE)
        			.addComponent(btnNewButton)
        			.addGap(22))
        );
        layout.setVerticalGroup(
        	layout.createParallelGroup(Alignment.LEADING)
        		.addGroup(layout.createSequentialGroup()
        			.addGap(64)
        			.addComponent(jLabel2)
        			.addGap(18)
        			.addGroup(layout.createParallelGroup(Alignment.LEADING)
        				.addComponent(jPanel1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
        				.addGroup(layout.createSequentialGroup()
        					.addGroup(layout.createParallelGroup(Alignment.BASELINE)
        						.addComponent(jLabel1)
        						.addComponent(nameTxt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
        					.addPreferredGap(ComponentPlacement.UNRELATED)
        					.addComponent(retrieveBtn)
        					.addGap(27)
        					.addGroup(layout.createParallelGroup(Alignment.BASELINE)
        						.addComponent(clearBtn)
        						.addComponent(addBtn))))
        			.addPreferredGap(ComponentPlacement.RELATED, 22, Short.MAX_VALUE)
        			.addComponent(btnNewButton)
        			.addContainerGap())
        );
        getContentPane().setLayout(layout);

        pack();
    }// </editor-fold>

    private void jComboBox1ItemStateChanged(java.awt.event.ItemEvent evt) {
       id=jComboBox1.getSelectedItem().toString();
       nameTxt.setText(id);
    }

    private void addBtnActionPerformed(java.awt.event.ActionEvent evt) {
        if(new Manager_connexion().add(nameTxt.getText()))
        {
            JOptionPane.showMessageDialog(null, "Medicament affecté, avec succès");
            retrieve();;

             //SELECT ADDED ITEM
            jComboBox1.setSelectedItem(nameTxt.getText());
            //CLEAR TXT
            nameTxt.setText("");
        }else
        {
            JOptionPane.showMessageDialog(null, "Erreur");
        }
    }

    private void retrieveBtnActionPerformed(java.awt.event.ActionEvent evt) {
      retrieve();
    }

    private void clearBtnActionPerformed(java.awt.event.ActionEvent evt) {
        jComboBox1.setModel(new DefaultComboBoxModel());
    }


    // Variables declaration - do not modify
    private javax.swing.JButton addBtn;
    private javax.swing.JButton clearBtn;
    private javax.swing.JComboBox jComboBox1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JTextField nameTxt;
    private javax.swing.JButton retrieveBtn;
}
