package com.helmes.spring.controller;

import com.helmes.spring.model.Type;
import com.helmes.spring.service.TypeService;
import com.helmes.spring.util.PaginationResult;
import com.helmes.spring.validator.PolicyValidator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import com.helmes.spring.model.Policy;
import com.helmes.spring.service.PolicyService;

import javax.validation.Valid;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.UUID;

//You can add your name and date here
@Controller
public class PolicyController {
    private static final int FIRST_PAGE_INDEX = 1;
    private static final Logger logger = LoggerFactory.getLogger(PolicyController.class);
    private final int MAX_RESULT = 5;
    private final int MAX_NAVIGATION_PAGE = 10;
    @Autowired
    private PolicyService policyService;

    @Autowired
    private TypeService typeService;

    @Autowired
    private PolicyValidator policyValidator;

    @RequestMapping(value = {"/policys"}, method = RequestMethod.GET)
    public String listPolicys(@RequestParam(value = "page", defaultValue = "1" , required = false) String pageStr,Model model) {
        model.addAttribute("policy", new Policy());
        fillPolicies(model, pageStr);
        //    List<Policy> list = this.policyService.listPolicys(page, MAX_RESULT, MAX_NAVIGATION_PAGE);
        return "policy";
    }
    @RequestMapping(value = "/policy/find")
    public String findPolicys(@ModelAttribute("policy") Policy p, @RequestParam(value = "pricef", defaultValue = "0.0", required = false)  String priceStr, Model model) {
        BigDecimal pricef = new BigDecimal(0.0);
        try {
            pricef = BigDecimal.valueOf(Double.parseDouble(priceStr));
        } catch (Exception e) {
            logger.info("BigDecimal.valueOf had not been successfully, Exception Details="+priceStr);
        }
        List<Policy> list = policyService.findPolicys(pricef, p.getTypef(), p.getActivef());
        model.addAttribute("policy", new Policy());

       // fillPolicies(model, priceStr);
        return "policy";
    }
    //For add and update policy both
    @RequestMapping(value= "/policy/add", method = RequestMethod.POST)
    public String addPolicy(@ModelAttribute("policy") @Valid Policy p, BindingResult result,@RequestParam(value = "page", defaultValue = "1", required = false)  String pageStr, Model model){
        policyValidator.validate(p, result);
        if (result.hasErrors()) {
            fillPolicies(model, pageStr);
            return "policy";
        }
        if(p.getId()==null){
            //new policy, add it
            policyService.addPolicy(p);
        }else{
            //existing policy, call update
            policyService.updatePolicy(p);
        }
        return "redirect:/policys";
    }

    @RequestMapping("/remove/{id}")
    public String removePolicy(@PathVariable("id") UUID id){

        policyService.removePolicy(id);
        return "redirect:/policys";
    }

    @RequestMapping("/edit/{id}")
    public String editPolicy(@PathVariable("id") UUID id,@RequestParam(value = "page", defaultValue = "1", required = false)  String pageStr, Model model){
        Policy p = policyService.getPolicyById(id);
        model.addAttribute("policy", p);
        fillPolicies(model, pageStr);
        return "policy";
    }

    private void fillPolicies(Model model, String pageStr){
        int page = FIRST_PAGE_INDEX;
        try {
            page = Integer.parseInt(pageStr);
        } catch (Exception e) {
            logger.info("ParseInt(page) had not been successfully, Exception Details="+pageStr);
        }
        PaginationResult<Policy> paginationResult //
                = policyService.listPolicys(page, MAX_RESULT, MAX_NAVIGATION_PAGE);
        model.addAttribute("paginationResult", paginationResult);

        model.addAttribute("listPolicys", paginationResult.getList());
        model.addAttribute("typeList", typeService.getAllTypes());
    }
    @RequestMapping(value = {"/"}, method = RequestMethod.GET)
    public String home(Model model) {
        return "redirect:/policys";
    }
}