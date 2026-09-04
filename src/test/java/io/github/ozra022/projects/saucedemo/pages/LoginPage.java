package io.github.ozra022.projects.saucedemo.pages;

import io.github.ozra022.automation.core.reporting.TestReport;
import io.github.ozra022.automation.selenium.page.BasePage;
import io.github.ozra022.automation.selenium.wait.WaitManager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

/**
 * Representa la pantalla de inicio de sesión
 * de SauceDemo.
 *
 * <p>Contiene los localizadores y acciones
 * disponibles sobre la pantalla de login.</p>
 *
 * <p>Cada acción permite indicar si se requiere
 * evidencia mediante screenshot.</p>
 *
 * @author Brandon Sanchez
 * @version 1.1
 * @since 2026-08-27
 */
public class LoginPage extends BasePage {

    /**
     * Campo de usuario.
     */
    private final By usernameInput =
            By.id("user-name");

    /**
     * Campo de contraseña.
     */
    private final By passwordInput =
            By.id("password");

    /**
     * Botón Login.
     */
    private final By loginButton =
            By.id("login-button");

    /**
     * Título de la pantalla de productos.
     */
    private final By productsTitle =
            By.className("title");

    /**
     * Ingresa el nombre de usuario.
     *
     * @param username   nombre de usuario
     * @param screenshot true si se requiere captura
     * @return instancia actual
     * @since 2026-08-27
     */
    public LoginPage enterUsername(
            String username,
            boolean screenshot
    ) {

        WebElement element =
                WaitManager.visible(
                        usernameInput
                );

        element.clear();

        element.sendKeys(
                username
        );

        if (screenshot) {

            TestReport.addScreenshot(
                    "Nombre de usuario ingresado."
            );
        }

        return this;
    }

    /**
     * Ingresa la contraseña.
     *
     * @param password   contraseña
     * @param screenshot true si se requiere captura
     * @return instancia actual
     * @since 2026-08-27
     */
    public LoginPage enterPassword(
            String password,
            boolean screenshot
    ) {

        WebElement element =
                WaitManager.visible(
                        passwordInput
                );

        element.clear();

        element.sendKeys(
                password
        );

        if (screenshot) {

            TestReport.addScreenshot(
                    "Contraseña ingresada."
            );
        }

        return this;
    }

    /**
     * Presiona el botón de inicio de sesión.
     *
     * @param screenshot true si se requiere captura
     * @return instancia actual
     * @since 2026-08-27
     */
    public LoginPage clickLogin(
            boolean screenshot
    ) {

        WaitManager
                .clickable(loginButton)
                .click();

        if (screenshot) {

            TestReport.addScreenshot(
                    "Resultado después de presionar el botón Login."
            );
        }

        return this;
    }

    /**
     * Valida que el título de la pantalla
     * de productos sea "Products".
     *
     * @param screenshot true si se requiere captura
     * @return true si el título corresponde al esperado
     * @since 2026-08-27
     */
    public boolean validateProductsTitle(
            boolean screenshot
    ) {

        boolean visible =
                WaitManager.isVisible(
                        productsTitle
                );

        if (!visible) {

            if (screenshot) {

                TestReport.addScreenshot(
                        "No se encontró visible el título de Products."
                );
            }

            return false;
        }

        String title =
                WaitManager
                        .visible(productsTitle)
                        .getText()
                        .trim();

        if (screenshot) {

            TestReport.addScreenshot(
                    "Validación del título de la pantalla de productos."
            );
        }

        return "Products".equalsIgnoreCase(
                title
        );
    }
}