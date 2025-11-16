package br.com.fiap.service;

import br.com.fiap.dao.FaqDao;
import br.com.fiap.dto.FaqMostrarDto;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import java.util.List;
@ApplicationScoped
public class FaqService {
    @Inject
    FaqDao faqDao;

    public List<FaqMostrarDto> listar(){
        return faqDao.listar();
    }
}
