import javax.swing.*;
//import java.awt.event.*;

public class PasswordManager {

    public static void main(String a[]){
        //creating Window
        JFrame fr = new JFrame("Password Manager");
        fr.setSize(500,400);
        fr.setLayout(null);
        //fr.setSize(500,400);


        //Adding labels

        JLabel l1 = new JLabel("Website");
        fr.add(l1);
        JLabel l2 = new JLabel("Username"); 
        fr.add(l2);
        JLabel l3 = new JLabel("Password");
        fr.add(l3);

        //Added Layouts
        l1.setBounds(50, 20, 100, 25);  // Website
        l2.setBounds(50, 60, 100, 25);  // Username
        l3.setBounds(50, 100, 100, 25); // Password

        //Adding TextField

        JTextField f1 = new JTextField();
        fr.add(f1);
        f1.setBounds(150,20,150,25);
        JTextField f2 = new JTextField();
        fr.add(f2);
        f2.setBounds(150,60,150,25);
        fr.add(f2);
        JTextField f3 = new JTextField();
        f3.setBounds(150,100,150,25);
        fr.add(f3);


        fr.setVisible(true);
        fr.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    }
    
    

    
    
    
}
