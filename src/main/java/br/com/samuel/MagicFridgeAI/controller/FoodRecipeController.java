package br.com.samuel.MagicFridgeAI.controller;

import br.com.samuel.MagicFridgeAI.service.FoodRecipeService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController("https://api.openai.com/v1/responses")
public class FoodRecipeController {

    private final FoodRecipeService service;

    public FoodRecipeController(FoodRecipeService service) {
        this.service = service;
    }

    @GetMapping("/generate")
    public Mono<ResponseEntity<String>> generateRecipe() {

        return service.generateRecipe()
                .map(recipe -> ResponseEntity.ok(recipe))
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }
}


