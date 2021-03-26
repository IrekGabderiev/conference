package com.irek.conference.repository;

import com.irek.conference.entity.User;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface UserRepository extends CrudRepository<User, Integer> {
    User findByLogin(String login);
    @Modifying
    @Query("update User u set u.role = :role where u.id= :id")
    void changeRole(@Param("role") String role, @Param("id") Integer id);
}
