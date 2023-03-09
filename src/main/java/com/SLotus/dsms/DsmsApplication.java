package com.SLotus.dsms;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

@SpringBootApplication
@MapperScan("com.SLotus.dsms.mapper")
@ServletComponentScan("com.SLotus.dsms.filter")
public class DsmsApplication {

    public static void main(String[] args) {
        SpringApplication.run(DsmsApplication.class, args);
    }

}
