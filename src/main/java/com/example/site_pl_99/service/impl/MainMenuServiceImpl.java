package com.example.site_pl_99.service.impl;

import com.example.site_pl_99.entity.MainMenuEntity;
import com.example.site_pl_99.excaption.IncorectInputException;
import com.example.site_pl_99.excaption.NotFoundException;
import com.example.site_pl_99.repository.MainMenuRepository;
import com.example.site_pl_99.service.MainMenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MainMenuServiceImpl implements MainMenuService {
    private final MainMenuRepository mainMenuRepository;

    @Autowired
    public MainMenuServiceImpl(MainMenuRepository mainMenuRepository) {
        this.mainMenuRepository = mainMenuRepository;
    }

    @Override
    public MainMenuEntity getMainMenuById(Long id) {
        return mainMenuRepository.findById(id).orElseThrow(() -> new NotFoundException(""));
    }

    @Override
    public MainMenuEntity addMainMenu(MainMenuEntity mainMenu) {
        if(mainMenu.getTitleRu().isEmpty() && mainMenu.getTitleKg().isEmpty()) throw new IncorectInputException("");
        if(mainMenu.getSubTitleRu().isEmpty() && mainMenu.getSubTitleKg().isEmpty()) throw new IncorectInputException("");
        return mainMenuRepository.save(mainMenu);
    }

    @Override
    public MainMenuEntity updateMainMenuById(Long id,MainMenuEntity mainMenu) {
        MainMenuEntity mainMenuEntity = mainMenuRepository.findById(id).orElseThrow(() -> new NotFoundException(""));
        if(mainMenu.getTitleRu().isEmpty() && mainMenu.getTitleKg().isEmpty()) throw new IncorectInputException("");
        if(mainMenu.getSubTitleRu().isEmpty() && mainMenu.getSubTitleKg().isEmpty()) throw new IncorectInputException("");
        mainMenuEntity.setTitleRu(mainMenu.getTitleRu());
        mainMenuEntity.setTitleKg(mainMenu.getTitleKg());
        mainMenuEntity.setSubTitleRu(mainMenu.getSubTitleRu());
        if(mainMenu.getAmountStudents() != 0) mainMenuEntity.setSubTitleKg(mainMenu.getSubTitleKg());
        if(mainMenu.getAmountGraduates() != 0) mainMenuEntity.setAmountStudents(mainMenu.getAmountStudents());
        if(mainMenu.getAmountPartners() != 0) mainMenuEntity.setAmountGraduates(mainMenu.getAmountGraduates());
        return mainMenuRepository.save(mainMenuEntity);
    }

    @Override
    public void deleteMainMenuById(Long id) {
        mainMenuRepository.deleteById(id);
    }
}
