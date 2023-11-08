package com.openarknightsjvav;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.boot.Banner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.env.Environment;

import java.net.InetAddress;
import java.net.UnknownHostException;

@SpringBootApplication
@Slf4j
public class OpenarknightsjvavApplication {

    public static void main(String[] args) throws UnknownHostException {
        ConfigurableApplicationContext application = SpringApplication.run(OpenarknightsjvavApplication.class, args);
        System.out.println("..######..##.....##..######...######..########..######...######.\n" +
                           ".##....##.##.....##.##....##.##....##.##.......##....##.##....##\n" +
                           ".##.......##.....##.##.......##.......##.......##.......##......\n" +
                           "..######..##.....##.##.......##.......######....######...######.\n" +
                           ".......##.##.....##.##.......##.......##.............##.......##\n" +
                           ".##....##.##.....##.##....##.##....##.##.......##....##.##....##\n" +
                           "..######...#######...######...######..########..######...######.");
        Environment env = application.getEnvironment();
        String port = env.getProperty("server.port");
        String property = env.getProperty("server.servlet.context-path");
        String path = property == null ? "" :  property;
        System.out.println(
                "\n" +
                        "----------------------------------------------------------------\n" +
                        "\t\tOpenDoctorateJvav v1.0 is running! \n" +
                        "\t\tAccess URLs: http://127.0.0.1:" + port + path + "/\n" +
                        "----------------------------------------------------------------");
        System.out.println("Press CTRL + C to exit");
    }

}
