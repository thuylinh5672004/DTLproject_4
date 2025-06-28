import React from 'react';
import { useCart } from '../components/CartContext';
import { useNavigate } from 'react-router-dom';

const Checkout = () => {
  const { cartItems, clearCart } = useCart();
  const navigate = useNavigate();

  const handlePlaceOrder = () => {
    alert("Đặt hàng thành công!");
    clearCart();
    navigate("/");
  };

  const total = cartItems.reduce((acc, item) => acc + item.quantity * item.tl_giaban, 0);

  return (
    <div className="container mt-4">
      <h2>Xác nhận đơn hàng</h2>
      <ul>
        {cartItems.map((item, idx) => (
          <li key={idx}>
            {item.tl_tenthuc} - SL: {item.quantity} - Tổng: {(item.tl_giaban * item.quantity).toLocaleString()} VNĐ
          </li>
        ))}
      </ul>
      <h4>Tổng cộng: {total.toLocaleString()} VNĐ</h4>
      <button className="btn btn-primary" onClick={handlePlaceOrder}>
        Xác nhận đặt hàng
      </button>
    </div>
  );
};

export default Checkout;
// Note: Ensure you have the necessary components and pages created as per the routes defined above.