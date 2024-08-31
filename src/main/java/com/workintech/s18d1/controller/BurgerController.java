package com.workintech.s18d1.controller;

import com.workintech.s18d1.dao.BurgerDao;
import com.workintech.s18d1.entity.BreadType;
import com.workintech.s18d1.entity.Burger;
import com.workintech.s18d1.exceptions.BurgerException;
import com.workintech.s18d1.util.BurgerValidation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Iterator;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/burger")
public class BurgerController {
    private final  BurgerDao burgerDao;

    @Autowired
    public BurgerController(BurgerDao burgerDao) {
        this.burgerDao = burgerDao;
    }

    @GetMapping
    public List<Burger> getAllBurgers(){
     return burgerDao.findAll();
    }

    @GetMapping("/{id}")
    public Burger getBurgerById(@PathVariable long id){
        if(id<=0){
            throw new BurgerException("Id must be greater than 0",HttpStatus.BAD_REQUEST);
        }
        Burger burger = burgerDao.findById(id);
        if (burger == null) {
            throw new BurgerException("Burger with this id doesn't exist.", HttpStatus.NOT_FOUND);
        }
      return  burgerDao.findById(id);
    }

    @GetMapping("/price/{price}")
    public List<Burger> findByPrice(@PathVariable("price") Integer price){
        return burgerDao.findByPrice(price);
    }

    @GetMapping("/breadType/{breadType}")
    public List<Burger> getByBreadType(@PathVariable("breadType") String breadType) {
        BreadType bt = BreadType.valueOf(breadType);
        return burgerDao.findByBreadType(bt);
    }


    @GetMapping("/content/{content}")
    public List<Burger> findByContent(@PathVariable("content") String content){
        return burgerDao.findByContent(content);
    }

    @PutMapping
    public Burger updateBurger(@RequestBody Burger burger){
        return burgerDao.update(burger);
    }

    @PostMapping
    public Burger save(@RequestBody Burger burger){
        BurgerValidation.checkName(burger.getName());
        burgerDao.save(burger);
        return burger;
    }

    @DeleteMapping("/{id}")
    public Burger deleteBurger(@PathVariable long id){
        return burgerDao.remove(id);
    }


}
