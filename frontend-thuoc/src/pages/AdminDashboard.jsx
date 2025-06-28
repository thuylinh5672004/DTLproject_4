import React, { useEffect, useState } from 'react';
import axios from 'axios';
import { Link, useNavigate } from 'react-router-dom';

function AdminDashboard() {
  const [products, setProducts] = useState([]);
  const [users, setUsers] = useState([]);
  const [orders, setOrders] = useState([]);
  const [customers, setCustomers] = useState([]);
  const [contacts, setContacts] = useState([]);
  const navigate = useNavigate();

  useEffect(() => {
    const user = JSON.parse(localStorage.getItem('user'));
    if (!user || user.tl_role !== 'admin') {
      navigate('/');
      return;
    }

    axios.get('http://localhost:3000/api/products')
      .then(res => setProducts(res.data))
      .catch(err => console.error(err));

    axios.get('http://localhost:3000/api/users')
      .then(res => setUsers(res.data))
      .catch(err => console.error(err));

    axios.get('http://localhost:3000/api/orders')
      .then(res => setOrders(res.data))
      .catch(err => console.error(err));

    axios.get('http://localhost:3000/api/customers')
      .then(res => setCustomers(res.data))
      .catch(err => console.error(err));

    axios.get('http://localhost:3000/api/contacts')
      .then(res => setContacts(res.data))
      .catch(err => console.error(err));
  }, [navigate]);

  const handleLogout = () => {
    localStorage.removeItem('user');
    navigate('/');
  };

  return (
    <div className="container mt-4">
      <div className="d-flex justify-content-between align-items-center mb-3">
        <h1 className="mb-0">Bảng điều khiển Quản trị</h1>
        <button className="btn btn-outline-danger" onClick={handleLogout}>Đăng xuất</button>
      </div>

      {/* Thống kê */}
      <div className="row text-white mb-5">
        <div className="col-md-2">
          <div className="card bg-primary shadow">
            <div className="card-body">
              <h5 className="card-title">Sản phẩm</h5>
              <p className="display-6">{products.length}</p>
            </div>
          </div>
        </div>
        <div className="col-md-2">
          <div className="card bg-success shadow">
            <div className="card-body">
              <h5 className="card-title">Đơn hàng</h5>
              <p className="display-6">{orders.length}</p>
            </div>
          </div>
        </div>
        <div className="col-md-2">
          <div className="card bg-warning shadow">
            <div className="card-body">
              <h5 className="card-title">Khách hàng</h5>
              <p className="display-6">{customers.length}</p>
            </div>
          </div>
        </div>
        <div className="col-md-2">
          <div className="card bg-secondary shadow">
            <div className="card-body">
              <h5 className="card-title">Tài khoản</h5>
              <p className="display-6">{users.length}</p>
            </div>
          </div>
        </div>
        <div className="col-md-2">
          <div className="card bg-dark shadow">
            <div className="card-body">
              <h5 className="card-title">Liên hệ</h5>
              <p className="display-6">{contacts.length}</p>
            </div>
          </div>
        </div>
      </div>

      {/* Nút điều hướng */}
      <div className="d-flex justify-content-center mb-4 flex-wrap gap-2">
        <Link to="/admin/drugs" className="btn btn-primary">Quản lý thuốc</Link>
        <Link to="/admin/orders" className="btn btn-success">Quản lý đơn hàng</Link>
        <Link to="/admin/customers" className="btn btn-warning">Quản lý khách hàng</Link>
        <Link to="/admin/users" className="btn btn-secondary">Quản lý người dùng</Link>
        <Link to="/admin/contacts" className="btn btn-dark">Quản lý liên hệ</Link>
      </div>

      {/* Bảng Thuốc */}
      <div className="mb-5">
        <h4 className="mb-3">Danh sách thuốc</h4>
        <div className="table-responsive">
          <table className="table table-striped table-hover">
            <thead className="table-dark">
              <tr>
                <th>Mã thuốc</th>
                <th>Tên thuốc</th>
                <th>Loại</th>
                <th>Công dụng</th>
                <th>Giá bán</th>
                <th>Số lượng</th>
                <th>Nhà cung cấp</th>
              </tr>
            </thead>
            <tbody>
              {products.map(p => (
                <tr key={p.tl_mathuoc}>
                  <td>{p.tl_mathuoc}</td>
                  <td>{p.tl_tenthuc}</td>
                  <td>{p.tl_loai}</td>
                  <td>{p.tl_congdung}</td>
                  <td>{p.tl_giaban} VND</td>
                  <td>{p.tl_soluongton}</td>
                  <td>{p.tl_nhacungcap}</td>
                </tr>
              ))}
            </tbody>
          </table>
        </div>
      </div>

      {/* Bảng Người dùng */}
      <div className="mb-5">
        <h4 className="mb-3">Danh sách người dùng</h4>
        <div className="table-responsive">
          <table className="table table-striped table-hover">
            <thead className="table-dark">
              <tr>
                <th>Mã</th>
                <th>Tên đăng nhập</th>
                <th>Họ tên</th>
                <th>Email</th>
                <th>SĐT</th>
                <th>Địa chỉ</th>
                <th>Vai trò</th>
              </tr>
            </thead>
            <tbody>
              {users.map(u => (
                <tr key={u.tl_mauser}>
                  <td>{u.tl_mauser}</td>
                  <td>{u.tl_username}</td>
                  <td>{u.tl_fullname}</td>
                  <td>{u.tl_email}</td>
                  <td>{u.tl_phonenumber}</td>
                  <td>{u.tl_address}</td>
                  <td>{u.tl_role}</td>
                </tr>
              ))}
            </tbody>
          </table>
        </div>
      </div>

      {/* Bảng Khách hàng */}
      <div className="mb-5">
        <h4 className="mb-3">Danh sách khách hàng</h4>
        <div className="table-responsive">
          <table className="table table-striped table-hover">
            <thead className="table-dark">
              <tr>
                <th>Mã KH</th>
                <th>Họ tên</th>
                <th>Email</th>
                <th>Số điện thoại</th>
                <th>Địa chỉ</th>
              </tr>
            </thead>
            <tbody>
              {customers.map(c => (
                <tr key={c.tl_makh}>
                  <td>{c.tl_makh}</td>
                  <td>{c.tl_tenkh}</td>
                  <td>{c.tl_email}</td>
                  <td>{c.tl_sdt}</td>
                  <td>{c.tl_diachi}</td>
                </tr>
              ))}
            </tbody>
          </table>
        </div>
      </div>

      {/* Bảng Đơn hàng */}
      <div className="mb-5">
        <h4 className="mb-3">Danh sách đơn hàng</h4>
        <div className="table-responsive">
          <table className="table table-striped table-hover">
            <thead className="table-dark">
              <tr>
                <th>Mã đơn</th>
                <th>Mã khách hàng</th>
                <th>Tổng tiền</th>
                <th>Ngày tạo</th>
                <th>Trạng thái</th>
              </tr>
            </thead>
            <tbody>
              {orders.map(o => (
                <tr key={o.tl_madon}>
                  <td>{o.tl_madon}</td>
                  <td>{o.tl_makh}</td>
                  <td>{o.tl_tongtien} VND</td>
                  <td>{new Date(o.tl_ngaytao).toLocaleDateString('vi-VN')}</td>
                  <td>{o.tl_trangthai}</td>
                </tr>
              ))}
            </tbody>
          </table>
        </div>
      </div>

      {/* Bảng Liên hệ */}
      <div className="mb-5">
        <h4 className="mb-3">Danh sách liên hệ</h4>
        <div className="table-responsive">
          <table className="table table-striped table-hover">
            <thead className="bg-info text-dark">
              <tr>
                <th>Họ tên</th>
                <th>Email</th>
                <th>Nội dung</th>
                <th>Trạng thái</th>
                <th>Trả lời</th>
              </tr>
            </thead>
            <tbody>
              {contacts.map((c, index) => (
                <tr key={index}>
                  <td>{c.tl_hoten}</td>
                  <td>{c.tl_email}</td>
                  <td>{c.tl_noidung}</td>
                  <td>{c.tl_trangthai || 'Chưa trả lời'}</td>
                  <td>{c.tl_traloi || 'Nội dung trả lời...'}</td>
                </tr>
              ))}
            </tbody>
          </table>
        </div>
      </div>
    </div>
  );
}

export default AdminDashboard;
// This code is a React component for the admin dashboard of a pharmacy management system.