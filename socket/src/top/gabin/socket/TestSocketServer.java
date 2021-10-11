package top.gabin.socket;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class TestSocketServer {

    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(8080);
        while (true) {
            Socket accept = serverSocket.accept();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(accept.getInputStream()));
            String line;
            while (!(line = bufferedReader.readLine()).isEmpty()) {
                System.out.println(line);
            }
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(accept.getOutputStream()));
            writer.write("HTTP/1.1 200 ok\n\nhello world\n");
            writer.flush();
            accept.close();
        }
    }

}
