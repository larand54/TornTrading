package torntrading
import grails.util.Environment
import com.buffer.*
class BootStrap {

    def init = { servletContext ->
		if (Environment.current == Environment.DEVELOPMENT) {
			def pb = new ProdBuffer(sawMill:'Boda',product:'38x125 o/s, Furu', length:4200, packageSize:'Helpaket', store:500, onOrder:200, delivered:50, rest:150, availW01:500, availW02:500, availW03:450, availW04:450, availW05:350, availW06:350, availW07:350, availW08:350, availW09:350, availW10:350).save(failOnError: true)
			
				new ProdBuffer(sawMill:'Boda',product:'38x150 V, Furu', length:4200, packageSize:'Helpaket', store:500, onOrder:200, delivered:50, rest: 150, availW01:500, availW02:500, availW03:450, availW04:450, availW05:350, availW06:350, availW07:350, availW08:350, availW09:350, availW10:350).save(failOnError: true)
				new ProdBuffer(sawMill:'Boda',product:'22x90 o/s, Furu', length:5600, packageSize:'Helpaket', store:500, onOrder:200, delivered:50, rest: 150, availW01:500, availW02:500, availW03:450, availW04:450, availW05:350, availW06:350, availW07:350, availW08:350, availW09:350, availW10:350).save(failOnError: true)
				new ProdBuffer(sawMill:'Boda',product:'38x125 o/s, Furu', length:3800, packageSize:'Helpaket', store:500, onOrder:200, delivered:50, rest: 150, availW01:500, availW02:500, availW03:450, availW04:450, availW05:350, availW06:350, availW07:350, availW08:350, availW09:350, availW10:350).save(failOnError: true)
				new Orders(sawMill:'Boda',customer:'Poznan timber',orderNo:'BP-0001',destination:'Poznan',period:'1701',product:'38x125 o/s, Furu',length:4200,packetSize:'Helpaket',quantity:50,price:200).save()
				new Orders(sawMill:'Boda',customer:'Poznan timber',orderNo:'BP-0002',destination:'Poznan',period:'1701',product:'38x150 V, Furu',length:4200,packetSize:'Helpaket',quantity:50,price:200).save()
				new Orders(sawMill:'Boda',customer:'Poznan timber',orderNo:'BP-0003',destination:'Poznan',period:'1703',product:'22x90 o/s, Furu',length:3800,packetSize:'Helpaket',quantity:150,price:110).save()
				new Orders(sawMill:'Boda',customer:'Poznan timber',orderNo:'BP-0004',destination:'Poznan',period:'1702',product:'38x125 o/s, Furu',length:4200,packetSize:'Helpaket',quantity:50,price:200).save()
				new Orders(sawMill:'Boda',customer:'Poznan timber',orderNo:'BP-0004',destination:'Poznan',period:'1702',product:'38x150 V, Furu',length:4200,packetSize:'Helpaket',quantity:50,price:200).save()
		}
    }
    def destroy = {
    }
}
