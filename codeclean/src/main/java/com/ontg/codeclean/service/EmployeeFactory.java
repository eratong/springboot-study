package com.ontg.codeclean.service;

import com.ontg.codeclean.pojo.EmployeeRecord;

public interface EmployeeFactory {
    public Employee makeEmployee(EmployeeRecord r);
}