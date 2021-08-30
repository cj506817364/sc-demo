package com.ppj.demo.sc.eshop.cache.ha.model;

import lombok.Data;

/**
 * @author pipi
 * @since 2021/8/24 19:51
 */
@Data
public class ProductInfo {
    private Long id;
    private String name;
    private Double price;
    private String pictureList;
    private String specification;
    private String service;
    private String color;
    private String size;
    private Long shopId;
    private String modifiedTime;
    private Long cityId;
    private String cityName;
    private Long brandId;
    private String brandName;
}
