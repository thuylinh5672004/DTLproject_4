import React from 'react';
import { useParams, Link } from 'react-router-dom';
import tuVanData from '../data/tuVanData';

function TuvanDetail() {
  const { id } = useParams();
  const item = tuVanData.find((t) => t.id === parseInt(id));

  if (!item) {
    return <div className="container mt-4">❌ Không tìm thấy thông tin tư vấn.</div>;
  }

  return (
    <div className="container mt-4">
      <Link to="/tuvan" className="btn btn-secondary mb-3">← Quay lại danh sách</Link>

      <h2 className="mb-3">{item.title}</h2>

      <img
        src={item.image}
        className="img-fluid rounded mb-3"
        alt={item.title}
        style={{ maxHeight: '400px', objectFit: 'cover' }}
      />

      <p className="lead">{item.detail}</p>

      <div className="mb-5">
        <div style={{ position: 'relative', paddingBottom: '56.25%', height: 0 }}>
          <iframe
            src={item.video}
            title={item.title}
            frameBorder="0"
            allow="accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture"
            allowFullScreen
            style={{
              position: 'absolute',
              top: 0,
              left: 0,
              width: '100%',
              height: '100%',
            }}
          ></iframe>
        </div>
      </div>
    </div>
  );
}

export default TuvanDetail;
