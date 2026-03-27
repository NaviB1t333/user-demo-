package com.example.userdemo.controller;

import com.example.userdemo.common.Result;
import com.example.userdemo.entity.User;
import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {
    private static final List<User> userList = new ArrayList<>();

    static {
        userList.add(new User(1L,"张三",20));
        userList.add(new User(2L,"李四",22));
    }

    @GetMapping("/list")
    public Result<List<User>> list() {
        return Result.success(userList);
    }

    @GetMapping("/{id}")
    public Result<User> getById(@PathVariable Long id) {
        for (User user : userList) {
            if (user.getId().equals(id)) {
                return Result.success(user);
            }
        }
        return Result.error("用户不存在");
    }

    @PostMapping("/add")
    public Result<String> add(@RequestBody User user) {
        userList.add(user);
        return Result.success("添加成功");
    }
}