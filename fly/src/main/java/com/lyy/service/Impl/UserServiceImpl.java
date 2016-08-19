package com.lyy.service.Impl;

import javax.annotation.Resource;  

import org.springframework.stereotype.Service;  
  
import com.lyy.dao.UserMapper;  
import com.lyy.pojo.User;  
import com.lyy.service.IUserService;  
  
@Service("userService")  
public class UserServiceImpl implements IUserService {  
    @Resource  
    private UserMapper userDao;  
    public User getUserById(int userId) {  
        // TODO Auto-generated method stub  
        return this.userDao.selectByPrimaryKey(userId);  
    }  
  
}  