package org.restaurant.orderservice.features.menu.internal.service.impl;

import org.restaurant.orderservice.features.menu.internal.model.Dish;
import org.restaurant.orderservice.features.menu.internal.repository.MenuDao;
import org.restaurant.orderservice.features.menu.internal.service.MenuService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MenuServiceImpl implements MenuService {

   private final MenuDao menuDao;

    public MenuServiceImpl(MenuDao menuDao) {
        this.menuDao = menuDao;
    }

    @Override
    public List<Dish> listMenu() {
        return menuDao.findAll();
    }
}
