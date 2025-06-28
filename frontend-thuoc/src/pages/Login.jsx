import React, { useState } from "react";
import { useAuth } from "../components/AuthContext";
import { loginAPI } from "../services/authService"; // Đừng quên import API
import { useNavigate } from "react-router-dom"; // 👈 Thêm hook điều hướng

function Login() {
  const [form, setForm] = useState({ tl_email: "", tl_password: "" });
  const { login } = useAuth();
  const navigate = useNavigate(); // 👈 Khởi tạo điều hướng

  const handleSubmit = async (e) => {
    e.preventDefault();
    try {
      const res = await loginAPI(form);
      login(res.user); // Cập nhật context
      alert("Đăng nhập thành công");

      // 👉 Điều hướng dựa vào vai trò
      if (res.user.tl_role === "admin") {
        navigate("/admin"); // 👈 AdminDashboard
      } else {
        navigate("/"); // 👈 Trang chủ người dùng
      }
    } catch {
      alert("Sai tài khoản hoặc mật khẩu");
    }
  };

  return (
    <div style={styles.container}>
      <h2 style={styles.title}>Đăng nhập</h2>
      <form onSubmit={handleSubmit} style={styles.form}>
        <input
          name="tl_email"
          placeholder="Email"
          value={form.tl_email}
          onChange={(e) => setForm({ ...form, tl_email: e.target.value })}
          style={styles.input}
        />
        <input
          name="tl_password"
          type="password"
          placeholder="Mật khẩu"
          value={form.tl_password}
          onChange={(e) => setForm({ ...form, tl_password: e.target.value })}
          style={styles.input}
        />
        <button type="submit" style={styles.button}>Đăng nhập</button>
      </form>
    </div>
  );
}

const styles = {
  container: {
    maxWidth: "400px",
    margin: "50px auto",
    padding: "30px",
    border: "1px solid #ccc",
    borderRadius: "10px",
    backgroundColor: "#f9f9f9",
    boxShadow: "0 0 10px rgba(0,0,0,0.1)",
    fontFamily: "Arial, sans-serif"
  },
  title: {
    textAlign: "center",
    marginBottom: "20px",
    color: "#007bff"
  },
  form: {
    display: "flex",
    flexDirection: "column"
  },
  input: {
    marginBottom: "15px",
    padding: "10px",
    fontSize: "16px",
    borderRadius: "5px",
    border: "1px solid #ccc"
  },
  button: {
    padding: "10px",
    fontSize: "16px",
    backgroundColor: "#007bff",
    color: "#fff",
    border: "none",
    borderRadius: "5px",
    cursor: "pointer"
  }
};

export default Login;
