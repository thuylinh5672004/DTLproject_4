import React from "react";

const StatCard = ({ title, value, icon, color }) => {
  return (
    <div className="bg-white shadow-md rounded-2xl p-5 flex justify-between items-center hover:shadow-lg transition">
      <div className={`text-white text-3xl p-3 rounded-full ${color}`}>
        {icon}
      </div>
      <div className="text-right">
        <h4 className="text-gray-500 text-sm">{title}</h4>
        <p className="text-2xl font-bold text-gray-800">{value}</p>
      </div>
    </div>
  );
};

export default StatCard;
