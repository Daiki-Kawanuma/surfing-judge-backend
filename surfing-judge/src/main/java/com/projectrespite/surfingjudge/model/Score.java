package com.projectrespite.surfingjudge.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.gson.annotations.SerializedName;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class Score {

    @JsonProperty("player_number")
    @SerializedName("player_number")
    private int playerNumber;
    private String name;
    private ArrayList<Double> scores;
    private Double aggregate;

    private double[][] judgedScores = new double[10][5];
}
