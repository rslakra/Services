package com.rslakra.springservices.thymeleafsidebar.setting.controller;

import com.rslakra.springservices.thymeleafsidebar.framework.controller.AbstractController;
import com.rslakra.springservices.thymeleafsidebar.setting.service.ProfileService;
import com.rslakra.springservices.thymeleafsidebar.setting.service.SettingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;

@Controller
@RequestMapping("/settings")
public class SettingController extends AbstractController {

    private final SettingService settingService;
    private final ProfileService profileService;

    /**
     * @param settingService
     * @param profileService
     */
    @Autowired
    public SettingController(final SettingService settingService, final ProfileService profileService) {
        this.settingService = settingService;
        this.profileService = profileService;
    }

    /**
     * @param principal
     * @return
     */
    @GetMapping
    public String accountSettingsIndex(Principal principal) {
//        return (Objects.nonNull(principal) ? "home/signedIn" : "home/notSignedIn");
        return "setting/index";
    }

}
