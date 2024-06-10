package com.mgiandia.library.representation;

import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;

import com.mgiandia.catalog.domain.ISBN;

@Mapper(componentModel = "jakarta",
injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public class IsbnMapper {
	public String toString(ISBN isbn) {
		return isbn == null ? null : isbn.getValue();
	}
}
