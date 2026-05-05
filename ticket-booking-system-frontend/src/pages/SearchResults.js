import React, { useState, useEffect } from 'react';
import { useLocation, useNavigate } from 'react-router-dom';
import { searchAPI } from '../services/api';
import { toast } from 'react-toastify';
import { Train, Bus, Plane, TrainFront, Clock, ArrowRight, Star, Wifi, Coffee, Plug } from 'lucide-react';

const SearchResults = () => {
  const location = useLocation();
  const navigate = useNavigate();
  const [results, setResults] = useState([]);
  const [loading, setLoading] = useState(true);
  const searchData = location.state?.searchData;

  useEffect(() => {
    if (!searchData) {
      navigate('/');
      return;
    }
    fetchResults();
  }, []);

  const fetchResults = async () => {
    try {
      const response = await searchAPI.searchTransports(searchData);
      setResults(response.data.data || []);
    } catch (error) {
      toast.error('Failed to fetch search results');
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

  const getAmenityIcon = (amenity) => {
    switch (amenity.toLowerCase()) {
      case 'wifi': return Wifi;
      case 'food': return Coffee;
      case 'charging': return Plug;
      default: return Star;
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
    <div className="max-w-7xl mx-auto px-4 py-8">
      <div className="mb-6">
        <h2 className="text-2xl font-bold">
          {searchData?.source} <ArrowRight className="inline h-5 w-5 mx-2" /> {searchData?.destination}
        </h2>
        <p className="text-gray-600">{searchData?.journeyDate} | {results.length} results found</p>
      </div>

      {results.length === 0 ? (
        <div className="text-center py-12">
          <p className="text-gray-500 text-lg">No transports found for this route</p>
        </div>
      ) : (
        <div className="space-y-4">
          {results.map((transport) => {
            const Icon = getIcon(transport.type);
            return (
              <div key={transport.transportId} className="card hover:shadow-lg transition-shadow">
                <div className="flex flex-col md:flex-row justify-between items-start md:items-center">
                  <div className="flex-1">
                    <div className="flex items-center space-x-3 mb-2">
                      <div className="bg-blue-100 p-2 rounded-lg">
                        <Icon className="h-6 w-6 text-blue-600" />
                      </div>
                      <div>
                        <h3 className="text-lg font-semibold">{transport.name}</h3>
                        <p className="text-sm text-gray-500">{transport.number}</p>
                      </div>
                    </div>

                    <div className="flex items-center space-x-6 mt-4">
                      <div className="text-center">
                        <p className="text-lg font-bold">{transport.departureTime}</p>
                        <p className="text-sm text-gray-500">{transport.source}</p>
                      </div>
                      <div className="flex flex-col items-center">
                        <p className="text-sm text-gray-500">{transport.duration}</p>
                        <div className="w-24 h-0.5 bg-gray-300 my-1"></div>
                        <p className="text-xs text-gray-400">{transport.distanceKm} km</p>
                      </div>
                      <div className="text-center">
                        <p className="text-lg font-bold">{transport.arrivalTime}</p>
                        <p className="text-sm text-gray-500">{transport.destination}</p>
                      </div>
                    </div>

                    <div className="flex items-center space-x-4 mt-3">
                      {transport.amenities?.map((amenity) => {
                        const AmenityIcon = getAmenityIcon(amenity);
                        return (
                          <span key={amenity} className="flex items-center text-sm text-gray-600">
                            <AmenityIcon className="h-4 w-4 mr-1" />
                            {amenity}
                          </span>
                        );
                      })}
                    </div>
                  </div>

                  <div className="mt-4 md:mt-0 text-right">
                    <p className="text-2xl font-bold text-blue-600">Rs. {transport.baseFare}</p>
                    <p className="text-sm text-gray-500">{transport.availableSeats} seats available</p>
                    <button
                      onClick={() => navigate(`/book/${transport.transportId}`, { state: { transport, searchData } })}
                      className="btn-primary mt-2"
                    >
                      Book Now
                    </button>
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

export default SearchResults;