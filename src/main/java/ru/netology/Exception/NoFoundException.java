package ru.netology.Exception;

import lombok.Data;
import lombok.EqualsAndHashCode;
import ru.netology.manager.IssueManager;
import ru.netology.repository.IssueRepository;

@Data
@EqualsAndHashCode(callSuper = true)
public class NoFoundException extends RuntimeException {


    public NoFoundException(String message) {
        super(message);
    }

    public NoFoundException() {
    }

    public NoFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public NoFoundException(Throwable cause) {
        super(cause);
    }

    public NoFoundException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}