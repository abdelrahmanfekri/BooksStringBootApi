package com.technomad.books.mappers;

public interface Mapper<T, D> {

    public D mapTo(T t);

    public T mapFrom(D d);

}
