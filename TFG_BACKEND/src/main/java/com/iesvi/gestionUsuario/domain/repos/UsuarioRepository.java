package com.iesvi.gestionUsuario.domain.repos;

import com.iesvi.gestionUsuario.domain.vo.UsuarioVO;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioRepository extends MongoRepository<UsuarioVO,String> {

}
