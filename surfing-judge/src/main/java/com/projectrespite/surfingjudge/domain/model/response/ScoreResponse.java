package com.projectrespite.surfingjudge.domain.model.response;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.gson.annotations.SerializedName;
import lombok.Data;

import java.util.ArrayList;

@Data
public class ScoreResponse {

    @JsonProperty("player_number")
    @SerializedName("player_number")
    private int playerNumber;

    @JsonProperty("name")
    private String name;

    @JsonProperty("scores")
    private ArrayList<Double> scores;

    @JsonProperty("aggregate")
    private Double aggregate;

    @JsonIgnore
    private double[][] judgedScores = new double[10][5];
}
