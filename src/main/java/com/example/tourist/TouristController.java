
package com.example.tourist;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;

@RestController
@RequestMapping("/api/tourist")
@SecurityRequirement(name = "bearerAuth")
public class TouristController {

    @Autowired
    private TouristRepository repo;

    @PutMapping("/{id}")
    public Tourist updateTourist(
            @PathVariable Long id,
            @RequestBody Tourist t) {

        Tourist old = repo.findById(id)
                .orElseThrow(() ->
                    new RuntimeException("Tourist not found"));

        old.setName(t.getName());
        old.setPlace(t.getPlace());
        old.setCountry(t.getCountry());

        return repo.save(old);
    }
}
