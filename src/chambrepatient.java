
package Vue;

import java.awt.Color;
import java.awt.EventQueue;
import java.util.ArrayList;
import java.util.Iterator;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

import Manager.Manager;
import Model.Chambres;
import Model.Patient;

import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.awt.event.ActionEvent;

public class chambrepatient {

        private JFrame frame;
        private JTable table;
        /**
         * Launch the application.
         */
        //public static void main(String[] args) {


                //EventQueue.invokeLater(new Runnable() {

        public static void main(String[] args) {
                EventQueue.invokeLater(new Runnable() {
                        public void run() {


                                try {
                                        chambrepatient window = new chambrepatient();
                                        window.frame.setVisible(true);
                                } catch (Exception e) {
                                        e.printStackTrace();
                                }
                        }
                });
                        }
                //});
//      }

        /**
         * Create the application.
         */
        public chambrepatient() {
                initialize();
        }

        /**
         * Initialize the contents of the frame.
         */
        private void initialize() {
                frame = new JFrame();
                frame.getContentPane().setBackground(new Color(135, 206, 250));
                frame.setBounds(550, 300, 700, 500);
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.getContentPane().setLayout(null);
                JScrollPane scrollPane = new JScrollPane();
                scrollPane.setBounds(200, 48, 468, 388);
                frame.getContentPane().add(scrollPane);
                DefaultTableModel tblModel = new DefaultTableModel(

                                new String[] {
                                                "id","Nom", "Prenom", "numéro de chambre", "Choix"
                                },0
                                );
                table = new JTable(tblModel);
                table.getColumnModel().getColumn(0).setPreferredWidth(135);
                table.getColumnModel().getColumn(1).setPreferredWidth(135);
                table.getColumnModel().getColumn(2).setPreferredWidth(164);
                table.getColumnModel().getColumn(3).setPreferredWidth(300);
                table.getColumnModel().getColumn(4).setPreferredWidth(201);
       table.getSelectionModel().addListSelectionListener((ListSelectionListener) new ListSelectionListener() {
                public void valueChanged(ListSelectionEvent e) {

           int row = table.getSelectedRow();

             int column = table.getSelectedColumn();

            int trucSelectionee = (int) table.getValueAt(row, 0);

            System.out.println(trucSelectionee); }
               
                });
                Manager m = new Manager();
                ArrayList<Object[]> o = m.hospitalisation();
                for(int i = 0 ; i < o.size(); i ++) {
                        tblModel.addRow(o.get(i));
                }
                scrollPane.setViewportView(table);


                //ArrayList<String>liste= m.recupatient();
                ArrayList<Patient>liste =        m.recupatients();
               
                JComboBox<Patient> comboBox = new JComboBox(liste.toArray());
                comboBox.setBounds(17, 83, 171, 53);
                frame.getContentPane().add(comboBox);


                ArrayList<Chambres>liste1 = m.recuchambres();

                JComboBox<Chambres> comboBox_1 = new JComboBox(liste1.toArray());
                comboBox_1.setBounds(17, 238, 171, 36);
                frame.getContentPane().add(comboBox_1);

                JLabel lblNewLabel = new JLabel("Nom");
                System.out.println(liste.get(0));
                lblNewLabel.setBounds(31, 68, 113, 23);
                frame.getContentPane().add(lblNewLabel);

                JLabel lblNewLabel_1 = new JLabel("Numéo de chambre");
                lblNewLabel_1.setBounds(31, 212, 155, 24);
                frame.getContentPane().add(lblNewLabel_1);

                JButton btnNewButton = new JButton("Ajouter");
                btnNewButton.addActionListener(new ActionListener() {
                        public void actionPerformed(ActionEvent e) {
                                Patient selectValue = (Patient) comboBox.getSelectedItem();
                                Chambres selectValue1 = (Chambres) comboBox_1.getSelectedItem();
                                selectValue.getId();
                               
                                Manager man = new Manager();
                                try {
                                        man.ajouthospit(selectValue.getId(),selectValue1.getId());
                                } catch (SQLException e1) {
                                        // TODO Auto-generated catch block
                                        e1.printStackTrace();
                                }

                        }
                });
                btnNewButton.setBounds(69, 340, 117, 29);
                frame.getContentPane().add(btnNewButton);
               
               
                btnNewButton.setBounds(69, 340, 117, 29);
                frame.getContentPane().add(btnNewButton);
               
                JButton btnNewButton_1 = new JButton("Retour");
                btnNewButton_1.addActionListener(new ActionListener() {
                        public void actionPerformed(ActionEvent e) {
                                infirmiere inf = new infirmiere();
                                inf.run();
                                frame.dispose();
                        }
                });
                btnNewButton_1.setBounds(6, 17, 113, 23);
                frame.getContentPane().add(btnNewButton_1);
               
        }
}