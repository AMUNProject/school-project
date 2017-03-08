package amuntest;

import com.qoppa.pdfWriter.PDFDocument;
import com.qoppa.pdfWriter.PDFGraphics;
import com.qoppa.pdfWriter.PDFPage;
import java.awt.Desktop;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.ImageObserver;
import java.awt.print.PageFormat;
import java.io.File;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.WindowConstants;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.imageio.ImageIO;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class Driver {
    private ArrayList<Student> allStudents;
    private JFrame window;
    private JPanel contentPanel;
    private JPanel controlPanel;
    private JPanel framePanel;
    private JButton buttonOne;
    private JButton buttonTwo;
    private JButton buttonThree;
    private JButton buttonFour;
    private JTable studentTable;
    
    private class ButtonOneHandler implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent ae) {
            Connection conn = null;
            try {
                String sheetID = "1rg3hlOUAq-QxbVS1fEuBAsEHh7OaeX9bdUxqSG9JWmk";
                String sheetTabName = "Form Responses 1";
                String sheetRange = "A2:FH";
                List<List<Object>> data = GoogleSheetsUtility.getGoogleSheetsData(sheetID, sheetTabName, sheetRange);
                //System.out.println("Timestamp \t\t Email Address \t First Name \t Last Name \t Birthdate \t School");
                Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
                conn = DriverManager.getConnection("jdbc:ucanaccess://AMUN.accdb");
                for (List eachOne : data) {
                    try {
                        PreparedStatement safeQuery = conn.prepareStatement("SELECT COUNT(ID) FROM Student WHERE Timestamp = ? "
                                + "AND State = ? AND FName = ? AND LName = ? AND ArSchool = ? AND OkSchool AND TnSchool = ? AND "
                                + "LaSchool = ? AND MsSchool = ? AND TxSchool = ? AND MoSchool = ? AND HeadDelegate = ? "
                                + "AND StudentDesignation = ? AND CountryAssignment = ? "
                                + "AND CommitteeAssignment = ? AND AddStudent = ?");
                        safeQuery.setString(1, (String)eachOne.get(0));
                        safeQuery.setString(2, (String)eachOne.get(1));
                        safeQuery.setString(3, (String)eachOne.get(2));
                        safeQuery.setString(4, (String)eachOne.get(3));
                        safeQuery.setString(5, (String)eachOne.get(4));
                        safeQuery.setString(6, (String)eachOne.get(5));
                        safeQuery.setString(7, (String)eachOne.get(6));
                        safeQuery.setString(8, (String)eachOne.get(7));
                        safeQuery.setString(9, (String)eachOne.get(8));
                        safeQuery.setString(10, (String)eachOne.get(9));
                        safeQuery.setString(11, (String)eachOne.get(10));
                        safeQuery.setString(12, (String)eachOne.get(11));
                        safeQuery.setString(13, (String)eachOne.get(12));
                        safeQuery.setString(14, (String)eachOne.get(13));
                        safeQuery.setString(15, (String)eachOne.get(14));
                        safeQuery.setString(16, (String)eachOne.get(15));
                        ResultSet rs = safeQuery.executeQuery();
                        rs.next();
                        if (rs.getInt(1) == 0) { // new student to DB
                            safeQuery = conn.prepareStatement("INSERT INTO Student (Timestamp, State, FName, LName, State, ArSchool, "
                                    + "OkSchool, TnSchool, MsSchool, TxSchool, MoSchool, HeadDelegate, "
                                    + "StudentDesignation, CountryAssignment, "
                                    + "CommitteeAssignment, AddStudent)VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
                            safeQuery.setString(1, (String)eachOne.get(0));
                            safeQuery.setString(2, (String)eachOne.get(1));
                            safeQuery.setString(3, (String)eachOne.get(2));
                            safeQuery.setString(4, (String)eachOne.get(3));
                            safeQuery.setString(5, (String)eachOne.get(4));
                            safeQuery.setString(6, (String)eachOne.get(5));
                            safeQuery.setString(7, (String)eachOne.get(6));
                            safeQuery.setString(8, (String)eachOne.get(7));
                            safeQuery.setString(9, (String)eachOne.get(8));
                            safeQuery.setString(10, (String)eachOne.get(9));
                            safeQuery.setString(11, (String)eachOne.get(10));
                            safeQuery.setString(12, (String)eachOne.get(11));
                            safeQuery.setString(13, (String)eachOne.get(12));
                            safeQuery.setString(14, (String)eachOne.get(13));
                            safeQuery.setString(15, (String)eachOne.get(14));
                            safeQuery.setString(16, (String)eachOne.get(15));
                            safeQuery.execute();
                            System.out.printf("Student %s %s added to the DB.\n",(String)eachOne.get(2), (String)eachOne.get(3));
                        } else {
                            System.out.printf("Student %s %s is already in the DB.\n",(String)eachOne.get(2), (String)eachOne.get(3));
                        } // end else
                        rs.close();
                        safeQuery.close();
                    } catch (SQLException e) {
                        System.out.printf("Couldn't save user: %s %s\n",(String)eachOne.get(2), (String)eachOne.get(3));
                        System.out.println(e.getMessage());
                        e.printStackTrace();
                    } // end catch
                } // end for      
                conn.close();
            } // end try
            catch (Exception e) {
                System.out.println(e.getMessage());
                e.printStackTrace();
            } // end catch
            allStudents = new ArrayList<Student>();
//            Connection conn = null;
            try {
                Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");  // can be omitted in most cases
                conn = DriverManager.getConnection("jdbc:ucanaccess://AMUN.accdb");
                PreparedStatement stmt = conn.prepareStatement("SELECT * FROM Student");
                stmt.setQueryTimeout(30);
                ResultSet rs = stmt.executeQuery();
                while (rs.next()) { // load all rows into the rowData array
                    allStudents.add(new Student(Integer.valueOf(rs.getString(1)), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7), rs.getString(8), rs.getString(9), rs.getString(10), rs.getString(11), rs.getString(12), rs.getBoolean(13), rs.getString(14), rs.getString(15), rs.getString(16), rs.getBoolean(17)));
                } // end while
                stmt.close(); // close the PreparedStatement
                rs.close(); // close the ResultSet                
                conn.close();
            } catch (Exception e) {
                System.out.println(e.getMessage());
                e.printStackTrace();
            } // end catch            
        } // end method actionPerformed
    } // end class ButtonOneHandler
    
//    private class ButtonTwoHandler implements ActionListener {
//        @Override
//        public void actionPerformed(ActionEvent ae) {
//            allStudents = new ArrayList<Student>();
//            Connection conn = null;
//            try {
//                Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");  // can be omitted in most cases
//                conn = DriverManager.getConnection("jdbc:ucanaccess://AMUN.accdb");
//                PreparedStatement stmt = conn.prepareStatement("SELECT * FROM Student");
//                stmt.setQueryTimeout(30);
//                ResultSet rs = stmt.executeQuery();
//                while (rs.next()) { // load all rows into the rowData array
//                    allStudents.add(new Student(Integer.valueOf(rs.getString(1)), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7), rs.getString(8), rs.getString(9), rs.getString(10), rs.getString(11), rs.getString(12), rs.getBoolean(13), rs.getString(14), rs.getString(15), rs.getString(16), rs.getBoolean(17)));
//                } // end while
//                stmt.close(); // close the PreparedStatement
//                rs.close(); // close the ResultSet                
//                conn.close();
//            } catch (Exception e) {
//                System.out.println(e.getMessage());
//                e.printStackTrace();
//            } // end catch
//        } // end method actionPerformed
//    } // end class ButtonOneHandler
//
//    private class ButtonThreeHandler implements ActionListener {
//        @Override
//        public void actionPerformed(ActionEvent ae) {
//                String[] columnHeaders = new String[]{"ID","Timestamp","Email Address","First Name","Last Name","Birthdate","School"};
//                String[][] rowData = new String[allStudents.size()][6];
//                int index = 0; // start an indexer since we're using an enhanced loop, start at 0 for the array
//                for (Student eachOne: allStudents) { // load all rows into the rowData array
//                    rowData[index] = new String[]{String.valueOf(eachOne.getUniqueID()),eachOne.getTimeStamp(), eachOne.getEmailAddress(), eachOne.getfName(), eachOne.getlName(), eachOne.getBirthdate(), eachOne.getSchool()};
//                    index++;
//                } // end while
//                // the table's model is what actually holds the column header and data
//                DefaultTableModel model = new DefaultTableModel(rowData, columnHeaders);
//                // set the previously instantiated JTable to the model we just instantiated and populated
//                studentTable.setModel(model);
//        } // end method actionPerformed
//    } // end class ButtonOneHandler
//    
//    private class ButtonFourHandler implements ActionListener {
//        @Override
//        public void actionPerformed(ActionEvent ae) {
//            try {
//                PDFDocument pdfDoc = new PDFDocument();
//                PageFormat pf = new PageFormat();
//                pf.setOrientation(PageFormat.LANDSCAPE);
//                for (Student eachOne: allStudents) {
//                    PDFPage newPage = pdfDoc.createPage(pf);
//                    // Draw to the page
//                    Graphics2D g2d = newPage.createGraphics();
//                    Image logo = ImageIO.read(Driver.class.getResourceAsStream("resources/logo.png"));
//                    g2d.drawImage(logo, 300, 75, 150, 60, (ImageObserver)ae.getSource());
//                    g2d.drawLine(75, 145, 700, 145);
//                    int yValue = 175;
//                    g2d.setFont (PDFGraphics.HELVETICA.deriveFont(14f));
//                    g2d.drawString("2017 Arkansas Model United Nations Participant", 80, yValue);
//                    yValue += 20;
//                    g2d.setFont(new Font("HELVETICA", Font.BOLD, 14));
//                    g2d.drawString(eachOne.getfName() + " " + eachOne.getlName(), 80, yValue);
//                    yValue += 20;
//                    g2d.setFont (PDFGraphics.HELVETICA.deriveFont(14f));
//                    g2d.drawString(eachOne.getSchool(), 80, yValue);
//                    yValue += 20;
//                    pdfDoc.addPage(newPage);
//                } // end for
//                pdfDoc.saveDocument("ParticipationCerts.pdf");
//                if (Desktop.isDesktopSupported()) {
//                    try {
//                        File myFile = new File("ParticipationCerts.pdf");
//                        Desktop.getDesktop().open(myFile);
//                    } catch (Exception e) {
//                        System.out.println(e.getMessage());
//                        e.printStackTrace();
//                    } // end catch
//                } // end if  
//            } catch (Exception e) {
//                System.out.println(e.getMessage());
//                e.printStackTrace();
//            } // end catch
//
//        } // end method actionPerformed
//    } // end class ButtonOneHandler
    
    public Driver() {
        initComponents(); // the ctor simple invokes a method that initializes all components
    } // end ctor
    
    private void initComponents(){
        // this is where all of the setup and definitions of the components takes place
        window = new JFrame("AMUN Demonstration");
        window.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        contentPanel = new JPanel();
        controlPanel = new JPanel();
        controlPanel.setBorder(BorderFactory.createTitledBorder("Application Controls"));
        controlPanel.setLayout(new FlowLayout());
        controlPanel.setPreferredSize(new Dimension(250,150));
        buttonOne = new JButton("Update");
        buttonOne.addActionListener(new ButtonOneHandler());
//        buttonTwo = new JButton("Get data from DB");
//        buttonTwo.addActionListener(new ButtonTwoHandler());
//        buttonThree = new JButton("Show all data in table");
//        buttonThree.addActionListener(new ButtonThreeHandler());
//        buttonFour = new JButton("Print participation certificates");
//        buttonFour.addActionListener(new ButtonFourHandler());
        controlPanel.add(buttonOne);
//        controlPanel.add(buttonTwo);
//        controlPanel.add(buttonThree);
//        controlPanel.add(buttonFour);
        framePanel = new JPanel();
        framePanel.setBorder(BorderFactory.createTitledBorder("Application Data"));
        framePanel.setLayout(new FlowLayout());
        framePanel.setPreferredSize(new Dimension(650,260));
        studentTable = new JTable();
        JScrollPane scrollPaneOne = new JScrollPane(studentTable);
        scrollPaneOne.setPreferredSize(new Dimension(600,225));
        framePanel.add(scrollPaneOne);
        contentPanel.add(controlPanel);
        contentPanel.add(framePanel);
        window.setContentPane(contentPanel);
        window.setSize(new Dimension(675,465));
        window.setVisible(true);
    } // end method initComponents

    public static void main (String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new Driver();
            } // end method run
        });
    } // end method main
} // end class Driver