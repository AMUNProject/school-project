package amuntest;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.SwingUtilities;
import javax.swing.WindowConstants;

public class SQLAccessWindow {
    private JFrame window;
    private JPanel contentPanel;
    private JButton submitButton;
    private JTextArea sqlWindow;

    private class ButtonOneHandler implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent ae) {
            Connection conn = null;
            try {
                Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
                conn = DriverManager.getConnection("jdbc:ucanaccess://AMUN.accdb");
                String command = sqlWindow.getText().trim(); // cut off any white space
                if (command .equals("")) {
                    return;
                } // end if
                if (command.charAt(command.length()-1) == (';')){ // JDBC doesn't want trailing semi-colon
                    command = command.substring(0, command.length()-1); // cut off any trailing semi-colon
                } // end if
                System.out.println(command);
                Statement stmt = conn.createStatement();
                stmt.setQueryTimeout(5);
                stmt.execute(command);
                stmt.close();
            } catch (Exception e) {
                System.out.println(e.getMessage());
                e.printStackTrace();
            } finally {
                try {
                    conn.close();
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                    e.printStackTrace();
                } // end catch
            } // end for
        } // end method actionPerformed
    } // end class ButtonOneHandler
    
    public SQLAccessWindow() {
        initComponents(); // the ctor simple invokes a method that initializes all components
    } // end ctor
    
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new SQLAccessWindow();
            } // end method run
        });
    } // end method main

    private void initComponents(){
        // this is where all of the setup and definitions of the components takes place
        window = new JFrame("Access SQL Window");
        window.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        contentPanel = new JPanel();
        sqlWindow = new JTextArea(10, 50);
        submitButton = new JButton("Submit SQL Command");
        submitButton.addActionListener(new ButtonOneHandler());
        contentPanel.add(sqlWindow);
        contentPanel.add(submitButton);
        window.setContentPane(contentPanel);
        window.setSize(new Dimension(675,265));
        window.setVisible(true);
    } // end method initComponents    
} // end class SQLAccessWindow