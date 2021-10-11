package top.gabin.socket;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.function.Function;

public class Step1Server {

    private ServerSocket serverSocket;
    private final Function<String, String> handler;

    public Step1Server(Function<String, String> handler) {
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
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(accept.getInputStream()));
        String line;
        StringBuilder requestBody = new StringBuilder();
        while (!(line = bufferedReader.readLine()).isEmpty()) {
            requestBody.append(line);
        }
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(accept.getOutputStream()));
        String responseBody = this.handler.apply(requestBody.toString());
        writer.write(responseBody);
        writer.flush();
        accept.close();
    }

    public static void main(String[] args) throws IOException {
        new Step1Server(req -> "HTTP/1.1 200 ok\n\nHello World\n").listen(8080);
    }


}
