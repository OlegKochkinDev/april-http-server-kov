package ru.otus.java.basic.april.server.processors;

import com.google.gson.Gson;
import ru.otus.java.basic.april.server.HttpRequest;
import ru.otus.java.basic.april.server.HttpResponse;
import ru.otus.java.basic.april.server.app.Item;

import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CreateItemRequestProcessor implements RequestProcessor {
    @Override
    public void execute(HttpRequest request, OutputStream output) throws IOException {

        int statusCode = 201;
        Gson gson = new Gson();
        Item item = gson.fromJson(request.getBody(), Item.class);

        HttpResponse httpResponse = request.getStandartResponse(statusCode);
        httpResponse.addHeader("Content-Type", "text/html");
        httpResponse.addHeader("Content-length", String.valueOf(0));
        httpResponse.addHeader("Connection", "close");
        httpResponse.send(output);
    }
}
