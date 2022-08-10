import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.sql.*;
import java.text.MessageFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JTextField;

public class upload implements ActionListener{
    private JFrame frame;
    public JButton homework;
    private Dimension size;
    public static final String SUBMIT = "�ύ��ҵ";

    public upload(){
        String connectionUrl = "jdbc:sqlserver://localhost:1433;DatabaseName=rgks";
        // Declare the JDBC objects.
        Connection con = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        File f;
        JFileChooser chooser;
        chooser = new JFileChooser();
        chooser.setCurrentDirectory(new File("C:\\Users\\10334\\Desktop"));	//��λ����ǰĿ¼
        try {
            if(chooser.showOpenDialog(null)==JFileChooser.CANCEL_OPTION){
                System.exit(0);
            }
            f = chooser.getSelectedFile();
            int length = (int)f.length();
            System.out.println(length);
            InputStream fin = new FileInputStream(f);	//�ļ�������
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            con = DriverManager.getConnection(connectionUrl, "sa", "123456");
            // Create and execute an SQL statement that returns some data.
            String SQL;
            //*****************�����ݿ��ϴ��ļ�*******************//
            SQL = "insert into images VALUES(?)";
            stmt = con.prepareStatement(SQL);
            stmt.setBinaryStream(1, fin, length);
            stmt.executeUpdate();
            stmt.clearParameters();
            stmt.close();
            //******************��������Ϊ�ļ�********************//
            /*Statement stmt2 = con.createStatement();
            ResultSet rs2 = stmt2.executeQuery("select BLOBData FROM Images");
            rs2.next();
            Blob blob = rs2.getBlob(1);
            if(chooser.showSaveDialog(null)==JFileChooser.CANCEL_OPTION){
                System.exit(0);
               }
               f=chooser.getSelectedFile();
               FileOutputStream fout=new FileOutputStream(f);
               fout.write(blob.getBytes(1, (int)blob.length()));
               fout.flush();
               fout.close();
               stmt.close();*/
            /*frame.setVisible(true);*/
        }catch (Exception ex) {
            ex.printStackTrace();
            System.out.println("�ļ��ϴ�ʧ��\n");
        }
        /*setloginpanel();*/
    }
    public void setloginpanel(){
        homework = new JButton(SUBMIT);
        homework.addActionListener(this);
        size = new Dimension(300,200);
        frame = new JFrame();
        frame.setSize(size);
        frame.setTitle("�ϴ��ļ�");
        frame.add(homework);
        frame.setVisible(true);
    }
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		String connectionUrl = "jdbc:sqlserver://localhost:1433;DatabaseName=rgks";
        // Declare the JDBC objects.
        Connection con = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        File f;
        JFileChooser chooser;
        chooser = new JFileChooser();
        chooser.setCurrentDirectory(new File("."));	//��λ����ǰĿ¼
        
        try {
        	if(chooser.showOpenDialog(null)==JFileChooser.CANCEL_OPTION){
        	    System.exit(0);
        	}
        	f = chooser.getSelectedFile();
        	int length = (int)f.length();
        	System.out.println(length);
        	InputStream fin = new FileInputStream(f);	//�ļ�������
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            con = DriverManager.getConnection(connectionUrl, "sa", "123456");
            // Create and execute an SQL statement that returns some data.
            String SQL;
            //*****************�����ݿ��ϴ��ļ�*******************//
            SQL = "insert into Images VALUES(?)";
            stmt = con.prepareStatement(SQL);
            stmt.setBinaryStream(1, fin, length);
            stmt.executeUpdate();
            stmt.clearParameters();
            stmt.close();
            //******************��������Ϊ�ļ�********************//
            Statement stmt2 = con.createStatement();
            ResultSet rs2 = stmt2.executeQuery("select BLOBData FROM Images");
            rs2.next();
            Blob blob = rs2.getBlob(1);
            if(chooser.showSaveDialog(null)==JFileChooser.CANCEL_OPTION){
                System.exit(0);
               }
               f=chooser.getSelectedFile();
               FileOutputStream fout=new FileOutputStream(f);
               fout.write(blob.getBytes(1, (int)blob.length()));
               fout.flush();
               fout.close();
               stmt.close();
            frame.setVisible(true);
        }catch (Exception ex) {
            ex.printStackTrace();
            System.out.println("�ļ��ϴ�ʧ��\n");
        }
	}
	public static void main(String[] args) {
		new upload();     
	}
}
