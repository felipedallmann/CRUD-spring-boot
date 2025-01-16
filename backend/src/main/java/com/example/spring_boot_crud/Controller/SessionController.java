package com.example.spring_boot_crud.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.servlet.http.HttpSession;

@RestController
@RequestMapping("/session")
public class SessionController {

    @GetMapping("/create")
    public ResponseEntity<String> createSession(HttpSession session) {
        String sessionId = session.getId();
        String username = "exampleUser_" + sessionId;
        session.setAttribute("user", username );
        return new ResponseEntity<>("Session created with ID: " + session.getId(), HttpStatus.CREATED);
    }

    @GetMapping("/get")
    public ResponseEntity<String> getSession(HttpSession session) {
        Object user = session.getAttribute("user");
        if (user == null) {
            return new ResponseEntity<>("No session found", HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>("Session data: " + user, HttpStatus.OK);
    }

    @GetMapping("/invalidate")
    public ResponseEntity<String> invalidateSession(HttpSession session) {
        session.invalidate();
        return new ResponseEntity<>("Session invalidated", HttpStatus.OK);
    }
}