package com.stubhub.customer.user.repository;

import com.stubhub.customer.user.entity.UserEntity;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<UserEntity, Long> {

    public UserEntity findByUsername(String username);

}
