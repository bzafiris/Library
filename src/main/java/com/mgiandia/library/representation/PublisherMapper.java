package com.mgiandia.library.representation;

import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;

import com.mgiandia.catalog.domain.Publisher;

@Mapper(componentModel = "jakarta",
injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public abstract class PublisherMapper {
	public abstract PublisherRepresentation toRepresentation(Publisher publisher);
	
}
