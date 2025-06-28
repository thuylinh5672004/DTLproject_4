import React, { useEffect, useState } from 'react';
import axios from "axios";
import { useParams, useNavigate } from "react-router-dom";
import { useCart } from '../components/CartContext';

const ProductDetail = () => {
  const { id } = useParams();
  const navigate = useNavigate();
  const [product, setProduct] = useState(null);
  const { addToCart } = useCart();

  useEffect(() => {
    const fetchProduct = async () => {
      try {
        const response = await axios.get(`http://localhost:3000/api/products/${id}`);
        setProduct(response.data);
      } catch (error) {
        console.error("Lỗi khi lấy chi tiết thuốc:", error);
      }
    };

    fetchProduct();
  }, [id]);

  const handleAddToCart = () => {
    if (product) {
      addToCart(product);
      navigate("/cart");
    }
  };

  if (!product) {
    return <p className="text-center mt-5">Đang tải thông tin thuốc...</p>;
  }

  return (
    <div style={{
      display: 'flex',
      justifyContent: 'center',
      padding: '2rem',
      backgroundColor: '#f8f9fa',
      minHeight: '100vh'
    }}>
      <div style={{
        maxWidth: '600px',
        width: '100%',
        backgroundColor: '#fff',
        padding: '2rem',
        borderRadius: '10px',
        boxShadow: '0 0 15px rgba(0,0,0,0.1)'
      }}>
        <h2 className="mb-3 text-center">{product.tl_tenthuc}</h2>
        <div className="text-center mb-3">
          <img
            src={product.tl_hinhanh}
            alt={product.tl_tenthuc}
            style={{ width: '250px', borderRadius: '8px' }}
          />
        </div>
        <p><strong>Loại:</strong> {product.tl_loai}</p>
        <p><strong>Công dụng:</strong> {product.tl_congdung}</p>
        <p><strong>Giá bán:</strong> {parseInt(product.tl_giaban).toLocaleString()} VNĐ</p>
        <p><strong>Số lượng tồn:</strong> {product.tl_soluongton}</p>
        <p><strong>Nhà cung cấp:</strong> {product.tl_mancc}</p>
        <div className="text-center mt-4">
          <button
            onClick={handleAddToCart}
            style={{
              padding: '0.5rem 1.5rem',
              backgroundColor: '#007bff',
              color: 'white',
              border: 'none',
              borderRadius: '4px',
              cursor: 'pointer'
            }}
          >
            Thêm vào giỏ
          </button>
        </div>
      </div>
    </div>
  );
};

export default ProductDetail;
