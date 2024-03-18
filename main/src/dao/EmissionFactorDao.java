package dao;

import model.EmissionFactor;

import java.util.Set;

public interface EmissionFactorDao {
    EmissionFactor createEmissionFactor(EmissionFactor emissionFactor);
    EmissionFactor readEmissionFactorById(int emissionFactorId);
    Set<EmissionFactor> readAllEmissionFactors();
    boolean updateEmissionFactor(EmissionFactor emissionFactor);
    boolean deleteEmissionFactor(int emissionFactorId);
}

