package com.selenium.demo;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class ChatGptApi {
    private static final String CHATGPT_API_URL = "https://api.openai.com/v1/chat/completions";
    private static final String API_KEY = "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";

    private static final String MODEL = "gpt-4o-mini";

    private static String getJsonPayload(String prompt) {
        return "{"
                + "\"model\": \"" + MODEL + "\","
                + "\"messages\": [{\"role\": \"user\", \"content\": \"" + prompt + "\"}],"
                + "\"max_tokens\": 100,"
                + "\"stream\": false"
                + "}";
    }

    static String makeChatGptRequestAndGetAnswer(String prompt) throws IOException, InterruptedException {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(CHATGPT_API_URL))
                .header("Content-Type", "application/json")
                .header("Authorization", "Bearer " + API_KEY)
                .POST(HttpRequest.BodyPublishers.ofString(getJsonPayload(prompt)))
                .build();

        HttpClient client = HttpClient.newHttpClient();
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        System.out.println("This is the response from OpenAi API: \n " + response.body().toString());

        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode rootNode = objectMapper.readTree(response.body());

        String content = rootNode
                .path("choices")
                .path(0)
                .path("message")
                .path("content")
                .asText();
        return content;

    }
}
