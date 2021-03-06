/*
 * Copyright (c) 2019 Jalasoft.
 *
 * This software is the confidential and proprietary information of Jalasoft.
 * ("Confidential Information"). You shall not disclose such Confidential
 * Information and shall use it only in accordance with the terms of the
 * license agreement you entered into with Jalasoft.
 */

package com.jalasoft.jfc.controller;

import com.jalasoft.jfc.Main;
import com.jalasoft.jfc.model.utility.PathJfc;

import org.junit.Test;
import org.junit.Before;
import org.junit.runner.RunWith;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebAppConfiguration
@SpringBootTest(classes = Main.class)
public class UserControllerTest {

    private MockMvc mockMvc;

    @Autowired
    WebApplicationContext wContext;

    @Before
    public void setup() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(wContext).alwaysDo(MockMvcResultHandlers.print()).build();
        PathJfc pathJfc = new PathJfc();
    }

    @Test
    public void loginUser_validUserNameAndPassword_isAccepted202() throws Exception {
        String userName = "userName";
        String password = "password";

        String userNameValue = "control";
        String passwordValue = "Control123";

        mockMvc.perform(post("/api/v1/login/").param(userName, userNameValue).param(password, passwordValue)
        .characterEncoding("UTF-8")).andExpect(status().isAccepted());
    }

    @Test
    public void loginUser_invalidUserNameAndPassword_isUnauthorized401() throws Exception {
        String userName = "userName";
        String password = "password";

        String userNameValue = "invalidUsername";
        String passwordValue = "invalidPassword";

        mockMvc.perform(post("/api/v1/login/").param(userName, userNameValue).param(password, passwordValue)
        .characterEncoding("UTF-8")).andExpect(status().isUnauthorized());
    }

    @Test
    public void addUser_validUserValues_isCreated201() throws Exception {

        String userName = "userName";
        String password = "password";
        String rol = "rol";
        String email = "email";

        String userNameValue = "newUser";
        String passwordValue = "Control123";
        String rolValue = "admin";
        String emailValue = "control@abc.com";

        mockMvc.perform(post("/api/v1/addUser/").param(userName, userNameValue).param(password, passwordValue)
        .param(rol, rolValue).param(email, emailValue).characterEncoding("UTF-8")).andExpect(status().isCreated());
    }

    @Test
    public void addUser_existUserInDatabase_isNotAcceptable406() throws Exception {
        String userName = "userName";
        String password = "password";
        String rol = "rol";
        String email = "email";

        String userNameValue = "control";
        String passwordValue = "Control123";
        String rolValue = "admin";
        String emailValue = "control@abc.com";

        mockMvc.perform(post("/api/v1/addUser/").param(userName, userNameValue).param(password, passwordValue)
        .param(rol, rolValue).param(email, emailValue).characterEncoding("UTF-8")).andExpect(status()
        .isNotAcceptable());
    }

    @Test
    public void updateUser_anExistentUserEntity_ok200() throws Exception {
        String id = "id";
        String userName = "userName";
        String password = "password";
        String rol = "rol";
        String email = "email";

        int idValue = 11;
        String userNameValue = "updateControl1";
        String passwordValue = "updateControl123A";
        String rolValue = "superAdmin";
        String emailValue = "updatecontrol@abc.com";

        mockMvc.perform(put("/api/v1/updateUser/").param(id, String.valueOf(idValue))
        .param(userName, userNameValue).param(password, passwordValue)
        .param(rol, rolValue).param(email, emailValue).characterEncoding("UTF-8")).andExpect(status().isOk());
    }

    @Test
    public void updateUser_anNotExistentUserEntity_isNotModified304() throws Exception {
        String id = "id";
        String userName = "userName";
        String password = "password";
        String rol = "rol";
        String email = "email";

        int idValue = -1;
        String userNameValue = "controlUpdate";
        String passwordValue = "Control123Updated";
        String rolValue = "superAdmin";
        String emailValue = "controlupdated@abc.com";

        mockMvc.perform(put("/api/v1/updateUser/").param(id, String.valueOf(idValue))
        .param(userName, userNameValue).param(password, passwordValue)
        .param(rol, rolValue).param(email, emailValue).characterEncoding("UTF-8")).andExpect(status().isNotModified());
    }

    @Test
    public void findUserById_whenTheIdIsAnValidValue_isFound302() throws Exception {
        String id = "id";
        int idValue = 1;

        mockMvc.perform(get("/api/v1/findById/").param(id, String.valueOf(idValue))
        .characterEncoding("UTF-8")).andExpect(status().isFound());
    }

    @Test
    public void findUserById_whenTheIdIsAnInalidValue_isNotFound302() throws Exception {
        String id = "id";
        int idValue = -1;

        mockMvc.perform(get("/api/v1/findById/").param(id, String.valueOf(idValue))
        .characterEncoding("UTF-8")).andExpect(status().isNotFound());
    }

    @Test
    public void getAllUsers_returnAListOfAllUsers_isOK200() throws Exception {
        mockMvc.perform(get("/api/v1/getAllUsers/")
        .characterEncoding("UTF-8")).andExpect(status().isOk());
    }

    @Test
    public void deleteById_whenIsSentAnValidUserId_isMovedPermanetly301() throws Exception {
        int idValue = 20;
        String idDelete = "id";

        mockMvc.perform(delete("/api/v1/deleteById/").param(idDelete, String.valueOf(idValue))
        .characterEncoding("UTF-8")).andExpect(status().isMovedPermanently());
    }

    @Test
    public void deleteById_whenIsSentAnInvalidUserId_isNotAcceptable406() throws Exception {
        String id = "id";
        int idValue = -1;

        mockMvc.perform(delete("/api/v1/deleteById/").param(id, String.valueOf(idValue))
        .characterEncoding("UTF-8")).andExpect(status().isNotAcceptable());
    }
}
