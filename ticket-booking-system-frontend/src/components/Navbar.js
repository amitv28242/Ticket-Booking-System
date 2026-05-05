import React, { useState } from 'react';
import { Link, useNavigate } from 'react-router-dom';
import { useAuth } from '../context/AuthContext';
import { Train, Bus, Plane, TrainFront, Menu, X, Wallet, User, Ticket, Activity } from 'lucide-react';

const Navbar = () => {
  const { user, logout } = useAuth();
  const navigate = useNavigate();
  const [isOpen, setIsOpen] = useState(false);

  const handleLogout = () => {
    logout();
    navigate('/');
  };

  return (
    <nav className="bg-white shadow-lg sticky top-0 z-50">
      <div className="max-w-7xl mx-auto px-4">
        <div className="flex justify-between h-16">
          <div className="flex items-center">
            <Link to="/" className="flex items-center space-x-2">
              <Ticket className="h-8 w-8 text-blue-600" />
              <span className="text-xl font-bold text-gray-900">TicketBooking</span>
            </Link>
          </div>

          <div className="hidden md:flex items-center space-x-6">
            <Link to="/running-status" className="flex items-center space-x-1 text-gray-600 hover:text-blue-600">
              <Activity className="h-4 w-4" />
              <span>Running Status</span>
            </Link>

            {user ? (
              <>
                <Link to="/wallet" className="flex items-center space-x-1 text-gray-600 hover:text-blue-600">
                  <Wallet className="h-4 w-4" />
                  <span>Wallet</span>
                </Link>
                <Link to="/my-bookings" className="flex items-center space-x-1 text-gray-600 hover:text-blue-600">
                  <Ticket className="h-4 w-4" />
                  <span>My Bookings</span>
                </Link>
                <div className="relative group">
                  <button className="flex items-center space-x-1 text-gray-600 hover:text-blue-600">
                    <User className="h-4 w-4" />
                    <span>{user.firstName}</span>
                  </button>
                  <div className="absolute right-0 mt-2 w-48 bg-white rounded-lg shadow-lg py-2 hidden group-hover:block">
                    <Link to="/profile" className="block px-4 py-2 text-gray-700 hover:bg-gray-100">Profile</Link>
                    <button onClick={handleLogout} className="block w-full text-left px-4 py-2 text-gray-700 hover:bg-gray-100">
                      Logout
                    </button>
                  </div>
                </div>
              </>
            ) : (
              <div className="flex items-center space-x-4">
                <Link to="/login" className="text-gray-600 hover:text-blue-600">Login</Link>
                <Link to="/signup" className="btn-primary">Sign Up</Link>
              </div>
            )}
          </div>

          <div className="md:hidden flex items-center">
            <button onClick={() => setIsOpen(!isOpen)} className="text-gray-600">
              {isOpen ? <X className="h-6 w-6" /> : <Menu className="h-6 w-6" />}
            </button>
          </div>
        </div>
      </div>

      {isOpen && (
        <div className="md:hidden bg-white border-t">
          <div className="px-4 py-2 space-y-2">
            <Link to="/running-status" className="block py-2 text-gray-600">Running Status</Link>
            {user ? (
              <>
                <Link to="/wallet" className="block py-2 text-gray-600">Wallet</Link>
                <Link to="/my-bookings" className="block py-2 text-gray-600">My Bookings</Link>
                <Link to="/profile" className="block py-2 text-gray-600">Profile</Link>
                <button onClick={handleLogout} className="block w-full text-left py-2 text-gray-600">Logout</button>
              </>
            ) : (
              <>
                <Link to="/login" className="block py-2 text-gray-600">Login</Link>
                <Link to="/signup" className="block py-2 text-gray-600">Sign Up</Link>
              </>
            )}
          </div>
        </div>
      )}
    </nav>
  );
};

export default Navbar;