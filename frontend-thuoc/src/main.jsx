import React from "react";
import ReactDOM from "react-dom/client";
import App from "./App.jsx";
import { AuthProvider } from "./components/AuthContext.jsx";
import "./index.css"; // Import your global styles
import { CartProvider } from './components/CartContext';

ReactDOM.createRoot(document.getElementById("root")).render(
  <React.StrictMode>
    <AuthProvider>
      <CartProvider>
        <App />
      </CartProvider>
    </AuthProvider>
  </React.StrictMode>
);
// Note: Ensure you have the necessary components and pages created as per the routes defined in App.jsx.