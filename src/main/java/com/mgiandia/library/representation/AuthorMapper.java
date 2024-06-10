package com.mgiandia.library.representation;

import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;

import com.mgiandia.catalog.domain.Author;

@Mapper(componentModel = "jakarta",
injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public abstract class AuthorMapper {
	public abstract AuthorRepresentation toRepresentation(Author author);
	
}
