package com.iesvi.gestionUsuario.infra;

import com.iesvi.gestionUsuario.domain.builder.UsuarioVOBuilder;
import com.iesvi.gestionUsuario.domain.vo.UsuarioVO;
import com.iesvi.shared.UnitTestCase;
import com.iesvi.shared.config.ConfiguracionPersistenciaTest;
import com.iesvi.shared.config.ConfigurationMongoTest;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {ConfiguracionPersistenciaTest.class, ConfigurationMongoTest.class})
public class VideojuegoInfraUnitTC extends UnitTestCase {

    @Autowired
    MongoTemplate em;

    @Override
    public void setup() {
        super.setup();
        if (em.collectionExists(UsuarioVO.class)) {
            em.dropCollection(UsuarioVO.class);
        }
    }

    @Override
    public void tearDown() {
        super.tearDown();
        em.dropCollection(UsuarioVO.class);
    }

    @Test
    public void ShouldAddnewVideojuegoTest() {
        //Arrange
        UsuarioVO ett = createAndSaveNewVideojuego();

        //Act
        UsuarioVO ettbd = em.findById(ett.getId(), UsuarioVO.class);

        //Assert
        Assert.assertEquals(ett, ettbd);
    }

    @Test
    public void ShouldEditVideojuegoTest() {
        //Arrange
        UsuarioVO ett = createAndSaveNewVideojuego();

        UsuarioVO ettEdit = em.findById(ett.getId(), UsuarioVO.class);
        ettEdit.setNombre("Samuel");
        ettEdit.setUsuario("sam123");

        //Act
        em.save(ettEdit);

        //Assert
        UsuarioVO ettbd = em.findById(ett.getId(), UsuarioVO.class);

        Assert.assertEquals(ettEdit, ettbd);
    }

    @Test
    public void consultarDatosVideojuego() {

    }

    @Test
    public void ShouldRemoveVideojuego() {
        //Arrange
        UsuarioVO ettdelete = em.findById(createAndSaveNewVideojuego().getId(), UsuarioVO.class);

        //Act
        em.remove(ettdelete);

        UsuarioVO ettbd = em.findById(ettdelete.getId(), UsuarioVO.class);

        //Assert
        Assert.assertNull(ettbd);

    }

    private UsuarioVO createAndSaveNewVideojuego() {
        UsuarioVO ett = new UsuarioVOBuilder().build();
        em.insert(ett);

        return ett;
    }

}