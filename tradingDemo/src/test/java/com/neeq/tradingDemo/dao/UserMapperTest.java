package com.neeq.tradingDemo.dao;

import com.neeq.tradingDemo.model.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

/**
 * @author 宋立成
 * @date 2021/8/12 15:30
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class UserMapperTest {

    @Autowired
    private UserMapper userMapper;

    @Test
    public void getAllUsersTest() {
        List<User> users = userMapper.getAllUsers();
        if(!users.isEmpty()) {
            System.out.println("无数据");
        }
        for(User user : users) {
            System.out.println(user);
        }
    }

    @Test
    public void getUserByIdTest() {
        User user = userMapper.getUserById(1);
        System.out.println(user);
    }

    @Test
    public void addUserTest() {
        User user = new User();
        user.setName("admin");
        user.setPassword("123456");
        userMapper.addUser(user);
    }

    @Test
    public void deleteUserTest() {
        userMapper.deleteUser(2);
    }

    @Test
    public void findByUserNameTest() {
        String name = "admin";
        User user = userMapper.findByUserName(name);
        System.out.println(user);
    }
}
