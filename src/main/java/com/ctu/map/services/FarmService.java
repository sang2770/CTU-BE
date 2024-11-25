package com.ctu.map.services;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ctu.map.models.Farm;
import com.ctu.map.repository.FarmRepository;

import java.util.List;

@Service
public class FarmService {

    @Autowired
    private FarmRepository farmRepository;

    public List<Farm> getAllFarms() {
        return farmRepository.findAll();
    }

    public Farm getFarmById(Long id) {
        return farmRepository.findById(id).orElse(null);
    }

    public Farm saveFarm(Farm farm) {
        return farmRepository.save(farm);
    }

    public void deleteFarm(Long id) {
        farmRepository.deleteById(id);
    }
}
