import React, { useState } from 'react';
import { useLocation, useNavigate } from 'react-router-dom';
import { bookingAPI, couponAPI } from '../services/api';
import { useAuth } from '../context/AuthContext';
import { toast } from 'react-toastify';
import { Train, Bus, Plane, TrainFront, User, Minus, Plus, Tag, CreditCard } from 'lucide-react';

const Booking = () => {
  const location = useLocation();
  const navigate = useNavigate();
  const { user } = useAuth();
  const { transport, searchData } = location.state || {};

  const [passengers, setPassengers] = useState([
    { name: '', age: '', gender: 'MALE', idProofNumber: '', idProofType: 'AADHAR', mobileNumber: '', email: '', seatPreference: '', mealPreference: '', specialAssistance: '' }
  ]);
  const [couponCode, setCouponCode] = useState('');
  const [discount, setDiscount] = useState(0);
  const [paymentMethod, setPaymentMethod] = useState('UPI');
  const [loading, setLoading] = useState(false);

  if (!transport) {
    navigate('/');
    return null;
  }

  const baseFare = transport.baseFare * passengers.length;
  const tax = baseFare * 0.18;
  const convenienceFee = 50;
  const totalFare = baseFare + tax + convenienceFee - discount;

  const addPassenger = () => {
    if (passengers.length < 6) {
      setPassengers([...passengers, { name: '', age: '', gender: 'MALE', idProofNumber: '', idProofType: 'AADHAR', mobileNumber: '', email: '', seatPreference: '', mealPreference: '', specialAssistance: '' }]);
    }
  };

  const removePassenger = (index) => {
    if (passengers.length > 1) {
      setPassengers(passengers.filter((_, i) => i !== index));
    }
  };

  const updatePassenger = (index, field, value) => {
    const updated = [...passengers];
    updated[index][field] = value;
    setPassengers(updated);
  };

  const applyCoupon = async () => {
    try {
      const response = await couponAPI.applyCoupon({
        code: couponCode,
        bookingAmount: baseFare,
        transportType: transport.type
      });
      setDiscount(response.data.data?.discountValue || 0);
      toast.success('Coupon applied successfully');
    } catch (error) {
      toast.error(error.response?.data?.message || 'Invalid coupon');
    }
  };

  const handleBooking = async () => {
    if (!user) {
      toast.error('Please login to book tickets');
      navigate('/login');
      return;
    }

    setLoading(true);
    try {
      const bookingData = {
        transportId: transport.transportId,
        transportType: transport.type,
        ticketType: 'ONE_WAY',
        journeyDate: searchData.journeyDate,
        source: searchData.source,
        destination: searchData.destination,
        passengers: passengers,
        couponCode: couponCode || undefined,
        paymentMethod: paymentMethod,
      };

      const response = await bookingAPI.createBooking(bookingData);
      toast.success(`Booking confirmed! PNR: ${response.data.data.pnrNumber}`);
      navigate('/my-bookings');
    } catch (error) {
      toast.error(error.response?.data?.message || 'Booking failed');
    } finally {
      setLoading(false);
    }
  };

  const getIcon = (type) => {
    switch (type) {
      case 'TRAIN': return Train;
      case 'METRO': return TrainFront;
      case 'BUS': return Bus;
      case 'FLIGHT': return Plane;
      default: return Train;
    }
  };

  const Icon = getIcon(transport.type);

  return (
    <div className="max-w-4xl mx-auto px-4 py-8">
      <div className="card mb-6">
        <div className="flex items-center space-x-3 mb-4">
          <Icon className="h-8 w-8 text-blue-600" />
          <div>
            <h2 className="text-xl font-bold">{transport.name}</h2>
            <p className="text-gray-500">{transport.number} | {transport.type}</p>
          </div>
        </div>
        <div className="grid grid-cols-3 gap-4 text-center">
          <div>
            <p className="font-bold">{transport.departureTime}</p>
            <p className="text-sm text-gray-500">{transport.source}</p>
          </div>
          <div>
            <p className="text-sm text-gray-500">{transport.duration}</p>
            <p className="text-xs text-gray-400">{transport.distanceKm} km</p>
          </div>
          <div>
            <p className="font-bold">{transport.arrivalTime}</p>
            <p className="text-sm text-gray-500">{transport.destination}</p>
          </div>
        </div>
      </div>

      <div className="card mb-6">
        <div className="flex justify-between items-center mb-4">
          <h3 className="text-lg font-semibold">Passenger Details</h3>
          <div className="flex items-center space-x-2">
            <button onClick={removePassenger} className="p-1 rounded-full hover:bg-gray-100" disabled={passengers.length <= 1}>
              <Minus className="h-5 w-5" />
            </button>
            <span>{passengers.length}</span>
            <button onClick={addPassenger} className="p-1 rounded-full hover:bg-gray-100" disabled={passengers.length >= 6}>
              <Plus className="h-5 w-5" />
            </button>
          </div>
        </div>

        {passengers.map((passenger, index) => (
          <div key={index} className="border rounded-lg p-4 mb-4">
            <h4 className="font-medium mb-3">Passenger {index + 1}</h4>
            <div className="grid grid-cols-1 md:grid-cols-2 gap-4">
              <input
                type="text"
                placeholder="Full Name"
                value={passenger.name}
                onChange={(e) => updatePassenger(index, 'name', e.target.value)}
                className="input-field"
                required
              />
              <input
                type="number"
                placeholder="Age"
                value={passenger.age}
                onChange={(e) => updatePassenger(index, 'age', e.target.value)}
                className="input-field"
                required
              />
              <select
                value={passenger.gender}
                onChange={(e) => updatePassenger(index, 'gender', e.target.value)}
                className="input-field"
              >
                <option value="MALE">Male</option>
                <option value="FEMALE">Female</option>
                <option value="OTHER">Other</option>
              </select>
              <select
                value={passenger.idProofType}
                onChange={(e) => updatePassenger(index, 'idProofType', e.target.value)}
                className="input-field"
              >
                <option value="AADHAR">Aadhar</option>
                <option value="PAN">PAN</option>
                <option value="PASSPORT">Passport</option>
              </select>
              <input
                type="text"
                placeholder="ID Proof Number"
                value={passenger.idProofNumber}
                onChange={(e) => updatePassenger(index, 'idProofNumber', e.target.value)}
                className="input-field"
                required
              />
              <input
                type="tel"
                placeholder="Mobile Number"
                value={passenger.mobileNumber}
                onChange={(e) => updatePassenger(index, 'mobileNumber', e.target.value)}
                className="input-field"
                required
              />
              <input
                type="email"
                placeholder="Email"
                value={passenger.email}
                onChange={(e) => updatePassenger(index, 'email', e.target.value)}
                className="input-field"
                required
              />
            </div>
          </div>
        ))}
      </div>

      <div className="card mb-6">
        <h3 className="text-lg font-semibold mb-4">Apply Coupon</h3>
        <div className="flex space-x-2">
          <input
            type="text"
            placeholder="Enter coupon code"
            value={couponCode}
            onChange={(e) => setCouponCode(e.target.value)}
            className="input-field flex-1"
          />
          <button onClick={applyCoupon} className="btn-secondary">
            <Tag className="h-5 w-5 mr-1" />
            Apply
          </button>
        </div>
      </div>

      <div className="card mb-6">
        <h3 className="text-lg font-semibold mb-4">Payment Method</h3>
        <div className="grid grid-cols-2 md:grid-cols-4 gap-3">
          {['UPI', 'CREDIT_CARD', 'DEBIT_CARD', 'WALLET'].map((method) => (
            <button
              key={method}
              onClick={() => setPaymentMethod(method)}
              className={`p-3 border rounded-lg text-center ${
                paymentMethod === method ? 'border-blue-600 bg-blue-50 text-blue-600' : 'border-gray-200'
              }`}
            >
              <CreditCard className="h-6 w-6 mx-auto mb-1" />
              <span className="text-sm">{method.replace('_', ' ')}</span>
            </button>
          ))}
        </div>
      </div>

      <div className="card">
        <h3 className="text-lg font-semibold mb-4">Fare Summary</h3>
        <div className="space-y-2">
          <div className="flex justify-between">
            <span>Base Fare ({passengers.length} passengers)</span>
            <span>Rs. {baseFare}</span>
          </div>
          <div className="flex justify-between">
            <span>Tax (18% GST)</span>
            <span>Rs. {tax.toFixed(2)}</span>
          </div>
          <div className="flex justify-between">
            <span>Convenience Fee</span>
            <span>Rs. {convenienceFee}</span>
          </div>
          {discount > 0 && (
            <div className="flex justify-between text-green-600">
              <span>Discount</span>
              <span>- Rs. {discount}</span>
            </div>
          )}
          <div className="border-t pt-2 flex justify-between font-bold text-lg">
            <span>Total Fare</span>
            <span className="text-blue-600">Rs. {totalFare.toFixed(2)}</span>
          </div>
        </div>

        <button
          onClick={handleBooking}
          disabled={loading}
          className="btn-primary w-full mt-6 py-3"
        >
          {loading ? 'Processing...' : `Pay Rs. ${totalFare.toFixed(2)}`}
        </button>
      </div>
    </div>
  );
};

export default Booking;