package com.big;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScans;

/**
 * 大事件系统启动类
 *
 */
@SpringBootApplication
public class BigEventAdmin
{
    public static void main( String[] args ) {
        SpringApplication.run(BigEventAdmin.class, args);
    }
}
