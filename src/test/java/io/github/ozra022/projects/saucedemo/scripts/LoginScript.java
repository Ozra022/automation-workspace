package io.github.ozra022.projects.saucedemo.scripts;

import io.github.ozra022.automation.core.reporting.annotation.ReportInfo;
import io.github.ozra022.automation.core.reporting.annotation.ReportTest;
import io.github.ozra022.automation.selenium.driver.DriverManager;

import io.github.ozra022.projects.saucedemo.config.SauceDemoConfig;
import io.github.ozra022.projects.saucedemo.pages.LoginPage;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static io.github.ozra022.automation.core.reporting.TestReport.addStep;
import static io.github.ozra022.automation.core.reporting.TestReport.addText;

/**
 * Contiene las pruebas automatizadas
 * correspondientes al inicio de sesión
 * de SauceDemo.
 *
 * @author Brandon Sanchez
 * @version 1.2
 * @since 2026-08-27
 */
@ReportInfo(
        executor = "Brandon Sanchez"
)
public class LoginScript {

    /**
     * Inicializa Selenium y navega
     * hacia SauceDemo antes de cada prueba.
     *
     * @since 2026-08-27
     */
    @BeforeMethod
    public void setUp() {

        DriverManager.start();

        DriverManager
                .getDriver()
                .get(
                        SauceDemoConfig.getBaseUrl()
                );
    }

    /**
     * Valida el inicio de sesión utilizando
     * credenciales válidas.
     *
     * @since 2026-08-27
     */
    @Test
    @ReportTest(
            name = "Login exitoso SauceDemo",
            description = "Validar el inicio de sesión en SauceDemo utilizando credenciales válidas.",
            prerequisites = {
                    "Google Chrome debe encontrarse instalado.",
                    "SauceDemo debe encontrarse disponible.",
                    "El usuario de prueba debe encontrarse habilitado."
            }
    )
    public void loginExitoso() {

        LoginPage loginPage =
                new LoginPage();

        addStep(
                "Ingresar el nombre de usuario configurado."
        );

        loginPage.enterUsername(
                SauceDemoConfig.getUsername(),
                true
        );

        addStep(
                "Ingresar la contraseña configurada."
        );

        loginPage.enterPassword(
                SauceDemoConfig.getPassword(),
                true
        );

        addStep(
                "Presionar el botón Login."
        );

        loginPage.clickLogin(
                true
        );

        addStep(
                "Validar que el título de la pantalla sea Products."
        );

        Assert.assertTrue(
                loginPage.validateProductsTitle(
                        true
                ),
                "El título Products no se encuentra visible."
        );

        addText(
                "El inicio de sesión fue realizado correctamente."
        );
    }
}