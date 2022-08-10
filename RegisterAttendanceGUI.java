import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.Vector;

import javax.bluetooth.RemoteDevice;
import javax.microedition.io.ConnectionNotFoundException;
import javax.swing.*;

class RegisterAttendanceGUI extends JFrame {
	public JPanel jPanel;
	public JButton findDevices;
	public static JTextArea attendanceInfo;
		
	public RegisterAttendanceGUI(String id) {
		jPanel = new JPanel();
		attendanceInfo = new JTextArea(8,30);
		attendanceInfo.setEditable(false);
		
		findDevices = new JButton("开始签到");
		findDevices.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				BluetoothClient client = new BluetoothClient();

		        Vector<RemoteDevice> remoteDevices = new Vector<>();

		        client.setOnDiscoverListener(new BluetoothClient.OnDiscoverListener() {

		            @Override
		            public void onDiscover(RemoteDevice remoteDevice) {
		                remoteDevices.add(remoteDevice);
		            }

		        });

		        try {
		            client.find(id);

		            if (remoteDevices.size() > 0 ) {

		               // client.startClient(remoteDevices.firstElement(), serverUUID);
		            }
		        }catch (InterruptedException e1) {
		            e1.printStackTrace();
		        }catch (ConnectionNotFoundException e1){
		            attendanceInfo.setText("当前蓝牙不在线");
		            e1.printStackTrace();
		        }catch (IOException e1) {
		            e1.printStackTrace();
		        }
			}
			
		});
		
		jPanel.add(findDevices);
		jPanel.add(attendanceInfo);
		
		this.add(jPanel, BorderLayout.CENTER);
		
		this.setSize(600, 480);
		this.setLocation(200, 200);
		this.setVisible(true);
		this.setTitle("课堂签到管理");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	/*public static void main(String[] args) {
		new RegisterAttendanceGUI();     
	}*/
}
