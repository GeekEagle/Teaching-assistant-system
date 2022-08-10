import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.sql.*;

class download implements ActionListener {
    public download(){
        String connectionUrl = "jdbc:sqlserver://localhost:1433;DatabaseName=rgks";
        // Declare the JDBC objects.
        Connection con = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        File f;
        JFileChooser chooser;
        chooser = new JFileChooser();
        /*chooser.setCurrentDirectory(new File("."));*/	//定位到当前目录
        try {
            if(chooser.showOpenDialog(null)==JFileChooser.CANCEL_OPTION){
                System.exit(0);
            }
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            con = DriverManager.getConnection(connectionUrl, "sa", "123456");
            // Create and execute an SQL statement that returns some data.
            String SQL="select count(*) from Images";
            stmt = con.prepareStatement(SQL);
            rs = stmt.executeQuery();
            rs.next();
            int amount = rs.getInt(1);
            System.out.println(amount);
            //******************读出保存为文件********************//
            Statement stmt2 = con.createStatement();
            ResultSet rs2 = stmt2.executeQuery("select * from Images where InfoID='"+amount+"';");
            if (rs2.next()){
                Blob blob = rs2.getBlob(2);
                if(chooser.showSaveDialog(null)==JFileChooser.CANCEL_OPTION){
                    System.exit(0);
                }
                f=chooser.getSelectedFile();
                FileOutputStream fout=new FileOutputStream(f);
                System.out.println(blob.length());
                fout.write(blob.getBytes(1, (int)blob.length()));
                fout.flush();
                fout.close();
                stmt.close();
            }
            /* frame.setVisible(true);*/
        }catch (Exception ex) {
            ex.printStackTrace();
            System.out.println("文件上传失败\n");
        }
    }
    public void actionPerformed(ActionEvent e) {

    }

}
