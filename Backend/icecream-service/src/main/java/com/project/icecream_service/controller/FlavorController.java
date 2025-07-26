package com.project.icecream_service.controller;

import com.project.icecream_service.entity.Flavor;
import com.project.icecream_service.service.FlavorService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/flavors")
@RequiredArgsConstructor
public class FlavorController {

    private final FlavorService flavorService;

    @GetMapping
    public List<Flavor> getAll() {
        return flavorService.getAllFlavors();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Flavor> getOne(@PathVariable Long id) {
        return flavorService.getFlavorById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Flavor create(@RequestBody Flavor flavor) {
        return flavorService.createFlavor(flavor);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Flavor> update(@PathVariable Long id, @RequestBody Flavor updated) {
        return flavorService.updateFlavor(id, updated)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        return flavorService.deleteFlavor(id) ?
                ResponseEntity.noContent().build() :
                ResponseEntity.notFound().build();
    }
}
