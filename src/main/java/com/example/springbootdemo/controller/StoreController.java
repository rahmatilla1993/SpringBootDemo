package com.example.springbootdemo.controller;

import com.example.springbootdemo.domains.Store;
import com.example.springbootdemo.dto.ErrorDto;
import com.example.springbootdemo.dto.StoreDto;
import com.example.springbootdemo.security.SessionUser;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping(value = "/api/store/*")
@Tag(name = "Store Controller", description = "Store bilan ishlash controlleri")
public class StoreController {

    private final SessionUser sessionUser;

    @Autowired
    public StoreController(SessionUser sessionUser) {
        this.sessionUser = sessionUser;
    }

    @GetMapping("/all")
    @PreAuthorize("hasRole('ROLE_USER')")
    @Operation(summary = "Storelar ro'yxatini olish")
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Storelar listi olindi",
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
    public HttpEntity<List<Store>> getAll() {
        return ResponseEntity.ok(Collections.singletonList(Store.builder().build()));
    }

    @PostMapping("create")
    @PreAuthorize("hasRole('ROLE_USER')")
    @Operation(summary = "Store qo'shish")
    @ApiResponses({
            @ApiResponse(
                    responseCode = "201",
                    description = "Store qo'shildi",
                    content = {
                            @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(implementation = Store.class)
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
    public ResponseEntity<Store> create(@RequestBody StoreDto dto) {
        Store store = Store.builder()
                .createdBy(sessionUser.getUserName().get())
                .desc(dto.getDesc())
                .title(dto.getTitle())
                .number(dto.getNumber())
                .id(1)
                .build();
        return new ResponseEntity<>(store, HttpStatus.CREATED);
    }

    @PutMapping("update/{id}")
    @PreAuthorize("hasRole('ROLE_USER')")
    @Operation(summary = "Storeni edit qilish")
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Store edit qilindi",
                    content = {
                            @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(implementation = Store.class)
                            )
                    }),
            @ApiResponse(
                    responseCode = "404",
                    description = "Berilgan idli store topilmadi",
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
    public ResponseEntity<Store> update(@PathVariable("id") long id, @RequestBody StoreDto dto) {
        Store store = Store.builder()
                .createdBy(sessionUser.getUserName().get())
                .desc(dto.getDesc())
                .title(dto.getTitle())
                .number(dto.getNumber())
                .id(id)
                .build();
        return new ResponseEntity<>(store, HttpStatus.OK);
    }

    @DeleteMapping("delete/{id}")
    @PreAuthorize("hasRole('ROLE_USER')")
    @Operation(summary = "Berilgan idli storeni o'chirish")
    @ApiResponses({
            @ApiResponse(
                    responseCode = "204",
                    description = "Store o'chirildi",
                    content = {
                            @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(implementation = String.class)
                            )
                    }),
            @ApiResponse(
                    responseCode = "404",
                    description = "Berilgan idli store topilmadi",
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
        return new ResponseEntity<>("Successfully Deleted - Store", HttpStatus.NO_CONTENT);
    }

    @GetMapping("get/{id}")
    @PreAuthorize("hasRole('ROLE_USER')")
    @Operation(summary = "Berilgan idli storeni olish")
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Store olindi",
                    content = {
                            @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(implementation = Store.class)
                            )
                    }),
            @ApiResponse(
                    responseCode = "404",
                    description = "Berilgan idli store topilmadi",
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
    public ResponseEntity<Store> get(@PathVariable Long id) {
        return new ResponseEntity<>(new Store(id, "Store", ".....@gmail.com",
                20, "The point of using Lorem Ipsum is that it"), HttpStatus.OK);
    }
}
