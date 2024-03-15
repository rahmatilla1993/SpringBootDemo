package com.example.springbootdemo.controller;

import com.example.springbootdemo.domains.Item;
import com.example.springbootdemo.dto.ErrorDto;
import com.example.springbootdemo.dto.ItemDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping(value = "/api/item/*")
@Tag(name = "Item Controller", description = "Mahsulotlar bilan ishlash controlleri")
public class ItemController {

    @GetMapping("all")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @Operation(summary = "Itemlar listini olish")
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Itemlar ro'yxati olindi",
                    content = {
                            @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(implementation = List.class)
                            )
                    }),
            @ApiResponse(
                    responseCode = "401",
                    description = "UnAuthorized",
                    content = {
                            @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(implementation = ErrorDto.class)
                            )
                    }),
            @ApiResponse(
                    responseCode = "403",
                    description = "Access Denied",
                    content = {
                            @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(implementation = ErrorDto.class)
                            )
                    })
    })
    public HttpEntity<List<Item>> getAll() {
        return ResponseEntity.ok(Collections.singletonList(Item.builder().build()));
    }

    @PostMapping("create")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @Operation(summary = "Item qo'shish")
    @ApiResponses({
            @ApiResponse(
                    responseCode = "201",
                    description = "Item qo'shildi",
                    content = {
                            @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(implementation = Item.class)
                            )
                    }),
            @ApiResponse(
                    responseCode = "401",
                    description = "UnAuthorized",
                    content = {
                            @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(implementation = ErrorDto.class)
                            )
                    }),
            @ApiResponse(
                    responseCode = "403",
                    description = "Access Denied",
                    content = {
                            @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(implementation = ErrorDto.class)
                            )
                    })
    })
    public ResponseEntity<Item> create(@RequestBody ItemDto dto) {
        Item item = Item.builder()
                .price(dto.getPrice())
                .title(dto.getTitle())
                .desc(dto.getDesc())
                .id(1)
                .build();
        return new ResponseEntity<>(item, HttpStatus.CREATED);
    }

    @PutMapping("update/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @Operation(summary = "Berilgan id li itemni edit qiladi")
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Item edit qilindi",
                    content = {
                            @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(implementation = Item.class)
                            )
                    }),
            @ApiResponse(
                    responseCode = "404",
                    description = "Berilgan idli item topilmadi",
                    content = {
                            @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(implementation = RuntimeException.class)
                            )
                    }),
            @ApiResponse(
                    responseCode = "401",
                    description = "UnAuthorized",
                    content = {
                            @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(implementation = ErrorDto.class)
                            )
                    }),
            @ApiResponse(
                    responseCode = "403",
                    description = "Access Denied",
                    content = {
                            @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(implementation = ErrorDto.class)
                            )
                    })
    })
    public ResponseEntity<Item> update(@PathVariable("id") long id, @RequestBody ItemDto dto) {
        Item item = Item.builder()
                .price(dto.getPrice())
                .title(dto.getTitle())
                .desc(dto.getDesc())
                .id(id)
                .build();
        return new ResponseEntity<>(item, HttpStatus.OK);
    }

    @DeleteMapping("delete/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @Operation(summary = "Berilgan idli itemni o'chiradi")
    @ApiResponses({
            @ApiResponse(
                    responseCode = "204",
                    description = "Item o'chirildi",
                    content = {
                            @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(implementation = String.class)
                            )
                    }),
            @ApiResponse(
                    responseCode = "404",
                    description = "Item topilmadi",
                    content = {
                            @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(implementation = RuntimeException.class)
                            )
                    }),
            @ApiResponse(
                    responseCode = "401",
                    description = "UnAuthorized",
                    content = {
                            @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(implementation = ErrorDto.class)
                            )
                    }),
            @ApiResponse(
                    responseCode = "403",
                    description = "Access Denied",
                    content = {
                            @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(implementation = ErrorDto.class)
                            )
                    })
    })
    public ResponseEntity<String> delete(@PathVariable Long id) {
        return new ResponseEntity<>("Successfully Deleted - Item", HttpStatus.NO_CONTENT);
    }

    @GetMapping("get/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @Operation(summary = "Berilgan idli itemni oladi")
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Item topildi",
                    content = {
                            @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(implementation = Item.class)
                            )
                    }),
            @ApiResponse(
                    responseCode = "404",
                    description = "Item topilmadi",
                    content = {
                            @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(implementation = RuntimeException.class)
                            )
                    }),
            @ApiResponse(
                    responseCode = "401",
                    description = "UnAuthorized",
                    content = {
                            @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(implementation = ErrorDto.class)
                            )
                    }),
            @ApiResponse(
                    responseCode = "403",
                    description = "Access Denied",
                    content = {
                            @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(implementation = ErrorDto.class)
                            )
                    })
    })
    public ResponseEntity<Item> get(@PathVariable Long id) {
        return new ResponseEntity<>(new Item(id, "Swagger", "Lorem Ipsum", 216.86D), HttpStatus.OK);
    }
}
