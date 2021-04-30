package com.iesvi.shared;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;

public abstract class UnitTestCase {

   /*@BeforeEach
   protected void setUp() {
        //CONFIGURACIÓN A NIVEL DE CLASE O MÉTODO
    }*/

    //TODO: Esto no hace falta
//    static EntityManagerFactory emf;
//    protected EntityManager em;

    @Before
    public void setup() {

    }

    @After
    public void tearDown() {

    }

    @AfterClass
    public static void tearDownStatic() {

    }

    /** INCLUIR MÉTODO COMPARTIDOS POR TODOS LOS TEST UNITARIOS  */

//    public void shouldHavePublished(List<DomainEvent> domainEvents) {
//        verify(eventBus, atLeastOnce()).publish(domainEvents);
//    }
//
//    public void shouldHavePublished(DomainEvent domainEvent) {
//        shouldHavePublished(Collections.singletonList(domainEvent));
//    }
//
//    public void shouldGenerateUuid(String uuid) {
//        when(uuidGenerator.generate()).thenReturn(uuid);
//    }
//
//    public void shouldGenerateUuids(String uuid, String... others) {
//        when(uuidGenerator.generate()).thenReturn(uuid, others);
//    }
//
//    public void shouldAsk(Query query, Response response) {
//        when(queryBus.ask(query)).thenReturn(response);
//    }
}