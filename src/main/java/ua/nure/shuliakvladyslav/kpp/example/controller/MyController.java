package ua.nure.shuliakvladyslav.kpp.example.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import ua.nure.shuliakvladyslav.kpp.example.dao.DAOFactory;
import ua.nure.shuliakvladyslav.kpp.example.dao.DAOType;
import ua.nure.shuliakvladyslav.kpp.example.dao.IDAO;
import ua.nure.shuliakvladyslav.kpp.example.entity.Color;
import ua.nure.shuliakvladyslav.kpp.example.entity.Transport;
import ua.nure.shuliakvladyslav.kpp.example.entity.Type;
import ua.nure.shuliakvladyslav.kpp.example.form.DeleteByIdForm;
import ua.nure.shuliakvladyslav.kpp.example.form.DeleteByNameForm;
import ua.nure.shuliakvladyslav.kpp.example.form.SearchTransportByColor;

@Controller
public class MyController {

    private static IDAO dao = DAOFactory.getInstance(DAOType.COLLECTION);

    @GetMapping(value = {"/transport","/"})
    public String showAllTransport(Model model){
        model.addAttribute("allTransport",dao.getAllTransport());
        return "transport";
    }

    @GetMapping("/transport/new")
    public String showAddTransport(Model model){
        model.addAttribute("transport",new Transport());
        model.addAttribute("colors",dao.getAllColors());
        model.addAttribute("types",dao.getAllTypes());
        return "addTransport";
    }

    @PostMapping("/transport")
    public String addTransport(@ModelAttribute("transport")Transport transport){
        dao.saveTransport(transport);
        return "redirect:/transport";
    }

    @GetMapping("/color")
    public String showAllColors(Model model){
        model.addAttribute("colors",dao.getAllColors());
        return "colors";
    }

    @GetMapping("/color/new")
    public String showAddColor(Model model){
        model.addAttribute("color",new Color());
        return "addColor";
    }

    @PostMapping("/color")
    public String addColor(@ModelAttribute("color") Color color){
        dao.saveColor(color);
        return "redirect:/color";
    }
    @GetMapping("/type")
    public String showAllTypes(Model model){
        model.addAttribute("types",dao.getAllTypes());
        return "types";
    }

    @GetMapping("/type/new")
    public String showAddType(Model model){
        model.addAttribute("type",new Type());
        return "addType";
    }
    @PostMapping("/type")
    public String addType(@ModelAttribute("type") Type type){
        dao.saveType(type);
        return "redirect:/type";
    }
    @GetMapping("/transport/search")
    public String searchByColor(Model model){
        model.addAttribute("searchTransportByColorForm",new SearchTransportByColor());
        return "searchPage";
    }
    @GetMapping("/transport/result")
    public String searchResult(@ModelAttribute ("searchTransportByColorForm")SearchTransportByColor searchTransportByColor,Model model){
        model.addAttribute("allTransport",dao.getTransportByColor(searchTransportByColor.getColor()));
        return "transport";
    }
    @GetMapping("/transport/delete")
    public String showDeleteTransport(Model model){
        model.addAttribute("form",new DeleteByIdForm());
        return "deleteTransport";
    }

    @PostMapping("/transport/delete")
    public String deleteTransport(@ModelAttribute("form") DeleteByIdForm form){
        dao.deleteTransport(form.getId());
        return "redirect:/transport";
    }

    @GetMapping("/type/delete")
    public String showDeleteType(Model model){
        model.addAttribute("form",new DeleteByNameForm());
        return "deleteType";
    }

    @PostMapping("/type/delete")
    public String deleteType(@ModelAttribute("form")DeleteByNameForm form){
        dao.deleteType(form.getName());
        return "redirect:/type";
    }

    @GetMapping("/color/delete")
    public String showDeleteColor(Model model){
        model.addAttribute("form",new DeleteByNameForm());
        return "deleteColor";
    }

    @PostMapping("/color/delete")
    public String deleteColor(@ModelAttribute("form")DeleteByNameForm form){
        dao.deleteColor(form.getName());
        System.out.println(form.getName());
        return "redirect:/color";
    }


}
