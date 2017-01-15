package com.buffer

class Orders_and_StoreController {

    def list(Integer max) { 
//        params.max = Math.min(max ?: 10, 100)
//       respond Orders.list(params), model:[ordersCount: Orders.count(), ProdBuffer.list(params), model:[prodBufferCount: ProdBuffer.count()]]
		def orders = Orders.list()
		def prodBuffer = ProdBuffer.list()
		[orders: orders, prodBuffer: prodBuffer]
	}
	
	def show_prodbuffer() {
		redirect(controller: "prodBuffer",action: "show", id: params.id)
	}

	def show_order() {
		redirect(controller: "orders",action: "show", id: params.id)
	}
	
}
