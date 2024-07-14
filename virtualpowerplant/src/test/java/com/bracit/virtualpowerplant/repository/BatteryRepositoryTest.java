package com.bracit.virtualpowerplant.repository;

import com.bracit.virtualpowerplant.model.Battery;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;
import static org.assertj.core.api.Assertions.assertThat;
@DataJpaTest
public class BatteryRepositoryTest {

    @Autowired
    private BatteryRepository batteryRepository;

    @Test
    public void testFindByPostcodeBetween() {
        Battery battery1 = new Battery();
        battery1.setName("Battery1");
        battery1.setPostcode("12345");
        battery1.setWattCapacity(100);
        batteryRepository.save(battery1);

        Battery battery2 = new Battery();
        battery2.setName("Battery2");
        battery2.setPostcode("23456");
        battery2.setWattCapacity(200);
        batteryRepository.save(battery2);

        Battery battery3 = new Battery();
        battery3.setName("Battery3");
        battery3.setPostcode("34567");
        battery3.setWattCapacity(150);
        batteryRepository.save(battery3);

        List<Battery> batteries = batteryRepository.findByPostcodeBetween("20000", "30000");
        assertThat(batteries).hasSize(1);
        assertThat(batteries.get(0).getName()).isEqualTo("Battery2");
    }

}
