package com.ppj.demo.sc.eshop.product.ha.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;

/**
 * @author pipi
 * @since 2021/8/24 19:40
 */
@RestController
public class ProductController {

    @RequestMapping("/getProductInfo")
    @ResponseBody
    public String getProductInfo(Long productId) {
        return "{\"id\": " + productId + ", \"name\": \"iphone7手机\", \"price\": 5599, \"pictureList\":\"a.jpg,b.jpg\", \"specification\": \"iphone7的规格\", \"service\": \"iphone7的售后服务\", \"color\": \"红色,白色,黑色\", \"size\": \"5.5\", \"shopId\": 1, \"modifiedTime\": \"2017-01-01 12:00:00\", \"cityId\": 1, \"brandId\": 1}";
    }

    @RequestMapping("/getProductInfos")
    @ResponseBody
    public String getProductInfos(Long[] productIds) {
        System.out.println("getProductInfos接口，接收到一次请求，productIds=" + Arrays.toString(productIds));
        JSONArray jsonArray = new JSONArray();
        for (Long productId : productIds) {
            String json = "{\"id\": " + productId + ", \"name\": \"iphone7手机\", \"price\": 5599, \"pictureList\":\"a.jpg,b.jpg\", \"specification\": \"iphone7的规格\", \"service\": \"iphone7的售后服务\", \"color\": \"红色,白色,黑色\", \"size\": \"5.5\", \"shopId\": 1, \"modifiedTime\": \"2017-01-01 12:00:00\", \"cityId\": 1, \"brandId\": 1}";
            jsonArray.add(JSONObject.parseObject(json));
        }
        return jsonArray.toJSONString();
    }
}
