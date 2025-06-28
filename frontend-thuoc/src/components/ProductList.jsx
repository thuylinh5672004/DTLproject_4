import React, { useEffect, useState } from "react";
import { Link } from 'react-router-dom';
import axios from "axios";
// Gọi API thật từ backend để lấy danh sách thuốc

const fetchThuocList = async () => {
  const response = await fetch("http://localhost:3000/api/products");
  if (!response.ok) {
    throw new Error("Không thể lấy danh sách thuốc từ máy chủ");
  }
  return await response.json();
};

export default function ThuocList() {
  const [thuocs, setThuocs] = useState([]);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState(null);
  const [searchTerm, setSearchTerm] = useState("");
  const [sortBy, setSortBy] = useState("tenthuc");
  const [filterBy, setFilterBy] = useState("all");
  const [viewMode, setViewMode] = useState("table"); // table hoặc card

  useEffect(() => {
    const fetchData = async () => {
      try {
        const data = await fetchThuocList();
        setThuocs(data);
        setLoading(false);
      } catch (err) {
        setError(err.message);
        setLoading(false);
      }
    };

    fetchData();
  }, []);

  const filteredThuocs = thuocs.filter(thuoc => {
    const matchesSearch =
      thuoc.tl_tenthuc.toLowerCase().includes(searchTerm.toLowerCase()) ||
      thuoc.tl_mathuoc.toLowerCase().includes(searchTerm.toLowerCase());

    if (filterBy === "all") return matchesSearch;
    if (filterBy === "lowStock") return matchesSearch && thuoc.tl_soluong < 50;
    if (filterBy === "expiringSoon") {
      const today = new Date();
      const expiryDate = new Date(thuoc.tl_ngayhethan);
      const daysUntilExpiry = (expiryDate - today) / (1000 * 60 * 60 * 24);
      return matchesSearch && daysUntilExpiry < 180;
    }
    return matchesSearch;
  });

  const sortedThuocs = filteredThuocs.sort((a, b) => {
    if (sortBy === "tl_giaban" || sortBy === "tl_soluong") return a[sortBy] - b[sortBy];
    return a[sortBy].localeCompare(b[sortBy]);
  });

  const formatCurrency = (amount) => {
    return new Intl.NumberFormat('vi-VN', {
      style: 'currency',
      currency: 'VND'
    }).format(amount);
  };

  const isExpiringSoon = (dateString) => {
    const today = new Date();
    const expiryDate = new Date(dateString);
    const daysUntilExpiry = (expiryDate - today) / (1000 * 60 * 60 * 24);
    return daysUntilExpiry < 180;
  };

  const ThuocCard = ({ thuoc }) => (
    <div className="bg-white rounded-lg shadow-md p-6 border hover:shadow-lg transition-shadow">
      <div className="flex justify-between items-start mb-3">
        <h3 className="text-lg font-semibold text-gray-800">{thuoc.tl_tenthuc}</h3>
        <span className="text-sm text-gray-500">#{thuoc.tl_mathuoc}</span>
      </div>
      <div className="space-y-2 text-sm">
        <div className="flex justify-between">
          <span className="text-gray-600">Giá bán:</span>
          <span className="font-medium text-green-600">{formatCurrency(thuoc.tl_giaban)}</span>
        </div>
        <div className="flex justify-between">
          <span className="text-gray-600">Số lượng:</span>
          <span className={`font-medium ${thuoc.tl_soluong < 50 ? 'text-red-600' : 'text-blue-600'}`}>
            {thuoc.tl_soluong} {thuoc.tl_donvi}
          </span>
        </div>
        <div className="flex justify-between">
          <span className="text-gray-600">Hết hạn:</span>
          <span className={`font-medium ${isExpiringSoon(thuoc.tl_ngayhethan) ? 'text-red-600' : 'text-gray-800'}`}>
            {new Date(thuoc.tl_ngayhethan).toLocaleDateString('vi-VN')}
          </span>
        </div>
        <div className="flex justify-between">
          <span className="text-gray-600">Nhà cung cấp:</span>
          <span className="font-medium">{thuoc.tl_nhacungcap}</span>
        </div>
      </div>
      <div className="mt-3 pt-3 border-t">
        <p className="text-gray-600 text-sm">{thuoc.tl_mota}</p>
      </div>
      {(thuoc.tl_soluong < 50 || isExpiringSoon(thuoc.tl_ngayhethan)) && (
        <div className="mt-3 flex gap-2">
          {thuoc.tl_soluong < 50 && (
            <span className="px-2 py-1 bg-red-100 text-red-700 text-xs rounded-full">Sắp hết hàng</span>
          )}
          {isExpiringSoon(thuoc.tl_ngayhethan) && (
            <span className="px-2 py-1 bg-orange-100 text-orange-700 text-xs rounded-full">Sắp hết hạn</span>
          )}
        </div>
      )}
    </div>
  );

  if (loading) {
    return (
      <div className="flex justify-center items-center h-64">
        <div className="animate-spin rounded-full h-12 w-12 border-b-2 border-blue-500"></div>
        <span className="ml-3 text-gray-600">Đang tải dữ liệu...</span>
      </div>
    );
  }

  if (error) {
    return (
      <div className="bg-red-50 border border-red-200 rounded-lg p-4">
        <div className="flex items-center">
          <div className="text-red-500">
            <svg className="w-5 h-5" fill="currentColor" viewBox="0 0 20 20">
              <path fillRule="evenodd" d="M10 18a8 8 0 100-16 8 8 0 000 16zM8.707 7.293a1 1 0 00-1.414 1.414L8.586 10l-1.293 1.293a1 1 0 101.414 1.414L10 11.414l1.293 1.293a1 1 0 001.414-1.414L11.414 10l1.293-1.293a1 1 0 00-1.414-1.414L10 8.586 8.707 7.293z" clipRule="evenodd" />
            </svg>
          </div>
          <span className="ml-2 text-red-700">Lỗi: {error}</span>
        </div>
      </div>
    );
  }

  return (
    <div className="max-w-7xl mx-auto p-6">
      <div className="mb-6">
        <h1 className="text-3xl font-bold text-gray-800 mb-2">Quản Lý Thuốc</h1>
        <p className="text-gray-600">Tổng số: {thuocs.length} thuốc | Hiển thị: {sortedThuocs.length} thuốc</p>
      </div>

      <div className="bg-white rounded-lg shadow-sm border p-4 mb-6">
        <div className="grid grid-cols-1 md:grid-cols-4 gap-4">
          <div>
            <label className="block text-sm font-medium text-gray-700 mb-1">Tìm kiếm</label>
            <input
              type="text"
              className="w-full border rounded-md p-2 text-sm"
              placeholder="Tên thuốc hoặc mã thuốc"
              value={searchTerm}
              onChange={(e) => setSearchTerm(e.target.value)}
            />
          </div>

          <div>
            <label className="block text-sm font-medium text-gray-700 mb-1">Sắp xếp</label>
            <select
              className="w-full border rounded-md p-2 text-sm"
              value={sortBy}
              onChange={(e) => setSortBy(e.target.value)}
            >
              <option value="tl_tenthuc">Tên thuốc</option>
              <option value="tl_giaban">Giá bán</option>
              <option value="tl_soluong">Số lượng</option>
            </select>
          </div>

          <div>
            <label className="block text-sm font-medium text-gray-700 mb-1">Lọc theo</label>
            <select
              className="w-full border rounded-md p-2 text-sm"
              value={filterBy}
              onChange={(e) => setFilterBy(e.target.value)}
            >
              <option value="all">Tất cả</option>
              <option value="lowStock">Sắp hết hàng</option>
              <option value="expiringSoon">Sắp hết hạn</option>
            </select>
          </div>

          <div>
            <label className="block text-sm font-medium text-gray-700 mb-1">Chế độ hiển thị</label>
            <select
              className="w-full border rounded-md p-2 text-sm"
              value={viewMode}
              onChange={(e) => setViewMode(e.target.value)}
            >
              <option value="table">Bảng</option>
              <option value="card">Thẻ</option>
            </select>
          </div>
        </div>
      </div>

      {viewMode === "card" ? (
        <div className="grid grid-cols-1 md:grid-cols-2 lg:grid-cols-3 gap-6">
          {sortedThuocs.map((thuoc) => (
            <ThuocCard key={thuoc.tl_mathuoc} thuoc={thuoc} />
          ))}
        </div>
      ) : (
        <div className="overflow-x-auto">
          <table className="min-w-full bg-white border border-gray-200 rounded-lg overflow-hidden">
            <thead className="bg-gray-50">
              <tr>
                <th className="px-4 py-2 text-left text-sm font-medium text-gray-700">Mã</th>
                <th className="px-4 py-2 text-left text-sm font-medium text-gray-700">Tên thuốc</th>
                <th className="px-4 py-2 text-sm font-medium text-gray-700">Giá</th>
                <th className="px-4 py-2 text-sm font-medium text-gray-700">Số lượng</th>
                <th className="px-4 py-2 text-sm font-medium text-gray-700">Hết hạn</th>
                <th className="px-4 py-2 text-sm font-medium text-gray-700">Nhà cung cấp</th>
              </tr>
            </thead>
            <tbody>
              {sortedThuocs.map((thuoc) => (
                <tr key={thuoc.tl_mathuoc} className="border-t hover:bg-gray-50">
                  <td className="px-4 py-2 text-sm text-gray-800">{thuoc.tl_mathuoc}</td>
                  <td className="px-4 py-2 text-sm text-gray-800">{thuoc.tl_tenthuc}</td>
                  <td className="px-4 py-2 text-sm text-green-600">{formatCurrency(thuoc.tl_giaban)}</td>
                  <td className={`px-4 py-2 text-sm ${thuoc.tl_soluong < 50 ? 'text-red-600' : 'text-blue-600'}`}>
                    {thuoc.tl_soluong} {thuoc.tl_donvi}
                  </td>
                  <td className={`px-4 py-2 text-sm ${isExpiringSoon(thuoc.tl_ngayhethan) ? 'text-red-600' : 'text-gray-800'}`}>
                    {new Date(thuoc.tl_ngayhethan).toLocaleDateString('vi-VN')}
                  </td>
                  <td className="px-4 py-2 text-sm text-gray-800">{thuoc.tl_nhacungcap}</td>
                </tr>
              ))}
            </tbody>
          </table>
        </div>
      )}
    </div>
  );
}
