package com.smartmess.smart_mess_management.serviceimpl;


import java.time.LocalDate;
import java.util.List;

import org.springframework.stereotype.Service;

import com.smartmess.smart_mess_management.Services.MenuService;
import com.smartmess.smart_mess_management.entity.Menu;
import com.smartmess.smart_mess_management.repository.MenuRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MenuServiceImpl implements MenuService {

    private final MenuRepository menuRepository;

    @Override
    public Menu createMenu(Menu menu) {
        return menuRepository.save(menu);
    }

    @Override
    public Menu getMenuById(Long id) {
        return menuRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Menu not found"));
    }

    @Override
    public List<Menu> getAllMenus() {
        return menuRepository.findAll();
    }

    @Override
    public Menu updateMenu(Long id, Menu menu) {
        Menu existing = getMenuById(id);

        existing.setMenuDate(menu.getMenuDate());
        existing.setDayOfWeek(menu.getDayOfWeek());
        existing.setMess(menu.getMess());
        existing.setMeal(menu.getMeal());
        existing.setItems(menu.getItems());

        return menuRepository.save(existing);
    }

    @Override
    public void deleteMenu(Long id) {
        menuRepository.deleteById(id);
    }

    @Override
    public List<Menu> getMenuByMessAndDate(Long messId, LocalDate date) {
        return menuRepository.findByMessIdAndMenuDate(messId, date);
    }
}