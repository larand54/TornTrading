package com.torntrading.security
import grails.plugin.springsecurity.authentication.dao.NullSaltSource
import grails.plugin.springsecurity.ui.strategy.MailStrategy
import grails.plugin.springsecurity.ui.strategy.PropertiesStrategy
import grails.plugin.springsecurity.ui.strategy.RegistrationCodeStrategy
import groovy.text.SimpleTemplateEngine
import org.springframework.security.authentication.dao.SaltSource
import grails.plugin.springsecurity.annotation.Secured

	/** Dependency injection for the 'saltSource' bean. */
	SaltSource saltSource

	/** Dependency injection for the 'uiMailStrategy' bean. */
	MailStrategy uiMailStrategy

	/** Dependency injection for the 'uiRegistrationCodeStrategy' bean. */
	RegistrationCodeStrategy uiRegistrationCodeStrategy

	/** Dependency injection for the 'uiPropertiesStrategy' bean. */
	PropertiesStrategy uiPropertiesStrategy
/*
class RegisterController2 extends grails.plugin.springsecurity.ui.RegisterController {
	def register(RegisterCommand registerCommand) {

		if (!request.post) {
			return [registerCommand: new RegisterCommand()]
		}

		if (registerCommand.hasErrors()) {
			return [registerCommand: registerCommand]
		}

		def user = uiRegistrationCodeStrategy.createUser(registerCommand)
		String salt = saltSource instanceof NullSaltSource ? null : registerCommand.username
		RegistrationCode registrationCode = uiRegistrationCodeStrategy.register(user, registerCommand.password, salt)

		if (registrationCode == null || registrationCode.hasErrors()) {
			// null means problem creating the user
			flash.error = message(code: 'spring.security.ui.register.miscError')
			return [registerCommand: registerCommand]
		}

		user = uiRegistrationCodeStrategy.finishRegistration(registrationCode)

		if (!user) {
			flash.error = message(code: 'spring.security.ui.register.badCode')
			redirect uri: successHandlerDefaultTargetUrl
			return
		}

		if (user.hasErrors()) {
			// expected to be handled already by ErrorsStrategy.handleValidationErrors
			return
		}

		flash.message = message(code: 'spring.security.ui.register.complete')
		redirect uri: registerPostRegisterUrl ?: successHandlerDefaultTargetUrl
	}
}*/