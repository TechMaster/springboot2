package vn.techmaster.demobean;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import vn.techmaster.demobean.bean.Car;
import vn.techmaster.demobean.bean.ElectricEngine;
import vn.techmaster.demobean.bean.GasEngine;
import vn.techmaster.demobean.bean.HybridEngine;

import static org.assertj.core.api.Assertions.*;
@SpringBootTest
class CarTest {
	@Autowired private Car car;
	
	@Test
	void HybridCar() {		
		var engine = car.getEngine();
		assertThat(engine).isInstanceOf(HybridEngine.class);
	}
}
