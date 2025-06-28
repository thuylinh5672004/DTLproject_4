import React, { useEffect, useState } from "react";
import axios from "axios";
import "bootstrap/dist/css/bootstrap.min.css"; // Thêm Bootstrap để có giao diện đẹp

// import "bootstrap/dist/js/bootstrap.bundle.min.js"; // Thêm Bootstrap JS nếu cần
function AdminUsers() {
  const [users, setUsers] = useState([]);
  const [form, setForm] = useState({
    tl_username: "",
    tl_email: "",
    tl_password: "",
    tl_fullname: "",
    tl_phonenumber: "",
    tl_address: "",
    tl_role: "customer",
    tl_makh: "", // ✅ thêm để tránh lỗi foreign key
  });
  const [editingId, setEditingId] = useState(null);

  // Lấy danh sách user
  const fetchUsers = () => {
    axios.get("http://localhost:3000/api/users")
      .then((res) => {
        setUsers(res.data);
        console.log("✅ Lấy danh sách user:", res.data);
      })
      .catch((err) => {
        console.error("❌ Lỗi lấy user:", err);
        alert("❌ Không thể tải danh sách người dùng.");
      });
  };

  useEffect(() => {
    fetchUsers();
  }, []);

  // Xoá user
  const handleDelete = (id) => {
    if (window.confirm("Xác nhận xoá người dùng này?")) {
      axios.delete(`http://localhost:3000/api/users/${id}`)
        .then(() => {
          alert("✅ Xoá user thành công!");
          fetchUsers();
        })
        .catch((err) => {
          console.error("❌ Lỗi xoá user:", err);
          alert("❌ Không thể xoá user.");
        });
    }
  };

  // Chỉnh sửa user
  const handleEdit = (user) => {
    setForm({
      tl_username: user.tl_username,
      tl_email: user.tl_email,
      tl_password: "",
      tl_fullname: user.tl_fullname,
      tl_phonenumber: user.tl_phonenumber,
      tl_address: user.tl_address,
      tl_role: user.tl_role,
      tl_makh: user.tl_makh || "",
    });
    setEditingId(user.tl_mauser);
  };

  // Submit thêm hoặc cập nhật
  const handleSubmit = async (e) => {
    e.preventDefault();
    const dataToSend = { ...form };

    // ⚠️ Bắt buộc có password khi thêm
    if (!editingId && !dataToSend.tl_password) {
      alert("Mật khẩu không được để trống khi thêm mới.");
      return;
    }

    // Nếu không nhập mã khách hàng thì để null
    if (!dataToSend.tl_makh || dataToSend.tl_makh.trim() === "") {
      dataToSend.tl_makh = null;
    }

    try {
      if (!editingId) {
        console.log("📤 Thêm user:", dataToSend);
        await axios.post("http://localhost:3000/api/users", dataToSend);
        alert("✅ Thêm user thành công!");
      } else {
        if (!dataToSend.tl_password) delete dataToSend.tl_password;
        console.log("🛠 Cập nhật user:", dataToSend);
        await axios.put(`http://localhost:3000/api/users/${editingId}`, dataToSend);
        alert("✅ Cập nhật user thành công!");
      }

      setForm({
        tl_username: "",
        tl_email: "",
        tl_password: "",
        tl_fullname: "",
        tl_phonenumber: "",
        tl_address: "",
        tl_role: "customer",
        tl_makh: "",
      });
      setEditingId(null);
      fetchUsers();
    } catch (error) {
      console.error("❌ Lỗi gửi form:", error.response?.data || error);
      alert("❌ Gửi form thất bại. Kiểm tra dữ liệu đầu vào.");
    }
  };

  return (
    <div className="container mt-4">
      <h2>Quản lý người dùng</h2>

      <form onSubmit={handleSubmit} className="mb-4">
        <input placeholder="Username" value={form.tl_username} onChange={e => setForm({ ...form, tl_username: e.target.value })} required />
        <input placeholder="Email" value={form.tl_email} onChange={e => setForm({ ...form, tl_email: e.target.value })} required />
        <input
          type="password"
          placeholder="Password"
          value={form.tl_password}
          onChange={e => setForm({ ...form, tl_password: e.target.value })}
          required={!editingId}
        />
        <input placeholder="Họ tên" value={form.tl_fullname} onChange={e => setForm({ ...form, tl_fullname: e.target.value })} />
        <input placeholder="SĐT" value={form.tl_phonenumber} onChange={e => setForm({ ...form, tl_phonenumber: e.target.value })} />
        <input placeholder="Địa chỉ" value={form.tl_address} onChange={e => setForm({ ...form, tl_address: e.target.value })} />
        <select value={form.tl_role} onChange={e => setForm({ ...form, tl_role: e.target.value })}>
          <option value="customer">Customer</option>
          <option value="admin">Admin</option>
        </select>
        <input
          placeholder="Mã khách hàng (nếu có)"
          value={form.tl_makh}
          onChange={e => setForm({ ...form, tl_makh: e.target.value })}
        />

        <button type="submit" className="btn btn-primary mt-2">{editingId ? "Cập nhật" : "Thêm mới"}</button>
      </form>

      <table className="table table-bordered">
        <thead>
          <tr>
            <th>Mã</th>
            <th>Username</th>
            <th>Họ tên</th>
            <th>Email</th>
            <th>SĐT</th>
            <th>Địa chỉ</th>
            <th>Vai trò</th>
            <th>Mã KH</th>
            <th>Hành động</th>
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
              <td>{u.tl_makh || "–"}</td>
              <td>
                <button className="btn btn-sm btn-warning me-2" onClick={() => handleEdit(u)}>Sửa</button>
                <button className="btn btn-sm btn-danger" onClick={() => handleDelete(u.tl_mauser)}>Xoá</button>
              </td>
            </tr>
          ))}
        </tbody>
      </table>
    </div>
  );
}

export default AdminUsers;
