package org.restaurant.orderservice.features.menu.internal.repository;

import org.restaurant.orderservice.features.menu.internal.model.Dish;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MenuDao extends JpaRepository<Dish, Integer>{
    List<Dish> findAll();

    Optional<Dish> findByDishName(String dishName);

}
