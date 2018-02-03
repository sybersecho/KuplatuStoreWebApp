package com.kupalatu.store.model;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.Valid;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "t_supplier")
public class Supplier implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2168792160714779078L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@NotEmpty
	@Size(max = 10)
	private String code;
	@NotEmpty
	private String contact;
	@NotEmpty
	@Email
	private String email;
	@NotEmpty
	private String name;
	@Valid
	@NotNull
	@OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinColumn(name = "address_id")
	private Address supplierAddress;

	public Supplier() {
		super();
	}

	public Supplier(@NotEmpty @Size(max = 10) String code, @NotEmpty String contact, @NotEmpty @Email String email,
			@NotEmpty String name, @Valid @NotNull Address supplierAddress) {
		super();
		this.code = code;
		this.contact = contact;
		this.email = email;
		this.name = name;
		this.supplierAddress = supplierAddress;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getContact() {
		return contact;
	}

	public void setContact(String contact) {
		this.contact = contact;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Address getSupplierAddress() {
		return supplierAddress;
	}

	public void setSupplierAddress(Address supplierAddress) {
		this.supplierAddress = supplierAddress;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Supplier other = (Supplier) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Supplier [id=" + id + ", code=" + code + ", contact=" + contact + ", email=" + email + ", name=" + name
				+ ", supplierAddress=" + supplierAddress + "]";
	}

}
