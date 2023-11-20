package com.example.Bibz.User;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("userService")
@Transactional
public class UserServiceImpl implements iuserService {

    @Autowired
    private User_Dao userDao;
    @Override
    public user saveUser(user user) {
        return userDao.save(user);
    }

    @Override
    public user updateUser(user user) {
        return userDao.save(user);
    }

    @Override
    public void deleteUser(Integer id) {
        userDao.deleteById(id);
    }

    @Override
    public user findUserByUsername(String username) {
        return userDao.findByUsername(username);
    }

    @Override
    public user findUserByNames(String names) {
        return userDao.findByNames(names);
    }

    @Override
    public boolean checkIfIdexists(Integer id) {
        return false;
    }

    @Override
    public List<user> getUserByAge(int age) {
        return (List<user>) userDao.findByAge(age);
    }
}
