package com.ppj.sc.demo.eureka.service.api;

import com.ppj.sc.demo.eureka.service.api.dto.User;
import org.springframework.web.bind.annotation.*;

/**
 * @author pipi
 * @since 2021/8/20 11:30
 */
@RequestMapping("/user")
public interface ServiceAInterface {
    @RequestMapping(value = "/sayHello/{id}", method = RequestMethod.GET)
    String sayHello(@PathVariable("id") Long id,
                    @RequestParam("name") String name,
                    @RequestParam("age") Integer age);

    @RequestMapping(value = "/", method = RequestMethod.POST)
    String createUser(@RequestBody User user);

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    String updateUser(@PathVariable("id") Long id, @RequestBody User user);

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    String deleteUser(@PathVariable("id") Long id);

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    User getById(@PathVariable("id") Long id);

}
