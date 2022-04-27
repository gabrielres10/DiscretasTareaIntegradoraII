package model;

import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;

public class Person {

	

	private String name;
	private String lastName;
	private Gender gender;
	private LocalDate birthDate;
	private int age;
	private double height;
	private Nationality nationality;
	private String photo;
	
	public Person() {
		// TODO Auto-generated constructor stub
	}

	
	public Person(String name, String lastName, Gender gender, LocalDate birthDate, double height, Nationality nationality,
			String photo) {
		this.name = name;
		this.lastName = lastName;
		this.gender = ramdomGender();
		this.birthDate = ramdomDateBirt();
		this.age = ramdomAge(this.birthDate);
		this.height = ramdonHeight();
		this.nationality = ramdomNationality();
		this.photo = photo;
	}
	
	
	
	
	
	private double ramdonHeight() {
		return (double)Math.random()*(2.01-1.49+1)+1.49;
	}
	
	private LocalDate ramdomDateBirt() {
		
		LocalDate birthDate = LocalDate.of((int)Math.random()*(2021-1925+1)+1925, (int)Math.random()*(12-0+1)+0, (int)Math.random()*(30-0+1)+0);
		
		return birthDate;
	}
	
	private int  ramdomAge(LocalDate birthDate) {
		Period edad = Period.between(birthDate, LocalDate.now());
		
		int age = edad.getYears();
		
		return age;
	}
	
	private Gender ramdomGender() {
		// TODO Auto-generated method stub
		return null;
	}


	private Nationality ramdomNationality() {
		
		Nationality[] nationality = Nationality.values();
		
		return nationality[(int)Math.random()*(nationality.length-0+1)+0];
	}


	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public Gender getGender() {
		return gender;
	}

	public void setGender(Gender gender) {
		this.gender = gender;
	}

	public LocalDate getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(LocalDate birthDate) {
		this.birthDate = birthDate;
	}

	public double getHeight() {
		return height;
	}

	public void setHeight(double height) {
		this.height = height;
	}

	public Nationality getNationality() {
		return nationality;
	}

	public void setNationality(Nationality nationality) {
		this.nationality = nationality;
	}

	public String getPhoto() {
		return photo;
	}

	public void setPhoto(String photo) {
		this.photo = photo;
	}


	public int getAge() {
		return age;
	}


	public void setAge(int age) {
		this.age = age;
	}

}
