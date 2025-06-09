package exercise.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import exercise.model.Task;
import exercise.repository.TaskRepository;
import net.datafaker.Faker;
import org.instancio.Instancio;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static net.javacrumbs.jsonunit.assertj.JsonAssertions.assertThatJson;
import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

// BEGIN
@SpringBootTest
@AutoConfigureMockMvc
// END
class TaskControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private Faker faker;

    @Autowired
    private ObjectMapper om;

    @Autowired
    private TaskRepository taskRepository;


    @Test
    public void testWelcomePage() throws Exception {
        var result = mockMvc.perform(get("/"))
                .andExpect(status().isOk())
                .andReturn();

        var body = result.getResponse().getContentAsString();
        assertThat(body).contains("Welcome to Spring!");
    }

    @Test
    public void testIndex() throws Exception {
        var result = mockMvc.perform(get("/tasks"))
                .andExpect(status().isOk())
                .andReturn();

        var body = result.getResponse().getContentAsString();
        assertThatJson(body).isArray();
    }


    // BEGIN

    @Test
    public void testShow() throws Exception {
        var task = Instancio.create(Task.class);
        task = taskRepository.save(task);

        var result = mockMvc.perform(get("/tasks/" + task.getId()))
                .andExpect(status().isOk())
                .andReturn();

        var body = result.getResponse().getContentAsString();
        assertThatJson(body).node("id").isEqualTo(task.getId());
        assertThatJson(body).node("title").isEqualTo(task.getTitle());
        assertThatJson(body).node("description").isEqualTo(task.getDescription());
    }

    @Test
    public void testCreate() throws Exception {
        var task = Instancio.create(Task.class);
        task.setTitle(faker.book().title());
        task.setDescription(faker.lorem().paragraph());

        var result = mockMvc.perform(post("/tasks")
                        .content(om.writeValueAsString(task))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andReturn();

        var body = result.getResponse().getContentAsString();
        assertThatJson(body).node("id").isNotNull();
        assertThatJson(body).node("title").isEqualTo(task.getTitle());
        assertThatJson(body).node("description").isEqualTo(task.getDescription());
    }

    @Test
    public void testUpdate() throws Exception {
        var task = Instancio.create(Task.class);
        task = taskRepository.save(task);

        var updatedTask = Instancio.create(Task.class);
        updatedTask.setId(task.getId());

        var result = mockMvc.perform(put("/tasks/" + task.getId())
                        .content(om.writeValueAsString(updatedTask))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();

        var taskInDb = taskRepository.findById(task.getId()).get();
        assert taskInDb.getTitle().equals(updatedTask.getTitle());
        assert taskInDb.getDescription().equals(updatedTask.getDescription());
    }

    @Test
    public void testDelete() throws Exception {
        var task = Instancio.create(Task.class);
        task = taskRepository.save(task);

        mockMvc.perform(delete("/tasks/" + task.getId()))
                .andExpect(status().isOk());

        assertThat(taskRepository.findById(task.getId())).isEmpty();
    }

    // END
}
