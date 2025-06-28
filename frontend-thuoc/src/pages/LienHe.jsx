import React, { useState } from 'react';
import axios from 'axios';
import { useNavigate } from 'react-router-dom';

export default function LienHe() {
  const [form, setForm] = useState({
    ten: '',
    email: '',
    noidung: ''
  });

  const navigate = useNavigate();

  const handleChange = (e) => {
    const { name, value } = e.target;
    setForm(prev => ({ ...prev, [name]: value }));
  };

  const handleSubmit = async (e) => {
    e.preventDefault();

    if (!form.ten || !form.email || !form.noidung) {
      alert('❌ Vui lòng điền đầy đủ thông tin');
      return;
    }

    try {
      await axios.post('http://localhost:3000/api/lienhe', form);
      alert('✅ Gửi liên hệ thành công!');
      setForm({ ten: '', email: '', noidung: '' });
      navigate('/');
    } catch (err) {
      console.error('❌ Lỗi gửi liên hệ:', err);
      alert('❌ Gửi thất bại!');
    }
  };

  const handleCancel = () => {
    const confirmCancel = window.confirm("Bạn có chắc muốn hủy và quay về trang chủ?");
    if (confirmCancel) {
      navigate('/');
    }
  };

  return (
    <div className="container mt-5" style={{ maxWidth: '600px' }}>
      <h2 className="mb-4 text-center">📨 Liên hệ với chúng tôi</h2>
      <form onSubmit={handleSubmit}>
        <div className="mb-3">
          <label htmlFor="ten" className="form-label">Họ tên</label>
          <input
            type="text"
            className="form-control"
            id="ten"
            name="ten"
            placeholder="Nhập họ tên của bạn"
            value={form.ten}
            onChange={handleChange}
            required
          />
        </div>

        <div className="mb-3">
          <label htmlFor="email" className="form-label">Email</label>
          <input
            type="email"
            className="form-control"
            id="email"
            name="email"
            placeholder="example@email.com"
            value={form.email}
            onChange={handleChange}
            required
          />
        </div>

        <div className="mb-3">
          <label htmlFor="noidung" className="form-label">Nội dung liên hệ</label>
          <textarea
            className="form-control"
            id="noidung"
            name="noidung"
            rows="4"
            placeholder="Nhập nội dung bạn muốn liên hệ..."
            value={form.noidung}
            onChange={handleChange}
            required
          />
        </div>

        <div className="d-flex justify-content-between">
          <button type="button" className="btn btn-secondary" onClick={handleCancel}>
            Quay về trang chủ
          </button>
          <button type="submit" className="btn btn-success">
            Gửi liên hệ
          </button>
        </div>
      </form>
    </div>
  );
}
