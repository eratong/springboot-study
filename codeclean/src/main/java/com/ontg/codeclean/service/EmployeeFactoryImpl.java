package com.ontg.codeclean.service;

import com.ontg.codeclean.pojo.EmployeeRecord;

public class EmployeeFactoryImpl implements EmployeeFactory {
    public Employee makeEmployee(EmployeeRecord r) {
//        switch (r.type) {
//            case COMMISSIONED:
//                return new CommissionedEmployee(r);
//            case HOURLY:
//                return new HourlyEmployee(r);
//            case SALARIED:
//                return new SalariedEmploye(r);
//            default:
//                throw new InvalidEmployeeType(r.type);
//        }
        return null;
    }
}