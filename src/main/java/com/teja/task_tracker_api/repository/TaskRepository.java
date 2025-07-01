package com.teja.task_tracker_api.repository;

import com.teja.task_tracker_api.model.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TaskRepository extends JpaRepository<Task, Integer> {
    // No need to write code! All basic CRUD comes from JpaRepository
}