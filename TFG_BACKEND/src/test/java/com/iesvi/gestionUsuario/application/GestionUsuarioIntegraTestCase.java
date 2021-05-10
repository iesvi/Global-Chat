/*
package com.iesvi.gestionUsuario.application;

import com.iesvi.gestionUsuario.application.dto.UsuarioDTO;
import com.iesvi.gestionUsuario.domain.builder.UsuarioVOBuilder;
import com.iesvi.gestionUsuario.domain.repos.UsuarioRepository;
import com.iesvi.gestionUsuario.domain.vo.UsuarioVO;
import com.iesvi.shared.config.ConfiguracionPersistenciaTest;
import com.iesvi.shared.config.ConfigurationMongoTest;
import com.iesvi.utils.UsuarioConverterToDTO;
import com.iesvi.utils.UsuarioConverterToVO;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {ConfiguracionPersistenciaTest.class, ConfigurationMongoTest.class})
public class GestionUsuarioIntegraTestCase {

    @Autowired
    UsuarioServiceImpl usuarioService;

    @Autowired
    UsuarioRepository usuarioRepository;

    @Autowired
    UsuarioConverterToDTO usuarioConverterToDTO;

    @Autowired
    UsuarioConverterToVO usuarioConverterToVO;

    private UsuarioDTO buildUsuarioDto() {

        return usuarioConverterToDTO.convert(new UsuarioVOBuilder().build());

    }

    // Test que debe registrar un usuario
    @Test
    public void ShouldRegisterUsuarioNotExistTest() {

        UsuarioDTO newUsuario = usuarioService.createUsuario(buildUsuarioDto());

        UsuarioVO entityFind = (UsuarioVO) usuarioService.findByUsuario(newUsuario.getUsuario());

        Assert.assertNotNull("Devuelve nuevo Usuario", newUsuario);
        Assert.assertNotNull("Usuario Existe en BD", entityFind);
        Assert.assertEquals("Usuario creado y obtenido de la BD son iguales", newUsuario, entityFind);

    }


    // Test que debe borrar un usuario
    @Test
    public void ShouldRemoveVideojuegoExistTest() {

        // **** Arrange
        UsuarioVO videojuegoExist = usuarioService.createUsuario(usuarioConverterToDTO.convert(new UsuarioVOBuilder().build()));

        // Act
        Assert.assertEquals(true, usuarioService.deleteUsuario(videojuegoExist.getId()));

    }

    // Test que debe modificar un usuario
    @Test
    public void ShouldModificarVideojuegos() {
        UsuarioVO videojuego = usuarioService.createUsuario(usuarioConverterToDTO.convert(new UsuarioVOBuilder().build()));

        videojuego.setNombre("Nuevo nombre");
        videojuego.setUsuario("Nuevo usuario");

        UsuarioVO videojuegoModificado = usuarioService.updateUsuario(usuarioConverterToDTO.convert(videojuego));

        Assert.assertNotEquals(videojuego, usuarioConverterToDTO.convert(videojuegoModificado));
    }

    // Test que debe buscar un videojuego con ese nombre y que esté en el catálogo
    @Test
    public void ShouldFindVideojuego() {

        UsuarioVO videojuego = usuarioService.createUsuario(usuarioConverterToDTO.convert(new UsuarioVOBuilder().build()));

        videojuego.setNombre("Nombre de prueba");

        usuarioService.updateUsuario(usuarioConverterToDTO.convert(videojuego));

        List<UsuarioVO> videojuegoVO = usuarioService.findByUsuario(videojuego.getUsuario());

        Assert.assertEquals(true, videojuegoVO.get(0).getNombre().contains(videojuego.getNombre()));

    }

}
*/
