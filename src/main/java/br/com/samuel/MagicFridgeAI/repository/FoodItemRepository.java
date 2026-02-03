package br.com.samuel.MagicFridgeAI.repository;

import br.com.samuel.MagicFridgeAI.model.entity.FoodItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FoodItemRepository extends JpaRepository<FoodItem, Long>{
}
