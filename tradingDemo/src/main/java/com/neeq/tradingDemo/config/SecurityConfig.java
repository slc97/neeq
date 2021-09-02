//package com.neeq.tradingDemo.config;
//
//import com.neeq.tradingDemo.service.UserService;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.builders.WebSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
//import org.springframework.security.core.userdetails.User;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.provisioning.InMemoryUserDetailsManager;
//
///**
// * @author 宋立成
// * @date 2021/8/12 14:56
// */
//@Configuration
//public class SecurityConfig extends WebSecurityConfigurerAdapter {
//
//    @Bean
//    public UserDetailsService userDetailsService() {
//        return new UserSecurity();
//    }
//
//    //配置绕过security验证  不走Spring security
//    @Override
//    public void configure(WebSecurity web) throws Exception {
//        super.configure( web );
////        web.ignoring().antMatchers( "/getAllUsers" );
//    }
//
//    //配置
//    @Override
//    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//        // super.configure( auth );
//        //通过IDEA自带的Java类图看出了UserDetailService的子类InMemoryUserDetailsManager
//        //测试方式  给密码添加的时候必须加上{noop}  authorities 或者roles必须添加
//        //authorities 此用户的权限
//        //roles 此用户的角色
//        auth.userDetailsService(userDetailsService());
////        auth.userDetailsService(new InMemoryUserDetailsManager(
////                User.builder().username("jsbintask1").password("{noop}123456").authorities("jsbintask1").build(),
////                User.builder().username("jsbintask2").password("{noop}123456").authorities("jsbintask2").build()));
//    }
//
//    //默认配置 是拦截所有的  不采用默认配置 则重写
//    @Override
//    protected void configure(HttpSecurity http) throws Exception {
//        //super.configure( http );
//        //loginPage  自定义配置页面
//        //loginProcessinUrl登录拦截的URL   security有一个自带的login页面 如果需要自定义登陆页面则重写
//        //successForwardUrl  成功登录之后跳转
//        http.formLogin()
//                .permitAll()
//                .and()
//                .authorizeRequests();
//    }
//}