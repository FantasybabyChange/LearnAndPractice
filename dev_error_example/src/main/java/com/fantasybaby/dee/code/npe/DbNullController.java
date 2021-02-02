package com.fantasybaby.dee.code.npe;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.util.Date;

/**
 * @author reid.liu
 */
@RestController
@RequestMapping("dbnull")
@Slf4j
public class DbNullController {

    @Resource
    private UserRepository userRepository;

    /**
     * {
     *  "name":"曦月",
     *  "age":"13"
     *  }
     * @param user
     * @return
     */
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public User save(@RequestBody User user) {
        user.setCreatedDate(new Date());
        return userRepository.save(user);
    }
    @RequestMapping(value = "/save/wrong", method = RequestMethod.POST)
    public User insertUser(@RequestBody User user) {
        user.setNickName(String.format("guest%s", user.getName()));
        return userRepository.save(user);
    }
//    @PostConstruct
//    public void init() {
//        userRepository.save(new User());
//    }
//    @GetMapping("wrong")
//    public void wrong() {
//        log.info("result: {} {} {} ", userRepository.wrong1(), userRepository.wrong2(), userRepository.wrong3());
//    }
//
//    @GetMapping("right")
//    public void right() {
//        log.info("result: {} {} {} ", userRepository.right1(), userRepository.right2(), userRepository.right3());
//    }
}
