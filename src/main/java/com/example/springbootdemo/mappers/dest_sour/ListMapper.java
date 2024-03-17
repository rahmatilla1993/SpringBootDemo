package com.example.springbootdemo.mappers.dest_sour;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface ListMapper {

    ListMapper LIST_MAPPER = Mappers.getMapper(ListMapper.class);

    List<Destination> fromSourceList(List<Source> sources);

    List<Source> fromDestinationList(List<Destination> destinations);
}
