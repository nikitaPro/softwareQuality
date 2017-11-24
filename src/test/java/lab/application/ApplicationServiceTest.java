package lab.application;

import lab.application.impl.ApplicationServiceImpl;
import lab.domain.Book;
import lab.domain.impl.BookRepositoryImpl;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.internal.verification.VerificationModeFactory.times;

public class ApplicationServiceTest {
    @InjectMocks
    ApplicationServiceImpl applicationService;

    @Mock
    BookRepositoryImpl mockUserRepository;

    @Before
    public void setUp(){
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testCreateRandomBooks(){
        // Given

        // When
        applicationService.createRandomBooks(10);
        List<Book> library = applicationService.getLibrary();
        // Then
        // you are expecting service to return whatever returned by repo

        // you are expecting repo to be called once with correct param
        verify(mockUserRepository, times(10)).createBook(any(Book.class));
    }
    @Test
    public void testDeleteEntitiesIdNotFibonacci(){
        // Given
        Book userBook;
        List<Book> library = new ArrayList<Book>(12);
        List<Book> expectedLibrary = new ArrayList<Book>(10);
        int first = 0;
        int second = 1;
        int buf;
        for (int i = 0; i < 10; i++) {
            userBook = new Book();
            userBook.setId(first);
            library.add(userBook);
            expectedLibrary.add(userBook);
            buf = first + second;
            first = second;
            second = buf;
        }
        Book userBookRemoved1 = new Book();
        userBookRemoved1.setId(10);
        library.add(userBookRemoved1);

        Book userBookRemoved2 = new Book();
        userBookRemoved2.setId(9);
        library.add(userBookRemoved2);
        when(mockUserRepository.getBooks()).thenReturn(library);
        when(mockUserRepository.removeBook(userBookRemoved1)).thenCallRealMethod();
        when(mockUserRepository.removeBook(userBookRemoved2)).thenCallRealMethod();
        // When
        applicationService.deleteEntitiesIdNotFibonacci();
        List<Book> resLibrary = applicationService.getLibrary();
        // Then
        // you are expecting service to return whatever returned by repo
        assertThat("result", resLibrary, is(equalTo(expectedLibrary)));
        // you are expecting repo to be called once with correct param
        verify(mockUserRepository, times(2)).removeBook(any(Book.class));
    }
}
