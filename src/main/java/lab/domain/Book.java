package lab.domain;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

//@Table(name = "BOOK", schema="RECORDS")
@Entity
public class Book implements Serializable {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private long id; // primary key

    @NotNull(message="Please enter name of Book")
	private String name;

	@NotNull(message="Please enter numbers of pages of Book")
	private int numbersOfPages;

	@NotNull(message="Please enter author name of Book")
	private String author;
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getNumbersOfPages() {
		return numbersOfPages;
	}
	public void setNumbersOfPages(int numbersOfPages) {
		this.numbersOfPages = numbersOfPages;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	@Override
	public String toString() {
		return id + " " + name + " by " + author + " pages: " + numbersOfPages;
	}
	
}
