package com.tothecloud.offices.services.impl;

import com.tothecloud.offices.domain.Office;
import com.tothecloud.offices.domain.OfficesLite;
import com.tothecloud.offices.mapper.OfficeMapper;
import com.tothecloud.offices.services.OfficesService;
import com.tothecloud.offices.utils.ConstantUtils;
import com.tothecloud.offices.utils.NullUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;


@Service
@Slf4j
public class OfficesServiceImpl implements OfficesService {

    @Autowired
    private OfficeMapper officeMapper;

    @Override
    public List<Office> getOfficesList() {
        List<Office> officesList = officeMapper.selectAll();
        return Objects.requireNonNullElse(officesList, Collections.emptyList());
    }

    @Override
    public List<OfficesLite> getOfficesLiteList() throws AssertionError{
        List<Office> officesList = this.getOfficesList();
        if (officesList.size() == 0) {
            return Collections.emptyList();
        }
        List<OfficesLite> officesLiteList = new ArrayList<>(officesList.size());
        for (Office office:officesList){
            try{
                if (null == office.getOfficeName() || office.getOfficeName().equals("null")){
                    officesLiteList.add(new OfficesLite(ConstantUtils.SET_DISTINCT));
                    continue;
                }
                officesLiteList.add(new OfficesLite(office));
            }catch (AssertionError assertionError){
                log.debug(assertionError.getLocalizedMessage());
                log.debug(assertionError.getMessage());
            }
        }
        return officesLiteList;
    }

    @Override
    public Office getOfficesOne(Long id) {
        Office notification = officeMapper.selectOneByid(id);
//        if (notification != null) {
//            return Mono.just(notification);
//        }
//        return Mono.empty();
        return Objects.requireNonNullElse(notification,null);
    }


    @Override
    public Boolean insertOfficesOne(Office office) {
        if (null == office.getOfficeName()) {
            return false;
        }
        return officeMapper.insertOne(NullUtils.fixNull(office)) > 0;
    }

    @Override
    public Boolean deleteOfficesByid(Long id) {
        return officeMapper.deleteOneByid(id) > 0;
    }

    @Override
    public Boolean updateOfficesByDomain(Office office) {
        if (null == office.getId()){
            return false;
        }
        return officeMapper.updateOneByDomain(NullUtils.mergeNull(officeMapper.selectOneByid(office.getId()), office)) > 0;
    }
}
