package torntrading
import grails.util.Environment
import com.buffer.*
import com.torntrading.portal.*
import com.torntrading.security.Role
import com.torntrading.security.User
import com.torntrading.security.UserRole
import com.torntrading.security.UserSettings


class BootStrap {
    def prodBufferService
    def init = { servletContext ->

        // An important user, he has no roles.
        def nobody = User.findByUsername('Nobody')?:new User(username: 'Nobody', password: 'okSxvEmCnQaBMO3EE6Nn5R4Z9WIHIk').save(failOnError:true)
        def nbus = UserSettings.findByUser(nobody)?:new UserSettings(user:nobody, supplierName:'none').save(failOnError:true)
        
	if (Environment.current == Environment.DEVELOPMENT) {
            Locale defLocale = new Locale("en", "GB");
            Locale.setDefault(defLocale);
            def adminRole = Role.findByAuthority('ROLE_ADMIN')?:new Role(authority: 'ROLE_ADMIN').save(failOnError:true)
            def salesRole = Role.findByAuthority('ROLE_SALES')?:new Role(authority: 'ROLE_SALES').save(failOnError:true)
            def supplierRole = Role.findByAuthority('ROLE_SUPPLIER')?:new Role(authority: 'ROLE_SUPPLIER').save(failOnError:true)

            def salesUser = User.findByUsername('seller')?:new User(username: 'seller', password: 'sales2017').save(failOnError:true)
            def larandUser = User.findByUsername('larand')?:new User(username: 'larand', password: 'mulan2010').save(failOnError:true)
            def lmUser = User.findByUsername('lars')?:new User(username: 'lars', password: 'woods2011!').save(failOnError:true)
            def btg = User.findByUsername('btg')?:new User(username: 'btg', password: 'borje').save(failOnError:true)
            def woodfix = User.findByUsername('woodfix')?:new User(username: 'woodfix', password: 'woodfix').save(failOnError:true)
            def kSeger = User.findByUsername('kSeger')?:new User(username: 'kSeger', password: 'kSeger').save(failOnError:true)
            

            def sus = UserSettings.findByUser(salesUser)?:new UserSettings(user:salesUser, supplierName:'Boda', currency:'EUR', volumeUnit: 'AM3').save(failOnError:true) 
            def laus = UserSettings.findByUser(larandUser)?:new UserSettings(user:larandUser, supplierName:'Boda', currency:'SEK', volumeUnit: 'AM3').save(failOnError:true) 
            def lmus = UserSettings.findByUser(lmUser)?:new UserSettings(user:lmUser, supplierName:'Boda', currency:'SEK', volumeUnit: 'PKG').save(failOnError:true) 
            def bous = UserSettings.findByUser(btg)?:new UserSettings(user:btg, supplierName:'Boda', currency:'SEK', volumeUnit: 'AM3').save(failOnError:true)
            def wf = UserSettings.findByUser(woodfix)?:new UserSettings(user:woodfix, supplierName:'WOODFIX AB', currency:'SEK', volumeUnit: 'AM3').save(failOnError:true)
            def ks = UserSettings.findByUser(kSeger)?:new UserSettings(user:kSeger, supplierName:'Karl Segerström AB', currency:'SEK', volumeUnit: 'AM3').save(failOnError:true)

            UserRole.create larandUser, adminRole
            UserRole.create lmUser, adminRole
            UserRole.create salesUser, salesRole
            UserRole.create woodfix, supplierRole
            UserRole.create kSeger, supplierRole
            UserRole.create btg, adminRole

            UserRole.withSession {
                it.flush()
                it.clear()
            }

/*            assert User.count() == 12
            assert Role.count() == 4
            assert UserRole.count() == 12
*/
        }
	if (Environment.current == Environment.PRODUCTION) {
            Locale.setDefault( new Locale("en", "GB"));
            def adminRole = Role.findByAuthority('ROLE_ADMIN')?:new Role(authority: 'ROLE_ADMIN').save(failOnError:true)
            
            def salesRole = Role.findByAuthority('ROLE_SALES')?:new Role(authority: 'ROLE_SALES').save(failOnError:true)
            def supplierRole = Role.findByAuthority('ROLE_SUPPLIER')?:new Role(authority: 'ROLE_SUPPLIER').save(failOnError:true)

            def larandUser = User.findByUsername('larand')?:new User(username: 'larand', password: 'mulan2010').save(failOnError:true)
            def lmUser = User.findByUsername('lars')?:new User(username: 'lars', password: 'woods2011!').save(failOnError:true)
            def btg = User.findByUsername('btg')?:new User(username: 'btg', password: 'borje').save(failOnError:true)
            

            def laus = UserSettings.findByUser(larandUser)?:new UserSettings(user:larandUser, supplierName:'Boda', currency:'SEK', volumeUnit: 'AM3').save(failOnError:true) 
            def lmus = UserSettings.findByUser(lmUser)?:new UserSettings(user:lmUser, supplierName:'Boda2', currency:'SEK', volumeUnit: 'PKG').save(failOnError:true) 
            def bous = UserSettings.findByUser(btg)?:new UserSettings(user:btg, supplierName:'Boda', currency:'SEK', volumeUnit: 'AM3').save(failOnError:true)

            UserRole.create larandUser, adminRole
            UserRole.create lmUser, adminRole
            UserRole.create btg, adminRole

            UserRole.withSession {
                it.flush()
                it.clear()
            }

        }
        
        // Update woodtrading status with new week and correct volumelist on weekchange
        int id = 1
        def wts = WtStatus.findById(1)?:new WtStatus(id:id).save(failOnError:true)
        
        def destroy = {
        }
    }
}