package com.github.karixdev.orderservice.mapper;

import java.util.Collection;
import java.util.List;

public interface DtoMapper<T, E> {
    E map(T t);
    List<E> map(Collection<T> ts);
}
