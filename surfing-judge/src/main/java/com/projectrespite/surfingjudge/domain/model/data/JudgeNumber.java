package com.projectrespite.surfingjudge.domain.model.data;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.gson.annotations.SerializedName;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class JudgeNumber {

    @JsonProperty("id")
    @SerializedName("_id")
    private String id;

    @JsonProperty("rev")
    @SerializedName("_rev")
    private String rev;

    @JsonProperty("numbers")
    @SerializedName("numbers")
    private List<String> numbers;
}
