package com.example.springbootdemo.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Upload {

    private int id;
    private String originalName;
    private String generatedName;
    private long size;
    private String mimeType;
    private String uploadedPath;
}
