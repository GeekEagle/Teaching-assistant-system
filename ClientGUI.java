import javax.swing.*;
import java.applet.AudioClip;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import com.microsoft.sqlserver.jdbc.SQLServerDataSource;

public class ClientGUI extends JFrame{
    private JLabel yonghuming;
    private JLabel mima;
    private JLabel shenfen;
    public JTextField  usernamelog;
    public JPasswordField  passwordlog;
    private JComboBox identity;
    private Dimension size;
    private JPanel btnpanel;
    public static final String LOGIN = "登录";
    public static final String EXIT = "退出";
    public static final String CHAPASS = "修改密码";
    public static final String STUDENT = "学生";
    public static final String TEACHER = "教师";
    public static final String ASSISTANT = "助教";

    public static void main(String[] args) {
        try{
            UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
        }catch (Exception evt){}
        ClientGUI frame = new ClientGUI();
        frame.addWindowListener(new WindowAdapter(){
            public void windowClosing(WindowEvent E){
                System.exit(0);
            }
        });
        frame.setSize(500, 420);
        frame.setVisible(true);
    }
    public ClientGUI() {
        super("教学辅助系统登录");
        size = new Dimension(800,500);
        setloginpanel();
    }
    public void setloginpanel(){
        identity = new JComboBox();
        identity.addItem(STUDENT);
        identity.addItem(TEACHER);
        identity.addItem(ASSISTANT);
        yonghuming = new JLabel("用户名");
        mima = new JLabel("密码");
        shenfen = new JLabel("身份");
        usernamelog = new JTextField(20);
        passwordlog = new JPasswordField(20);
        JButton login = new JButton(LOGIN);
        JButton exit = new JButton(EXIT);
        JButton chapass = new JButton(CHAPASS);
        Listeners btn = new Listeners();
        login.addActionListener(btn);
        exit.addActionListener(btn);
        chapass.addActionListener(btn);

        btnpanel = new JPanel();
        GridBagLayout gridbag = new GridBagLayout();
        btnpanel.setLayout(gridbag);
        GridBagConstraints gbc = new GridBagConstraints();
        btnpanel.add(yonghuming);
        btnpanel.add(usernamelog);
        btnpanel.add(mima);
        btnpanel.add(passwordlog);
        btnpanel.add(shenfen);
        btnpanel.add(identity);
        btnpanel.add(login);
        btnpanel.add(exit);
        /*btnpanel.add(chapass);*/

        gbc.insets.top = 5;
        gbc.insets.bottom = 5;
        gbc.insets.left = 5;
        gbc.insets.right = 5;

        gbc.gridx = 0;
        gbc.gridy = 0;
        gridbag.setConstraints(yonghuming, gbc);
        gbc.gridx = 1;
        gbc.gridy = 0;
        gridbag.setConstraints(usernamelog, gbc);
        gbc.gridx = 0;
        gbc.gridy = 1;
        gridbag.setConstraints(mima, gbc);
        gbc.gridx = 1;
        gbc.gridy = 1;
        gridbag.setConstraints(passwordlog, gbc);
        gbc.gridx = 0;
        gbc.gridy = 2;
        gridbag.setConstraints(shenfen, gbc);
        gbc.gridx = 1;
        gbc.gridy = 2;
        gridbag.setConstraints(identity, gbc);

        gbc.insets.left = 2;
        gbc.insets.right = 2;
        gbc.insets.top = 15;
        gbc.gridx = 0;
        gbc.gridy = 5;
        gridbag.setConstraints(login, gbc);
        gbc.anchor = GridBagConstraints.WEST;
        gbc.gridx = 1;
        gbc.gridy = 5;
        gridbag.setConstraints(exit, gbc);
        gbc.gridx = 2;
        gbc.gridy = 5;
        gridbag.setConstraints(chapass, gbc);
        this.add(btnpanel);
    }

    class Listeners implements ActionListener {
       private JFrame frame = new JFrame();
       chosenidentity user;
        public void actionPerformed(ActionEvent e) {
            if (e.getActionCommand().equals(EXIT)) {
                System.exit(1);
            }
            else if (e.getActionCommand().equals(LOGIN)) {
                if (usernamelog.getText().length() == 0 || usernamelog.getText().length() == 0) {
                    JOptionPane.showMessageDialog(frame, "please input text");
                    return;
                }
                String connectionUrl = "jdbc:sqlserver://localhost:1433;DatabaseName=rgks";
                Connection con = null;
                PreparedStatement stmt = null;
                ResultSet rs = null;
                try {
                    // Establish the connection.
                    //com.microsoft.jdbc.Sqlserver.SQLServerDriver
                    Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
                    con = DriverManager.getConnection(connectionUrl, "sa", "123456");
                    // Create and execute an SQL statement that returns some data.
                    String type = identity.getSelectedItem().toString().trim();
                    String SQL = "select userid,password,role" + " from 登录信息表" + " where userid='" + usernamelog.getText().trim() + "'and " +
                            "password='" + passwordlog.getText().trim()+"';";
                    stmt = con.prepareStatement(SQL);
                    rs = stmt.executeQuery();
                    if (rs.next()) {
                        if (type.equals(STUDENT)) {
                            if (rs.getString(3).trim().equals(type)) {
                                user = new Student();
                                user.login(usernamelog,passwordlog);
                            } else JOptionPane.showMessageDialog(frame, "用户身份不匹配");
                        }
                        else if (type.equals(TEACHER)) {
                            if (rs.getString(3).trim().equals(type)) {
                                user = new Teacher();
                                user.login( usernamelog, passwordlog);
                            } else JOptionPane.showMessageDialog(frame, "用户身份不匹配");
                        }
                        else if (type.equals(ASSISTANT)) {
                            if (rs.getString(3).trim().equals(type)) {
                                user = new Assistant();
                                user.login(usernamelog, passwordlog);
                            } else JOptionPane.showMessageDialog(frame, "用户身份不匹配");
                        }
                    }
                    else JOptionPane.showMessageDialog(frame, "账号或密码错误");
                }catch (Exception ex) {
                    ex.printStackTrace();
                    System.out.println("no this user\n");
                }
            }
        }
    }
}






