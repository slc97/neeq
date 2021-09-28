package cn.com.neeq.ubs.demo.service.impl;

import cn.com.neeq.ubs.demo.mapper.UserMapper;
import cn.com.neeq.ubs.demo.model.User;
import cn.com.neeq.ubs.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author 宋立成
 * @date 2021/8/12 16:07
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public List<User> getAllUsers() {
        return userMapper.getAllUsers();
    }

    @Override
    public User getUserById(Integer id) {
        return userMapper.getUserById(id);
    }

    @Override
    public void addUser(User user) {
        userMapper.addUser(user);
    }

    @Override
    public void deleteUser(Integer id) {
        userMapper.deleteUser(id);
    }

    @Override
    public User findByUserName(String name) {
        return userMapper.findByUserName(name);
    }

    @Override
    public String login(String name, String password) {
        User user = userMapper.findByUserName(name);
        if(user == null) {
            return "用户名不存在";
        }
        if(user.getPassword().equals(password)) {
            return "Welcome to neeq";
        }
        return "密码错误";
    }
}
