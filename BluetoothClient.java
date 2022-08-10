import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Iterator;
import java.util.Set;
import javax.bluetooth.RemoteDevice;

class BluetoothClient {
    private OnDiscoverListener onDiscoverListener = null;//���ּ���
    private OnClientListener onClientListener = null;//�ͻ��˼���

    /**
     * �ͻ��˼���
     */
    public interface OnClientListener {
        void onConnected(InputStream inputStream, OutputStream outputStream);
        void onConnectionFailed();
        void onDisconnected();
        void onClose();
    }

    /**
     * ���ּ���
     */
    public interface OnDiscoverListener {
        void onDiscover(RemoteDevice remoteDevice);
    }

    /**
     * �޲ι��캯��
     */
    public BluetoothClient() {
    }

    /**
     * ��������
     * @throws IOException
     * @throws InterruptedException
     */
    public void find(String id) throws IOException, InterruptedException {
        //�������е������豸��������ִ�� runDiscovery
    	try {
    		Set<RemoteDevice> devicesDiscovered = RemoteDeviceDiscovery.getDevices(id);
    		Iterator<RemoteDevice> itr = devicesDiscovered.iterator();
            //����
            while (itr.hasNext()) {
                RemoteDevice remoteDevice = itr.next();
                onDiscoverListener.onDiscover(remoteDevice);
            }
    	}
    	catch(javax.bluetooth.BluetoothStateException e) {
    		System.out.println("*****�豸����δ�򿪣��뿪������*****");
    	}
    }

    public OnDiscoverListener getOnDiscoverListener() {
        return onDiscoverListener;
    }


    public void setOnDiscoverListener(OnDiscoverListener onDiscoverListener) {
        this.onDiscoverListener = onDiscoverListener;
    }


    public OnClientListener getClientListener() {
        return onClientListener;
    }
    public void setClientListener(OnClientListener onClientListener) {
        this.onClientListener = onClientListener;
    }


}
