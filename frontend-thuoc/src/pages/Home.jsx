import React, { useEffect, useState } from 'react';
import axios from 'axios';
import { Link } from 'react-router-dom';
import Navbar from '../components/Navbar'; // Đúng path thực tế từ Home.jsx
import './Home.css'; // CSS riêng cho Home

function Home() {
  const [products, setProducts] = useState([]);

  useEffect(() => {
    axios.get('http://localhost:3000/api/products')
      .then(res => setProducts(res.data))
      .catch(err => console.error(err));
  }, []);

  return (
    <>
      <Navbar /> {/* Hiển thị navbar ở đầu trang */}
      <div className="container py-4">
        <h1 className="mb-4 text-primary fw-bold">💊 Danh sách thuốc</h1>
        <div className="row g-4">
          {products.map(product => (
            <div className="col-md-4" key={product.tl_mathuoc}>
              <div className="card h-100 shadow-sm product-card">
                <img
                  src={product.tl_hinhanh}
                  alt={product.tl_tenthuc}
                  className="card-img-top product-img"
                />
                <div className="card-body">
                  <h5 className="card-title">{product.tl_tenthuc}</h5>
                  <p className="card-text">
                    {product.tl_congdung
                      ? product.tl_congdung.substring(0, 100) + '...'
                      : 'Không có mô tả'}
                  </p>
                </div>
                <div className="card-footer bg-transparent border-0">
                  <Link to={`/product/${product.tl_mathuoc}`} className="btn btn-outline-primary w-100">
                    Xem chi tiết
                  </Link>
                </div>
              </div>
            </div>
          ))}
        </div>
      </div>
    </>
  );
}

export default Home;
