package com.elcom.management_library_data.mysql.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class FirstTextWithBookCount {
    private String firstText;
    private int total;
}
