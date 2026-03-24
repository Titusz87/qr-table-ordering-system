package org.restaurant.orderservice.features.menu.internal.service;
import org.restaurant.orderservice.features.menu.internal.model.Dish;

import java.util.List;

public interface MenuService {
    List<Dish> listMenu();
}
