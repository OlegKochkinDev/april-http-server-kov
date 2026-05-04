package ru.otus.java.basic.april.server.processors;

import ru.otus.java.basic.april.server.HttpRequest;
import ru.otus.java.basic.april.server.HttpResponse;
import ru.otus.java.basic.april.server.StandartResponses;


import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;

public class CalculatorRequestProcessor implements RequestProcessor {
    @Override
    public void execute(HttpRequest request, OutputStream output) throws IOException {
        int a = 0;
        int b = 0;
        String body = "";
        int statusCode = 200;

        try {
            a = Integer.parseInt(request.getParameter("a"));
            b = Integer.parseInt(request.getParameter("b"));
            String solution = a + " + " + b + " = " + (a + b);
            body = "<html><body><h1>" + solution + "</h1></body></html>";
        } catch (NumberFormatException e) {
            statusCode = 400;
        }

        HttpResponse httpResponse = request.getStandartResponse(statusCode);
        httpResponse.addHeader("Content-Type", "text/html");
        httpResponse.addHeader("Content-length", String.valueOf(body.length()));
        httpResponse.addHeader("Connection", "close");
        httpResponse.setBody(body);
        httpResponse.send(output);
    }
}
