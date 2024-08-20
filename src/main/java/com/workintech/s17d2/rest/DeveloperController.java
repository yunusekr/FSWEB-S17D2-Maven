package com.workintech.s17d2.rest;

import com.workintech.s17d2.tax.Taxable;
import jakarta.annotation.PostConstruct;
import com.workintech.s17d2.model.Developer;
import com.workintech.s17d2.model.enums.Experience;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import com.workintech.s17d2.tax.DeveloperTax;

import java.time.temporal.TemporalAccessor;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/developers")
public class DeveloperController {

    public Map<Integer, Developer> developers = new HashMap<>();
    private Taxable developerTax;

    @PostConstruct
    public void init(){
        developers.put(1,new Developer(1,"Yunus",100000, Experience.SENIOR));
        developers.put(2,new Developer(2,"Furkan",50000, Experience.MID));
        developers.put(3,new Developer(3,"Batuhan",20000, Experience.JUNIOR));
    }

    @Autowired
    public DeveloperController(Taxable developerTax){
        this.developerTax = developerTax;
    }

    @GetMapping
    public List<Developer> getAllDevelopers(){
        return developers.values().stream().toList();
    }

    @GetMapping("/{id}")
    public Developer getAllDevelopersId(@PathVariable int id){
        return developers.get(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Developer addDeveloper(@RequestBody Developer developer){
        developers.put(developer.getId(),developer);
        if(developer.getExperience() == Experience.JUNIOR){
            developer.setSalary(developerTax.getSimpleTaxRate());
        }else if(developer.getExperience() == Experience.MID) {
            developer.setSalary(developerTax.getMiddleTaxRate());
        }else{
            developer.setSalary(developerTax.getUpperTaxRate());
        }
        return developers.get(developer.getId());
    }

    @PutMapping("/{id}")
    public Developer updateDeveloper(@PathVariable int id,@RequestBody Developer developer){
       return developers.put(id,developer);
    }

    @DeleteMapping("/{id}")
    public Developer deleteDeveloper(@PathVariable int id){
        return developers.remove(id);
    }


}
