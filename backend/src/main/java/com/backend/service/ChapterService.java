package com.backend.service;

import com.backend.entity.Chapter;
import com.backend.entity.Course;

import java.util.List;

public interface ChapterService {
    List<Chapter> findChapterByCourseId(int id);

    Chapter findChapterByChapterId(int id);

    boolean insertChapter(Chapter chapter);

    boolean deleteChapter(int id);

    boolean updateChapter(Chapter chapter);
}
