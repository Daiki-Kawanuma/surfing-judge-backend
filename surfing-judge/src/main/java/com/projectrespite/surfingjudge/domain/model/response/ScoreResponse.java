package com.projectrespite.surfingjudge.domain.model.response;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.gson.annotations.SerializedName;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class ScoreResponse {

    @JsonProperty("player_number")
    @SerializedName("player_number")
    private int playerNumber;

    @JsonProperty("player_name")
    private String playerName;

    @JsonProperty("player_color")
    private String playerColor;

    @JsonProperty("scores")
    private List<Double> scores;

    @JsonProperty("aggregate")
    private Double aggregate;

    @JsonIgnore
    private double[][] judgedScores = new double[10][5];
}
