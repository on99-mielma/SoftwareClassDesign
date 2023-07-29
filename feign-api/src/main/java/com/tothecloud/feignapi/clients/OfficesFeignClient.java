package com.tothecloud.feignapi.clients;


import com.tothecloud.feignapi.domain.Office;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import reactor.core.publisher.Mono;

import java.util.List;

@FeignClient(value = "OfficesService")
public interface OfficesFeignClient {
    @GetMapping("/offs/all")
    List<Office> feignGetOfficesList();
    @PostMapping("/offs/one/{id}")
    Office feignGetOfficesOne(@PathVariable Long id);
}
