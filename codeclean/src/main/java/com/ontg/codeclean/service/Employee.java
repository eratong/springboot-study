package com.ontg.codeclean.service;

import com.ontg.codeclean.pojo.Money;

public abstract class Employee {
    public abstract boolean isPayday();

    public abstract Money calculatePay();

    public abstract void deliverPay(Money pay);
}



