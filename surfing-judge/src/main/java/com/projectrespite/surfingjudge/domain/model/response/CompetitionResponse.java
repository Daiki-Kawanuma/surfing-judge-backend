package com.projectrespite.surfingjudge.domain.model.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.gson.annotations.SerializedName;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CompetitionResponse {

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

    @JsonProperty("wave_count")
    private int waveCount;
}
