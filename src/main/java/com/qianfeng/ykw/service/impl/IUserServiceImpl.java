package com.qianfeng.ykw.service.impl;

import com.qianfeng.ykw.dao.IUserDAO;
import com.qianfeng.ykw.pojo.User;
import com.qianfeng.ykw.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author 闫坤炜
 * @version 1.0
 */
@Service
public class IUserServiceImpl implements IUserService {
    
    @Autowired
    IUserDAO userDAO;
    
    @Override
    public boolean addUser(User user) {
        return userDAO.addUser(user) > 0;
    }
}
