//package com.neeq.tradingDemo.config;
//
//import org.springframework.security.core.userdetails.User;
//import com.neeq.tradingDemo.service.UserService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.core.GrantedAuthority;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//
//import java.util.ArrayList;
//import java.util.List;
//
///**
// * @author 宋立成
// * @date 2021/8/13 11:10
// */
//public class UserSecurity implements UserDetailsService {
//
//    @Autowired
//    private UserService userService;
//
//    @Override
//    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//        if (username == null || "".equals(username)) {
//            throw new RuntimeException("用户不能为空");
//        }
//        //根据用户名查询用户
//        com.neeq.tradingDemo.model.User user = userService.findByUserName(username);
//        if (user == null) {
//            throw new RuntimeException("用户不存在");
//        }
//        List<GrantedAuthority> grantedAuthorities = new ArrayList<>();
//        return new User(user.getName(), user.getPassword(), grantedAuthorities);
//    }
//}
