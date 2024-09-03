package com.webapp.tp2_lab_javareact.exception;

/**
 * La clase {@code BussinessException} sirve como la clase base para todas las excepciones
 * relacionadas con la lógica de negocio en la aplicación. Es una clase abstracta, lo que
 * significa que no está destinada a ser instanciada directamente, sino extendida por otras
 * clases de excepciones más específicas.
 * <p>
 * Esta clase extiende {@link RuntimeException}, lo que permite que las excepciones de negocio
 * sean no verificadas (unchecked). Las subclases de {@code BussinessException} pueden representar
 * diversos errores específicos de negocio, como que una entidad no se encuentre o que un atributo
 * ya exista.
 * </p>
 *
 * @author Ezequiel Mamani Aguilar
 * @version 1.0
 * @since 2024-09-01
 */
public class BussinessException extends RuntimeException{
    public BussinessException(String message) {
        super(message);
    }
}
