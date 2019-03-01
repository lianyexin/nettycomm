package com.wstone.pojo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

/**
 * Created by shiwei on 2019/3/1.
 */
@Getter
@Setter
@AllArgsConstructor
public class DataPojo {

    private short header;
    private short length;
    private float data;

}
