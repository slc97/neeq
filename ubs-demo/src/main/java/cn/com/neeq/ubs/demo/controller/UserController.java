package cn.com.neeq.ubs.demo.controller;

import cn.com.neeq.ubs.demo.model.User;
import cn.com.neeq.ubs.demo.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author 宋立成
 * @date 2021/8/12 16:09
 */
@Api(tags = "用户管理")
@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @ApiOperation("列表查询")
    @RequestMapping(value = "getAllUsers", method = RequestMethod.GET)
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    @ApiOperation("获取单个用户详细信息")
    @RequestMapping(value = "getUserById", method = RequestMethod.POST)
    public User getUserById(Integer id) {
        return userService.getUserById(id);
    }

    @ApiOperation("增加股票")
    @RequestMapping(value = "addUser", method = RequestMethod.POST)
    public void addUser(User user) {
        userService.addUser(user);
    }

    @ApiOperation("删除股票")
    @RequestMapping(value = "deleteUser", method = RequestMethod.POST)
    public void deleteUser(Integer id) {
        userService.deleteUser(id);
    }

    @ApiOperation("根据用户名查询用户")
    @RequestMapping(value = "findByUserName", method = RequestMethod.POST)
    public User findByUserName(String name) {
        return userService.findByUserName(name);
    }

    @ApiOperation("登录")
    @RequestMapping(value = "login",method=RequestMethod.POST)
    public String login(String name, String password) {
        return userService.login(name, password);
    }
}
