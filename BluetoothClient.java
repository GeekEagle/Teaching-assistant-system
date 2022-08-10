import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Iterator;
import java.util.Set;
import javax.bluetooth.RemoteDevice;

class BluetoothClient {
    private OnDiscoverListener onDiscoverListener = null;//发现监听
    private OnClientListener onClientListener = null;//客户端监听

    /**
     * 客户端监听
     */
    public interface OnClientListener {
        void onConnected(InputStream inputStream, OutputStream outputStream);
        void onConnectionFailed();
        void onDisconnected();
        void onClose();
    }

    /**
     * 发现监听
     */
    public interface OnDiscoverListener {
        void onDiscover(RemoteDevice remoteDevice);
    }

    /**
     * 无参构造函数
     */
    public BluetoothClient() {
    }

    /**
     * 查找所有
     * @throws IOException
     * @throws InterruptedException
     */
    public void find(String id) throws IOException, InterruptedException {
        //附近所有的蓝牙设备，必须先执行 runDiscovery
    	try {
    		Set<RemoteDevice> devicesDiscovered = RemoteDeviceDiscovery.getDevices(id);
    		Iterator<RemoteDevice> itr = devicesDiscovered.iterator();
            //连接
            while (itr.hasNext()) {
                RemoteDevice remoteDevice = itr.next();
                onDiscoverListener.onDiscover(remoteDevice);
            }
    	}
    	catch(javax.bluetooth.BluetoothStateException e) {
    		System.out.println("*****设备蓝牙未打开，请开启蓝牙*****");
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
