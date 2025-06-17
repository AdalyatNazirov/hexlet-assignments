package exercise.service;

import exercise.dto.AuthorCreateDTO;
import exercise.dto.AuthorDTO;
import exercise.dto.AuthorUpdateDTO;
import exercise.mapper.AuthorMapper;
import exercise.repository.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AuthorService {

    // BEGIN

    @Autowired
    private AuthorRepository authorRepository;
    @Autowired
    private AuthorMapper authorMapper;

    public List<AuthorDTO> getAll() {
        return authorRepository.findAll()
                .stream()
                .map(authorMapper::map)
                .toList();
    }

    public AuthorDTO getById(Long id) {
        var author = authorRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Author not found"));
        return authorMapper.map(author);
    }

    public AuthorDTO create(AuthorCreateDTO dto) {
        var model = authorMapper.map(dto);
        var saved = authorRepository.save(model);
        return authorMapper.map(saved);
    }

    public AuthorDTO update(Long id, AuthorUpdateDTO dto) {
        var author = authorRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Author not found"));
        authorMapper.update(dto, author);
        var saved = authorRepository.save(author);
        return authorMapper.map(saved);
    }

    public void delete(Long id) {
        var author = authorRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Author not found"));
        authorRepository.delete(author);
    }


    // END
}
