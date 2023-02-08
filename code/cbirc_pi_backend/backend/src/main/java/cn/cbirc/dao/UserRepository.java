package cn.cbirc.dao;

import cn.cbirc.model.po.UserPO;
import org.springframework.data.repository.CrudRepository;

//@Repository
//@Mapper
//public interface UserRepository {
//
//    @Select("select * from _user where name=#{name}")
//    UserPO getUserByName(@Param("name") String name);
//
//    @Select("select count(id) from _user")
//    int count();
//
//    @Select("select id from _user where name=#{name}")
//    Integer getUserIdByName(@Param("name") String name);
//
//    @Select("select password from _user where name=#{name}")
//    String getPasswordByName(@Param("name") String name);
//
//    @Insert("insert into _user(name,password) values (#{name},#{password})")
//    void addUser(@Param("name")String name,@Param("password")String password);
//}


public interface UserRepository extends CrudRepository<UserPO,Integer> {
    UserPO getByName(String name);

    long count();

    UserPO save(UserPO userPO);

    UserPO getById(int id);
}