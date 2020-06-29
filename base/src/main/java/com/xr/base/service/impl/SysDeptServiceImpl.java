package com.xr.base.service.impl;

import com.xr.base.entity.SysDept;
import com.xr.base.mapper.SysDeptMapper;
import com.xr.base.service.SysDeptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class SysDeptServiceImpl implements SysDeptService {
    @Autowired
    private SysDeptMapper sysDeptMapper;



    @Override
    public List<SysDept> groupDept(Integer id ,String name , Integer page , Integer limit) {
        List<SysDept> sysDepts = sysDeptMapper.groupDept(id,name, page, limit);
        sysDepts.forEach(System.out::print);
        return sysDepts;
    }

    @Override
    public int deptsize() {
        return sysDeptMapper.deptsize();
    }

    @Override
    public void add(SysDept sysDept) {
        sysDeptMapper.add(sysDept);
    }

    @Override
    public void update(SysDept sysDept) {
        sysDeptMapper.update(sysDept);
    }

    @Override
    public SysDept findOne(Integer id) {
        return sysDeptMapper.findOne(id);
    }

    @Override
    public void dele(int id) {
        sysDeptMapper.dele(id);
    }



    @Override
    public List<SysDept> leadership() {
        List<SysDept> leadership = sysDeptMapper.leadership();
        System.out.print("a");
        leadership.forEach(System.out::print);
        return leadership;
    }



    //查询部门，递归

    @Override
    public List<SysDept> DeptList() {
        //查询所有1级部门
        List<SysDept> sysDeptParentAll = sysDeptMapper.groupByFidList(0);
        findSysDeptAllChrlen(sysDeptParentAll);


        sysDeptParentAll.forEach(System.out::print);
        return sysDeptParentAll;
    }

    @Override
    public List<SysDept> groupByFidList(int fid) {
        return sysDeptMapper.groupByFidList(fid);
    }
    //查询子部门
    private  void findSysDeptAllChrlen( List<SysDept> sysDeptList){

        for (SysDept sysDept : sysDeptList){
            List<SysDept> sysDepts = sysDeptMapper.groupByFidList(sysDept.getId());
            findSysDeptAllChrlen(sysDepts);
            sysDept.setDeptCharlen(sysDepts);
        }
    }
}
