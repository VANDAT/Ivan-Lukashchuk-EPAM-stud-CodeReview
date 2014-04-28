package com.epam.kiev.springdao;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.epam.kiev.springdao.repository.employee.EmployeeRepository;

public class App {

    public static void main(String[] args) {
        System.out.println("Hello World!");
      
        ApplicationContext appCtx = 
                new ClassPathXmlApplicationContext("persistenceContext.xml");
        
        EmployeeRepository repository = appCtx.getBean("employeeRepository", EmployeeRepository.class);
                     
        System.out.println(repository);
    }
}
