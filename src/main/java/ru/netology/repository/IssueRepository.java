package ru.netology.repository;

import ru.netology.domain.Issue;
import ru.netology.Exception.NoFoundException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class IssueRepository{
    private List<Issue> issues = new ArrayList<>();


    public List<Issue> getAll() {
        return issues;
    }

    public void save(Issue issue) {
        issues.add(issue);
    }

    public Issue findById(int id) {

        for (Issue item : issues) {
            if (item.getId() == id) {
                return item;
            }
        }
        throw new NoFoundException("Incorrect");
    }



    public boolean add(Issue item) {
        //
        return issues.add(item);
        //
    }

    public List<Issue> findAll() {
        return issues;
    }

    public boolean remove(Issue item) {
        //
        return issues.remove(item);
        //
    }

    public boolean addAll(Collection<? extends Issue> items) {
        return this.issues.addAll(items);
    }

    public boolean removeAll(Collection<? extends Issue> items) {
        return this.issues.removeAll(items);
    }

    public void openById(int id) {
        for (Issue issue : issues) {
            if (issue.getId() == id) {
                issue.setClosed(false);
                issue.setStatus("Open");
            }

        }
    }

    public void closeById(int id) {
        for (Issue issue : issues) {
            if (issue.getId() == id) {
                issue.setClosed(true);
                issue.setStatus("Closed");
            }
        }
    }

}
