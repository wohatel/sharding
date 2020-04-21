package com.github.wohatel.sharding.mapper;

import com.github.wohatel.sharding.entity.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper
@Component
public interface UserMapper {

    @Insert("insert into ts_user (name,user_id) values (#{name},#{userId})")
    void insert(@Param("name") String name, @Param("userId") Long userId);

    @Select("<script>" +
            "select * from ts_user where id in" +
            "<foreach collection='list' item='id' open='(' separator=',' close=')'> " +
            "#{id} " +
            "</foreach> " +
            "</script>")
    List<User> findUsers(@Param("list") List<Long> ids);
}
