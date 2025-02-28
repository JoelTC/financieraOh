package com.financieraOh.crudEmpleado_webcore.application.dto.response;

import java.util.List;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ResponseDTO<T> {
	private String codigo;
	private String mensaje;
	private List<T> dataResponse;
	private T entity;

	public ResponseDTO() {
	}

	public ResponseDTO(String codigoError, String mensaje) {
		this.codigo = codigoError;
		this.mensaje = mensaje;
	}

	public ResponseDTO(String codigo, String mensaje, List<T> dataResponse, T entity) {
		this.codigo = codigo;
		this.mensaje = mensaje;
		this.dataResponse = dataResponse;
		this.entity = entity;
	}
	
	public static <T> ResponseDTO<T> success(List<T> dataResponse, T entity, String mensaje) {
        return new ResponseDTO<>("200", mensaje, dataResponse, entity);
    }

    public static <T> ResponseDTO<T> noContent(String mensaje) {
        return new ResponseDTO<>("204", mensaje);
    }
    
    public static <T> ResponseDTO<T> noElement(String mensaje) {
        return new ResponseDTO<>("205", mensaje);
    }

    public static <T> ResponseDTO<T> error(String mensaje) {
        return new ResponseDTO<>("999", mensaje);
    }

	@Override
	public String toString() {
		return "ResponseDTO [codigo=" + codigo + ", mensaje=" + mensaje + ", dataResponse=" + dataResponse + ", entity="
				+ entity + "]";
	}
}