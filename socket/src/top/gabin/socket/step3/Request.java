package top.gabin.socket.step3;

import java.io.*;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Request {
    private Socket socket;

    private final String path;
    private String method;
    private final String body;
    private final Map<String, String> headers = new HashMap<>();

    private final static Pattern COMPILE = Pattern.compile("(GET|POST|DELETE|OPTION|HEAD|PUT|PUSH|TRACE)");

    public Request(Socket socket) throws IOException {
        this.socket = socket;
        String[] dataArray = getDataArray();

        String first = dataArray[0];
        Matcher matcher = COMPILE.matcher(first);
        if (matcher.find()) {
            method = matcher.group();
        }
        this.path = first.split(" ")[1];

        int i = 1;
        for (; i < dataArray.length; i++) {
            String s = dataArray[i];
            if (s.trim().isEmpty()) {
                break;
            }
            String[] split = s.split(": ");
            headers.put(split[0], split[1]);
        }
        body = dataArray[i + 1].trim();
    }

    private String[] getDataArray() throws IOException {
        InputStream inputStream = this.socket.getInputStream();
        DataInputStream dataInputStream = new DataInputStream(inputStream);
        BufferedReader reader = new BufferedReader(new InputStreamReader(dataInputStream));
        char[] chars = new char[1024];
        while (dataInputStream.available() > 0) {
            reader.read(chars);
        }
        String allData = new String(chars);
        return allData.split("\n");
    }

    public String getMethod() {
        return method;
    }

    public Map<String, String> getHeaders() {
        return headers;
    }

    public String getBody() {
        return body;
    }

    public String getPath() {
        return path;
    }

    @Override
    public String toString() {
        return "Request{" +
                "path='" + path + '\'' +
                ", method='" + method + '\'' +
                ", headers=" + headers +
                ", body='" + body + '\'' +
                '}';
    }
}
