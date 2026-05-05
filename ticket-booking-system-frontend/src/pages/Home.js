import React from 'react';
import SearchForm from '../components/SearchForm';
import { Train, Bus, Plane, TrainFront, Shield, Clock, CreditCard, Headphones } from 'lucide-react';

const Home = () => {
  return (
    <div>
      {/* Hero Section */}
      <div className="bg-gradient-to-r from-blue-600 to-blue-800 text-white py-16">
        <div className="max-w-7xl mx-auto px-4">
          <div className="text-center mb-8">
            <h1 className="text-4xl md:text-5xl font-bold mb-4">Book Your Journey</h1>
            <p className="text-xl text-blue-100">Train, Metro, Bus, and Flight tickets at your fingertips</p>
          </div>
          <SearchForm />
        </div>
      </div>

      {/* Features Section */}
      <div className="max-w-7xl mx-auto px-4 py-16">
        <h2 className="text-3xl font-bold text-center mb-12">Why Choose Us?</h2>
        <div className="grid grid-cols-1 md:grid-cols-4 gap-8">
          {[
            { icon: Shield, title: 'Secure Booking', desc: '100% secure payment and data protection' },
            { icon: Clock, title: 'Instant Confirmation', desc: 'Get confirmed tickets in seconds' },
            { icon: CreditCard, title: 'Easy Payments', desc: 'Multiple payment options including wallet' },
            { icon: Headphones, title: '24/7 Support', desc: 'Round the clock customer assistance' },
          ].map(({ icon: Icon, title, desc }) => (
            <div key={title} className="card text-center">
              <Icon className="h-12 w-12 text-blue-600 mx-auto mb-4" />
              <h3 className="text-lg font-semibold mb-2">{title}</h3>
              <p className="text-gray-600">{desc}</p>
            </div>
          ))}
        </div>
      </div>

      {/* Transport Types */}
      <div className="bg-gray-100 py-16">
        <div className="max-w-7xl mx-auto px-4">
          <h2 className="text-3xl font-bold text-center mb-12">Available Services</h2>
          <div className="grid grid-cols-1 md:grid-cols-4 gap-6">
            {[
              { icon: Train, title: 'Train', desc: 'Book IRCTC trains across India', color: 'bg-green-100 text-green-600' },
              { icon: TrainFront, title: 'Metro', desc: 'Metro tickets for major cities', color: 'bg-blue-100 text-blue-600' },
              { icon: Bus, title: 'Bus', desc: 'Inter-city and intra-city buses', color: 'bg-orange-100 text-orange-600' },
              { icon: Plane, title: 'Flight', desc: 'Domestic and international flights', color: 'bg-purple-100 text-purple-600' },
            ].map(({ icon: Icon, title, desc, color }) => (
              <div key={title} className="card hover:shadow-lg transition-shadow cursor-pointer">
                <div className={`${color} w-16 h-16 rounded-full flex items-center justify-center mb-4`}>
                  <Icon className="h-8 w-8" />
                </div>
                <h3 className="text-xl font-semibold mb-2">{title}</h3>
                <p className="text-gray-600">{desc}</p>
              </div>
            ))}
          </div>
        </div>
      </div>
    </div>
  );
};

export default Home;