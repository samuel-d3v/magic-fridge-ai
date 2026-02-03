package br.com.samuel.MagicFridgeAI.service;

import br.com.samuel.MagicFridgeAI.model.mapper.FoodItemMapper;
import br.com.samuel.MagicFridgeAI.model.dto.FoodItemResponseDTO;
import br.com.samuel.MagicFridgeAI.model.dto.RegisterFoodItemRequest;
import br.com.samuel.MagicFridgeAI.model.entity.FoodItem;
import br.com.samuel.MagicFridgeAI.repository.FoodItemRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FoodItemService {

    private final FoodItemRepository repository;
    private final FoodItemMapper mapper;

    public FoodItemService(FoodItemRepository repository, FoodItemMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    public FoodItemResponseDTO addItem(RegisterFoodItemRequest foodRequest) {
        if(foodRequest.nome().isBlank()) {
            throw new IllegalArgumentException("Error");
        }

        FoodItem item = repository.save(mapper.toEntity(foodRequest));
        return mapper.toDTO(item);
    }

    public List<FoodItemResponseDTO> listFood() {
         return repository.findAll()
                 .stream()
                 .map(mapper::toDTO)
                 .toList();
    }

    public FoodItemResponseDTO findById(long id) {
        FoodItem item = repository.findById(id)
                .orElseThrow(
                        () -> new IllegalArgumentException("Erro")
                );

        return mapper.toDTO(item);
    }

    public FoodItemResponseDTO updateById(long id, RegisterFoodItemRequest request) {
        FoodItem existItem = repository.findById(id)
                .orElseThrow(
                        () -> new IllegalArgumentException("teste")
                );

        mapper.applyPatch(request, existItem);

        FoodItem itemSalvo = repository.save(existItem);
        return mapper.toDTO(itemSalvo);
    }

    public void deleteById(long id) {
        findById(id);

        repository.deleteById(id);
    }

}
