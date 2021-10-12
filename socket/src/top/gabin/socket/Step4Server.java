package top.gabin.socket;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Set;

public class Step4Server {
    private ServerSocketChannel ssc;

    @SuppressWarnings("InfiniteLoopStatement")
    public void listen(int port) throws IOException {
        if (ssc != null) {
            throw new UnsupportedOperationException("不能重复启动");
        }
        ssc = ServerSocketChannel.open();
        ssc.bind(new InetSocketAddress(port));

        // Reactive | Reactor
        ssc.configureBlocking(false);

        Selector selector = Selector.open();

        ssc.register(selector, ssc.validOps(), null);
        ByteBuffer byteBuffer = ByteBuffer.allocate(1024 * 16);

        for (;;) {
            int numOfKeys = selector.select();
            System.out.println(numOfKeys);
            Set<SelectionKey> selectionKeys = selector.selectedKeys();
            for (SelectionKey key : selectionKeys) {
                if (key.isAcceptable()) {
                    SocketChannel channel = ssc.accept();
                    if (channel == null) {
                        continue;
                    }
                    channel.configureBlocking(false);
                    channel.register(selector, SelectionKey.OP_READ);
                } else {
                    SocketChannel channel = (SocketChannel) key.channel();

                    byteBuffer.clear();
                    channel.read(byteBuffer);
                    String request = new String(byteBuffer.array());
                    System.out.println(request);
                    byteBuffer.clear();
                    byteBuffer.put("HTTP/1.1 200 ok\n\nHello NIO\n".getBytes());
                    byteBuffer.flip();

                    channel.write(byteBuffer);
                    channel.close();
                }
            }
        }
    }

    public static void main(String[] args) throws IOException {
        Step4Server step4Server = new Step4Server();
        step4Server.listen(8080);
    }

}
