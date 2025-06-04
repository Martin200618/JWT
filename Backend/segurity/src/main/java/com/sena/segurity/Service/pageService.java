package com.sena.segurity.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.sena.segurity.DTO.pageDTO;
import com.sena.segurity.Model.page;
import com.sena.segurity.Repository.IPage;
@Service
public class pageService {

    @Autowired
    private IPage pageRepository;

    // Obtener todas las páginas
    public List<page> findAll() {
        try {
            return pageRepository.findAll();
        } catch (Exception e) {
            throw new RuntimeException("Error al obtener las páginas: " + e.getMessage());
        }
    }

    // Obtener una página por ID
    public Optional<page> findById(int pageId) {
        try {
            return pageRepository.findById(pageId);
        } catch (Exception e) {
            throw new RuntimeException("Error al obtener la página con ID: " + pageId + ", Error: " + e.getMessage());
        }
    }

    // Registrar una página
    public String save(pageDTO pageDTO) {
        if (pageDTO == null || !isValidPage(pageDTO)) {
            return HttpStatus.BAD_REQUEST.toString() + ": Los datos de la página son inválidos";
        }

        try {
            page page = converToModel(pageDTO);
            pageRepository.save(page);
            return HttpStatus.OK.toString() + ": Página registrada exitosamente";
        } catch (Exception e) {
            return HttpStatus.INTERNAL_SERVER_ERROR.toString() + ": Error al registrar la página, Detalle: " + e.getMessage();
        }
    }

    // Actualizar una página
    public String update(int pageId, pageDTO pageDTO) {
        Optional<page> existingPage = findById(pageId);

        if (!existingPage.isPresent()) {
            return HttpStatus.NOT_FOUND.toString() + ": La página con ID " + pageId + " no existe o ya fue eliminada";
        }

        try {
            page pageToUpdate = existingPage.get();
            pageToUpdate.setNamePage(pageDTO.getNamePage());
            pageToUpdate.setPath(pageDTO.getPath());
            pageToUpdate.setDescription(pageDTO.getDescription());
            pageRepository.save(pageToUpdate);
            return HttpStatus.OK.toString() + ": Página actualizada correctamente";
        } catch (Exception e) {
            return HttpStatus.INTERNAL_SERVER_ERROR.toString() + ": Error al actualizar la página, Detalle: " + e.getMessage();
        }
    }

    // Eliminar una página por ID
    public String delete(int pageId) {
        Optional<page> page = findById(pageId);

        if (!page.isPresent()) {
            return HttpStatus.NOT_FOUND.toString() + ": La página con ID " + pageId + " no existe o ya fue eliminada";
        }

        try {
            pageRepository.deleteById(pageId);
            return HttpStatus.OK.toString() + ": Página eliminada correctamente";
        } catch (Exception e) {
            return HttpStatus.INTERNAL_SERVER_ERROR.toString() + ": Error al eliminar la página, Detalle: " + e.getMessage();
        }
    }

    // Convertir DTO a modelo
    private page converToModel(pageDTO pageDTO) {
        page page = new page();
        page.setNamePage(pageDTO.getNamePage());
        page.setPath(pageDTO.getPath());
        page.setDescription(pageDTO.getDescription());
        return page;
    }

    // Validación de datos de página
    private boolean isValidPage(pageDTO pageDTO) {
        return pageDTO.getNamePage() != null && !pageDTO.getNamePage().trim().isEmpty()
                && pageDTO.getPath() != null && !pageDTO.getPath().trim().isEmpty();
    }
}