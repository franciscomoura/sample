package sample;

import javax.validation.constraints.Email;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;


import org.hibernate.validator.constraints.CreditCardNumber;

import java.util.Objects;


public class Person {
	private Long id;
	private String firstName;
	@NotNull
	@NotBlank
	@Size(max = 10)
	private String lastName;
	@Pattern(regexp = ".+@.+\\..+", message = "Please provide a valid email address")
	private String email;
	@Email()
	private String email1;
	@Min(18)
	@Max(30)
	private int age;
	@CreditCardNumber
	private String creditCardNumber;

	public Person() {
	}

	public String getCreditCardNumber() {
		return creditCardNumber;
	}

	public Long getId() {
		return id;
	}

	public String getEmail1() {
		return email1;
	}

	@Size(min = 2)
	public String getFirstName() {
		return firstName;
	}

	public String getLastName() {
		return lastName;
	}


	public String getEmail() {
		return email;
	}


	public int getAge() {
		return age;
	}

	public Person setId(Long id) {
		this.id = id;
		return this;
	}

	public Person setFirstName(String firstName) {
		this.firstName = firstName;
		return this;
	}

	public Person setLastName(String lastName) {
		this.lastName = lastName;
		return this;
	}

	public Person setEmail(String email) {
		this.email = email;
		return this;
	}

	public Person setEmail1(String email1) {
		this.email1 = email1;
		return this;
	}

	public Person setAge(int age) {
		this.age = age;
		return this;
	}

	public Person setCreditCardNumber(String creditCardNumber) {
		this.creditCardNumber = creditCardNumber;
		return this;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Person person = (Person) o;
		return Objects.equals(id, person.id);
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public String toString() {
		return "Person{" +
				"id=" + id +
				", firstName='" + firstName + '\'' +
				", lastName='" + lastName + '\'' +
				", email='" + email + '\'' +
				", email1='" + email1 + '\'' +
				", age=" + age +
				", creditCardNumber='" + creditCardNumber + '\'' +
				'}';
	}
}
