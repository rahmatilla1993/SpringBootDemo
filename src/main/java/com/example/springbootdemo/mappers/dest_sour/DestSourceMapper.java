package com.example.springbootdemo.mappers.dest_sour;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface DestSourceMapper {
    DestSourceMapper TEST_MAPPER = Mappers.getMapper(DestSourceMapper.class);
    Destination toDest(Source source);

    Source fromDest(Destination destination);
}
