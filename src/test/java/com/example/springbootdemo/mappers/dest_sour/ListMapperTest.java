package com.example.springbootdemo.mappers.dest_sour;

import org.junit.jupiter.api.Test;

import java.util.List;

import static com.example.springbootdemo.mappers.dest_sour.ListMapper.LIST_MAPPER;
import static org.junit.jupiter.api.Assertions.*;

class ListMapperTest {

    @Test
    void fromSourceList() {
        List<Source> sources = List.of(
                new Source("one", "desc1"),
                new Source("two", "desc2"),
                new Source("three", "desc3"),
                new Source("four", "desc4")
        );
        List<Destination> destinations = LIST_MAPPER.fromSourceList(sources);
        destinations.forEach(System.out::println);
    }

    @Test
    void fromDestinationList() {
        List<Destination> destinations = List.of(
                new Destination("one", "desc1"),
                new Destination("two", "desc2"),
                new Destination("three", "desc3"),
                new Destination("four", "desc4")
        );
        List<Source> sources = LIST_MAPPER.fromDestinationList(destinations);
        sources.forEach(System.out::println);
    }
}