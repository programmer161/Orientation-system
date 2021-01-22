package com.Digital_Orientation_System.service.impl;

import com.Digital_Orientation_System.dao.SysFrontendMenuTableDao;
import com.Digital_Orientation_System.vo.SysFrontendVo;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.Digital_Orientation_System.entity.SysFrontendMenuTable;
import com.Digital_Orientation_System.service.SysFrontendMenuTableService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * (SysFrontendMenuTable)表服务实现类
 * 该类由EasyCode工具生成
 * @author 小明哥
 * @since 2020-03-07 13:42:36
 */
@Service("sysFrontendMenuTableService")
public class SysFrontendMenuTableServiceImpl extends ServiceImpl<SysFrontendMenuTableDao, SysFrontendMenuTable> implements SysFrontendMenuTableService {

    /**
     * 查所有的前端菜单（编辑使用）
     */
    @Override
   public List<SysFrontendVo> getAllMenuList(){
       return this.baseMapper.getAllMenuList();
    }

    /**
     * 根据登录账号，获得前端展现的菜单
     * 控制前端菜单的权限
     * @param username
     * @return
     */
    @Override
    public List<SysFrontendMenuTable> getMenusByUserName(String username){
        return this.baseMapper.getMenusByUserName(username);
    }
}