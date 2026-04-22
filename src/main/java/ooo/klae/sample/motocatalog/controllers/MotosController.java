package ooo.klae.sample.motocatalog.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import lombok.extern.slf4j.Slf4j;
import ooo.klae.sample.motocatalog.beans.Brand;
import ooo.klae.sample.motocatalog.beans.Motorcycle;
import ooo.klae.sample.motocatalog.beans.SearchCondition;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.ArrayList;
import java.util.List;

import ooo.klae.sample.motocatalog.services.MotosService;

@Controller
@Slf4j
public class MotosController {

    @Autowired
    MotosService service;

    // private static final Logger log = LoggerFactory.getLogger(MotosController.class);

    @RequestMapping("/hello")
    public String hello(@RequestParam(name="name", required=false, defaultValue="World") String name, Model model) {
        model.addAttribute("name", name);
        return "test";
    }

    @GetMapping("/motos")
    public String motos(Model model) {
        // ブランド
        List<Brand> brands = new ArrayList<>();
        brands = service.getBrands();

        List<Motorcycle> motorcycles = new ArrayList<>();
        SearchCondition condition = new SearchCondition();
        motorcycles = service.getMotos(condition);

        model.addAttribute("brands", brands);
        model.addAttribute("motorcycles", motorcycles);

        log.info("motorcycles: {}", motorcycles);

        return "moto_list";
    }
}
