package model;

import java.time.LocalDate;
import java.time.Period;
import java.util.Random;

public class Person implements Comparable<Person>{

	

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

	/**
	 * Random constructor
	 * @param name
	 * @param lastName
	 */
	public Person(String name, String lastName, String gender) {
		this.name = name;
		this.lastName = lastName;
		
		Gender g = Gender.MALE;
		if(gender.equals("FEMALE")) {
			g = Gender.FEMALE;
		}
		
		this.gender = g;
		this.birthDate = ramdomDateBirt();
		this.age = ramdomAge(this.birthDate);
		this.height = ramdonHeight();
		this.nationality = ramdomNationality();
		this.photo = null;
	}
	
	
	
	
	
	private double ramdonHeight() {
		return (double)Math.random()*(2.01-1.49+1)+1.49;
	}
	
	private LocalDate ramdomDateBirt() {
		
		int year = 0;
		
		Random rand =  new Random();
		
		int percent = rand.nextInt(100);
		
		if(percent<19) {
			year = (int)Math.random()*(2021-2008+1)+2008;
		}else if(percent >= 19 && percent < 32) {
			year = (int)Math.random()*(2007-1998+1)+1998;
		}else if(percent >= 32 && percent < 71) {
			year = (int)Math.random()*(1997-1968+1)+1968;
		}else if(percent >= 71 && percent < 84) {
			year = (int)Math.random()*(1967-1958+1)+1958;
		}else if(percent >= 84) {
			year = (int)Math.random()*(1957-1930+1)+1930;
		}
		
		LocalDate birthDate = LocalDate.of(year, 1 +(int)Math.random()*(11), 1 + (int)Math.random()*(29));
		
		return birthDate;
	}
	
	private int  ramdomAge(LocalDate birthDate) {
		
		Period edad = Period.between(birthDate, LocalDate.now());
		
		int age = edad.getYears();
		
		return age;
	}
	

	private Nationality ramdomNationality() {
		
		Nationality[] nationality = Nationality.values();
		
		return nationality[(int)Math.random()*(nationality.length-0+1)+0];
	}


	/*
	 * ---------Getters and Setters---------
	 */
	
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


	@Override
	public int compareTo(Person p) {
		int out = 0;
		
		if(name.compareTo(p.getName()) == 0) {
			if(lastName.compareTo(p.getLastName())== 0) {
				if(age == p.getAge()) {
					if(height == p.getHeight()) {
						
					}else {
						out = height>p.getHeight()? 1:-1;
					}
				}else {
					out = age>p.getAge()? 1:-1;
				}
			}else {
				out = lastName.compareTo(p.getLastName());
			}
		}else {
			out = name.compareTo(p.getName());
		}
		
		
		return out;
	}

}
