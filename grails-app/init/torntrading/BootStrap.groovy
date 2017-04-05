package torntrading
import grails.util.Environment
import com.buffer.*
import com.torntrading.security.Role
import com.torntrading.security.User
import com.torntrading.security.UserRole
import com.torntrading.portal.UserSettings


class BootStrap {

    def init = { servletContext ->
	if (Environment.current == Environment.DEVELOPMENT) {
            Locale defLocale = new Locale("en", "GB");
            Locale.setDefault(defLocale);
            
            ProdBuffer.findByProduct('38x125 o/s, Furu')?:new ProdBuffer(sawMill:'Boda',product:'38x125 o/s, Furu', length:4200, volumeAvailable: 500, onOrder: 67, currency: 'EUR', kd: '7%',grade: 'S/F',priceFSC:'250.55',  weekStart: '1711', weekEnd: '1718', status: 'Preliminary', volumeUnit: 'AM3').save(failOnError: true)
			
            ProdBuffer.findByProduct('38x150 V, Furu')?:new ProdBuffer(sawMill:'Boda',product:'38x150 V, Furu', length:'4200 mm', volumeAvailable: 750, onOrder: 150, currency: 'EUR',  weekStart: '1704', weekEnd: '1715', status: 'Preliminary', volumeUnit: 'AM3', kd: '6%', pricePEFC: '355').save()
            ProdBuffer.findByProduct('22x90 o/s, Furu')?:new ProdBuffer(sawMill:'Boda',product:'22x90 o/s, Furu', length:'5,6 m', volumeAvailable: 400, , currency: 'EUR',  weekStart: '1706', weekEnd: '1718', status: 'Preliminary', volumeUnit: 'AM3', kd: '11%', priceCW: '338').save()
            ProdBuffer.findByProduct('38x125 o/s, Gran')?:new ProdBuffer(sawMill:'Boda',product:'38x125 o/s, Gran', length:'3800mm', volumeAvailable: 375, onOrder: 90, volumeBooked: 200, volumeRest: 300, currency: 'SEK',  weekStart: '1708', weekEnd: '1712', status: 'Preliminary', volumeUnit: 'AM3', kd: '9%', priceUC: '305').save()
/**/
            Orders.findByProduct('38x150 V, Furu')?:new Orders(sawMill:'Boda',customer:'Poznan timber',orderNo:'BP-0004',destination:'Poznan',period:'1702',product:'38x150 V, Furu',lengthDescr:'4200',packetSize:'Helpaket',quantity:50,currency:'SEK',price:200,status:'Preliminär').save()
            Orders.findByProduct('38x125 o/s, Furu')?:new Orders(sawMill:'Boda',customer:'Poznan timber',orderNo:'BP-0001',destination:'Poznan',period:'1701',product:'38x125 o/s, Furu',lengthDescr:'4200',packetSize:'Helpaket',quantity:50,currency:'SEK',price:200,status:'Preliminär').save()
            Orders.findByProduct('45x150 V, Furu')?:new Orders(sawMill:'Boda',customer:'Poznan timber',orderNo:'BP-0002',destination:'Poznan',period:'1701',product:'45x150 V, Furu',lengthDescr:'4200',packetSize:'Helpaket',quantity:50,currency:'SEK',price:200,status:'Avslutad').save()
            Orders.findByProduct('22x90 o/s, Furu')?:new Orders(sawMill:'Boda',customer:'Poznan timber',orderNo:'BP-0003',destination:'Poznan',period:'1703',product:'22x90 o/s, Furu',lengthDescr:'3800',packetSize:'Helpaket',quantity:150,currency:'SEK',price:110,status:'Cancellerad').save()
            Orders.findByProduct('45x125 o/s, Furu')?:new Orders(sawMill:'Boda',customer:'Poznan timber',orderNo:'BP-0004',destination:'Poznan',period:'1702',product:'45x125 o/s, Furu',lengthDescr:'4200',packetSize:'Helpaket',quantity:50,currency:'EUR',price:22,status:'Aktiv').save()
            Request1.findByLength(4700)?:new Request1(length:4700,width:90,thickness:45,volumeRequested:200,quality:'q123',kd:'75%',fsc: 'false',species: 'Gran',creditRate:0,termsOfDelivery:'Fritt kunden',weekStart:'1704',weekEnd:'1708',company:'Bengtssons trävaror AB',country:'Sweden',city:'Malmö',contactPerson:'Peter Andersson',contactPhone:'+46703500763',contactEmail:'peter@gmail.com',status:'Ny', freeText:'Skriv vaduvill här!').save(failOnError:true)
            Request1.findByLength(3800)?:new Request1(length:3800,width:120,thickness:35,volumeRequested:180,quality:'FS',kd:'7%',fsc:'true',species: 'Furu',creditRate:0,termsOfDelivery:'Fritt leverantören',weekStart:'1709',weekEnd:'1712',company:'Bengtssons trävaror AB',country:'Sweden',city:'Malmö',contactPerson:'Peter Andersson',contactPhone:'+46703500763',contactEmail:'peter@gmail.com',status:'Ny', freeText:'Skriv nåt mer här!').save(failOnError:true)

            def adminRole = Role.findByAuthority('ROLE_ADMIN')?:new Role(authority: 'ROLE_ADMIN').save(failOnError:true)
            
            def userRole = Role.findByAuthority('ROLE_USER')?:new Role(authority: 'ROLE_USER').save(failOnError:true)
            def salesRole = Role.findByAuthority('ROLE_SALES')?:new Role(authority: 'ROLE_SALES').save(failOnError:true)
            def supplierRole = Role.findByAuthority('ROLE_SUPPLIER')?:new Role(authority: 'ROLE_SUPPLIER').save(failOnError:true)

            def testUser = User.findByUsername('me')?:new User(username: 'me', password: 'me').save(failOnError:true)
            def guestUser = User.findByUsername('he')?:new User(username: 'he', password: 'he').save(failOnError:true)
            def salesUser = User.findByUsername('seller')?:new User(username: 'seller', password: 'sales2017').save(failOnError:true)
            def larandUser = User.findByUsername('larand')?:new User(username: 'larand', password: 'mulan2010').save(failOnError:true)
            def lmUser = User.findByUsername('lars')?:new User(username: 'lars', password: 'woods2011!').save(failOnError:true)
            def supplierUser1 = User.findByUsername('supplier1')?:new User(username: 'supplier1', password: 'supplier1').save(failOnError:true)
            def supplierUser2 = User.findByUsername('supplier2')?:new User(username: 'supplier2', password: 'supplier2').save(failOnError:true)
            def supplierUser3 = User.findByUsername('supplier3')?:new User(username: 'supplier3', password: 'supplier3').save(failOnError:true)
            def borje = User.findByUsername('borje')?:new User(username: 'borje', password: 'borje').save(failOnError:true)
            def joakim = User.findByUsername('joakim')?:new User(username: 'joakim', password: 'joakim').save(failOnError:true)

            def tus = UserSettings.findByUser(testUser)?:new UserSettings(user:testUser, supplierName:'Boda', currency:'USD', volumeUnit: 'PKG').save(failOnError:true) 
            def gus = UserSettings.findByUser(guestUser)?:new UserSettings(user:guestUser, supplierName:'Boda2', currency:'GBP', volumeUnit: 'AM3').save(failOnError:true) 
            def sus = UserSettings.findByUser(salesUser)?:new UserSettings(user:salesUser, supplierName:'Boda', currency:'EUR', volumeUnit: 'AM3').save(failOnError:true) 
            def laus = UserSettings.findByUser(larandUser)?:new UserSettings(user:larandUser, supplierName:'Boda', currency:'SEK', volumeUnit: 'AM3').save(failOnError:true) 
            def lmus = UserSettings.findByUser(lmUser)?:new UserSettings(user:lmUser, supplierName:'Boda2', currency:'SEK', volumeUnit: 'PKG').save(failOnError:true) 
            def userSettings1 = UserSettings.findByUser(supplierUser1)?:new UserSettings(user:supplierUser1, supplierName:'supplier1', currency:'USD', volumeUnit: 'PKG').save(failOnError:true) 
            def userSettings2 = UserSettings.findByUser(supplierUser2)?:new UserSettings(user:supplierUser2, supplierName:'supplier2', currency:'EUR', volumeUnit: 'AM3').save(failOnError:true)
            def userSettings3 = UserSettings.findByUser(supplierUser3)?:new UserSettings(user:supplierUser3, supplierName:'supplier3', currency:'GBP', volumeUnit: 'PKG').save(failOnError:true) 
            def jous = UserSettings.findByUser(joakim)?:new UserSettings(user:joakim, supplierName:'Boda', currency:'SEK', volumeUnit: 'AM3').save(failOnError:true)
            def bous = UserSettings.findByUser(borje)?:new UserSettings(user:borje, supplierName:'Boda', currency:'SEK', volumeUnit: 'AM3').save(failOnError:true)

            UserRole.create testUser, adminRole
            UserRole.create guestUser, userRole
            UserRole.create larandUser, adminRole
            UserRole.create lmUser, adminRole
            UserRole.create salesUser, salesRole
            UserRole.create supplierUser1, supplierRole
            UserRole.create supplierUser2, supplierRole
            UserRole.create supplierUser3, supplierRole
            UserRole.create joakim, adminRole
            UserRole.create borje, adminRole

            UserRole.withSession {
                it.flush()
                it.clear()
            }

            assert User.count() == 10
            assert Role.count() == 4
            assert UserRole.count() == 10

        }
	if (Environment.current == Environment.PRODUCTION) {
            Locale.setDefault( new Locale("en", "GB"));
            ProdBuffer.findByProduct('38x125 o/s, Furu')?:new ProdBuffer(sawMill:'Boda',product:'38x125 o/s, Furu', length:4200, volumeAvailable: 500, onOrder: 67, currency: 'EUR', kd: '7%',grade: 'S/F',priceFSC:'250.55',  weekStart: '1711', weekEnd: '1718', status: 'Preliminary', volumeUnit: 'AM3').save(failOnError: true)
			
            ProdBuffer.findByProduct('38x150 V, Furu')?:new ProdBuffer(sawMill:'Boda',product:'38x150 V, Furu', length:'4200 mm', volumeAvailable: 750, onOrder: 150, currency: 'EUR',  weekStart: '1704', weekEnd: '1715', status: 'Preliminary', volumeUnit: 'AM3', kd: '6%', pricePEFC: '355').save()
            ProdBuffer.findByProduct('22x90 o/s, Furu')?:new ProdBuffer(sawMill:'Boda',product:'22x90 o/s, Furu', length:'5,6 m', volumeAvailable: 400, , currency: 'EUR',  weekStart: '1706', weekEnd: '1718', status: 'Preliminary', volumeUnit: 'AM3', kd: '11%', priceCW: '338').save()
            ProdBuffer.findByProduct('38x125 o/s, Gran')?:new ProdBuffer(sawMill:'Boda',product:'38x125 o/s, Gran', length:'3800mm', volumeAvailable: 375, onOrder: 90, volumeBooked: 200, volumeRest: 300, currency: 'SEK',  weekStart: '1708', weekEnd: '1712', status: 'Preliminary', volumeUnit: 'AM3', kd: '9%', priceUC: '305').save()
/**/
            Orders.findByProduct('38x150 V, Furu')?:new Orders(sawMill:'Boda',customer:'Poznan timber',orderNo:'BP-0004',destination:'Poznan',period:'1702',product:'38x150 V, Furu',lengthDescr:'4200',packetSize:'Helpaket',quantity:50,currency:'SEK',price:200,status:'Preliminär').save()
            Orders.findByProduct('38x125 o/s, Furu')?:new Orders(sawMill:'Boda',customer:'Poznan timber',orderNo:'BP-0001',destination:'Poznan',period:'1701',product:'38x125 o/s, Furu',lengthDescr:'4200',packetSize:'Helpaket',quantity:50,currency:'SEK',price:200,status:'Preliminär').save()
            Orders.findByProduct('45x150 V, Furu')?:new Orders(sawMill:'Boda',customer:'Poznan timber',orderNo:'BP-0002',destination:'Poznan',period:'1701',product:'45x150 V, Furu',lengthDescr:'4200',packetSize:'Helpaket',quantity:50,currency:'SEK',price:200,status:'Avslutad').save()
            Orders.findByProduct('22x90 o/s, Furu')?:new Orders(sawMill:'Boda',customer:'Poznan timber',orderNo:'BP-0003',destination:'Poznan',period:'1703',product:'22x90 o/s, Furu',lengthDescr:'3800',packetSize:'Helpaket',quantity:150,currency:'SEK',price:110,status:'Cancellerad').save()
            Orders.findByProduct('45x125 o/s, Furu')?:new Orders(sawMill:'Boda',customer:'Poznan timber',orderNo:'BP-0004',destination:'Poznan',period:'1702',product:'45x125 o/s, Furu',lengthDescr:'4200',packetSize:'Helpaket',quantity:50,currency:'EUR',price:22,status:'Aktiv').save()
            Request1.findByLength(4700)?:new Request1(length:4700,width:90,thickness:45,volumeRequested:200,quality:'q123',kd:'75%',fsc: 'false',species: 'Gran',creditRate:0,termsOfDelivery:'Fritt kunden',weekStart:'1704',weekEnd:'1708',company:'Bengtssons trävaror AB',country:'Sweden',city:'Malmö',contactPerson:'Peter Andersson',contactPhone:'+46703500763',contactEmail:'peter@gmail.com',status:'Ny', freeText:'Skriv vaduvill här!').save(failOnError:true)
            Request1.findByLength(3800)?:new Request1(length:3800,width:120,thickness:35,volumeRequested:180,quality:'FS',kd:'7%',fsc:'true',species: 'Furu',creditRate:0,termsOfDelivery:'Fritt leverantören',weekStart:'1709',weekEnd:'1712',company:'Bengtssons trävaror AB',country:'Sweden',city:'Malmö',contactPerson:'Peter Andersson',contactPhone:'+46703500763',contactEmail:'peter@gmail.com',status:'Ny', freeText:'Skriv nåt mer här!').save(failOnError:true)

            def adminRole = Role.findByAuthority('ROLE_ADMIN')?:new Role(authority: 'ROLE_ADMIN').save(failOnError:true)
            
            def userRole = Role.findByAuthority('ROLE_USER')?:new Role(authority: 'ROLE_USER').save(failOnError:true)
            def salesRole = Role.findByAuthority('ROLE_SALES')?:new Role(authority: 'ROLE_SALES').save(failOnError:true)
            def supplierRole = Role.findByAuthority('ROLE_SUPPLIER')?:new Role(authority: 'ROLE_SUPPLIER').save(failOnError:true)

            def testUser = User.findByUsername('me')?:new User(username: 'me', password: 'me').save(failOnError:true)
            def guestUser = User.findByUsername('he')?:new User(username: 'he', password: 'he').save(failOnError:true)
            def salesUser = User.findByUsername('seller')?:new User(username: 'seller', password: 'sales2017').save(failOnError:true)
            def larandUser = User.findByUsername('larand')?:new User(username: 'larand', password: 'mulan2010').save(failOnError:true)
            def lmUser = User.findByUsername('lars')?:new User(username: 'lars', password: 'woods2011!').save(failOnError:true)
            def supplierUser1 = User.findByUsername('supplier1')?:new User(username: 'supplier1', password: 'supplier1').save(failOnError:true)
            def supplierUser2 = User.findByUsername('supplier2')?:new User(username: 'supplier2', password: 'supplier2').save(failOnError:true)
            def supplierUser3 = User.findByUsername('supplier3')?:new User(username: 'supplier3', password: 'supplier3').save(failOnError:true)
            def borje = User.findByUsername('borje')?:new User(username: 'borje', password: 'borje').save(failOnError:true)
            def joakim = User.findByUsername('joakim')?:new User(username: 'joakim', password: 'joakim').save(failOnError:true)

            def tus = UserSettings.findByUser(testUser)?:new UserSettings(user:testUser, supplierName:'Boda', currency:'USD', volumeUnit: 'PKG').save(failOnError:true) 
            def gus = UserSettings.findByUser(guestUser)?:new UserSettings(user:guestUser, supplierName:'Boda2', currency:'GBP', volumeUnit: 'AM3').save(failOnError:true) 
            def sus = UserSettings.findByUser(salesUser)?:new UserSettings(user:salesUser, supplierName:'Boda', currency:'EUR', volumeUnit: 'AM3').save(failOnError:true) 
            def laus = UserSettings.findByUser(larandUser)?:new UserSettings(user:larandUser, supplierName:'Boda', currency:'SEK', volumeUnit: 'AM3').save(failOnError:true) 
            def lmus = UserSettings.findByUser(lmUser)?:new UserSettings(user:lmUser, supplierName:'Boda2', currency:'SEK', volumeUnit: 'PKG').save(failOnError:true) 
            def userSettings1 = UserSettings.findByUser(supplierUser1)?:new UserSettings(user:supplierUser1, supplierName:'supplier1', currency:'USD', volumeUnit: 'PKG').save(failOnError:true) 
            def userSettings2 = UserSettings.findByUser(supplierUser2)?:new UserSettings(user:supplierUser2, supplierName:'supplier2', currency:'EUR', volumeUnit: 'AM3').save(failOnError:true)
            def userSettings3 = UserSettings.findByUser(supplierUser3)?:new UserSettings(user:supplierUser3, supplierName:'supplier3', currency:'GBP', volumeUnit: 'PKG').save(failOnError:true) 
            def jous = UserSettings.findByUser(joakim)?:new UserSettings(user:joakim, supplierName:'Boda', currency:'SEK', volumeUnit: 'AM3').save(failOnError:true)
            def bous = UserSettings.findByUser(borje)?:new UserSettings(user:borje, supplierName:'Boda', currency:'SEK', volumeUnit: 'AM3').save(failOnError:true)

            UserRole.create testUser, adminRole
            UserRole.create guestUser, userRole
            UserRole.create larandUser, adminRole
            UserRole.create lmUser, adminRole
            UserRole.create salesUser, salesRole
            UserRole.create supplierUser1, supplierRole
            UserRole.create supplierUser2, supplierRole
            UserRole.create supplierUser3, supplierRole
            UserRole.create joakim, adminRole
            UserRole.create borje, adminRole

            UserRole.withSession {
                it.flush()
                it.clear()
            }

            assert User.count() == 10
            assert Role.count() == 4
            assert UserRole.count() == 10

        }
        
        def destroy = {
        }
    }
}