package com.ppj.demo.sc.eshop.cache.ha.local;

import java.util.HashMap;
import java.util.Map;

/**
 * @author pipi
 * @since 2021/8/24 20:35
 */
public class BrandCache {

    private static Map<Long, String> brandMap = new HashMap<>();

    static {
        brandMap.put(1L, "IPhone");
    }

    public static String getBrandName(Long brandId) {
        return brandMap.get(brandId);
    }


}
