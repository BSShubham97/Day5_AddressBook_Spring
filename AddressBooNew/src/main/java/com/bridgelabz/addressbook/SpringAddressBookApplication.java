package com.bridgelabz.addressbook;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;


@SpringBootApplication
@Slf4j
public class SpringAddressBookApplication {

    public static void main(String[] args) {
        ApplicationContext context= SpringApplication.run(SpringAddressBookApplication.class, args);
        log.info("EMPLOYEE PAYROLL STARTED !!! in {} ENVIRONMENT" , context.getEnvironment().getProperty("environment"));
        log.info("EMPLOYEE_PAYROLL_DATABASE USER IS {} ",context.getEnvironment().getProperty("spring.datasource.username"));
    }
}