package com.projectrespite.surfingjudge.domain.model.data;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.gson.annotations.SerializedName;
import lombok.Data;

@Data
public class Judge {

    private String _id;
    private String _rev;
    private int round;
    private int heat;
    @JsonProperty("player_number")
    @SerializedName("player_number")
    private int playerNumber;
    private String name;
    @JsonProperty("judge_number")
    @SerializedName("judge_number")
    private int judgeNumber;
    private int wave;
    private double score;
}
