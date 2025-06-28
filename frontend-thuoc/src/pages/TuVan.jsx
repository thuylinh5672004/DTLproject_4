import React from 'react';
import { Link } from 'react-router-dom';
import tuVanData from '../data/tuVanData';

function Tuvan() {
  return (
    <div className="container mt-4">
      <h2 className="text-center mb-4">🩺 Tư vấn sức khỏe</h2>
      <div className="row">
        {tuVanData.map((item) => (
          <div key={item.id} className="col-md-4 mb-4">
            <div className="card h-100 shadow">
              <img
                src={item.image}
                className="card-img-top"
                alt={item.title}
                style={{ height: '200px', objectFit: 'cover' }}
              />
              <div className="card-body">
                <h5 className="card-title">{item.title}</h5>
                <p className="card-text">{item.shortDesc}</p>
                <Link to={`/tuvan/${item.id}`} className="btn btn-primary">
                  Xem thêm
                </Link>
              </div>
            </div>
          </div>
        ))}
      </div>

      <div className="text-center mt-4">
        <Link to="/" className="btn btn-secondary">
          ⬅ Quay về trang chủ
        </Link>
      </div>
    </div>
  );
}

export default Tuvan;
