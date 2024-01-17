package com.mindex.challenge.service.impl;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mindex.challenge.dao.CompensationRepository;
import com.mindex.challenge.data.Compensation;
import com.mindex.challenge.service.CompensationService;

@Service
public class CompensationServiceImpl implements CompensationService{
    private static final Logger LOG = LoggerFactory.getLogger(CompensationServiceImpl.class);

    @Autowired
    CompensationRepository compensationRepository;
    
	@Override
	public Compensation create(Compensation compensation) {
		LOG.debug("Creating compensation [{}]", compensation);

		compensationRepository.insert(compensation);

		return compensation;
	}

	@Override
	public List<Compensation> readByEmpId(String empId) {
        LOG.debug("Finding compensation with emp id [{}]", empId);

        List<Compensation> compensation = compensationRepository.findByEmployeeId(empId);

        if (compensation == null) {
            throw new RuntimeException("Invalid employeeId: " + empId);
        }

        return compensation;
	}

	@Override
	public Compensation update(Compensation compensation) {
		LOG.debug("Updating compensation [{}]", compensation);

		return compensationRepository.save(compensation);
	}

	@Override
	public Compensation read(String id) {
        LOG.debug("Finding compensation with comp id [{}]", id);

        Optional<Compensation> compensation = compensationRepository.findById(id);

        if (compensation.isEmpty()) {
            throw new RuntimeException("Invalid compensation id: " + id);
        }

        return compensation.get();
	}

}
