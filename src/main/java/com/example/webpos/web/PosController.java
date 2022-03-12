package com.example.webpos.web;

import com.example.webpos.biz.PosService;
import com.example.webpos.model.Item;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class PosController {

    private PosService posService;

    @Autowired
    public void setPosService(PosService posService) {
        this.posService = posService;
    }

    @GetMapping({"/", "/index.html"})
    public String indexPage(Model model) {
        model.addAttribute("products", posService.products());
        model.addAttribute("cart", posService.getCart());
        return "index";
    }

    @GetMapping("/charge-success.html")
    public String chargeSuccessPage() {
        return "charge-success";
    }

    @GetMapping("/save/{productId}")
    public ModelAndView addProduct(@PathVariable("productId") String productId) {
        posService.save(productId);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("redirect:/index.html");
        return modelAndView;
    }

    @GetMapping("/update/incr/{productId}")
    public ModelAndView updateItemIncrement(@PathVariable("productId") String productId) {
        posService.updateIncrement(productId);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("redirect:/index.html");
        return modelAndView;
    }

    @GetMapping("/update/decr/{productId}")
    public ModelAndView updateItemDecrease(@PathVariable("productId") String productId) {
        posService.updateDecrease(productId);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("redirect:/index.html");
        return modelAndView;
    }

    @GetMapping("/delete/{productId}")
    public ModelAndView deleteItem(@PathVariable("productId") String productId) {
        posService.remove(productId);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("redirect:/index.html");
        return modelAndView;
    }

    @GetMapping("/charge")
    public ModelAndView charge() {
        posService.emptyCart();
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("redirect:/charge-success.html");
        return modelAndView;
    }

    @GetMapping("/cancel")
    public ModelAndView cancel() {
        posService.emptyCart();
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("redirect:/index.html");
        return modelAndView;
    }
}
