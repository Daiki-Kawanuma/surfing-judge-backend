package com.projectrespite.surfingjudge.domain.model.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class JudgedWaveResponse {

    @JsonProperty("player_number")
    private int playerNumber;

    @JsonProperty("wave")
    private int wave;
}
