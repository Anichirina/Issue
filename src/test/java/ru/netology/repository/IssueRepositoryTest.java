package ru.netology.repository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import ru.netology.Exception.NoFoundException;
import ru.netology.comparator.IssueComparator;
import ru.netology.domain.Assignee;
import ru.netology.domain.Issue;
import ru.netology.manager.IssueManager;

import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class IssueRepositoryTest<Public> {
    private IssueRepository repository = new IssueRepository();
    private IssueManager manager = new IssueManager(repository);
    private IssueComparator comparator = new IssueComparator();

    private Issue is1 = new Issue(1, "name1", "open", "author1",
            Arrays.asList("projects1", "projects2"), "genelal1",
            new Assignee(1, "Oleg", "Ivanov"), false, 5,
            "10,04.20", 1, "Test");

    private Issue is2 = new Issue(2, "name2", "open", "author2",
            Arrays.asList("projects2", "projects3"), "genelal2",
            new Assignee(2, "Igor", "Pavlov"), false, 6,
            "10,05.20", 2, "Test2");

    private Issue is3 = new Issue(3, "name3", "closed", "author3",
            Arrays.asList("projects3"), "genelal3",
            new Assignee(3, "Vasya", "Smirnov"), true, 15,
            "10,06.20", 0, "Test3");

    private Issue is4 = new Issue(4, "name4", "open", "author3",
            Arrays.asList("projects2", "projects8", "projects12"), "genelal4",
            new Assignee(4, "Vova", "Petrov"), false, 11,
            "10,04.19", 1, "Test4");

    private Issue is5 = new Issue(5, "name5", "closed", null,
            Arrays.asList("projects5", "projects16"), "genelal1",
            null, true, 9,
            "10,04.20", 1, null);

    private Issue is6 = new Issue(566, "name6", "open", "author6",
            Arrays.asList("projects1", "project10"), "genelal4",
            new Assignee(0, null, "Maheev"), false, 5,
            "10,04.19", 10, "Test6");

    @BeforeEach()
    void setUp() {
        manager.add(is1);
        manager.add(is2);
        manager.add(is3);
        manager.add(is4);
        manager.add(is5);
        manager.add(is6);
    }

    //проверка простых функций
    @Nested
    class WorkinStandardFuction {
        @Test
        void shouldfindAll() {

            repository.getAll();

            Collection<Issue> issues = new ArrayList<>();
            List<Issue> actual = repository.findAll();
            List<Issue> expected = Arrays.asList(is1, is2, is3, is4, is5, is6);
            assertEquals(expected, actual);


        }

        @Test
        void shouldNotValuefindAll() {

            repository.getAll();
            repository.removeAll(new ArrayList<>(repository.findAll()));
            Collection<Issue> issues = new ArrayList<>();
            List<Issue> actual = repository.findAll();
            List<Issue> expected = Arrays.asList();
            assertEquals(expected, actual);
        }

        @Test
        void shouldgetById() {
            List<Issue> actual = repository.findById(5);
            assertEquals(is5, actual);


        }

        @Test
        void shouldgetByIdNull() {
            List<Issue> actual = repository.findById(7);
            assertEquals(null, actual);


        }

        @Test
        void shouldAddAllandRemovAll() {
            repository.removeAll(List.of(is1, is2, is3, is4, is5, is6));
            repository.addAll(List.of(is1, is2, is3, is4, is5, is6));
            List<Issue> actual = repository.findAll();
            List<Issue> expected = Arrays.asList(is1, is2, is3, is4, is5, is6);
            assertEquals(expected, actual);
        }

        @Test
        void shouldAddRemovAllNotValue() {
            repository.removeAll(List.of(is1, is2, is3, is4, is5, is6));
            List<Issue> actual = repository.findAll();
            List<Issue> expected = Arrays.asList();
            assertEquals(expected, actual);
        }

    }

    @Nested
    class WorkinOpenAndClosedById {
        @Test
        void shouldOpenById() {
            repository.openById(2);
            Issue byId = repository.findById(2);
            boolean expected = true;
            boolean actual = is3.isClosed();
            assertEquals(expected, actual);
        }
        @Test
        void shouldClosedWihtOpenById() {
            repository.openById(3);
            List <Issue> byId = repository.findById(3);
            boolean expected = false;
            boolean actual = is3.isClosed();
            assertEquals(expected, actual);
        }
        @Test
        void shouldClosedWithCloseById() {
            repository.closeById(4);
            List <Issue> byId = repository.findById(4);
            boolean expected = true;
            boolean actual = is3.isClosed();
            assertEquals(expected, actual);
        }
    }

    @Nested
    class AddAssignee {
    @Test
    void shouldAddAssignee() {
        repository.add(new Assignee(8,"Mike","Malikov"));
    }

    @Test
    void shouldAddMultipleAssignee() {
        boolean removed = repository.addAll(List.of(new Assignee(6,"Petr","Sidorov"), new Assignee(7,"Denis","Smirnov")));
    }
}
    @Nested
    class Exception {

        @Test
        void shouldWhenTryToRemoveMissingElement() {
            int id = 17;
            java.lang.Exception e = assertThrows(NoFoundException.class, () -> repository.findById(id));
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }
}