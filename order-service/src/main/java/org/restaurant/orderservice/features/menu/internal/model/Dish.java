package org.restaurant.orderservice.features.menu.internal.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;

import java.math.BigDecimal;
import java.util.Objects;

@Entity
@Table(name = "menu_data")
public class Dish {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Integer id;
        @JsonProperty("name")
        @Column(name = "dish_name")
        private String dishName;
        private String ingredients;
        @Column(name="price", precision=10, scale=2)
        private BigDecimal price;
        @Enumerated(EnumType.STRING)
        private Category category;
        @Enumerated(EnumType.STRING)
        private Dietary dietary;

        public Dish() {
        }

        public Dish(Integer id, String dishName, String ingredients, BigDecimal price, Category category, Dietary dietary) {
                this.id = id;
                this.dishName = dishName;
                this.ingredients = ingredients;
                this.price = price;
                this.category = category;
                this.dietary = dietary;
        }

        public Integer getId() {
                return id;
        }

        public void setId(Integer id) {
                this.id = id;
        }

        public String getDishName() {
                return dishName;
        }

        public void setDishName(String dishName) {
                this.dishName = dishName;
        }

        public String getIngredients() {
                return ingredients;
        }

        public void setIngredients(String ingredients) {
                this.ingredients = ingredients;
        }

        public BigDecimal getPrice() {
                return price;
        }

        public void setPrice(BigDecimal price) {
                this.price = price;
        }

        public Category getCategory() {
                return category;
        }

        public void setCategory(Category category) {
                this.category = category;
        }

        public Dietary getDietary() {
                return dietary;
        }

        public void setDietary(Dietary dietary) {
                this.dietary = dietary;
        }

        @Override
        public boolean equals(Object o) {
                if (o == null || getClass() != o.getClass()) return false;
                Dish dish = (Dish) o;
                return Objects.equals(id, dish.id);
        }

        @Override
        public String toString() {
                return "Dish{" +
                        "id=" + id +
                        ", dishName='" + dishName + '\'' +
                        ", ingredients='" + ingredients + '\'' +
                        ", price=" + price +
                        ", category=" + category +
                        ", dietary=" + dietary +
                        '}';
        }

        @Override
        public int hashCode() {
                return Objects.hashCode(id);
        }
}
