package com.raven.pattern.template.service;

import com.raven.pattern.template.dao.UserDao;
import com.raven.pattern.template.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @PackageName: com.raven.pattern.template.service
 * @ClassName: UserService
 * @Blame: raven
 * @Date: 2021-08-03 21:46
 * @Description:
 */
@Service
public class UserService {

    @Autowired
    private UserDao userDao;

    public List<User> selectAll(){
        List<?> list = userDao.selectAll();
        return (List<User>) list;
    }
}
