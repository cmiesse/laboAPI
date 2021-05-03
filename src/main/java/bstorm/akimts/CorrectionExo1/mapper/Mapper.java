package bstorm.akimts.CorrectionExo1.mapper;

public interface Mapper<DTO, ENTITY> {
    DTO toDTO(ENTITY entity);
    ENTITY toEntity(DTO dto);
}
