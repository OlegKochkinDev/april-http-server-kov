package ru.otus.java.basic.april.server;

import java.util.HashMap;
import java.util.Map;

public class StandartResponses {

    Map<Integer, HttpResponse> responses;


    public StandartResponses(){
        System.out.println("StandartResponses init");
        responses = new HashMap<Integer, HttpResponse>();
        responses.put(200, new HttpResponse(200, "OK"));
        responses.put(201, new HttpResponse(201, "Created"));
        responses.put(400, new HttpResponse(400, "Bad Request"));
        responses.put(404, new HttpResponse(404, "Not Found"));
        responses.put(500, new HttpResponse(500, "Internal Server Error"));
    }

    public HttpResponse get(int id) {
        return responses.get(id);
    }


}
