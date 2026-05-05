import axios from 'axios';

const API_BASE_URL = 'http://localhost:8888/api';

const api = axios.create({
  baseURL: API_BASE_URL,
  headers: {
    'Content-Type': 'application/json',
  },
});

api.interceptors.request.use(
  (config) => {
    const token = localStorage.getItem('token');
    if (token) {
      config.headers.Authorization = `Bearer ${token}`;
    }
    return config;
  },
  (error) => Promise.reject(error)
);

api.interceptors.response.use(
  (response) => response,
  (error) => {
    if (error.response?.status === 401) {
      localStorage.removeItem('token');
      window.location.href = '/login';
    }
    return Promise.reject(error);
  }
);

export default api;

export const authAPI = {
  login: (data) => api.post('/auth/login', data),
  signup: (data) => api.post('/auth/signup', data),
  forgotPassword: (data) => api.post('/auth/forgot-password', data),
  resetPassword: (data) => api.post('/auth/reset-password', data),
  verifyEmail: (token) => api.get(`/auth/verify-email?token=${token}`),
};

export const searchAPI = {
  searchTransports: (data) => api.post('/search/transports', data),
  getPopularSources: () => api.get('/search/popular-sources'),
  getPopularDestinations: () => api.get('/search/popular-destinations'),
};

export const bookingAPI = {
  createBooking: (data) => api.post('/bookings', data),
  getMyBookings: () => api.get('/bookings/my-bookings'),
  getBookingByPnr: (pnr) => api.get(`/bookings/pnr/${pnr}`),
  cancelBooking: (bookingId, reason) => api.post(`/bookings/${bookingId}/cancel?reason=${reason}`),
  downloadTicket: (pnr) => api.get(`/bookings/${pnr}/download`),
};

export const walletAPI = {
  getWallet: () => api.get('/wallet'),
  addMoney: (data) => api.post('/wallet/add-money', data),
  getTransactions: () => api.get('/wallet/transactions'),
};

export const couponAPI = {
  applyCoupon: (data) => api.post('/coupons/apply', data),
  getActiveCoupons: () => api.get('/coupons/active'),
  validateCoupon: (code, type) => api.get(`/coupons/validate?code=${code}&transportType=${type}`),
};

export const runningStatusAPI = {
  getRunningStatus: (number, type, date) => 
    api.get(`/running-status/${number}?type=${type}&date=${date}`),
  getRunningStatusByType: (type, date) => 
    api.get(`/running-status/type/${type}?date=${date}`),
  getRunningStatusHistory: (number) => 
    api.get(`/running-status/${number}/history`),
};

export const paymentMethodAPI = {
  getPaymentMethods: () => api.get('/payment-methods'),
  addPaymentMethod: (data) => api.post('/payment-methods', data),
  deletePaymentMethod: (id) => api.delete(`/payment-methods/${id}`),
  setDefault: (id) => api.put(`/payment-methods/${id}/set-default`),
};