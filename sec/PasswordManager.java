import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.*;

public class PasswordManager {
    public static void main(String[] args){
        // Creating window
        JFrame fr = new JFrame("Password Manager");
        fr.setSize(500, 400);
        fr.setLayout(null);

        // Labels
        JLabel l1 = new JLabel("Website");
        l1.setBounds(50, 20, 100, 25);
        fr.add(l1);

        JLabel l2 = new JLabel("Username");
        l2.setBounds(50, 60, 100, 25);
        fr.add(l2);

        JLabel l3 = new JLabel("Password");
        l3.setBounds(50, 100, 100, 25);
        fr.add(l3);

        // Input fields
        JTextField f1 = new JTextField();
        f1.setBounds(150, 20, 150, 25);
        fr.add(f1);

        JTextField f2 = new JTextField();
        f2.setBounds(150, 60, 150, 25);
        fr.add(f2);

        JPasswordField f3 = new JPasswordField();
        f3.setBounds(150, 100, 150, 25);
        fr.add(f3);

        // Add button
        JButton b = new JButton("Add");
        b.setBounds(150, 150, 80, 25);
        fr.add(b);

        //Delete button
        JButton b1 = new JButton("Delete");
        b1.setBounds(250,150,80,25);
        fr.add(b1);
        b1.setEnabled(false);




        // Table
        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("Website");
        model.addColumn("Username");
        model.addColumn("Password");

        JTable table = new JTable(model);
        table.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
        JScrollPane sp = new JScrollPane(table);
        sp.setBounds(20, 200, 450, 150);
        fr.add(sp);

        // Button logic
        b.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                String w = f1.getText();
                String u = f2.getText();
                String p = new String(f3.getPassword());

                // Add row to table
                model.addRow(new Object[]{w, u, p});

                // Clear fields
                f1.setText("");
                f2.setText("");
                f3.setText("");
            }
        });

        System.out.println("Before delete: " + model.getRowCount());


        //delete logic
        b1.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                int row = table.getSelectedRow();
                if(row != -1){
                    model.removeRow(row);
                }
                
                System.out.println("Rows after delete: " + model.getRowCount());
            
            }
        } );

        


        fr.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        fr.setVisible(true);
    }
}