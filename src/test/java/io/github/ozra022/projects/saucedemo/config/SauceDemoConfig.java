package io.github.ozra022.projects.saucedemo.config;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Proporciona acceso a la configuración
 * utilizada por las pruebas de SauceDemo.
 *
 * <p>Los valores se obtienen desde el archivo:</p>
 *
 * <pre>
 * src/test/resources/projects/saucedemo/config.properties
 * </pre>
 *
 * @author Brandon Sanchez
 * @version 1.0
 * @since 2026-08-27
 */
public final class SauceDemoConfig {

    private static final String CONFIG_FILE =
            "projects/saucedemo/config.properties";

    private static final Properties PROPERTIES =
            new Properties();

    static {

        try (
                InputStream inputStream =
                        SauceDemoConfig.class
                                .getClassLoader()
                                .getResourceAsStream(CONFIG_FILE)
        ) {

            if (inputStream == null) {

                throw new IllegalStateException(
                        "No se encontró el archivo de configuración: "
                                + CONFIG_FILE
                );
            }

            PROPERTIES.load(
                    inputStream
            );

        } catch (IOException exception) {

            throw new IllegalStateException(
                    "No fue posible cargar la configuración de SauceDemo.",
                    exception
            );
        }
    }

    /**
     * Constructor privado para evitar instancias.
     *
     * @since 2026-08-27
     */
    private SauceDemoConfig() {
    }

    /**
     * Obtiene la URL base de SauceDemo.
     *
     * @return URL de SauceDemo
     * @since 2026-08-27
     */
    public static String getBaseUrl() {

        return getRequired(
                "base.url"
        );
    }

    /**
     * Obtiene el usuario utilizado
     * durante las pruebas.
     *
     * @return usuario
     * @since 2026-08-27
     */
    public static String getUsername() {

        return getRequired(
                "username"
        );
    }

    /**
     * Obtiene la contraseña utilizada
     * durante las pruebas.
     *
     * @return contraseña
     * @since 2026-08-27
     */
    public static String getPassword() {

        return getRequired(
                "password"
        );
    }

    /**
     * Obtiene una propiedad obligatoria.
     *
     * @param key nombre de la propiedad
     * @return valor configurado
     * @throws IllegalStateException si la propiedad no existe o está vacía
     * @since 2026-08-27
     */
    private static String getRequired(
            String key
    ) {

        String value =
                PROPERTIES.getProperty(key);

        if (value == null || value.isBlank()) {

            throw new IllegalStateException(
                    "La propiedad obligatoria '"
                            + key
                            + "' no se encuentra configurada."
            );
        }

        return value.trim();
    }
}