package com.projectrespite.surfingjudge.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.gson.annotations.SerializedName;
import lombok.Data;

import java.util.List;

@Data
public class JudgeAggregate {

    @JsonProperty("player_number")
    @SerializedName("player_number")
    private int playerNumber;
    private String name;
    private int wave;
    private List<Double> scores;
}
