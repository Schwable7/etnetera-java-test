package com.etnetera.hr.data;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Simple data entity describing basic properties of every JavaScript framework.
 * 
 * @author Etnetera
 *
 */
@Entity
public class JavaScriptFramework {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@Column(nullable = false, length = 30, unique = true)
	private String name;

	@Column(name = "versions")
	@ElementCollection
	private List<String> versions = new ArrayList<>();

	@Column(name = "hype_level")
	private BigDecimal hypeLevel;

	@Column(name = "deprecation_date")
	private Date deprecationDate;

	public JavaScriptFramework() {
	}

	public JavaScriptFramework(String name) {
		this.name = name;
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

	public List<String> getVersions() {
		return versions;
	}

	public void setVersions(List<String> versions) {
		this.versions = versions;
	}

	public BigDecimal getHypeLevel() {
		return hypeLevel;
	}

	public void setHypeLevel(BigDecimal hypeLevel) {
		this.hypeLevel = hypeLevel;
	}

	public Date getDeprecationDate() {
		return deprecationDate;
	}

	public void setDeprecationDate(Date deprecationDate) {
		this.deprecationDate = deprecationDate;
	}

	@Override
	public String toString() {
		return "JavaScriptFramework{" +
				"id=" + id +
				", name='" + name + '\'' +
				", versions=" + versions +
				", hypeLevel=" + hypeLevel +
				", deprecationDate=" + deprecationDate +
				'}';
	}
}
