import React, { createContext, useContext, useEffect, useState } from 'react';

// Tạo context
const CartContext = createContext();

// Provider để bọc ứng dụng
export const CartProvider = ({ children }) => {
  const [cartItems, setCartItems] = useState(() => {
    const savedCart = localStorage.getItem('cart');
    return savedCart ? JSON.parse(savedCart) : [];
  });

  // Lưu vào localStorage khi cartItems thay đổi
  useEffect(() => {
    localStorage.setItem('cart', JSON.stringify(cartItems));
  }, [cartItems]);

  // Thêm vào giỏ hàng
  const addToCart = (product) => {
    setCartItems(prevItems => {
      const existingItem = prevItems.find(item => item.tl_mathuoc === product.tl_mathuoc);
      if (existingItem) {
        return prevItems.map(item =>
          item.tl_mathuoc === product.tl_mathuoc
            ? { ...item, quantity: item.quantity + 1 }
            : item
        );
      }
      return [...prevItems, { ...product, quantity: 1 }];
    });
  };

  // Xoá 1 sản phẩm khỏi giỏ
  const removeFromCart = (id) => {
    setCartItems(prevItems => prevItems.filter(item => item.tl_mathuoc !== id));
  };

  // Cập nhật số lượng
  const updateQuantity = (id, quantity) => {
    setCartItems(prevItems =>
      prevItems.map(item =>
        item.tl_mathuoc === id
          ? { ...item, quantity: Math.max(1, parseInt(quantity)) }
          : item
      )
    );
  };

  // Xoá toàn bộ giỏ hàng
  const clearCart = () => {
    setCartItems([]);
  };

  return (
    <CartContext.Provider
      value={{ cartItems, addToCart, removeFromCart, updateQuantity, clearCart }}
    >
      {children}
    </CartContext.Provider>
  );
};

// Hook sử dụng
export const useCart = () => useContext(CartContext);
// Lưu ý: Đảm bảo bạn đã bọc ứng dụng của mình bằng CartProvider trong index.js hoặc App.js