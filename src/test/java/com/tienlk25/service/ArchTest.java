package com.tienlk25.service;

import com.tngtech.archunit.core.domain.JavaClasses;
import com.tngtech.archunit.core.importer.ClassFileImporter;
import com.tngtech.archunit.core.importer.ImportOption;
import org.junit.jupiter.api.Test;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.noClasses;

class ArchTest {

    @Test
    void servicesAndRepositoriesShouldNotDependOnWebLayer() {

        JavaClasses importedClasses = new ClassFileImporter()
            .withImportOption(ImportOption.Predefined.DO_NOT_INCLUDE_TESTS)
            .importPackages("com.tienlk25.service");

        noClasses()
            .that()
                .resideInAnyPackage("com.tienlk25.service.service..")
            .or()
                .resideInAnyPackage("com.tienlk25.service.repository..")
            .should().dependOnClassesThat()
                .resideInAnyPackage("..com.tienlk25.service.web..")
        .because("Services and repositories should not depend on web layer")
        .check(importedClasses);
    }
}
