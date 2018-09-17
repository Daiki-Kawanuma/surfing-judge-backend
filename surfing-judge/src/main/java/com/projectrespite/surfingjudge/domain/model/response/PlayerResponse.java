package com.projectrespite.surfingjudge.domain.model.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.gson.annotations.SerializedName;
import lombok.Data;

@Data
public class PlayerResponse {

    @JsonProperty(value = "player_number")
    @SerializedName("player_number")
    private Integer playerNumber;

    @JsonProperty(value = "player_name")
    @SerializedName("player_name")
    private String playerName;
}
