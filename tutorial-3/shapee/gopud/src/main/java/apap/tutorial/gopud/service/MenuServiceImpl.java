package apap.tutorial.gopud.service;

import apap.tutorial.gopud.model.MenuModel;
import apap.tutorial.gopud.model.RestoranModel;
import apap.tutorial.gopud.repository.MenuDb;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class MenuServiceImpl implements MenuService {
    @Autowired
    private MenuDb menuDb;

    @Override
    public void addMenu(MenuModel menu) {
        menuDb.save(menu);
    }

    @Override
    public List<MenuModel> findAllMenuByIdRestoran(Long idRestoran) {
        return menuDb.findByRestoranIdRestoran(idRestoran);
    }

    @Override
    public MenuModel findById(Long idMenu) {
        try{
            Optional<MenuModel> menu = menuDb.findById(idMenu);
            return menu.get();
        }catch (NoSuchElementException e){
            throw  e;
        }
    }

    @Override
    public MenuModel changeRestoran(MenuModel menu) {
        try {
            // mengambil object restoran yang ingin diubah
            MenuModel targetMenu = findById(menu.getId());
            targetMenu.setNama(menu.getNama());
            targetMenu.setHarga(menu.getHarga());
            targetMenu.setDurasiMasak(menu.getDurasiMasak());
            targetMenu.setDeskripsi(menu.getDeskripsi());
            menuDb.save(targetMenu);
            return targetMenu;
        } catch (NullPointerException nullException) {
            throw nullException;
        }
    }

    @Override
    public void deleteMenu(Long idMenu) {
        MenuModel menu = findById(idMenu);
        menuDb.deleteById(idMenu);
    }
}
