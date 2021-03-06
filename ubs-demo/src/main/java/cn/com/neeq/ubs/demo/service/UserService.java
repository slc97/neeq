package cn.com.neeq.ubs.demo.service;

import cn.com.neeq.ubs.demo.model.User;

import java.util.List;

/**
 * @author 宋立成
 * @date 2021/8/12 16:06
 */
public interface UserService {
    List<User> getAllUsers();
    User getUserById(Integer id);
    void addUser(User user);
    void deleteUser(Integer id);
    User findByUserName(String name);
    String login(String name, String password);
}
