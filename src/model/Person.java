package model;

import java.time.LocalDate;
import java.time.Period;
import java.util.Random;

public class Person implements Comparable<Person>{

	public static int population = 6782 * 162253;

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
	public Person(String name, String lastName, String gender, int[] popProp,int nationality) {
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
		this.nationality = ramdomNationality(nationality, popProp);
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
	

	private Nationality ramdomNationality(int x, int[] popProp) {
		
		Nationality[] nationality = Nationality.values();
		
		if(x>= 0 && x < popProp[0]) {
			return nationality[0];
		}else if(x>= popProp[1] && x < popProp[2]) {
			return nationality[1];
		}else if(x>= popProp[2] && x < popProp[3]) {
			return nationality[2];
		}else if(x>= popProp[3] && x < popProp[4]) {
			return nationality[3];
		}else if(x>= popProp[4] && x < popProp[5]) {
			return nationality[4];
		}else if(x>= popProp[5] && x < popProp[6]) {
			return nationality[5];
		}else if(x>= popProp[6] && x < popProp[7]) {
			return nationality[6];
		}else if(x>= popProp[7] && x < popProp[8]) {
			return nationality[7];
		}else if(x>= popProp[8] && x < popProp[9]) {
			return nationality[8];
		}else if(x>= popProp[9] && x < popProp[10]) {
			return nationality[9];
		}else if(x>= popProp[10] && x < popProp[11]) {
			return nationality[10];
		}else if(x>= popProp[11] && x < popProp[12]) {
			return nationality[11];
		}else if(x>= popProp[12] && x < popProp[13]) {
			return nationality[12];
		}else if(x>= popProp[13] && x < popProp[14]) {
			return nationality[13];
		}else if(x>= popProp[14] && x < popProp[15]) {
			return nationality[14];
		}else if(x>= popProp[15] && x < popProp[16]) {
			return nationality[15];
		}else if(x>= popProp[16] && x < popProp[17]) {
			return nationality[16];
		}else if(x>= popProp[17] && x < popProp[18]) {
			return nationality[17];
		}else if(x>= popProp[18] && x < popProp[19]) {
			return nationality[18];
		}else if(x>= popProp[19] && x < popProp[20]) {
			return nationality[19];
		}else if(x>= popProp[20] && x < popProp[21]) {
			return nationality[20];
		}else if(x>= popProp[21] && x < popProp[22]) {
			return nationality[21];
		}else if(x>= popProp[22] && x < popProp[23]) {
			return nationality[22];
		}else if(x>= popProp[23] && x < popProp[24]) {
			return nationality[23];
		}else if(x>= popProp[24] && x < popProp[25]) {
			return nationality[24];
		}else if(x>= popProp[25] && x < popProp[26]) {
			return nationality[25];
		}else if(x>= popProp[26] && x < popProp[27]) {
			return nationality[27];
		}else if(x>= popProp[27] && x < popProp[28]) {
			return nationality[26];
		}else if(x>= popProp[28] && x < popProp[29]) {
			return nationality[28];
		}else if(x>= popProp[29] && x < popProp[30]) {
			return nationality[29];
		}else if(x>= popProp[30] && x < popProp[31]) {
			return nationality[30];
		}else if(x>= popProp[31] && x < popProp[32]) {
			return nationality[31];
		}else if(x>= popProp[32] && x < popProp[33]) {
			return nationality[32];
		}else if(x>= popProp[33] && x < popProp[34]) {
			return nationality[33];
		}else if(x>= popProp[34] && x < popProp[35]) {
			return nationality[34];
		}else if(x>= popProp[35] && x < popProp[36]) {
			return nationality[35];
		}else if(x>= popProp[36] && x < popProp[37]) {
			return nationality[36];
		}else if(x>= popProp[38] && x < popProp[38]) {
			return nationality[37];
		}else if(x>= popProp[38] && x < popProp[39]) {
			return nationality[38];
		}else if(x>= popProp[39] && x < popProp[40]) {
			return nationality[39];
		}else if(x>= popProp[40] && x < popProp[41]) {
			return nationality[40];
		}else if(x>= popProp[41] && x < popProp[42]) {
			return nationality[41];
		}else if(x>= popProp[42] && x < popProp[43]) {
			return nationality[42];
		}else if(x>= popProp[43] && x < popProp[44]) {
			return nationality[43];
		}else if(x>= popProp[44] && x < popProp[45]) {
			return nationality[44];
		}else if(x>= popProp[45] && x < popProp[46]) {
			return nationality[45];
		}else if(x>= popProp[46] && x < popProp[47]) {
			return nationality[46];
		}else if(x>= popProp[47] && x < popProp[48]) {
			return nationality[47];
		}else if(x>= popProp[48] && x < popProp[49]) {
			return nationality[48];
		}else if(x>= popProp[49] && x < popProp[50]) {
			return nationality[49];
		}else if(x>= popProp[50] && x < popProp[51]) {
			return nationality[50];
		}else if(x>= popProp[51] && x < popProp[52]) {
			return nationality[51];
		}else if(x>= popProp[52] && x < popProp[53]) {
			return nationality[52];
		}else if(x>= popProp[53] && x < popProp[54]) {
			return nationality[53];
		}else{
			return nationality[54];
		}
		
		
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
	/*
	private String name;
	private String lastName;
	private Gender gender;
	private LocalDate birthDate;
	private int age;
	private double height;
	private Nationality nationality;
	private String photo;
	*/
	public String toString() {
		return "" + name + "," + lastName + "," + gender + "," + birthDate + "," + age + "," + height + ","+nationality + "," +photo;
	}

}