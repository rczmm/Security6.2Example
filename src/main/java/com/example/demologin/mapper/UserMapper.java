package com.example.demologin.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.demologin.domain.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper extends BaseMapper<User> {
}
