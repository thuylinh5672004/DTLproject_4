import React, { useEffect, useState } from 'react';
import { useParams, useNavigate } from 'react-router-dom';
import axios from 'axios';

function ProductForm() {
  const { id } = useParams();
  const navigate = useNavigate();

  const [formData, setFormData] = useState({
    name: '',
    description: '',
    price: ''
  });

  useEffect(() => {
    if (id) {
      axios.get(`http://localhost:3000/api/products/${id}`)
        .then(res => setFormData(res.data))
        .catch(err => console.error(err));
    }
  }, [id]);

  const handleChange = e => {
    setFormData({ ...formData, [e.target.name]: e.target.value });
  };

  const handleSubmit = e => {
    e.preventDefault();
    if (id) {
      axios.put(`http://localhost:3000/api/products/${id}`, formData)
        .then(() => {
          alert('Cập nhật thuốc thành công');
          navigate('/');
        })
        .catch(err => alert('Lỗi cập nhật thuốc'));
    } else {
      axios.post('http://localhost:3000/api/products', formData)
        .then(() => {
          alert('Thêm thuốc thành công');
          navigate('/');
        })
        .catch(err => alert('Lỗi thêm thuốc'));
    }
  };

  return (
    <div className="container mt-4">
      <h1>{id ? 'Sửa thuốc' : 'Thêm thuốc mới'}</h1>
      <form onSubmit={handleSubmit}>
        <div className="mb-3">
          <label className="form-label">Tên thuốc</label>
          <input
            type="text"
            className="form-control"
            name="name"
            value={formData.name}
            onChange={handleChange}
            required />
        </div>
        <div className="mb-3">
          <label className="form-label">Mô tả</label>
          <textarea
            className="form-control"
            name="description"
            value={formData.description}
            onChange={handleChange}
            required />
        </div>
        <div className="mb-3">
          <label className="form-label">Giá</label>
          <input
            type="number"
            className="form-control"
            name="price"
            value={formData.price}
            onChange={handleChange}
            required />
        </div>
        <button type="submit" className="btn btn-primary">{id ? 'Cập nhật' : 'Thêm'}</button>
      </form>
    </div>
  );
}

export default ProductForm;
