package lab.domain.impl;

import java.util.ArrayList;
import java.util.List;

import lab.domain.Book;
import lab.domain.BookRepository;
import org.eclipse.persistence.internal.jpa.rs.metadata.model.Query;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

@Service

//@Scope("prototype")
public class BookRepositoryImpl implements BookRepository {
   // EntityManagerFactory emf = Persistence.createEntityManagerFactory("cars-pu");
   // EntityManager eman = emf.createEntityManager();
	ArrayList<Book> books = new ArrayList<Book>();


	@Transactional(readOnly = true)
	public Book getBook(Long id) {
		return null;
	}

	public void createBook(Book createdEntity) {
		books.add(createdEntity);
		// System.out.println(createdEntity);
	}

	@Transactional
	public boolean removeBook(Book updatedEntity) {
		return getBooks().remove(updatedEntity);
	}

	public List<Book> getBooks() {
		return books;
	}
}
