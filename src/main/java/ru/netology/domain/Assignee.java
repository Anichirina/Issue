package ru.netology.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
@AllArgsConstructor
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)

public class Assignee extends Issue{
    private int id;
    private String name;
    private String surname;

    }


