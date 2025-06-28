package com.example.site_pl_99.service;

import com.example.site_pl_99.entity.MainMenuEntity;

public interface MainMenuService {
    public MainMenuEntity getMainMenuById(Long id);
    public MainMenuEntity addMainMenu(MainMenuEntity mainMenu);
    public MainMenuEntity updateMainMenuById(Long id,MainMenuEntity mainMenu);
    public void deleteMainMenuById(Long id);
}
