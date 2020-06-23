package com.xr.base.controller;

import com.xr.base.entity.SysEmp;
import com.xr.base.entity.SysRole;
import com.xr.base.service.SysEmpService;
import com.xr.base.service.SysRoleService;
import com.xr.base.util.ResponseResult;
import io.swagger.annotations.Api;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.crypto.SecureRandomNumberGenerator;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.sql.Time;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Api(tags = {"SysEmprController"}, description = "用户Controller")
@RestController
@RequestMapping("emp")
@CrossOrigin
public class SysEmpController {
    @Autowired
    private SysEmpService sysEmpService;

    @Autowired
    private SysRoleService sysRoleService;


    @RequestMapping("login")
    public ResponseResult login(@RequestBody SysEmp sysEmp) {
//        SysEmp loginUser = sysEmpService.Login(sysEmp);
//        ResponseResult result = new ResponseResult();
//        if (loginUser != null) {
//            // 返回给前台的toke，唯一标识用户
//            result.getData().put("token", loginUser.getUsername());
//        } else {
//            //登录失败
//            result.getData().put("message", "登录失败，用户名或密码错误");
//        }
//        return result;
        ResponseResult result = new ResponseResult();
        UsernamePasswordToken token = new UsernamePasswordToken(sysEmp.getUsername(), sysEmp.getPassword());
        Subject subject = SecurityUtils.getSubject();
        subject.login(token);
        result.getData().put("token",subject.getSession().getId());
        return result;
    }
    /**
     * 退出
     * @return
     */
    @RequestMapping("logout")
    public ResponseResult logout(){
        ResponseResult result = new ResponseResult();
        Subject subject = SecurityUtils.getSubject();
        // 只需调用shiro的logout方法就可以了
        subject.logout();
        return result;
    }
    @RequestMapping("info")
    public ResponseResult info(String token){
        // 根据用户名查询用户信息
//        SysEmp user = sysEmpService.findUserByUserName(token);
//        // 根据用户名查询角色信息
//        List<String> roles =  sysEmpService.findUserRoles(token);
//        ResponseResult result = new ResponseResult();
//        result.getData().put("roles",roles);
//        result.getData().put("introduction","简介");
//        result.getData().put("avatar","头像");
//        result.getData().put("name",user.getUsername());
//        return result;
        Subject subject = SecurityUtils.getSubject();
        Session session = subject.getSession();
        ResponseResult result = new ResponseResult();
        if(token.equals(subject.getSession().getId().toString())){
            // 从shiro的session里获得保存的用户信息
            SysEmp loginUser = (SysEmp) session.getAttribute("USER_SESSION");
            // 获得角色字符串集合
            List<String> roles = (List<String>) session.getAttribute("roles");
            if(loginUser!=null){
                // 根据用户获得角色字符串数组
                roles = sysEmpService.findUserRoles(loginUser.getUsername());
                result.getData().put("roles",roles);
                result.getData().put("introduction","介绍");
                result.getData().put("avatar","头像");
                result.getData().put("name",loginUser.getUsername());
                return result;
            }
        }
        return null;
    }

    @RequestMapping("list")
    @RequiresPermissions("emp:list")
    public ResponseResult list(String username, Integer page, Integer limit){
        System.out.println(username);
        System.out.println(page+""+limit);
        List<SysEmp> list = sysEmpService.list(username,(page-1)*limit, limit);
        int empsize = sysEmpService.empsize();
        ResponseResult result = new ResponseResult();
        result.getData().put("items",list);
        result.getData().put("total",empsize);
        return result;
    }
    @RequestMapping("findroleList")
    public ResponseResult findroleList(){
        ResponseResult result = new ResponseResult();
        result.getData().put("findroleList",sysEmpService.findUserRolesList());
        return result;
    }

    @RequestMapping("delete")
    @RequiresPermissions("emp:delete")
    public ResponseResult delete(Integer id){
        sysEmpService.dele(id);
        ResponseResult result = new ResponseResult();
        result.getData().put("message","删除成功");
        return result;
    }

    @RequestMapping("add")
    @RequiresPermissions("emp:add")
    public ResponseResult add(SysEmp sysEmp,Integer roleId,Integer deptId){
        Session session = SecurityUtils.getSubject().getSession();
        SysEmp sss = (SysEmp)session.getAttribute("USER_SESSION");
        //生成盐（部分，需要存入数据库中）
        String salt=new SecureRandomNumberGenerator().nextBytes().toHex();
        //将原始密码加盐（上面生成的盐），并且用md5算法加密两次，将最后结果存入数据库中
        String password = new Md5Hash(sysEmp.getPassword(),salt,2).toString();
        sysEmp.setSalt(salt);
        sysEmp.setPassword(password);
        sysEmp.setRoleId(roleId);
        sysEmp.setDeptId(deptId);
        sysEmp.setCreateId(sss.getId());
        sysEmp.setCreateName(sss.getUsername());
        sysEmpService.add(sysEmp);
        System.out.println("角色ID"+roleId +"部门id"+deptId);
        sysEmpService.addrole(sysEmp.getId(),roleId);
        sysRoleService.adddept(roleId,deptId);
        ResponseResult result = new ResponseResult();
        result.getData().put("message","添加成功");
        return result;
    }


    @RequestMapping("update")
    @RequiresPermissions("emp:update")
    public ResponseResult update(SysEmp sysEmp,Integer roleId,Integer deptId){
        sysEmpService.update(sysEmp);
        sysEmpService.updaterole(sysEmp.getId(),roleId);
        sysRoleService.updeptname(roleId,deptId);
        ResponseResult result = new ResponseResult();
        result.getData().put("message","修改成功");
        return result;
    }

}
