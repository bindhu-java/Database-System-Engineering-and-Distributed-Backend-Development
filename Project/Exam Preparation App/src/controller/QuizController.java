package controller;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.util.List;
import model.Question;
import service.AdaptiveQuizService;

public class QuizController implements HttpHandler {

    private final AdaptiveQuizService service;

    public QuizController(AdaptiveQuizService service) {
        this.service = service;
    }

    @Override
    public void handle(HttpExchange exchange) throws IOException {

        addCors(exchange);

        if (exchange.getRequestMethod().equalsIgnoreCase("OPTIONS")) {
            exchange.sendResponseHeaders(204, -1);
            exchange.close();
            return;
        }

        String query = exchange.getRequestURI().getQuery();

        String topic = "Java";
        int count = 5;

        if (query != null) {
            String[] parameters = query.split("&");

            for (String parameter : parameters) {
                String[] pair = parameter.split("=", 2);

                if (pair.length == 2) {

                    if (pair[0].equals("topic")) {
                        topic = URLDecoder.decode(
                                pair[1],
                                StandardCharsets.UTF_8
                        );
                    }

                    if (pair[0].equals("count")) {
                        try {
                            count = Integer.parseInt(pair[1]);
                        } catch (NumberFormatException e) {
                            count = 5;
                        }
                    }
                }
            }
        }

        List<Question> result = service.getQuestions(topic, count);

        StringBuilder json = new StringBuilder();

        json.append("{");
        json.append("\"topic\":\"").append(topic).append("\",");
        json.append("\"questionCount\":").append(result.size()).append(",");
        json.append("\"adaptiveMode\":true,");
        json.append("\"questions\":[");

        for (int i = 0; i < result.size(); i++) {

            Question q = result.get(i);

            json.append("{");

            json.append("\"id\":").append(q.getId()).append(",");
            json.append("\"topic\":\"").append(q.getTopic()).append("\",");
            json.append("\"difficulty\":\"").append(q.getDifficulty()).append("\",");
            json.append("\"question\":\"")
                    .append(q.getQuestion().replace("\"", "\\\""))
                    .append("\",");

            json.append("\"options\":[");

            String[] options = q.getOptions();

            if (options != null) {
                for (int j = 0; j < options.length; j++) {

                    json.append("\"")
                            .append(options[j].replace("\"", "\\\""))
                            .append("\"");

                    if (j < options.length - 1) {
                        json.append(",");
                    }
                }
            }

            json.append("]");
            json.append("}");

            if (i < result.size() - 1) {
                json.append(",");
            }
        }

        json.append("]");
        json.append("}");

        String response = json.toString();

        exchange.getResponseHeaders()
                .set("Content-Type", "application/json");

        exchange.sendResponseHeaders(
                200,
                response.getBytes(StandardCharsets.UTF_8).length
        );

        OutputStream output = exchange.getResponseBody();

        output.write(response.getBytes(StandardCharsets.UTF_8));

        output.close();
    }

    private void addCors(HttpExchange exchange) {

        exchange.getResponseHeaders().set(
                "Access-Control-Allow-Origin",
                "*"
        );

        exchange.getResponseHeaders().set(
                "Access-Control-Allow-Methods",
                "GET, POST, OPTIONS"
        );

        exchange.getResponseHeaders().set(
                "Access-Control-Allow-Headers",
                "Content-Type"
        );
    }
}