import React, { useEffect, useState } from 'react';
import axios from 'axios';

function AdminOrders() {
  const [orders, setOrders] = useState([]);

  useEffect(() => {
    axios.get('http://localhost:3000/api/orders')
      .then(res => setOrders(res.data))
      .catch(err => console.error("❌ Lỗi lấy đơn hàng:", err));
  }, []);

  return (
    <div className="container mt-4">
      <h2 className="mb-4 text-center">Quản lý đơn hàng</h2>

      <div className="table-responsive">
        <table className="table table-bordered table-striped table-hover">
          <thead className="table-dark text-center">
            <tr>
              <th>Mã đơn</th>
              <th>Mã khách hàng</th>
              <th>Ngày đặt</th>
              <th>Tổng tiền</th>
              <th>Trạng thái</th>
            </tr>
          </thead>
          <tbody>
            {orders.map((order) => (
              <tr key={order.tl_mahd}>
                <td>{order.tl_mahd}</td>
                <td>{order.tl_makh}</td>
                <td>{new Date(order.tl_ngaylap).toLocaleDateString()}</td>
                <td>{order.tl_tongtien.toLocaleString()} VND</td>
                <td>{order.tl_trangthai}</td>
              </tr>
            ))}
            {orders.length === 0 && (
              <tr>
                <td colSpan="5" className="text-center">Không có đơn hàng</td>
              </tr>
            )}
          </tbody>
        </table>
      </div>
    </div>
  );
}

export default AdminOrders;
// This code defines an AdminOrders component that fetches and displays a list of orders from the API.