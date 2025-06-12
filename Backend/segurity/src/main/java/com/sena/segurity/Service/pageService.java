package com.sena.segurity.Service;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sena.segurity.DTO.ResponsesDTO;
import com.sena.segurity.DTO.pageDTO;
import com.sena.segurity.Model.pages;
import com.sena.segurity.Repository.IPage;

@Service
public class pageService {
    @Autowired
    private IPage data;
    
    public List<pages> findAll() {
        return data.findAll();
    }

    public Optional<pages> findById(int id) {
        return data.findById(id);
    }

    public ResponsesDTO deletePage(int id) {
        Optional<pages> pageOpt = findById(id);
        if (!pageOpt.isPresent()) {
            return new ResponsesDTO("404", "La página no existe");
        }
        data.deleteById(id);
        return new ResponsesDTO("200", "Página eliminada correctamente");
    }

    public ResponsesDTO save(pageDTO pageDTO) {
        pages page = convertToModel(pageDTO);
        data.save(page);
        return new ResponsesDTO("200", "Página guardada correctamente");
    }

    public ResponsesDTO updatePage(int id, pageDTO pageDTO) {
        Optional<pages> pageOpt = findById(id);
        if (!pageOpt.isPresent()) {
            return new ResponsesDTO("404", "La página no existe");
        }
        pages updatedPage = pageOpt.get();
        updatedPage.setName(pageDTO.getName());
        updatedPage.setUrl(pageDTO.getUrl());
        updatedPage.setDescription(pageDTO.getDescription());

        data.save(updatedPage);
        return new ResponsesDTO("200", "Página actualizada correctamente");
    }

    public pageDTO convertToDTO(pages page) {
        return new pageDTO(
            page.getPageid(),
            page.getName(),
            page.getUrl(),
            page.getDescription()
        );
    }

    public pages convertToModel(pageDTO pageDTO) {
        return new pages(
            pageDTO.getPageid(),
            pageDTO.getName(),
            pageDTO.getUrl(),
            pageDTO.getDescription()
        );
    }

}