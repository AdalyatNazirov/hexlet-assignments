package exercise.service;

import exercise.dto.BookCreateDTO;
import exercise.dto.BookDTO;
import exercise.dto.BookUpdateDTO;
import exercise.exception.ResourceNotFoundException;
import exercise.mapper.BookMapper;
import exercise.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService {
    // BEGIN
    @Autowired
    private BookRepository bookRepository;
    @Autowired
    private BookMapper bookMapper;

    public List<BookDTO> getAll() {
        return bookRepository.findAll()
                .stream()
                .map(bookMapper::map)
                .toList();
    }

    public BookDTO getById(Long id) {
        var book = bookRepository.findById(id);
        if (book.isPresent()) {
            return bookMapper.map(book.get());
        } else {
            throw new ResourceNotFoundException("Book not found");
        }
    }

    public BookDTO create(BookCreateDTO dto) {
        var model = bookMapper.map(dto);
        var saved = bookRepository.save(model);
        return bookMapper.map(saved);
    }

    public BookDTO update(Long id, BookUpdateDTO dto) {
        var book = bookRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Book not found"));
        bookMapper.update(dto, book);
        var saved = bookRepository.save(book);
        return bookMapper.map(saved);
    }

    public void delete(Long id) {
        var book = bookRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Book not found"));
        bookRepository.delete(book);
    }
    // END
}
