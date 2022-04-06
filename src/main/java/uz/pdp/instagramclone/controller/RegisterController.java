package uz.pdp.instagramclone.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import uz.pdp.instagramclone.entity.Registration;
import uz.pdp.instagramclone.payload.ApiResponse;
import uz.pdp.instagramclone.service.UserService;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/register")
public class RegisterController {

    final UserService userService;

    @PostMapping
    public HttpEntity<?> addUser(@RequestBody Registration registration){
        ApiResponse apiResponse = userService.addUser(registration);
        return ResponseEntity.status(apiResponse.isSuccess() ? HttpStatus.CREATED : HttpStatus.CONFLICT).body(apiResponse);
    }
}
