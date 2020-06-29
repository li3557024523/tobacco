package com.xr.base.service.impl;

import com.xr.base.entity.TEarlyWarning;
import com.xr.base.mapper.TEarlyWarningMapper;
import com.xr.base.service.TEarlyWarningService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TEarylWarningServiceImpl implements TEarlyWarningService {

    @Autowired
    TEarlyWarningMapper tEarlyWarningMapper;

    @Override
    public List<TEarlyWarning> list() {
        return tEarlyWarningMapper.list();
    }
}
