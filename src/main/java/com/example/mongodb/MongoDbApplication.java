package com.example.mongodb;

import lombok.NonNull;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.core.convert.MongoCustomConversions;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

import javax.swing.*;
import java.awt.*;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.time.LocalDate;
import java.time.ZoneOffset;
import java.util.Date;
import java.util.List;

@SpringBootApplication
@EnableMongoRepositories
@Configuration
public class MongoDbApplication /* implements CommandLineRunner */{

	public static void main(String[] args) {
		SpringApplication.run(MongoDbApplication.class, args);
//		new SpringApplicationBuilder(MongoDbApplication.class).headless(false).run(args);

	}

//	public static void main(String[] args) {

//		LocalDate localDate=LocalDate.now();
//		StringBuilder endMonth= new StringBuilder().append(localDate.getYear()).append(localDate.getMonth()).append("01");
//
//		System.out.println( );
//		System.out.println(LocalDate.parse("2022-12"));

//		double nextAmount=Double.parseDouble(new DecimalFormat("##.##").format(Math.random()*100));
//		double taxAmount=Double.parseDouble(new DecimalFormat("#.##").format(Math.random()*10));
//		System.out.println(nextAmount);
//		System.out.println(taxAmount);
//		System.out.println(new DecimalFormat("###.##").format(nextAmount+taxAmount));
//	}

//
//	@Bean
//	public MongoCustomConversions mongoCustomConversions() {
//		// we use exact UTC date pointing to 00:00 of given day to store LocalDate
//		return new MongoCustomConversions(List.of(
//
//				new Converter<LocalDate, Date>() {
//					@Override
//					public Date convert(@NonNull LocalDate source) {
//						return new Date(source.atStartOfDay().atZone(ZoneOffset.UTC).toInstant().toEpochMilli());
//					}
//				},
//
//				new Converter<Date, LocalDate>() {
//					@Override
//					public LocalDate convert(@NonNull Date source) {
//						return source.toInstant().atZone(ZoneOffset.UTC).toLocalDate();
//					}
//				}
//
//		));
//	}

//	@Override
//	public void run(String... args) {
//		JFrame frame = new JFrame("Spring Boot Swing App");
//		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//		frame.setSize(300,300);
//		JPanel panel = new JPanel(new BorderLayout());
//		JTextField text = new JTextField("Spring Boot can be used with Swing apps");
//		panel.add(text, BorderLayout.CENTER);
//		frame.setContentPane(panel);
//		frame.setVisible(true);
//	}
}
