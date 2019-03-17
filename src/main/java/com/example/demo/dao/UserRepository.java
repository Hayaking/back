package com.example.demo.dao;

import com.example.demo.pojo.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("userRepository")
public interface UserRepository extends JpaRepository<User, String> {
    @Query("from User users")
    List<User> findAll();
    User queryUserByName(String name);
    User queryUserByNameAndPsw(String name, String psw);
    @Query(nativeQuery = true,value ="SELECT users.name,users.psw,users.role FROM user_group JOIN users ON users.NAME = user_group.user_name  WHERE user_group.group_id = ?")
    List<User> findAllByGroupId(int id);
}
