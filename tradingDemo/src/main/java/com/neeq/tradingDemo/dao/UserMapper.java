package com.neeq.tradingDemo.dao;

import com.neeq.tradingDemo.model.Stock;
import com.neeq.tradingDemo.model.User;
import lombok.Data;

import java.util.List;

/**
 * @author 宋立成
 * @date 2021/8/12 15:21
 */
public interface UserMapper {
    List<User> getAllUsers();
    User getUserById(Integer id);
    void addUser(User user);
    void deleteUser(Integer id);

    User findByUserName(String name);
}
