package com.Digital_Orientation_System.service;

import com.Digital_Orientation_System.entity.SysRoleTable;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@SpringBootTest
class SysRoleTableServiceTest {

    @Autowired
    SysRoleTableService service;

    @Test
    void  add() {
        Set<SysRoleTable> sets = new HashSet<>();
        sets.add(new SysRoleTable(null,"ADMIN","老板"));
        sets.add(new SysRoleTable(null,"SHOP_MANAGER","店长"));
        sets.add(new SysRoleTable(null,"SHOP_BUSINESS","运营"));
        service.saveBatch(sets) ;

    }

    @Test
    void select() {
       List<String> roles =  service.getRolesByUserName("ming206");
       //输出
       roles.forEach(System.out::println);
    }
}