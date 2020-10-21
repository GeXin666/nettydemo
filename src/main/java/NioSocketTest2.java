
import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;

public class NioSocketTest2 {

    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket();
        //serverSocket.setReceiveBufferSize(8192*8);
        System.out.println(serverSocket.getReceiveBufferSize());
        serverSocket.bind(new InetSocketAddress("0.0.0.0", 9090), 256);
        System.out.println("ServerSocketBind: 9090");
        Socket socket = serverSocket.accept();
        System.out.println("Accept new Client" + socket.getReceiveBufferSize());
        socket.setKeepAlive(true);
        System.out.println("Set KeepAlive:true");
    }

}
