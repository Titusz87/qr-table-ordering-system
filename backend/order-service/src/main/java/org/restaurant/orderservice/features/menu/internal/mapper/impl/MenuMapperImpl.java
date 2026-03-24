package org.restaurant.orderservice.features.menu.internal.mapper.impl;

import org.restaurant.orderservice.features.menu.dto.GetMenuResponseDto;
import org.restaurant.orderservice.features.menu.internal.mapper.MenuMapper;
import org.restaurant.orderservice.features.menu.internal.model.Dish;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class MenuMapperImpl implements MenuMapper {
    @Override
    public List<GetMenuResponseDto> toGetMenuResponseDto(List<Dish> menu) {
        return menu.stream()
                .map(dish -> new GetMenuResponseDto(
                        dish.getDishName(),
                        dish.getIngredients(),
                        dish.getCategory(),
                        dish.getDietary(),
                        dish.getPrice()
                ))
                .toList();
    }
}
