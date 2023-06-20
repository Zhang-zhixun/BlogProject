package com.backend.service.impl;

import com.backend.entity.Teach;
import com.backend.mapper.TeachMapper;
import com.backend.service.TeachService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class TeachServiceImpl implements TeachService {

    @Autowired
    TeachMapper teachMapper;

    @Override
    public List<Teach> findTeachByTeacherId(int id) {
        return teachMapper.findTeachByTeacherId(id);
    }

    @Override
    public List<Teach> findTeachByCourseId(int id) {
        return teachMapper.findTeachByCourseId(id);
    }

    @Override
    public List<Teach> findAllTeach() {
        return teachMapper.findAllTeach();
    }

    @Override
    public boolean insertTeach(Teach teach) {
        return teachMapper.insertTeach(teach);
    }

    @Override
    public boolean deleteteach(int tid, int cid) {
        return teachMapper.deleteteach(tid,cid);
    }

    @Override
    public boolean updateTeach(Teach teach) {
        return teachMapper.updateTeach(teach);
    }
}
