package com.digi9.NotificationServiceApp.controller;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.digi9.NotificationServiceApp.controller.service.JwtUtil;
import com.digi9.NotificationServiceApp.controller.service.RegisterService;
import com.digi9.NotificationServiceApp.controller.service.UserLoginService;
import com.digi9.NotificationServiceApp.notificationsender.FcmTokenTemp;
import com.digi9.NotificationServiceApp.notificationsender.NotiReq;
import com.digi9.NotificationServiceApp.notificationsender.NotificationServiceOneTime;
import com.digi9.NotificationServiceApp.pojo.LoginRequest;
import com.digi9.NotificationServiceApp.pojo.NotificationRequest;
import com.digi9.NotificationServiceApp.pojo.ProjectRequest;
import com.digi9.NotificationServiceApp.pojo.RegiterRequest;

@RestController
@RequestMapping("/api")
public class UserController {
	
	@Autowired
	RegisterService registerService;
	
	@Autowired
	UserLoginService userLoginService;
	@Autowired
	FcmTokenTemp fcmTokenTemp;
	
	 @Autowired
	 NotificationServiceOneTime notificationService;
	
	 @Autowired
	    private JdbcTemplate jdbcTemplate;
	  @Autowired
	    private JwtUtil jwtUtil;
	
	  
	@PostMapping("/register")	
	public String register(@RequestBody RegiterRequest req) {
		
		System.out.println("user registeration successful" +req );
		
		registerService.registerUser(req);
		
		return "user registeration successful" ;
	}

	/*
	 * @PostMapping("/login") public String login(@RequestBody LoginRequest request)
	 * { return userLoginService.loginUser(request); }
	 */
	
	
	   @PostMapping("/login")
	    public ResponseEntity<Map<String, String>> login(@RequestBody LoginRequest request) {
	        String token = userLoginService.loginUser(request);
	        if (token != null) {
	            Map<String, String> response = new HashMap<>();
	            response.put("token", token);
	            return ResponseEntity.ok(response);
	        } else {
	            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
	                                 .body(Collections.singletonMap("error", "Invalid Credentials"));
	        }
	    }
	   
	   
	   
	   @PostMapping("/add")
	    public String addProject(@RequestHeader("Authorization") String token, @RequestBody ProjectRequest request) {
		
		   
		   System.out.println("Token "+token +"ProjectRequest "+request);
	        return "receive successfully";
	        		//ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid token");
	    }
	   
	   
	   @GetMapping("/project-ids")
	    public List<String> addProject(@RequestHeader("Authorization") String token) {
	        System.out.println("Token: " + token);
	        
	        // Dummy project IDs
	        return Arrays.asList("ecommercenotifiaction");
	    }
	   

	   @GetMapping("/user-groups")
	    public List<String> adduser(@RequestHeader("Authorization") String token) {
	        System.out.println("Token: " + token);
	        
	        // Dummy project IDs
	        return Arrays.asList("ALL");
	    }
	   
	   
	   @PostMapping("/send")
	   public ResponseEntity<String> addUser2(@RequestHeader("Authorization") String token, 
	                                         @RequestBody NotificationRequest req) {
	       try {
	           System.out.println("Token: " + token + " Received request: " + req);

	           // Extract token without 'Bearer ' prefix
	           String jwt = token.replace("Bearer ", "");

	           // Parse JWT to extract email
	           String email = jwtUtil.parseToken(jwt).getSubject();

	           // Query database to get user_id using email
	           String sql = "SELECT user_id FROM users WHERE email = ?";
	           String userId;

	           try {
	               var result = jdbcTemplate.queryForMap(sql, email);
	               userId = String.valueOf(result.get("user_id"));
	           } catch (EmptyResultDataAccessException e) {
	               return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found for email: " + email);
	           }

	           // If userGroup is "ALL", fetch all FCM tokens
	           if ("ALL".equalsIgnoreCase(req.getUserGroup())) {
	               List<String> tokens = fcmTokenTemp.fcmT();

	               NotiReq n = new NotiReq();
	               n.setTitle(req.getTitle());
	               n.setBody(req.getMessage());
	               n.setFcmTokens(tokens);
	               n.setImageUrl(req.getImageUrl());
	               n.setProjectId(req.getProjectId());

	               System.out.println("sending request to "+n);
	               
	               // Send notification
	               notificationService.sendNotification(userId, n);
	           }

	           return ResponseEntity.ok("Notification sent successfully!");

	       } catch (Exception e) {
	           return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
	                   .body("Failed to send notification: " + e.getMessage());
	       }
	   }

}