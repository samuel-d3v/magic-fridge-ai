package br.com.samuel.MagicFridgeAI.model.dto;

import br.com.samuel.MagicFridgeAI.model.entity.Categoria;

import java.time.LocalDate;
import java.time.LocalDateTime;

public record FoodItemResponseDTO(Long id, String nome, Categoria categoria, Integer quantidade, LocalDate validade, LocalDateTime dataCriacao) {
}
