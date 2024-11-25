package com.ctu.map.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.OptimisticLockingFailureException;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import com.ctu.map.models.Farm;
import com.ctu.map.services.FarmService;

import java.util.List;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("/api/farms")
public class FarmController {

    @Autowired
    private FarmService farmService;

    @GetMapping
    public List<Farm> getAllFarms() {
        return farmService.getAllFarms();
    }

    @GetMapping("/{id}")
    public Farm getFarmById(@PathVariable Long id) {
        return farmService.getFarmById(id);
    }

    @PostMapping("/{id}")
    public ResponseEntity<Farm> updateFarm(@PathVariable Long id, @RequestBody Farm farmDetails) {
        Farm existingFarm = farmService.getFarmById(id);

        if (existingFarm != null) {
            try {
                // Preserve the version and update other fields
                existingFarm.setName(farmDetails.getName());
                existingFarm.setCoordinates(farmDetails.getCoordinates());
                farmService.saveFarm(existingFarm); // Save the existing farm
                // The version field is managed by Hibernate, so no need to set it manually
                return ResponseEntity.ok()
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(existingFarm);
            } catch (OptimisticLockingFailureException e) {
                // Handle optimistic locking failure
                throw new ResponseStatusException(HttpStatus.CONFLICT,
                        "Farm record was updated or deleted by another transaction");
            }
        } else {
            // If farm doesn't exist, create a new one
            farmDetails.setId(id);
            farmService.saveFarm(farmDetails); // Correctly save farmDetails here
            return ResponseEntity.ok()
                    .contentType(MediaType.APPLICATION_JSON)
                    .body(farmDetails);
        }
    }

    @DeleteMapping("/{id}")
    public void deleteFarm(@PathVariable Long id) {
        farmService.deleteFarm(id);
    }
}
