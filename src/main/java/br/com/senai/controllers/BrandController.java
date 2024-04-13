package br.com.senai.controllers;

import br.com.senai.models.Brand;
import br.com.senai.repositories.BrandRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/brand")
public class BrandController {
    @Autowired
    BrandRepository brandRepository;

    @GetMapping(value = "/all", produces = MediaType.APPLICATION_JSON_VALUE)

    public List<Brand> getAllCoffee() {
        return brandRepository.findAll();
    }

    @PostMapping(value = "/createBrand",
            produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    public Brand createBrand(@RequestBody Brand brand) {
        Brand newBrand = new Brand();
        newBrand.setName(brand.getName());
        newBrand.setPassword(brand.getPassword());

        return brandRepository.save(newBrand);
    }

    @PutMapping(value = "/updateBrand",
            produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    public Brand updateBrand(@RequestBody Brand brand){
        Brand getBrand = brandRepository.findById(brand.getId()).orElseThrow();
        Brand updateBrand = new Brand();

        updateBrand.setId(brand.getId());
        updateBrand.setName(brand.getName());
        updateBrand.setPassword(brand.getPassword());

        return brandRepository.save(updateBrand);

    }

    @DeleteMapping(value="/deleteCoffee/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Brand deleteBrand(@PathVariable Long id){
        Brand getBrand = brandRepository.findById(id).orElseThrow();
        brandRepository.delete(getBrand);
        return getBrand;
    }
}
