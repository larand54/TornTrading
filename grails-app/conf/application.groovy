// Added by the Spring Security Core plugin:
grails.plugin.springsecurity.userLookup.userDomainClassName = 'com.torntrading.security.User'
grails.plugin.springsecurity.userLookup.authorityJoinClassName = 'com.torntrading.security.UserRole'
grails.plugin.springsecurity.authority.className = 'com.torntrading.security.Role'
grails.plugin.springsecurity.controllerAnnotations.staticRules = [
	[pattern: '/',               access: ['permitAll']],
	[pattern: '/.well-known/**/acme-chalenge/**', access: ['permitAll']],
        [pattern: '/AD2F6917563C3933684C2D3AD746575C.txt', access: ['permitAll']],
	[pattern: '/test.txt', access: ['permitAll']],
        [pattern: '/certVerification', access: ['permitAll']],
	[pattern: '/textFile', access: ['permitAll']],
	[pattern: '/error',          access: ['permitAll']],
	[pattern: '/index',          access: ['permitAll']],
	[pattern: '/index.gsp',      access: ['permitAll']],
	[pattern: '/shutdown',       access: ['permitAll']],
	[pattern: '/assets/**',      access: ['permitAll']],
	[pattern: '/**/js/**',       access: ['permitAll']],
	[pattern: '/**/css/**',      access: ['permitAll']],
	[pattern: '/**/images/**',   access: ['permitAll']],
	[pattern: '/**/favicon.ico', access: ['permitAll']],
        [pattern: '/user/**',        access: 'ROLE_ADMIN'],
        [pattern: '/role/**',        access: 'ROLE_ADMIN'],
        [pattern: '/securityInfo/**',        access: 'ROLE_ADMIN'],
        [pattern: '/register/**',        access: ['permitAll']],
        [pattern: '/registrationCode/**',        access: 'ROLE_ADMIN'],
      	[pattern: '/quickrequest/**',      access: ['permitAll']],
      	[pattern: '/appInfo',      access: ['ROLE_ADMIN']],
        [pattern: '/console/**',   access: ['ROLE_ADMIN']],
        [pattern: '/static/console/**', access:    ['ROLE_ADMIN']], // Grails 3.x
      	[pattern: '/under_construction',      access: ['permitAll']]

]

grails.plugin.springsecurity.filterChain.chainMap = [
	[pattern: '/assets/**',      filters: 'none'],
	[pattern: '/**/js/**',       filters: 'none'],
	[pattern: '/**/css/**',      filters: 'none'],
	[pattern: '/**/images/**',   filters: 'none'],
	[pattern: '/**/favicon.ico', filters: 'none'],
	[pattern: '/**',             filters: 'JOINED_FILTERS']
]

grails.plugins.localeConfiguration.defaultLocale = Locale.ENGLISH
grails.plugin.springsecurity.logout.postOnly = false
grails.plugin.databasemigration.updateOnStartFilename = 'changelog.groovy'
grails.plugin.databasemigration.updateOnStart = false
grails.plugin.springsecurity.logout.postOnly = false
logout.afterLogoutUrl = "/"

grails.plugin.springsecurity.useSecurityEventListener = true
//grails.plugin.databasemigration.updateOnStartContexts = ['WoodTrading']
/*
        // Config mail-plugin
        println("############  SMTP - CONFIGURATION #############")
        grails {
            mail {
                    host = "smtp.gmail.com"
                    'default' {
                        from = "caves700@gmail.com"
                    }
                port = 465
                username = "caves700@gmail.com"
                password = ""
                props = ["mail.smtp.auth":"true",
                    "mail.smtp.socketFactory.port":"465",
                    "mail.smtp.socketFactory.class":"javax.net.ssl.SSLSocketFactory",
                    "mail.smtp.socketFactory.fallback":"false"]
            }
        }
*/