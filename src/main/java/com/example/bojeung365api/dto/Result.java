package com.example.bojeung365api.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

// List 형태로 response 해줘야 하는 경우에 한 번 감싸서 돌려 주기 위한 객체
@Data
@AllArgsConstructor
public class Result<T> {
    private T data;
}