package com.smallwonders.model.core;

import lombok.*;
import lombok.Data;

import javax.persistence.*;

@Embeddable
@Data
@NoArgsConstructor
public class Coordinates {
    private Long coordinatesId;
    private double latitude;
    private double longitude;
}
