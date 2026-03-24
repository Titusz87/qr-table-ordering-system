package org.restaurant.orderservice.features.menu.internal.mapper;

import org.restaurant.orderservice.features.menu.dto.GetMenuResponseDto;
import org.restaurant.orderservice.features.menu.internal.model.Dish;

import java.util.List;

public interface MenuMapper {
    List<GetMenuResponseDto> toGetMenuResponseDto(List<Dish> menu);
}
