package com.workintech.s18d1.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name="burger",schema = "public")
public class Burger {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
   private long id;

    @Column(name="name")
   private String name;

    @Column(name="price")
   private double price;

    @Column(name="is_Vegan")
   private boolean isVegan;

    @Enumerated(EnumType.STRING)
   private BreadType breadType;

    @Column(name="contents")
   private String contents;

    public void setIsVegan(boolean isVegan){
        this.isVegan=isVegan;
    }

    public boolean getIsVegan(){
        return isVegan;
    }
}
