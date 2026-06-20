package utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ConfigReader {

    // Esta función recibe la "llave" (ej. url.base) y nos devuelve el valor guardado
    public static String getProperty(String key) {
        Properties properties = new Properties();
        try {
            // 1. Le decimos a Java la ruta exacta donde guardamos el libro
            FileInputStream file = new FileInputStream("src/test/resources/config.properties");
            // 2. Abre el libro y lo carga en la memoria
            properties.load(file);
        } catch (IOException e) {
            System.out.println("¡Error! No se pudo encontrar o leer el archivo config.properties");
        }
        // 3. Busca la palabra que le pedimos y nos devuelve el texto
        return properties.getProperty(key);
    }
}