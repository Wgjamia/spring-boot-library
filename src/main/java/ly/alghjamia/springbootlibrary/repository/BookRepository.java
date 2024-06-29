package ly.alghjamia.springbootlibrary.repository;

import ly.alghjamia.springbootlibrary.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book, Long> {
}
