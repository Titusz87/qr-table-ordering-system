package org.restaurant.orderservice.features.menu.dto;

import org.restaurant.orderservice.features.menu.internal.model.Category;
import org.restaurant.orderservice.features.menu.internal.model.Dietary;
import java.math.BigDecimal;

public record GetMenuResponseDto(
         Integer id,
         String dishName,
         String ingredients,
         Category category,
         Dietary dietary,
         BigDecimal price
) {
}
