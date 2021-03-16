package pl.khamul.charity.institution;

import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class InstitutionService implements InstitutionInterface{
    private final InstitutionRepository institutionRepository;

    public InstitutionService(InstitutionRepository institutionRepository) {
        this.institutionRepository = institutionRepository;
    }

    public List<Institution> institutionList(){
      return  institutionRepository.findAll();
    }
}
