package com.projectrespite.surfingjudge.domain.model.data;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.gson.annotations.SerializedName;
import lombok.Data;

@Data
public class JudgeEntity {

    @JsonProperty("_id")
    @SerializedName("_id")
    private String id;

    @JsonProperty("_rev")
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
