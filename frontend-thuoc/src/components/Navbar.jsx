import React, { useContext } from 'react';
import { Link, NavLink } from 'react-router-dom';
import { AuthContext } from './AuthContext';
import {
  FaHome,
  FaPills,
  FaShoppingCart,
  FaSignInAlt,
  FaUserPlus,
  FaSignOutAlt,
  FaUserCircle,
} from 'react-icons/fa';

export default function Navbar() {
  const { user, logout } = useContext(AuthContext);

  return (
    <nav className="navbar navbar-expand-lg navbar-light bg-light px-3">
      <Link className="navbar-brand d-flex align-items-center" to="/">
        <FaPills style={{ marginRight: '0.5rem' }} />
        Pharmacy
      </Link>

      <button
        className="navbar-toggler"
        type="button"
        data-bs-toggle="collapse"
        data-bs-target="#navbarNav"
        aria-controls="navbarNav"
        aria-expanded="false"
        aria-label="Toggle navigation"
      >
        <span className="navbar-toggler-icon" />
      </button>

      <div className="collapse navbar-collapse" id="navbarNav">
        <ul className="navbar-nav me-auto">
          <li className="nav-item">
            <NavLink className="nav-link" to="/">
              <FaHome style={{ marginRight: '0.3rem' }} />
              Trang chủ
            </NavLink>
          </li>

          <li className="nav-item">
            <NavLink className="nav-link" to="/products">
              <FaPills style={{ marginRight: '0.3rem' }} />
              Danh sách thuốc
            </NavLink>
          </li>

          <li className="nav-item">
            <NavLink className="nav-link" to="/tuvan">
              💬 Tư vấn
            </NavLink>
          </li>

          <li className="nav-item">
            <NavLink className="nav-link" to="/lienhe">
              📞 Liên hệ
            </NavLink>
          </li>

          {user && (
            <li className="nav-item">
              <NavLink className="nav-link" to="/cart">
                <FaShoppingCart style={{ marginRight: '0.3rem' }} />
                Giỏ hàng
              </NavLink>
            </li>
          )}
        </ul>

        <ul className="navbar-nav ms-auto">
          {user ? (
            <>
              <li className="nav-item d-flex align-items-center">
                <span className="nav-link d-flex align-items-center" style={{ cursor: 'default' }}>
                  <FaUserCircle style={{ marginRight: '0.3rem' }} />
                  Xin chào, {user.email}
                </span>
              </li>

              <li className="nav-item">
                <button className="btn btn-link nav-link" onClick={logout}>
                  <FaSignOutAlt style={{ marginRight: '0.3rem' }} />
                  Đăng xuất
                </button>
              </li>
            </>
          ) : (
            <>
              <li className="nav-item">
                <NavLink className="nav-link" to="/login">
                  <FaSignInAlt style={{ marginRight: '0.3rem' }} />
                  Đăng nhập
                </NavLink>
              </li>
              <li className="nav-item">
                <NavLink className="nav-link" to="/register">
                  <FaUserPlus style={{ marginRight: '0.3rem' }} />
                  Đăng ký
                </NavLink>
              </li>
            </>
          )}
        </ul>
      </div>
    </nav>
  );
}
//     <Route path="/admin/users" element={<AdminUsers />} />