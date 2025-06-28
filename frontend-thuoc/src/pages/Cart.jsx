import React from 'react';
import { Link, useNavigate } from 'react-router-dom';
import { useCart } from '../components/CartContext';
import '../App.css';

function Cart() {
  const { cartItems, removeFromCart, clearCart, updateQuantity } = useCart();
  const navigate = useNavigate();

  const formatCurrency = (value) => {
    return parseInt(value).toLocaleString() + ' VNĐ';
  };

  if (cartItems.length === 0) {
    return (
      <div className="container mt-4">
        <h2>Giỏ hàng trống</h2>
        <Link to="/" className="btn btn-primary mt-2">Tiếp tục mua hàng</Link>
      </div>
    );
  }

  const totalPrice = cartItems.reduce(
    (total, item) => total + item.quantity * parseInt(item.tl_giaban),
    0
  );

  return (
    <div className="container mt-4">
      <h2>Giỏ hàng</h2>
      <table className="table">
        <thead>
          <tr>
            <th>Hình ảnh</th>
            <th>Tên thuốc</th>
            <th>Số lượng</th>
            <th>Giá</th>
            <th>Thành tiền</th>
            <th>Thao tác</th>
          </tr>
        </thead>
        <tbody>
          {cartItems.map((item, idx) => (
            <tr key={idx}>
              <td>
                <img src={item.tl_hinhanh} alt={item.tl_tenthuc} style={{ width: '80px' }} />
              </td>
              <td>{item.tl_tenthuc}</td>
              <td>
                <input
                  type="number"
                  min="1"
                  value={item.quantity}
                  onChange={(e) => updateQuantity(item.tl_mathuoc, e.target.value)}
                  style={{ width: '60px' }}
                />
              </td>
              <td>{formatCurrency(item.tl_giaban)}</td>
              <td>{formatCurrency(item.quantity * item.tl_giaban)}</td>
              <td>
                <button
                  className="btn btn-danger btn-sm"
                  onClick={() => removeFromCart(item.tl_mathuoc)}
                >
                  Xoá
                </button>
              </td>
            </tr>
          ))}
        </tbody>
      </table>

      <h4 className="mt-3">Tổng cộng: {formatCurrency(totalPrice)}</h4>

      <div className="mt-4 d-flex flex-wrap gap-2">
        <button className="btn btn-success" onClick={() => navigate('/checkout')}>
          Đặt thuốc
        </button>
        <button className="btn btn-secondary" onClick={clearCart}>
          Xoá toàn bộ
        </button>
        <Link to="/" className="btn btn-outline-primary">
          Về trang chủ
        </Link>
      </div>
    </div>
  );
}

export default Cart;
// This code is a React component for displaying and managing a shopping cart in a pharmacy management system.