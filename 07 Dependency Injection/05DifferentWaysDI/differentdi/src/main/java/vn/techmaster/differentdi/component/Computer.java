package vn.techmaster.differentdi.component;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.stereotype.Component;

import vn.techmaster.differentdi.interfaces.HardDisk;
import vn.techmaster.differentdi.interfaces.Memory;
import vn.techmaster.differentdi.interfaces.PowerSupply;
import vn.techmaster.differentdi.interfaces.USB;

@Component
@DependsOn({"powersupply"})
@Configuration
public class Computer {
  @Autowired private @Qualifier("hynix") Memory ram;  //Property inject
  @Autowired private PowerSupply psu;  //Property inject
  private HardDisk hdd;
  private List<USB> usbDevices;
  @Autowired private List<USB> usbDevices2;  //Collection inject: tự động thu

  @Value("${model}") //Có thể dùng trong tham số của constructor
  private String model;

  public String getModel() {
    return model;
  }  


  //Constructor inject
  public Computer(@Qualifier("fujitsu") HardDisk hdd) {
    this.hdd = hdd;
  }

  public List<USB> getUsbDevices() {
    return usbDevices;
  }

  //Setter inject
  @Autowired
  public void setUsbDevices(List<USB> usbDevices) {
    this.usbDevices = usbDevices;
  }

  public Memory getRam() {
    return ram;
  }

  public void setRam(Memory ram) {
    this.ram = ram;
  }

  public HardDisk getHdd() {
    return hdd;
  }

  public void setHdd(HardDisk hdd) {
    this.hdd = hdd;
  }

  public PowerSupply getPsu() {
    return psu;
  }

  public void setPsu(PowerSupply psu) {
    this.psu = psu;
  }

  public List<USB> getUsbDevices2() {
    return usbDevices2;
  }

  public void setUsbDevices2(List<USB> usbDevices2) {
    this.usbDevices2 = usbDevices2;
  }
}
