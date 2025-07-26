package com.project.icecream_service.service;

import com.project.icecream_service.entity.Flavor;
import com.project.icecream_service.repository.FlavorRepository;
import com.project.icecream_service.service.FlavorService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class FlavorServiceImpl implements FlavorService {

    private final FlavorRepository flavorRepository;

    @Override
    public List<Flavor> getAllFlavors() {
        return flavorRepository.findAll();
    }

    @Override
    public Optional<Flavor> getFlavorById(Long id) {
        return flavorRepository.findById(id);
    }

    @Override
    public Flavor createFlavor(Flavor flavor) {
        return flavorRepository.save(flavor);
    }

    @Override
    public Optional<Flavor> updateFlavor(Long id, Flavor updatedFlavor) {
        return flavorRepository.findById(id).map(existing -> {
            existing.setName(updatedFlavor.getName());
            existing.setDescription(updatedFlavor.getDescription());
            existing.setPrice(updatedFlavor.getPrice());
            existing.setAvailable(updatedFlavor.getAvailable());
            return flavorRepository.save(existing);
        });
    }

    @Override
    public boolean deleteFlavor(Long id) {
        if (flavorRepository.existsById(id)) {
            flavorRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
