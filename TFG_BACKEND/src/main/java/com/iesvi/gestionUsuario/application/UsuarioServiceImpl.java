package com.iesvi.gestionUsuario.application;

import com.iesvi.gestionUsuario.application.dto.UsuarioDTO;
import com.iesvi.gestionUsuario.domain.repos.UsuarioRepository;
import com.iesvi.gestionUsuario.domain.vo.UsuarioVO;
import com.iesvi.utils.UsuarioConverterToDTO;
import com.iesvi.utils.UsuarioConverterToVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UsuarioServiceImpl implements UsuarioService {

    @Autowired
    UsuarioRepository usuarioRepository;

    @Autowired
    UsuarioConverterToDTO usuarioConverterToDTO;

    @Autowired
    UsuarioConverterToVO usuarioConverterToVO;

    @Transactional
    public List<UsuarioDTO> findAll() {
        return usuarioRepository.findAll()
                .stream()
                .map(usuarioConverterToDTO::convert)
                .collect(Collectors.toList());
    }

    @Transactional
    public List<UsuarioDTO> findByUsuario(String usuario) {
        return usuarioRepository.findAll()
                .stream()
                .filter(usuarioVO -> usuarioVO.getUsuario().contains(usuario))
                .map(usuarioConverterToDTO::convert)
                .collect(Collectors.toList());
    }

    @Transactional
    public List<UsuarioDTO> findByTelefono(String telefono) {
        return usuarioRepository.findAll()
                .stream()
                .filter(usuarioVO -> usuarioVO.getTelefono().contains(telefono))
                .map(usuarioConverterToDTO::convert)
                .collect(Collectors.toList());
    }

    @Transactional
    public UsuarioDTO createUsuario(UsuarioDTO usuarioDTO) {

        UsuarioVO usuarioVO = usuarioConverterToVO.convert(usuarioDTO);

        try {
            return usuarioConverterToDTO.convert(usuarioRepository.insert(usuarioVO));
        } catch (Exception e) {
            return usuarioConverterToDTO.convert(usuarioRepository.insert(usuarioVO));
        }

    }

    @Transactional
    public UsuarioDTO updateUsuario(String id, UsuarioDTO usuarioDTO) {
        Optional<UsuarioVO> dataVO = usuarioRepository.findById(id);
        UsuarioVO usuarioVO = dataVO.get();

        usuarioVO.setNombre(usuarioDTO.getNombre());
        usuarioVO.setUsuario(usuarioDTO.getUsuario());
        usuarioVO.setTelefono(usuarioDTO.getTelefono());
        usuarioVO.setContraseña(usuarioDTO.getContraseña());
        usuarioVO.setMensajes(usuarioDTO.getMensajes());

        return usuarioConverterToDTO.convert(usuarioRepository.save(usuarioVO));
    }


    @Transactional
    public boolean deleteUsuario(String id) {
        try {
            usuarioRepository.deleteById(id);
            return Boolean.TRUE;
        } catch (Exception e) {
            return Boolean.FALSE;
        }
    }

}

