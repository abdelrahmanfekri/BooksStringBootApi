package com.technomad.books.mappers.impl;

import com.technomad.books.mappers.Mapper;
import com.technomad.books.domain.entities.BookEntity;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import com.technomad.books.domain.dto.BookDto;

@Component
public class BookMapperImpl implements Mapper<BookEntity, BookDto> {

    private final ModelMapper modelMapper;

    public BookMapperImpl(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    @Override
    public BookDto mapTo(BookEntity bookEntity) {
        return modelMapper.map(bookEntity, BookDto.class);
    }

    @Override
    public BookEntity mapFrom(BookDto bookDto) {
        return modelMapper.map(bookDto, BookEntity.class);
    }

}
