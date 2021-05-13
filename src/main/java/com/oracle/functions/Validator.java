package com.oracle.functions;

@FunctionalInterface
public interface Validator<T> {
    boolean valid(T data);
}
