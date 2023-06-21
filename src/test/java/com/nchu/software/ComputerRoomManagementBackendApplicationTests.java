package com.nchu.software;

import com.nchu.software.entity.LoginLog;
import com.nchu.software.service.LoginLogService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class ComputerRoomManagementBackendApplicationTests {
	@Autowired
	private LoginLogService loginLogService;

	@Test
	void contextLoads() {
	}
	@Test
	void addLoginLog(){
		LoginLog loginLog=new LoginLog();
		loginLog.setLoginIp("10.1.88.4");
		loginLog.setUsername("王五");
		loginLog.setStatus(1);
		loginLog.setInfo("登陆成功！");
		loginLogService.save(loginLog);
	}
}
