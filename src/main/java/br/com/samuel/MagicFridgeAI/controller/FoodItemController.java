package br.com.samuel.MagicFridgeAI.controller;

import br.com.samuel.MagicFridgeAI.model.dto.FoodItemResponseDTO;
import br.com.samuel.MagicFridgeAI.model.dto.RegisterFoodItemRequest;
import br.com.samuel.MagicFridgeAI.service.FoodItemService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/food")
public class FoodItemController {
    FoodItemService service;

    public FoodItemController(FoodItemService service) {
        this.service = service;
    }

    @PostMapping()
    public ResponseEntity<FoodItemResponseDTO> addItem(@RequestBody RegisterFoodItemRequest request) {
        FoodItemResponseDTO item = service.addItem(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(item);
    }

    @GetMapping()
    public ResponseEntity<List<FoodItemResponseDTO>> listFood() {
        List<FoodItemResponseDTO> items = service.listFood();
        return ResponseEntity.ok(items);
    }

    @GetMapping("/{id}")
    public ResponseEntity<FoodItemResponseDTO> findById(@PathVariable long id) {
        FoodItemResponseDTO item = service.findById(id);
        return ResponseEntity.ok(item);
    }

    @PutMapping("/{id}")
    public ResponseEntity<FoodItemResponseDTO> updateItem(@PathVariable long id, @RequestBody RegisterFoodItemRequest request) {
        FoodItemResponseDTO item = service.updateById(id, request);
        return ResponseEntity.ok(item);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteItem(@PathVariable long id) {
        service.deleteById(id);
        return ResponseEntity.ok("Deletado item com ID: " + id);
    }
}
