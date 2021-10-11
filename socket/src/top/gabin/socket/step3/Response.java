package top.gabin.socket.step3;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;

public class Response {
    private final Socket socket;
    private final int status = 200;
    private static final Map<Integer, String> CODE_MAP = new HashMap<>();

    static {
        CODE_MAP.put(200, "OK");
    }

    public Response(Socket socket) {
        this.socket = socket;
    }

    public void send(String msg) throws IOException {
        String responseString = "HTTP/1.1 " + this.status + " " + CODE_MAP.get(this.status) + "\n\n" +
                msg
                + "\n";
        sendRaw(responseString);
    }

    public void sendRaw(String msg) throws IOException {
        BufferedWriter outStream = getOutStream();
        outStream.write(msg);
        outStream.flush();
        socket.close();
    }

    private BufferedWriter getOutStream() throws IOException {
        return new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
    }
}
