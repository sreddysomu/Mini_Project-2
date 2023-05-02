package com.Reddy.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.Reddy.entity.CoursesEntity;

public interface CourseRepo extends JpaRepository<CoursesEntity, Integer> {

}
