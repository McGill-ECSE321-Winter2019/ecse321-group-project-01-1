package ca.mcgill.ecse321.backend.dto;

import java.util.HashSet;
import java.util.Set;

public class InternshipDeepDto extends InternshipDto{

    private ApplicationFormDto applicationForm;
    private Set<DocumentDto> document = new HashSet<DocumentDto>();

}

