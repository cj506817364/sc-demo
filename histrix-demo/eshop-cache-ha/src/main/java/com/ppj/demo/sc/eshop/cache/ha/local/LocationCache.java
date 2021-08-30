package com.ppj.demo.sc.eshop.cache.ha.local;

import java.util.HashMap;
import java.util.Map;

/**
 * @author pipi
 * @since 2021/8/24 20:35
 */
public class LocationCache {

    private static Map<Long, String> cityMap = new HashMap<>();

    static {
        cityMap.put(1L, "北京");
    }

    public static String getCityName(Long cityId) {
        return cityMap.get(cityId);
    }


}
