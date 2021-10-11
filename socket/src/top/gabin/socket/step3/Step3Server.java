package top.gabin.socket.step3;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.function.BiConsumer;

/**
 * 2、优化线程
 */
public class Step3Server {

    private ServerSocket serverSocket;
    private final ExecutorService executorService;
    private final IHttpHandler handler;

    public Step3Server(IHttpHandler handler) {
        executorService = Executors.newCachedThreadPool();
        this.handler = handler;
    }

    // pending queue
    public void listen(int port) throws IOException {
        serverSocket = new ServerSocket(port);
        while (true) {
            this.accept();
        }
    }

    private void accept() throws IOException {
        // Blocking...
        // Thread--->Sleep--->Other Socket\
        Socket accept = serverSocket.accept();
        executorService.submit(() -> {
            try {
                this.handler.handler(new Request(accept), new Response(accept));
                accept.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    public static void main(String[] args) throws IOException {
        new Step3Server((request, response) -> {
            System.out.println(request.getPath());
            System.out.println(request.getMethod());
            request.getHeaders().forEach((s, s2) -> {
                System.out.print(s);
                System.out.print(":");
                System.out.println(s2);
            });
            System.out.println(request.getBody());
            response.send("Hello World");
        }).listen(8080);
        /*
            for i in {1..50};

            do

            curl -X POST http://localhost:8080 &

            done
         */
    }


}
