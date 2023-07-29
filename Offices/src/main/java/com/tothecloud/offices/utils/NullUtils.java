package com.tothecloud.offices.utils;

import com.tothecloud.offices.domain.Office;

public class NullUtils {
    private final static String SYSTEM_DEFAULT_INFO = "SYSTEM_DEFAULT_INFO";
    public static Office fixNull(Office office){
        if (null == office.getId()){
            office.setId(SnowflakeUtils.generateId());
        }
        if (null == office.getOfficeInfo()){
            office.setOfficeInfo(SYSTEM_DEFAULT_INFO);
        }
        return office;
    }

    public static Office mergeNull(Office exist,Office update){
        if (null == exist){
            return update;
        }
        if (null == update.getOfficeName()){
            update.setOfficeName(exist.getOfficeName());
        }
        if (null == update.getOfficeInfo()){
            update.setOfficeInfo(exist.getOfficeInfo());
        }
        return update;
    }
}
