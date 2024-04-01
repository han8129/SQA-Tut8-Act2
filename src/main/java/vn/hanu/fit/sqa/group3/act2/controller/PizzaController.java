package vn.hanu.fit.sqa.group3.act2.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.ui.Model;

@Controller
public class PizzaController {

    @GetMapping("/")
    public String showOrderForm() {
        return "OrderForm"; // Thymeleaf template name
    }

    @PostMapping("/confirmOrder")
    public String confirmOrder(@RequestParam int size, @RequestParam int topping, Model model) {

        String sizeText = getSizeText(size);
        String toppingText = getToppingText(topping);
        String orderSummary = "Order Summary: " + sizeText + " pizza with " + toppingText;
        model.addAttribute("orderSummary", orderSummary);

        return "success"; // Thymeleaf template name for success page
    }

    private String getSizeText(int size) {
        // Convert size value to text (customize as needed)
        String[] sizes = {"Small", "Medium", "Large"};
        return sizes[size];
    }

    private String getToppingText(int topping) {
        // Convert topping value to text (customize as needed)
        String[] toppings = {"None", "Pepperoni", "Mushroom", "Seafood"};
        return toppings[topping];
    }
}
