package br.com.samuel.MagicFridgeAI.model.dto;

import br.com.samuel.MagicFridgeAI.model.entity.Categoria;

import java.time.LocalDate;

public record RegisterFoodItemRequest(String nome, Categoria categoria, Integer quantidade, LocalDate validade) {
}
