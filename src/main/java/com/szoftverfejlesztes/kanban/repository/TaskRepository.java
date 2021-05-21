package com.szoftverfejlesztes.kanban.repository;

import com.szoftverfejlesztes.kanban.entity.Task;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TaskRepository extends JpaRepository<Task, Long> {

}
