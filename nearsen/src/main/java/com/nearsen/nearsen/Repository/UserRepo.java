package com.nearsen.nearsen.Repository;

import com.nearsen.nearsen.Model.UserModel;

import org.springframework.data.couchbase.core.query.Query;
import org.springframework.data.couchbase.core.query.View;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface UserRepo extends CrudRepository<UserModel,String> {

    public List<UserModel>getUserModelByUserId(String userId);

    public UserModel getUserModelByUserNameAndUserPassword(String userName,String password);

}
