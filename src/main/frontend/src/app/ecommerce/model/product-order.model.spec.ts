import { ProductOrder } from './product-order.model';
import { Product } from './product.model';
describe('ProductOrder', () => {
        it('should create an instance', () => {
                expect(new ProductOrder(new Product(1, 'a', 5, 'b'), 5)).toBeTruthy();
        });
});
