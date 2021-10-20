package com.example.myspringtestdemo.application;

import com.example.myspringtestdemo.application.dto.UserDto;
import com.example.myspringtestdemo.application.dto.UserWithPostDto;
import com.example.myspringtestdemo.application.dto.mapper.DtoToEntity;
import com.example.myspringtestdemo.application.dto.mapper.EntityToDto;
import com.example.myspringtestdemo.client.FeignTest;
import com.example.myspringtestdemo.domain.UserRepository;
import com.example.myspringtestdemo.unit.ExcelHelper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import reactor.core.publisher.Mono;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

import static com.google.common.collect.Lists.newArrayList;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final FeignTest feignClient;

    public UserDto getUser(String userid) {
        return EntityToDto.INSTANCE.clone(userRepository.findById(userid));
    }

    public UserWithPostDto getUserAndPost(String id) {
        return EntityToDto.INSTANCE.clone(userRepository.findUserPostById(id));
    }

    public void saveUsers(List<UserDto> users) {
        userRepository.saveUsers(users.stream().map(DtoToEntity.INSTANCE::clone).collect(Collectors.toList()));
    }

    public void saveUsersAsync(List<UserDto> users) {
        userRepository.saveUsersAsync(users.stream().map(DtoToEntity.INSTANCE::clone).collect(Collectors.toList()));
    }

    public void getExcel(String userid, HttpServletResponse response) {
        response.setCharacterEncoding("utf-8");
        response.setHeader("Content-disposition", "attachment;filename*=utf-8''" + "test_demo" + ".xlsx");
        response.setHeader("attachment", "test_demo" + ".xlsx");
        try {
            ServletOutputStream outputStream = response.getOutputStream();
            ByteArrayInputStream byteArrayInputStream = ExcelHelper.UserToExcel(newArrayList(getUser(userid)));
            byte[] array = new byte[byteArrayInputStream.available()];
            byteArrayInputStream.read(array);
            outputStream.write(array);
        } catch (IOException e) {
            throw new RuntimeException("fail to put excel data: " + e.getMessage());
        }
    }

    public void saveFromExcel(MultipartFile file) {
        List<UserDto> userDtos = newArrayList();
        try {
            userDtos = ExcelHelper.excelToUser(file.getInputStream());
        } catch (IOException e) {
            throw new RuntimeException("fail to store excel data: " + e.getMessage());
        } finally {
            saveUsers(userDtos);
        }
    }

    public java.io.ByteArrayInputStream loadUser(String id) {
        return ExcelHelper.UserToExcel(newArrayList(getUser(id)));
    }

    public Mono<UserDto> getUser2(String userId) {
        return userRepository.findById2(userId);
    }

    public String testFeignClient(String code) {
       return feignClient.encode(code);
        //return "s";
    }
}

