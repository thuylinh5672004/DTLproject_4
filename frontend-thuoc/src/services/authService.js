import axios from "axios";

const API_URL = "http://localhost:3000/api/auth";

export const register = async (form) => {
  const res = await axios.post(`${API_URL}/register`, form);
  return res.data;
};

export const loginAPI = async (form) => {
  const res = await axios.post(`${API_URL}/login`, form);
  return res.data;
};
