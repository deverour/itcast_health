package com.itheima.health.dao;

import com.itheima.health.pojo.Member;
import org.apache.ibatis.annotations.Param;

public interface MemberDao {
    Member findByTelephone(@Param("telephone") String telephone);

    void add(Member member);
}
