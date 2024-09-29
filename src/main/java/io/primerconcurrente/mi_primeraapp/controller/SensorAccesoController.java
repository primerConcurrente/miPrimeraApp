package io.primerconcurrente.mi_primeraapp.controller;

import io.primerconcurrente.mi_primeraapp.model.SensorAccesoDTO;
import io.primerconcurrente.mi_primeraapp.model.TipoDeAcceso;
import io.primerconcurrente.mi_primeraapp.service.SensorAccesoService;
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
@RequestMapping("/sensorAccesos")
public class SensorAccesoController {

    private final SensorAccesoService sensorAccesoService;

    public SensorAccesoController(final SensorAccesoService sensorAccesoService) {
        this.sensorAccesoService = sensorAccesoService;
    }

    @ModelAttribute
    public void prepareContext(final Model model) {
        model.addAttribute("tipoDeAccesoValues", TipoDeAcceso.values());
    }

    @GetMapping
    public String list(final Model model) {
        model.addAttribute("sensorAccesoes", sensorAccesoService.findAll());
        return "sensorAcceso/list";
    }

    @GetMapping("/add")
    public String add(@ModelAttribute("sensorAcceso") final SensorAccesoDTO sensorAccesoDTO) {
        return "sensorAcceso/add";
    }

    @PostMapping("/add")
    public String add(@ModelAttribute("sensorAcceso") @Valid final SensorAccesoDTO sensorAccesoDTO,
            final BindingResult bindingResult, final RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            return "sensorAcceso/add";
        }
        sensorAccesoService.create(sensorAccesoDTO);
        redirectAttributes.addFlashAttribute(WebUtils.MSG_SUCCESS, WebUtils.getMessage("sensorAcceso.create.success"));
        return "redirect:/sensorAccesos";
    }

    @GetMapping("/edit/{id}")
    public String edit(@PathVariable(name = "id") final Long id, final Model model) {
        model.addAttribute("sensorAcceso", sensorAccesoService.get(id));
        return "sensorAcceso/edit";
    }

    @PostMapping("/edit/{id}")
    public String edit(@PathVariable(name = "id") final Long id,
            @ModelAttribute("sensorAcceso") @Valid final SensorAccesoDTO sensorAccesoDTO,
            final BindingResult bindingResult, final RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            return "sensorAcceso/edit";
        }
        sensorAccesoService.update(id, sensorAccesoDTO);
        redirectAttributes.addFlashAttribute(WebUtils.MSG_SUCCESS, WebUtils.getMessage("sensorAcceso.update.success"));
        return "redirect:/sensorAccesos";
    }

    @PostMapping("/delete/{id}")
    public String delete(@PathVariable(name = "id") final Long id,
            final RedirectAttributes redirectAttributes) {
        sensorAccesoService.delete(id);
        redirectAttributes.addFlashAttribute(WebUtils.MSG_INFO, WebUtils.getMessage("sensorAcceso.delete.success"));
        return "redirect:/sensorAccesos";
    }

}
