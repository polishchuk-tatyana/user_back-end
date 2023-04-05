package com.user.userapp.repository;

import com.user.userapp.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long>{
    @Query("select u.userName from User u where u.userName= :userName")
    public Optional<User> findByUserName(String userName);

}
