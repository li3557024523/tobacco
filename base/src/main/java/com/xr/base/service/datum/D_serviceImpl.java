package com.xr.base.service.datum;

import com.xr.base.entity.Datum;
import com.xr.base.mapper.D_mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class D_serviceImpl implements D_service {
    @Autowired
    D_mapper d_mapper;

    @Override
    public List<Datum> D_list(Integer page,Integer limit) {
        return d_mapper.D_list(page,limit);
    }
}
