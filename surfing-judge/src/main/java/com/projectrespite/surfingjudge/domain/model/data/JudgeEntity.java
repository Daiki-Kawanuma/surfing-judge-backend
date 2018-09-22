package com.projectrespite.surfingjudge.domain.model.data;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.gson.annotations.SerializedName;
import lombok.Data;

@Data
public class JudgeEntity {

    @JsonIgnore
    @SerializedName("_id")
    private String id;

    @JsonIgnore
    @SerializedName("_rev")
    private String rev;

    @JsonProperty("round")
    @SerializedName("round")
    private int round;

    @JsonProperty("heat")
    @SerializedName("heat")
    private int heat;

    @JsonProperty("player_number")
    @SerializedName("player_number")
    private int playerNumber;

    @JsonProperty("player_name")
    @SerializedName("player_name")
    private String playerName;

    @JsonProperty("player_color")
    @SerializedName("player_color")
    private String playerColor;

    @JsonProperty("judge_number")
    @SerializedName("judge_number")
    private int judgeNumber;

    @JsonProperty("wave")
    @SerializedName("wave")
    private int wave;

    @JsonProperty("score")
    @SerializedName("score")
    private double score;
}
