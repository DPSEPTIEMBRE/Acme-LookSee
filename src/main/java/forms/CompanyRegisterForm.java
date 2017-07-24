package forms;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.SafeHtml;

public class CompanyRegisterForm {
	
	// Attributes ---------------------------------------------------------

		private String name;
		private String surname;
		private String email;
		private String phone;
		private String address;
		private String username;
		private String password;
		private boolean termsAndConditions;

		// Constructor ---------------------------------------------------------

		public CompanyRegisterForm() {
			super();
		}

		// Getters and Setters --------------------------------------------------

		@NotBlank
		@NotNull
		@SafeHtml
		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		@NotBlank
		@NotNull
		@SafeHtml
		public String getSurname() {
			return surname;
		}

		public void setSurname(String surname) {
			this.surname = surname;
		}

		@NotBlank
		@NotNull
		@Email
		@SafeHtml
		public String getEmail() {
			return email;
		}

		public void setEmail(String email) {
			this.email = email;
		}

		@NotBlank
		@NotNull
		@SafeHtml
		public String getPhone() {
			return phone;
		}

		public void setPhone(String phone) {
			this.phone = phone;
		}
		
		@NotBlank
		@NotNull
		@SafeHtml
		public String getAddress() {
			return address;
		}

		public void setAddress(String address) {
			this.address = address;
		}
		
		@Size(min = 5, max = 32)
		@SafeHtml
		public String getUsername() {
			return this.username;
		}

		public void setUsername(final String username) {
			this.username = username;
		}

		@Size(min = 5, max = 32)
		@SafeHtml
		public String getPassword() {
			return this.password;
		}

		public void setPassword(final String password) {
			this.password = password;
		}


		public boolean isTermsAndConditions() {
			return this.termsAndConditions;
		}

		public void setTermsAndConditions(final boolean termsAndConditions) {
			this.termsAndConditions = termsAndConditions;
		}



}
