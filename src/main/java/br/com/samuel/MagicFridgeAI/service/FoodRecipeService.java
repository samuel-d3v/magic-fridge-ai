package br.com.samuel.MagicFridgeAI.service;

import br.com.samuel.MagicFridgeAI.model.dto.FoodItemResponseDTO;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class FoodRecipeService {
    private final WebClient webClient;

    public FoodRecipeService(WebClient webClient) {
        this.webClient = webClient;
    }

    public Mono<String> generateRecipe(List<FoodItemResponseDTO> items) {
        String alimento = items.stream()
                .map(item -> String.format("Nome: %s - Categoria: %s, Quantidade: %d, Validade: %td/%tm/%tY", item.nome(), item.categoria(), item.quantidade(), item.validade(), item.validade(), item.validade()))
                .collect(Collectors.joining("\n"));

        String prompt = "Baseado no meu banco de dados, faça uma receita com os seguintes itens: " + alimento;

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

    private Mono<String> fetchResponse(Map<String, Object> requestBody) {
        return webClient.post()
                .uri("/responses")
                .bodyValue(requestBody)
                .retrieve()
                .bodyToMono(Map.class)
                .map(response -> {
                    String text = extractOutputText(response);

                    return text != null ? text : "Nenhuma receita foi gerada";
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
