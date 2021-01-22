package com.Digital_Orientation_System.controller;



import com.Digital_Orientation_System.log.MyLog;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.api.ApiController;
import com.baomidou.mybatisplus.extension.api.R;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.Digital_Orientation_System.entity.SysRoleTable;
import com.Digital_Orientation_System.service.SysRoleBackendApiTableService;
import com.Digital_Orientation_System.service.SysRoleFrontendMenuTableService;
import com.Digital_Orientation_System.service.SysRoleTableService;
import com.Digital_Orientation_System.service.SysRoleUserTableService;
import com.Digital_Orientation_System.vo.SysRoleAndPermissionVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.io.Serializable;
import java.util.List;

/**
 * (SysRoleTable)表控制层
 * 该类由EasyCode工具生成
 * @author 小明哥
 * @since 2020-03-07 14:31:50
 */
@RestController
@RequestMapping("sysRoleTable")
public class SysRoleTableController extends ApiController {
    /**
     * 角色
     */
    @Resource
    private SysRoleTableService sysRoleTableService;

    /**
     * 角色-用户
     */
    @Autowired
    SysRoleUserTableService sysRoleUserTableService;

    /**
     * 角色-前端菜单
     */
    @Autowired
    SysRoleFrontendMenuTableService sysRoleFrontendMenuTableService;

    /**
     * 角色-API
     */
    @Autowired
    SysRoleBackendApiTableService sysRoleBackendApiTableService;

    /**
     * 分页查询所有数据
     *
     * @param page 分页对象
     * @param sysRoleTable 查询实体
     * @return 所有数据
     */
    @MyLog(operation = "获取所有角色信息")
    @GetMapping
    public R selectAll(Page<SysRoleTable> page, SysRoleTable sysRoleTable) {

        return success(this.sysRoleTableService.page(page, new QueryWrapper<>(sysRoleTable)));
    }

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @MyLog(operation = "通过id获取角色信息")
    @GetMapping("/{id}")
    public R selectOne(@PathVariable Serializable id) {
        return success(this.sysRoleTableService.getById(id));
    }

    /**
     * 新增数据
     *
     * @param sysRoleTable 实体对象
     * @return 新增结果
     */
    @MyLog(operation = "新增角色")
    @PostMapping
    public R insert(@RequestBody SysRoleTable sysRoleTable) {
        this.sysRoleTableService.save(sysRoleTable);
        String roleId = sysRoleTable.getRoleId();
        sysRoleBackendApiTableService.saveRoleApi(roleId);
        return success("添加成功！");
    }

    /**
     * 修改数据
     *
     * @param sysRoleTable 实体对象
     * @return 修改结果
     */
    @MyLog(operation = "修改角色信息")
    @PutMapping
    public R update(@RequestBody SysRoleTable sysRoleTable) {
        return success(this.sysRoleTableService.updateById(sysRoleTable));
    }

    /**
     * 删除数据
     *
     * @param idList 主键结合
     * @return 删除结果
     */
    @MyLog(operation = "删除角色")
    @DeleteMapping
    public R delete(@RequestParam("idList") List<String> idList) {
        return success(this.sysRoleTableService.removeByIds(idList));

    }

    //-----------------------------------------------------------------------
    /**
     * 对应前端：系统管理->用户角色设置->右边用户查询
     * @param roleId
     * @return
     */
    @MyLog(operation = "通过角色id获取具有该角色的用户")
    @GetMapping("/roleUser/{roleId}")
    public R getRoleAndUserList(@PathVariable("roleId") String roleId) {
        return success(sysRoleTableService.getRoleAndUserList(roleId));
    }
    @MyLog(operation = "给用户分配角色")
    @PostMapping("/roleUser/set")
    public R saveRoleUser(@RequestParam("roleId") String roleId,
                          @RequestBody SysRoleAndPermissionVo[] sysRoleAndPermissionVos){

        System.out.println("--------------现在进入方法saveRoleUser--------------");
        return success(sysRoleUserTableService.saveRoleUser(roleId, sysRoleAndPermissionVos));
    }

    //-----------------------------------------------------------------------
    /**
     * 对应前端：系统管理->菜单角色设置->右边菜单查询
     * @param roleId
     * @return
     */
    @MyLog(operation = "通过角色id获取角色具有的权限")
    @GetMapping("/roleMenu/{roleId}")
    public R getRoleAndMenuList(@PathVariable("roleId") String roleId) {
        return success(sysRoleTableService.getRoleAndMenuList(roleId));
    }

    @MyLog(operation = "给角色分配权限")
    @PostMapping("/roleMenu/set")
    public R saveRoleMenu(@RequestParam("roleId") String roleId,
                                @RequestBody SysRoleAndPermissionVo[] sysRoleAndPermissionVos){

        System.out.println("--------------现在进入方法saveRoleMenu--------------");
        return success(sysRoleFrontendMenuTableService.saveRoleMenu(roleId,sysRoleAndPermissionVos));
    }

    //-----------------------------------------------------------------------
    /**
     * 对应前端：系统管理->菜单角色设置->右边api查询
     * @param roleId
     * @return
     */
    @MyLog(operation = "通过角色id获取角色的接口")
    @GetMapping("/roleApi/{roleId}")
    public R getRoleAndApiList(@PathVariable("roleId") String roleId) {
        return success(sysRoleTableService.getRoleAndApiList(roleId));
    }

    @MyLog(operation = "给角色分配接口")
    @PostMapping("/roleApi/set")
    public R saveRoleApi(@RequestParam("roleId") String roleId,
                          @RequestBody SysRoleAndPermissionVo[] sysRoleAndPermissionVos){

        System.out.println("--------------现在进入方法saveRoleApi--------------");
        return success(sysRoleBackendApiTableService.saveRoleAip(roleId,sysRoleAndPermissionVos));
    }
    @MyLog(operation = "获取所有角色信息")
    @GetMapping("/getall")//获取所有角色信息
    public R getRoleAll(){
        return success(sysRoleTableService.getRoleAll());
    }


//    @PostMapping("/deleterole")//删除角色
//    public R deleteRole(@RequestParam List<String> idList){
//        return success(sysRoleTableService.removeByIds(idList));
//    }

    @MyLog(operation = "通过角色Id获取角色管理的学院专业班级")
    @PostMapping("/getcollegemc")
    public R getCollegeMC(@RequestParam String roleId){
        SysRoleTable roleTable = sysRoleTableService.getById(roleId);
        if(roleId!=null){
            return R.ok(sysRoleTableService.getCollegeMC(roleId));
        }else{
            return R.failed("角色不存在！");
        }
    }

    @MyLog(operation = "通过角色Id修改角色管理的学院专业班级")
    @PostMapping("/updatecollegemc")
    public R updateCollegeMC(@RequestBody SysRoleTable roleTable){
        SysRoleTable roleTable1 = sysRoleTableService.getById(roleTable.getRoleId());
        if(roleTable1!=null){
            sysRoleTableService.updateCollegeMC(roleTable);
            return R.ok("修改成功！");
        }else{
            return R.failed("角色不存在！");
        }
    }

}