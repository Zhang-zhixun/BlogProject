package com.backend.service.impl;

import com.backend.entity.Chapter;
import com.backend.mapper.ChapterMapper;
import com.backend.service.ChapterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ChapterServiceImpl implements ChapterService {

    @Autowired
    ChapterMapper chapterMapper;

    @Override
    public List<Chapter> findChapterByCourseId(int id) {
        return chapterMapper.findChapterByCourseId(id);
    }

    @Override
    public Chapter findChapterByChapterId(int id) {
        return chapterMapper.findChapterByChapterId(id);
    }

    @Override
    public boolean insertChapter(Chapter chapter) {
        return chapterMapper.insertChapter(chapter);
    }

    @Override
    public boolean deleteChapter(int id) {
        return chapterMapper.deleteChapter(id);
    }

    @Override
    public boolean updateChapter(Chapter chapter) {
        return chapterMapper.updateChapter(chapter);
    }
}
