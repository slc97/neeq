package cn.com.neeq.ubs.demo.mapper;

import cn.com.neeq.ubs.demo.model.User;

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
