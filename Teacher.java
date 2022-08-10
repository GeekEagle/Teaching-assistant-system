import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Teacher extends JFrame implements chosenidentity {
    //Frame北面
    JLabel numberLabel = new JLabel("职工号");
    JLabel nameLabel = new JLabel("姓名");
    JLabel professionLabel = new JLabel("专业");
    //Frame中间
    JButton signButton = new JButton("发起签到");
    JButton scoreButton = new JButton("录入成绩") ;
    JButton messageButton = new JButton("发布消息");
    //3个按钮
    JLabel cNumLabel = new JLabel("课程号");
    JTextField cNumTextField = new JTextField(5);
    JLabel messageLabel = new JLabel("消息");
    JTextField messageField = new JTextField(5);
    JLabel messageTypeLabel = new JLabel("消息类型");
    JTextField messageTypeField = new JTextField(5);
    //发布消息
    JLabel sIDLable = new JLabel("学号");
    JTextField sIDTextField = new JTextField(5);
    JLabel cIDLable = new JLabel("课程号");
    JTextField cIDTextField = new JTextField(5);
    JLabel gradeLable = new JLabel("成绩");
    JTextField gradeTextField = new JTextField(5);
    JLabel creditLable = new JLabel("学分");
    JTextField creditTextField = new JTextField(5);  //录入成绩
    String teanum,subject;

    public static void main(String args[])
    {
        Teacher tea = new Teacher();
        JTextField login = new JTextField();
        login.setText("tea123");
        JPasswordField password = new JPasswordField();
        password.setText("12345");
        tea.login(login,password);
    }

    public Teacher(){
        setBounds(500,100,900,280);
        setTitle("教师登录");
        Box frame_n = Box.createHorizontalBox();
        frame_n.add(numberLabel);
        frame_n.add(Box.createHorizontalStrut(100));
        frame_n.add(nameLabel);
        frame_n.add(Box.createHorizontalStrut(100));
        frame_n.add(professionLabel);
        numberLabel.setFont(new Font("宋体",Font.PLAIN,20));
        nameLabel.setFont(new Font("宋体",Font.PLAIN,20));
        professionLabel.setFont(new Font("宋体",Font.PLAIN,20));
        sIDLable.setFont(new Font("宋体",Font.PLAIN,20));
        cIDLable.setFont(new Font("宋体",Font.PLAIN,20));
        gradeLable.setFont(new Font("宋体",Font.PLAIN,20));
        creditLable.setFont(new Font("宋体",Font.PLAIN,20));
        cNumLabel.setFont(new Font("宋体",Font.PLAIN,20));
        messageLabel.setFont(new Font("宋体",Font.PLAIN,20));
        messageTypeLabel.setFont(new Font("宋体",Font.PLAIN,20));
        //将盒容器加入Frame北面
        add(frame_n,"North");

//录取分数盒容器
        Box score_box = Box.createHorizontalBox();
            //学号
        score_box.add(sIDLable);
        score_box.add(sIDTextField);
        score_box.add(Box.createHorizontalStrut(50));
            //课程号
        /*score_box.add(cIDLable);
        score_box.add(cIDTextField);
        score_box.add(Box.createHorizontalStrut(50));*/
            //成绩
        score_box.add(gradeLable);
        score_box.add(gradeTextField);
        score_box.add(Box.createHorizontalStrut(50));
            //学分
        score_box.add(creditLable);
        score_box.add(creditTextField);
        score_box.add(Box.createHorizontalStrut(50));
            //录入成绩按钮
        score_box.add(scoreButton);

        ActionHandler ah = new ActionHandler();
        scoreButton.addActionListener(ah);
//消息盒容器//消息号，课程号，消息，消息类型
        Box message_box = Box.createHorizontalBox();

        /*message_box.add(cNumLabel);
        message_box.add(Box.createHorizontalStrut(20));
        message_box.add(cNumTextField);
        message_box.add(Box.createHorizontalStrut(20));*/

        message_box.add(messageLabel);
        message_box.add(Box.createHorizontalStrut(20));
        message_box.add(messageField);
        message_box.add(Box.createHorizontalStrut(20));

        message_box.add(messageTypeLabel);
        message_box.add(Box.createHorizontalStrut(20));
        message_box.add(messageTypeField);
        message_box.add(Box.createHorizontalStrut(20));

        message_box.add(messageButton);

        messageButton.addActionListener(ah);
        signButton.addActionListener(ah);

        //Frame中间盒容器
        Box frame_c = Box.createVerticalBox();
        frame_c.add(Box.createVerticalStrut(50));//发起签到按钮
        frame_c.add(signButton);
        frame_c.add(Box.createVerticalStrut(20));//录取分数盒容器
        frame_c.add(score_box);
        frame_c.add(Box.createVerticalStrut(20)); //消息盒容器
        frame_c.add(message_box);
        frame_c.add(Box.createVerticalStrut(50));

        //将Frame中间盒容器加入Frame中间
        add(frame_c);
        setVisible(true);
    }

    public void login(JTextField account, JPasswordField password){ //连接数据库，将教师信息显示在JFrame中
        try{
            //加载驱动
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            //建立连接
            Connection connection = DriverManager.getConnection("jdbc:sqlserver://localhost:1433;DatabaseName=rgks","sa","123456");
            //生成语句
            Statement statement = connection.createStatement();
            String sqlstr1 = "select *" + " from 教师信息表" + " where TeaID='" + account.getText().trim()+"';";
            //选择教师信息表中的一条记录。

            //语句执行
            ResultSet q_result = statement.executeQuery(sqlstr1);
            while(q_result.next()){
                String column1 = q_result.getString(1);
                String column2 = q_result.getString(2);
                String column3 = q_result.getString(3);
                subject = q_result.getString(4).trim();
                teanum = column1;
                numberLabel.setText(numberLabel.getText()+": "+column1);
                nameLabel.setText(nameLabel.getText()+": "+column2);
                professionLabel.setText(professionLabel.getText()+": "+column3);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    class ActionHandler implements ActionListener {
        private  JFrame frame_d = new JFrame();
        public boolean isNumeric(String str) {
            for (int i = str.length(); --i >= 0;) {
                if (!Character.isDigit(str.charAt(i))) {
                    return false;
                }
            }
            return true;
        }
        public void actionPerformed(ActionEvent ae) {
            try {
                Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
                Connection connection = DriverManager.getConnection("jdbc:sqlserver://localhost:1433;DatabaseName=rgks", "sa", "123456");
                Statement statement = connection.createStatement();
                ResultSet rs = null;
                PreparedStatement stmt = null;

                if (ae.getActionCommand().equals("录入成绩")) {
                    String sqlstr = "insert into 成绩信息表 values ('"+sIDTextField.getText().trim()+
                        "','"+subject+"',"+gradeTextField.getText().trim()+","+creditTextField.getText().trim()+");";
                    if(!gradeTextField.getText().equals(null) && !creditTextField.getText().trim().equals(null)){
                        if(isNumeric(gradeTextField.getText()) && isNumeric(creditTextField.getText())) {
                            String sqlstr1 = "select *" + " from 学生信息表" + " where Stunum='" + sIDTextField.getText().trim()+"';";
                            ResultSet q_result = statement.executeQuery(sqlstr1);
                            if(q_result.next()) {
                                statement.executeUpdate(sqlstr);
                                JOptionPane.showMessageDialog(frame_d, "录入成功！");
                                statement.close();
                                connection.close();
                            }else JOptionPane.showMessageDialog(frame_d, "该学生不存在");
                        } else JOptionPane.showMessageDialog(frame_d, "成绩和学分必须都是整数");
                    }else JOptionPane.showMessageDialog(frame_d, "输入不可为空");
                }
                else if (ae.getActionCommand().equals("发布消息")){
                    String sqlstr = "{call 发布课程消息(?,?,?,?)}";
                    CallableStatement calls = connection.prepareCall(sqlstr);
                    calls.setString(1,subject);calls.setString(2, messageField.getText());
                    calls.setString(3,messageTypeField.getText());calls.setBinaryStream(4,null,0);
                    if(messageTypeField.getText().equals("")||messageField.getText().equals("")){
                        JOptionPane.showMessageDialog(frame_d, "输入不可为空");
                        return;
                    }
                    else if(messageTypeField.getText().equals("通知")){calls.setBinaryStream(4,null,0);}
                    else if(messageTypeField.getText().equals("作业")){
                        /*upload upl = new upload();*/
                        File f;
                        JFileChooser chooser;
                        chooser = new JFileChooser();
                        chooser.setCurrentDirectory(new File("C:\\Users\\10334\\Desktop"));	//定位到当前目录
                        if(chooser.showOpenDialog(null)==JFileChooser.CANCEL_OPTION)
                            System.exit(0);
                        f = chooser.getSelectedFile();
                        int length = (int)f.length();
                        System.out.println(length);
                        InputStream fin = new FileInputStream(f);	//文件输入流
                        calls.setBinaryStream(4,fin,length);
                    }
                    else{
                        JOptionPane.showMessageDialog(frame_d, "只能是通知或作业");
                        return;
                    }
                    calls.execute();
                    calls.close();
                    JOptionPane.showMessageDialog(frame_d, "发布成功！");
                }
                else if (ae.getActionCommand().equals("发起签到")) {
                    String sql = "select Cournum from 教师信息表 where TeaID='"+teanum+"';";
                    stmt = connection.prepareStatement(sql);
                    rs = stmt.executeQuery();
                    if(rs.next()){
                        String id = rs.getString(1);
                        RegisterAttendanceGUI sign = new RegisterAttendanceGUI(id);
                    }
                }
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }
}


