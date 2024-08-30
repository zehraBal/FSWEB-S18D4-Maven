package com.workintech.s18d1.controller;

import com.workintech.s18d1.dao.BurgerDao;
import com.workintech.s18d1.entity.BreadType;
import com.workintech.s18d1.entity.Burger;
import com.workintech.s18d1.exceptions.BurgerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Iterator;
import java.util.List;

@RestController
@RequestMapping("/burgers")
public class BurgerController {
    private BurgerDao burgerDao;

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

    @GetMapping("/findByPrice")
    public List<Burger> getBurgersByPrice(@RequestParam double price){
        return burgerDao.findByPrice(price);
    }

    @GetMapping("/findByBreadType")
    public List<Burger> getBurgerByBreadType(@RequestParam String breadType){
        BreadType type = BreadType.valueOf(breadType.toUpperCase());
        return burgerDao.findByBreadType(type);
    }

    @GetMapping("/findByContent")
    public List<Burger> getBurgerByContent(@RequestParam String content){
      return  burgerDao.findByContent(content);
    }

    @PutMapping("/{id}")
    public Burger updateBurger(@PathVariable long id,@RequestBody Burger burger){
        Burger oldBurger=burgerDao.findById(id);
        oldBurger.setName(burger.getName());
        oldBurger.setPrice(burger.getPrice());
        oldBurger.setBreadType(burger.getBreadType());
        oldBurger.setContents(burger.getContents());
        oldBurger.setIsVegan(burger.getIsVegan());
        return burgerDao.update(oldBurger);
    }

    @PostMapping
    public Burger save(@RequestBody Burger burger){
        burgerDao.save(burger);
        return burger;
    }

    @DeleteMapping("/{id}")
    public Burger deleteBurger(@PathVariable long id){
        Burger burgerToDelete = burgerDao.findById(id);
        if (burgerToDelete == null) {
            throw new BurgerException("Burger not found with this id.", HttpStatus.NOT_FOUND);
        }

        return burgerDao.remove(id);
    }


}
