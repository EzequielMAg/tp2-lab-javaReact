package com.webapp.tp2_lab_javareact.exception;

import org.springframework.http.HttpStatus;

/**
 * Excepción que indica que un atributo de una entidad ya existe.
 *
 * Esta excepción se utiliza para representar un conflicto en la entidad
 * cuando se intenta agregar o modificar un atributo que ya está presente
 * en la base de datos o en el contexto de la aplicación.
 *
 * <p>El código de estado HTTP asociado a esta excepción es {@link HttpStatus#CONFLICT}++
 * (409 Conflict).</p>
 *
 * <p>Se espera que este tipo de excepción sea manejado por las capas de servicio
 * o controlador para proporcionar una respuesta adecuada al cliente.</p>
 */
public class EntityAttributeExistsException extends BusinessException {

    public EntityAttributeExistsException(String message) {
        super(message);
        super.setHttStatus(HttpStatus.CONFLICT);
    }
}
