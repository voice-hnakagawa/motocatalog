package ooo.klae.sample.motocatalog.mappers;

import org.apache.ibatis.annotations.Mapper;

import ooo.klae.sample.motocatalog.beans.User;

@Mapper
public interface UserMapper {
    public User selectByUsername(String username);
}
