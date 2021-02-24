package ru.netology.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode

public class Issue implements List<Issue> {
    private int id;
    private String name;
    private String status;
    private String author;
    private Set<String> projects = new HashSet<>();
    private String milestone;
    private Assignee assignee;
    private boolean isClosed;
    private int commentsCount;
    private String date;
    private int pullRequestCount;
    private String labels;

    public Issue(int id, String name, String status,
                 String author, Collection<String> projects, String milestone,
                 Assignee assignee, boolean isClosed, int commentsCount,
                 String date, int pullRequestCount, String labels) {
        this.id = id;
        this.name = name;
        this.status = status;
        this.author = author;
        this.projects.addAll(projects);
        this.milestone = milestone;
        this.assignee=assignee;
        this.isClosed = isClosed;
        this.commentsCount = commentsCount;
        this.date = date;
        this.pullRequestCount = pullRequestCount;
        this.labels = labels;
    }


    @Override
    public int size() {
        return 0;
    }

    @Override
    public boolean isEmpty() {
        return false;
    }

    @Override
    public boolean contains(Object o) {
        return false;
    }

    @Override
    public Iterator<Issue> iterator() {
        return null;
    }

    @Override
    public Object[] toArray() {
        return new Object[0];
    }

    @Override
    public <T> T[] toArray(T[] a) {
        return null;
    }

    @Override
    public boolean add(Issue issue) {
        return false;
    }

    @Override
    public boolean remove(Object o) {
        return false;
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        return false;
    }

    @Override
    public boolean addAll(Collection<? extends Issue> c) {
        return false;
    }

    @Override
    public boolean addAll(int index, Collection<? extends Issue> c) {
        return false;
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        return false;
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        return false;
    }

    @Override
    public void clear() {

    }

    @Override
    public Issue get(int index) {
        return null;
    }

    @Override
    public Issue set(int index, Issue element) {
        return null;
    }

    @Override
    public void add(int index, Issue element) {

    }

    @Override
    public Issue remove(int index) {
        return null;
    }

    @Override
    public int indexOf(Object o) {
        return 0;
    }

    @Override
    public int lastIndexOf(Object o) {
        return 0;
    }

    @Override
    public ListIterator<Issue> listIterator() {
        return null;
    }

    @Override
    public ListIterator<Issue> listIterator(int index) {
        return null;
    }

    @Override
    public List<Issue> subList(int fromIndex, int toIndex) {
        return null;
    }
}
