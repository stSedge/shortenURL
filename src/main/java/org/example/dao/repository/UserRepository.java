package org.example.dao.repository;

import java.sql.SQLException;

import jakarta.transaction.Transactional;
import org.example.dao.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.Modifying;


@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {

    @Query(nativeQuery = true,
            value = "SELECT ID FROM USERS WHERE LOGIN =:login AND PASSWORD=:password")
    long findUser(@Param("login") String login, @Param("password") String password) throws SQLException;

   @Query(nativeQuery = true,
            value = "SELECT ID FROM USERS WHERE LOGIN =:login")
   long findUser(@Param("login") String login) throws SQLException;

   @Modifying
   @Query(nativeQuery = true,
           value = "INSERT INTO USERS (LOGIN, PASSWORD) VALUES (:login,:password)")
   @Transactional
   void saveUser(@Param("login") String login, @Param("password") String password) throws SQLException;
}
