package com.mindex.challenge.service;

import java.util.List;

import com.mindex.challenge.data.Compensation;

public interface CompensationService {
    Compensation create(Compensation employee);
    List<Compensation> readByEmpId(String empId);
    Compensation update(Compensation employee);
	Compensation read(String id);
}
