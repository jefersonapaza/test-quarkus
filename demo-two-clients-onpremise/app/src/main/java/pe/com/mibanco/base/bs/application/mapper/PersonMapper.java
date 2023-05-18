package pe.com.mibanco.base.bs.application.mapper;

import org.mapstruct.Mapper;
import pe.com.mibanco.base.bs.client.model.Persona;

@Mapper
public interface PersonMapper {
    pe.com.mibanco.base.bs.domain.model.Persona personToPersonDTO(Persona person);
}