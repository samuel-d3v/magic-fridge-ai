package br.com.samuel.MagicFridgeAI.model.mapper;

import br.com.samuel.MagicFridgeAI.model.dto.FoodItemResponseDTO;
import br.com.samuel.MagicFridgeAI.model.dto.RegisterFoodItemForm;
import br.com.samuel.MagicFridgeAI.model.dto.RegisterFoodItemRequest;
import br.com.samuel.MagicFridgeAI.model.entity.FoodItem;
import org.springframework.stereotype.Component;

@Component
public class FoodItemMapper {
    public FoodItemMapper(){}

    /**
     * Mapeia o request para a entidade food item
     */
    public FoodItem toEntity(RegisterFoodItemRequest request) {
        FoodItem item = new FoodItem();
        item.setNome(request.nome());
        item.setCategoria(request.categoria());
        item.setQuantidade(request.quantidade());
        item.setValidade(request.validade());

        return item;
    }

    /**
     * Mapeia a entidade para o DTO food item
     */
    public FoodItemResponseDTO toDTO(FoodItem item) {
        return new FoodItemResponseDTO(
                item.getId(),
                item.getNome(),
                item.getCategoria(),
                item.getQuantidade(),
                item.getValidade(),
                item.getDataCriacao()
        );
    }

    /**
     *
     * Mapeia o DTO food item form para o dto food item request
     */
    public RegisterFoodItemRequest toDTOForm(RegisterFoodItemForm form) {
        return new RegisterFoodItemRequest(
                form.getNome(),
                form.getCategoria(),
                form.getQuantidade(),
                form.getValidade()
        );
    }

    /**
     * Mapeia as informações do DTO request que não forem nulas para a entidade food item
     */
    public void applyPatch(RegisterFoodItemRequest dto, FoodItem entity) {
        if(dto.nome() != null) {
            entity.setNome(dto.nome());
        }
        if(dto.categoria() != null) {
            entity.setCategoria(dto.categoria());
        }
        if(dto.quantidade() != null) {
            entity.setQuantidade(dto.quantidade());
        }
        if(dto.validade() != null) {
            entity.setValidade(dto.validade());
        }
    }
}
