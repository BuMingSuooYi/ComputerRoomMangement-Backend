package com.nchu.software;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

@SpringBootApplication
public class ComputerRoomManagementBackendApplication {

	public static void main(String[] args) {
		SpringApplication.run(ComputerRoomManagementBackendApplication.class, args);
	}

	@PostConstruct
	public void init() {
		// 在项目启动后执行的逻辑代码

		System.out.println("项目启动后执行的函数");
	}

	@PreDestroy
	public void cleanup() {
		// 在项目关闭前执行的逻辑代码
		System.out.println("项目关闭前执行的函数");
	}

}
