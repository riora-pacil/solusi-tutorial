package apap.tutorial.gopud.controller;

import apap.tutorial.gopud.model.MenuModel;
import apap.tutorial.gopud.model.RestoranModel;
import apap.tutorial.gopud.service.MenuService;
import apap.tutorial.gopud.service.RestoranService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.NoSuchElementException;

@Controller
public class MenuController {
    @Autowired
    MenuService menuService;

    @Qualifier("restoranServiceImpl")
    @Autowired
    RestoranService restoranService;

    @RequestMapping(value = "/menu/add/{idRestoran}", method = RequestMethod.GET)
    private String addProductFormPage(@PathVariable(value = "idRestoran") Long idRestoran, Model model){
        MenuModel menu = new MenuModel();
        RestoranModel restoran = restoranService.getRestoranByIdRestoran(idRestoran);
        menu.setRestoran(restoran);

        model.addAttribute("menu", menu);

        return "form-add-menu";

    }

    @RequestMapping(value = "menu/add", method = RequestMethod.POST)
    private String addProductSubmit(@ModelAttribute MenuModel menu, Model model){
        menuService.addMenu(menu);

        model.addAttribute("nama", menu.getNama());

        return "add-menu";
    }

    @RequestMapping(value="menu/change/{idMenu}", method = RequestMethod.GET)
    public String changeMenuFormPage(@PathVariable Long idMenu, Model model) {
        //Mengambil existing data restoran
        try{
            MenuModel existingMenu = menuService.findById(idMenu);
            model.addAttribute("menu", existingMenu);
            return "form-change-menu";
        }catch (NoSuchElementException e){
            model.addAttribute("idMenu", idMenu);
            return "change-menu-error";
        }
    }

    @RequestMapping(value="menu/change/{idMenu}", method = RequestMethod.POST)
    public String changeMenuFormSubmit(@PathVariable Long idMenu, @ModelAttribute MenuModel menu, Model model) {
        try{
            MenuModel newMenuData = menuService.changeRestoran(menu);
            model.addAttribute("menu", newMenuData);
        }
        catch (NoSuchElementException e){
            model.addAttribute("idMenu", idMenu);
            return "change-menu-error";
        }

        return "change-menu";
    }

    @RequestMapping(value="menu/delete/id/{idMenu}", method = RequestMethod.GET)
    public String deleteMenuFormPage(@PathVariable Long idMenu, @ModelAttribute MenuModel menu, Model model){
        try{
            menuService.deleteMenu(idMenu);
        }catch (NoSuchElementException e){
            model.addAttribute("errorMessage", "Id tidak ditemukan");
            return "delete-menu-error";
        }
        return "delete-menu";
    }

}
