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

class UaiValidatorTest {

    private Validator validator;

    @BeforeEach
    void setUp() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Test
    void validUaiShouldPass() {
        String validUai = "0750662M";
        UaiValidatorTest.TestClass testObj = new UaiValidatorTest.TestClass(validUai);

        Set<ConstraintViolation<UaiValidatorTest.TestClass>> violations = validator.validate(testObj);
        assertTrue(violations.isEmpty(), "UAI valide ne doit pas produire d'erreurs");
    }

    @Test
    void nullUaiShouldPass() {
        UaiValidatorTest.TestClass testObj = new UaiValidatorTest.TestClass(null);

        Set<ConstraintViolation<UaiValidatorTest.TestClass>> violations = validator.validate(testObj);
        assertTrue(violations.isEmpty(), "UAI null (optionnel) ne doit pas produire d'erreur");
    }

    @Test
    void emptyUaiShouldPass() {
        UaiValidatorTest.TestClass testObj = new UaiValidatorTest.TestClass("");

        Set<ConstraintViolation<UaiValidatorTest.TestClass>> violations = validator.validate(testObj);
        assertTrue(violations.isEmpty(), "UAI vide (optionnel) ne doit pas produire d'erreur");
    }

    @Test
    void wrongUaiFormatShouldFail() {
        UaiValidatorTest.TestClass testObj = new UaiValidatorTest.TestClass("750662M");

        Set<ConstraintViolation<UaiValidatorTest.TestClass>> violations = validator.validate(testObj);
        assertFalse(violations.isEmpty(), "UAI mal formaté doit produire une erreur");
    }

    @Test
    void wrongUaiKeyShouldFail() {
        UaiValidatorTest.TestClass testObj = new UaiValidatorTest.TestClass("0750662N");

        Set<ConstraintViolation<UaiValidatorTest.TestClass>> violations = validator.validate(testObj);
        assertFalse(violations.isEmpty(), "UAI mal formaté doit produire une erreur");
    }

    static class TestClass {
        @ValidUai
        private String uai;

        TestClass(String uai) {
            this.uai = uai;
        }
    }
}