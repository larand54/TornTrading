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
            Locale swedishLocale = new Locale("sv", "SE");
            Locale.setDefault(swedishLocale);
            def pb = new ProdBuffer(sawMill:'Boda',product:'38x125 o/s, Furu', length:4200, volumeAvailable: 500, onOrder: 67, currency: 'EUR', price: '25,5', weekStart: '1711', weekEnd: '1718', status: 'Preliminary', volumeUnit: 'AM3').save(failOnError: true)
			
            new ProdBuffer(sawMill:'Boda',product:'38x150 V, Furu', length:'4200 mm', volumeAvailable: 500, volumeOffered:500, volumeBooked:350, volumeRest: 150, currency: 'EUR', price: 24.7, weekStart: '1704', weekEnd: '1715', status: 'Preliminary', volumeUnit: 'AM3', availW01:500, availW02:500, availW03:450, availW04:450, availW05:350, availW06:350, availW07:350, availW08:350, availW09:350, availW10:350).save(failOnError: true)
            new ProdBuffer(sawMill:'Boda',product:'22x90 o/s, Furu', length:'5,6 m', volumeAvailable: 500, volumeOffered: 500, volumeBooked: 350, volumeRest: 150, currency: 'EUR', price: 26.5, weekStart: '1706', weekEnd: '1718', status: 'Preliminary', volumeUnit: 'AM3', availW01:500, availW02:500, availW03:450, availW04:450, availW05:350, availW06:350, availW07:350, availW08:350, availW09:350, availW10:350).save(failOnError: true)
            new ProdBuffer(sawMill:'Boda',product:'38x125 o/s, Furu', length:'3800mm', volumeAvailable: 500, volumeOffered: 500, volumeBooked: 200, volumeRest: 300, currency: 'SEK', price: 255.5, weekStart: '1708', weekEnd: '1712', status: 'Preliminary', volumeUnit: 'AM3', availW01:500, availW02:500, availW03:450, availW04:450, availW05:350, availW06:350, availW07:350, availW08:350, availW09:350, availW10:350).save(failOnError: true)

            new Orders(sawMill:'Boda',customer:'Poznan timber',orderNo:'BP-0004',destination:'Poznan',period:'1702',product:'38x150 V, Furu',lengthDescr:'4200',packetSize:'Helpaket',quantity:50,currency:'SEK',price:200,status:'Preliminär').save()
            new Orders(sawMill:'Boda',customer:'Poznan timber',orderNo:'BP-0001',destination:'Poznan',period:'1701',product:'38x125 o/s, Furu',lengthDescr:'4200',packetSize:'Helpaket',quantity:50,currency:'SEK',price:200,status:'Preliminär').save()
            new Orders(sawMill:'Boda',customer:'Poznan timber',orderNo:'BP-0002',destination:'Poznan',period:'1701',product:'38x150 V, Furu',lengthDescr:'4200',packetSize:'Helpaket',quantity:50,currency:'SEK',price:200,status:'Avslutad').save()
            new Orders(sawMill:'Boda',customer:'Poznan timber',orderNo:'BP-0003',destination:'Poznan',period:'1703',product:'22x90 o/s, Furu',lengthDescr:'3800',packetSize:'Helpaket',quantity:150,currency:'SEK',price:110,status:'Cancellerad').save()
            new Orders(sawMill:'Boda',customer:'Poznan timber',orderNo:'BP-0004',destination:'Poznan',period:'1702',product:'38x125 o/s, Furu',lengthDescr:'4200',packetSize:'Helpaket',quantity:50,currency:'EUR',price:22,status:'Aktiv').save()
            new Request1(length:4700,width:90,thickness:45,volumeRequested:200,quality:'q123',kd:'75%',fsc: 'false',species: 'Gran',creditRate:0,termsOfDelivery:'Fritt kunden',weekStart:'1704',weekEnd:'1708',company:'Bengtssons trävaror AB',country:'Sweden',city:'Malmö',contactPerson:'Peter Andersson',contactPhone:'+46703500763',contactEmail:'peter@gmail.com',status:'Ny', freeText:'Skriv vaduvill här!').save(failOnError:true)
            new Request1(length:3800,width:120,thickness:35,volumeRequested:180,quality:'FS',kd:'7%',fsc:'true',species: 'Furu',creditRate:0,termsOfDelivery:'Fritt leverantören',weekStart:'1709',weekEnd:'1712',company:'Bengtssons trävaror AB',country:'Sweden',city:'Malmö',contactPerson:'Peter Andersson',contactPhone:'+46703500763',contactEmail:'peter@gmail.com',status:'Ny', freeText:'Skriv nåt mer här!').save(failOnError:true)

            def adminRole = new Role(authority: 'ROLE_ADMIN').save()
            def userRole = new Role(authority: 'ROLE_USER').save()
            def salesRole = new Role(authority: 'ROLE_SALES').save()
            def supplierRole = new Role(authority: 'ROLE_SUPPLIER').save()

            def testUser = new User(username: 'me', password: 'me').save(failOnError:true)
            def guestUser = new User(username: 'he', password: 'he').save(failOnError:true)
            def salesUser = new User(username: 'seller', password: 'sales2017').save(failOnError:true)
            def larandUser = new User(username: 'larand', password: 'mulan2010').save(failOnError:true)
            def lmUser = new User(username: 'lars', password: 'woods2011!').save(failOnError:true)
            def supplierUser1 = new User(username: 'supplier1', password: 'supplier1').save(failOnError:true)
            def supplierUser2 = new User(username: 'supplier2', password: 'supplier2').save(failOnError:true)
            def supplierUser3 = new User(username: 'supplier3', password: 'supplier3').save(failOnError:true)

            def tus = new UserSettings(user:testUser, supplierName:'Boda', currency:'USD', volumeUnit: 'PKG').save(failOnError:true) 
            def gus = new UserSettings(user:guestUser, supplierName:'Boda2', currency:'GBP', volumeUnit: 'AM3').save(failOnError:true) 
            def sus = new UserSettings(user:salesUser, supplierName:'Boda', currency:'EUR', volumeUnit: 'AM3').save(failOnError:true) 
            def laus = new UserSettings(user:larandUser, supplierName:'Boda', currency:'SEK', volumeUnit: 'AM3').save(failOnError:true) 
            def lmus = new UserSettings(user:lmUser, supplierName:'Boda2', currency:'SEK', volumeUnit: 'PKG').save(failOnError:true) 
            def userSettings1 = new UserSettings(user:supplierUser1, supplierName:'supplier1', currency:'USD', volumeUnit: 'PKG').save(failOnError:true) 
            def userSettings2 = new UserSettings(user:supplierUser2, supplierName:'supplier2', currency:'EUR', volumeUnit: 'AM3').save(failOnError:true)
            def userSettings3 = new UserSettings(user:supplierUser3, supplierName:'supplier3', currency:'GBP', volumeUnit: 'PKG').save(failOnError:true) 
/**/
            UserRole.create testUser, adminRole
            UserRole.create guestUser, userRole
            UserRole.create larandUser, adminRole
            UserRole.create lmUser, adminRole
            UserRole.create salesUser, salesRole
            UserRole.create supplierUser1, supplierRole
            UserRole.create supplierUser2, supplierRole
            UserRole.create supplierUser3, supplierRole

            UserRole.withSession {
                it.flush()
                it.clear()
            }

            assert User.count() == 8
            assert Role.count() == 4
            assert UserRole.count() == 8

        }
	if (Environment.current == Environment.PRODUCTION) {
            Locale swedishLocale = new Locale("sv", "SE");
            Locale.setDefault(swedishLocale);
            def pb = new ProdBuffer(sawMill:'Boda',product:'38x125 o/s, Furu', length:4200, volumeAvailable: 500, onOrder: 67, currency: 'EUR', price: '25,5', weekStart: '1711', weekEnd: '1718', status: 'Preliminary', volumeUnit: 'AM3').save(failOnError: true)
			
            new ProdBuffer(sawMill:'Boda',product:'38x150 V, Furu', length:'4200 mm', volumeAvailable: 500, volumeOffered:500, volumeBooked:350, volumeRest: 150, currency: 'EUR', price: 24.7, weekStart: '1704', weekEnd: '1715', status: 'Preliminary', volumeUnit: 'AM3', availW01:500, availW02:500, availW03:450, availW04:450, availW05:350, availW06:350, availW07:350, availW08:350, availW09:350, availW10:350).save(failOnError: true)
            new ProdBuffer(sawMill:'Boda',product:'22x90 o/s, Furu', length:'5,6 m', volumeAvailable: 500, volumeOffered: 500, volumeBooked: 350, volumeRest: 150, currency: 'EUR', price: 26.5, weekStart: '1706', weekEnd: '1718', status: 'Preliminary', volumeUnit: 'AM3', availW01:500, availW02:500, availW03:450, availW04:450, availW05:350, availW06:350, availW07:350, availW08:350, availW09:350, availW10:350).save(failOnError: true)
            new ProdBuffer(sawMill:'Boda',product:'38x125 o/s, Furu', length:'3800mm', volumeAvailable: 500, volumeOffered: 500, volumeBooked: 200, volumeRest: 300, currency: 'SEK', price: 255.5, weekStart: '1708', weekEnd: '1712', status: 'Preliminary', volumeUnit: 'AM3', availW01:500, availW02:500, availW03:450, availW04:450, availW05:350, availW06:350, availW07:350, availW08:350, availW09:350, availW10:350).save(failOnError: true)

            new Orders(sawMill:'Boda',customer:'Poznan timber',orderNo:'BP-0004',destination:'Poznan',period:'1702',product:'38x150 V, Furu',lengthDescr:'4200',packetSize:'Helpaket',quantity:50,currency:'SEK',price:200,status:'Preliminär').save()
            new Orders(sawMill:'Boda',customer:'Poznan timber',orderNo:'BP-0001',destination:'Poznan',period:'1701',product:'38x125 o/s, Furu',lengthDescr:'4200',packetSize:'Helpaket',quantity:50,currency:'SEK',price:200,status:'Preliminär').save()
            new Orders(sawMill:'Boda',customer:'Poznan timber',orderNo:'BP-0002',destination:'Poznan',period:'1701',product:'38x150 V, Furu',lengthDescr:'4200',packetSize:'Helpaket',quantity:50,currency:'SEK',price:200,status:'Avslutad').save()
            new Orders(sawMill:'Boda',customer:'Poznan timber',orderNo:'BP-0003',destination:'Poznan',period:'1703',product:'22x90 o/s, Furu',lengthDescr:'3800',packetSize:'Helpaket',quantity:150,currency:'SEK',price:110,status:'Cancellerad').save()
            new Orders(sawMill:'Boda',customer:'Poznan timber',orderNo:'BP-0004',destination:'Poznan',period:'1702',product:'38x125 o/s, Furu',lengthDescr:'4200',packetSize:'Helpaket',quantity:50,currency:'EUR',price:22,status:'Aktiv').save()
            new Request1(length:4700,width:90,thickness:45,volumeRequested:200,quality:'q123',kd:'75%',fsc: 'false',species: 'Gran',creditRate:0,termsOfDelivery:'Fritt kunden',weekStart:'1704',weekEnd:'1708',company:'Bengtssons trävaror AB',country:'Sweden',city:'Malmö',contactPerson:'Peter Andersson',contactPhone:'+46703500763',contactEmail:'peter@gmail.com',status:'Ny', freeText:'Skriv vaduvill här!').save(failOnError:true)
            new Request1(length:3800,width:120,thickness:35,volumeRequested:180,quality:'FS',kd:'7%',fsc:'true',species: 'Furu',creditRate:0,termsOfDelivery:'Fritt leverantören',weekStart:'1709',weekEnd:'1712',company:'Bengtssons trävaror AB',country:'Sweden',city:'Malmö',contactPerson:'Peter Andersson',contactPhone:'+46703500763',contactEmail:'peter@gmail.com',status:'Ny', freeText:'Skriv nåt mer här!').save(failOnError:true)

            def adminRole = new Role(authority: 'ROLE_ADMIN').save()
            def userRole = new Role(authority: 'ROLE_USER').save()
            def salesRole = new Role(authority: 'ROLE_SALES').save()
            def supplierRole = new Role(authority: 'ROLE_SUPPLIER').save()

            def testUser = new User(username: 'me', password: 'me').save(failOnError:true)
            def guestUser = new User(username: 'he', password: 'he').save(failOnError:true)
            def salesUser = new User(username: 'seller', password: 'sales2017').save(failOnError:true)
            def larandUser = new User(username: 'larand', password: 'mulan2010').save(failOnError:true)
            def lmUser = new User(username: 'lars', password: 'woods2011!').save(failOnError:true)
            def supplierUser1 = new User(username: 'supplier1', password: 'supplier1').save(failOnError:true)
            def supplierUser2 = new User(username: 'supplier2', password: 'supplier2').save(failOnError:true)
            def supplierUser3 = new User(username: 'supplier3', password: 'supplier3').save(failOnError:true)

            def tus = new UserSettings(user:testUser, supplierName:'Boda', currency:'USD', volumeUnit: 'PKG').save(failOnError:true) 
            def gus = new UserSettings(user:guestUser, supplierName:'Boda2', currency:'GBP', volumeUnit: 'AM3').save(failOnError:true) 
            def sus = new UserSettings(user:salesUser, supplierName:'Boda', currency:'EUR', volumeUnit: 'AM3').save(failOnError:true) 
            def laus = new UserSettings(user:larandUser, supplierName:'Boda', currency:'SEK', volumeUnit: 'AM3').save(failOnError:true) 
            def lmus = new UserSettings(user:lmUser, supplierName:'Boda2', currency:'SEK', volumeUnit: 'PKG').save(failOnError:true) 
            def userSettings1 = new UserSettings(user:supplierUser1, supplierName:'supplier1', currency:'USD', volumeUnit: 'PKG').save(failOnError:true) 
            def userSettings2 = new UserSettings(user:supplierUser2, supplierName:'supplier2', currency:'EUR', volumeUnit: 'AM3').save(failOnError:true)
            def userSettings3 = new UserSettings(user:supplierUser3, supplierName:'supplier3', currency:'GBP', volumeUnit: 'PKG').save(failOnError:true) 

            UserRole.create testUser, adminRole
            UserRole.create guestUser, userRole
            UserRole.create larandUser, adminRole
            UserRole.create lmUser, adminRole
            UserRole.create salesUser, salesRole
            UserRole.create supplierUser1, supplierRole
            UserRole.create supplierUser2, supplierRole
            UserRole.create supplierUser3, supplierRole

            UserRole.withSession {
                it.flush()
                it.clear()
            }

            assert User.count() == 8
            assert Role.count() == 4
            assert UserRole.count() == 8

        }
        def destroy = {
        }
    }
}