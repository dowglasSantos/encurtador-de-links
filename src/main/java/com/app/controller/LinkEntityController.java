package com.app.controller;

import com.app.dto.RequestLinkEntity;
import com.app.dto.ResponseLinkEntity;
import com.app.service.LinkEntityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@RequestMapping("/links")
public class LinkEntityController {
    @Autowired
    LinkEntityService linkEntityService;

    @PostMapping("/create")
    public ResponseEntity<ResponseLinkEntity> createLinkShortened(@RequestBody RequestLinkEntity requestLinkEntity) {
        return ResponseEntity.ok(linkEntityService.createLinkShortened(requestLinkEntity));
    }

    @GetMapping("/redirect/{shortenedLink}")
    public ResponseEntity<Void> redirectBrowser(@PathVariable(name = "shortenedLink") String shortenedLink) {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setLocation(URI.create(linkEntityService.redirectBrowser(shortenedLink)));

        return ResponseEntity
                .status(HttpStatus.FOUND)
                .headers(httpHeaders)
                .build();
    }
}
