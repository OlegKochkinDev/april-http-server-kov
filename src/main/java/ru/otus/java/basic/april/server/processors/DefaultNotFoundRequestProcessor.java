package ru.otus.java.basic.april.server.processors;

import ru.otus.java.basic.april.server.HttpRequest;
import ru.otus.java.basic.april.server.HttpResponse;

import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;

public class DefaultNotFoundRequestProcessor implements RequestProcessor {
    @Override
    public void execute(HttpRequest request, OutputStream output) throws IOException {
        String body = "<html><body><h1>404.. Page Not Found</h1></body></html>";
        HttpResponse httpResponse = request.getStandartResponse(404);
        httpResponse.addHeader("Content-Type", "text/html");
        httpResponse.addHeader("Content-length", String.valueOf(body.length()));
        httpResponse.addHeader("Connection", "close");
        httpResponse.setBody(body);
        httpResponse.send(output);
    }
}
