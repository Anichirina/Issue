package ru.netology.manager;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import ru.netology.Exception.NoFoundException;
import ru.netology.comparator.IssueComparator;
import ru.netology.domain.Assignee;
import ru.netology.domain.Issue;
import ru.netology.repository.IssueRepository;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.function.Predicate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class IssueManagerTest {
    private IssueRepository repository = new IssueRepository();
    private IssueManager manager = new IssueManager(repository);
    private IssueComparator comparator = new IssueComparator();

    private Issue is1 = new Issue(1, "name1", "open", "author1",
            Arrays.asList("projects1", "projects2"), "genelal1",
            new Assignee(1, "Oleg", "Ivanov"), true, 5,
            "10,04.20", 1, "Test");

    private Issue is2 = new Issue(2, "name2", "open", "author2",
            Arrays.asList("projects2", "projects3"), "genelal2",
            new Assignee(3, "Vasya", "Smirnov"), true, 6,
            "10,05.20", 2, "Test2");

    private Issue is3 = new Issue(3, "name3", "closed", "author3",
            Arrays.asList("projects3"), "genelal3",
            new Assignee(3, "Vasya", "Smirnov"), false, 15,
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
            "10,04.19", 10, "Test4");

    @BeforeEach()
    void setUp() {
        manager.add(is1);
        manager.add(is2);
        manager.add(is3);
        manager.add(is4);
        manager.add(is5);
        manager.add(is6);
    }
    @Nested
    class shouldHowLesson {
        @Test
        void shouldAddPIssue() {
            repository.add(new Issue());
        }

        @Test
        void shouldAddMultipleIssue() {
            Collection<Issue> issues = new ArrayList<>();
            issues.add(new Issue());
            issues.add(new Issue());
            repository.addAll(issues);

            repository.addAll(List.of(new Issue(), new Issue()));
        }

        @Test
        void shouldAddAllNull() {
            repository.add(null);
        }

        @Test
        void shouldRemoveIssue() {
            boolean removed = repository.addAll(List.of(new Issue(), new Issue()));
        }

        @Test
        void shouldAddMultiRemoveAll() {
            Collection<Issue> issues = new ArrayList<>();
            issues.add(new Issue());
            issues.add(new Issue());
            repository.addAll(issues);
            repository.removeAll(issues);

            repository.removeAll(List.of(new Issue(), new Issue()));
        }

        @Test
        void shouldAddNullRemoveAll() {
            Collection<Issue> issues = new ArrayList<>();
            issues.add(null);
            repository.addAll(issues);
            repository.removeAll(issues);
        }

        @Test
        void shouldAddOneRemoveAll() {
            Collection<Issue> issues = new ArrayList<>();
            issues.add(new Issue());
            repository.addAll(issues);
            repository.removeAll(issues);
        }
    }
    @Nested
    class shouldFindAll {
        @Test
        void shouldFindAllClosed() {
            Collection<Issue> issues = new ArrayList<>();
            List<Issue> actual = manager.findAllClosed();
            List<Issue> expected = Arrays.asList(is1, is2, is5);
            assertEquals(expected, actual);
        }

        @Test
        void shouldOneValueFindAllClosed() {
            repository.remove(is1);
            repository.remove(is2);
            repository.remove(is3);
            repository.remove(is4);
            Collection<Issue> issues = new ArrayList<>();
            List<Issue> actual = manager.findAllClosed();
            List<Issue> expected = Arrays.asList(is5);
            assertEquals(expected, actual);
        }

        @Test
        void shouldNotValueFindAllClosed() {
            repository.remove(is1);
            repository.remove(is2);
            repository.remove(is3);
            repository.remove(is4);
            repository.remove(is5);
            repository.remove(is6);
            Collection<Issue> issues = new ArrayList<>();
            List<Issue> actual = manager.findAllClosed();
            List<Issue> expected = Arrays.asList();
            assertEquals(expected, actual);
        }

        @Test
        void shouldFindAllOpen() {
            Collection<Issue> issues = new ArrayList<>();
            List<Issue> actual = manager.findAllOpened();
            List<Issue> expected = Arrays.asList(is3, is4, is6);
            assertEquals(expected, actual);
        }

        @Test
        void shouldNotValueFindAllOpen() {
            repository.remove(is1);
            repository.remove(is2);
            repository.remove(is3);
            repository.remove(is4);
            repository.remove(is6);
            Collection<Issue> issues = new ArrayList<>();
            List<Issue> actual = manager.findAllOpened();
            List<Issue> expected = Arrays.asList();
            assertEquals(expected, actual);
        }

        @Test
        void shouldOneValueFindAllOpen() {
            repository.remove(is1);
            repository.remove(is2);
            repository.remove(is3);
            repository.remove(is4);
            Collection<Issue> issues = new ArrayList<>();
            List<Issue> actual = manager.findAllOpened();
            List<Issue> expected = Arrays.asList(is6);
            assertEquals(expected, actual);
        }
    }
    @Nested
    class shouldFilterByAuthorOtherVariant {
    @Test
    void shouldFilterByAuthor() {

        repository.remove(is5);
        List<Issue> actual = manager.filterByAuthor("author2", comparator);
        List<Issue> expected = Arrays.asList(is2);
        assertEquals(expected, actual);
    }
        @Test
        void shouldFilterByAuthors() {

            repository.remove(is5);
            List<Issue> actual = manager.filterByAuthor("author3", comparator);
            List<Issue> expected = Arrays.asList(is4,is3);
            assertEquals(expected, actual);
        }
        @Test
        void shouldfilterByLabel() {

            repository.remove(is5);
            List<Issue> actual = manager.filterByLabel("Test2", comparator);
            List<Issue> expected = Arrays.asList(is2);
            assertEquals(expected, actual);
        }
        @Test
        void shouldfilterByLabels() {

            repository.remove(is5);
            List<Issue> actual = manager.filterByLabel("Test4", comparator);
            List<Issue> expected = Arrays.asList(is6,is4);
            assertEquals(expected, actual);
        }
        @Test
        void shouldfilterByAssignee() {

            repository.remove(is5);
            List<Issue> actual = manager.filterByAssignee(new Assignee(4, "Vova", "Petrov"),comparator);
            List<Issue> expected = Arrays.asList(is4);
            assertEquals(expected, actual);
        }
        @Test
        void shouldfilterByAssignees() {

            repository.remove(is5);
            List<Issue> actual = manager.filterByAssignee(new Assignee(3, "Vasya", "Smirnov"),comparator);
            List<Issue> expected = Arrays.asList(is3,is2);
            assertEquals(expected, actual);
        }
        @Test
        void shouldNotFilterByLabel() {
            repository.remove(is5);
            List<Issue> actual = manager.filterByLabel("Test20",comparator);

            assertEquals(0, actual.size());
        }
        @Test
        void shouldNotFilterByAuthor() {
            repository.remove(is5);
            List<Issue> actual = manager.filterByAuthor("Author20",comparator);

            assertEquals(0, actual.size());
        }
        @Test
        void shouldNotFilterByAssignee() {
            repository.remove(is5);
            List<Issue> actual = manager.filterByAssignee(new Assignee(15,"autor","name"), comparator);

            assertEquals(0, actual.size());
        }
        }


}