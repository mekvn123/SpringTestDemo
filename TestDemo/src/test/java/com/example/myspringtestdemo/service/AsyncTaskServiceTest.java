package com.example.myspringtestdemo.service;

import com.example.myspringtestdemo.UnitTest;
import com.example.myspringtestdemo.application.AsyncTaskService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;

@Slf4j
public class AsyncTaskServiceTest extends UnitTest {
    @InjectMocks
    private AsyncTaskService asyncTaskService;

    @Test
    public void threadTest() {
        for (int i = 0; i < 20; i++) {
            asyncTaskService.executeAsyncTask(i);
        }
    }

    @Test
    void test() {
        asyncTaskService.test();
    }
}
