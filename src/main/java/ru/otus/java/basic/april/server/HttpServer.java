package ru.otus.java.basic.april.server;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class HttpServer {
    private int port;
    private Dispatcher dispatcher;
    private ExecutorService executor;

    public HttpServer(int port, int threadCount) {
        this.port = port;
        this.dispatcher = new Dispatcher();
        this.executor = Executors.newFixedThreadPool(threadCount);

    }


    public void start() {
        try (ServerSocket serverSocket = new ServerSocket(port)) {
            System.out.println("Сервер запущен на порту: " + port);
            while (true) {
                Socket socket = serverSocket.accept();
                executor.execute(() -> {
                    try (Socket s = socket;
                         InputStream in = s.getInputStream();
                         OutputStream out = s.getOutputStream()) {

                        process(in, out);

                    } catch (IOException | InterruptedException e) {
                        e.printStackTrace();
                    }
                });
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void process(InputStream inputStream, OutputStream outputStream) throws IOException, InterruptedException {
        String rawRequest =  readRequest(inputStream);
        if (rawRequest.length() < 1) {
            return;
        }

        HttpRequest request = new HttpRequest(rawRequest);
        request.info(false);
        dispatcher.execute(request, outputStream);
    }

    public String readRequest(InputStream inputStream) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
        StringBuilder request = new StringBuilder();

        String line;
        int contentLength = 0;

        while ((line = reader.readLine()) != null) {
            if (line.isEmpty()) {
                break;
            }
            request.append(line).append("\r\n");
            if (line.toLowerCase().startsWith("content-length:")) {
                contentLength = Integer.parseInt(line.split(":")[1].trim());
            }
        }
        if (request.length() < 1) {
            return request.toString();
        }
        request.append("\r\n");

        if (contentLength > 0) {
            char[] body = new char[contentLength];
            int read = 0;

            while (read < contentLength) {
                int r = reader.read(body, read, contentLength - read);
                if (r == -1) break;
                read += r;
            }
            request.append(body, 0, read);
        }
        return request.toString();
    }
}
