import React from 'react';
import { Train, Bus, Plane, TrainFront } from 'lucide-react';

const Footer = () => {
  return (
    <footer className="bg-gray-900 text-white mt-auto">
      <div className="max-w-7xl mx-auto px-4 py-8">
        <div className="grid grid-cols-1 md:grid-cols-4 gap-8">
          <div>
            <h3 className="text-lg font-bold mb-4">TicketBooking</h3>
            <p className="text-gray-400 text-sm">Book tickets for Train, Metro, Bus, and Flight easily and securely.</p>
          </div>
          <div>
            <h4 className="font-semibold mb-4">Services</h4>
            <ul className="space-y-2 text-gray-400 text-sm">
              <li className="flex items-center"><Train className="h-4 w-4 mr-2" /> Train Tickets</li>
              <li className="flex items-center"><TrainFront className="h-4 w-4 mr-2" /> Metro Tickets</li>
              <li className="flex items-center"><Bus className="h-4 w-4 mr-2" /> Bus Tickets</li>
              <li className="flex items-center"><Plane className="h-4 w-4 mr-2" /> Flight Tickets</li>
            </ul>
          </div>
          <div>
            <h4 className="font-semibold mb-4">Quick Links</h4>
            <ul className="space-y-2 text-gray-400 text-sm">
              <li>About Us</li>
              <li>Contact</li>
              <li>FAQs</li>
              <li>Terms & Conditions</li>
            </ul>
          </div>
          <div>
            <h4 className="font-semibold mb-4">Contact</h4>
            <p className="text-gray-400 text-sm">support@ticketbooking.com</p>
            <p className="text-gray-400 text-sm">+91 1800-123-4567</p>
          </div>
        </div>
        <div className="border-t border-gray-800 mt-8 pt-4 text-center text-gray-400 text-sm">
          © 2026 TicketBooking. All rights reserved.
        </div>
      </div>
    </footer>
  );
};

export default Footer;