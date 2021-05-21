package com.szoftverfejlesztes.kanban;

import com.szoftverfejlesztes.kanban.entity.Task;
import com.szoftverfejlesztes.kanban.repository.TaskRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
class KanbanApplicationTests {

    @Autowired
    private TaskRepository repo;

    @Test
    void contextLoads() {
    }

    @Test
    public void testCreate(){

        Task task = new Task();
        task.setLanguage("teszt");
        task.setProject("tesztpro");
        task.setDeveloper("teszt.dev");

        repo.save(task);


    }

    @Test
    public void testRead(){

        Optional<Task> reTask = repo.findById(2l);
        Task task = reTask.isPresent() ? reTask.get() : null;
        assertNotNull(task);
        assertEquals("agile4", task.getProject());
        System.out.println(task);

    }
    @Test
    public void testUpdate(){
        Optional<Task> reTask = repo.findById(2l);
        Task task = reTask.isPresent() ? reTask.get() : null;
        task.setDeveloper("re-create");
        repo.save(task);
    }

    @Test
    public void testDelete(){

        Optional<Task> reTask = repo.findById(9l);
        Task task = reTask.isPresent() ? reTask.get() : null;
        repo.delete(task);


    }

}
