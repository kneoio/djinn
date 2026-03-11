package com.semantyca.jesoos.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
public class AvailableStationsAiDTO {
    private List<RadioStationAiDTO> radioStations;
}