package com.nearsen_enterprise.Repository;

import com.nearsen_enterprise.Model.UserModel;

import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface UserRepository extends CrudRepository<UserModel,String> {

    public List<UserModel>getUserModelByUserId(String userId);

    public UserModel getUserModelByUserNameAndUserPassword(String userName,String password);

}
