package org.andreaj.springboot.domain;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import org.andreaj.springboot.domain.enums.PositionEnum;

@Entity
public class Player {

	@Id
	@GeneratedValue
	private Long id;
	private String name;
	private PositionEnum position;
	private Integer age;
	private Date birthdate;
	private String nationality;
	private Float height;
	private Float weight;

	public Player() {
		/**
		 * Constructor for the Player class.
		 * This constructor is intentionally left empty as it is used for object
		 * instantiation without initializing any specific attributes.
		 **/
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public PositionEnum getPosition() {
		return position;
	}

	public void setPosition(PositionEnum position) {
		this.position = position;
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	public Date getBirthdate() {
		return birthdate;
	}

	public void setBirthdate(Date birthdate) {
		this.birthdate = birthdate;
	}

	public String getNationality() {
		return nationality;
	}

	public void setNationality(String nationality) {
		this.nationality = nationality;
	}

	public Float getHeight() {
		return height;
	}

	public void setHeight(Float height) {
		this.height = height;
	}

	public Float getWeight() {
		return weight;
	}

	public void setWeight(Float weight) {
		this.weight = weight;
	}
}
