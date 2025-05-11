package com.guillaumegasnier.education.recherche.validations;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class SiretValidatorTest {

    private Validator validator;

    @BeforeEach
    void setUp() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Test
    void validSiretShouldPass() {
        String validSiret = "73282932000074"; // Luhn OK
        TestClass testObj = new TestClass(validSiret);

        Set<ConstraintViolation<TestClass>> violations = validator.validate(testObj);
        assertTrue(violations.isEmpty(), "SIRET valide ne doit pas produire d'erreurs");
    }

    @Test
    void nullSiretShouldPass() {
        TestClass testObj = new TestClass(null);

        Set<ConstraintViolation<TestClass>> violations = validator.validate(testObj);
        assertTrue(violations.isEmpty(), "SIRET null (optionnel) ne doit pas produire d'erreur");
    }

    @Test
    void emptySiretShouldPass() {
        TestClass testObj = new TestClass("");

        Set<ConstraintViolation<TestClass>> violations = validator.validate(testObj);
        assertTrue(violations.isEmpty(), "SIRET vide (optionnel) ne doit pas produire d'erreur");
    }

    @Test
    void invalidLengthSiretShouldFail() {
        TestClass testObj = new TestClass("123");

        Set<ConstraintViolation<TestClass>> violations = validator.validate(testObj);
        assertFalse(violations.isEmpty(), "SIRET avec mauvaise longueur doit échouer");
    }

    @Test
    void invalidLuhnSiretShouldFail() {
        TestClass testObj = new TestClass("73282932000075"); // Mauvais contrôle Luhn

        Set<ConstraintViolation<TestClass>> violations = validator.validate(testObj);
        assertFalse(violations.isEmpty(), "SIRET avec mauvais contrôle Luhn doit échouer");
    }

    static class TestClass {
        @ValidSiret
        private String siret;

        TestClass(String siret) {
            this.siret = siret;
        }
    }
}