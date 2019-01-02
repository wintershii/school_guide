package com.winter.dao;

import com.winter.pojo.User;
import org.apache.ibatis.annotations.Param;

public interface UserMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);

    int selectValidAccount(@Param("username") String username, @Param("password") String password);

    User selectUserByUsername(String username);

    int selectNumByUsername(String username);

    int insertRegister(@Param("username") String username, @Param("password") String password);

}