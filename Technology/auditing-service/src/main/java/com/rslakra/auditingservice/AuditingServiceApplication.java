package com.rslakra.auditingservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

//@EnableJpaAuditing
@SpringBootApplication
@EnableJpaRepositories
public class AuditingServiceApplication {
//public class AuditingServiceApplication implements CommandLineRunner {

//    @Resource
//    private FileRepository fileRepository;

    /**
     * @param args
     */
    public static void main(String[] args) {
        SpringApplication.run(AuditingServiceApplication.class, args);
    }

//    /**
//     * @param args
//     * @throws Exception
//     */
//    @Override
//    public void run(String... args) throws Exception {
//        fileRepository.saveAndFlush(new File("Spring-Boot-File", "Spring Boot JPA is awesome"));
//        fileRepository.saveAndFlush(new File("Java-File", "Java file system is awesome"));
//    }
}
