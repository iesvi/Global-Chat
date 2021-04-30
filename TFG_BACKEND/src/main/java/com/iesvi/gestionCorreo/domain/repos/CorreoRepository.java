package com.iesvi.gestionCorreo.domain.repos;

import com.iesvi.gestionCorreo.domain.vo.CorreoVO;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CorreoRepository extends MongoRepository<CorreoVO, String> {

}
