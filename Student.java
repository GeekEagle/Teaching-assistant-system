import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.sql.*;
import java.text.MessageFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Student implements chosenidentity{
    private JFrame frame;
    private JLabel hwframe,difframe,thingsframe;
    private JTextArea difficult,answer,things;
    private JComboBox homeworkarea;
    public JButton submit,question,homework,check,attention;
    private Dimension size;
    public static final String QUESTION = "答疑";
    public static final String HOMEWORK = "做作业";
    public static final String SUBMIT = "提交作业";
    public static final String CHECKANSWER = "查询答疑";
    public static final String ATTENTION = "查看消息";
    public static final String sweg = "softwareengineering";
    public static final String os = "operatingsystem";
    public static final String ds = "datastructure";
    private String studentnum;
    private int process;
    JLabel stunum = new JLabel();
    JLabel studentname = new JLabel();
    JLabel stupro = new JLabel();
    JLabel stugra = new JLabel();

    public static void main(String args[])
    {
       Student stu = new Student();
        JTextField login = new JTextField();
        login.setText("stu123");
        JPasswordField password = new JPasswordField();
        password.setText("1234555");
       stu.login(login,password);
    }

    public Student(){
        try{
            UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
        }catch (Exception evt){}
        size = new Dimension(30,30);
        setloginpanel();
    }
    public void setloginpanel(){
        hwframe = new JLabel("提交作业框");
        difframe = new JLabel("答疑框");
        thingsframe = new JLabel("通知栏");
        homeworkarea = new JComboBox();
        homeworkarea.addItem(sweg);
        homeworkarea.addItem(os);
        homeworkarea.addItem(ds);
        difficult = new JTextArea(30,30);
        answer = new JTextArea(100,100);
        things = new JTextArea(30,30);
        question = new JButton(QUESTION);
        submit = new JButton(SUBMIT);
        homework = new JButton(HOMEWORK);
        check = new JButton(CHECKANSWER);
        attention = new JButton(ATTENTION);
        StudentIssue btn = new StudentIssue();
        question.addActionListener(btn);
        submit.addActionListener(btn);
        homework.addActionListener(btn);
        check.addActionListener(btn);
        attention.addActionListener(btn);
        JLabel jstunum,jstuname,jstupro,jstugra;
        jstunum = new JLabel("学号");jstunum.setFont(new Font("宋体",Font.PLAIN,20));
        jstuname = new JLabel("姓名");jstuname.setFont(new Font("宋体",Font.PLAIN,20));
        jstupro = new JLabel("专业");jstupro.setFont(new Font("宋体",Font.PLAIN,20));
        jstugra = new JLabel("年级");jstugra.setFont(new Font("宋体",Font.PLAIN,20));

        frame = new JFrame();
        frame.setTitle("学生登录");
        frame.setSize(1500,1200);
        GridBagLayout gridbag = new GridBagLayout();
        frame.setLayout(gridbag);
        GridBagConstraints gbc = new GridBagConstraints();

        gbc.insets.top = 20;
        gbc.insets.bottom = 20;
        gbc.insets.left = 20;
        gbc.insets.right = 20;
        gbc.anchor = GridBagConstraints.NORTHWEST;
        gbc.gridx = 0;
        gbc.gridy = 0;
        gridbag.setConstraints(jstunum, gbc);
        gbc.gridx = 2;
        gbc.gridy = 0;
        gridbag.setConstraints(jstuname, gbc);
        gbc.gridx = 4;
        gbc.gridy = 0;
        gridbag.setConstraints(jstupro, gbc);
        gbc.gridx = 6;
        gbc.gridy = 0;
        gridbag.setConstraints(jstugra, gbc);
        gbc.gridx = 1;
        gbc.gridy = 0;
        gridbag.setConstraints(stunum, gbc);
        gbc.gridx = 3;
        gbc.gridy = 0;
        gridbag.setConstraints(studentname, gbc);
        gbc.gridx = 5;
        gbc.gridy = 0;
        gridbag.setConstraints(stupro, gbc);
        gbc.gridx = 7;
        gbc.gridy = 0;
        gridbag.setConstraints(stugra, gbc);

        gbc.insets.left = 5;
        gbc.insets.right = 5;
        gbc.insets.top = 15;
        gbc.gridx = 1;
        gbc.gridy = 8;
        gridbag.setConstraints(question, gbc);
        gbc.gridx = 2;
        gbc.gridy = 8;
        gridbag.setConstraints(submit, gbc);
        gbc.gridx = 3;
        gbc.gridy = 8;
        gridbag.setConstraints(homework, gbc);
        gbc.gridx = 4;
        gbc.gridy = 8;
        gridbag.setConstraints(check, gbc);
        gbc.gridx = 5;
        gbc.gridy = 8;
        gridbag.setConstraints(attention, gbc);

        gbc.insets.left = 4;
        gbc.insets.right = 4;
        gbc.insets.top = 10;
        gbc.gridx = 0;
        gbc.gridy = 1;
        gridbag.setConstraints(hwframe, gbc);
        gbc.gridx = 1;
        gbc.gridy = 1;
        gridbag.setConstraints(homeworkarea, gbc);
        gbc.gridx = 2;
        gbc.gridy = 1;
        gridbag.setConstraints(difframe, gbc);
        gbc.gridx = 3;
        gbc.gridy = 1;
        gridbag.setConstraints(difficult, gbc);
        gbc.gridx = 4;
        gbc.gridy = 1;
        gridbag.setConstraints(thingsframe, gbc);
        gbc.gridx = 5;
        gbc.gridy = 1;
        gridbag.setConstraints(things, gbc);

        frame.add(jstunum);
        frame.add(stunum);
        frame.add(jstuname);
        frame.add(studentname);
        frame.add(jstupro);
        frame.add(stupro);
        frame.add(jstugra);
        frame.add(stugra);
        frame.add(question);
        frame.add(submit);
        frame.add(homework);
        frame.add(check);
        frame.add(hwframe);
        frame.add(homeworkarea);
        frame.add(difframe);
        frame.add(difficult);
        frame.add(attention);
        frame.add(thingsframe);
        frame.add(things);
        frame.setVisible(true);
    }
    public void login( JTextField login, JPasswordField text_name ) {
        String connectionUrl = "jdbc:sqlserver://localhost:1433;DatabaseName=rgks";
        // Declare the JDBC objects.
        Connection con = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        String str[] = new String[15];
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            con = DriverManager.getConnection(connectionUrl, "sa", "123456");

            // Create and execute an SQL statement that returns some data.
            String SQL = new String();
            SQL = "select *" + " from 学生信息表" + " where Stunum='" + login.getText().trim() + "';";
            stmt = con.prepareStatement(SQL);
            // stmt.setString(1,text_name.getText());
            rs = stmt.executeQuery();
            if (rs.next()) {
                str[1] = rs.getString(1);
                str[2] = rs.getString(2);
                str[3] = rs.getString(3);
                str[4] = rs.getString(4);
                process = rs.getInt(5);
            }
        }catch(Exception ex){
            ex.printStackTrace();
            System.out.println("no this user\n");
        }
        studentnum = str[1];
        stunum.setText(str[1]);stunum.setFont(new Font("宋体",Font.PLAIN,20));
        studentname.setText(str[2]);studentname.setFont(new Font("宋体",Font.PLAIN,20));
        stupro.setText(str[3]);stupro.setFont(new Font("宋体",Font.PLAIN,20));
        stugra.setText(str[4]);stugra.setFont(new Font("宋体",Font.PLAIN,20));
    }
    class StudentIssue implements ActionListener{
        private  JFrame fra = new JFrame();
        String subject;
        public void actionPerformed(ActionEvent e){
            String connectionUrl = "jdbc:sqlserver://localhost:1433;DatabaseName=rgks";
            Connection con = null;
            PreparedStatement stmt = null;
            ResultSet rs = null;
            Date date = new Date();
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
            String dat = format.format(date);
            String id = new String();
            try{
                Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
                con = DriverManager.getConnection(connectionUrl, "sa", "123456");

                if(e.getActionCommand().equals(SUBMIT)) {
                    File f;
                    JFileChooser chooser;
                    chooser = new JFileChooser();
                    chooser.setCurrentDirectory(new File("C:\\Users\\10334\\Desktop"));	//定位到当前目录
                    if(chooser.showOpenDialog(null)==JFileChooser.CANCEL_OPTION)
                        return;
                    f = chooser.getSelectedFile();
                    int length = (int)f.length();
                    System.out.println(length);
                    InputStream fin = new FileInputStream(f);	//文件输入流
                    String SQL = "{call 提交作业(?,?,?,?)}";
                    CallableStatement calls = con.prepareCall(SQL);
                    calls.setString(1, studentnum);
                    calls.setBinaryStream(2,fin,length);
                    calls.setInt(3, -1);
                    calls.setString(4,homeworkarea.getSelectedItem().toString().trim());
                    calls.execute();
                    calls.close();
                    SQL = "update 学生信息表 set process ='"+process+"' where Stunum='"+studentnum+"';";
                    stmt = con.prepareStatement(SQL);
                    int resu = stmt.executeUpdate();
                    JOptionPane.showMessageDialog(fra, "提交成功");
                }

                else if(e.getActionCommand().equals(QUESTION)){
                    String mydif = difficult.getText();
                    if(mydif.equals("")){
                        JOptionPane.showMessageDialog(fra, "不可提交空问题");
                        return;
                    }
                    String SQL = "{call 提问(?,?,?)}";
                    CallableStatement calls = con.prepareCall(SQL);
                    calls.setString(1,studentnum);calls.setString(2,mydif);
                    calls.setString(3,"未解决");
                    System.out.println(dat);
                    calls.execute();
                    calls.close();
                    JOptionPane.showMessageDialog(fra, "提交成功");
                }

                else if(e.getActionCommand().equals(HOMEWORK)){
                    String SQL = "select Infoid,CourseID,Homework,Message "+"from 课程消息表 "+"where InfoType ='"+ "作业"+"'and Infoid>'"+process+"';";
                    stmt = con.prepareStatement(SQL);
                    rs = stmt.executeQuery();
                    if (rs.next()){
                        File f;
                        JFileChooser chooser;
                        chooser = new JFileChooser();
                        chooser.setCurrentDirectory(new File("C:\\Users\\10334\\Desktop"));	//定位到当前目录
                        if(chooser.showOpenDialog(null)==JFileChooser.CANCEL_OPTION){
                            System.exit(0);
                        }
                        Blob blob = rs.getBlob(3);
                        process = rs.getInt(1);
                        subject = rs.getString(2).trim();
                        f=chooser.getSelectedFile();
                        FileOutputStream fout=new FileOutputStream(f);
                        System.out.println(blob.length());
                        fout.write(blob.getBytes(1, (int)blob.length()));
                        fout.flush();
                        fout.close();
                        stmt.close();
                    }
                    else{
                        JOptionPane.showMessageDialog(fra, "无作业");
                        return;
                    }
                }

                else if(e.getActionCommand().equals(CHECKANSWER)){
                    String SQL = "select Message,quesol "+"from 待答疑问题表 "+"where quescon= '"+"已解决"+"' and Stunum='"+studentnum+"';";
                    stmt = con.prepareStatement(SQL);
                    rs = stmt.executeQuery();
                    JFrame answerframe = new JFrame();
                    answerframe.setTitle("解答问题");
                    answerframe.setSize(500,500);
                    GridBagLayout gridbag = new GridBagLayout();
                    answerframe.setLayout(gridbag);
                    answerframe.add(answer);
                    answer.setText("");
                    while (rs.next()){
                        String QUES = rs.getString(1).trim();
                        answer.append(QUES+"\t");
                        String answ = rs.getString(2).trim();
                        answer.append(answ+"\n");
                        answerframe.setVisible(true);
                    }

                }

                else if(e.getActionCommand().equals(ATTENTION)){
                    String SQL = "select Infoid,Message "+"from 课程消息表 "+"where InfoType ='"+ "通知"+"';";
                    stmt = con.prepareStatement(SQL);
                    rs = stmt.executeQuery();
                    int i=0;
                    while (rs.next()){
                        i++;
                        id = rs.getString(1).trim();
                        String zuoye = rs.getString(2).trim();
                        things.append(zuoye+"\n");
                        /*SQL = "update 课程消息表"+" set Infocon ='"+"已读"+"' where Infoid='"+id+"';";
                        stmt = con.prepareStatement(SQL);
                        int resu = stmt.executeUpdate();*/
                    }
                    if(i==0) {
                        JOptionPane.showMessageDialog(fra, "无新消息");
                        return;
                    }
                }
            }catch (Exception ex) {
                ex.printStackTrace();
                System.out.println("no this user\n");
            }
        }
    }
}