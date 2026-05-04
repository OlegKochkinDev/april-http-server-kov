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

public class GetItemsRequestProcessor implements RequestProcessor {
    @Override
    public void execute(HttpRequest request, OutputStream output) throws IOException {
        List<Item> items = new ArrayList<>(Arrays.asList(
                new Item(1L, "Bread", 50),
                new Item(2L, "Milk", 150),
                new Item(3L, "Cheese", 400)
        ));
        Gson gson = new Gson();
        String result = gson.toJson(items);


        HttpResponse httpResponse = request.getStandartResponse(200);
        httpResponse.addHeader("Content-Type", "application/json");
        httpResponse.addHeader("Content-length", String.valueOf(result.length()));
        httpResponse.addHeader("Connection", "close");
        httpResponse.setBody(result);
        httpResponse.send(output);
    }
}
