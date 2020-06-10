package com.stubhub.customer.login.repository;

import com.stubhub.customer.login.entity.UserEntity;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<UserEntity, Long> {

    public UserEntity findByUsername(String username);

}
