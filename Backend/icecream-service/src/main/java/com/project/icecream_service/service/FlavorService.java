package com.project.icecream_service.service;

import com.project.icecream_service.entity.Flavor;

import java.util.List;
import java.util.Optional;

public interface FlavorService {
    List<Flavor> getAllFlavors();
    Optional<Flavor> getFlavorById(Long id);
    Flavor createFlavor(Flavor flavor);
    Optional<Flavor> updateFlavor(Long id, Flavor updatedFlavor);
    boolean deleteFlavor(Long id);
}
