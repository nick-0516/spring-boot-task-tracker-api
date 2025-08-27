package com.teja.task_tracker_api.repository;

import com.teja.task_tracker_api.model.Task;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.domain.Page;

@Repository
public interface TaskRepository extends JpaRepository<Task, Integer> {
    //All basic CRUD comes from JpaRepository

    //for filtering
    Page<Task> findByCompleted(boolean completed, Pageable pageable);
    Page<Task> findByProjectId(Long projectId, Pageable pageable);
    Page<Task> findByCompletedAndProjectId(boolean completed, Long projectId, Pageable pageable);
}