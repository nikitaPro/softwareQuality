package lab.application.impl;

import java.util.ArrayList;
import java.util.List;
/*
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;*/

import lab.application.ApplicationService;
import lab.domain.Book;
import lab.domain.BookRepository;
import net.bytebuddy.utility.RandomString;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
//@Scope("Singleton")
//@Component
public class ApplicationServiceImpl implements ApplicationService {
    private static final String PERSISTENCE_UNIT_NAME = "lab1b";
    //private static EntityManagerFactory factory;
	@Autowired
	private BookRepository repository;
	
	
	/*public ApplicationServiceImpl(BookRepository repository) {
		this.repository = repository;
	}*/

	public void createRandomBooks(int amount) {
		for (int i = 0; i < amount; i++) {
			Book randomBook = new Book();
			randomBook.setAuthor(RandomString.make());
			randomBook.setName(RandomString.make());
			randomBook.setNumbersOfPages((int)(Math.random() * 1000.0));
			repository.createBook(randomBook);
		}
	}
    @Transactional
	public void deleteEntitiesIdNotFibonacci() {
		int buf;
		List<Book> list = repository.getBooks();
        int fibo1;
        int fibo2;
        boolean isFibo;
        for (int i = 0; i < list.size(); i++) {
            Book book = list.get(i);
            fibo1 = 0;
            fibo2 = 1;
            isFibo = false;
            while (book.getId() >= fibo1){
                if (book.getId() == fibo1){
                    isFibo = true;
                    break;
                }
                buf = getFiboNum(fibo1, fibo2);
                fibo1 = fibo2;
                fibo2 = buf;
            }
            if (isFibo) continue;
            else{
                repository.removeBook(book);
                i--;
            }
        }

	}
    private int getFiboNum(int first, int second){
	    return first + second;
    }
	public List<Book> getLibrary() {
		return repository.getBooks();
	}
/*	public void testServ(){
		factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
	    EntityManager em = factory.createEntityManager();
	    // read the existing entries and write to console
	    Query q = em.createQuery("select b from Book b");
	    List<Book> bookList = q.getResultList();
	    for (Book book : bookList) {
	        System.out.println(book.getId());
	    }
	    System.out.println("Size: " + bookList.size());
	
	    // create new todo
	    em.getTransaction().begin();
	    Book book = new Book();
	    book.setAuthor("Nikita Prokopenko");
	    book.setName("Lab_1b_Book");
	    book.setNumbersOfPages(5);
	    book.setId(1);
	    em.persist(book);
	    em.getTransaction().commit();
	
	    em.close();
	}*/
}
