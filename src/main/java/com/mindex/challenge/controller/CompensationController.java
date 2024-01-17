package com.mindex.challenge.controller;

import com.mindex.challenge.data.Employee;

public class CompensationController {
    private static final Logger LOG = LoggerFactory.getLogger(EmployeeController.class);

    @Autowired
    private CompensationService compensationService;

    @PostMapping("/compensation")
    public Compensation create(@RequestBody Employee employee) {
        LOG.debug("Received compensation create request for [{}]", employee);

        return compensationService.create(employee);
    }

    @GetMapping("/compensation/{id}")
    public Compensation read(@PathVariable String id) {
        LOG.debug("Received compensation read request for empid [{}]", id);

        return compensationService.read(id);
    }
}
