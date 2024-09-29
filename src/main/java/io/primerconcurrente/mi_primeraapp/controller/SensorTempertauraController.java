package io.primerconcurrente.mi_primeraapp.controller;

import io.primerconcurrente.mi_primeraapp.model.SensorTempertauraDTO;
import io.primerconcurrente.mi_primeraapp.service.SensorTempertauraService;
import io.primerconcurrente.mi_primeraapp.util.WebUtils;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


@Controller
@RequestMapping("/sensorTempertauras")
public class SensorTempertauraController {

    private final SensorTempertauraService sensorTempertauraService;

    public SensorTempertauraController(final SensorTempertauraService sensorTempertauraService) {
        this.sensorTempertauraService = sensorTempertauraService;
    }

    @GetMapping
    public String list(final Model model) {
        model.addAttribute("sensorTempertauras", sensorTempertauraService.findAll());
        return "sensorTempertaura/list";
    }

    @GetMapping("/add")
    public String add(
            @ModelAttribute("sensorTempertaura") final SensorTempertauraDTO sensorTempertauraDTO) {
        return "sensorTempertaura/add";
    }

    @PostMapping("/add")
    public String add(
            @ModelAttribute("sensorTempertaura") @Valid final SensorTempertauraDTO sensorTempertauraDTO,
            final BindingResult bindingResult, final RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            return "sensorTempertaura/add";
        }
        sensorTempertauraService.create(sensorTempertauraDTO);
        redirectAttributes.addFlashAttribute(WebUtils.MSG_SUCCESS, WebUtils.getMessage("sensorTempertaura.create.success"));
        return "redirect:/sensorTempertauras";
    }

    @GetMapping("/edit/{id}")
    public String edit(@PathVariable(name = "id") final Long id, final Model model) {
        model.addAttribute("sensorTempertaura", sensorTempertauraService.get(id));
        return "sensorTempertaura/edit";
    }

    @PostMapping("/edit/{id}")
    public String edit(@PathVariable(name = "id") final Long id,
            @ModelAttribute("sensorTempertaura") @Valid final SensorTempertauraDTO sensorTempertauraDTO,
            final BindingResult bindingResult, final RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            return "sensorTempertaura/edit";
        }
        sensorTempertauraService.update(id, sensorTempertauraDTO);
        redirectAttributes.addFlashAttribute(WebUtils.MSG_SUCCESS, WebUtils.getMessage("sensorTempertaura.update.success"));
        return "redirect:/sensorTempertauras";
    }

    @PostMapping("/delete/{id}")
    public String delete(@PathVariable(name = "id") final Long id,
            final RedirectAttributes redirectAttributes) {
        sensorTempertauraService.delete(id);
        redirectAttributes.addFlashAttribute(WebUtils.MSG_INFO, WebUtils.getMessage("sensorTempertaura.delete.success"));
        return "redirect:/sensorTempertauras";
    }

}
