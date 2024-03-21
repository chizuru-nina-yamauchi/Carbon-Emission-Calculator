package dao;

import model.EmissionFactor;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class EmissionFactorDaoImplTest {

    private final EmissionFactorDao emissionFactorDao = new EmissionFactorDaoImpl();



    // all create, read, update, delete test passed successfully

    /*
    @Test
    public void testCreateEmissionFactor(){
        EmissionFactor emissionFactor = new EmissionFactor(1,0.75, "testUnit");
        EmissionFactor createdEmissionFactor = emissionFactorDao.createEmissionFactor(emissionFactor);
        assertNotNull(createdEmissionFactor);
    }

     */

    @Test
    public void testUpdateEmissionFactor(){
        int emissionFactorIdToUpdate = 9;

        EmissionFactor originalEmissionFactor = emissionFactorDao.readEmissionFactorById(emissionFactorIdToUpdate);
        assertNotNull(originalEmissionFactor);

        originalEmissionFactor.setEmissionFactorActivityId(2);
        originalEmissionFactor.setEmissionFactor(0.5);
        originalEmissionFactor.setEmissionFactorUnit("updatedUnit");

        boolean updated = emissionFactorDao.updateEmissionFactor(originalEmissionFactor);
        assertTrue(updated);

        EmissionFactor updatedEmissionFactor = emissionFactorDao.readEmissionFactorById(emissionFactorIdToUpdate);
        assertNotNull(updatedEmissionFactor);
    }

    /*
    @Test
    public void testDeleteEmissionFactor(){
        int emissionFactorIdToDelete = 8;
        boolean deleted = emissionFactorDao.deleteEmissionFactor(emissionFactorIdToDelete);
        assertTrue(deleted);
    }

    */


    @Test
    public void testReadEmissionFactorById(){
        int emissionFactorId = 1;
        EmissionFactor emissionFactor = emissionFactorDao.readEmissionFactorById(emissionFactorId);
        assertNotNull(emissionFactor);
    }


    @Test
    public void testReadAllEmissionFactors(){
        Set<EmissionFactor> emissionFactors = emissionFactorDao.readAllEmissionFactors();
        assertNotNull(emissionFactors);
    }

}