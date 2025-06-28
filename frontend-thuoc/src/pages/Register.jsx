import React, { useState } from "react";
import { register } from "../services/authService";

function Register() {
  const [form, setForm] = useState({
    username: "",
    email: "",
    password: "",
    fullname: "",
    phonenumber: "",
    address: ""
  });

  const handleChange = (e) => {
    setForm({ ...form, [e.target.name]: e.target.value });
  };

  const handleSubmit = async (e) => {
    e.preventDefault();
    try {
      const res = await register(form);
      alert(res.message);
    } catch (err) {
      console.error("Lỗi:", err.response?.data || err);
      alert("Đăng ký thất bại");
    }
  };

  return (
    <div className="container mt-5">
      <div className="row justify-content-center">
        <div className="col-md-6">
          <div className="card shadow p-4">
            <h3 className="text-center text-primary mb-4">Đăng ký tài khoản</h3>
            <form onSubmit={handleSubmit}>
              <div className="mb-3">
                <label className="form-label">Tên đăng nhập</label>
                <input
                  type="text"
                  name="username"
                  className="form-control"
                  placeholder="Tên đăng nhập"
                  value={form.username}
                  onChange={handleChange}
                  required
                />
              </div>

              <div className="mb-3">
                <label className="form-label">Email</label>
                <input
                  type="email"
                  name="email"
                  className="form-control"
                  placeholder="Email"
                  value={form.email}
                  onChange={handleChange}
                  required
                />
              </div>

              <div className="mb-3">
                <label className="form-label">Mật khẩu</label>
                <input
                  type="password"
                  name="password"
                  className="form-control"
                  placeholder="Mật khẩu"
                  value={form.password}
                  onChange={handleChange}
                  required
                />
              </div>

              <div className="mb-3">
                <label className="form-label">Họ tên</label>
                <input
                  type="text"
                  name="fullname"
                  className="form-control"
                  placeholder="Họ tên"
                  value={form.fullname}
                  onChange={handleChange}
                />
              </div>

              <div className="mb-3">
                <label className="form-label">Số điện thoại</label>
                <input
                  type="text"
                  name="phonenumber"
                  className="form-control"
                  placeholder="Số điện thoại"
                  value={form.phonenumber}
                  onChange={handleChange}
                />
              </div>

              <div className="mb-4">
                <label className="form-label">Địa chỉ</label>
                <input
                  type="text"
                  name="address"
                  className="form-control"
                  placeholder="Địa chỉ"
                  value={form.address}
                  onChange={handleChange}
                />
              </div>

              <button type="submit" className="btn btn-primary w-100">
                Đăng ký
              </button>
            </form>
          </div>
        </div>
      </div>
    </div>
  );
}

export default Register;
// This code defines a Register component that allows users to create a new account.