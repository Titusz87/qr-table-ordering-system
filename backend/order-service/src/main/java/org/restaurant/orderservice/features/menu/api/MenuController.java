package org.restaurant.orderservice.features.menu.api;

import org.restaurant.orderservice.features.menu.dto.GetMenuResponseDto;
import org.restaurant.orderservice.features.menu.internal.mapper.MenuMapper;
import org.restaurant.orderservice.features.menu.internal.model.Dish;
import org.restaurant.orderservice.features.menu.internal.service.MenuService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RestController
@RequestMapping(path="/api/v1/menu")
public class MenuController {
    private final MenuService menuService;
    private final MenuMapper menuMapper;

    public MenuController(MenuService menuService, MenuMapper menuMapper) {
        this.menuService = menuService;
        this.menuMapper = menuMapper;
    }

    @GetMapping
    public ResponseEntity<List<GetMenuResponseDto>> listMenu(
    ) {
        List<Dish> menu = menuService.listMenu();
        List<GetMenuResponseDto> response = menuMapper.toGetMenuResponseDto(menu);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}

