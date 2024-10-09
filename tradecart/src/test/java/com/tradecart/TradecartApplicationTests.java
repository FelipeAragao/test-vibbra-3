package com.tradecart;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@ActiveProfiles(profiles = "h2")
@SpringBootTest
class TradecartApplicationTests {

    @Test
    void contextLoads() {
    }

}
