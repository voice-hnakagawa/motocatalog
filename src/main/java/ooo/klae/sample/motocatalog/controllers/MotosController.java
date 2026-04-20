package ooo.klae.sample.motocatalog.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import ooo.klae.sample.bean.Brand;
import ooo.klae.sample.bean.Motorcycle;

import java.util.ArrayList;
import java.util.List;

@Controller
public class MotosController {

    @RequestMapping("/hello")
    public String hello(@RequestParam(name="name", required=false, defaultValue="World") String name, Model model) {
        model.addAttribute("name", name);
        return "test";
    }

    @GetMapping("/motos")
    public String motos(Model model) {
        // ブランド
        List<Brand> brands = new ArrayList<>();
        brands.add(new Brand(1, "Yamaha"));
        brands.add(new Brand(2, "Honda"));
        brands.add(new Brand(3, "Suzuki"));
        brands.add(new Brand(4, "Kawasaki"));

        List<Motorcycle> motorcycles = new ArrayList<>();
        motorcycles.add(new Motorcycle(1, "YZF-R1", 850, 4, 1, 1500000, "A high-performance sport bike.", "Yamaha", 1, null));
        motorcycles.add(new Motorcycle(2, "CBR1000RR", 820, 4, 1, 1400000, "A powerful and agile sport bike.", "Honda", 1, null));
        motorcycles.add(new Motorcycle(3, "GSX-R1000", 810, 4, 1, 1300000, "A lightweight and fast sport bike.", "Suzuki", 1, null));
        motorcycles.add(new Motorcycle(4, "Ninja ZX-10R", 830, 4, 1, 1600000, "A cutting-edge sport bike with advanced technology.", "Kawasaki", 1, null));

        model.addAttribute("brands", brands);
        model.addAttribute("motorcycles", motorcycles);

        return "moto_list";
    }
}
