package com.cafein.controller;

import com.cafein.dto.user.signin.SignInOutput;
import com.cafein.response.Response;
import com.cafein.service.social.OauthService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
@Slf4j
public class OauthController {

    private final OauthService oauthService;

    @GetMapping("/{type}")
    public void socialLogin(@PathVariable("type") String type) {
        log.info("[GET] /auth/" + type);
        oauthService.request(type);
    }

    @GetMapping("/{type}/callback")
    public Response<SignInOutput> socialLoginCallback(@PathVariable("type") String type, @RequestParam("code") String code) {
        log.info("[GET] /auth/" + type + "/callback");
        return oauthService.requestLogin(type, code);
    }
}
