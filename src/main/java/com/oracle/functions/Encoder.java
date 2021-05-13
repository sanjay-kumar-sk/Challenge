package com.oracle.functions;

@FunctionalInterface
public interface Encoder<T> {
    T encode(T data);
}
