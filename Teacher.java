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
    //Frame����
    JLabel numberLabel = new JLabel("ְ����");
    JLabel nameLabel = new JLabel("����");
    JLabel professionLabel = new JLabel("רҵ");
    //Frame�м�
    JButton signButton = new JButton("����ǩ��");
    JButton scoreButton = new JButton("¼��ɼ�") ;
    JButton messageButton = new JButton("������Ϣ");
    //3����ť
    JLabel cNumLabel = new JLabel("�γ̺�");
    JTextField cNumTextField = new JTextField(5);
    JLabel messageLabel = new JLabel("��Ϣ");
    JTextField messageField = new JTextField(5);
    JLabel messageTypeLabel = new JLabel("��Ϣ����");
    JTextField messageTypeField = new JTextField(5);
    //������Ϣ
    JLabel sIDLable = new JLabel("ѧ��");
    JTextField sIDTextField = new JTextField(5);
    JLabel cIDLable = new JLabel("�γ̺�");
    JTextField cIDTextField = new JTextField(5);
    JLabel gradeLable = new JLabel("�ɼ�");
    JTextField gradeTextField = new JTextField(5);
    JLabel creditLable = new JLabel("ѧ��");
    JTextField creditTextField = new JTextField(5);  //¼��ɼ�
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
        setTitle("��ʦ��¼");
        Box frame_n = Box.createHorizontalBox();
        frame_n.add(numberLabel);
        frame_n.add(Box.createHorizontalStrut(100));
        frame_n.add(nameLabel);
        frame_n.add(Box.createHorizontalStrut(100));
        frame_n.add(professionLabel);
        numberLabel.setFont(new Font("����",Font.PLAIN,20));
        nameLabel.setFont(new Font("����",Font.PLAIN,20));
        professionLabel.setFont(new Font("����",Font.PLAIN,20));
        sIDLable.setFont(new Font("����",Font.PLAIN,20));
        cIDLable.setFont(new Font("����",Font.PLAIN,20));
        gradeLable.setFont(new Font("����",Font.PLAIN,20));
        creditLable.setFont(new Font("����",Font.PLAIN,20));
        cNumLabel.setFont(new Font("����",Font.PLAIN,20));
        messageLabel.setFont(new Font("����",Font.PLAIN,20));
        messageTypeLabel.setFont(new Font("����",Font.PLAIN,20));
        //������������Frame����
        add(frame_n,"North");

//¼ȡ����������
        Box score_box = Box.createHorizontalBox();
            //ѧ��
        score_box.add(sIDLable);
        score_box.add(sIDTextField);
        score_box.add(Box.createHorizontalStrut(50));
            //�γ̺�
        /*score_box.add(cIDLable);
        score_box.add(cIDTextField);
        score_box.add(Box.createHorizontalStrut(50));*/
            //�ɼ�
        score_box.add(gradeLable);
        score_box.add(gradeTextField);
        score_box.add(Box.createHorizontalStrut(50));
            //ѧ��
        score_box.add(creditLable);
        score_box.add(creditTextField);
        score_box.add(Box.createHorizontalStrut(50));
            //¼��ɼ���ť
        score_box.add(scoreButton);

        ActionHandler ah = new ActionHandler();
        scoreButton.addActionListener(ah);
//��Ϣ������//��Ϣ�ţ��γ̺ţ���Ϣ����Ϣ����
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

        //Frame�м������
        Box frame_c = Box.createVerticalBox();
        frame_c.add(Box.createVerticalStrut(50));//����ǩ����ť
        frame_c.add(signButton);
        frame_c.add(Box.createVerticalStrut(20));//¼ȡ����������
        frame_c.add(score_box);
        frame_c.add(Box.createVerticalStrut(20)); //��Ϣ������
        frame_c.add(message_box);
        frame_c.add(Box.createVerticalStrut(50));

        //��Frame�м����������Frame�м�
        add(frame_c);
        setVisible(true);
    }

    public void login(JTextField account, JPasswordField password){ //�������ݿ⣬����ʦ��Ϣ��ʾ��JFrame��
        try{
            //��������
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            //��������
            Connection connection = DriverManager.getConnection("jdbc:sqlserver://localhost:1433;DatabaseName=rgks","sa","123456");
            //�������
            Statement statement = connection.createStatement();
            String sqlstr1 = "select *" + " from ��ʦ��Ϣ��" + " where TeaID='" + account.getText().trim()+"';";
            //ѡ���ʦ��Ϣ���е�һ����¼��

            //���ִ��
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

                if (ae.getActionCommand().equals("¼��ɼ�")) {
                    String sqlstr = "insert into �ɼ���Ϣ�� values ('"+sIDTextField.getText().trim()+
                        "','"+subject+"',"+gradeTextField.getText().trim()+","+creditTextField.getText().trim()+");";
                    if(!gradeTextField.getText().equals(null) && !creditTextField.getText().trim().equals(null)){
                        if(isNumeric(gradeTextField.getText()) && isNumeric(creditTextField.getText())) {
                            String sqlstr1 = "select *" + " from ѧ����Ϣ��" + " where Stunum='" + sIDTextField.getText().trim()+"';";
                            ResultSet q_result = statement.executeQuery(sqlstr1);
                            if(q_result.next()) {
                                statement.executeUpdate(sqlstr);
                                JOptionPane.showMessageDialog(frame_d, "¼��ɹ���");
                                statement.close();
                                connection.close();
                            }else JOptionPane.showMessageDialog(frame_d, "��ѧ��������");
                        } else JOptionPane.showMessageDialog(frame_d, "�ɼ���ѧ�ֱ��붼������");
                    }else JOptionPane.showMessageDialog(frame_d, "���벻��Ϊ��");
                }
                else if (ae.getActionCommand().equals("������Ϣ")){
                    String sqlstr = "{call �����γ���Ϣ(?,?,?,?)}";
                    CallableStatement calls = connection.prepareCall(sqlstr);
                    calls.setString(1,subject);calls.setString(2, messageField.getText());
                    calls.setString(3,messageTypeField.getText());calls.setBinaryStream(4,null,0);
                    if(messageTypeField.getText().equals("")||messageField.getText().equals("")){
                        JOptionPane.showMessageDialog(frame_d, "���벻��Ϊ��");
                        return;
                    }
                    else if(messageTypeField.getText().equals("֪ͨ")){calls.setBinaryStream(4,null,0);}
                    else if(messageTypeField.getText().equals("��ҵ")){
                        /*upload upl = new upload();*/
                        File f;
                        JFileChooser chooser;
                        chooser = new JFileChooser();
                        chooser.setCurrentDirectory(new File("C:\\Users\\10334\\Desktop"));	//��λ����ǰĿ¼
                        if(chooser.showOpenDialog(null)==JFileChooser.CANCEL_OPTION)
                            System.exit(0);
                        f = chooser.getSelectedFile();
                        int length = (int)f.length();
                        System.out.println(length);
                        InputStream fin = new FileInputStream(f);	//�ļ�������
                        calls.setBinaryStream(4,fin,length);
                    }
                    else{
                        JOptionPane.showMessageDialog(frame_d, "ֻ����֪ͨ����ҵ");
                        return;
                    }
                    calls.execute();
                    calls.close();
                    JOptionPane.showMessageDialog(frame_d, "�����ɹ���");
                }
                else if (ae.getActionCommand().equals("����ǩ��")) {
                    String sql = "select Cournum from ��ʦ��Ϣ�� where TeaID='"+teanum+"';";
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


