package com.sff.rbacdemo.biz.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * @author Frankie Fan
 * @date 2022-04-28 15:29
 */

@Data
public class OverdueBillingsDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    private String billingCode;

    private float stdFee;

    private float whPrice;

    private float totalWorkHours;

    private float serviceFee;

    private float extFee;

    private float otherFee;

    private float totalCost;

}
