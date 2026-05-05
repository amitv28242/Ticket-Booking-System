import React, { useState, useEffect } from 'react';
import { bookingAPI } from '../services/api';
import { toast } from 'react-toastify';
import { Train, Bus, Plane, TrainFront, Download, XCircle, Calendar, MapPin, Users } from 'lucide-react';

const MyBookings = () => {
  const [bookings, setBookings] = useState([]);
  const [loading, setLoading] = useState(true);

  useEffect(() => {
    fetchBookings();
  }, []);

  const fetchBookings = async () => {
    try {
      const response = await bookingAPI.getMyBookings();
      setBookings(response.data.data || []);
    } catch (error) {
      toast.error('Failed to fetch bookings');
    } finally {
      setLoading(false);
    }
  };

  const handleCancel = async (bookingId) => {
    if (!window.confirm('Are you sure you want to cancel this booking?')) return;
    try {
      await bookingAPI.cancelBooking(bookingId, 'User requested cancellation');
      toast.success('Booking cancelled successfully');
      fetchBookings();
    } catch (error) {
      toast.error(error.response?.data?.message || 'Cancellation failed');
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

  const getStatusColor = (status) => {
    switch (status) {
      case 'CONFIRMED': return 'bg-green-100 text-green-800';
      case 'PENDING': return 'bg-yellow-100 text-yellow-800';
      case 'CANCELLED': return 'bg-red-100 text-red-800';
      default: return 'bg-gray-100 text-gray-800';
    }
  };

  if (loading) {
    return (
      <div className="flex items-center justify-center h-64">
        <div className="animate-spin rounded-full h-12 w-12 border-b-2 border-blue-600"></div>
      </div>
    );
  }

  return (
    <div className="max-w-4xl mx-auto px-4 py-8">
      <h2 className="text-2xl font-bold mb-6">My Bookings</h2>

      {bookings.length === 0 ? (
        <div className="text-center py-12 card">
          <p className="text-gray-500 text-lg">No bookings found</p>
          <p className="text-gray-400 mt-2">Start by searching for your journey</p>
        </div>
      ) : (
        <div className="space-y-4">
          {bookings.map((booking) => {
            const Icon = getIcon(booking.transportType);
            return (
              <div key={booking.id} className="card">
                <div className="flex flex-col md:flex-row justify-between items-start md:items-center">
                  <div className="flex-1">
                    <div className="flex items-center space-x-3 mb-2">
                      <Icon className="h-6 w-6 text-blue-600" />
                      <div>
                        <h3 className="font-semibold">{booking.transportName}</h3>
                        <p className="text-sm text-gray-500">{booking.transportNumber}</p>
                      </div>
                      <span className={`px-3 py-1 rounded-full text-xs font-medium ${getStatusColor(booking.status)}`}>
                        {booking.status}
                      </span>
                    </div>

                    <div className="grid grid-cols-2 md:grid-cols-4 gap-4 mt-3">
                      <div className="flex items-center text-sm text-gray-600">
                        <Calendar className="h-4 w-4 mr-1" />
                        {booking.journeyDate}
                      </div>
                      <div className="flex items-center text-sm text-gray-600">
                        <MapPin className="h-4 w-4 mr-1" />
                        {booking.source} → {booking.destination}
                      </div>
                      <div className="flex items-center text-sm text-gray-600">
                        <Users className="h-4 w-4 mr-1" />
                        {booking.totalPassengers} passengers
                      </div>
                      <div className="text-sm font-semibold text-blue-600">
                        Rs. {booking.totalFare}
                      </div>
                    </div>

                    <div className="mt-2">
                      <p className="text-sm font-medium">PNR: <span className="text-blue-600">{booking.pnrNumber}</span></p>
                    </div>
                  </div>

                  <div className="flex space-x-2 mt-4 md:mt-0">
                    {booking.status === 'CONFIRMED' && (
                      <>
                        <button
                          onClick={() => window.open(booking.ticketPdfUrl, '_blank')}
                          className="flex items-center px-3 py-2 bg-blue-100 text-blue-600 rounded-lg hover:bg-blue-200"
                        >
                          <Download className="h-4 w-4 mr-1" />
                          Ticket
                        </button>
                        <button
                          onClick={() => handleCancel(booking.id)}
                          className="flex items-center px-3 py-2 bg-red-100 text-red-600 rounded-lg hover:bg-red-200"
                        >
                          <XCircle className="h-4 w-4 mr-1" />
                          Cancel
                        </button>
                      </>
                    )}
                  </div>
                </div>
              </div>
            );
          })}
        </div>
      )}
    </div>
  );
};

export default MyBookings;