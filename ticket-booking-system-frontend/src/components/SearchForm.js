import React, { useState } from 'react';
import { useNavigate } from 'react-router-dom';
import DatePicker from 'react-datepicker';
import 'react-datepicker/dist/react-datepicker.css';
import { Search, Train, Bus, Plane, TrainFront, ArrowRightLeft } from 'lucide-react';

const SearchForm = () => {
  const navigate = useNavigate();
  const [source, setSource] = useState('');
  const [destination, setDestination] = useState('');
  const [journeyDate, setJourneyDate] = useState(new Date());
  const [transportType, setTransportType] = useState('TRAIN');
  const [passengers, setPassengers] = useState(1);

  const handleSearch = (e) => {
    e.preventDefault();
    const searchData = {
      source,
      destination,
      journeyDate: journeyDate.toISOString().split('T')[0],
      transportType,
      passengers,
    };
    navigate('/search', { state: { searchData } });
  };

  const swapLocations = () => {
    setSource(destination);
    setDestination(source);
  };

  return (
    <div className="card max-w-4xl mx-auto">
      <div className="flex justify-center space-x-4 mb-6">
        {[
          { type: 'TRAIN', icon: Train, label: 'Train' },
          { type: 'METRO', icon: TrainFront, label: 'Metro' },
          { type: 'BUS', icon: Bus, label: 'Bus' },
          { type: 'FLIGHT', icon: Plane, label: 'Flight' },
        ].map(({ type, icon: Icon, label }) => (
          <button
            key={type}
            onClick={() => setTransportType(type)}
            className={`flex flex-col items-center p-3 rounded-lg transition-colors ${
              transportType === type ? 'bg-blue-100 text-blue-600' : 'text-gray-600 hover:bg-gray-100'
            }`}
          >
            <Icon className="h-6 w-6 mb-1" />
            <span className="text-sm font-medium">{label}</span>
          </button>
        ))}
      </div>

      <form onSubmit={handleSearch} className="space-y-4">
        <div className="grid grid-cols-1 md:grid-cols-2 gap-4">
          <div>
            <label className="block text-sm font-medium text-gray-700 mb-1">From</label>
            <input
              type="text"
              value={source}
              onChange={(e) => setSource(e.target.value)}
              placeholder="Enter source city"
              className="input-field"
              required
            />
          </div>
          <div className="relative">
            <label className="block text-sm font-medium text-gray-700 mb-1">To</label>
            <input
              type="text"
              value={destination}
              onChange={(e) => setDestination(e.target.value)}
              placeholder="Enter destination city"
              className="input-field"
              required
            />
            <button
              type="button"
              onClick={swapLocations}
              className="absolute right-2 top-8 text-gray-400 hover:text-blue-600"
            >
              <ArrowRightLeft className="h-5 w-5" />
            </button>
          </div>
        </div>

        <div className="grid grid-cols-1 md:grid-cols-3 gap-4">
          <div>
            <label className="block text-sm font-medium text-gray-700 mb-1">Journey Date</label>
            <DatePicker
              selected={journeyDate}
              onChange={(date) => setJourneyDate(date)}
              minDate={new Date()}
              className="input-field"
              dateFormat="yyyy-MM-dd"
            />
          </div>
          <div>
            <label className="block text-sm font-medium text-gray-700 mb-1">Passengers</label>
            <input
              type="number"
              min="1"
              max="10"
              value={passengers}
              onChange={(e) => setPassengers(parseInt(e.target.value))}
              className="input-field"
            />
          </div>
          <div className="flex items-end">
            <button type="submit" className="btn-primary w-full flex items-center justify-center space-x-2">
              <Search className="h-5 w-5" />
              <span>Search</span>
            </button>
          </div>
        </div>
      </form>
    </div>
  );
};

export default SearchForm;