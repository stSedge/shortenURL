package org.example.dao.repository;

import java.sql.SQLException;

import jakarta.transaction.Transactional;
import org.example.dao.entity.HashEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HashRepository extends JpaRepository<HashEntity, Long> {

    @Modifying
    @Query(nativeQuery = true,
            value = "INSERT INTO URLS (LONGURL, SHORTURL) VALUES (:longURL,:shortURL)")
    @Transactional
    void save(@Param("longURL") String longURL, @Param("shortURL") String shortURL) throws SQLException;
    // "INSERT INTO QUERIES (USERID, URLID) VALUES (?,?)";

    @Query(nativeQuery = true,
            value = "SELECT SHORTURL FROM URLS WHERE LONGURL = :longURL")
    String findHashByLongURL(@Param("longURL") String longURL) throws SQLException;

    @Query(nativeQuery = true,
            value = "SELECT ID FROM URLS WHERE LONGURL = :longURL")
    long findURLID(@Param("longURL") String longURL) throws SQLException;


    @Query(nativeQuery = true,
            value = "SELECT LONGURL FROM URLS WHERE SHORTURL = :shortURL")
    String findHashByShortURL(@Param("shortURL") String shortURL) throws SQLException;

}
