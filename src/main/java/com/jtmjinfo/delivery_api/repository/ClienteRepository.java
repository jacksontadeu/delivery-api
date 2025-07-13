package com.jtmjinfo.delivery_api.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.jtmjinfo.delivery_api.entity.model.Cliente;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Long> {
    Optional<Cliente> findByEmail(String email);
    boolean existsByEmail(String email);
    List<Cliente> findByAtivoTrue();
//    // Busca cliente por email
//    Optional<Cliente> findByEmail(String nome);
//
//    // Verifica se o email já existe
//    boolean existsByEmail(String email);
//
//    // Busca clientes ativos
//    List<Cliente> findByNomeContainingIgnoreCase(String nome);
//
//    //Busca cliente por teefone
//    Optional<Cliente> findByTelefone(String telefone);

//    //Query personalizada -> clientes com pedidos
//    @Query("select distinct c from Cliente c join c.pedidos p where c.ativo=true ")
//    List<Cliente> fincClientesComPedidos();
//
//    //Query  -> clientes por cidade
//    @Query(value ="select * from Cliente endereco like %cidade% and ativo= true", nativeQuery = true)
//    List<Cliente> findByCidade(@Param("cidade")String cidade);
//
//    //Query contar clientes ativos]
//    @Query("select count(c) from Cliente c  where c.ativo=true")
//    Long countClientesAtivos();
//

}
