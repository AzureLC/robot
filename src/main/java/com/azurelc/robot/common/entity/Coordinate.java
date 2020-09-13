package com.azurelc.robot.common.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 返回坐标实体类
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Coordinate {
    /**
     * x坐标
     */
    private int x;

    /**
     * y坐标
     */
    private int y;
}
