package top.gabin.socket;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.function.Function;

/**
 * 2、优化线程
 */
public class Step2Server {

    private ServerSocket serverSocket;
    private final ExecutorService executorService;
    private final Function<String, String> handler;

    public Step2Server(Function<String, String> handler) {
        executorService = Executors.newCachedThreadPool();
        this.handler = handler;
    }

    // pending queue
    @SuppressWarnings("InfiniteLoopStatement")
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
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(accept.getInputStream()));
                String line;
                StringBuilder requestBody = new StringBuilder();
                while (!(line = bufferedReader.readLine()).isEmpty()) {
                    requestBody.append(line).append("\n");
                }
                BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(accept.getOutputStream()));
                String responseBody = this.handler.apply(requestBody.toString());
                writer.write(responseBody);
                writer.flush();
                accept.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    public static void main(String[] args) throws IOException {
        new Step2Server(req -> {
            System.out.println(Thread.currentThread().hashCode());
            System.out.println(req);
            return "HTTP/1.1 200 ok\n\nHello World\n";
        }).listen(8080);
        /*
            for i in {1..50};

            do

            curl -X POST http://localhost:8080 &

            done
         */
    }


}
