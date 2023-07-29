package com.tothecloud.offices.controller;


import com.tothecloud.offices.domain.Office;
import com.tothecloud.offices.services.OfficesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import java.util.List;

@RestController
@RequestMapping("/offs")
public class OfficesController {

    @Autowired
    private OfficesService officesService;

    @GetMapping("/all")
    Mono<List<Office>> getOfficesList() {
        return Mono.just(officesService.getOfficesList());
    }

    @PostMapping("/one/{id}")
    Mono<Office> getOfficesOne(@PathVariable Long id) {
        Office office = officesService.getOfficesOne(id);
        if (office == null) {
            return Mono.empty();
        }
        return Mono.just(office);
    }

    @PostMapping("/addOne")
    Mono<Boolean> addOfficesOne(@RequestBody Office office) {
        return Mono.just(officesService.insertOfficesOne(office));
    }

    @DeleteMapping("/del/{id}")
    Mono<Boolean> delOfficesOne(@PathVariable Long id) {
        return Mono.just(officesService.deleteOfficesByid(id));
    }

    @PutMapping("/updOne")
    Mono<Boolean> updOfficesOne(@RequestBody Office office) {
        return Mono.just(officesService.updateOfficesByDomain(office));
    }
}
