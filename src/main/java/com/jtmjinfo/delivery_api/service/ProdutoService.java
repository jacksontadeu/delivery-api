package com.jtmjinfo.delivery_api.service;

import com.jtmjinfo.delivery_api.entity.model.Produto;
import com.jtmjinfo.delivery_api.repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProdutoService {

    @Autowired
    private ProdutoRepository produtoRepository;

    public Produto cadstrarProduto(Produto produto){
        return produtoRepository.save(produto);
    }
    public Optional<Produto> buscarProdutoPorId(Long id){
        return produtoRepository.findById(id);
    }
    public List<Produto> listarProdutosRestaurante(Long restauranteId){
        return produtoRepository.findByRestauranteId(restauranteId);
    }
    public List<Produto> listarProdutosDisponiveis(){
        return produtoRepository.findByDisponivelTrue();
    }
    public Produto atualizarProduto(Long id, Produto atualizado){
        return produtoRepository.findById(id).
                map(p -> { p.setNome(atualizado.getNome());
                    p.setDescricao(atualizado.getDescricao());
                    p.setPreco(atualizado.getPreco());
                    p.setDisponivel(atualizado.getDisponivel());
                    return produtoRepository.save(p);
                }).orElseThrow(()-> new RuntimeException("Produto não encontrado"));
    }
}
