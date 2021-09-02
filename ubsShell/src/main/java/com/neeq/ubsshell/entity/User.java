package com.neeq.ubsshell.entity;


/**
 * @author 宋立成
 * @date 2021/8/12 15:14
 */
public class User {

    private Integer id;

    private String name;

    private String password;

    public User(String s) {
        String[] strs = s.split(",");
        this.id = Integer.valueOf(strs[0]);
        this.name = String.valueOf(strs[1]);
        this.password = String.valueOf(strs[2]);
    }

//    public User(String name, String password) {
//        this.name = name;
//        this.password = password;
//    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
