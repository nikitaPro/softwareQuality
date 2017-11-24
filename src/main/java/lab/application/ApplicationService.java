package lab.application;

import lab.domain.Book;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ApplicationService {
	void createRandomBooks(int amount);
	void deleteEntitiesIdNotFibonacci();
	List<Book> getLibrary();
}
