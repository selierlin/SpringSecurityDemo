package com.imooc.web.controller;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserControllerTest {

    @Autowired
    private WebApplicationContext wac;

    private MockMvc mockMvc;

    @Before
    public void setup() {
        mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();
    }

    /**
     * 模拟GET请求 /User
     *
     * @throws Exception
     */
    @Test
    public void whenQuerySuccess() throws Exception {
        String result = mockMvc.perform(
                MockMvcRequestBuilders.get("/user")//模拟请求/user地址
                        //UserQueryCondition 相关参数：
                        .param("username", "传入的username")
                        .param("age", "22")
                        .param("ageTo", "30")
                        //Pageable相关参数：
                        .param("size", "15").param("page", "3")
                        .param("sort", "age,desc").param("sort", "username,asc")
                        .contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(MockMvcResultMatchers.status().isOk())//期望结果返回200
                .andExpect(MockMvcResultMatchers.jsonPath("$.length()").value(3))//期望返回结果的数组长度为3
                .andReturn().getResponse().getContentAsString();//将服务器返回的字符串输出
        System.out.println(result);
    }

    @Test
    public void whenGetInfoSuccess() throws Exception {
        String result = mockMvc.perform(MockMvcRequestBuilders.get("/user/1")//模拟请求/user/1地址
                .contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(MockMvcResultMatchers.status().isOk())//期望结果返回200
                .andExpect(MockMvcResultMatchers.jsonPath("$.username").value("tom"))//期望返回的Json中username为tom
                .andReturn().getResponse().getContentAsString();//获取返回值

        System.out.println(result);
    }

    @Test
    public void whenGetInfoFail() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/user/a")
                .contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(MockMvcResultMatchers.status().is4xxClientError());//期望响应的状态码为4xx
    }

}
