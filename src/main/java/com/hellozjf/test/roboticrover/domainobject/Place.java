package com.hellozjf.test.roboticrover.domainobject;

import com.hellozjf.test.roboticrover.util.ConverterUtils;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 机器人漫游车的位置和方向
 * @author hellozjf
 */
@Data
@EqualsAndHashCode
public class Place {

    /**
     * x坐标
     */
    private Integer x;

    /**
     * y坐标
     */
    private Integer y;

    /**
     * 角度信息，0表示正东，90表示正北，180表示正西，270表示正南，360表示正东……
     * 移动的时候，x = x + cos(rad(angle))，y = y + sin(rad(angle))
     */
    private Integer angle;

    public Place(Integer x, Integer y, Integer angle) {
        this.x = x;
        this.y = y;
        this.angle = angle;
    }

    public String getDirectionInfo() {
        return x + " " + y + " " + ConverterUtils.getDirectionByAngle(angle);
    }
}
