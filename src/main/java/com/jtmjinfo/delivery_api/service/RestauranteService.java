package com.jtmjinfo.delivery_api.service;

import com.jtmjinfo.delivery_api.entity.model.Restaurante;
import com.jtmjinfo.delivery_api.repository.RestauranteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RestauranteService {

    @Autowired
    private RestauranteRepository restauranteRepository;

    public Restaurante cadastrarRestaurante(Restaurante restaurante){
        return restauranteRepository.save(restaurante);
    }
    public Optional<Restaurante> buscarRestaurantePorId(Long id){
        return restauranteRepository.findById(id);
    }
    public List<Restaurante> listarRestaurantes(){
        return restauranteRepository.findAll();
    }
    public Restaurante alterarRestaurante(Long id, Restaurante atualizado){
        return restauranteRepository.findById(id).
                map(r ->{
                    r.setNome(atualizado.getNome());
                    r.setCategoria(atualizado.getCategoria());
                    r.setTaxaEntrega(atualizado.getTaxaEntrega());
                    r.setTempoEntrega(atualizado.getTempoEntrega());
                    r.setAtivo(atualizado.getAtivo());

                    return restauranteRepository.save(r);
                }).orElseThrow(()-> new RuntimeException("Resturante não encontrado"));
    }
}
