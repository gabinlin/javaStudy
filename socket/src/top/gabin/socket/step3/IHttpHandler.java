package top.gabin.socket.step3;

import java.io.IOException;

@FunctionalInterface
public interface IHttpHandler {
    void handler(Request request, Response response) throws IOException;
}
