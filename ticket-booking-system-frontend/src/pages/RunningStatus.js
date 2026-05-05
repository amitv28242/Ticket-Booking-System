import React, { useState } from 'react';
import { runningStatusAPI } from '../services/api';
import { toast } from 'react-toastify';
import { Train, TrainFront, Search, Clock, MapPin, AlertCircle, CheckCircle, XCircle } from 'lucide-react';

const RunningStatus = () => {
  const [transportNumber, setTransportNumber] = useState('');
  const [transportType, setTransportType] = useState('TRAIN');
  const [date, setDate] = useState(new Date().toISOString().split('T')[0]);
  const [status, setStatus] = useState(null);
  const [loading, setLoading] = useState(false);

  const handleSearch = async (e) => {
    e.preventDefault();
    if (!transportNumber) {
      toast.error('Please enter transport number');
      return;
    }
    setLoading(true);
    try {
      const response = await runningStatusAPI.getRunningStatus(transportNumber, transportType, date);
      setStatus(response.data.data);
    } catch (error) {
      toast.error(error.response?.data?.message || 'Status not found');
      setStatus(null);
    } finally {
      setLoading(false);
    }
  };

  const getStatusIcon = (status) => {
    switch (status) {
      case 'ON_TIME': return <CheckCircle className="h-8 w-8 text-green-500" />;
      case 'DELAYED': return <AlertCircle className="h-8 w-8 text-yellow-500" />;
      case 'CANCELLED': return <XCircle className="h-8 w-8 text-red-500" />;
      default: return <Clock className="h-8 w-8 text-blue-500" />;
    }
  };

  const getStatusColor = (status) => {
    switch (status) {
      case 'ON_TIME': return 'bg-green-100 text-green-800';
      case 'DELAYED': return 'bg-yellow-100 text-yellow-800';
      case 'CANCELLED': return 'bg-red-100 text-red-800';
      default: return 'bg-blue-100 text-blue-800';
    }
  };

  return (
    <div className="max-w-4xl mx-auto px-4 py-8">
      <h2 className="text-2xl font-bold mb-6">Check Running Status</h2>

      <div className="card mb-6">
        <form onSubmit={handleSearch} className="space-y-4">
          <div className="grid grid-cols-1 md:grid-cols-3 gap-4">
            <div>
              <label className="block text-sm font-medium text-gray-700 mb-1">Transport Type</label>
              <select
                value={transportType}
                onChange={(e) => setTransportType(e.target.value)}
                className="input-field"
              >
                <option value="TRAIN">Train</option>
                <option value="METRO">Metro</option>
                <option value="BUS">Bus</option>
                <option value="FLIGHT">Flight</option>
              </select>
            </div>
            <div>
              <label className="block text-sm font-medium text-gray-700 mb-1">Transport Number</label>
              <input
                type="text"
                value={transportNumber}
                onChange={(e) => setTransportNumber(e.target.value)}
                placeholder="e.g., 12345, AI101"
                className="input-field"
                required
              />
            </div>
            <div>
              <label className="block text-sm font-medium text-gray-700 mb-1">Date</label>
              <input
                type="date"
                value={date}
                onChange={(e) => setDate(e.target.value)}
                className="input-field"
              />
            </div>
          </div>
          <button type="submit" className="btn-primary w-full flex items-center justify-center" disabled={loading}>
            <Search className="h-5 w-5 mr-2" />
            {loading ? 'Searching...' : 'Check Status'}
          </button>
        </form>
      </div>

      {status && (
        <div className="card">
          <div className="flex items-center justify-between mb-4">
            <div className="flex items-center space-x-3">
              {getStatusIcon(status.status)}
              <div>
                <h3 className="text-xl font-bold">{status.transportNumber}</h3>
                <span className={`px-3 py-1 rounded-full text-xs font-medium ${getStatusColor(status.status)}`}>
                  {status.status}
                </span>
              </div>
            </div>
            <div className="text-right">
              <p className="text-sm text-gray-500">Last Updated</p>
              <p className="text-sm font-medium">{new Date(status.lastUpdated).toLocaleString()}</p>
            </div>
          </div>

          <div className="grid grid-cols-1 md:grid-cols-2 gap-4 mt-4">
            <div className="bg-gray-50 p-4 rounded-lg">
              <p className="text-sm text-gray-500 mb-1">Current Station</p>
              <p className="text-lg font-semibold flex items-center">
                <MapPin className="h-5 w-5 mr-1 text-blue-600" />
                {status.currentStation}
              </p>
            </div>
            <div className="bg-gray-50 p-4 rounded-lg">
              <p className="text-sm text-gray-500 mb-1">Next Station</p>
              <p className="text-lg font-semibold flex items-center">
                <MapPin className="h-5 w-5 mr-1 text-green-600" />
                {status.nextStation || 'N/A'}
              </p>
            </div>
          </div>

          {status.delayMinutes > 0 && (
            <div className="mt-4 p-4 bg-yellow-50 border border-yellow-200 rounded-lg">
              <p className="text-yellow-800 font-medium">
                Delayed by {status.delayMinutes} minutes
              </p>
              <p className="text-sm text-yellow-600">{status.delayReason}</p>
            </div>
          )}

          <div className="grid grid-cols-2 gap-4 mt-4">
            <div>
              <p className="text-sm text-gray-500">Expected Arrival</p>
              <p className="font-semibold">{status.expectedArrival || 'N/A'}</p>
            </div>
            <div>
              <p className="text-sm text-gray-500">Expected Departure</p>
              <p className="font-semibold">{status.expectedDeparture || 'N/A'}</p>
            </div>
          </div>

          {status.platformNumber && (
            <div className="mt-4 p-3 bg-blue-50 rounded-lg text-center">
              <p className="text-blue-800 font-semibold">Platform: {status.platformNumber}</p>
            </div>
          )}
        </div>
      )}
    </div>
  );
};

export default RunningStatus;