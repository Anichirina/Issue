package ru.netology.comparator;

import ru.netology.domain.Issue;

import java.util.Collection;
import java.util.Comparator;

public class IssueComparator implements Comparator<Issue> {
    public int compare(Issue i1, Issue i2) {
        return i2.getId() - i1.getId();
    }
    //сортировка Issues по id

    }





