package torntrading
import grails.util.Environment
import com.buffer.*
class BootStrap {

    def init = { servletContext ->
		if (Environment.current == Environment.DEVELOPMENT) {
			def pb = new ProdBuffer(sawMill:'Boda',product:'38x125 o/s, Furu', length:4200, volumeOffered: 500, volumeBooked: 450, volumeAvailable: 50, currency: 'EUR', price: '25,5', weekStart: '1704', weekEnd: '1709', status: 'Preliminär', volumeUnit: 'AM3', availW01:500, availW02:500, availW03:450, availW04:450, availW05:350, availW06:350, availW07:350, availW08:350, availW09:350, availW10:350).save(failOnError: true)
			
				new ProdBuffer(sawMill:'Boda',product:'38x150 V, Furu', length:4200, volumeOffered:500, volumeBooked:350, volumeAvailable: 150, currency: 'EUR', price: 24.7, weekStart: '1704', weekEnd: '1715', status: 'Preliminär', volumeUnit: 'AM3', availW01:500, availW02:500, availW03:450, availW04:450, availW05:350, availW06:350, availW07:350, availW08:350, availW09:350, availW10:350).save(failOnError: true)
				new ProdBuffer(sawMill:'Boda',product:'22x90 o/s, Furu', length:5600, volumeOffered: 500, volumeBooked: 350, volumeAvailable: 150, currency: 'EUR', price: 26.5, weekStart: '1706', weekEnd: '1718', status: 'Preliminär', volumeUnit: 'AM3', availW01:500, availW02:500, availW03:450, availW04:450, availW05:350, availW06:350, availW07:350, availW08:350, availW09:350, availW10:350).save(failOnError: true)
				new ProdBuffer(sawMill:'Boda',product:'38x125 o/s, Furu', length:3800, volumeOffered: 500, volumeBooked: 200, volumeAvailable: 300, currency: 'SEK', price: 255.5, weekStart: '1708', weekEnd: '1712', status: 'Preliminär', volumeUnit: 'AM3', availW01:500, availW02:500, availW03:450, availW04:450, availW05:350, availW06:350, availW07:350, availW08:350, availW09:350, availW10:350).save(failOnError: true)

				new Orders(sawMill:'Boda',customer:'Poznan timber',orderNo:'BP-0004',destination:'Poznan',period:'1702',product:'38x150 V, Furu',lengthDescr:'4200',packetSize:'Helpaket',quantity:50,currency:'SEK',price:200,status:'Preliminär').save()
				new Orders(sawMill:'Boda',customer:'Poznan timber',orderNo:'BP-0001',destination:'Poznan',period:'1701',product:'38x125 o/s, Furu',lengthDescr:'4200',packetSize:'Helpaket',quantity:50,currency:'SEK',price:200,status:'Preliminär').save()
				new Orders(sawMill:'Boda',customer:'Poznan timber',orderNo:'BP-0002',destination:'Poznan',period:'1701',product:'38x150 V, Furu',lengthDescr:'4200',packetSize:'Helpaket',quantity:50,currency:'SEK',price:200,status:'Avslutad').save()
				new Orders(sawMill:'Boda',customer:'Poznan timber',orderNo:'BP-0003',destination:'Poznan',period:'1703',product:'22x90 o/s, Furu',lengthDescr:'3800',packetSize:'Helpaket',quantity:150,currency:'SEK',price:110,status:'Cancellerad').save()
				new Orders(sawMill:'Boda',customer:'Poznan timber',orderNo:'BP-0004',destination:'Poznan',period:'1702',product:'38x125 o/s, Furu',lengthDescr:'4200',packetSize:'Helpaket',quantity:50,currency:'EUR',price:22,status:'Aktiv').save()
				new Request1(length:4700,width:90,thickness:45,volumeRequested:200,quality:'q123',kd:'75%',weekStart:'1704',weekEnd:'1708',company:'Bengtssons trävaror AB',country:'Sweden',city:'Malmö',contactPerson:'Peter Andersson',contactPhone:'+46703500763',contactEmail:'peter@gmail.com',status:'Preliminär', freeText:'Skriv vaduvill här!').save(failOnError:true)
		}
    }
    def destroy = {
    }
}
