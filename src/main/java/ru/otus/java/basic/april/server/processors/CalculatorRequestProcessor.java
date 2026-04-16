package ru.otus.java.basic.april.server.processors;

import ru.otus.java.basic.april.server.HttpRequest;

import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;

public class CalculatorRequestProcessor implements RequestProcessor {
    @Override
    public void execute(HttpRequest request, OutputStream output) throws IOException {
        int a = Integer.parseInt(request.getParameter("a"));
        int b = Integer.parseInt(request.getParameter("b"));
        String solution = a + " + " + b + " = " + (a + b);
        String result =  "<html><body><h1>" + solution + "</h1></body></html>";

        String response = "" +
                "HTTP/1.1 200 OK\r\n" +
                "Content-Type: text/html\r\n" +
                "Content-length: " + result.length() + "\r\n" +
                "Connection: close\r\n" +
                "\r\n" +
                result;
        output.write(response.getBytes(StandardCharsets.UTF_8));
    }
}
