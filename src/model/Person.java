package model;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.Period;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class Person implements Comparable<Person>, Serializable {

	private static final long serialVersionUID = 1L;

	private String id;
	private String name;
	private String lastName;
	private String fullName;
	private Gender gender;
	private LocalDate birthDate;
	private int age;
	private double height;
	private Nationality nationality;

	/**
	 * Empty constructor of Person class
	 */
	public Person() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * General constructor of Person class
	 * 
	 * @param id
	 * @param name
	 * @param lastName
	 * @param fullName
	 * @param gender
	 * @param birthDate
	 * @param age
	 * @param height
	 * @param nationality
	 */
	public Person(String name, String lastName, Gender gender, LocalDate birthDate, double height,
			Nationality nationality) {
		this.name = name;
		this.lastName = lastName;
		this.fullName = name + " " + lastName;
		this.gender = gender;
		this.birthDate = birthDate;
		this.age = ramdomAge(this.birthDate);
		this.height = height;
		this.nationality = nationality;
		this.id = generateRandomID();
	}

	/**
	 * Random constructor of the Person class
	 * 
	 * @param name
	 * @param lastName
	 */
	public Person(String id, String name, String lastName, String gender, double[] popProp, int nationality,
			int amountOfPeople) {

		this.name = name;
		this.lastName = lastName;
		this.setFullName(name + " " + lastName);
		Gender g = Gender.MALE;
		if (gender.equals("FEMALE")) {
			g = Gender.FEMALE;
		}

		this.gender = g;
		this.birthDate = ramdomDateBirt();
		this.age = ramdomAge(this.birthDate);
		this.height = ramdonHeight();
		this.nationality = ramdomNationality(nationality, popProp, amountOfPeople);
		this.id = generateRandomID();

	}

	/**
	 * This method generate a random id. Choosing a random letter of the first name
	 * and another of the last name. "F" if this.gender == Gender.FEMALE, otherwise
	 * it will be "M". In addition to your age and year of birth
	 * 
	 * @return id, String, it's the value of random id.
	 */
	private String generateRandomID() {
		// TODO Auto-generated method stub
		String id = "";

		int randomValue = ThreadLocalRandom.current().nextInt(0, this.name.length()-1);

		id += name.charAt(randomValue);
		randomValue = ThreadLocalRandom.current().nextInt(0, this.lastName.length()-1);
		id += lastName.charAt(randomValue);

		if (this.gender.equals(Gender.FEMALE)) {
			id += "F";
		} else {
			id += "M";
		}

		id += "" + this.age;

		id += "" + this.birthDate.getYear();

		return id;
	}

	/**
	 * This method generates a random height between 1.5 and 2 meters
	 * 
	 * @return double, random height between 1.50m and 2m
	 */
	private double ramdonHeight() {
		double height = 1.5 + ThreadLocalRandom.current().nextDouble(0, 0.5);
		return Math.round(height * 100.0) / 100.0;
	}

	/**
	 * This method generates a random date between 1930 and 2021 taking into account
	 * the age distribution of the united states obtained from this link:
	 * 
	 * https://www.indexmundi.com/es/estados_unidos/distribucion_por_edad.html
	 * 
	 * 
	 * @return birthDate, LocalDate, it's the random date generated from the random
	 *         year between 1930 and 2021.
	 */
	private LocalDate ramdomDateBirt() {

		int year = 0;

		Random rand = new Random();

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
		
		System.out.println("year: " + year);

		LocalDate birthDate = LocalDate.of(year, 1 + (int) Math.random() * (11), 1 + (int) Math.random() * (29));

		return birthDate;
	}

	/**
	 * This method calculates the person's age from their date of birth
	 * 
	 * @param birthDate, LocalDate this.birthDate
	 * @return age, int, it's value of the age of person.
	 */
	private int ramdomAge(LocalDate birthDate) {

		Period edad = Period.between(birthDate, LocalDate.now());

		int age = edad.getYears();

		return age;
	}

	/**
	 * This method assign a "Random" nationality to the current person
	 * 
	 * @param x,       int, Current quantity of persons generated
	 * @param popProp, int[], vector of total accumulated according to American
	 *                 continent population
	 * @return Nationality, Value of nationality according to percentages of
	 *         population proportion
	 */
	private Nationality ramdomNationality(int x, double[] popProp, int amountOfPeople) {

		Nationality[] nationality = Nationality.values();

		if (x >= 0 && x < popProp[0] * amountOfPeople) {
			return nationality[0];
		} else if (x >= popProp[1] * amountOfPeople && x < popProp[2] * amountOfPeople) {
			return nationality[1];
		} else if (x >= popProp[2] * amountOfPeople && x < popProp[3] * amountOfPeople) {
			return nationality[2];
		} else if (x >= popProp[3] * amountOfPeople && x < popProp[4] * amountOfPeople) {
			return nationality[3];
		} else if (x >= popProp[4] * amountOfPeople && x < popProp[5] * amountOfPeople) {
			return nationality[4];
		} else if (x >= popProp[5] * amountOfPeople && x < popProp[6] * amountOfPeople) {
			return nationality[5];
		} else if (x >= popProp[6] * amountOfPeople && x < popProp[7] * amountOfPeople) {
			return nationality[6];
		} else if (x >= popProp[7] * amountOfPeople && x < popProp[8] * amountOfPeople) {
			return nationality[7];
		} else if (x >= popProp[8] * amountOfPeople && x < popProp[9] * amountOfPeople) {
			return nationality[8];
		} else if (x >= popProp[9] * amountOfPeople && x < popProp[10] * amountOfPeople) {
			return nationality[9];
		} else if (x >= popProp[10] * amountOfPeople && x < popProp[11] * amountOfPeople) {
			return nationality[10];
		} else if (x >= popProp[11] * amountOfPeople && x < popProp[12] * amountOfPeople) {
			return nationality[11];
		} else if (x >= popProp[12] * amountOfPeople && x < popProp[13] * amountOfPeople) {
			return nationality[12];
		} else if (x >= popProp[13] * amountOfPeople && x < popProp[14] * amountOfPeople) {
			return nationality[13];
		} else if (x >= popProp[14] * amountOfPeople && x < popProp[15] * amountOfPeople) {
			return nationality[14];
		} else if (x >= popProp[15] * amountOfPeople && x < popProp[16] * amountOfPeople) {
			return nationality[15];
		} else if (x >= popProp[16] * amountOfPeople && x < popProp[17] * amountOfPeople) {
			return nationality[16];
		} else if (x >= popProp[17] * amountOfPeople && x < popProp[18] * amountOfPeople) {
			return nationality[17];
		} else if (x >= popProp[18] * amountOfPeople && x < popProp[19] * amountOfPeople) {
			return nationality[18];
		} else if (x >= popProp[19] * amountOfPeople && x < popProp[20] * amountOfPeople) {
			return nationality[19];
		} else if (x >= popProp[20] * amountOfPeople && x < popProp[21] * amountOfPeople) {
			return nationality[20];
		} else if (x >= popProp[21] * amountOfPeople && x < popProp[22] * amountOfPeople) {
			return nationality[21];
		} else if (x >= popProp[22] * amountOfPeople && x < popProp[23] * amountOfPeople) {
			return nationality[22];
		} else if (x >= popProp[23] * amountOfPeople && x < popProp[24] * amountOfPeople) {
			return nationality[23];
		} else if (x >= popProp[24] * amountOfPeople && x < popProp[25] * amountOfPeople) {
			return nationality[24];
		} else if (x >= popProp[25] * amountOfPeople && x < popProp[26]) {
			return nationality[25];
		} else if (x >= popProp[26] * amountOfPeople && x < popProp[27] * amountOfPeople) {
			return nationality[27];
		} else if (x >= popProp[27] * amountOfPeople && x < popProp[28] * amountOfPeople) {
			return nationality[26];
		} else if (x >= popProp[28] * amountOfPeople && x < popProp[29] * amountOfPeople) {
			return nationality[28];
		} else if (x >= popProp[29] * amountOfPeople && x < popProp[30] * amountOfPeople) {
			return nationality[29];
		} else if (x >= popProp[30] * amountOfPeople && x < popProp[31] * amountOfPeople) {
			return nationality[30];
		} else if (x >= popProp[31] * amountOfPeople && x < popProp[32] * amountOfPeople) {
			return nationality[31];
		} else if (x >= popProp[32] * amountOfPeople && x < popProp[33] * amountOfPeople) {
			return nationality[32];
		} else if (x >= popProp[33] * amountOfPeople && x < popProp[34] * amountOfPeople) {
			return nationality[33];
		} else if (x >= popProp[34] * amountOfPeople && x < popProp[35] * amountOfPeople) {
			return nationality[34];
		} else if (x >= popProp[35] * amountOfPeople && x < popProp[36] * amountOfPeople) {
			return nationality[35];
		} else if (x >= popProp[36] * amountOfPeople && x < popProp[37] * amountOfPeople) {
			return nationality[36];
		} else if (x >= popProp[37] * amountOfPeople && x < popProp[38] * amountOfPeople) {
			return nationality[37];
		} else if (x >= popProp[38] * amountOfPeople && x < popProp[39] * amountOfPeople) {
			return nationality[38];
		} else if (x >= popProp[39] * amountOfPeople && x < popProp[40] * amountOfPeople) {
			return nationality[39];
		} else if (x >= popProp[40] * amountOfPeople && x < popProp[41] * amountOfPeople) {
			return nationality[40];
		} else if (x >= popProp[41] * amountOfPeople && x < popProp[42] * amountOfPeople) {
			return nationality[41];
		} else if (x >= popProp[42] * amountOfPeople && x < popProp[43] * amountOfPeople) {
			return nationality[42];
		} else if (x >= popProp[43] * amountOfPeople && x < popProp[44] * amountOfPeople) {
			return nationality[43];
		} else if (x >= popProp[44] * amountOfPeople && x < popProp[45] * amountOfPeople) {
			return nationality[44];
		} else if (x >= popProp[45] * amountOfPeople && x < popProp[46] * amountOfPeople) {
			return nationality[45];
		} else if (x >= popProp[46] * amountOfPeople && x < popProp[47] * amountOfPeople) {
			return nationality[46];
		} else if (x >= popProp[47] * amountOfPeople && x < popProp[48] * amountOfPeople) {
			return nationality[47];
		} else if (x >= popProp[48] * amountOfPeople && x < popProp[49] * amountOfPeople) {
			return nationality[48];
		} else if (x >= popProp[49] * amountOfPeople && x < popProp[50] * amountOfPeople) {
			return nationality[49];
		} else if (x >= popProp[50] * amountOfPeople && x < popProp[51] * amountOfPeople) {
			return nationality[50];
		} else if (x >= popProp[51] * amountOfPeople && x < popProp[52] * amountOfPeople) {
			return nationality[51];
		} else if (x >= popProp[52] * amountOfPeople && x < popProp[53] * amountOfPeople) {
			return nationality[53];
		} else {
			return nationality[54];
		}

	}

	@Override
	public int compareTo(Person p) {
		int out = 0;

		if (name.compareTo(p.getName()) == 0) {
			if (lastName.compareTo(p.getLastName()) == 0) {
				return 0;
			} else {
				out = lastName.compareTo(p.getLastName());
			}
		} else {
			out = name.compareTo(p.getName());
		}

		return out;
	}

	/*
	 * ---------Getters and Setters---------
	 */

	/**
	 * @return the id
	 */
	public String getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the lastName
	 */
	public String getLastName() {
		return lastName;
	}

	/**
	 * @param lastName the lastName to set
	 */
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	/**
	 * @return the gender
	 */
	public Gender getGender() {
		return gender;
	}

	/**
	 * @param gender the gender to set
	 */
	public void setGender(Gender gender) {
		this.gender = gender;
	}

	/**
	 * @return the birthDate
	 */
	public LocalDate getBirthDate() {
		return birthDate;
	}

	/**
	 * @param birthDate the birthDate to set
	 */
	public void setBirthDate(LocalDate birthDate) {
		this.birthDate = birthDate;
	}

	/**
	 * @return the age
	 */
	public int getAge() {
		return age;
	}

	/**
	 * @param age the age to set
	 */
	public void setAge(int age) {
		this.age = age;
	}

	/**
	 * @return the height
	 */
	public double getHeight() {
		return height;
	}

	/**
	 * @param height the height to set
	 */
	public void setHeight(double height) {
		this.height = height;
	}

	/**
	 * @return the nationality
	 */
	public Nationality getNationality() {
		return nationality;
	}

	/**
	 * @param nationality the nationality to set
	 */
	public void setNationality(Nationality nationality) {
		this.nationality = nationality;
	}

	/**
	 * To String method for the .csv File Writing serialization
	 * 
	 * @return String, it contains the data of person
	 */
	public String toString() {
		return "" + name + "," + lastName + "," + gender + "," + birthDate + "," + age + "," + height + ","
				+ nationality;
	}

	/**
	 * @return the fullName
	 */
	public String getFullName() {
		return fullName;
	}

	/**
	 * @param fullName the fullName to set
	 */
	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

}