package com.elyadata.sm.controller;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class InfoResources {

    @GetMapping("/status")
    public ResponseEntity<ServerStatus> info() {
        ServerStatus serverStatus = ServerStatus
                .builder()
                .status("UP")
                .build();
        return ResponseEntity.ok(serverStatus);
    }
}

@Builder
@Getter
@Setter
class ServerStatus {
    private String status;
}
