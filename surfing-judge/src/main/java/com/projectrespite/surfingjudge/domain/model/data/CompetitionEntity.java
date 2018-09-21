package com.projectrespite.surfingjudge.domain.model.data;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.gson.annotations.SerializedName;

public class CompetitionEntity {

    @JsonProperty("id")
    @SerializedName("_id")
    private String id;

    @JsonProperty("rev")
    @SerializedName("_rev")
    private String rev;

    @JsonProperty("player_number")
    @SerializedName("player_number")
    private String playerNumber;

    @JsonProperty("player_name")
    @SerializedName("player_name")
    private String playerName;

    @JsonProperty("player_color")
    @SerializedName("player_color")
    private String playerColor;

    @JsonProperty("round")
    @SerializedName("round")
    private String round;

    @JsonProperty("heat")
    @SerializedName("heat")
    private String heat;
}
