package bstorm.akimts.CorrectionExo1.mapper;

import bstorm.akimts.CorrectionExo1.dto.SectionDTO;
import bstorm.akimts.CorrectionExo1.entities.Section;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class SectionMapper implements Mapper<SectionDTO, Section> {

    @Autowired
    private StudentSectionMapper sMapper;

    @Override
    public SectionDTO toDTO(Section section) {
        if( section == null )
            return null;

        return SectionDTO.builder()
                .id(section.getId())
                .name(section.getName())
                .delegateId(section.getDelegateId())
                .students(section.getStudents().stream()
                                .map(sMapper::toDTO)
                                .collect(Collectors.toList()))
                .build();
    }

    @Override
    public Section toEntity(SectionDTO sectionDTO) {
        if( sectionDTO == null )
            return null;

        return Section.builder()
                .id(sectionDTO.getId())
                .name(sectionDTO.getName())
                .delegateId(sectionDTO.getDelegateId())
                .build();
    }
}
