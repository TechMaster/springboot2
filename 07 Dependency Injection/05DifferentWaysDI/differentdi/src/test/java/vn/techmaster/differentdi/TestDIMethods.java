package vn.techmaster.differentdi;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;

import vn.techmaster.differentdi.component.Bar;
import vn.techmaster.differentdi.component.Computer;
import vn.techmaster.differentdi.component.Foo;
import vn.techmaster.differentdi.component.FujitsuHDD;
import vn.techmaster.differentdi.component.InverterPower;
import vn.techmaster.differentdi.component.Keyboard;
import vn.techmaster.differentdi.component.Mouse;
import vn.techmaster.differentdi.component.SamsungRAM;
import vn.techmaster.differentdi.component.WebCam;
import vn.techmaster.differentdi.interfaces.USB;

import static org.assertj.core.api.Assertions.*;

import java.util.List;

@SpringBootTest
class TestDIMethods {
	@Autowired private Computer pc;
	@Autowired private ApplicationContext context;
	@Autowired private Foo foo;
	@Autowired private Bar bar;
	@Test
	void testRAM() {
		var ram = pc.getRam();
		assertThat(ram).isInstanceOf(SamsungRAM.class);
	}

	@Test
	void testHDD() {
		var hdd = pc.getHdd();
		assertThat(hdd).isInstanceOf(FujitsuHDD.class);
	}

	@Test
	void testUSB() {
		List<USB> usbDevices = pc.getUsbDevices();
		USB keyboard = (USB) context.getBean("keyboard");
		USB mouse = (USB) context.getBean("mouse");
		assertThat(usbDevices).contains(keyboard, mouse);
	}

	@Test
	void testCollectionInject() {
		List<USB> usbDevices2 = pc.getUsbDevices2();
		assertThat(usbDevices2.get(0)).isInstanceOf(Mouse.class);
		assertThat(usbDevices2.get(1)).isInstanceOf(WebCam.class);
		assertThat(usbDevices2.get(2)).isInstanceOf(Keyboard.class);
	}

	@Test
	void testPSU() {
		var psu = pc.getPsu();
		assertThat(psu).isInstanceOf(InverterPower.class);
	}

	@Test
	void testRandomizer() {		
 		assertThat(foo.randomizer).isNotSameAs(bar.randomizer);
	}

	@Test
	void testValueModel() {		
 		assertThat(pc.getModel()).isEqualTo("Alienware");
	}

}
