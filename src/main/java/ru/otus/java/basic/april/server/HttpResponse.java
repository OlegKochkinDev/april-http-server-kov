package ru.otus.java.basic.april.server;

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

public class HttpResponse {
    private int statusCode;
    private String statusMessage;
    private String body = "";
    private Map<String, String> headers;


    public HttpResponse(int statusCode, String statusMessage) {
        this.statusCode = statusCode;
        this.statusMessage = statusMessage;
        this.headers = new HashMap<>();
    }

    public int getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }

    public String getStatusMessage() {
        return statusMessage;
    }

    public void setStatusMessage(String statusMessage) {
        this.statusMessage = statusMessage;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public void addHeader(String key, String value) {
        this.headers.put(key, value);
    }

    public HttpResponse clear(){
        headers.clear();
        this.body = "";
        return this;
    }


    public String getRawResponse() {
        StringBuilder builder = new StringBuilder();
        builder.append("HTTP/1.1 " + statusCode + " " + statusMessage + "\r\n");
        for (String key : headers.keySet()) {
            builder.append(key + ": " + headers.get(key) + "\r\n");
        }
        builder.append("\r\n");
        builder.append(body);
        return builder.toString();
    }

    public void send(OutputStream out) throws IOException {
        out.write(this.getRawResponse().getBytes(StandardCharsets.UTF_8));
    }

}
