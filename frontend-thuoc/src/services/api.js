const API_BASE = 'http://localhost:3000';

// Lấy danh sách sản phẩm
export async function fetchProducts() {
  const res = await fetch(`${API_BASE}/products`);
  if (!res.ok) throw new Error('Lỗi lấy danh sách sản phẩm');
  return res.json();
}

// Lấy chi tiết sản phẩm theo id
export async function fetchProductById(id) {
  const res = await fetch(`${API_BASE}/products/${id}`);
  if (!res.ok) throw new Error('Lỗi lấy sản phẩm');
  return res.json();
}

// Đăng nhập
export async function login(email, password) {
  const res = await fetch(`${API_BASE}/auth/login`, {
    method: 'POST',
    headers: { 'Content-Type': 'application/json' },
    body: JSON.stringify({ email, password }),
  });
  if (!res.ok) {
    const err = await res.json();
    throw new Error(err.message || 'Đăng nhập thất bại');
  }
  return res.json(); // thường trả về token, user info
}

// Đăng ký
export async function register(name, email, password) {
  const res = await fetch(`${API_BASE}/auth/register`, {
    method: 'POST',
    headers: { 'Content-Type': 'application/json' },
    body: JSON.stringify({ name, email, password }),
  });
  if (!res.ok) {
    const err = await res.json();
    throw new Error(err.message || 'Đăng ký thất bại');
  }
  return res.json();
}

// Lấy thông tin user (khi có token)
export async function fetchUserProfile(token) {
  const res = await fetch(`${API_BASE}/auth/profile`, {
    headers: {
      Authorization: `Bearer ${token}`,
    },
  });
  if (!res.ok) throw new Error('Lỗi lấy thông tin người dùng');
  return res.json();
}

// Logout (tuỳ backend, có thể xoá token ở client hoặc gọi api logout)
export async function logout() {
  // Nếu backend có api logout thì gọi ở đây
  // Ví dụ:
  // await fetch(`${API_BASE}/auth/logout`, { method: 'POST', credentials: 'include' });
  // Còn nếu không, chỉ cần xoá token phía client
  return true;
}

// Thêm sản phẩm vào giỏ hàng (tuỳ API backend)
export async function addToCart(productId, quantity, token) {
  const res = await fetch(`${API_BASE}/cart`, {
    method: 'POST',
    headers: {
      'Content-Type': 'application/json',
      Authorization: `Bearer ${token}`,
    },
    body: JSON.stringify({ productId, quantity }),
  });
  if (!res.ok) throw new Error('Lỗi thêm sản phẩm vào giỏ hàng');
  return res.json();
}

// Lấy giỏ hàng
export async function fetchCart(token) {
  const res = await fetch(`${API_BASE}/cart`, {
    headers: {
      Authorization: `Bearer ${token}`,
    },
  });
  if (!res.ok) throw new Error('Lỗi lấy giỏ hàng');
  return res.json();
}

// Xóa sản phẩm khỏi giỏ hàng
export async function removeFromCart(itemId, token) {
  const res = await fetch(`${API_BASE}/cart/${itemId}`, {
    method: 'DELETE',
    headers: {
      Authorization: `Bearer ${token}`,
    },
  });
  if (!res.ok) throw new Error('Lỗi xóa sản phẩm khỏi giỏ hàng');
  return res.json();
}

// Cập nhật số lượng sản phẩm trong giỏ hàng
export async function updateCartItem(itemId, quantity, token) {
  const res = await fetch(`${API_BASE}/cart/${itemId}`, {
    method: 'PUT',
    headers: {
      'Content-Type': 'application/json',
      Authorization: `Bearer ${token}`,
    },
    body: JSON.stringify({ quantity }),
  });
  if (!res.ok) throw new Error('Lỗi cập nhật giỏ hàng');
  return res.json();
}
