import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.HashSet;
import java.util.Set;
import java.util.Vector;

import javax.bluetooth.DataElement;
import javax.bluetooth.DeviceClass;
import javax.bluetooth.DiscoveryAgent;
import javax.bluetooth.DiscoveryListener;
import javax.bluetooth.LocalDevice;
import javax.bluetooth.RemoteDevice;
import javax.bluetooth.ServiceRecord;
import javax.swing.JTextArea;

class RemoteDeviceDiscovery {

    public final static Set<RemoteDevice> devicesDiscovered = new HashSet<RemoteDevice>();

    public final static Vector<String> serviceFound = new Vector<String>();

    final static Object serviceSearchCompletedEvent = new Object();
    final static Object inquiryCompletedEvent = new Object();
    private static String courseid;
    public static String str[] = new String[15];
    public static int i=0;
    
    /**
     * 发现监听
     */
    private static DiscoveryListener listener = new DiscoveryListener() {
        public void inquiryCompleted(int discType) {
            System.out.println("#" + "搜索完成");
            RegisterAttendanceGUI.attendanceInfo.append("#" + "签到结束");
            synchronized (inquiryCompletedEvent) {
                inquiryCompletedEvent.notifyAll();
            }
        }
        /**
         * 发现设备
         * @param remoteDevice
         * @param deviceClass
         */
        @Override
        public void deviceDiscovered(RemoteDevice remoteDevice, DeviceClass deviceClass) {
            devicesDiscovered.add(remoteDevice);

            try {
                str[i++] = remoteDevice.getFriendlyName(false);
                System.out.println("#发现设备" + str[i]);
                RegisterAttendanceGUI.attendanceInfo.append(remoteDevice.getFriendlyName(false)+"\n");
            } catch (IOException e) {
                e.printStackTrace();
            }
            String connectionUrl = "jdbc:sqlserver://localhost:1433;DatabaseName=rgks";
            // Declare the JDBC objects.
            Connection con = null;
            PreparedStatement stmt = null;
            ResultSet rs = null;
            try {
                Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
                con = DriverManager.getConnection(connectionUrl, "sa", "123456");
                // Create and execute an SQL statement that returns some data.
                String SQL = "select * " + "from 教师信息表 " + "where TeaID='"+courseid+"';";
                stmt = con.prepareStatement(SQL);
                rs = stmt.executeQuery();
                if (rs.next()) {
                    String kechenghao = rs.getString(4).trim();
                    String qiandaotijiao = "insert into 签到信息表 "+"values('"+str[i]+"','"+kechenghao+"'); ";
                    stmt = con.prepareStatement(qiandaotijiao);
                    rs = stmt.executeQuery();
                    rs.next();
                }
            }catch (Exception ex) {
                    ex.printStackTrace();
                    System.out.println("no this user\n");
                }
        }

        /**
         * 发现服务
         * @param transID id
         * @param servRecord 服务记录
         */
        @Override
        public void servicesDiscovered(int transID, ServiceRecord[] servRecord) {
            for (int i = 0; i < servRecord.length; i++) {
                String url = servRecord[i].getConnectionURL(ServiceRecord.NOAUTHENTICATE_NOENCRYPT, false);
                if (url == null) {
                    continue;
                }
                serviceFound.add(url);
                DataElement serviceName = servRecord[i].getAttributeValue(0x0100);
                if (serviceName != null) {
                    System.out.println("service " + serviceName.getValue() + " found " + url);
                    RegisterAttendanceGUI.attendanceInfo.append("service " + serviceName.getValue() + " found " + url+"\n");
                } else {
                    System.out.println("service found " + url);
                    RegisterAttendanceGUI.attendanceInfo.append("service found " + url+"\n");
                }
            }
            System.out.println("#" + "servicesDiscovered");
            RegisterAttendanceGUI.attendanceInfo.append("#" + "servicesDiscovered"+"\n");
        }

        /**
         * 服务搜索已完成
         * @param arg0
         * @param arg1
         */
        @Override
        public void serviceSearchCompleted(int arg0, int arg1) {
            System.out.println("#" + "serviceSearchCompleted");
            RegisterAttendanceGUI.attendanceInfo.append("#" + "serviceSearchCompleted"+"\n");
            synchronized(serviceSearchCompletedEvent){
                serviceSearchCompletedEvent.notifyAll();
            }
        }
    };


    /**
     * 查找设备
     * @throws IOException
     * @throws InterruptedException
     */
    private static void findDevices() throws IOException, InterruptedException {

        devicesDiscovered.clear();

        synchronized (inquiryCompletedEvent) {

            LocalDevice ld = LocalDevice.getLocalDevice();

            System.out.println("#本机蓝牙名称:" + ld.getFriendlyName());
            //RegisterAttendanceGUI.attendanceInfo.append("#本机蓝牙名称:" + ld.getFriendlyName()+"\n");

            boolean started = LocalDevice.getLocalDevice().getDiscoveryAgent().startInquiry(DiscoveryAgent.GIAC,listener);

            if (started) {
                System.out.println("#" + "等待搜索完成...");
                //RegisterAttendanceGUI.attendanceInfo.setText("#" + "等待搜索完成...");
                inquiryCompletedEvent.wait();
                LocalDevice.getLocalDevice().getDiscoveryAgent().cancelInquiry(listener);
                System.out.println("#发现设备数量：" + devicesDiscovered.size());
                RegisterAttendanceGUI.attendanceInfo.append("#已签到人数：" + devicesDiscovered.size()+"\n");
            }
        }
    }

    /**
     * 获取设备
     * @return
     * @throws IOException
     * @throws InterruptedException
     */
    public static Set<RemoteDevice> getDevices(String id) throws IOException, InterruptedException {
        courseid = id;
        findDevices();
        return devicesDiscovered;
    }
}