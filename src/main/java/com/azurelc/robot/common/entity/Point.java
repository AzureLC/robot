package com.azurelc.robot.common.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class Point extends Coordinate {
    private int rgb;

    public Point(int x, int y, int rgb) {
        super(x, y);
        this.rgb = rgb;
    }
}
