package com.sps.covid.tracker.controller;

import com.sps.covid.tracker.services.EntityService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
public class StateWiseCovidStatsController {

    final EntityService entityService;

    public StateWiseCovidStatsController(final EntityService entityService) {
        this.entityService = entityService;
    }

    @RequestMapping(value = "/stateWiseData", method = RequestMethod.GET)
    public String messages(final Model model, final HttpServletRequest request) {
        if (request.getCookies() != null) {
            for (int i = 0; i < request.getCookies().length; i++) {
                if ("login".equals(request.getCookies()[i].getName())
                        && "true".equals(request.getCookies()[i].getValue())) {
                    model.addAttribute("stateWiseData", entityService.getStateWiseCoronaData());
                    return "home";
                }
            }
        }
        return "login";
    }
}