package br.com.samuel.MagicFridgeAI.service;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.Map;

@Service
public class FoodRecipeService {
    private final WebClient webClient;

    public FoodRecipeService(WebClient webClient) {
        this.webClient = webClient;
    }

    public Mono<ResponseEntity<String>> generateRecipe() {
        String prompt = "Me sugira uma receita simples com ingredientes comuns.";

        Map<String, Object> requestBody = Map.of(
                "model", "gpt-5-nano",
                "input", List.of(
                        Map.of("role", "system",
                                "content", List.of(
                                        Map.of(
                                                "type", "input_text",
                                                "text", "Você é um assistente que cria receitas "
                                        )
                                )
                        ),
                        Map.of("role", "user",
                                "content", List.of(
                                        Map.of(
                                                "type", "input_text",
                                                "text", prompt
                                        )
                                )
                        )
                )
        );

        return fetchResponse(requestBody);
    }

    private Mono<ResponseEntity<String>> fetchResponse(Map<String, Object> requestBody) {
        return webClient.post()
                .uri("/responses")
                .bodyValue(requestBody)
                .retrieve()
                .bodyToMono(Map.class)
                .map(response -> {
                    String text = extractOutputText(response);

                    return text != null ? ResponseEntity.ok().body(text) : ResponseEntity.ok().body("Nenhuma receita foi gerada");
                });
    }

    private String extractOutputText(Map<String, Object> response) {
        var outputs = (List<Map<String, Object>>) response.get("output");

        if(outputs == null || outputs.isEmpty()) {
            return null;
        }

        for(var output : outputs) {
            var contents = (List<Map<String, Object>>) output.get("content");

            if(contents == null || contents.isEmpty()) continue;

            for(var content : contents) {
                if ("output_text".equals(content.get("type"))) {
                    return (String) content.get("text");
                }
            }
        }

        return null;
    }
}
