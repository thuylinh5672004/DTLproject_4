import React, { useState } from 'react';

export default function ProductCard({ product, canAddToCart }) {
  const [quantity, setQuantity] = useState(1);

  const handleAddToCart = () => {
    if (!canAddToCart) {
      alert('Bạn cần đăng nhập để thêm vào giỏ hàng!');
      return;
    }

    // Lấy giỏ hàng từ localStorage
    let cart = JSON.parse(localStorage.getItem('cart') || '[]');
    // Kiểm tra nếu thuốc đã có trong giỏ thì tăng số lượng
    const existing = cart.find(item => item.tl_mathuoc === product.tl_mathuoc);
    if (existing) {
      existing.quantity += quantity;
    } else {
      cart.push({ ...product, quantity });
    }
    localStorage.setItem('cart', JSON.stringify(cart));
    alert(`Đã thêm ${quantity} ${product.tl_tenthuc} vào giỏ hàng!`);
  };

  return (
    <div className="card h-100">
      <img
        src={product.tl_hinhanh || 'https://via.placeholder.com/150'}
        className="card-img-top"
        alt={product.tl_tenthuc}
        style={{ maxHeight: '200px', objectFit: 'contain' }}
      />
      <div className="card-body d-flex flex-column">
        <h5 className="card-title">{product.tl_tenthuc}</h5>
        <p className="card-text text-truncate">{product.tl_congdung}</p>
        <p className="card-text fw-bold">Giá: {product.tl_giaban.toLocaleString()} đ</p>
        <div className="d-flex align-items-center mb-2">
          <input
            type="number"
            min="1"
            className="form-control me-2"
            style={{ width: '80px' }}
            value={quantity}
            onChange={e => setQuantity(Math.max(1, Number(e.target.value)))}
          />
          <button
            className="btn btn-primary"
            onClick={handleAddToCart}
            disabled={!canAddToCart}
            title={!canAddToCart ? 'Vui lòng đăng nhập để thêm giỏ hàng' : ''}
          >
            Thêm vào giỏ
          </button>
        </div>
      </div>
    </div>
  );
}
