package com.projectrespite.surfingjudge.domain.model.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.gson.annotations.SerializedName;
import lombok.Data;

import java.util.List;

@Data
public class JudgeResponse {

    @JsonProperty("player_number")
    @SerializedName("player_number")
    private int playerNumber;

    @JsonProperty("player_name")
    @SerializedName("player_name")
    private String playerName;

    @JsonProperty("player_color")
    @SerializedName("player_color")
    private String playerColor;

    @JsonProperty("wave")
    @SerializedName("wave")
    private int wave;

    @JsonProperty("scores")
    @SerializedName("scores")
    private List<Double> scores;
}
