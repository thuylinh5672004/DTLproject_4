import React, { useEffect, useState } from 'react';
import axios from 'axios';

function AdminCustomers() {
  const [customers, setCustomers] = useState([]);
  const [editingCustomer, setEditingCustomer] = useState(null);
  const [form, setForm] = useState({
    tl_makh: '',
    tl_tenkh: '',
    tl_email: '',
    tl_sdt: '',
    tl_diachi: '',
    tl_matkhau: '',
  });

  const fetchCustomers = () => {
    axios.get('http://localhost:3000/api/customers')
      .then(res => setCustomers(res.data))
      .catch(err => console.error(err));
  };

  useEffect(() => {
    fetchCustomers();
  }, []);

  const handleChange = e => {
    const { name, value } = e.target;
    setForm(prev => ({ ...prev, [name]: value }));
  };

  const handleSubmit = async (e) => {
    e.preventDefault();
    try {
      if (editingCustomer) {
        await axios.put(`http://localhost:3000/api/customers/${form.tl_makh}`, form);
        alert('✅ Cập nhật khách hàng thành công!');
      } else {
        await axios.post('http://localhost:3000/api/customers', form);
        alert('✅ Thêm khách hàng thành công!');
      }
    } catch (error) {
      alert('❌ Lỗi khi lưu khách hàng!');
    }

    setForm({
      tl_makh: '',
      tl_tenkh: '',
      tl_email: '',
      tl_sdt: '',
      tl_diachi: '',
      tl_matkhau: '',
    });
    setEditingCustomer(null);
    fetchCustomers();
  };

  const handleEdit = (customer) => {
    setEditingCustomer(customer);
    setForm({ ...customer });
  };

  const handleDelete = async (id) => {
    if (window.confirm('Bạn có chắc muốn xóa khách hàng này?')) {
      try {
        await axios.delete(`http://localhost:3000/api/customers/${id}`);
        alert('🗑️ Xóa khách hàng thành công!');
        fetchCustomers();
      } catch (error) {
        alert('❌ Xóa khách hàng thất bại!');
      }
    }
  };

  return (
    <div className="container mt-4">
      <h2 className="mb-4 text-center">Quản lý khách hàng</h2>

      {/* 🔵 Form thêm/sửa khách hàng */}
      <form className="row g-3 mb-5" onSubmit={handleSubmit}>
        <div className="col-md-2">
          <input
            type="text"
            className="form-control"
            name="tl_makh"
            value={form.tl_makh}
            onChange={handleChange}
            placeholder="Mã KH"
            required={!editingCustomer}
            disabled={!!editingCustomer}
          />
        </div>
        <div className="col-md-2">
          <input
            type="text"
            className="form-control"
            name="tl_tenkh"
            value={form.tl_tenkh}
            onChange={handleChange}
            placeholder="Họ tên"
            required
          />
        </div>
        <div className="col-md-2">
          <input
            type="email"
            className="form-control"
            name="tl_email"
            value={form.tl_email}
            onChange={handleChange}
            placeholder="Email"
            required
          />
        </div>
        <div className="col-md-2">
          <input
            type="text"
            className="form-control"
            name="tl_sdt"
            value={form.tl_sdt}
            onChange={handleChange}
            placeholder="SĐT"
          />
        </div>
        <div className="col-md-2">
          <input
            type="text"
            className="form-control"
            name="tl_diachi"
            value={form.tl_diachi}
            onChange={handleChange}
            placeholder="Địa chỉ"
          />
        </div>
        <div className="col-md-2">
          <input
            type="password"
            className="form-control"
            name="tl_matkhau"
            value={form.tl_matkhau}
            onChange={handleChange}
            placeholder="Mật khẩu"
            required
          />
        </div>
        <div className="col-12 text-end">
          <button type="submit" className="btn btn-primary">
            {editingCustomer ? 'Cập nhật' : 'Thêm mới'}
          </button>
          {editingCustomer && (
            <button
              type="button"
              className="btn btn-secondary ms-2"
              onClick={() => {
                setEditingCustomer(null);
                setForm({
                  tl_makh: '',
                  tl_tenkh: '',
                  tl_email: '',
                  tl_sdt: '',
                  tl_diachi: '',
                  tl_matkhau: '',
                });
              }}
            >
              Huỷ
            </button>
          )}
        </div>
      </form>

      {/* 🟨 Bảng khách hàng */}
      <div className="table-responsive">
        <table className="table table-bordered table-hover align-middle">
          <thead className="table-dark text-center">
            <tr>
              <th>Mã KH</th>
              <th>Họ tên</th>
              <th>Email</th>
              <th>SĐT</th>
              <th>Địa chỉ</th>
              <th>Mật khẩu</th>
              <th>Hành động</th>
            </tr>
          </thead>
          <tbody>
            {customers.map((c) => (
              <tr key={c.tl_makh}>
                <td>{c.tl_makh}</td>
                <td>{c.tl_tenkh}</td>
                <td>{c.tl_email}</td>
                <td>{c.tl_sdt}</td>
                <td>{c.tl_diachi}</td>
                <td>{c.tl_matkhau}</td>
                <td className="text-center">
                  <button className="btn btn-sm btn-warning me-2" onClick={() => handleEdit(c)}>
                    Sửa
                  </button>
                  <button className="btn btn-sm btn-danger" onClick={() => handleDelete(c.tl_makh)}>
                    Xoá
                  </button>
                </td>
              </tr>
            ))}
            {customers.length === 0 && (
              <tr>
                <td colSpan="7" className="text-center">Không có dữ liệu</td>
              </tr>
            )}
          </tbody>
        </table>
      </div>
    </div>
  );
}

export default AdminCustomers;
// This code is a React component for managing customers in an admin dashboard.