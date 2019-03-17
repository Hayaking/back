package com.example.demo.dao;

import com.example.demo.pojo.Group;
import com.example.demo.pojo.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("groupRepository")
public interface GroupRepository extends JpaRepository<Group, String> {
    @Query(nativeQuery = true,value = "SELECT _group._id,_group.`name`,_group.count_p FROM user_group join _group ON user_group.group_id = _group._id where user_group.user_name = ?")
    List<Group>  findAllByUserName(String name);

}
