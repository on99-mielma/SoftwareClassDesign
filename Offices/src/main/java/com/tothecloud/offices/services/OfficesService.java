package com.tothecloud.offices.services;


import com.tothecloud.offices.domain.Office;
import com.tothecloud.offices.domain.OfficesLite;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import reactor.core.publisher.Mono;

import java.util.List;


public interface OfficesService {

    /**
     * 失败的设计 太早在Service层封装了 不方便service层的封装调用
     * @return Mono<List<Office>>
     */
    List<Office> getOfficesList();

    List<OfficesLite> getOfficesLiteList();
    Office getOfficesOne(@PathVariable Long id);

    Boolean insertOfficesOne(@RequestBody Office office);

    Boolean deleteOfficesByid(@PathVariable Long id);

    Boolean updateOfficesByDomain(@RequestBody Office office);


}
