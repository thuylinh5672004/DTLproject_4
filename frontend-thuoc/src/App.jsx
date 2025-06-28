import React from 'react';
import { BrowserRouter as Router, Routes, Route } from 'react-router-dom';

import Home from './pages/Home';
import ProductDetail from './pages/ProductDetail';
import ProductForm from './pages/ProductForm';
import Cart from './pages/Cart';
import Login from './pages/Login';
import Checkout from './pages/Checkout';
import ProductList from './components/ProductList';
import PrivateRoute from './components/PrivateRoute';
import AdminDashboard from './pages/AdminDashboard';
import { CartProvider } from './components/CartContext';
import { AuthProvider } from './components/AuthContext'; // 👈 Thêm dòng này
import Navbar from './components/Navbar';
import Banner from './components/Banner';
import Footer from './components/Footer';
import Register from "./pages/Register";
import AdminDrugs from "./pages/AdminDrugs";
import AdminUsers from "./pages/AdminUsers"; 
import AdminContacts from './pages/AdminContacts';
import TuVan from './pages/TuVan';
import LienHe from './pages/LienHe';
import AdminCustomers from './pages/AdminCustomers';
import AdminOrders from './pages/AdminOrders';
import TuvanDetail from './pages/TuvanDetail'; // 👈 thêm trang mới



import './App.css'; // Import your CSS styles

function App() {
  return (
    <Router>
      <AuthProvider> {/* 👈 Bọc toàn bộ app trong AuthProvider */}
        <CartProvider>
          
          <Banner />

          <Routes>
            <Route path="/" element={<Home />} />
            <Route path="/thuoc" element={<ProductList />} />
            <Route path="/product/:id" element={<ProductDetail />} />
            <Route path="/admin/product" element={<ProductList />} />

            <Route path="/admin/product/add" element={<ProductForm />} />
            <Route path="/admin/product/edit/:id" element={<ProductForm />} />
            <Route path="/login" element={<Login />} />
            <Route path="/register" element={<Register />} /> {/* ✅ thêm dòng này */}
            <Route path="/admin/drugs" element={<AdminDrugs />} />
            <Route path="/admin/users" element={<AdminUsers />} />
            <Route path="/tuvan" element={<TuVan />} />
            <Route path="/lienhe" element={<LienHe />} />
            <Route path="/admin/contacts" element={<AdminContacts />} />
            <Route path="/admin/customers" element={<AdminCustomers />} />
            <Route path="/admin/orders" element={<AdminOrders />} />
            <Route path="/tuvan/:id" element={<TuvanDetail />} /> {/* Thêm route cho TuvanDetail */}
            
            {/* Các route cần xác thực */}
            <Route
              path="/admin"
              element={
                <PrivateRoute>
                  <AdminDashboard />
                </PrivateRoute>
              }
            />
            <Route
              path="/cart"
              element={
                <PrivateRoute>
                  <Cart />
                </PrivateRoute>
              }
            />
            <Route path="/login" element={<Login />} />
            <Route path="/checkout" element={<Checkout />} />
          </Routes>

          <Footer />
        </CartProvider>
      </AuthProvider>
    </Router>

  );
}

export default App;
