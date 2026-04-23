package ooo.klae.sample.motocatalog.controllers;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.OptimisticLockingFailureException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import lombok.extern.slf4j.Slf4j;
import ooo.klae.sample.motocatalog.beans.Brand;
import ooo.klae.sample.motocatalog.beans.Motorcycle;
import ooo.klae.sample.motocatalog.beans.SearchForm;

import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import ooo.klae.sample.motocatalog.forms.MotoForm;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import ooo.klae.sample.motocatalog.services.MotosService;

@Controller
@Slf4j
public class MotosController {

    @Autowired
    MotosService service;

    // private static final Logger log =
    // LoggerFactory.getLogger(MotosController.class);

    /**
     * テスト用のコントローラー
     * 
     * @param name
     * @param model
     * @return
     */
    @RequestMapping("/hello")
    public String hello(@RequestParam(name = "name", required = false, defaultValue = "World") String name,
            Model model) {
        model.addAttribute("name", name);
        return "test";
    }

    /**
     * バイク一覧画面に遷移する
     * 
     * @param form
     * @param model
     * @return
     */
    @GetMapping("/motos")
    public String motos(@Validated SearchForm form, BindingResult result, Model model) {
        log.info("呼ばれた {}", form);
        if (result.hasErrors()) {
            // log.info("バリデーションエラー: {}", result.getAllErrors());
            return "moto_list";
        }

        // ブランド
        this.setBrands(model);

        List<Motorcycle> motorcycles = new ArrayList<>();
        // SearchForm condition = new SearchForm();
        motorcycles = service.getMotos(form);

        // model.addAttribute("brands", brands);
        model.addAttribute("motorcycles", motorcycles);
        model.addAttribute("datetime", LocalDateTime.now());

        // log.info("motorcycles: {}", motorcycles);

        return "moto_list";
    }

    /**
     * 検索条件をリセットしてバイク一覧画面に遷移する
     * 
     * @param form
     * @param model
     * @return
     */
    @GetMapping("/motos/reset")
    public String reset(SearchForm form, Model model) {
        // why not just redirect back to /motos without any uri parameters? r we
        // trolling?
        this.setBrands(model);

        form = new SearchForm();
        return "moto_list";
    }

    /**
     * バイクの更新画面に遷移する
     * 
     * @param motoNo   バイク番号
     * @param motoForm バイクの更新用のフォームクラス
     * @param model    モデルクラス
     * @return
     */
    @GetMapping("/motos/{motoNo}")
    public String initUpdate(@PathVariable("motoNo") int motoNo, @ModelAttribute("motoForm") MotoForm motoForm,
            Model model) {
        this.setBrands(model);

        Motorcycle motorcycle = service.getMotos(motoNo);
        // model.addAttribute("motorcycle", motorcycle);
        BeanUtils.copyProperties(motorcycle, motoForm);

        return "moto";
    }

    /**
     * バイクの登録
     * 
     * @param motoNo   バイク番号
     * @param motoForm バイクの登録用のフォームクラス
     * @param model    モデルクラス
     * @return
     */
    @GetMapping("/motos/new")
    public String initNew(@ModelAttribute("motoForm") MotoForm motoForm,
            Model model) {
        this.setBrands(model);
        
        return "moto";
    }

    @PostMapping("/motos/save")
    public String save(@ModelAttribute("motoForm") MotoForm motoForm, BindingResult result, Model model) {
        try {
            log.info("保存する {}", motoForm);
            Motorcycle motorcycle = new Motorcycle();
            BeanUtils.copyProperties(motoForm, motorcycle);
            motorcycle.setBrandId(new Brand(motoForm.getBrandId(), null));
            int cnt = service.save(motorcycle);

            log.info("保存件数: {}", cnt);

            return "redirect:/motos";
        } catch (OptimisticLockingFailureException e) {
            this.setBrands(model);
            result.addError(new ObjectError("global", e.getMessage()));
            return "moto";
        }

    }

    /**
     * ブランドのリストを取得してモデルにセットする
     * 
     * @param model
     */
    private void setBrands(Model model) {
        List<Brand> brands = new ArrayList<>();
        brands = service.getBrands();
        model.addAttribute("brands", brands);
    }
}
