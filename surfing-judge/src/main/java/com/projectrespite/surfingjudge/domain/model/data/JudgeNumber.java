package com.projectrespite.surfingjudge.domain.model.data;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class JudgeNumber {

    private String _id;
    private String _rev;
    private List<String> numbers;
}
