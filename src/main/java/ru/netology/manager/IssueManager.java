package ru.netology.manager;


import ru.netology.domain.Assignee;
import ru.netology.domain.Issue;
import ru.netology.repository.IssueRepository;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.function.Predicate;


public class IssueManager implements Predicate<Issue> {
    private IssueRepository repository;


    public IssueManager(IssueRepository repository) {
        this.repository = repository;
    }

    public void add(Issue issue) {
        repository.save(issue);
    }

    public List<Issue> findAllOpened() {
        List<Issue> tmp = new ArrayList<>();
        for (Issue issue : repository.findAll()) {
            if (!issue.isClosed()) {
                tmp.add(issue);
            }
        }
        return tmp;
    }

    public List<Issue> findAllClosed() {
        List<Issue> tmp = new ArrayList<>();
        for (Issue issue : repository.findAll()) {
            if (issue.isClosed()) {
                tmp.add(issue);
            }
        }
        return tmp;
    }

    public List<Issue> filterBy(Predicate<Issue> predicate, Comparator<Issue> issueComparator) {
        List<Issue> temp = new ArrayList<>();
        for (Issue issue : repository.findAll()) {
            if (predicate.test(issue)) {
                temp.add(issue);
            }
        }
        temp.sort(issueComparator);
        return temp;


    }


    public List<Issue> filterByAuthor(String author, Comparator<Issue> issueComparator) {

        Predicate<Issue> isAuthorPredicate = p -> p.getAuthor().equalsIgnoreCase(author);
        return filterBy(isAuthorPredicate, issueComparator);
    }


    public List<Issue> filterByAssignee(Assignee assignee, Comparator<Issue> issueComparator) {
        Predicate<Issue> isAssigneePredicate = p -> p.getAssignee().equals(assignee);
        return filterBy(isAssigneePredicate, issueComparator);
    }

    public List<Issue> filterByLabel(String label, Comparator<Issue> issueComparator) {
        Predicate<Issue> isLabelPredicate = p -> p.getLabels().equals(label);
        return filterBy(isLabelPredicate, issueComparator);
    }

    @Override
    public boolean test(Issue issue) {
        return false;
    }

    @Override
    public Predicate<Issue> and(Predicate<? super Issue> other) {
        return null;
    }

    @Override
    public Predicate<Issue> negate() {
        return null;
    }

    @Override
    public Predicate<Issue> or(Predicate<? super Issue> other) {
        return null;
    }

}