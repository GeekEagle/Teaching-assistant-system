import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileOutputStream;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.*;


public class Assistant implements chosenidentity {
	private JFrame frame;
    private JLabel hwframe,difframe,markframe;
    private JTextArea homeworkarea,answer;
    private JTextField score,solve;
    public JButton answerQuestion,mark,nexthw,solution;
    private Dimension size;
    public static final String MARK = "��ҵ����";
    public static final String CHECKANSWER = "���̴���";
    public static final String NEXTHW = "��һ����ҵ";
    public static final String SOLUTION = "�ϴ�����";
    private String stunum,subject;
    JLabel assnum = new JLabel();
    JLabel assname = new JLabel();
    JLabel asspro = new JLabel();
    JLabel asscour = new JLabel();

    public static void main(String args[])
    {
        Assistant ass = new Assistant();
        JTextField login = new JTextField();
        login.setText("ass456");
        JPasswordField password = new JPasswordField();
        password.setText("22222");
        ass.login(login,password);
    }
    public Assistant(){
        size = new Dimension(800,500);
        setloginpanel();
    }
    public void setloginpanel(){
        hwframe = new JLabel("������ҵ��");
        difframe = new JLabel("���ɿ�");
        markframe = new JLabel("������");
        homeworkarea = new JTextArea(20,20);
        score = new JTextField(20);
        solve = new JTextField(20);
        answer = new JTextArea(20,20);
        mark = new JButton(MARK);
        solution = new JButton(SOLUTION);
        nexthw = new JButton(NEXTHW);
        answerQuestion = new JButton(CHECKANSWER);
        AssisIssue btn = new AssisIssue();
        mark.addActionListener(btn);
        solution.addActionListener(btn);
        nexthw.addActionListener(btn);
        answerQuestion.addActionListener(btn);
        JLabel jassnum,jassname,jasspro,jasscour,huida;
        jassnum = new JLabel("����");jassnum.setFont(new Font("����",Font.PLAIN,20));
        jassname = new JLabel("����");jassname.setFont(new Font("����",Font.PLAIN,20));
        jasspro = new JLabel("רҵ");jasspro.setFont(new Font("����",Font.PLAIN,20));
        jasscour = new JLabel("�γ̺�");jasscour.setFont(new Font("����",Font.PLAIN,20));
        huida = new JLabel("�ش�");huida.setFont(new Font("����",Font.PLAIN,20));

        frame = new JFrame();
        frame.setTitle("���̵�¼");
        frame.setSize(1200,800);
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
        gridbag.setConstraints(jassnum, gbc);
        gbc.gridx = 2;
        gbc.gridy = 0;
        gridbag.setConstraints(jassname, gbc);
        gbc.gridx = 4;
        gbc.gridy = 0;
        gridbag.setConstraints(jasspro, gbc);
        gbc.gridx = 6;
        gbc.gridy = 0;
        gridbag.setConstraints(jasscour, gbc);

        gbc.gridx = 1;
        gbc.gridy = 0;
        gridbag.setConstraints(assnum, gbc);
        gbc.gridx = 3;
        gbc.gridy = 0;
        gridbag.setConstraints(assname, gbc);
        gbc.gridx = 5;
        gbc.gridy = 0;
        gridbag.setConstraints(asspro, gbc);
        gbc.gridx = 7;
        gbc.gridy = 0;
        gridbag.setConstraints(asscour, gbc);

        gbc.insets.left = 5;
        gbc.insets.right = 5;
        gbc.insets.top = 15;
        gbc.gridx = 1;
        gbc.gridy = 6;
        gridbag.setConstraints(mark, gbc);
        gbc.gridx = 2;
        gbc.gridy = 6;
        gridbag.setConstraints(solution, gbc);
        gbc.gridx = 3;
        gbc.gridy = 6;
        gridbag.setConstraints(nexthw, gbc);
        gbc.gridx = 4;
        gbc.gridy = 6;
        gridbag.setConstraints(answerQuestion, gbc);

        gbc.insets.left = 4;
        gbc.insets.right = 4;
        gbc.insets.top = 10;
        gbc.gridx = 2;
        gbc.gridy = 1;
        gridbag.setConstraints(hwframe, gbc);
        gbc.gridx = 3;
        gbc.gridy = 1;
        gridbag.setConstraints(homeworkarea, gbc);
        gbc.gridx = 4;
        gbc.gridy = 1;
        gridbag.setConstraints(difframe, gbc);
        gbc.gridx = 5;
        gbc.gridy = 1;
        gridbag.setConstraints(answer, gbc);
        gbc.gridx = 2;
        gbc.gridy = 2;
        gridbag.setConstraints(markframe, gbc);
        gbc.gridx = 3;
        gbc.gridy = 2;
        gridbag.setConstraints(score, gbc);
        gbc.gridx = 4;
        gbc.gridy = 2;
        gridbag.setConstraints(huida, gbc);
        gbc.gridx = 5;
        gbc.gridy = 2;
        gridbag.setConstraints(solve,gbc);

        frame.add(jassnum);
        frame.add(assnum);
        frame.add(jassname);
        frame.add(assname);
        frame.add(jasspro);
        frame.add(asspro);
        frame.add(jasscour);
        frame.add(asscour);
        frame.add(mark);
        frame.add(nexthw);
        frame.add(solution);
        frame.add(answerQuestion);
        frame.add(hwframe);
        frame.add(homeworkarea);
        frame.add(difframe);
        frame.add(answer);
        frame.add(markframe);
        frame.add(score);
        frame.add(huida);
        frame.add(solve);
        frame.setVisible(true);
    }
    public void login( JTextField login, JPasswordField text_name ){
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
            System.out.println("jhahahhah");
            SQL = "select *" + " from ������Ϣ��" + " where AssisID='" + login.getText().trim()+"';";
            stmt = con.prepareStatement(SQL);
            // stmt.setString(1,text_name.getText());
            rs = stmt.executeQuery();

            if (rs.next()) {
                str[1] = rs.getString(1).trim();
                str[2] = rs.getString(2).trim();
                str[3] = rs.getString(3).trim();
                str[4] = rs.getString(4).trim();
                subject = str[4];
            }
        }catch (Exception ex) {
            ex.printStackTrace();
            System.out.println("no this user\n");
        }
        assnum.setText(str[1]);assnum.setFont(new Font("����",Font.PLAIN,20));
        assname.setText(str[2]);assname.setFont(new Font("����",Font.PLAIN,20));
        asspro.setText(str[3]);asspro.setFont(new Font("����",Font.PLAIN,20));
        asscour.setText(str[4]);asscour.setFont(new Font("����",Font.PLAIN,20));
    }
    class AssisIssue implements ActionListener{
        private  JFrame fra = new JFrame();
        String quesid,id;
        public void actionPerformed(ActionEvent e){
            String connectionUrl = "jdbc:sqlserver://localhost:1433;DatabaseName=rgks";
            Connection con = null;
            PreparedStatement stmt = null;
            ResultSet rs = null;
            try{
                Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
                con = DriverManager.getConnection(connectionUrl, "sa", "123456");
                if(e.getActionCommand().equals(CHECKANSWER)){
                    String SQL = "select * from ����������� where quescon='"+"δ���"+"';";
                    stmt = con.prepareStatement(SQL);
                    rs = stmt.executeQuery();
                    if (rs.next()){
                        quesid = rs.getString(1).trim();
                        String answ = rs.getString(3).trim();
                        answer.append(answ);
                    }else{JOptionPane.showMessageDialog(fra, "�޴���");}
                }
                else if(e.getActionCommand().equals(MARK)){
                    String SQL = "select * from ��ҵ�ύ�� where score=-1 and CourseID='"+subject+"';";
                    stmt = con.prepareStatement(SQL);
                    rs = stmt.executeQuery();
                    if(score.getText().matches("[0-9]+")){
                        if (rs.next()){
                            String num = rs.getString(1).trim();	//��ҵ�ύ���
                            String SQL2 = "update ��ҵ�ύ�� set score='"+Integer.parseInt(score.getText())+"'where WorksubmitID='"+id+"';";
                            stmt = con.prepareStatement(SQL2);
                            int resu = stmt.executeUpdate();
                            JOptionPane.showMessageDialog(fra, "���ֳɹ�");
                        }
                    } else JOptionPane.showMessageDialog(fra, "�ɼ�Ҫ������");
                }

                else if(e.getActionCommand().equals(NEXTHW)){
                    String SQL = "select * from ��ҵ�ύ�� where score=-1 and CourseID='"+subject+"';";
                    stmt = con.prepareStatement(SQL);
                    rs = stmt.executeQuery();

                    if (rs.next()){
                        File f;
                        JFileChooser chooser;
                        chooser = new JFileChooser();
                        chooser.setCurrentDirectory(new File("C:\\Users\\10334\\Desktop"));	//��λ����ǰĿ¼
                        if(chooser.showOpenDialog(null)==JFileChooser.CANCEL_OPTION){
                            System.exit(0);
                        }
                        Blob blob = rs.getBlob(3);
                        id = rs.getString(1);
                        if(chooser.showSaveDialog(null)== JFileChooser.CANCEL_OPTION){
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
                    else{JOptionPane.showMessageDialog(fra, "��������");}
                }
                else if(e.getActionCommand().equals(SOLUTION)){
                    String sol = solve.getText();
                    if(sol.equals("")){
                        JOptionPane.showMessageDialog(fra, "�����ύ�ս��");
                        return;
                    }
                    String SQL = "update ����������� set quesol = '"+sol+"',quescon = '"+"�ѽ��"+"' where Quesid = '"+quesid+"';";
                    stmt = con.prepareStatement(SQL);
                    int resu = stmt.executeUpdate();
                    JOptionPane.showMessageDialog(fra, "�ύ�ɹ�");
                }
            }catch (Exception ex) {
                ex.printStackTrace();
                System.out.println("no this user\n");
            }
        }
    }
	
}
