package com.projectrespite.surfingjudge.domain.model.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.gson.annotations.SerializedName;
import lombok.Data;

import java.util.List;

@Data
public class JudgeListRequest {

    private int round;
    private int heat;
    @JsonProperty("player_number")
    @SerializedName("player_number")
    private int playerNumber;
    private String name;
    private int wave;
    private List<Double> scores;
}
