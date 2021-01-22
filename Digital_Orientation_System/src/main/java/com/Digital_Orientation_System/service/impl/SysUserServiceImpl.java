package com.Digital_Orientation_System.service.impl;

import com.Digital_Orientation_System.dao.SysUserMapper;
import com.Digital_Orientation_System.log.MyLog;
import com.Digital_Orientation_System.vo.SysUserVo;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.Digital_Orientation_System.components.BCryptPasswordEncoderUtil;
import com.Digital_Orientation_System.entity.SysUserEntity;

import com.Digital_Orientation_System.entity.Vo.AddStudentVo;
import com.Digital_Orientation_System.entity.Vo.StudentVo;
import com.Digital_Orientation_System.service.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * 用户服务
 */
@Service
public class SysUserServiceImpl extends ServiceImpl<SysUserMapper, SysUserEntity> implements SysUserService {

    @Autowired
    SysUserMapper sysUserMapper;
    @Autowired
    BCryptPasswordEncoderUtil bCryptPasswordEncoderUtil;

    //用户激活状态
    private static final int USER_STATE = 1;

    /**
     * 通过账号查询用户
     * @param username
     * @return
     */
    @Override
    public SysUserEntity getUserByUserName(String username) {
        LambdaQueryWrapper<SysUserEntity> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        //查询条件：全匹配账号名，和状态为1的账号
        lambdaQueryWrapper
                .eq(SysUserEntity::getUserName,username)
                .eq(SysUserEntity::getState,USER_STATE);

        //用getOne查询一个对象出来
        SysUserEntity user = this.getOne(lambdaQueryWrapper);

        return  user;
    }

    /**
     * 个性化验证登录
     * @param username 账号
     * @param rawPassword 原始密码
     * @return
     */
    @MyLog(operation = "登录")
    @Override
    public boolean checkLogin(String username,String rawPassword) throws Exception {
        SysUserEntity userEntity = this.getUserByUserName(username);
        System.out.println("userEntity = " + userEntity);
        if (userEntity == null) {
            //System.out.println("checkLogin--------->账号不存在，请重新尝试！");
            //设置友好提示
            throw  new Exception("账号不存在，请重新尝试！");
        }else {
            //加密的密码
            String encodedPassword = userEntity.getPassWord();
            //和加密后的密码进行比配
            if(!bCryptPasswordEncoderUtil.matches(rawPassword,encodedPassword)) {
                //System.out.println("checkLogin--------->密码不正确！");
                //设置友好提示
                throw new Exception("密码不正确！");
            }else{
                return  true;
            }
        }
    }

    /**
     * 注册
     * @param sysUserVo
     * @return
     * @throws Exception
     */
    @Override
    public boolean register(SysUserVo sysUserVo) throws Exception {
        if(sysUserVo !=null) {
            SysUserEntity f = this.getUserByUserName(sysUserVo.getName());
            if(f != null) {
                throw new Exception("这个用户已经存在，不能重复。");
            }
            //保存到数据库
            return  this.save(this.voToPo(sysUserVo));
        }else{
            throw new Exception("错误消息：用户对象为空！");
        }
    }

    /**
     * VO到PO的转换
     * @param vo
     * @return
     */
    public SysUserEntity voToPo(SysUserVo vo) {
        SysUserEntity po = new SysUserEntity();
        if(vo !=null) {
            po.setUserId(vo.getId());
            po.setUserName(vo.getName());
            po.setDescription(vo.getDes());
            //对密码进行加密
            po.setPassWord(bCryptPasswordEncoderUtil.encode(vo.getPassw()));
            //设置状态为1
            po.setState(USER_STATE);
        }
        return po;
    }

    @Override
    public String findSnoById(String idnumber) {
        return sysUserMapper.findSnoById(idnumber);
    }

    @Override
    public SysUserEntity findUserByIdnumber(String idnumber) {
        return sysUserMapper.findUserByIdnumber(idnumber);
    }

    @Override
    public SysUserEntity checkOldPwd(String id, String oldpwd) {
        return sysUserMapper.checkOldPwd(id,oldpwd);
    }



    @Override
    public StudentVo getSumAndFinished(String username) {
        StudentVo studentVo = new StudentVo();
        studentVo.setSum(sysUserMapper.getSum(username));
        studentVo.setFinishedcount(sysUserMapper.getFinishedcount(username));
        return studentVo;
    }

    @Override
    public List<SysUserEntity> getReportNow(String username) {
        return sysUserMapper.getReportNow(username);
    }

    @Override
    public StudentVo getReportStatus(String username) {
        StudentVo studentVo = new StudentVo();
        studentVo.setSum(sysUserMapper.getSum(username));
        studentVo.setFinishedcount(sysUserMapper.getFinishedcount(username));
        studentVo.setAffaircount(sysUserMapper.getAffaircount(username));
        studentVo.setInfocount(sysUserMapper.getInfocount(username));
        studentVo.setReportinfocount(sysUserMapper.getReportinfocount(username));
        studentVo.setReportnowcount(sysUserMapper.getReportnowcount(username));
        studentVo.setPaycount(sysUserMapper.getPaycount(username));
        return studentVo;
    }

    @Override
    public SysUserEntity getReportNowByUserName(String username) {
        return sysUserMapper.getReportNowByUserName(username);
    }

    @Override
    public List<SysUserEntity> getPay(String username) {
        return sysUserMapper.getPay(username);
    }

    @Override
    public SysUserEntity getPayByUserName(String username) {
        return sysUserMapper.getPayByUserName(username);
    }

    @Override
    public SysUserEntity findStudentById(String id) {
        return sysUserMapper.findStudentById(id);
    }

    @Override
    public List<SysUserEntity> findStudentAll(String username) {
        return sysUserMapper.findStudentAll(username);
    }

    @Override
    public List<SysUserEntity> getFinished(String username) {
        return sysUserMapper.getFinished(username);
    }

    @Override
    public List<SysUserEntity> getUnFinished(String username) {
        return sysUserMapper.getUnFinished(username);
    }

    @Override
    public void payConfirm(String username) {
        sysUserMapper.PayConfirm(username);
    }

    @Override
    public void reportConfirm(String username) {
        sysUserMapper.ReportConfirm(username);
    }

    @Override
    public List<StudentVo> getAgecount() {
        StudentVo studentVo = new StudentVo();

        List<StudentVo> list = new ArrayList<>();
        list = sysUserMapper.getAgecount();
        return sysUserMapper.getAgecount();
    }
    //---------------------------------------------------------------------------------------Sys_Manager

    @Override
    public int getManagerSum() {

        return sysUserMapper.getManagerSum();
    }

    @Override
    public List<SysUserEntity> getManagerAll() {
        return sysUserMapper.getManagerAll();
    }



    @Override
    public List<SysUserEntity> findByCollege(String college) {
        return sysUserMapper.findByCollege(college);
    }

    @Override
    public List<SysUserEntity> findByMajor(String major) {
        return sysUserMapper.findByMajor(major);
    }

    @Override
    public SysUserEntity findManagerByUserName(String username) {
        return sysUserMapper.findManagerByUserName(username);
    }

    @Override
    public void updateManager(SysUserEntity sysUserEntity) {
        sysUserMapper.updateManager(sysUserEntity);
    }

    @Override
    public void updateStudent(SysUserEntity sysUserEntity) {
        sysUserMapper.updateStudent(sysUserEntity);

    }

    @Override
    public List<SysUserEntity> findManagerByCollege(String college) {
        return sysUserMapper.findManagerByCollege(college);
    }

//    @Override
//    public SysUserEntity getByCollege(String college) {
//        return sysUserMapper.getByCollege(college);
//    }

//    @Override
//    public R addStudent(AddStudentVo studentVo) {
//        if(studentVo!=null){
//            SysUserEntity userEntity = this.getUserByUserName(studentVo.getName());
//            if(userEntity!=null){
//                return R.failed("用户已存在！");
//            }
//            SysUserEntity user = new SysUserEntity();
//            user.setUserId(studentVo.getId());
//            user.setUserName(studentVo.getName());
//            user.setDescription(studentVo.getDes());
//            //加密
//            user.setPassWord(bCryptPasswordEncoderUtil.encode(studentVo.getPsw()));
//            this.save(user);
//            return R.ok("添加成功！");
//        }
//        else {
//        return R.failed("添加失败！");
//        }
//    }


//    @Override
//    public void addStudent(AddStudentVo studentVo) {
//        SysUserEntity user = new SysUserEntity();
//        user.setUserId(studentVo.getId());
//        user.setUserName(studentVo.getName());
//        user.setDescription(studentVo.getDes());
//        //加密
//        user.setPassWord(bCryptPasswordEncoderUtil.encode(studentVo.getPsw()));
//        user.setState(USER_STATE);
//    }


    @Override
    public void addStudent(AddStudentVo studentVo) {
        SysUserEntity student = new SysUserEntity();
        student.setUserName(studentVo.getUsername());
        student.setName(studentVo.getName());
        student.setSex(studentVo.getSex());
        student.setNation(studentVo.getNation());
        student.setCollege(studentVo.getCollege());
        student.setMajor(studentVo.getMajor());
        student.setGrade(studentVo.getGrade());
        student.setClasses(studentVo.getClasses());
        student.setDrom(studentVo.getDrom());
        student.setXuezhi(studentVo.getXuezhi());
        student.setStudytype(studentVo.getStudytype());
        student.setPolitics(studentVo.getPolitics());
        student.setState(studentVo.getState());
        student.setInstructname(studentVo.getInstructname());
        student.setInstructphone(studentVo.getInstructphone());
        student.setPassWord(bCryptPasswordEncoderUtil.encode(studentVo.getPassword()));
        student.setDescription(studentVo.getDescription());
        student.setBirth(studentVo.getBirth());
        student.setStudenttype(studentVo.getStudenttype());
        student.setStudystate(studentVo.getStudystate());
        save(student);

    }

    @Override
    public SysUserEntity getUserByUsername(String username) {
        return sysUserMapper.getUserByUsername(username);
    }

    @Override
    public void deleteUser(String list) {
    }
    @Override
    public int updatePwd(String username, String password, String firstPassword, String secondPassword) {
        SysUserEntity userEntity = this.getUserByUserName(username);
        String str1 = firstPassword;
        String str2 = secondPassword;
        if(userEntity != null){
            String encodePassword = userEntity.getPassWord();
            if(!bCryptPasswordEncoderUtil.matches(password, encodePassword)){
                return 1;//密码不正确
            }else{
                if(!str1.equals(str2)){
                    return 2;//两次输入密码不一致
                }else{
                    sysUserMapper.updatePwd(username,bCryptPasswordEncoderUtil.encode(secondPassword));
                    return 3;//修改成功
                }
            }
        }else{
            return 4;//用户名不存在
        }

    }

    @Override
    public List<SysUserEntity> findStudentByManager(String major) {
        return sysUserMapper.findStudentByManager(major);
    }

    @Override
    public List<SysUserEntity> getInstructByCollege(String college) {
        return sysUserMapper.getInstructByCollege(college);
    }

    @Override
    public StudentVo getSexNumber(String username) {
        StudentVo studentVo = new StudentVo();
        studentVo.setMc(sysUserMapper.getMnumber(username));
        studentVo.setFMc(sysUserMapper.getFMnumber(username));
        return studentVo;
    }

    @Override
    public SysUserEntity findStudentByUserName(String username) {
        return sysUserMapper.findStudentByUserName(username);
    }

    @Override
    public List<SysUserEntity> getStudentByClasses(String major,String classes) {
        return sysUserMapper.getStudentByClasses(major, classes);
    }

    @Override
    public void insertUser(SysUserEntity user) {
        sysUserMapper.insert(user);
    }

    @Override
    public List<SysUserEntity> getFinishedSys() {
        return sysUserMapper.getFinishedSys();
    }

    @Override
    public List<SysUserEntity> getUnFinishedSys() {
        return sysUserMapper.getUnFinishedSys();
    }

    @Override
    public List<SysUserEntity> getStudentAllSys() {
        return sysUserMapper.getStudentAllSys();
    }

    @Override
    public int getFinishedCountSys() {
        return sysUserMapper.getFinishedCountSys();
    }

    @Override
    public int getSumSys() {
        return sysUserMapper.getSumSys();
    }
}
