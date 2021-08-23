package com.example.demo.model;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class NotificationEmail {
	private String subject;
	private String recipient;
	private String body;

}
