package com.backend.service;

import com.backend.entity.Teach;

import java.util.List;

public interface TeachService {
    List<Teach> findTeachByTeacherId(int id);

    List<Teach> findTeachByCourseId(int id);

    List<Teach> findAllTeach();

    boolean insertTeach(Teach teach);

    boolean deleteteachByTidAndCid(int tid,int cid);

    boolean deleteteachByCid(int cid);

    boolean updateTeach(Teach teach);
}
