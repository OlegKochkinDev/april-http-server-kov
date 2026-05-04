package ru.otus.java.basic.april.server.processors;

import ru.otus.java.basic.april.server.HttpRequest;
import ru.otus.java.basic.april.server.HttpResponse;

import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;

public class HelloRequestProcessor implements RequestProcessor {
    @Override
    public void execute(HttpRequest request, OutputStream output) throws IOException {
        String body = "<html><body><h1>Hello World!!!</h1></body></html>";
        HttpResponse httpResponse = request.getStandartResponse(200);
        httpResponse.addHeader("Content-Type", "text/html");
        httpResponse.addHeader("Content-length", String.valueOf(body.length()));
        httpResponse.addHeader("Connection", "close");
        httpResponse.setBody(body);
        httpResponse.send(output);
    }
}
