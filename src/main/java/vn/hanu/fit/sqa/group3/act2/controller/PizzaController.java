package vn.hanu.fit.sqa.group3.act2.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.ui.Model;

import vn.hanu.fit.sqa.group3.act2.model.Pizza;
import vn.hanu.fit.sqa.group3.act2.model.Size;
import vn.hanu.fit.sqa.group3.act2.model.Topping;
import vn.hanu.fit.sqa.group3.act2.repository.PizzaRepository;
import vn.hanu.fit.sqa.group3.act2.repository.SizeRepository;
import vn.hanu.fit.sqa.group3.act2.repository.ToppingRepository;

import java.util.List;
import java.util.Optional;


@Controller
public class PizzaController {

    @GetMapping("/")

    public String showOrderForm() {
        return "OrderForm"; // Thymeleaf template name
    }

    @PostMapping("/confirmOrder")
    public String confirmOrder(@RequestParam int size, @RequestParam int topping, Model model) {

    public String showOrderForm(
        @RequestParam(value = "size",  defaultValue = "0") int size,
        @RequestParam(value = "topping",  defaultValue = "0") int topping,
        Model model
    ) {
        Optional<Size> tempSize = sizeRepository.findByType((size));

        Optional<Topping> tempTop = toppingRepository.findByType((topping));

        List<Size> sizes = sizeRepository.findAll();
        List<Topping> toppings = toppingRepository.findAll();

        String sizeErrors = "";
        String topErrors = "";
        if (tempSize.isEmpty()) {
            sizeErrors = "Size does not exist";
            size = 0;
        } else if ( 1 > tempSize.get().getCounts()) {
            sizeErrors = String.format("There is no size %s left", getSizeText(size));
            size = 0;
        }

        if (tempTop.isEmpty()) {
            topErrors = "Size does not exist";
            topping = 0;
        } else if ( 1 > tempTop.get().getCounts()){
            topErrors = String.format("There is no %s topping left", getToppingText(topping));
            topping = 0;
        }

        Pizza pizza = new Pizza();
        pizza.setSize(size);
        pizza.setTopping(topping);

        model.addAttribute("sizes", sizes);
        model.addAttribute("toppings", toppings);
        model.addAttribute("pizza", pizza);
        model.addAttribute("sizeErrors", sizeErrors);
        model.addAttribute("topErrors", topErrors);

        return "order";
    }

    @Transactional
    @PostMapping("/confirm")
    public String confirmOrder(@RequestParam int size, @RequestParam int topping, Model model) {
        Optional<Size> tempSize = sizeRepository.findByType((size));
        Optional<Topping> tempTop = toppingRepository.findByType((topping));

        if (tempSize.isEmpty() || 1 > tempSize.get().getCounts()) {
            return "redirect:/";
        }

        if (tempTop.isEmpty() || 1 > tempTop.get().getCounts()){
            return "redirect:/";
        }

        Topping currentTop = toppingRepository.findByType(size).get();
        currentTop.setCounts( currentTop.getCounts() - 1);
        toppingRepository.save(currentTop);

        Size currentSize = sizeRepository.findByType(size).get();
        currentSize.setCounts( currentSize.getCounts() - 1);
        sizeRepository.save(currentSize);

        pizzaRepository.save(new Pizza(size, topping));


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


    @Autowired private ToppingRepository toppingRepository;
    @Autowired private SizeRepository sizeRepository;
    @Autowired private PizzaRepository pizzaRepository;
}
