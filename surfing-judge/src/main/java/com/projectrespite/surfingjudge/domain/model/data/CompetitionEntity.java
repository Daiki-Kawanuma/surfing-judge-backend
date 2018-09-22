package com.projectrespite.surfingjudge.domain.model.data;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.gson.annotations.SerializedName;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class CompetitionEntity {

    @JsonIgnore
    @SerializedName("_id")
    private String id;

    @JsonIgnore
    @SerializedName("_rev")
    private String rev;

    @JsonProperty("player_number")
    @SerializedName("player_number")
    private int playerNumber;

    @JsonProperty("player_name")
    @SerializedName("player_name")
    private String playerName;

    @JsonProperty("player_color")
    @SerializedName("player_color")
    private String playerColor;

    @JsonProperty("round")
    @SerializedName("round")
    private int round;

    @JsonProperty("heat")
    @SerializedName("heat")
    private int heat;
}
