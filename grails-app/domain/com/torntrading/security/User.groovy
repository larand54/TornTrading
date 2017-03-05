package com.torntrading.security

import groovy.transform.EqualsAndHashCode
import groovy.transform.ToString
import com.torntrading.portal.UserSettings

@EqualsAndHashCode(includes='username')
@ToString(includes='username', includeNames=true, includePackage=false)
class User implements Serializable {

	private static final long serialVersionUID = 1

	transient springSecurityService

	String username
	String password
	boolean enabled = true
	boolean accountExpired
	boolean accountLocked
	boolean passwordExpired

	Set<Role> getAuthorities() {
		UserRole.findAllByUser(this)*.role
	}

	def beforeInsert() {
		encodePassword()
	}

	def beforeUpdate() {
		if (isDirty('password')) {
			encodePassword()
		}
	}
        
        UserSettings getUserSettings() {
            us
        }

	protected void encodePassword() {
		password = springSecurityService?.passwordEncoder ? springSecurityService.encodePassword(password) : password
	}

	static transients = ['springSecurityService']

	static constraints = {
		password blank: false, password: true
		username blank: false, unique: true
                us unique:true, nullable:true
	}

	static mapping = {
		password column: '`password`'
                table '`user`'
	}
        
        static hasOne = [us:UserSettings]
}
