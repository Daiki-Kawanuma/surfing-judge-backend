package com.projectrespite.surfingjudge.domain.model.data;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;

@AllArgsConstructor
@Getter
public enum Color {
    RED("Red", 1),
    WHITE("White", 2),
    YELLOW("Yellow", 3),
    BLUE("Blue", 4),
    BLACK("Black", 5);

    private String color;
    private int order;

    public static Color fromString(String color){

        return Arrays.stream(values()).filter(v -> v.color.equals(color))
                .findFirst().orElseThrow(IllegalAccessError::new);
    }

    @Override
    public String toString(){
        return color;
    }
}