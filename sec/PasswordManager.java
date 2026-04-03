import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.*;
import java.io.*;
import java.util.Base64;

public class PasswordManager {
    public static void main(String[] args) {
        // Create window
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
        f1.setToolTipText("Enter website URL");
        fr.add(f1);

        JTextField f2 = new JTextField();
        f2.setBounds(150, 60, 150, 25);
        f2.setToolTipText("Enter your username/email");
        fr.add(f2);

        JPasswordField f3 = new JPasswordField();
        f3.setBounds(150, 100, 150, 25);
        f3.setToolTipText("Enter Your password");
        fr.add(f3);

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

        // Buttons
        JButton addBtn = new JButton("Add");
        addBtn.setBounds(150, 150, 80, 25);
        addBtn.setToolTipText("click to add a password");
        fr.add(addBtn);

        JButton deleteBtn = new JButton("Delete");
        deleteBtn.setBounds(250, 150, 80, 25);
        deleteBtn.setToolTipText("Select a row and click to delete");
        fr.add(deleteBtn);
        deleteBtn.setEnabled(false);

        // Load data from file at startup
        try {
            BufferedReader br = new BufferedReader(new FileReader("data.txt"));
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 3) {
                    // Decode password
                    String decodedPass = new String(Base64.getDecoder().decode(parts[2]));
                    model.addRow(new Object[]{parts[0], parts[1], decodedPass});
                }
            }
            br.close();
        } catch (Exception e) {
            System.out.println("File not found, starting fresh.");
        }

        // Add button logic
        addBtn.addActionListener(e -> {
            String w = f1.getText();
            String u = f2.getText();
            String p = new String(f3.getPassword());

            if (w.isEmpty() || u.isEmpty() || p.isEmpty()) {
                JOptionPane.showMessageDialog(fr, "All fields are required!");
                return;
            }

            // Encode password
            String encodedPass = Base64.getEncoder().encodeToString(p.getBytes());

            // Save to file
            try {
                FileWriter fw = new FileWriter("data.txt", true);
                fw.write(w + "," + u + "," + encodedPass + "\n");
                fw.close();
            } catch (Exception ex) {
                System.out.println("Error writing to file");
            }

            // Add row to table
            model.addRow(new Object[]{w, u, p});

            // Clear fields
            f1.setText("");
            f2.setText("");
            f3.setText("");
        });

        // Enable delete button only when row is selected
        table.getSelectionModel().addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                deleteBtn.setEnabled(table.getSelectedRow() != -1);
            }
        });

        // Delete button logic
        deleteBtn.addActionListener(e -> {
            int[] rows = table.getSelectedRows();
            for (int i = rows.length - 1; i >= 0; i--) {
                model.removeRow(rows[i]);
            }

            // Rewrite file after deletion
            try {
                FileWriter fw = new FileWriter("data.txt"); // overwrite
                for (int i = 0; i < model.getRowCount(); i++) {
                    String w = (String) model.getValueAt(i, 0);
                    String u = (String) model.getValueAt(i, 1);
                    String p = Base64.getEncoder().encodeToString(((String) model.getValueAt(i, 2)).getBytes());
                    fw.write(w + "," + u + "," + p + "\n");
                }
                fw.close();
            } catch (Exception ex) {
                System.out.println("Error updating file after deletion");
            }

            deleteBtn.setEnabled(false);
        });

        fr.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        fr.setVisible(true);
    }
}