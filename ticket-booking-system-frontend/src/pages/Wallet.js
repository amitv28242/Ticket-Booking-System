import React, { useState, useEffect } from 'react';
import { walletAPI } from '../services/api';
import { toast } from 'react-toastify';
import { Wallet as WalletIcon, Plus, ArrowDownCircle, ArrowUpCircle, History, IndianRupee } from 'lucide-react';

const Wallet = () => {
  const [wallet, setWallet] = useState(null);
  const [transactions, setTransactions] = useState([]);
  const [amount, setAmount] = useState('');
  const [loading, setLoading] = useState(true);

  useEffect(() => {
    fetchWalletData();
  }, []);

  const fetchWalletData = async () => {
    try {
      const [walletRes, transRes] = await Promise.all([
        walletAPI.getWallet(),
        walletAPI.getTransactions()
      ]);
      setWallet(walletRes.data.data);
      setTransactions(transRes.data.data || []);
    } catch (error) {
      toast.error('Failed to fetch wallet data');
    } finally {
      setLoading(false);
    }
  };

  const handleAddMoney = async () => {
    if (!amount || amount <= 0) {
      toast.error('Please enter a valid amount');
      return;
    }
    try {
      await walletAPI.addMoney({ amount: parseFloat(amount), paymentMethod: 'UPI' });
      toast.success('Money added successfully');
      setAmount('');
      fetchWalletData();
    } catch (error) {
      toast.error(error.response?.data?.message || 'Failed to add money');
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
      <h2 className="text-2xl font-bold mb-6">My Wallet</h2>

      <div className="card mb-6 bg-gradient-to-r from-blue-600 to-blue-800 text-white">
        <div className="flex items-center justify-between">
          <div>
            <p className="text-blue-100 mb-1">Available Balance</p>
            <p className="text-4xl font-bold flex items-center">
              <IndianRupee className="h-8 w-8 mr-1" />
              {wallet?.balance || '0.00'}
            </p>
          </div>
          <WalletIcon className="h-16 w-16 text-blue-200 opacity-50" />
        </div>
      </div>

      <div className="card mb-6">
        <h3 className="text-lg font-semibold mb-4">Add Money</h3>
        <div className="flex space-x-4">
          <input
            type="number"
            placeholder="Enter amount"
            value={amount}
            onChange={(e) => setAmount(e.target.value)}
            className="input-field flex-1"
          />
          <button onClick={handleAddMoney} className="btn-primary flex items-center">
            <Plus className="h-5 w-5 mr-1" />
            Add Money
          </button>
        </div>
        <div className="flex space-x-2 mt-3">
          {[100, 500, 1000, 2000].map((amt) => (
            <button
              key={amt}
              onClick={() => setAmount(amt.toString())}
              className="px-4 py-2 border border-gray-200 rounded-lg hover:bg-gray-50 text-sm"
            >
              Rs. {amt}
            </button>
          ))}
        </div>
      </div>

      <div className="card">
        <h3 className="text-lg font-semibold mb-4 flex items-center">
          <History className="h-5 w-5 mr-2" />
          Transaction History
        </h3>

        {transactions.length === 0 ? (
          <p className="text-gray-500 text-center py-4">No transactions yet</p>
        ) : (
          <div className="space-y-3">
            {transactions.map((transaction) => (
              <div key={transaction.id} className="flex items-center justify-between p-3 border rounded-lg">
                <div className="flex items-center space-x-3">
                  {transaction.type === 'CREDIT' ? (
                    <ArrowDownCircle className="h-8 w-8 text-green-500" />
                  ) : (
                    <ArrowUpCircle className="h-8 w-8 text-red-500" />
                  )}
                  <div>
                    <p className="font-medium">{transaction.description}</p>
                    <p className="text-sm text-gray-500">{transaction.referenceId}</p>
                  </div>
                </div>
                <div className="text-right">
                  <p className={`font-semibold ${transaction.type === 'CREDIT' ? 'text-green-600' : 'text-red-600'}`}>
                    {transaction.type === 'CREDIT' ? '+' : '-'} Rs. {transaction.amount}
                  </p>
                  <p className="text-xs text-gray-500">
                    {new Date(transaction.createdAt).toLocaleDateString()}
                  </p>
                </div>
              </div>
            ))}
          </div>
        )}
      </div>
    </div>
  );
};

export default Wallet;