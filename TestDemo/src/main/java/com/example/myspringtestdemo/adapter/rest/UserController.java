package com.example.myspringtestdemo.adapter.rest;

import com.example.myspringtestdemo.application.UserService;
import com.example.myspringtestdemo.application.dto.UserDto;
import com.example.myspringtestdemo.application.dto.UserWithPostDto;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import reactor.core.publisher.Mono;
import springfox.documentation.annotations.ApiIgnore;

import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping(path = "/test_demo")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @GetMapping("/{userid}")
    public UserDto getUser(@PathVariable String userid) {
        return userService.getUser(userid);
    }

    @GetMapping("/full/{userid}")
    public UserWithPostDto getFullUser(@PathVariable String userid) {
        return userService.getUserAndPost(userid);
    }

    @GetMapping("/excel/1/{userid}")
    public void getExcel(@PathVariable String userid, @ApiIgnore HttpServletResponse response) {
        userService.getExcel(userid, response);
    }

    @GetMapping("/mono/{userid}")
    public Mono<UserDto> getUser2(@PathVariable String userid) {
        return userService.getUser2(userid);
    }

    @PostMapping("/excel")
    public void saveFromExcel(@RequestParam("file") MultipartFile file) {
        userService.saveFromExcel(file);
    }

    @GetMapping("/excel/{userid}")
    public ResponseEntity<Resource> getUserExcel(@PathVariable String userid) {
        String filename = "userinfo.xlsx";
        InputStreamResource file = new InputStreamResource(userService.loadUser(userid));
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + filename)
                .contentType(MediaType.parseMediaType("application/vnd.ms-excel"))
                .body(file);
    }

    @GetMapping("/feignclient")
    public String test_feignClient(@RequestParam String code) {
        return userService.testFeignClient(code);
    }
}
