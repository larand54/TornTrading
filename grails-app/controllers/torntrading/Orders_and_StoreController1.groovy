package torntrading
import com.buffer.Orders
import com.buffer.ProdBuffer

class Orders_and_StoreController {

    def list(Integer max) { 
//        params.max = Math.min(max ?: 10, 100)
//       respond Orders.list(params), model:[ordersCount: Orders.count(), ProdBuffer.list(params), model:[prodBufferCount: ProdBuffer.count()]]
		def orders = Orders.list()
		def prodBuffer = ProdBuffer.list()
		[orders: orders, prodBuffer: prodBuffer]
	}
}
