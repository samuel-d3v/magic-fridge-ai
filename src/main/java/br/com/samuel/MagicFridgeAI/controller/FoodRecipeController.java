package br.com.samuel.MagicFridgeAI.controller;

import br.com.samuel.MagicFridgeAI.model.dto.FoodItemResponseDTO;
import br.com.samuel.MagicFridgeAI.service.FoodItemService;
import br.com.samuel.MagicFridgeAI.service.FoodRecipeService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

import java.util.List;

@RestController("https://api.openai.com/v1/responses")
public class FoodRecipeController {

    private final FoodRecipeService recipeService;
    private final FoodItemService itemService;

    public FoodRecipeController(FoodRecipeService recipeService, FoodItemService itemService) {
        this.recipeService = recipeService;
        this.itemService = itemService;
    }

    @GetMapping("/generate")
    public Mono<ResponseEntity<String>> generateRecipe() {
        List<FoodItemResponseDTO> foodItems = itemService.listFood();

        return recipeService.generateRecipe(foodItems)
                .map(recipe -> ResponseEntity.ok(recipe))
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }
}


